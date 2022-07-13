//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.2 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2022.07.13 at 12:25:58 AM CEST 
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
 *         &lt;element name="Licni_podaci"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="Drzavljanstvo"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;enumeration value="RS"/&gt;
 *                         &lt;enumeration value="Strani sa boravkom"/&gt;
 *                         &lt;enumeration value="Strani bez boravka"/&gt;
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
 *                   &lt;element name="Ime" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                   &lt;element name="Prezime" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                   &lt;element name="Imejl"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;pattern value="[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,64}"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="Broj_mobilnog_telefona"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;pattern value="06\d{7,8}"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="Broj_fiksnog_telefona"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;pattern value="0\d{8,9}"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="Zeljena_lokacija_vakcinacije" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="Zeljena_vakcina"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="Pfizer"/&gt;
 *               &lt;enumeration value="Sputnik"/&gt;
 *               &lt;enumeration value="Sinopharm"/&gt;
 *               &lt;enumeration value="AZ"/&gt;
 *               &lt;enumeration value="Moderna"/&gt;
 *               &lt;enumeration value="Bilo koja"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="Davalac_krvi"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;whiteSpace value="preserve"/&gt;
 *               &lt;enumeration value="Da"/&gt;
 *               &lt;enumeration value="Ne"/&gt;
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
    "licniPodaci",
    "zeljenaLokacijaVakcinacije",
    "zeljenaVakcina",
    "davalacKrvi",
    "datum"
})
@XmlRootElement(name = "Obrazac_interesovanja", namespace = "http://tim.robot/iskazivanje_interesovanja_za_vakcinaciju")
public class ObrazacInteresovanja {

    @XmlElement(name = "Licni_podaci", namespace = "http://tim.robot/iskazivanje_interesovanja_za_vakcinaciju", required = true)
    protected ObrazacInteresovanja.LicniPodaci licniPodaci;
    @XmlElement(name = "Zeljena_lokacija_vakcinacije", namespace = "http://tim.robot/iskazivanje_interesovanja_za_vakcinaciju", required = true)
    protected String zeljenaLokacijaVakcinacije;
    @XmlElement(name = "Zeljena_vakcina", namespace = "http://tim.robot/iskazivanje_interesovanja_za_vakcinaciju", required = true)
    protected String zeljenaVakcina;
    @XmlElement(name = "Davalac_krvi", namespace = "http://tim.robot/iskazivanje_interesovanja_za_vakcinaciju", required = true)
    protected String davalacKrvi;
    @XmlElement(name = "Datum", namespace = "http://tim.robot/iskazivanje_interesovanja_za_vakcinaciju", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar datum;

    /**
     * Gets the value of the licniPodaci property.
     * 
     * @return
     *     possible object is
     *     {@link ObrazacInteresovanja.LicniPodaci }
     *     
     */
    public ObrazacInteresovanja.LicniPodaci getLicniPodaci() {
        return licniPodaci;
    }

    /**
     * Sets the value of the licniPodaci property.
     * 
     * @param value
     *     allowed object is
     *     {@link ObrazacInteresovanja.LicniPodaci }
     *     
     */
    public void setLicniPodaci(ObrazacInteresovanja.LicniPodaci value) {
        this.licniPodaci = value;
    }

    /**
     * Gets the value of the zeljenaLokacijaVakcinacije property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZeljenaLokacijaVakcinacije() {
        return zeljenaLokacijaVakcinacije;
    }

    /**
     * Sets the value of the zeljenaLokacijaVakcinacije property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZeljenaLokacijaVakcinacije(String value) {
        this.zeljenaLokacijaVakcinacije = value;
    }

    /**
     * Gets the value of the zeljenaVakcina property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZeljenaVakcina() {
        return zeljenaVakcina;
    }

    /**
     * Sets the value of the zeljenaVakcina property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZeljenaVakcina(String value) {
        this.zeljenaVakcina = value;
    }

    /**
     * Gets the value of the davalacKrvi property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDavalacKrvi() {
        return davalacKrvi;
    }

    /**
     * Sets the value of the davalacKrvi property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDavalacKrvi(String value) {
        this.davalacKrvi = value;
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
     *         &lt;element name="Drzavljanstvo"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;enumeration value="RS"/&gt;
     *               &lt;enumeration value="Strani sa boravkom"/&gt;
     *               &lt;enumeration value="Strani bez boravka"/&gt;
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
     *         &lt;element name="Ime" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *         &lt;element name="Prezime" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *         &lt;element name="Imejl"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;pattern value="[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,64}"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="Broj_mobilnog_telefona"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;pattern value="06\d{7,8}"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="Broj_fiksnog_telefona"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;pattern value="0\d{8,9}"/&gt;
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
        "ime",
        "prezime",
        "imejl",
        "brojMobilnogTelefona",
        "brojFiksnogTelefona"
    })
    public static class LicniPodaci {

        @XmlElement(name = "Drzavljanstvo", namespace = "http://tim.robot/iskazivanje_interesovanja_za_vakcinaciju", required = true)
        protected String drzavljanstvo;
        @XmlElement(name = "JMBG", namespace = "http://tim.robot/iskazivanje_interesovanja_za_vakcinaciju", required = true)
        protected String jmbg;
        @XmlElement(name = "Ime", namespace = "http://tim.robot/iskazivanje_interesovanja_za_vakcinaciju", required = true)
        protected String ime;
        @XmlElement(name = "Prezime", namespace = "http://tim.robot/iskazivanje_interesovanja_za_vakcinaciju", required = true)
        protected String prezime;
        @XmlElement(name = "Imejl", namespace = "http://tim.robot/iskazivanje_interesovanja_za_vakcinaciju", required = true)
        protected String imejl;
        @XmlElement(name = "Broj_mobilnog_telefona", namespace = "http://tim.robot/iskazivanje_interesovanja_za_vakcinaciju", required = true)
        protected String brojMobilnogTelefona;
        @XmlElement(name = "Broj_fiksnog_telefona", namespace = "http://tim.robot/iskazivanje_interesovanja_za_vakcinaciju", required = true)
        protected String brojFiksnogTelefona;

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
         * Gets the value of the imejl property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getImejl() {
            return imejl;
        }

        /**
         * Sets the value of the imejl property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setImejl(String value) {
            this.imejl = value;
        }

        /**
         * Gets the value of the brojMobilnogTelefona property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getBrojMobilnogTelefona() {
            return brojMobilnogTelefona;
        }

        /**
         * Sets the value of the brojMobilnogTelefona property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setBrojMobilnogTelefona(String value) {
            this.brojMobilnogTelefona = value;
        }

        /**
         * Gets the value of the brojFiksnogTelefona property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getBrojFiksnogTelefona() {
            return brojFiksnogTelefona;
        }

        /**
         * Sets the value of the brojFiksnogTelefona property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setBrojFiksnogTelefona(String value) {
            this.brojFiksnogTelefona = value;
        }

    }

}
