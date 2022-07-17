package com.timrobot.vaccapp.services;

import com.timrobot.vaccapp.dao.DataAccessLayer;
import com.timrobot.vaccapp.models.*;
import com.timrobot.vaccapp.utility.QRcodeUtils;
import com.timrobot.vaccapp.utility.XMLMapper;
import org.apache.jena.base.Sys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;
import java.io.File;
import java.math.BigInteger;
import java.util.*;

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

//    public String sertifikat(){
//        try
//        {
//            mapper = new XMLMapper();
//            Sertifikat sertifikat = new Sertifikat();
//            Sertifikat.PodaciOSertifikatu podaciOSertifikatu = new Sertifikat.PodaciOSertifikatu();
//            Sertifikat.PodaciOPacijentu podaciOPacijentu = new Sertifikat.PodaciOPacijentu();
//            podaciOSertifikatu.setBroj(new BigInteger("2323455"));
//            GregorianCalendar cal = new GregorianCalendar();
//            cal.setTime(new Date());
//            XMLGregorianCalendar xmlDate = DatatypeFactory.newInstance().newXMLGregorianCalendarDate(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH)+1, cal.get(Calendar.DAY_OF_MONTH), DatatypeConstants.FIELD_UNDEFINED);
//            podaciOSertifikatu.setDatumIVreme(xmlDate);
//            podaciOPacijentu.setBrojPasosa("34344333");
//            podaciOPacijentu.setJMBG("1234567890123");
//            podaciOPacijentu.setDatumRodjenja(xmlDate);
//            podaciOPacijentu.setIme("Dano");
//            podaciOPacijentu.setPrezime("Dano");
//            podaciOPacijentu.setPol("Musko");
//            sertifikat.setPodaciOSertifikatu(podaciOSertifikatu);
//            sertifikat.setPodaciOPacijentu(podaciOPacijentu);
//
//            List<TDozaVakcinacije> dozaVakcinacijeList = new ArrayList<>();
//            List<TKovidTest> kovidTests = new ArrayList<>();
//            for(int i=1;i<3;i++){
//                TDozaVakcinacije dozaVakcinacije = new TDozaVakcinacije();
//                dozaVakcinacije.setDatum(xmlDate);
//                dozaVakcinacije.setProizvodjac("neko" + i);
//                dozaVakcinacije.setSerija("serija"+i);
//                dozaVakcinacije.setTip("tip"+i);
//                dozaVakcinacije.setZdravstvenaUstanova("ustanova"+i);
//                dozaVakcinacijeList.add(dozaVakcinacije);
//
//                TKovidTest kovidTest = new TKovidTest();
//                kovidTest.setDatumIVremeUzorkovanja(xmlDate);
//                kovidTest.setLaboratorija("lab"+i);
//                kovidTest.setNazivTesta("naziv"+i);
//                kovidTest.setProizvodjacTesta("proizvodjac"+i);
//                kovidTest.setRezultat("rez"+i);
//                kovidTest.setVrstaUzorka("vrsta"+i);
//                kovidTests.add(kovidTest);
//            }
//            sertifikat.getKovidTest().addAll(kovidTests);
//            sertifikat.getDozaVakcinacije().addAll(dozaVakcinacijeList);
//
//            String base64 = QRcodeUtils.writeQRCode(sertifikat.getPodaciOSertifikatu().getBroj().toString());
//            sertifikat.getPodaciOSertifikatu().setQrKod(base64);
//
//            return mapper.convertToXml(sertifikat, Sertifikat.class);
//        }
//        catch (Exception e)
//        {
//            return "";
//        }
//    }

//    public String potvrda(){
//        try{
//            mapper = new XMLMapper();
//            Potvrda potvrda = new Potvrda();
//            GregorianCalendar cal = new GregorianCalendar();
//            cal.setTime(new Date());
//            XMLGregorianCalendar xmlDate = DatatypeFactory.newInstance().newXMLGregorianCalendarDate(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH)+1, cal.get(Calendar.DAY_OF_MONTH), DatatypeConstants.FIELD_UNDEFINED);
//            potvrda.setDatum(xmlDate);
//            potvrda.setNazivVakcine("blabla");
//            potvrda.setSifraPotvrde("122122");
//            potvrda.setZdravstvenaUstanova("sdsdsfdf");
//            Potvrda.PodaciPacijenta podaciPacijenta = new Potvrda.PodaciPacijenta();
//            podaciPacijenta.setIme("dfdfdf");
//            podaciPacijenta.setJMBG("1234567890123");
//            podaciPacijenta.setPol("Musko");
//            podaciPacijenta.setPrezime("fdfgfddfg");
//            potvrda.setPodaciPacijenta(podaciPacijenta);
//            List<TDozaVakcine> dozaVakcines = new ArrayList<>();
//            for(int i=1;i<3;i++){
//                TDozaVakcine tDozaVakcine = new TDozaVakcine();
//                tDozaVakcine.setDatumDavanja(xmlDate);
//                tDozaVakcine.setSerija("serija"+i);
//                dozaVakcines.add(tDozaVakcine);
//            }
//            potvrda.getDozaVakcine().addAll(dozaVakcines);
//            String base64 = QRcodeUtils.writeQRCode(potvrda.getSifraPotvrde());
//            potvrda.setQrKod(base64);
//
//            return mapper.convertToXml(potvrda, Potvrda.class);
//        }catch (Exception e){
//            return "";
//        }
//    }

//    public String saglasnost(){
//        try {
//            mapper = new XMLMapper();
//            Obrazac obrazac = new Obrazac();
//            obrazac.setPodaciOPacijentu(new Obrazac.PodaciOPacijentu());
//            obrazac.getPodaciOPacijentu().setAdresa(new TAdresa());
//            obrazac.getPodaciOPacijentu().getAdresa().setBroj(new BigInteger("12"));
//            obrazac.getPodaciOPacijentu().getAdresa().setUlica("fdsdfsdsd");
//            GregorianCalendar cal = new GregorianCalendar();
//            cal.setTime(new Date());
//            XMLGregorianCalendar xmlDate = DatatypeFactory.newInstance().newXMLGregorianCalendarDate(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH)+1, cal.get(Calendar.DAY_OF_MONTH), DatatypeConstants.FIELD_UNDEFINED);
//            obrazac.getPodaciOPacijentu().setDatumRodjenja(xmlDate);
//            obrazac.getPodaciOPacijentu().setDrzavljanstvo(new Obrazac.PodaciOPacijentu.Drzavljanstvo());
//            obrazac.getPodaciOPacijentu().getDrzavljanstvo().setJMBG("1234567890123");
//            obrazac.getPodaciOPacijentu().getDrzavljanstvo().setStranoDrzavljanstvo(new Obrazac.PodaciOPacijentu.Drzavljanstvo.StranoDrzavljanstvo());
//            obrazac.getPodaciOPacijentu().getDrzavljanstvo().getStranoDrzavljanstvo().setBrPasosa("2232322");
//            obrazac.getPodaciOPacijentu().getDrzavljanstvo().getStranoDrzavljanstvo().setNaziv("dfdffd");
//            obrazac.getPodaciOPacijentu().setGrad("dfdf");
//            obrazac.getPodaciOPacijentu().setIme("Jalo");
//            obrazac.getPodaciOPacijentu().setImejl("dfdf@fdf");
//            obrazac.getPodaciOPacijentu().setImeRoditelja("dfdffddfdf");
//            obrazac.getPodaciOPacijentu().setKorisnikSocijalneZastite("DA");
//            obrazac.getPodaciOPacijentu().setMestoRodjenja("ddssdsd");
//            obrazac.getPodaciOPacijentu().setNaselje("dfdfdff");
//            obrazac.getPodaciOPacijentu().setPol("Musko");
//            obrazac.getPodaciOPacijentu().setPrezime("Jalic");
//            obrazac.getPodaciOPacijentu().setRadniStatus("Radnik");
//            obrazac.getPodaciOPacijentu().setTelefonFiksni("3445454545");
//            obrazac.getPodaciOPacijentu().setTelefonMobilni("34333434");
//            obrazac.getPodaciOPacijentu().setZanimanjeZaposlenog("nesto");
//            Obrazac.PodaciOPacijentu.SedisteSocijalneZastite sedisteSocijalneZastite = new Obrazac.PodaciOPacijentu.SedisteSocijalneZastite();
//            sedisteSocijalneZastite.setNaziv("dfdfdfdf");
//            sedisteSocijalneZastite.setOpstina("tyvfbfg");
//            obrazac.getPodaciOPacijentu().setSedisteSocijalneZastite(new JAXBElement(new QName("http://tim.robot/obrazac_saglasnosti_za_imunizaciju", "sedisteSocijalneZastite"), Obrazac.PodaciOPacijentu.SedisteSocijalneZastite.class, sedisteSocijalneZastite));
//
//            obrazac.setPodaciOSaglasnosti(new Obrazac.PodaciOSaglasnosti());
//            obrazac.getPodaciOSaglasnosti().setDatum(xmlDate);
//            obrazac.getPodaciOSaglasnosti().setNazivImunoloskogLeka("dfdfdf");
//            obrazac.getPodaciOSaglasnosti().setSaglasnost("SAGLASAN SAM");
//
//            obrazac.setEvidencijaOVakcinaciji(new Obrazac.EvidencijaOVakcinaciji());
//            obrazac.getEvidencijaOVakcinaciji().setVakcinacijskiPunkt("2");
//            obrazac.getEvidencijaOVakcinaciji().setZdravstvenaUstanova("dfdffd");
//            obrazac.getEvidencijaOVakcinaciji().setPodaciOIzvrsenimImunizacijama(new Obrazac.EvidencijaOVakcinaciji.PodaciOIzvrsenimImunizacijama());
//            obrazac.getEvidencijaOVakcinaciji().getPodaciOIzvrsenimImunizacijama().setOdlukaKomisije("nesto");
//            obrazac.getEvidencijaOVakcinaciji().getPodaciOIzvrsenimImunizacijama().setPrivremeneKontraindikacije(new Obrazac.EvidencijaOVakcinaciji.PodaciOIzvrsenimImunizacijama.PrivremeneKontraindikacije());
//            obrazac.getEvidencijaOVakcinaciji().getPodaciOIzvrsenimImunizacijama().getPrivremeneKontraindikacije().setDatum(xmlDate);
//            obrazac.getEvidencijaOVakcinaciji().getPodaciOIzvrsenimImunizacijama().getPrivremeneKontraindikacije().setDijagnoza("lose");
//            obrazac.getEvidencijaOVakcinaciji().setPodaciOLekaru(new Obrazac.EvidencijaOVakcinaciji.PodaciOLekaru());
//            obrazac.getEvidencijaOVakcinaciji().getPodaciOLekaru().setBrojTelefona("3434455");
//            obrazac.getEvidencijaOVakcinaciji().getPodaciOLekaru().setFaksimil("76766666");
//            obrazac.getEvidencijaOVakcinaciji().getPodaciOLekaru().setIme("Lola");
//            obrazac.getEvidencijaOVakcinaciji().getPodaciOLekaru().setPrezime("Lolic");
//
//            List<TPrimljenaVakcina> primljenaVakcinas = new ArrayList<>();
//            for(int i=1;i<5;i++){
//                TPrimljenaVakcina tPrimljenaVakcina = new TPrimljenaVakcina();
//                tPrimljenaVakcina.setDatumIzdavanja(xmlDate);
//                tPrimljenaVakcina.setEkstremitet("neki"+i);
//                tPrimljenaVakcina.setNaziv("bla"+i);
//                tPrimljenaVakcina.setNezeljenaReakcija("fgfg"+i);
//                tPrimljenaVakcina.setProizvodjac("dfdf"+i);
//                tPrimljenaVakcina.setSerijaVakcine("ser"+i);
//                primljenaVakcinas.add(tPrimljenaVakcina);
//            }
//            obrazac.getEvidencijaOVakcinaciji().getPodaciOIzvrsenimImunizacijama().getPrimljenaVakcina().addAll(primljenaVakcinas);
//
//            return mapper.convertToXml(obrazac, Obrazac.class);
//        }catch (Exception e){
//            return "";
//        }
//    }

    public String izvestaj(){
        try {
            mapper = new XMLMapper();
            Izvestaj izvestaj = new Izvestaj();
            GregorianCalendar cal = new GregorianCalendar();
            cal.setTime(new Date());
            XMLGregorianCalendar xmlDate = DatatypeFactory.newInstance().newXMLGregorianCalendarDate(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH)+1, cal.get(Calendar.DAY_OF_MONTH), DatatypeConstants.FIELD_UNDEFINED);
            izvestaj.setDatum(new Izvestaj.Datum());
            izvestaj.getDatum().setValue(xmlDate);
            izvestaj.setIzdatihZelenihSertifikata(new BigInteger("123"));
            izvestaj.setPodnetihInteresovanjaImunizacije(new BigInteger("1223"));
            izvestaj.setZahteviZaZeleniSertifikat(new BigInteger("4123"));
            izvestaj.setPeriodIzvestaja(new Izvestaj.PeriodIzvestaja());
            izvestaj.getPeriodIzvestaja().setDatumDo(new Izvestaj.PeriodIzvestaja.DatumDo());
            izvestaj.getPeriodIzvestaja().setDatumOd(new Izvestaj.PeriodIzvestaja.DatumOd());
            izvestaj.getPeriodIzvestaja().getDatumDo().setValue(xmlDate);
            izvestaj.getPeriodIzvestaja().getDatumOd().setValue(xmlDate);

            izvestaj.setPodaciODatimDozama(new Izvestaj.PodaciODatimDozama());
            izvestaj.getPodaciODatimDozama().setBrojDateDrugeDoze(new BigInteger("9"));
            izvestaj.getPodaciODatimDozama().setBrojDatePrveDoze(new BigInteger("8"));
            izvestaj.getPodaciODatimDozama().setBrojDateTreceDoze(new BigInteger("4"));
            izvestaj.getPodaciODatimDozama().setUkupnoDoza(new BigInteger("3"));

            izvestaj.setRaspodelaPoProizvodjacima(new Izvestaj.RaspodelaPoProizvodjacima());
            izvestaj.getRaspodelaPoProizvodjacima().setBrojAzVakcina(new BigInteger("5"));
            izvestaj.getRaspodelaPoProizvodjacima().setBrojPfizerVakcina(new BigInteger("1"));
            izvestaj.getRaspodelaPoProizvodjacima().setBrojSinopharmVakcina(new BigInteger("3"));
            izvestaj.getRaspodelaPoProizvodjacima().setBrojSputnikVakcina(new BigInteger("16"));

            return mapper.convertToXml(izvestaj, Izvestaj.class);
        }catch (Exception e){
            return "";
        }
    }
}
