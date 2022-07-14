//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.2 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2022.07.14 at 03:58:57 AM CEST 
//


package com.timrobot.vaccapp.models;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for TPrimljena_vakcina complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TPrimljena_vakcina"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Naziv" type="{http://tim.robot/obrazac_saglasnosti_za_imunizaciju}TNazivVakcine"/&gt;
 *         &lt;element name="Datum_izdavanja" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *         &lt;element name="Ekstremitet"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="DR"/&gt;
 *               &lt;enumeration value="LR"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="Serija_vakcine" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="Proizvodjac" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="Nezeljena_reakcija" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TPrimljena_vakcina", propOrder = {
    "naziv",
    "datumIzdavanja",
    "ekstremitet",
    "serijaVakcine",
    "proizvodjac",
    "nezeljenaReakcija"
})
public class TPrimljenaVakcina {

    @XmlElement(name = "Naziv", required = true)
    protected TNazivVakcine naziv;
    @XmlElement(name = "Datum_izdavanja", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar datumIzdavanja;
    @XmlElement(name = "Ekstremitet", required = true)
    protected String ekstremitet;
    @XmlElement(name = "Serija_vakcine", required = true)
    protected String serijaVakcine;
    @XmlElement(name = "Proizvodjac", required = true)
    protected String proizvodjac;
    @XmlElement(name = "Nezeljena_reakcija", required = true)
    protected String nezeljenaReakcija;

    /**
     * Gets the value of the naziv property.
     * 
     * @return
     *     possible object is
     *     {@link TNazivVakcine }
     *     
     */
    public TNazivVakcine getNaziv() {
        return naziv;
    }

    /**
     * Sets the value of the naziv property.
     * 
     * @param value
     *     allowed object is
     *     {@link TNazivVakcine }
     *     
     */
    public void setNaziv(TNazivVakcine value) {
        this.naziv = value;
    }

    /**
     * Gets the value of the datumIzdavanja property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDatumIzdavanja() {
        return datumIzdavanja;
    }

    /**
     * Sets the value of the datumIzdavanja property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDatumIzdavanja(XMLGregorianCalendar value) {
        this.datumIzdavanja = value;
    }

    /**
     * Gets the value of the ekstremitet property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEkstremitet() {
        return ekstremitet;
    }

    /**
     * Sets the value of the ekstremitet property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEkstremitet(String value) {
        this.ekstremitet = value;
    }

    /**
     * Gets the value of the serijaVakcine property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSerijaVakcine() {
        return serijaVakcine;
    }

    /**
     * Sets the value of the serijaVakcine property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSerijaVakcine(String value) {
        this.serijaVakcine = value;
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
     * Gets the value of the nezeljenaReakcija property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNezeljenaReakcija() {
        return nezeljenaReakcija;
    }

    /**
     * Sets the value of the nezeljenaReakcija property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNezeljenaReakcija(String value) {
        this.nezeljenaReakcija = value;
    }

}
