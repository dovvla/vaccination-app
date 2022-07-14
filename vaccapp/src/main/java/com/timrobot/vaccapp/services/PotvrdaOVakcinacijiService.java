package com.timrobot.vaccapp.services;

import com.timrobot.vaccapp.dao.DataAccessLayer;
import com.timrobot.vaccapp.models.EntityList;
import com.timrobot.vaccapp.models.Obrazac;
import com.timrobot.vaccapp.models.Potvrda;
import com.timrobot.vaccapp.models.Zahtev;
import com.timrobot.vaccapp.utility.FusekiUtil;
import com.timrobot.vaccapp.utility.XMLMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PotvrdaOVakcinacijiService {
    private final String folderId = "/db/vacc-app/potvrda-o-vakcinaciji";

    @Autowired
    private DataAccessLayer dataAccessLayer;

    @Autowired
    private XMLMapper mapper;

    public EntityList<Potvrda> getAll() {

        return new EntityList<>(dataAccessLayer
                .getAllDocuments(folderId)
                .stream()
                .map(s -> (Potvrda) mapper.convertToObject(s, "potvrda_o_vakcinaciji", Zahtev.class))
                .collect(Collectors.toList()));
    }

    public EntityList<Potvrda> getAllForUser(String id) {
        return new EntityList<>(dataAccessLayer
                .getAllDocuments(folderId)
                .stream()
                .map(s -> (Potvrda) mapper.convertToObject(s, "potvrda_o_vakcinaciji", Potvrda.class))
                .filter(potvrda -> potvrda
                        .getPodaciPacijenta()
                        .getJMBG()
                        .equals(id))
                .collect(Collectors.toList()));
    }

    public Potvrda getXmlAsObject(String documentId) {
        String xmlString = dataAccessLayer
                .getDocument(folderId, documentId)
                .get();

        return (Potvrda) mapper.convertToObject(xmlString, "potvrda_o_vakcinaciji", Potvrda.class);
    }

    public Potvrda createPotvrda(Potvrda potvrda) throws TransformerException {
        String identifikator = UUID.randomUUID().toString();
        String documentId = identifikator + ".xml";
//        String documentId = UUID
//                                    .randomUUID() + ".xml";

        potvrda.setSifraPotvrde(identifikator);
        potvrda.setAbout("http://tim.robot/potvrda_o_vakcinaciji/" + identifikator);

        dataAccessLayer.saveDocument(potvrda, folderId, documentId, Potvrda.class);

        // ----------- RDF -------------
        String potvrdaXML = mapper.convertToXml(potvrda, Potvrda.class);

        FusekiUtil.extractMetadataFromXML(potvrdaXML);

        String graphURI = "potvrda";

        try {
            FusekiUtil.saveRDFToFuseki(graphURI);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // ----------- RDF -------------

        return potvrda;

    }

    public List<Potvrda> regularSearchPotvrda(String search) throws Exception {
        String searchQuery = String.format("xquery version \"3.1\";\n" +
                "\n" +
                "declare namespace potv=\"http://tim.robot/potvrda_o_vakcinaciji\";\n" +
                "import module namespace functx=\"http://www.functx.com\";\n" +
                "\n" +
                "declare function local:search($keyword as xs:string)\n" +
                "{\n" +
                "    let $potvrde := collection(\"/db/vacc-app/potvrda-o-vakcinaciji\")\n" +
                "    let $rezultat :=\n" +
                "    (\n" +
                "        $potvrde//potv:Potvrda[contains(., $keyword)]\n" +
                "    )\n" +
                "\n" +
                " return\n" +
                "    functx:distinct-nodes($rezultat)\n" +
                "};\n" +
                "\n" +
                "local:search(\"%s\")", search);

        List<String> matchingPotvrdeXML = dataAccessLayer.executeXPathQuery(folderId, searchQuery, "http://tim.robot/potvrda_o_vakcinaciji");

        List<Potvrda> matchingPotvrde = new ArrayList<>();
        for (String XML : matchingPotvrdeXML) {
            matchingPotvrde.add((Potvrda) mapper.convertToObject(XML, "potvrda_o_vakcinaciji", Potvrda.class));
        }

        return matchingPotvrde;
    }

    public List<Potvrda> advancedSearchPotvrda(String datum, String zdravstvenaUstanova, boolean logicalAnd) throws IOException {
        ArrayList<String> queries = new ArrayList<>();
        if(!datum.trim().equals("")) {
            queries.add("?s <http://tim.robot/rdf/predicate/datum> ?X . FILTER(str(?X) = \"" + datum + "\")");
        }
        if(!zdravstvenaUstanova.trim().equals("")) {
            queries.add("?s <http://tim.robot/rdf/predicate/zdravstvena_ustanova> ?X . FILTER(str(?X) = \"" + zdravstvenaUstanova + "\")");
        }
        if(queries.isEmpty()) {
            queries.add("?s ?p ?o");
        }

        HashSet<String> resultSubjects = new HashSet<>();
        for (String query : queries) {
            HashSet<String> result = FusekiUtil.queryRdf("potvrda", query);

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

        List<Potvrda> potvrde = new ArrayList<>();
        for (String subject : resultSubjects) {
            // subject je namespace
            String[] splitNamespace = subject.split("/");
            String documentId = splitNamespace[splitNamespace.length - 1];
            potvrde.add(this.getXmlAsObject(documentId));
        }

        return potvrde;
    }

    public Map<String, String> getAllMetadataForDocumentForJSON(String documentId) throws IOException {
        return FusekiUtil.getAllMetadataForDocument("potvrda", "potvrda_o_vakcinaciji", documentId);
    }

    public String getAllMetadataForDocumentInRDF(String documentId) throws IOException {
        return FusekiUtil.getAllMetadataForDocumentInRDF("potvrda", "potvrda_o_vakcinaciji", documentId);
    }
}
