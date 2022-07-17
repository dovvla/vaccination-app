package com.timrobot.vaccapp.dao;

import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class DataAccessLayer {

    public String saveDocument(Object object, String folderId, String documentId, Class<?> classOfObject) {
        DatabaseConnection.storeInXMLDB(folderId, object, documentId, classOfObject);

        return documentId;
    }

    public String deleteDocument(Object object, String folderId, String documentId, Class<?> classOfObject) {
        DatabaseConnection.deleteOne(folderId, object, documentId, classOfObject);
        return documentId;
    }

    public Optional<String> getDocument(String folderId, String documentId) {
        String resourceContent = DatabaseConnection.retrieveFromXMLDB(folderId, documentId);
        if (resourceContent.equals(""))
            return Optional.empty();
        return Optional.of(resourceContent);
    }

    public List<String> getAllDocuments(String folderId) {
        return DatabaseConnection.retrieveAllFromXMLDB(folderId);
    }

    public List<String> executeXPathQuery(String folderId, String xpathExp, String namespace) throws Exception {
        return DatabaseConnection.executeXPathQuery(folderId, xpathExp, namespace);
    }

}