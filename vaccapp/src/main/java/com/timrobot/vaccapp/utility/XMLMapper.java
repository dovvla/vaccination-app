package com.timrobot.vaccapp.utility;

import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.*;

@Component
@SuppressWarnings("unchecked")
public class XMLMapper {

    public static <T> T unmarshal(Class<?> clazz, Object xmlObject, String xsdFileName) {
        try {
            JAXBContext context = JAXBContext.newInstance(clazz);
            Unmarshaller unmarshaller = context.createUnmarshaller();

            SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = schemaFactory.newSchema(new File("src/main/resources/xsd/" + xsdFileName));
            unmarshaller.setSchema(schema);
            unmarshaller.setEventHandler(new MyValidationEventHandler());

//            T unmarshalledObject = (T) unmarshaller.unmarshal(new File("./src/main/resources/xml/" + xmlFileName));
            T unmarshalledObject = null;
            if (xmlObject instanceof File)
                unmarshalledObject = (T) unmarshaller.unmarshal((File) xmlObject);
            else if (xmlObject instanceof Node)
                unmarshalledObject = (T) unmarshaller.unmarshal((Node) xmlObject);
            return unmarshalledObject;
        } catch (JAXBException | SAXException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void marshal(Class<?> clazz, OutputStream outputStream, String xsdFileName, Object objectToMarshal) {
        try {
            JAXBContext context = JAXBContext.newInstance(clazz);
            Marshaller marshaller = context.createMarshaller();

            SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = schemaFactory.newSchema(new File("src/main/resources/xsd/" + xsdFileName));
            marshaller.setSchema(schema);
            marshaller.setEventHandler(new MyValidationEventHandler());

            marshaller.marshal(objectToMarshal, outputStream);
        } catch (JAXBException | SAXException e) {
            e.printStackTrace();
        }
    }

    public Object convertToObject(String xmlString, String xsdFileName, Class<?> classOfObject) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(classOfObject);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

            File file = ResourceUtils.getFile("src/main/resources/xsd/" + xsdFileName + ".xsd");
            Schema schema = schemaFactory.newSchema(file);
            unmarshaller.setSchema(schema);

            return unmarshaller.unmarshal(new StringReader(xmlString));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String convertToXml(Object object, Class<?> classOfObject) {
        StringWriter sw = new StringWriter();
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(classOfObject);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            marshaller.marshal(object, sw);
        } catch (Exception ignored) {
        }
        return sw.toString();
    }

}
