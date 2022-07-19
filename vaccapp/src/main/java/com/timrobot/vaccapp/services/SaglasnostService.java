package com.timrobot.vaccapp.services;

import com.timrobot.vaccapp.dao.DataAccessLayer;
import com.timrobot.vaccapp.models.*;
import com.timrobot.vaccapp.utility.FusekiUtil;
import com.timrobot.vaccapp.utility.QRcodeUtils;
import com.timrobot.vaccapp.utility.XMLMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
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

    public Obrazac getXmlAsObject(String documentId) {
        String xmlString = dataAccessLayer
                .getDocument(folderId, documentId)
                .get();

        return (Obrazac) mapper.convertToObject(xmlString, "saglasnost", Obrazac.class);
    }

    public Obrazac putSaglasnost(Obrazac obrazac) throws TransformerException {
        String documentId = obrazac
                .getPodaciOPacijentu()
                .getDrzavljanstvo()
                .getJMBG()
                + ".xml";

        obrazac.setAbout("http://tim.robot/obrazac_saglasnosti_za_imunizaciju/" + obrazac
                .getPodaciOPacijentu()
                .getDrzavljanstvo()
                .getJMBG());
        obrazac.setRel("pred:fromObrazacInteresovanja");
        obrazac.setHref("http://tim.robot/iskazivanje_interesovanja_za_vakcinaciju/" + obrazac
                .getPodaciOPacijentu()
                .getDrzavljanstvo()
                .getJMBG());

        dataAccessLayer.saveDocument(obrazac, folderId, documentId, Obrazac.class);

        // ----------- RDF -------------
        String saglasnostXML = mapper.convertToXml(obrazac, Obrazac.class);

        FusekiUtil.extractMetadataFromXML(saglasnostXML);

        String graphURI = "saglasnost";

        try {
            FusekiUtil.saveRDFToFuseki(graphURI);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // ----------- RDF -------------

        return obrazac;
    }

    public XMLGregorianCalendar localDateTimeToXMLDate(LocalDateTime ldt) throws DatatypeConfigurationException {
        String iso = ldt.toString();
        if (ldt.getSecond() == 0 && ldt.getNano() == 0) {
            iso += ":00"; // necessary hack because the second part is not optional in XML
        }
        return DatatypeFactory
                .newInstance()
                .newXMLGregorianCalendar(iso);
    }

    public Obrazac imunizujGradjanina(Obrazac obrazac) throws DatatypeConfigurationException, TransformerException {

        Potvrda potvrda = new Potvrda();

        TDatum datum = new TDatum();
        datum.setDatatype("xs:date");
        datum.setProperty("pred:datum");
        datum.setValue(localDateTimeToXMLDate(LocalDateTime.now()));
        potvrda.setDatum(datum);
        potvrda.setNazivVakcine(obrazac
                .getEvidencijaOVakcinaciji()
                .getPodaciOIzvrsenimImunizacijama()
                .getPrimljenaVakcina()
                .get(0)
                .getNaziv().getValue().trim());
        Potvrda.PodaciPacijenta podaciPacijenta = new Potvrda.PodaciPacijenta();
        podaciPacijenta.setIme(obrazac
                .getPodaciOPacijentu()
                .getIme().getValue().trim());
        podaciPacijenta.setJMBG(obrazac
                .getPodaciOPacijentu()
                .getDrzavljanstvo()
                .getJMBG().trim());
        podaciPacijenta.setPol(obrazac
                .getPodaciOPacijentu()
                .getPol().trim());
        podaciPacijenta.setPrezime(obrazac
                .getPodaciOPacijentu()
                .getPrezime().getValue().trim());
        potvrda.setPodaciPacijenta(podaciPacijenta);
        // potvrda.setQRkod(QRcodeUtils.writeQRCode(potvrda.getSifraPotvrde()));
        TZdravstvenaUstanova tZdravstvenaUstanova = new TZdravstvenaUstanova();
        tZdravstvenaUstanova.setDatatype("xs:string");
        tZdravstvenaUstanova.setProperty("pred:zdravstvena_ustanova");
        tZdravstvenaUstanova.setValue(obrazac
                .getEvidencijaOVakcinaciji()
                .getZdravstvenaUstanova().trim());
        potvrda.setZdravstvenaUstanova(tZdravstvenaUstanova);

        // obrazac = this.getAllForUser(obrazac
        // .getPodaciOPacijentu()
        // .getDrzavljanstvo()
        // .getJMBG().trim()).getItems().get(0);
        obrazac
                .getEvidencijaOVakcinaciji()
                .getPodaciOIzvrsenimImunizacijama()
                .getPrimljenaVakcina()
                .forEach(tPrimljenaVakcina -> {
                    TDozaVakcine tDozaVakcine = new TDozaVakcine();
                    try {
                        tDozaVakcine.setDatumDavanja(localDateTimeToXMLDate(LocalDateTime.now()));
                    } catch (DatatypeConfigurationException e) {
                        throw new RuntimeException(e);
                    }
                    tDozaVakcine.setSerija(tPrimljenaVakcina.getSerijaVakcine().trim());
                    potvrda
                            .getDozaVakcine()
                            .add(tDozaVakcine);
                });
        Obrazac tmpObrazac = this.getAllForUser(obrazac
                .getPodaciOPacijentu()
                .getDrzavljanstvo()
                .getJMBG().trim()).getItems().get(0);
        tmpObrazac.setEvidencijaOVakcinaciji(obrazac.getEvidencijaOVakcinaciji());
        this.putSaglasnost(tmpObrazac);
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

        List<String> matchingSaglasnostiXML = dataAccessLayer.executeXPathQuery(folderId, searchQuery,
                "http://tim.robot/obrazac_saglasnosti_za_imunizaciju");

        List<Obrazac> matchingSaglasnosti = new ArrayList<>();
        for (String XML : matchingSaglasnostiXML) {
            matchingSaglasnosti.add((Obrazac) mapper.convertToObject(XML, "saglasnost", Obrazac.class));
        }

        return matchingSaglasnosti;
    }

    public List<Obrazac> advancedSearchSaglasnost(String ime, String prezime, String nazivVakcine,
            String datumIzdavanja, String hrefInteresovanje, boolean logicalAnd) throws IOException {
        ArrayList<String> queries = new ArrayList<>();
        if (!ime.trim().equals("")) {
            queries.add("?s <http://tim.robot/rdf/predicate/ime> ?X . FILTER(str(?X) = \"" + ime + "\")");
        }
        if (!prezime.trim().equals("")) {
            queries.add("?s <http://tim.robot/rdf/predicate/prezime> ?X . FILTER(str(?X) = \"" + prezime + "\")");
        }
        if (!nazivVakcine.trim().equals("")) {
            queries.add("?s <http://tim.robot/rdf/predicate/naziv_vakcine> ?X . FILTER(str(?X) = \"" + nazivVakcine
                    + "\")");
        }
        if (!datumIzdavanja.trim().equals("")) {
            queries.add("?s <http://tim.robot/rdf/predicate/datum> ?X . FILTER(str(?X) = \"" + datumIzdavanja + "\")");
        }
        if (!hrefInteresovanje.trim().equals("")) {
            queries.add("?s <http://tim.robot/rdf/predicate/fromObrazacInteresovanja> " + hrefInteresovanje + " .");
        }
        if (queries.isEmpty()) {
            queries.add("?s ?p ?o");
        }

        HashSet<String> resultSubjects = new HashSet<>();
        for (String query : queries) {
            HashSet<String> result = FusekiUtil.queryRdf("saglasnost", query);

            if (queries.indexOf(query) == 0) {
                resultSubjects.addAll(result);
            } else {
                if (logicalAnd) {
                    resultSubjects.retainAll(result);
                } else {
                    resultSubjects.addAll(result);
                }
            }
        }

        List<Obrazac> obrasci = new ArrayList<>();
        for (String subject : resultSubjects) {
            // subject je namespace
            String[] splitNamespace = subject.split("/");
            String documentId = splitNamespace[splitNamespace.length - 1];
            obrasci.add(this.getXmlAsObject(documentId));
        }

        return obrasci;
    }

    public Map<String, String> getAllMetadataForDocumentForJSON(String documentId) throws IOException {
        return FusekiUtil.getAllMetadataForDocument("saglasnost", "obrazac_saglasnosti_za_imunizaciju", documentId);
    }

    public String getAllMetadataForDocumentInRDF(String documentId) throws IOException {
        return FusekiUtil.getAllMetadataForDocumentInRDF("saglasnost", "obrazac_saglasnosti_za_imunizaciju",
                documentId);
    }

    public void popuniKorisnika(Obrazac obrazac, Korisnik korisnik) throws DatatypeConfigurationException {
        obrazac.getPodaciOPacijentu().getDrzavljanstvo().setJMBG(korisnik.getJmbg());
        obrazac.getPodaciOPacijentu().setPol(korisnik.getPol());
        obrazac.getPodaciOPacijentu().getIme().setValue(korisnik.getIme());
        obrazac.getPodaciOPacijentu().getIme().setDatatype("xs:string");
        obrazac.getPodaciOPacijentu().getPrezime().setDatatype("xs:string");
        obrazac.getPodaciOPacijentu().getPrezime().setProperty("pred:prezime");
        obrazac.getPodaciOPacijentu().getIme().setProperty("pred:ime");
        obrazac.getPodaciOPacijentu().getPrezime().setValue(korisnik.getPrezime());
        obrazac.getPodaciOPacijentu().setDatumRodjenja(korisnik.getDatumRodjenja());
        obrazac.getPodaciOPacijentu().setGrad(korisnik.getGrad());
        obrazac.getPodaciOPacijentu().setImejl(korisnik.getEmail());
        obrazac.getPodaciOPacijentu().setTelefonFiksni(korisnik.getFiksniTelefon());
        obrazac.getPodaciOPacijentu().setTelefonMobilni(korisnik.getMobilniTelefon());
        obrazac.getPodaciOPacijentu().setImeRoditelja(korisnik.getImeRoditelja());
        obrazac.getPodaciOPacijentu().setMestoRodjenja(korisnik.getMestoRodjenja());
        obrazac.getPodaciOSaglasnosti().getDatum().setValue(localDateTimeToXMLDate(LocalDateTime.now()));
        obrazac.getPodaciOSaglasnosti().getDatum().setDatatype("xs:date");
        obrazac.getPodaciOSaglasnosti().getDatum().setProperty("pred:datum");
    }
}
