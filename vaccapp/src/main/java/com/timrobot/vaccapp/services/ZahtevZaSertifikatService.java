package com.timrobot.vaccapp.services;

import com.timrobot.vaccapp.dao.DataAccessLayer;
import com.timrobot.vaccapp.exceptions.ResourceAlreadyExistsException;
import com.timrobot.vaccapp.models.*;
import com.timrobot.vaccapp.utility.EmailServiceImpl;
import com.timrobot.vaccapp.utility.XMLMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.GregorianCalendar;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ZahtevZaSertifikatService {
    private final String folderId = "/db/vacc-app/zahtev-zeleni";

    @Autowired
    private DataAccessLayer dataAccessLayer;

    @Autowired
    private XMLMapper mapper;

    @Autowired
    private KorisnikService korisnikService;

    @Autowired
    private EmailServiceImpl emailService;

    @Autowired
    private SaglasnostService saglasnostService;

    public Zahtev getXmlAsObject(String documentId) {
        String xmlString = dataAccessLayer
                .getDocument(folderId, documentId)
                .get();

        return (Zahtev) mapper.convertToObject(xmlString, "zahtev_za_sertifikat", Zahtev.class);
    }

    public Zahtev saveXmlFromText(Zahtev zahtev) {
        String documentId = zahtev.getIdentifikator() + ".xml";
        dataAccessLayer.saveDocument(zahtev, folderId, documentId, Zahtev.class);
        return zahtev;
    }

    public EntityList<Zahtev> getAll() {

        return new EntityList<>(dataAccessLayer
                .getAllDocuments(folderId)
                .stream()
                .map(s -> (Zahtev) mapper.convertToObject(s, "zahtev_za_sertifikat", Zahtev.class))
                .collect(Collectors.toList()));
    }

    public EntityList<Zahtev> getAllForUser(String id) {
        return new EntityList<>(dataAccessLayer
                .getAllDocuments(folderId)
                .stream()
                .map(s -> (Zahtev) mapper.convertToObject(s, "zahtev_za_sertifikat", Zahtev.class))
                .filter(zahtev -> zahtev
                        .getPodaciOPodnosiocu()
                        .getJMBG()
                        .equals(id))
                .collect(Collectors.toList()));
    }

    public Zahtev createZahtev(Zahtev zahtev) throws DatatypeConfigurationException {
        String identifikator = UUID.randomUUID().toString();
        String documentId = identifikator + ".xml";
        zahtev.setIdentifikator(identifikator);

        dataAccessLayer.saveDocument(zahtev, folderId, documentId, Zahtev.class);

        return zahtev;

    }

    public EntityList<Zahtev> getAllNeobradjen() {

        return new EntityList<>(dataAccessLayer
                .getAllDocuments(folderId)
                .stream()
                .map(s -> (Zahtev) mapper.convertToObject(s, "zahtev_za_sertifikat", Zahtev.class))
                .filter(z -> z.getStatus().equals("Neobradjen"))
                .collect(Collectors.toList()));
    }

    public boolean odbijZahtev(String id, String razlog) {
        Zahtev zahtev = this.getXmlAsObject(id);

        if (zahtev == null || zahtev.getStatus().equals("Odbijen") || zahtev.getStatus().equals("Prihvacen"))
            return false;

        ObjectFactory factory = new ObjectFactory();
        zahtev.setRazlogZaOdbijanje(factory.createZahtevRazlogZaOdbijanje(razlog));
        zahtev.setStatus("Odbijen");

        this.saveXmlFromText(zahtev);

        String email = korisnikService.getAll().getItems().stream()
                .filter(k -> k.getJmbg().equals(zahtev.getPodaciOPodnosiocu().getJMBG()))
                .collect(Collectors.toList())
                .get(0).getEmail();

        emailService.sendSimpleMessage(email, "Odbijen zahtev", "Zahtev za izdavanje zelenog sertifikata je odbijen. Razlog: " + razlog);

        return true;
    }

//    public boolean prihvatiZahtev(String id) throws DatatypeConfigurationException {
//        Zahtev zahtev = this.getXmlAsObject(id);
//
//        if (zahtev == null || zahtev.getStatus().equals("Odbijen") || zahtev.getStatus().equals("Prihvacen"))
//            return false;
//
//        zahtev.setStatus("Prihvacen");
//
//        this.saveXmlFromText(zahtev);
//
//        String email = korisnikService.getAll().getItems().stream()
//                .filter(k -> k.getJmbg().equals(zahtev.getPodaciOPodnosiocu().getJMBG()))
//                .collect(Collectors.toList())
//                .get(0).getEmail();
//
//        Sertifikat sertifikat = new Sertifikat();
//        // fake test example
//        TKovidTest tKovidTest = new TKovidTest();
//        LocalDateTime localDatetime = LocalDateTime.now();
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
//        String formattedDate = localDatetime.format(formatter);
//        XMLGregorianCalendar calendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(formattedDate);
//        tKovidTest.setDatumIVremeUzorkovanja(calendar);
//        tKovidTest.setLaboratorija("MecenskaLab");
//        tKovidTest.setNazivTesta("PCR");
//        tKovidTest.setProizvodjacTesta("WHO");
//        tKovidTest.setRezultat("Negativan");
//        tKovidTest.setVrstaUzorka("Bris nosa");
//        sertifikat.getKovidTest().add(tKovidTest);
//        TDozaVakcinacije tDozaVakcinacije = new TDozaVakcinacije();
//        Obrazac saglasnost = saglasnostService.getAllForUser(zahtev.getPodaciOPodnosiocu().getJMBG()).getItems().get(0);
//        Potvrda potvrda =
//
//        emailService.sendSimpleMessage(email, "Prihvacen zahtev", "Zahtev za izdavanje zelenog sertifikata je prihvacen.");
//
//        return true;
//    }
}
