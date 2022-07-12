package com.timrobot.vaccapp.utility;

import com.timrobot.vaccapp.models.*;
import net.sf.saxon.TransformerFactoryImpl;
import org.apache.fop.apps.FOUserAgent;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;
import org.springframework.util.ResourceUtils;
import org.xml.sax.SAXException;

import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;

public class PdfUtil {
    public static final String FOX_XCONF = "classpath:xsl_fo/fop.xconf";
    public static String XSL_FILE;
    public static String PDF_FILE = "document.pdf";
    private final FopFactory fopFactory;
    private final TransformerFactory transformerFactory;

    public PdfUtil() throws SAXException, IOException {
        fopFactory = FopFactory.newInstance(ResourceUtils.getFile(FOX_XCONF));
        transformerFactory = new TransformerFactoryImpl();
    }

    public ByteArrayInputStream generatePDF(String documentXml, Class<?> classOfDocument) {
        try {
            setXSLFile(classOfDocument);

            File xslFile = ResourceUtils.getFile(XSL_FILE);
            StreamSource transformSource = new StreamSource(xslFile);
            StreamSource source = new StreamSource(new ByteArrayInputStream(documentXml.getBytes()));
            FOUserAgent userAgent = fopFactory.newFOUserAgent();
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            Transformer xslFoTransformer = transformerFactory.newTransformer(transformSource);
            Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, userAgent, outStream);
            Result res = new SAXResult(fop.getDefaultHandler());
            xslFoTransformer.transform(source, res);
            OutputStream outputStream = new FileOutputStream(PDF_FILE);
            outStream.writeTo(outputStream);
            outputStream.close();
            return new ByteArrayInputStream(outStream.toByteArray());
        } catch (Exception ignored) {
        }
        return null;
    }

    private void setXSLFile(Class<?> classOfDocument) {
        if (classOfDocument.equals(ObrazacInteresovanja.class))
            XSL_FILE = "classpath:xsl_fo/iskazivanje_interesovanja_za_vakcinaciju.xsl";
        else if (classOfDocument.equals(Obrazac.class))
            XSL_FILE = "classpath:xsl_fo/saglasnost.xsl";
        else if (classOfDocument.equals(Potvrda.class))
            XSL_FILE = "classpath:xsl_fo/potvrda_o_vakcinaciji.xsl";
        else if (classOfDocument.equals(Sertifikat.class))
            XSL_FILE = "classpath:xsl_fo/zeleni_sertifikat.xsl";
        else if(classOfDocument.equals(Zahtev.class))
            XSL_FILE = "classpath:xsl_fo/zahtev_za_sertifikat.xsl";
        else
            XSL_FILE = "classpath:xsl_fo/izvestaj_o_imunizaciji.xsl";
    }

}
