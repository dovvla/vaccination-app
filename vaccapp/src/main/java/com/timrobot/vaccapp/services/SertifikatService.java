package com.timrobot.vaccapp.services;

import com.timrobot.vaccapp.dao.DataAccessLayer;
import com.timrobot.vaccapp.models.EntityList;
import com.timrobot.vaccapp.models.Obrazac;
import com.timrobot.vaccapp.models.Sertifikat;
import com.timrobot.vaccapp.models.Zahtev;
import com.timrobot.vaccapp.utility.FusekiUtil;
import com.timrobot.vaccapp.utility.XMLMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.datatype.DatatypeConfigurationException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class SertifikatService {
    private final String folderId = "/db/vacc-app/sertifikat";

    @Autowired
    private DataAccessLayer dataAccessLayer;

    @Autowired
    private XMLMapper mapper;

    private Date stringToDate(String date) {
        ZoneId defaultZoneId = ZoneId.systemDefault();

        //creating the instance of LocalDate using the day, month, year info
        LocalDate localDate = LocalDate.parse(date);

        //local date + atStartOfDay() + default time zone + toInstant() = Date
        return Date.from(localDate
                .atStartOfDay(defaultZoneId)
                .toInstant());
    }

    public EntityList<Sertifikat> getAllForDateRangeInclusive(String startDate, String endDate) throws DatatypeConfigurationException {
        return new EntityList<>(dataAccessLayer
                .getAllDocuments(folderId)
                .stream()
                .map(s -> (Sertifikat) mapper.convertToObject(s, "zeleni_sertifikat", Sertifikat.class))
                .filter(sertifikat -> !sertifikat.getPodaciOSertifikatu()
                        .getDatumIVreme()
                        .getValue()
                        .toGregorianCalendar()
                        .getTime()
                        .before(stringToDate(startDate)) && !sertifikat.getPodaciOSertifikatu()
                        .getDatumIVreme()
                        .getValue()
                        .toGregorianCalendar()
                        .getTime()
                        .after(stringToDate(endDate)))
                .collect(Collectors.toList()));
    }

    public Sertifikat getXmlAsObject(String documentId) {
        String xmlString = dataAccessLayer
                .getDocument(folderId, documentId)
                .get();

        return (Sertifikat) mapper.convertToObject(xmlString, "zeleni_sertifikat", Sertifikat.class);
    }

    public List<Sertifikat> regularSearchSertifikat(String search) throws Exception {
        String searchQuery = String.format("xquery version \"3.1\";\n" +
                "\n" +
                "declare namespace sert=\"http://tim.robot/zeleni_sertifikat\";\n" +
                "import module namespace functx=\"http://www.functx.com\";\n" +
                "\n" +
                "declare function local:search($keyword as xs:string)\n" +
                "{\n" +
                "    let $sertifikati := collection(\"/db/vacc-app/sertifikat\")\n" +
                "    let $rezultat :=\n" +
                "    (\n" +
                "        $sertifikati//sert:Sertifikat[contains(., $keyword)]\n" +
                "    )\n" +
                "\n" +
                " return\n" +
                "    functx:distinct-nodes($rezultat)\n" +
                "};\n" +
                "\n" +
                "local:search(\"%s\")", search);

        List<String> matchingSertifikatiXML = dataAccessLayer.executeXPathQuery(folderId, searchQuery, "http://tim.robot/zeleni_sertifikat");

        List<Sertifikat> matchingSertifikati = new ArrayList<>();
        for (String XML : matchingSertifikatiXML) {
            matchingSertifikati.add((Sertifikat) mapper.convertToObject(XML, "zeleni_sertifikat", Sertifikat.class));
        }

        return matchingSertifikati;
    }

    public List<Sertifikat> advancedSearchSertifikat(String ime, String prezime, String datumIVremeIzdavanja, String hrefZahtev, boolean logicalAnd) throws IOException {
        ArrayList<String> queries = new ArrayList<>();
        if(!ime.trim().equals("")) {
            queries.add("?s <http://tim.robot/rdf/predicate/ime> ?X . FILTER(str(?X) = \"" + ime + "\")");
        }
        if(!prezime.trim().equals("")) {
            queries.add("?s <http://tim.robot/rdf/predicate/prezime> ?X . FILTER(str(?X) = \"" + prezime + "\")");
        }
//        if(!datumIVremeIzdavanja.trim().equals("")) {
//            queries.add("?s <http://tim.robot/rdf/predicate/datum> ?X . FILTER(str(?X) = \"" + datumIVremeIzdavanja + "\")");
//        }
        if(!datumIVremeIzdavanja.trim().equals("")) {
            queries.add("?s <http://tim.robot/rdf/predicate/datum> ?X . FILTER(regex(str(?X), \"^" + datumIVremeIzdavanja + "\"))");
        }
        if(!hrefZahtev.trim().equals("")) {
            queries.add("?s <http://tim.robot/rdf/predicate/fromZahtev> " + hrefZahtev + " .");
        }
        if(queries.isEmpty()) {
            queries.add("?s ?p ?o");
        }

        HashSet<String> resultSubjects = new HashSet<>();
        for (String query : queries) {
            HashSet<String> result = FusekiUtil.queryRdf("sertifikat", query);

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

        List<Sertifikat> sertifikati = new ArrayList<>();
        for (String subject : resultSubjects) {
            // subject je namespace
            String[] splitNamespace = subject.split("/");
            String documentId = splitNamespace[splitNamespace.length - 1];
            sertifikati.add(this.getXmlAsObject(documentId));
        }

        return sertifikati;
    }

    public Map<String, String> getAllMetadataForDocumentForJSON(String documentId) throws IOException {
        return FusekiUtil.getAllMetadataForDocument("sertifikat", "zeleni_sertifikat", documentId);
    }

    public String getAllMetadataForDocumentInRDF(String documentId) throws IOException {
        return FusekiUtil.getAllMetadataForDocumentInRDF("sertifikat", "zeleni_sertifikat", documentId);
    }

}
