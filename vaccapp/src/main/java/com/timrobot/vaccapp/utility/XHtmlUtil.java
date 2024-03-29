package com.timrobot.vaccapp.utility;

import com.timrobot.vaccapp.models.*;
import org.apache.commons.io.FileUtils;
import org.springframework.util.ResourceUtils;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;

public class XHtmlUtil {
    public static final String HTML_FILE = "src/main/resources/static/documents/";
    public static final String HTML_FILE_1 = "document.html";

    private static final TransformerFactory transformerFactory;
    public static String XSL_FILE;

    static {
        transformerFactory = TransformerFactory.newInstance();
    }

    public static ByteArrayInputStream generateHTML(String documentXml, Class<?> classOfDocument) {
        ByteArrayInputStream retVal = null;
        File file = null;
        try {
            setXSLFile(classOfDocument);

            StreamSource transformSource = new StreamSource(ResourceUtils.getFile(XSL_FILE));
            Transformer transformer = transformerFactory.newTransformer(transformSource);
            transformer.setOutputProperty("{http://xml.apache.org/xalan}indent-amount", "2");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.METHOD, "xhtml");
            StreamSource source = new StreamSource(new ByteArrayInputStream(documentXml.getBytes()));
            file = new File(HTML_FILE_1);
            FileOutputStream output = new FileOutputStream(file);
            StreamResult result = new StreamResult(output);
            transformer.transform(source, result);
            retVal = new ByteArrayInputStream(FileUtils.readFileToByteArray(file));
        } catch (Exception ignored) {
        } finally {
            assert file != null;
            file.delete();
        }
        return retVal;
    }

    public static ByteArrayInputStream generateHTML(String documentXml, Class<?> classOfDocument, String path) {
        ByteArrayInputStream retVal = null;
        File file = null;
        try {
            setXSLFile(classOfDocument);

            StreamSource transformSource = new StreamSource(ResourceUtils.getFile(XSL_FILE));
            Transformer transformer = transformerFactory.newTransformer(transformSource);
            transformer.setOutputProperty("{http://xml.apache.org/xalan}indent-amount", "2");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.METHOD, "xhtml");
            StreamSource source = new StreamSource(new ByteArrayInputStream(documentXml.getBytes()));
            file = new File(HTML_FILE + path);
            FileOutputStream output = new FileOutputStream(file);
            StreamResult result = new StreamResult(output);
            transformer.transform(source, result);
            retVal = new ByteArrayInputStream(FileUtils.readFileToByteArray(file));
        } catch (Exception ignored) {
        } finally {
            assert file != null;
        }
        return retVal;
    }

    private static void setXSLFile(Class<?> classOfDocument) {
        if (classOfDocument.equals(ObrazacInteresovanja.class))
            XSL_FILE = "classpath:xsl/iskazivanje_interesovanja_za_vakcinaciju.xsl";
        else if (classOfDocument.equals(Obrazac.class))
            XSL_FILE = "classpath:xsl/saglasnost.xsl";
        else if (classOfDocument.equals(Potvrda.class))
            XSL_FILE = "classpath:xsl/potvrda_o_vakcinaciji.xsl";
        else if (classOfDocument.equals(Sertifikat.class))
            XSL_FILE = "classpath:xsl/zeleni_sertifikat.xsl";
        else if (classOfDocument.equals(Zahtev.class))
            XSL_FILE = "classpath:xsl/zahtev_za_sertifikat.xsl";
        else
            XSL_FILE = "classpath:xsl/izvestaj_o_imunizaciji.xsl";
    }
}
