//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.2 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2022.01.13 at 07:08:37 AM CET 
//


package com.timrobot.vaccapp.models;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for TDoza_vakcine complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TDoza_vakcine"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Datum_davanja" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *         &lt;element name="Serija" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TDoza_vakcine", namespace = "http://tim.robot/potvrda_o_vakcinaciji", propOrder = {
    "datumDavanja",
    "serija"
})
public class TDozaVakcine {

    @XmlElement(name = "Datum_davanja", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar datumDavanja;
    @XmlElement(name = "Serija", required = true)
    protected String serija;

    /**
     * Gets the value of the datumDavanja property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDatumDavanja() {
        return datumDavanja;
    }

    /**
     * Sets the value of the datumDavanja property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDatumDavanja(XMLGregorianCalendar value) {
        this.datumDavanja = value;
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

}
