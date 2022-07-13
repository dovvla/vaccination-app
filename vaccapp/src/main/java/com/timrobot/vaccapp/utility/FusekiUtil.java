package com.timrobot.vaccapp.utility;

import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.RDFNode;
import org.xml.sax.SAXException;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.update.UpdateExecutionFactory;
import org.apache.jena.update.UpdateFactory;
import org.apache.jena.update.UpdateProcessor;
import org.apache.jena.update.UpdateRequest;

import javax.xml.transform.TransformerException;
import java.io.*;
import java.util.HashSet;
import java.util.Iterator;

public class FusekiUtil {

    public static void extractMetadataFromXML(String xmlFile) {
        // Automatic extraction of RDF triples from XML file
        String rdfFilePath = "./src/main/resources/rdf/metadata.rdf";

        MetadataExtractor metadataExtractor = null;
        try {
            metadataExtractor = new MetadataExtractor();

            System.out.println("[INFO] Extracting metadata from RDFa attributes...");
            File rdfFile = new File(rdfFilePath);
            rdfFile.createNewFile();
            metadataExtractor.extractMetadata(
                    new ByteArrayInputStream(xmlFile.getBytes()),
                    new FileOutputStream(new File(rdfFilePath)));
        } catch (SAXException | IOException | TransformerException e) {
            e.printStackTrace();
        }
    }

    public static void saveRDFToFuseki(String graphURI) throws IOException {
        String rdfFilePath = "./src/main/resources/rdf/metadata.rdf";

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

    // returns subjects (namespaces, for now) that meet query conditions
    public static HashSet<String> queryRdf(String graphURI, String query) throws IOException {
        FusekiAuthenticationUtilities.FusekiConnectionProperties conn = FusekiAuthenticationUtilities.loadProperties();

        HashSet<String> retval = new HashSet<>();

        String sparqlQuery = SparqlUtil.selectData(conn.dataEndpoint.trim() + "/" + graphURI, query);
        QueryExecution queryExec = QueryExecutionFactory.sparqlService(conn.queryEndpoint, sparqlQuery);
        ResultSet results = queryExec.execSelect();
        String varName;
        RDFNode varValue;
        while (results.hasNext()) {
            QuerySolution querySolution = results.next();
            Iterator<String> variableBindings = querySolution.varNames();
            while (variableBindings.hasNext()) {
                varName = variableBindings.next();
                varValue = querySolution.get(varName);
                if(varName.equals("s"))
                    retval.add(varValue.toString());
            }
        }
        queryExec.close();

        return retval;
    }


}
