package com.timrobot.vaccapp.services;

import com.timrobot.vaccapp.models.Izvestaj;

import javax.xml.transform.TransformerException;

public interface DemoService {
    Izvestaj unmarshalExample();
    void marshalExample();
    void storeInXMLDBExample();
    Izvestaj retrieveFromXMLDBExample();
    void RDFExample() throws TransformerException;
}
