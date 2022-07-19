package com.timrobot.vaccapp.services;

import com.timrobot.vaccapp.dao.DataAccessLayer;
import com.timrobot.vaccapp.dao.DatabaseConnection;
import com.timrobot.vaccapp.models.*;
import com.timrobot.vaccapp.utility.FusekiUtil;
import com.timrobot.vaccapp.utility.QRcodeUtils;
import com.timrobot.vaccapp.utility.XMLMapper;
import org.apache.jena.vocabulary.RDF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class DemoServiceImpl implements DemoService {
    private Izvestaj izvestaj = null;
    private Obrazac saglasnost = null;
    private Potvrda potvrda = null;
    private Sertifikat sertifikat = null;
    private ObrazacInteresovanja obrazacInteresovanja = null;
    private Zahtev zahtev = null;

    public static final String IZVESTAJ_FILE_PATH = "./src/main/resources/xml/izvestaj_o_imunizaciji_1.xml";
    public static final String SAGLASNOST_FILE_PATH = "./src/main/resources/xml/saglasnost1.xml";
    public static final String POTVRDA_FILE_PATH = "./src/main/resources/xml/potvrda_o_vakcinaciji1.xml";
    public static final String SERTIFIKAT_FILE_PATH = "./src/main/resources/xml/zeleni_sertifikat1.xml";
    public static final String INTERESOVANJE_FILE_PATH = "./src/main/resources/xml/iskazivanje_interesovanja_za_vakcinaciju_1.xml";
    public static final String ZAHTEV_FILE_PATH = "./src/main/resources/xml/zahtev_za_sertifikat1.xml";

    @Override
    public Izvestaj unmarshalExample() {
        System.out.println("Testing unmarshalling...");
        izvestaj = XMLMapper.<Izvestaj>unmarshal(Izvestaj.class, new File(IZVESTAJ_FILE_PATH), "izvestaj_o_imunizaciji.xsd");
        saglasnost = XMLMapper.<Obrazac>unmarshal(Obrazac.class, new File(SAGLASNOST_FILE_PATH), "saglasnost.xsd");
        potvrda = XMLMapper.<Potvrda>unmarshal(Potvrda.class, new File(POTVRDA_FILE_PATH), "potvrda_o_vakcinaciji.xsd");
        sertifikat = XMLMapper.<Sertifikat>unmarshal(Sertifikat.class, new File(SERTIFIKAT_FILE_PATH), "zeleni_sertifikat.xsd");
//        sertifikat.getPodaciOSertifikatu().setQRkod(QRcodeUtils.writeQRCode(sertifikat.getPodaciOSertifikatu().getQRkod()));
        obrazacInteresovanja = XMLMapper.<ObrazacInteresovanja>unmarshal(ObrazacInteresovanja.class, new File(INTERESOVANJE_FILE_PATH), "iskazivanje_interesovanja_za_vakcinaciju.xsd");
        zahtev = XMLMapper.<Zahtev>unmarshal(Zahtev.class, new File(ZAHTEV_FILE_PATH), "zahtev_za_sertifikat.xsd");
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
        DatabaseConnection.<Obrazac>storeInXMLDB("/db/vacc-app/saglasnost", "saglasnost1.xml", Obrazac.class, saglasnost, "saglasnost.xsd");
        DatabaseConnection.<Potvrda>storeInXMLDB("/db/vacc-app/potvrda-o-vakcinaciji", "potvrda_o_vakcinaciji1.xml", Potvrda.class, potvrda, "potvrda_o_vakcinaciji.xsd");
        DatabaseConnection.<Sertifikat>storeInXMLDB("/db/vacc-app/sertifikat", "zeleni_sertifikat1.xml", Sertifikat.class, sertifikat, "zeleni_sertifikat.xsd");
        DatabaseConnection.<ObrazacInteresovanja>storeInXMLDB("/db/vacc-app/obrazac-interesovanja", "iskazivanje_interesovanja_za_vakcinaciju_1.xml", ObrazacInteresovanja.class, obrazacInteresovanja, "iskazivanje_interesovanja_za_vakcinaciju.xsd");
        DatabaseConnection.<Zahtev>storeInXMLDB("/db/vacc-app/zahtev-zeleni", "zahtev_za_sertifikat1.xml", Zahtev.class, zahtev, "zahtev_za_sertifikat.xsd");
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
    public void RDFExample() throws TransformerException {
//        byte[] encoded = new byte[0];
//        try {
//            encoded = Files.readAllBytes(Paths.get("./src/main/resources/xml/saglasnost1.xml"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        String xml = new String(encoded, StandardCharsets.UTF_8);
        System.out.println(potvrda.getZdravstvenaUstanova().getValue());
        String sertifikatXML = mapper.convertToXml(sertifikat, Sertifikat.class);

        FusekiUtil.extractMetadataFromXML(sertifikatXML);

        String graphURI = "sertifikat";

        try {
            FusekiUtil.saveRDFToFuseki(graphURI);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // -------------------------------------------------------------------------------------------------

    }

    @Autowired
    private DataAccessLayer dataAccessLayer;

    @Autowired
    private XMLMapper mapper;
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
