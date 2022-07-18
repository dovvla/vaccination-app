package com.timrobot.vaccapp.services;

import com.timrobot.vaccapp.dao.DataAccessLayer;
import com.timrobot.vaccapp.exceptions.ResourceAlreadyExistsException;
import com.timrobot.vaccapp.models.*;
import com.timrobot.vaccapp.utility.EmailServiceImpl;
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
import java.math.BigInteger;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
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

    @Autowired
    private PotvrdaOVakcinacijiService potvrdaOVakcinacijiService;

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

    private Date stringToDate(String date) {
        ZoneId defaultZoneId = ZoneId.systemDefault();

        //creating the instance of LocalDate using the day, month, year info
        LocalDate localDate = LocalDate.parse(date);

        //local date + atStartOfDay() + default time zone + toInstant() = Date
        return Date.from(localDate
                .atStartOfDay(defaultZoneId)
                .toInstant());
    }

    public EntityList<Zahtev> getAllForDateRangeInclusive(String startDate, String endDate) throws DatatypeConfigurationException {
        return new EntityList<>(dataAccessLayer
                .getAllDocuments(folderId)
                .stream()
                .map(s -> (Zahtev) mapper.convertToObject(s, "zahtev_za_sertifikat", Zahtev.class))
                .filter(zahtev -> !zahtev
                        .getDatum()
                        .toGregorianCalendar()
                        .getTime()
                        .before(stringToDate(startDate)) && !zahtev
                        .getDatum()
                        .toGregorianCalendar()
                        .getTime()
                        .after(stringToDate(endDate)))
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

    public boolean prihvatiZahtev(String id) throws DatatypeConfigurationException, TransformerException {
        Zahtev zahtev = this.getXmlAsObject(id);

        if (zahtev == null || zahtev.getStatus().equals("Odbijen") || zahtev.getStatus().equals("Prihvacen"))
            return false;

        zahtev.setStatus("Prihvacen");

        this.saveXmlFromText(zahtev);

        String email = korisnikService.getAll().getItems().stream()
                .filter(k -> k.getJmbg().equals(zahtev.getPodaciOPodnosiocu().getJMBG()))
                .collect(Collectors.toList())
                .get(0).getEmail();

        Sertifikat sertifikat = new Sertifikat();
        // fake test example
        TKovidTest tKovidTest = new TKovidTest();
        LocalDateTime localDatetime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        String formattedDate = localDatetime.format(formatter);
        XMLGregorianCalendar calendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(formattedDate);
        tKovidTest.setDatumIVremeUzorkovanja(calendar);
        tKovidTest.setLaboratorija("MecenskaLab");
        tKovidTest.setNazivTesta("PCR");
        tKovidTest.setProizvodjacTesta("WHO");
        tKovidTest.setRezultat("Negativan");
        tKovidTest.setVrstaUzorka("Bris nosa");
        sertifikat.getKovidTest().add(tKovidTest);
        Obrazac saglasnost = saglasnostService.getAllForUser(zahtev.getPodaciOPodnosiocu().getJMBG()).getItems().get(0);
        Potvrda potvrda = potvrdaOVakcinacijiService.getAllForUser(zahtev.getPodaciOPodnosiocu().getJMBG()).getItems().get(0);
//        for (TDozaVakcine doza : potvrda.getDozaVakcine()) {
//            TDozaVakcinacije tDozaVakcinacije = new TDozaVakcinacije();
//            tDozaVakcinacije.setDatum(doza.getDatumDavanja());
//            tDozaVakcinacije.setSerija(doza.getSerija());
//            tDozaVakcinacije.setProizvodjac(saglasnost.getEvidencijaOVakcinaciji().getPodaciOIzvrsenimImunizacijama().getPrimljenaVakcina());
//            tDozaVakcinacije.setZdravstvenaUstanova(potvrda.getZdravstvenaUstanova());
//            tDozaVakcinacije.setTip(potvrda.getNazivVakcine());
//        }
        for (TPrimljenaVakcina doza : saglasnost.getEvidencijaOVakcinaciji().getPodaciOIzvrsenimImunizacijama().getPrimljenaVakcina()) {
            TDozaVakcinacije tDozaVakcinacije = new TDozaVakcinacije();
            tDozaVakcinacije.setDatum(doza.getDatumIzdavanja());
            tDozaVakcinacije.setSerija(doza.getSerijaVakcine());
            tDozaVakcinacije.setProizvodjac(doza.getProizvodjac());
            tDozaVakcinacije.setZdravstvenaUstanova(potvrda.getZdravstvenaUstanova().getValue());
            tDozaVakcinacije.setTip(doza.getNaziv().getValue());
            sertifikat.getDozaVakcinacije().add(tDozaVakcinacije);
        }
        Sertifikat.PodaciOPacijentu podaciOPacijentu = new Sertifikat.PodaciOPacijentu();
        podaciOPacijentu.setJMBG(zahtev.getPodaciOPodnosiocu().getJMBG());
        podaciOPacijentu.setDatumRodjenja(zahtev.getPodaciOPodnosiocu().getDatumRodjenja());
        TImePacijenta tImePacijenta = new TImePacijenta();
        tImePacijenta.setDatatype("xs:string");
        tImePacijenta.setProperty("pred:ime");
        tImePacijenta.setValue(zahtev.getPodaciOPodnosiocu().getIme());
        podaciOPacijentu.setIme(tImePacijenta);
        podaciOPacijentu.setPol(zahtev.getPodaciOPodnosiocu().getPol());
        TPrezimePacijenta tPrezimePacijenta = new TPrezimePacijenta();
        tPrezimePacijenta.setDatatype("xs:string");
        tPrezimePacijenta.setProperty("pred:prezime");
        tPrezimePacijenta.setValue(zahtev.getPodaciOPodnosiocu().getPrezime());
        podaciOPacijentu.setPrezime(tPrezimePacijenta);
        sertifikat.setPodaciOPacijentu(podaciOPacijentu);
        Sertifikat.PodaciOSertifikatu podaciOSertifikatu = new Sertifikat.PodaciOSertifikatu();
        podaciOSertifikatu.setBroj(new BigInteger(32, new SecureRandom()));
        TDatumIVremeIzdavanja tDatumIVremeIzdavanja = new TDatumIVremeIzdavanja();
        tDatumIVremeIzdavanja.setDatatype("xs:dateTime");
        tDatumIVremeIzdavanja.setProperty("pred:datum");
        tDatumIVremeIzdavanja.setValue(calendar);
        podaciOSertifikatu.setDatumIVreme(tDatumIVremeIzdavanja);
        sertifikat.setPodaciOSertifikatu(podaciOSertifikatu);

        sertifikat.getPodaciOSertifikatu().setQRkod(QRcodeUtils.writeQRCode("http://localhost:8081/api/sertifikat/" + sertifikat.getPodaciOSertifikatu().getBroj() + "/xhtml"));

        // sertifikat se cuva u xml bazi
        String sertifikatDocumentId = sertifikat.getPodaciOSertifikatu().getBroj() + ".xml";
        sertifikat.setAbout("http://tim.robot/zeleni_sertifikat/" + sertifikat.getPodaciOSertifikatu().getBroj());
        sertifikat.setRel("pred:fromZahtev");
        sertifikat.setHref("http://tim.robot/zahtev_za_sertifikat/" + id);
        dataAccessLayer.saveDocument(sertifikat, "/db/vacc-app/sertifikat", sertifikatDocumentId, Sertifikat.class);

        // ----------- RDF -------------
        String sertifikatXML = mapper.convertToXml(sertifikat, Sertifikat.class);

        FusekiUtil.extractMetadataFromXML(sertifikatXML);

        String graphURI = "sertifikat";

        try {
            FusekiUtil.saveRDFToFuseki(graphURI);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // ----------- RDF -------------

        // TO DO : dobaviti sacuvani sertifikat u XHTML & PDF formi za download u mejlu

        emailService.sendSimpleMessage(email, "Prihvacen zahtev", "Zahtev za izdavanje zelenog sertifikata je prihvacen.");

        return true;
    }


}
