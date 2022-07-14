package com.timrobot.vaccapp.utility;

import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.RDFNode;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.update.UpdateExecutionFactory;
import org.apache.jena.update.UpdateFactory;
import org.apache.jena.update.UpdateProcessor;
import org.apache.jena.update.UpdateRequest;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

import org.w3c.dom.Element;

public class FusekiUtil {

    private static String addPredicateNamespaceToXML(String xml) throws TransformerException {
        Source source = new StreamSource(new StringReader(xml));
        DOMResult result = new DOMResult();
        TransformerFactory.newInstance().newTransformer().transform(source, result);
        Document document = (Document) result.getNode();
        Element element = ((Document) result.getNode()).getDocumentElement();
        element.setAttributeNS(XMLConstants.XMLNS_ATTRIBUTE_NS_URI, "xmlns:pred", "http://tim.robot/rdf/predicate/");

        DOMSource source2 = new DOMSource(element);
        StreamResult result2 = new StreamResult(new StringWriter());
        TransformerFactory.newInstance().newTransformer().transform(source2, result2);

        return result2.getWriter().toString();
    }

    public static void extractMetadataFromXML(String xmlFile) throws TransformerException {
        // Automatic extraction of RDF triples from XML file
        String rdfFilePath = "./src/main/resources/rdf/metadata.rdf";

        xmlFile = addPredicateNamespaceToXML(xmlFile);

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

    public static HashMap<String, String> getAllMetadataForDocument(String graphURI, String namespace, String documentId) throws IOException {
        FusekiAuthenticationUtilities.FusekiConnectionProperties conn = FusekiAuthenticationUtilities.loadProperties();

        HashMap<String, String> retval = new HashMap<>();

        String sparqlQuery = SparqlUtil.selectData(conn.dataEndpoint.trim() + "/" + graphURI, "<http://tim.robot/" + namespace + "/" + documentId + "> ?p ?o");
        QueryExecution queryExec = QueryExecutionFactory.sparqlService(conn.queryEndpoint, sparqlQuery);
        ResultSet results = queryExec.execSelect();
        String p;
        String o;
        RDFNode predicate;
        RDFNode object;
        while (results.hasNext()) {
            QuerySolution querySolution = results.next();
            Iterator<String> variableBindings = querySolution.varNames();

            p = variableBindings.next();
            predicate = querySolution.get(p);
            o = variableBindings.next();
            object = querySolution.get(o);

            int indexEOF = object.toString().indexOf("^");
            if (indexEOF != -1) {
                retval.put(predicate.toString(), object.toString().substring(0, indexEOF));
            } else {
                retval.put(predicate.toString(), object.toString());
            }
        }
        queryExec.close();
        return retval;
    }

    public static String getAllMetadataForDocumentInRDF(String graphURI, String namespace, String documentId) throws IOException {
        HashMap<String, String> map = getAllMetadataForDocument(graphURI, namespace, documentId);

        StringBuilder builder =
                new StringBuilder("<?xml version=\"1.0\" encoding=\"utf-8\" ?>\n" +
                        "<rdf:RDF xmlns:rdf=\"http://www.w3.org/1999/02/22-rdf-syntax-ns#\"\n" +
                        "         xmlns:pred=\"http://tim.robot/" + graphURI + "/predicate/\">\n" +
                        "\n" +
                        "  <rdf:Description rdf:about=\"http://tim.robot/" + namespace + "/" + documentId + "\">\n");

        for (Map.Entry<String, String> entry : map.entrySet()) {
            String[] splitPredicate = entry.getKey().split("/");
            String predicateVal = splitPredicate[splitPredicate.length - 1];
            builder.append("\t\t<pred:").append(predicateVal).append(">").append(entry.getValue()).append("</pred:").append(predicateVal).append(">\n");
        }

        builder.append("  </rdf:Description>\n\n</rdf:RDF>");
        return builder.toString();
    }

}
