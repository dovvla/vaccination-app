//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.2 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2022.07.18 at 07:21:59 PM CEST 
//


package com.timrobot.vaccapp.models;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
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
 *         &lt;element name="Identifikator" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="Podaci_o_podnosiocu"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="Ime" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                   &lt;element name="Prezime" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                   &lt;element name="Datum_rodjenja" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
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
 *                   &lt;element name="Broj_pasosa"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;pattern value="\d{9}"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="Razlog_za_podnosenje" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="Mesto" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="Datum" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *         &lt;element name="Status"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="Prihvacen"/&gt;
 *               &lt;enumeration value="Odbijen"/&gt;
 *               &lt;enumeration value="Neobradjen"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="Razlog_za_odbijanje" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
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
    "identifikator",
    "podaciOPodnosiocu",
    "razlogZaPodnosenje",
    "mesto",
    "datum",
    "status",
    "razlogZaOdbijanje"
})
@XmlRootElement(name = "Zahtev", namespace = "http://tim.robot/zahtev_za_sertifikat")
public class Zahtev {

    @XmlElement(name = "Identifikator", namespace = "http://tim.robot/zahtev_za_sertifikat", required = true)
    protected String identifikator;
    @XmlElement(name = "Podaci_o_podnosiocu", namespace = "http://tim.robot/zahtev_za_sertifikat", required = true)
    protected Zahtev.PodaciOPodnosiocu podaciOPodnosiocu;
    @XmlElement(name = "Razlog_za_podnosenje", namespace = "http://tim.robot/zahtev_za_sertifikat", required = true)
    protected String razlogZaPodnosenje;
    @XmlElement(name = "Mesto", namespace = "http://tim.robot/zahtev_za_sertifikat", required = true)
    protected String mesto;
    @XmlElement(name = "Datum", namespace = "http://tim.robot/zahtev_za_sertifikat", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar datum;
    @XmlElement(name = "Status", namespace = "http://tim.robot/zahtev_za_sertifikat", required = true, defaultValue = "Neobradjen")
    protected String status;
    @XmlElementRef(name = "Razlog_za_odbijanje", namespace = "http://tim.robot/zahtev_za_sertifikat", type = JAXBElement.class, required = false)
    protected JAXBElement<String> razlogZaOdbijanje;

    /**
     * Gets the value of the identifikator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdentifikator() {
        return identifikator;
    }

    /**
     * Sets the value of the identifikator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdentifikator(String value) {
        this.identifikator = value;
    }

    /**
     * Gets the value of the podaciOPodnosiocu property.
     * 
     * @return
     *     possible object is
     *     {@link Zahtev.PodaciOPodnosiocu }
     *     
     */
    public Zahtev.PodaciOPodnosiocu getPodaciOPodnosiocu() {
        return podaciOPodnosiocu;
    }

    /**
     * Sets the value of the podaciOPodnosiocu property.
     * 
     * @param value
     *     allowed object is
     *     {@link Zahtev.PodaciOPodnosiocu }
     *     
     */
    public void setPodaciOPodnosiocu(Zahtev.PodaciOPodnosiocu value) {
        this.podaciOPodnosiocu = value;
    }

    /**
     * Gets the value of the razlogZaPodnosenje property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRazlogZaPodnosenje() {
        return razlogZaPodnosenje;
    }

    /**
     * Sets the value of the razlogZaPodnosenje property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRazlogZaPodnosenje(String value) {
        this.razlogZaPodnosenje = value;
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
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatus(String value) {
        this.status = value;
    }

    /**
     * Gets the value of the razlogZaOdbijanje property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getRazlogZaOdbijanje() {
        return razlogZaOdbijanje;
    }

    /**
     * Sets the value of the razlogZaOdbijanje property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setRazlogZaOdbijanje(JAXBElement<String> value) {
        this.razlogZaOdbijanje = value;
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
     *         &lt;element name="Datum_rodjenja" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
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
     *         &lt;element name="Broj_pasosa"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;pattern value="\d{9}"/&gt;
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
        "datumRodjenja",
        "pol",
        "jmbg",
        "brojPasosa"
    })
    public static class PodaciOPodnosiocu {

        @XmlElement(name = "Ime", namespace = "http://tim.robot/zahtev_za_sertifikat", required = true)
        protected String ime;
        @XmlElement(name = "Prezime", namespace = "http://tim.robot/zahtev_za_sertifikat", required = true)
        protected String prezime;
        @XmlElement(name = "Datum_rodjenja", namespace = "http://tim.robot/zahtev_za_sertifikat", required = true)
        @XmlSchemaType(name = "date")
        protected XMLGregorianCalendar datumRodjenja;
        @XmlElement(name = "Pol", namespace = "http://tim.robot/zahtev_za_sertifikat", required = true)
        protected String pol;
        @XmlElement(name = "JMBG", namespace = "http://tim.robot/zahtev_za_sertifikat", required = true)
        protected String jmbg;
        @XmlElement(name = "Broj_pasosa", namespace = "http://tim.robot/zahtev_za_sertifikat", required = true)
        protected String brojPasosa;

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

    }

}
