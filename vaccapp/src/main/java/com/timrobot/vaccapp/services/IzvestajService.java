package com.timrobot.vaccapp.services;

import com.timrobot.vaccapp.dao.DataAccessLayer;
import com.timrobot.vaccapp.models.Izvestaj;
import com.timrobot.vaccapp.models.Obrazac;
import com.timrobot.vaccapp.models.TPrimljenaVakcina;
import com.timrobot.vaccapp.models.Zahtev;
import com.timrobot.vaccapp.utility.XMLMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class IzvestajService {
    private final String folderId = "/db/vacc-app/izvestaj";

    @Autowired
    private DataAccessLayer dataAccessLayer;

    @Autowired
    private XMLMapper mapper;

    @Autowired
    private ZahtevZaSertifikatService zahtevZaSertifikatService;

    @Autowired
    private SertifikatService sertifikatService;

    @Autowired
    private ObrazacInteresovanjaService obrazacInteresovanjaService;

    @Autowired
    private SaglasnostService saglasnostService;

    public Izvestaj getXmlAsObject(String documentId) {
        String xmlString = dataAccessLayer
                .getDocument(folderId, documentId)
                .get();

        return (Izvestaj) mapper.convertToObject(xmlString, "izvestaj_o_imunizaciji", Izvestaj.class);
    }

    public Izvestaj saveXmlFromText(Izvestaj izvestaj) {
        String datumOd = izvestaj.getPeriodIzvestaja().getDatumOd().getValue().toString();
        String datumDo = izvestaj.getPeriodIzvestaja().getDatumDo().getValue().toString();
        String documentId = datumOd + "/" + datumDo + ".xml";
        dataAccessLayer.saveDocument(izvestaj, folderId, documentId, Izvestaj.class);
        return izvestaj;
    }

    public Izvestaj getOrCreateIzvestajForDateRange(String datumOdStr, String datumDoStr) throws DatatypeConfigurationException {
        XMLGregorianCalendar datumOd = DatatypeFactory.newInstance().newXMLGregorianCalendar(datumOdStr);
        XMLGregorianCalendar datumDo = DatatypeFactory.newInstance().newXMLGregorianCalendar(datumDoStr);

        if (datumDo.toGregorianCalendar().getTime().before(datumOd.toGregorianCalendar().getTime())) {
            return null;
        }

        Izvestaj izvestaj = new Izvestaj();
        LocalDateTime localDatetime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        String formattedDate = localDatetime.format(formatter);
        XMLGregorianCalendar calendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(formattedDate);
        Izvestaj.Datum datum = new Izvestaj.Datum();
        datum.setProperty("pred:datum_zahtevan");
        datum.setValue(calendar);
        izvestaj.setDatum(datum);
        Izvestaj.PeriodIzvestaja periodIzvestaja = new Izvestaj.PeriodIzvestaja();
        Izvestaj.PeriodIzvestaja.DatumOd izvestajDatumOd = new Izvestaj.PeriodIzvestaja.DatumOd();
        izvestajDatumOd.setProperty("pred:datum_od");
        izvestajDatumOd.setValue(datumOd);
        Izvestaj.PeriodIzvestaja.DatumDo izvestajDatumDo = new Izvestaj.PeriodIzvestaja.DatumDo();
        izvestajDatumDo.setProperty("pred:datum_do");
        izvestajDatumDo.setValue(datumDo);
        periodIzvestaja.setDatumOd(izvestajDatumOd);
        periodIzvestaja.setDatumDo(izvestajDatumDo);
        izvestaj.setPeriodIzvestaja(periodIzvestaja);

        // broj zahteva za zeleni sertifikat
        int brojZahteva = zahtevZaSertifikatService.getAllForDateRangeInclusive(datumOdStr, datumDoStr).getItems().size();
        izvestaj.setZahteviZaZeleniSertifikat(BigInteger.valueOf(brojZahteva));

        // broj sertifikata
        int brojSertifikata = sertifikatService.getAllForDateRangeInclusive(datumOdStr, datumDoStr).getItems().size();
        izvestaj.setIzdatihZelenihSertifikata(BigInteger.valueOf(brojSertifikata));

        // broj interesovanja
        int brojInteresovanja = obrazacInteresovanjaService.getAllForDateRangeInclusive(datumOdStr, datumDoStr).getItems().size();
        izvestaj.setPodnetihInteresovanjaImunizacije(BigInteger.valueOf(brojInteresovanja));

        // ukupan broj doza, broj prvih, drugih i trecih doza, kao i raspodela po proizvodjacima
        Izvestaj.PodaciODatimDozama podaciODatimDozama = new Izvestaj.PodaciODatimDozama();
        Izvestaj.RaspodelaPoProizvodjacima raspodelaPoProizvodjacima = new Izvestaj.RaspodelaPoProizvodjacima();
        int ukupan = 0, prvih = 0, drugih = 0, trecih = 0, pfizer = 0, sinopharm = 0, sputnik = 0, az = 0;
        for (Obrazac saglasnost : saglasnostService.getAll().getItems()) {
            List<TPrimljenaVakcina> primljeneVakcine = saglasnost.getEvidencijaOVakcinaciji().getPodaciOIzvrsenimImunizacijama().getPrimljenaVakcina();
            try {
                TPrimljenaVakcina prvaDoza = primljeneVakcine.get(0);
                if (!prvaDoza.getDatumIzdavanja().toGregorianCalendar().getTime().before(datumOd.toGregorianCalendar().getTime()) &&
                        !prvaDoza.getDatumIzdavanja().toGregorianCalendar().getTime().after(datumDo.toGregorianCalendar().getTime())) {
                    prvih++;
                    ukupan++;
                    if (prvaDoza.getNaziv().getValue().toLowerCase().contains("pfizer")) {
                        pfizer++;
                    }
                    if (prvaDoza.getNaziv().getValue().toLowerCase().contains("sinopharm")) {
                        sinopharm++;
                    }
                    if (prvaDoza.getNaziv().getValue().toLowerCase().contains("sputnik")) {
                        sputnik++;
                    }
                    if (prvaDoza.getNaziv().getValue().toLowerCase().contains("az")) {
                        az++;
                    }
                }
                TPrimljenaVakcina drugaDoza = primljeneVakcine.get(1);
                if (!drugaDoza.getDatumIzdavanja().toGregorianCalendar().getTime().before(datumOd.toGregorianCalendar().getTime()) &&
                        !drugaDoza.getDatumIzdavanja().toGregorianCalendar().getTime().after(datumDo.toGregorianCalendar().getTime())) {
                    drugih++;
                    ukupan++;
                    if (drugaDoza.getNaziv().getValue().toLowerCase().contains("pfizer")) {
                        pfizer++;
                    }
                    if (drugaDoza.getNaziv().getValue().toLowerCase().contains("sinopharm")) {
                        sinopharm++;
                    }
                    if (drugaDoza.getNaziv().getValue().toLowerCase().contains("sputnik")) {
                        sputnik++;
                    }
                    if (drugaDoza.getNaziv().getValue().toLowerCase().contains("az")) {
                        az++;
                    }
                }
            } catch (Exception ignored) {

            }
        }
        podaciODatimDozama.setUkupnoDoza(BigInteger.valueOf(ukupan));
        podaciODatimDozama.setBrojDatePrveDoze(BigInteger.valueOf(prvih));
        podaciODatimDozama.setBrojDateDrugeDoze(BigInteger.valueOf(drugih));
        podaciODatimDozama.setBrojDateTreceDoze(BigInteger.valueOf(trecih));
        raspodelaPoProizvodjacima.setBrojPfizerVakcina(BigInteger.valueOf(pfizer));
        raspodelaPoProizvodjacima.setBrojSinopharmVakcina(BigInteger.valueOf(sinopharm));
        raspodelaPoProizvodjacima.setBrojAzVakcina(BigInteger.valueOf(az));
        raspodelaPoProizvodjacima.setBrojSputnikVakcina(BigInteger.valueOf(sputnik));
        izvestaj.setPodaciODatimDozama(podaciODatimDozama);
        izvestaj.setRaspodelaPoProizvodjacima(raspodelaPoProizvodjacima);

        this.saveXmlFromText(izvestaj);

        return izvestaj;
    }

}
