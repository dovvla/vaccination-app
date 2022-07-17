package com.timrobot.vaccapp.services;

import com.timrobot.vaccapp.dao.DataAccessLayer;
import com.timrobot.vaccapp.models.BrojVakcina;
import com.timrobot.vaccapp.models.EntityList;
import com.timrobot.vaccapp.models.ObrazacInteresovanja;
import com.timrobot.vaccapp.models.Termin;
import com.timrobot.vaccapp.utility.XMLMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.datatype.DatatypeConfigurationException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BrojVakcinaService {
    private final String folderId = "/db/vacc-app/broj-vakcina";

    @Autowired
    private DataAccessLayer dataAccessLayer;

    @Autowired
    private XMLMapper mapper;

    @Autowired
    private ObrazacInteresovanjaService obrazacInteresovanjaService;

    @Autowired
    private TerminService terminService;

    public BrojVakcina getXmlAsObject(String documentId) {
        try {
        String xmlString = dataAccessLayer
                .getDocument(folderId, documentId)
                .get();

            return (BrojVakcina) mapper.convertToObject(xmlString, "broj_vakcina", BrojVakcina.class);
        }
        catch (Exception ignored) {
            BrojVakcina brojVakcina = new BrojVakcina();
            brojVakcina.setBroj(0);
            brojVakcina.setVakcina(documentId);
            saveXmlFromText(brojVakcina);
            return brojVakcina;
        }
    }

    public BrojVakcina saveXmlFromText(BrojVakcina brojVakcina) {
        String documentId = brojVakcina.getVakcina() + ".xml";
        dataAccessLayer.saveDocument(brojVakcina, folderId, documentId, BrojVakcina.class);
        return brojVakcina;
    }

    public EntityList<BrojVakcina> getAll() {
        ArrayList<BrojVakcina> termins = (ArrayList<BrojVakcina>) dataAccessLayer
                .getAllDocuments(folderId)
                .stream()
                .map(s -> (BrojVakcina) mapper.convertToObject(s, "broj_vakcina", BrojVakcina.class))
                .collect(Collectors.toList());

        return new EntityList<>(termins);

    }

    public BrojVakcina izmeniBrojVakcina(String vakcina, Integer broj) throws DatatypeConfigurationException {
        BrojVakcina brojVakcina = getXmlAsObject(vakcina);
        brojVakcina.setBroj(brojVakcina.getBroj() + broj);
        if (brojVakcina.getBroj() < 0)
            brojVakcina.setBroj(0);
        saveXmlFromText(brojVakcina);
        if (broj > 1)
            this.popuniNedostajece();
        return brojVakcina;
    }

    private void popuniNedostajece() throws DatatypeConfigurationException {
        List<ObrazacInteresovanja> obrasci = obrazacInteresovanjaService
                .getAll()
                .getItems();

        List<Termin> termini = terminService
                .getAll()
                .getItems();

        Comparator<ObrazacInteresovanja> obrazacInteresovanjaDateComparator = Comparator
                .comparing(obrazacInteresovanja -> obrazacInteresovanja
                        .getDatum()
                        .toGregorianCalendar()
                        .getTime());

        boolean reservedSomething = false;
        for (BrojVakcina brojVakcina : this
                .getAll()
                .getItems()) {
            Optional<ObrazacInteresovanja> prioObrazacInteresovanja = obrasci
                    .stream()
                    .filter(obrazacInteresovanja -> obrazacInteresovanja
                            .getZeljenaVakcina()
                            .equals(brojVakcina.getVakcina())
                            || obrazacInteresovanja
                                    .getZeljenaVakcina()
                                    .equals("Bilo koja"))
                    .filter(obrazacInteresovanja -> termini
                            .stream()
                            .anyMatch(termin -> termin
                                    .getJmbg()
                                    .equals(obrazacInteresovanja
                                            .getLicniPodaci()
                                            .getJMBG())))
                    .min(obrazacInteresovanjaDateComparator);
            if (brojVakcina.getBroj() > 1 && prioObrazacInteresovanja.isPresent()) {
                obrazacInteresovanjaService.rezervisiTermin(prioObrazacInteresovanja.get());
                reservedSomething = true;
            }
        }
        if (reservedSomething)
            popuniNedostajece();

    }

    public int getBrojVakcina(String vakcina) {
        if (vakcina.equals("Bilo koja")) {
            for (BrojVakcina brojVakcina : this
                    .getAll()
                    .getItems()) {
                if (brojVakcina.getBroj() > 0)
                    return brojVakcina.getBroj();
            }
            return 0;
        }
        return getXmlAsObject(vakcina).getBroj();
    }

    public String getKokretnaVakcina() {
        for (BrojVakcina brojVakcina : this
                .getAll()
                .getItems()) {
            if (brojVakcina.getBroj() > 1)
                return brojVakcina.getVakcina();
        }
        return "";
    }
}
