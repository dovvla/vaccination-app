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
 *         &lt;element name="Drzavljanstvo" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="Jmbg" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="Broj_pasosa" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="Ime" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="Prezime" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="Ime_roditelja" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="Pol"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="Muski"/&gt;
 *               &lt;enumeration value="Zenski"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="Datum_rodjenja" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *         &lt;element name="Mesto_rodjenja" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="Adresa" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="Mesto" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="Grad" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="Fiksni_telefon" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="Mobilni_telefon" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="Email" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="Sifra" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="Rola"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="Gradjanin"/&gt;
 *               &lt;enumeration value="Sluzbenik"/&gt;
 *               &lt;enumeration value="Zdravstveni_radnik"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
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
    "drzavljanstvo",
    "jmbg",
    "brojPasosa",
    "ime",
    "prezime",
    "imeRoditelja",
    "pol",
    "datumRodjenja",
    "mestoRodjenja",
    "adresa",
    "mesto",
    "grad",
    "fiksniTelefon",
    "mobilniTelefon",
    "email",
    "sifra",
    "rola"
})
@XmlRootElement(name = "Korisnik", namespace = "http://tim.robot/korisnik")
public class Korisnik {

    @XmlElement(name = "Drzavljanstvo", namespace = "http://tim.robot/korisnik", required = true)
    protected String drzavljanstvo;
    @XmlElement(name = "Jmbg", namespace = "http://tim.robot/korisnik", required = true)
    protected String jmbg;
    @XmlElement(name = "Broj_pasosa", namespace = "http://tim.robot/korisnik", required = true)
    protected String brojPasosa;
    @XmlElement(name = "Ime", namespace = "http://tim.robot/korisnik", required = true)
    protected String ime;
    @XmlElement(name = "Prezime", namespace = "http://tim.robot/korisnik", required = true)
    protected String prezime;
    @XmlElement(name = "Ime_roditelja", namespace = "http://tim.robot/korisnik", required = true)
    protected String imeRoditelja;
    @XmlElement(name = "Pol", namespace = "http://tim.robot/korisnik", required = true)
    protected String pol;
    @XmlElement(name = "Datum_rodjenja", namespace = "http://tim.robot/korisnik", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar datumRodjenja;
    @XmlElement(name = "Mesto_rodjenja", namespace = "http://tim.robot/korisnik", required = true)
    protected String mestoRodjenja;
    @XmlElement(name = "Adresa", namespace = "http://tim.robot/korisnik", required = true)
    protected String adresa;
    @XmlElement(name = "Mesto", namespace = "http://tim.robot/korisnik", required = true)
    protected String mesto;
    @XmlElement(name = "Grad", namespace = "http://tim.robot/korisnik", required = true)
    protected String grad;
    @XmlElement(name = "Fiksni_telefon", namespace = "http://tim.robot/korisnik", required = true)
    protected String fiksniTelefon;
    @XmlElement(name = "Mobilni_telefon", namespace = "http://tim.robot/korisnik", required = true)
    protected String mobilniTelefon;
    @XmlElement(name = "Email", namespace = "http://tim.robot/korisnik", required = true)
    protected String email;
    @XmlElement(name = "Sifra", namespace = "http://tim.robot/korisnik", required = true)
    protected String sifra;
    @XmlElement(name = "Rola", namespace = "http://tim.robot/korisnik", required = true)
    protected String rola;

    /**
     * Gets the value of the drzavljanstvo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDrzavljanstvo() {
        return drzavljanstvo;
    }

    /**
     * Sets the value of the drzavljanstvo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDrzavljanstvo(String value) {
        this.drzavljanstvo = value;
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
     * Gets the value of the brojPasosa property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBrojPasosa() {
        return brojPasosa;
    }

    /**
     * Sets the value of the brojPasosa property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBrojPasosa(String value) {
        this.brojPasosa = value;
    }

    /**
     * Gets the value of the ime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIme() {
        return ime;
    }

    /**
     * Sets the value of the ime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIme(String value) {
        this.ime = value;
    }

    /**
     * Gets the value of the prezime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrezime() {
        return prezime;
    }

    /**
     * Sets the value of the prezime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrezime(String value) {
        this.prezime = value;
    }

    /**
     * Gets the value of the imeRoditelja property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getImeRoditelja() {
        return imeRoditelja;
    }

    /**
     * Sets the value of the imeRoditelja property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setImeRoditelja(String value) {
        this.imeRoditelja = value;
    }

    /**
     * Gets the value of the pol property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPol() {
        return pol;
    }

    /**
     * Sets the value of the pol property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPol(String value) {
        this.pol = value;
    }

    /**
     * Gets the value of the datumRodjenja property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDatumRodjenja() {
        return datumRodjenja;
    }

    /**
     * Sets the value of the datumRodjenja property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDatumRodjenja(XMLGregorianCalendar value) {
        this.datumRodjenja = value;
    }

    /**
     * Gets the value of the mestoRodjenja property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMestoRodjenja() {
        return mestoRodjenja;
    }

    /**
     * Sets the value of the mestoRodjenja property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMestoRodjenja(String value) {
        this.mestoRodjenja = value;
    }

    /**
     * Gets the value of the adresa property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAdresa() {
        return adresa;
    }

    /**
     * Sets the value of the adresa property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAdresa(String value) {
        this.adresa = value;
    }

    /**
     * Gets the value of the mesto property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMesto() {
        return mesto;
    }

    /**
     * Sets the value of the mesto property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMesto(String value) {
        this.mesto = value;
    }

    /**
     * Gets the value of the grad property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGrad() {
        return grad;
    }

    /**
     * Sets the value of the grad property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGrad(String value) {
        this.grad = value;
    }

    /**
     * Gets the value of the fiksniTelefon property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFiksniTelefon() {
        return fiksniTelefon;
    }

    /**
     * Sets the value of the fiksniTelefon property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFiksniTelefon(String value) {
        this.fiksniTelefon = value;
    }

    /**
     * Gets the value of the mobilniTelefon property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMobilniTelefon() {
        return mobilniTelefon;
    }

    /**
     * Sets the value of the mobilniTelefon property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMobilniTelefon(String value) {
        this.mobilniTelefon = value;
    }

    /**
     * Gets the value of the email property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the value of the email property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmail(String value) {
        this.email = value;
    }

    /**
     * Gets the value of the sifra property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSifra() {
        return sifra;
    }

    /**
     * Sets the value of the sifra property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSifra(String value) {
        this.sifra = value;
    }

    /**
     * Gets the value of the rola property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRola() {
        return rola;
    }

    /**
     * Sets the value of the rola property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRola(String value) {
        this.rola = value;
    }

}
