package com.timrobot.vaccapp.services;

import com.timrobot.vaccapp.dao.DataAccessLayer;
import com.timrobot.vaccapp.dao.DatabaseConnection;
import com.timrobot.vaccapp.models.Izvestaj;
import com.timrobot.vaccapp.utility.FusekiUtil;
import com.timrobot.vaccapp.utility.XMLMapper;
import org.apache.jena.vocabulary.RDF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class DemoServiceImpl implements DemoService {
    private Izvestaj izvestaj = null;

    public static final String IZVESTAJ_FILE_PATH = "./src/main/resources/xml/izvestaj_o_imunizaciji_1.xml";

    @Override
    public Izvestaj unmarshalExample() {
        System.out.println("Testing unmarshalling...");
        izvestaj = XMLMapper.<Izvestaj>unmarshal(Izvestaj.class, new File(IZVESTAJ_FILE_PATH), "izvestaj_o_imunizaciji.xsd");
        System.out.println(izvestaj);
        System.out.println("Unmarshalling tested.\n");
        return izvestaj;
    }

    @Override
    public void marshalExample() {
        System.out.println("Testing marshalling...");
        XMLMapper.marshal(Izvestaj.class, System.out, "izvestaj_o_imunizaciji.xsd", izvestaj);
        System.out.println("Marshalling tested.\n");
    }

    @Override
    public void storeInXMLDBExample() {
        System.out.println("Testing XML database storing...");
        DatabaseConnection.<Izvestaj>storeInXMLDB("/db/apps/vaccapp", "izvestajDBex.xml", Izvestaj.class, izvestaj, "izvestaj_o_imunizaciji.xsd");
        System.out.println("XML database storing tested.\n");
    }

    @Override
    public Izvestaj retrieveFromXMLDBExample() {
        System.out.println("Testing XML database retrieval...");
        Izvestaj izv = DatabaseConnection.<Izvestaj>retrieveFromXMLDB("/db/apps/vaccapp", "izvestajDBex.xml", Izvestaj.class, "izvestaj_o_imunizaciji.xsd");
        System.out.println(izv);
        System.out.println("XML database storing tested.\n");
        return izv;
    }

    @Override
    public void RDFExample() {
        String rdfFilePath = "./src/main/resources/rdf/izvestaj.rdf";

        FusekiUtil.extractMetadataFromXML(IZVESTAJ_FILE_PATH, rdfFilePath);

        String graphURI = "izvestaj";

        try {
            FusekiUtil.saveRDFToFuseki(rdfFilePath, graphURI);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    @Autowired
//    private DataAccessLayer dataAccessLayer;
//
//    @Autowired
//    private XMLMapper mapper;
//
//    @Override
//    public String regularSearchIzvestaji(String search) throws Exception {
//        String searchQuery = String.format("xquery version \"3.1\";\n" +
//                "\n" +
//                "declare namespace izv=\"http://tim.robot/izvestaj_o_imunizaciji\";\n" +
//                "import module namespace functx=\"http://www.functx.com\";\n" +
//                "\n" +
//                "declare function local:search($keyword as xs:string)\n" +
//                "{\n" +
//                "    let $izvestaji := collection(\"/db/apps/vaccapp\")\n" +
//                "    let $rezultat :=\n" +
//                "    (\n" +
//                "        $izvestaji//izv:Izvestaj[contains(., $keyword)]\n" +
//                "    )\n" +
//                "\n" +
//                " return\n" +
//                "    functx:distinct-nodes($rezultat)\n" +
//                "};\n" +
//                "\n" +
//                "local:search(\"%s\")", search);
//
//        List<String> found = dataAccessLayer.executeXPathQuery("/db/apps/vaccapp", searchQuery, "http://tim.robot/izvestaj_o_imunizaciji");
//        return found.get(0);
//    }

    public void main(String[] args) throws Exception {
        unmarshalExample();
        marshalExample();
        storeInXMLDBExample();
        retrieveFromXMLDBExample();
        RDFExample();
    }
}
