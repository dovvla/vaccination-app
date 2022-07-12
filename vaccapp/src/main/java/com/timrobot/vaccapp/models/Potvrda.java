//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.2 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2022.07.11 at 11:48:26 PM CEST 
//


package com.timrobot.vaccapp.models;

import java.util.ArrayList;
import java.util.List;
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
 *         &lt;element name="Sifra_potvrde" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="Podaci_pacijenta"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="Ime" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                   &lt;element name="Prezime" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                   &lt;element name="Pol"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;enumeration value="Musko"/&gt;
 *                         &lt;enumeration value="Zensko"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="JMBG"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;pattern value="\d{13}"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="Doza_vakcine" type="{http://tim.robot/potvrda_o_vakcinaciji}TDoza_vakcine" maxOccurs="2"/&gt;
 *         &lt;element name="Zdravstvena_ustanova" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="Naziv_vakcine"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="Pfizer"/&gt;
 *               &lt;enumeration value="Sputnik"/&gt;
 *               &lt;enumeration value="Sinopharm"/&gt;
 *               &lt;enumeration value="AZ"/&gt;
 *               &lt;enumeration value="Moderna"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="Datum" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
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
    "sifraPotvrde",
    "qrKod",
    "podaciPacijenta",
    "dozaVakcine",
    "zdravstvenaUstanova",
    "nazivVakcine",
    "datum"
})
@XmlRootElement(name = "Potvrda", namespace = "http://tim.robot/potvrda_o_vakcinaciji")
public class Potvrda {

    @XmlElement(name = "Sifra_potvrde", namespace = "http://tim.robot/potvrda_o_vakcinaciji", required = true)
    protected String sifraPotvrde;
    @XmlElement(name = "QRkod", namespace = "http://tim.robot/potvrda_o_vakcinaciji", required = true)
    protected String qrKod;
    @XmlElement(name = "Podaci_pacijenta", namespace = "http://tim.robot/potvrda_o_vakcinaciji", required = true)
    protected Potvrda.PodaciPacijenta podaciPacijenta;
    @XmlElement(name = "Doza_vakcine", namespace = "http://tim.robot/potvrda_o_vakcinaciji", required = true)
    protected List<TDozaVakcine> dozaVakcine;
    @XmlElement(name = "Zdravstvena_ustanova", namespace = "http://tim.robot/potvrda_o_vakcinaciji", required = true)
    protected String zdravstvenaUstanova;
    @XmlElement(name = "Naziv_vakcine", namespace = "http://tim.robot/potvrda_o_vakcinaciji", required = true)
    protected String nazivVakcine;
    @XmlElement(name = "Datum", namespace = "http://tim.robot/potvrda_o_vakcinaciji", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar datum;

    /**
     * Gets the value of the sifraPotvrde property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSifraPotvrde() {
        return sifraPotvrde;
    }

    /**
     * Sets the value of the sifraPotvrde property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSifraPotvrde(String value) {
        this.sifraPotvrde = value;
    }

    public String getQrKod() {
        return qrKod;
    }

    public void setQrKod(String qrKod) {
        this.qrKod = qrKod;
    }

    /**
     * Gets the value of the podaciPacijenta property.
     * 
     * @return
     *     possible object is
     *     {@link Potvrda.PodaciPacijenta }
     *     
     */



    public Potvrda.PodaciPacijenta getPodaciPacijenta() {
        return podaciPacijenta;
    }

    /**
     * Sets the value of the podaciPacijenta property.
     * 
     * @param value
     *     allowed object is
     *     {@link Potvrda.PodaciPacijenta }
     *     
     */
    public void setPodaciPacijenta(Potvrda.PodaciPacijenta value) {
        this.podaciPacijenta = value;
    }

    /**
     * Gets the value of the dozaVakcine property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the dozaVakcine property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDozaVakcine().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TDozaVakcine }
     * 
     * 
     */
    public List<TDozaVakcine> getDozaVakcine() {
        if (dozaVakcine == null) {
            dozaVakcine = new ArrayList<TDozaVakcine>();
        }
        return this.dozaVakcine;
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

    /**
     * Gets the value of the nazivVakcine property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNazivVakcine() {
        return nazivVakcine;
    }

    /**
     * Sets the value of the nazivVakcine property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNazivVakcine(String value) {
        this.nazivVakcine = value;
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
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;sequence&gt;
     *         &lt;element name="Ime" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *         &lt;element name="Prezime" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *         &lt;element name="Pol"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;enumeration value="Musko"/&gt;
     *               &lt;enumeration value="Zensko"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="JMBG"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;pattern value="\d{13}"/&gt;
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
        "ime",
        "prezime",
        "pol",
        "jmbg"
    })
    public static class PodaciPacijenta {

        @XmlElement(name = "Ime", namespace = "http://tim.robot/potvrda_o_vakcinaciji", required = true)
        protected String ime;
        @XmlElement(name = "Prezime", namespace = "http://tim.robot/potvrda_o_vakcinaciji", required = true)
        protected String prezime;
        @XmlElement(name = "Pol", namespace = "http://tim.robot/potvrda_o_vakcinaciji", required = true)
        protected String pol;
        @XmlElement(name = "JMBG", namespace = "http://tim.robot/potvrda_o_vakcinaciji", required = true)
        protected String jmbg;

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
         * Gets the value of the jmbg property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getJMBG() {
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
        public void setJMBG(String value) {
            this.jmbg = value;
        }

    }

}
