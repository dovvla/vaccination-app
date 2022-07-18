package com.timrobot.vaccapp.services;

import com.timrobot.vaccapp.dao.DataAccessLayer;
import com.timrobot.vaccapp.exceptions.ResourceAlreadyExistsException;
import com.timrobot.vaccapp.models.EntityList;
import com.timrobot.vaccapp.models.Korisnik;
import com.timrobot.vaccapp.models.ObrazacInteresovanja;
import com.timrobot.vaccapp.models.Termin;
import com.timrobot.vaccapp.utility.EmailServiceImpl;
import com.timrobot.vaccapp.utility.XMLMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.stream.Collectors;

@Service
public class ObrazacInteresovanjaService {
        private final String folderId = "/db/vacc-app/obrazac-interesovanja";

        @Autowired
        private DataAccessLayer dataAccessLayer;

        @Autowired
        private XMLMapper mapper;

        @Autowired
        private TerminService terminService;

        @Autowired
        private BrojVakcinaService brojVakcinaService;

        @Autowired
        private EmailServiceImpl emailService;

        public ObrazacInteresovanja getXmlAsObject(String documentId) {
                String xmlString = dataAccessLayer
                                .getDocument(folderId, documentId)
                                .get();

                return (ObrazacInteresovanja) mapper.convertToObject(xmlString, "iskazivanje_interesovanja_za_vakcinaciju",
                                ObrazacInteresovanja.class);
        }

        public EntityList<ObrazacInteresovanja> getAll() {

                return new EntityList<>(dataAccessLayer
                                .getAllDocuments(folderId)
                                .stream()
                                .map(s -> (ObrazacInteresovanja) mapper.convertToObject(s,
                                                "iskazivanje_interesovanja_za_vakcinaciju", ObrazacInteresovanja.class))
                                .collect(Collectors.toList()));
        }

        public EntityList<ObrazacInteresovanja> getAllForUser(String id) {
                return new EntityList<>(dataAccessLayer
                                .getAllDocuments(folderId)
                                .stream()
                                .map(s -> (ObrazacInteresovanja) mapper.convertToObject(s,
                                                "iskazivanje_interesovanja_za_vakcinaciju", ObrazacInteresovanja.class))
                                .filter(obrazacInteresovanja -> obrazacInteresovanja
                                                .getLicniPodaci()
                                                .getJMBG()
                                                .equals(id))
                                .collect(Collectors.toList()));
        }

        private Date stringToDate(String date) {
                ZoneId defaultZoneId = ZoneId.systemDefault();

                // creating the instance of LocalDate using the day, month, year info
                LocalDate localDate = LocalDate.parse(date);

                // local date + atStartOfDay() + default time zone + toInstant() = Date
                return Date.from(localDate
                                .atStartOfDay(defaultZoneId)
                                .toInstant());
        }

        public EntityList<ObrazacInteresovanja> getAllForDateRange(String startDate, String endDate) {
                return new EntityList<>(dataAccessLayer
                                .getAllDocuments(folderId)
                                .stream()
                                .map(s -> (ObrazacInteresovanja) mapper.convertToObject(s,
                                                "iskazivanje_interesovanja_za_vakcinaciju", ObrazacInteresovanja.class))
                                .filter(obrazacInteresovanja -> obrazacInteresovanja
                                                .getDatum()
                                                .toGregorianCalendar()
                                                .getTime()
                                                .after(stringToDate(startDate))
                                                && obrazacInteresovanja
                                                                .getDatum()
                                                                .toGregorianCalendar()
                                                                .getTime()
                                                                .before(stringToDate(endDate)))
                                .collect(Collectors.toList()));
        }

        public EntityList<ObrazacInteresovanja> getAllForDateRangeInclusive(String startDate, String endDate) {
                return new EntityList<>(dataAccessLayer
                                .getAllDocuments(folderId)
                                .stream()
                                .map(s -> (ObrazacInteresovanja) mapper.convertToObject(s,
                                                "iskazivanje_interesovanja_za_vakcinaciju", ObrazacInteresovanja.class))
                                .filter(obrazacInteresovanja -> !obrazacInteresovanja
                                                .getDatum()
                                                .toGregorianCalendar()
                                                .getTime()
                                                .before(stringToDate(startDate))
                                                && !obrazacInteresovanja
                                                                .getDatum()
                                                                .toGregorianCalendar()
                                                                .getTime()
                                                                .after(stringToDate(endDate)))
                                .collect(Collectors.toList()));
        }

        public ObrazacInteresovanja createObrazacInteresovanja(ObrazacInteresovanja obrazacInteresovanja)
                        throws DatatypeConfigurationException {
                String documentId = obrazacInteresovanja
                                .getLicniPodaci()
                                .getJMBG() + ".xml";

                if (dataAccessLayer
                                .getDocument(folderId, obrazacInteresovanja
                                                .getLicniPodaci()
                                                .getJMBG())
                                .isPresent()) {
                        throw new ResourceAlreadyExistsException("Obrazac interesovanja sa ovim jmbgom vec postoji");
                }

                emailService.sendSimpleMessage(obrazacInteresovanja
                                .getLicniPodaci()
                                .getImejl(), "Iskazano interesovanje",
                                "Dobicete prvi slobodan termin koji bude dostupan " + obrazacInteresovanja);

                if (brojVakcinaService.getBrojVakcina(obrazacInteresovanja.getZeljenaVakcina()) > 1) {
                        this.rezervisiTermin(obrazacInteresovanja);
                }

                dataAccessLayer.saveDocument(obrazacInteresovanja, folderId, documentId, ObrazacInteresovanja.class);

                return obrazacInteresovanja;

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

        public void rezervisiTermin(ObrazacInteresovanja obrazacInteresovanja) throws DatatypeConfigurationException {
                Termin termin = terminService
                                .getSlobodanTermin()
                                .orElse(new Termin());
                if (obrazacInteresovanja
                                .getZeljenaVakcina()
                                .equals("Bilo koja"))
                        obrazacInteresovanja.setZeljenaVakcina(brojVakcinaService.getKokretnaVakcina());
                termin.setVakcina(obrazacInteresovanja.getZeljenaVakcina());
                termin.setJmbg(obrazacInteresovanja
                                .getLicniPodaci()
                                .getJMBG());
                if (termin.getDatumVreme() == null)
                        termin.setDatumVreme(localDateTimeToXMLDate(LocalDateTime
                                        .now()
                                        .plusDays(3)));
                terminService.saveXmlFromText(termin);
                emailService.sendSimpleMessage(obrazacInteresovanja
                                .getLicniPodaci()
                                .getImejl(), "Termin vase vakcinacije", termin.toString());
                brojVakcinaService.izmeniBrojVakcina(obrazacInteresovanja.getZeljenaVakcina(), -2);
        }

        public void rezervisiNePrviTermin(ObrazacInteresovanja obrazacInteresovanja)
                        throws DatatypeConfigurationException {
                Termin termin = terminService
                                .getSlobodanTermin()
                                .orElse(new Termin());
                termin.setVakcina(obrazacInteresovanja.getZeljenaVakcina());
                termin.setJmbg(obrazacInteresovanja
                                .getLicniPodaci()
                                .getJMBG());
                int brojDana = 21;
                if (obrazacInteresovanja
                                .getZeljenaVakcina()
                                .equals("AZ")) {
                        brojDana = 90;
                }
                if (termin.getDatumVreme() == null)
                        termin.setDatumVreme(localDateTimeToXMLDate(LocalDateTime
                                        .now()
                                        .plusDays(brojDana)));
                terminService.saveXmlFromText(termin);
                emailService.sendSimpleMessage(obrazacInteresovanja
                                .getLicniPodaci()
                                .getImejl(), "Termin vase vakcinacije", termin.toString());
                brojVakcinaService.izmeniBrojVakcina(obrazacInteresovanja.getZeljenaVakcina(), 0);
        }
}
