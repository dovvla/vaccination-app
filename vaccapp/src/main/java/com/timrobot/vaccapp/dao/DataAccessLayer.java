package com.timrobot.vaccapp.dao;

import com.timrobot.vaccapp.models.Zahtev;
import com.timrobot.vaccapp.utility.PdfUtil;
import com.timrobot.vaccapp.utility.XHtmlUtil;
import com.timrobot.vaccapp.utility.XMLMapper;
import org.apache.jena.base.Sys;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class DataAccessLayer {
    @Autowired
    private XMLMapper mapper;


    public String saveDocument(Object object, String folderId, String documentId, Class<?> classOfObject) {
        DatabaseConnection.storeInXMLDB(folderId, object, documentId, classOfObject);

        PdfUtil pdfUtil = null;
        try {
            pdfUtil = new PdfUtil();


            pdfUtil.generatePDF(mapper.convertToXml(object, classOfObject), classOfObject, Arrays
                                                                                                   .stream(folderId.split("/"))
                                                                                                   .collect(Collectors.toList())
                                                                                                   .get(folderId.split("/").length - 1) + "_" + documentId.replace(".xml", "") + ".pdf");
            XHtmlUtil.generateHTML(mapper.convertToXml(object, classOfObject), classOfObject, Arrays
                                                                                                      .stream(folderId.split("/"))
                                                                                                      .collect(Collectors.toList())
                                                                                                      .get(folderId.split("/").length - 1) + "_" + documentId.replace(".xml", "") + ".html");
        }catch (Exception e) {
            throw new RuntimeException(e);
        }

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