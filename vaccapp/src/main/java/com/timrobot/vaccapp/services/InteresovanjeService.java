package com.timrobot.vaccapp.services;

import com.timrobot.vaccapp.dao.DataAccessLayer;
import com.timrobot.vaccapp.models.Korisnik;
import com.timrobot.vaccapp.models.ObrazacInteresovanja;
import com.timrobot.vaccapp.models.Zahtev;
import com.timrobot.vaccapp.utility.XMLMapper;
import org.apache.jena.base.Sys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBElement;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class InteresovanjeService {
    private final String folderId = "/db/vacc-app/korisnici";

    private XMLMapper mapper;


    public String convertToXML() throws DatatypeConfigurationException {
        mapper = new XMLMapper();
        ObrazacInteresovanja obrazacInteresovanja = new ObrazacInteresovanja();
        obrazacInteresovanja.setZeljenaVakcina("Sputnik");
        obrazacInteresovanja.setDavalacKrvi("Nije");
        obrazacInteresovanja.setZeljenaLokacijaVakcinacije("Novi Sad");
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(new Date());
        XMLGregorianCalendar xmlDate = DatatypeFactory.newInstance().newXMLGregorianCalendarDate(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH)+1, cal.get(Calendar.DAY_OF_MONTH), DatatypeConstants.FIELD_UNDEFINED);
        obrazacInteresovanja.setDatum(xmlDate);
        ObrazacInteresovanja.LicniPodaci licniPodaci = new ObrazacInteresovanja.LicniPodaci();
        licniPodaci.setBrojFiksnogTelefona("0232092332");
        licniPodaci.setBrojMobilnogTelefona("7643543543");
        licniPodaci.setDrzavljanstvo("Srbsko");
        licniPodaci.setIme("Vlado");
        licniPodaci.setPrezime("Vladic");
        licniPodaci.setImejl("nesto@nesto.com");
        licniPodaci.setJMBG("4585478954654");
        obrazacInteresovanja.setLicniPodaci(licniPodaci);
        System.out.println(mapper.convertToXml(obrazacInteresovanja, ObrazacInteresovanja.class));
        return mapper.convertToXml(obrazacInteresovanja, ObrazacInteresovanja.class);
    }

    public String zahtev() throws DatatypeConfigurationException {
        mapper = new XMLMapper();
        Zahtev zahtev = new Zahtev();
        zahtev.setMesto("Novi Sad");
        zahtev.setRazlogZaPodnosenje("Idk why");
        zahtev.setRazlogZaOdbijanje(new JAXBElement(new QName("http://tim.robot", "amount"), String.class, "Hello World"));
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(new Date());
        XMLGregorianCalendar xmlDate = DatatypeFactory.newInstance().newXMLGregorianCalendarDate(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH)+1, cal.get(Calendar.DAY_OF_MONTH), DatatypeConstants.FIELD_UNDEFINED);
        zahtev.setDatum(xmlDate);
        Zahtev.PodaciOPodnosiocu podaciOPodnosiocu = new Zahtev.PodaciOPodnosiocu();
        podaciOPodnosiocu.setBrojPasosa("232232332");
        podaciOPodnosiocu.setDatumRodjenja(xmlDate);
        podaciOPodnosiocu.setIme("Vlado");
        podaciOPodnosiocu.setPol("Musko");
        podaciOPodnosiocu.setJMBG("322323223");
        podaciOPodnosiocu.setPrezime("Vladic");
        zahtev.setPodaciOPodnosiocu(podaciOPodnosiocu);
        return mapper.convertToXml(zahtev, Zahtev.class);
    }
}
