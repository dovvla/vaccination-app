package com.timrobot.vaccapp.dao;

import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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


//    public Optional<String> getSearchQuery(String folderId, String documentId) {
//        String resourceContent = dbConnection.getOneSearchQuery(folderId, documentId);
//        if (resourceContent.equals(""))
//            return Optional.empty();
//        return Optional.of(resourceContent);
//    }
}