package com.timrobot.vaccapp.dao;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.transform.OutputKeys;

import com.timrobot.vaccapp.utility.ExistAuthenticationUtilities;
import com.timrobot.vaccapp.utility.ExistAuthenticationUtilities.ConnectionProperties;
import com.timrobot.vaccapp.utility.XMLMapper;
import org.apache.xerces.parsers.XMLParser;
import org.exist.xmldb.EXistResource;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.CollectionManagementService;
import org.xmldb.api.modules.XMLResource;

@Component
public class DatabaseConnection {

    public static <T> boolean storeInXMLDB(String collectionId, String documentId, Class<?> clazz, T objectToStore, String xsdFileName) {
        // a collection of Resources stored within an XML database
        Collection col = null;
        XMLResource res = null;
        OutputStream os = new ByteArrayOutputStream();

        try {
            ExistAuthenticationUtilities.ConnectionProperties conn = ExistAuthenticationUtilities.loadProperties();

            // initialize database driver
            Class<?> cl = Class.forName(conn.driver);

            // encapsulation of the database driver functionality
            Database database = (Database) cl.newInstance();
            database.setProperty("create-database", "true");

            // entry point for the API which enables you to get the Collection reference
            DatabaseManager.registerDatabase(database);

            col = getOrCreateCollection(collectionId, conn);
            res = (XMLResource) col.createResource(documentId, XMLResource.RESOURCE_TYPE);

            XMLMapper.marshal(clazz, os, xsdFileName, objectToStore);

            // link the stream to the XML resource
            res.setContent(os);
            col.storeResource(res);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | XMLDBException |
                 IOException e) {
            return false;
        } finally {
            //don't forget to cleanup
            if (res != null) {
                try {
                    ((EXistResource) res).freeResources();
                } catch (XMLDBException xe) {
                    xe.printStackTrace();
                }
            }

            if (col != null) {
                try {
                    col.close();
                } catch (XMLDBException xe) {
                    xe.printStackTrace();
                }
            }
        }
        return true;
    }

    public static void storeInXMLDB(String collectionId, Object object, String documentId, Class<?> classOfObject) {
        try {
            ExistAuthenticationUtilities.ConnectionProperties conn = ExistAuthenticationUtilities.loadProperties();
            Class<?> cl = Class.forName(conn.driver);
            Database database = (Database) cl.newInstance();
            database.setProperty("create-database", "true");
            DatabaseManager.registerDatabase(database);
            Collection col = getOrCreateCollection(collectionId, conn);
            col.setProperty(OutputKeys.INDENT, "yes");
            XMLResource res = (XMLResource) col.createResource(documentId, XMLResource.RESOURCE_TYPE);
            OutputStream os = new ByteArrayOutputStream();
            JAXBContext context = JAXBContext.newInstance(classOfObject);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(object, os);
            res.setContent(os);
            col.storeResource(res);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static <T> T retrieveFromXMLDB(String collectionId, String documentId, Class<?> clazz, String xsdFileName) {
        Collection col = null;
        XMLResource res = null;

        try {
            ExistAuthenticationUtilities.ConnectionProperties conn = ExistAuthenticationUtilities.loadProperties();

            // initialize database driver
            Class<?> cl = Class.forName(conn.driver);

            Database database = (Database) cl.newInstance();
            database.setProperty("create-database", "true");

            DatabaseManager.registerDatabase(database);

            col = DatabaseManager.getCollection(conn.uri + collectionId);
            col.setProperty(OutputKeys.INDENT, "yes");

            res = (XMLResource) col.getResource(documentId);

            if (res == null) {
                System.out.println("[WARNING] Document '" + documentId + "' can not be found!");
            } else {
                return XMLMapper.<T>unmarshal(clazz, res.getContentAsDOM(), xsdFileName);
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | XMLDBException |
                 IOException e) {
            e.printStackTrace();
        } finally {
            if (res != null) {
                try {
                    ((EXistResource) res).freeResources();
                } catch (XMLDBException xe) {
                    xe.printStackTrace();
                }
            }

            if (col != null) {
                try {
                    col.close();
                } catch (XMLDBException xe) {
                    xe.printStackTrace();
                }
            }
        }

        return null;
    }

    public static String retrieveFromXMLDB(String folderId, String documentId) {
        XMLResource res;
        Collection col = null;
        String responseContent = "";

        try {
            ExistAuthenticationUtilities.ConnectionProperties conn = ExistAuthenticationUtilities.loadProperties();

            Class<?> cl = Class.forName(conn.driver);
            Database database = (Database) cl.newInstance();
            database.setProperty("create-database", "true");
            DatabaseManager.registerDatabase(database);
            col = DatabaseManager.getCollection(conn.uri + folderId, conn.user, conn.password);
            col.setProperty(OutputKeys.INDENT, "yes");

            res = (XMLResource) col.getResource(documentId + ".xml");
            if (res != null)
                responseContent = (String) res.getContent();
        } catch (Exception ignored) {
        } finally {
            if (col != null) {
                try {
                    col.close();
                } catch (XMLDBException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return responseContent;
    }

    private static Collection getOrCreateCollection(String collectionUri, ConnectionProperties conn) throws XMLDBException {
        return getOrCreateCollection(collectionUri, 0, conn);
    }

    private static Collection getOrCreateCollection(String collectionUri, int pathSegmentOffset, ConnectionProperties conn) throws XMLDBException {

        Collection col = DatabaseManager.getCollection(conn.uri + collectionUri, conn.user, conn.password);

        // create the collection if it does not exist
        if (col == null) {

            if (collectionUri.startsWith("/")) collectionUri = collectionUri.substring(1);

            String[] pathSegments = collectionUri.split("/");

            if (pathSegments.length > 0) {
                StringBuilder path = new StringBuilder();

                for (int i = 0; i <= pathSegmentOffset; i++) {
                    path.append("/").append(pathSegments[i]);
                }

                Collection startCol = DatabaseManager.getCollection(conn.uri + path, conn.user, conn.password);

                if (startCol == null) {

                    // child collection does not exist

                    String parentPath = path.substring(0, path.lastIndexOf("/"));
                    Collection parentCol = DatabaseManager.getCollection(conn.uri + parentPath, conn.user, conn.password);

                    CollectionManagementService mgt = (CollectionManagementService) parentCol.getService("CollectionManagementService", "1.0");

                    System.out.println("[INFO] Creating the collection: " + pathSegments[pathSegmentOffset]);
                    col = mgt.createCollection(pathSegments[pathSegmentOffset]);

                    col.close();
                    parentCol.close();

                } else {
                    startCol.close();
                }
            }
            return getOrCreateCollection(collectionUri, ++pathSegmentOffset, conn);
        } else {
            return col;
        }
    }

    public static void deleteOne(String folderId, Object object, String documentId, Class<?> classOfObject) {
        try {
            ExistAuthenticationUtilities.ConnectionProperties conn = ExistAuthenticationUtilities.loadProperties();

            Class<?> cl = Class.forName(conn.driver);
            Database database = (Database) cl.newInstance();
            database.setProperty("create-database", "true");
            DatabaseManager.registerDatabase(database);
            Collection col = getOrCreateCollection(folderId, conn);
            col.setProperty(OutputKeys.INDENT, "yes");
            XMLResource res = (XMLResource) col.createResource(documentId, XMLResource.RESOURCE_TYPE);
            OutputStream os = new ByteArrayOutputStream();
            JAXBContext context = JAXBContext.newInstance(classOfObject);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(object, os);
            res.setContent(os);
            col.removeResource(res);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
