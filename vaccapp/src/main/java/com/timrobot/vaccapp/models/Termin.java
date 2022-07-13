//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.2 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2022.07.13 at 07:51:48 PM CEST 
//


package com.timrobot.vaccapp.models;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="datumVreme" type="{http://www.w3.org/2001/XMLSchema}dateTime"/&gt;
 *         &lt;element name="jmbg" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="vakcina" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "datumVreme",
    "jmbg",
    "vakcina"
})
@XmlRootElement(name = "Termin", namespace = "http://www.ftn.uns.ac.rs/termin")
public class Termin {

    @XmlElement(namespace = "http://www.ftn.uns.ac.rs/termin", required = true, nillable = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar datumVreme;
    @XmlElement(namespace = "http://www.ftn.uns.ac.rs/termin", required = true)
    protected String jmbg;
    @XmlElement(namespace = "http://www.ftn.uns.ac.rs/termin", required = true)
    protected String vakcina;

    /**
     * Gets the value of the datumVreme property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDatumVreme() {
        return datumVreme;
    }

    /**
     * Sets the value of the datumVreme property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDatumVreme(XMLGregorianCalendar value) {
        this.datumVreme = value;
    }

    /**
     * Gets the value of the jmbg property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getJmbg() {
        return jmbg;
    }

    /**
     * Sets the value of the jmbg property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setJmbg(String value) {
        this.jmbg = value;
    }

    /**
     * Gets the value of the vakcina property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVakcina() {
        return vakcina;
    }

    /**
     * Sets the value of the vakcina property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVakcina(String value) {
        this.vakcina = value;
    }

}
