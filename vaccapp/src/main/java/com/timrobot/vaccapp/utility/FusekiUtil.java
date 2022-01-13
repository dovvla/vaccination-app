package com.timrobot.vaccapp.utility;

import org.xml.sax.SAXException;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.update.UpdateExecutionFactory;
import org.apache.jena.update.UpdateFactory;
import org.apache.jena.update.UpdateProcessor;
import org.apache.jena.update.UpdateRequest;

import javax.xml.transform.TransformerException;
import java.io.*;

public class FusekiUtil {

    public static void extractMetadataFromXML(String xmlFilePath, String rdfFilePath) {
        // Automatic extraction of RDF triples from XML file
        MetadataExtractor metadataExtractor = null;
        try {
            metadataExtractor = new MetadataExtractor();

            System.out.println("[INFO] Extracting metadata from RDFa attributes...");
            File rdfFile = new File(rdfFilePath);
            rdfFile.createNewFile();
            metadataExtractor.extractMetadata(
                    new FileInputStream(new File(xmlFilePath)),
                    new FileOutputStream(new File(rdfFilePath)));
        } catch (SAXException | IOException | TransformerException e) {
            e.printStackTrace();
        }
    }

    public static void saveRDFToFuseki(String rdfFilePath, String graphURI) throws IOException {
        System.out.println("[INFO] Loading triples from an RDF/XML to a model...");
        FusekiAuthenticationUtilities.FusekiConnectionProperties conn = FusekiAuthenticationUtilities.loadProperties();

        Model model = ModelFactory.createDefaultModel();
        model.read(rdfFilePath);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        model.write(out, SparqlUtil.NTRIPLES);
        System.out.println("[INFO] Rendering model as RDF/XML...");
        model.write(System.out, SparqlUtil.RDF_XML);

        UpdateRequest request = UpdateFactory.create();
        UpdateProcessor processor = UpdateExecutionFactory.createRemote(request,conn.updateEndpoint);
        processor.execute();
        System.out.println("[INFO] Writing the triples to a named graph \"" + graphURI + "\".");
        String sparqlUpdate = SparqlUtil.insertData(conn.dataEndpoint + "/"+ graphURI,
                new String(out.toByteArray()));
        System.out.println(sparqlUpdate);

        UpdateRequest update = UpdateFactory.create(sparqlUpdate);
        processor = UpdateExecutionFactory.createRemote(update,conn.updateEndpoint);
        processor.execute();

    }

}
