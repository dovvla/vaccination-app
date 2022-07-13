package com.timrobot.vaccapp.services;

import com.timrobot.vaccapp.dao.DataAccessLayer;
import com.timrobot.vaccapp.models.*;
import com.timrobot.vaccapp.utility.XMLMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SaglasnostService {
    private final String folderId = "/db/vacc-app/saglasnost";

    @Autowired
    private DataAccessLayer dataAccessLayer;

    @Autowired
    private XMLMapper mapper;

    @Autowired
    private PotvrdaOVakcinacijiService potvrdaOVakcinacijiService;

    public EntityList<Obrazac> getAll() {
        return new EntityList<>(dataAccessLayer
                .getAllDocuments(folderId)
                .stream()
                .map(s -> (Obrazac) mapper.convertToObject(s, "saglasnost", Obrazac.class))
                .collect(Collectors.toList()));
    }

    public EntityList<Obrazac> getAllForUser(String id) {
        return new EntityList<>(dataAccessLayer
                .getAllDocuments(folderId)
                .stream()
                .map(s -> (Obrazac) mapper.convertToObject(s, "saglasnost", Obrazac.class))
                .filter(obrazac -> obrazac
                        .getPodaciOPacijentu()
                        .getDrzavljanstvo()
                        .getJMBG()
                        .equals(id))
                .collect(Collectors.toList()));
    }

    public Obrazac putSaglasnost(Obrazac obrazac) {
        String documentId = obrazac
                                    .getPodaciOPacijentu()
                                    .getDrzavljanstvo()
                                    .getJMBG()
                            + ".xml";
        dataAccessLayer.saveDocument(obrazac, folderId, documentId, Obrazac.class);
        return obrazac;
    }

    public XMLGregorianCalendar localDateTimeToXMLDate(LocalDateTime ldt) throws DatatypeConfigurationException {
        String iso = ldt.toString();
        if (ldt.getSecond() == 0 && ldt.getNano() == 0) {
            iso += ":00"; // necessary hack because the second part is not optional in XML
        }
        return
                DatatypeFactory
                        .newInstance()
                        .newXMLGregorianCalendar(iso);
    }

    public Obrazac imunizujGradjanina(Obrazac obrazac) throws DatatypeConfigurationException {

        Potvrda potvrda = new Potvrda();

        potvrda.setDatum(localDateTimeToXMLDate(LocalDateTime.now()));
        potvrda.setNazivVakcine(obrazac
                .getEvidencijaOVakcinaciji()
                .getPodaciOIzvrsenimImunizacijama()
                .getPrimljenaVakcina()
                .get(0)
                .getNaziv());
        Potvrda.PodaciPacijenta podaciPacijenta = new Potvrda.PodaciPacijenta();
        podaciPacijenta.setIme(obrazac
                .getPodaciOPacijentu()
                .getIme());
        podaciPacijenta.setJMBG(obrazac
                .getPodaciOPacijentu()
                .getDrzavljanstvo()
                .getJMBG());
        podaciPacijenta.setPol(obrazac
                .getPodaciOPacijentu()
                .getPol());
        podaciPacijenta.setPrezime(obrazac
                .getPodaciOPacijentu()
                .getPrezime());
        potvrda.setZdravstvenaUstanova(obrazac
                .getEvidencijaOVakcinaciji()
                .getZdravstvenaUstanova());

        obrazac
                .getEvidencijaOVakcinaciji()
                .getPodaciOIzvrsenimImunizacijama()
                .getPrimljenaVakcina()
                .forEach(tPrimljenaVakcina -> {
                    TDozaVakcine tDozaVakcine = new TDozaVakcine();
                    try {
                        tDozaVakcine.setDatumDavanja(localDateTimeToXMLDate(LocalDateTime.now()));
                    }
                    catch (DatatypeConfigurationException e) {
                        throw new RuntimeException(e);
                    }
                    tDozaVakcine.setSerija(tPrimljenaVakcina.getSerijaVakcine());
                    potvrda
                            .getDozaVakcine()
                            .add(tDozaVakcine);
                });
        potvrdaOVakcinacijiService.createPotvrda(potvrda);


        return obrazac;
    }

    public List<Obrazac> regularSearchSaglasnost(String search) throws Exception {
        String searchQuery = String.format("xquery version \"3.1\";\n" +
                "\n" +
                "declare namespace sagl=\"http://tim.robot/obrazac_saglasnosti_za_imunizaciju\";\n" +
                "import module namespace functx=\"http://www.functx.com\";\n" +
                "\n" +
                "declare function local:search($keyword as xs:string)\n" +
                "{\n" +
                "    let $saglasnosti := collection(\"/db/vacc-app/saglasnost\")\n" +
                "    let $rezultat :=\n" +
                "    (\n" +
                "        $saglasnosti//sagl:Obrazac[contains(., $keyword)]\n" +
                "    )\n" +
                "\n" +
                " return\n" +
                "    functx:distinct-nodes($rezultat)\n" +
                "};\n" +
                "\n" +
                "local:search(\"%s\")", search);

        List<String> matchingSaglasnostiXML = dataAccessLayer.executeXPathQuery(folderId, searchQuery, "http://tim.robot/obrazac_saglasnosti_za_imunizaciju");

        List<Obrazac> matchingSaglasnosti = new ArrayList<>();
        for (String XML : matchingSaglasnostiXML) {
            matchingSaglasnosti.add((Obrazac) mapper.convertToObject(XML, "saglasnost", Obrazac.class));
        }

        return matchingSaglasnosti;
    }
}
