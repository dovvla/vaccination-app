//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.2 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2022.07.19 at 09:05:19 AM CEST 
//


package com.timrobot.vaccapp.models;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for TDoza_vakcinacije complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TDoza_vakcinacije"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Tip" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="Proizvodjac" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="Serija" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="Datum" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *         &lt;element name="Zdravstvena_ustanova" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TDoza_vakcinacije", namespace = "http://tim.robot/zeleni_sertifikat", propOrder = {
    "tip",
    "proizvodjac",
    "serija",
    "datum",
    "zdravstvenaUstanova"
})
public class TDozaVakcinacije {

    @XmlElement(name = "Tip", required = true)
    protected String tip;
    @XmlElement(name = "Proizvodjac", required = true)
    protected String proizvodjac;
    @XmlElement(name = "Serija", required = true)
    protected String serija;
    @XmlElement(name = "Datum", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar datum;
    @XmlElement(name = "Zdravstvena_ustanova", required = true)
    protected String zdravstvenaUstanova;

    /**
     * Gets the value of the tip property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTip() {
        return tip;
    }

    /**
     * Sets the value of the tip property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTip(String value) {
        this.tip = value;
    }

    /**
     * Gets the value of the proizvodjac property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProizvodjac() {
        return proizvodjac;
    }

    /**
     * Sets the value of the proizvodjac property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProizvodjac(String value) {
        this.proizvodjac = value;
    }

    /**
     * Gets the value of the serija property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSerija() {
        return serija;
    }

    /**
     * Sets the value of the serija property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSerija(String value) {
        this.serija = value;
    }

    /**
     * Gets the value of the datum property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDatum() {
        return datum;
    }

    /**
     * Sets the value of the datum property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDatum(XMLGregorianCalendar value) {
        this.datum = value;
    }

    /**
     * Gets the value of the zdravstvenaUstanova property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZdravstvenaUstanova() {
        return zdravstvenaUstanova;
    }

    /**
     * Sets the value of the zdravstvenaUstanova property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZdravstvenaUstanova(String value) {
        this.zdravstvenaUstanova = value;
    }

}
