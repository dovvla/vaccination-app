//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.2 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2022.07.12 at 10:28:31 AM CEST 
//


package com.timrobot.vaccapp.models;

import java.math.BigInteger;
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
 *         &lt;element name="Podaci_o_sertifikatu"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="Broj" type="{http://www.w3.org/2001/XMLSchema}positiveInteger"/&gt;
 *                   &lt;element name="Datum_i_vreme" type="{http://www.w3.org/2001/XMLSchema}dateTime"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="Podaci_o_pacijentu"&gt;
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
 *                   &lt;element name="Datum_rodjenja" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
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
 *         &lt;element name="Doza_vakcinacije" type="{http://tim.robot/zeleni_sertifikat}TDoza_vakcinacije" maxOccurs="2" minOccurs="0"/&gt;
 *         &lt;element name="Kovid_test" type="{http://tim.robot/zeleni_sertifikat}TKovid_test" maxOccurs="unbounded" minOccurs="0"/&gt;
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
    "podaciOSertifikatu",
    "podaciOPacijentu",
    "dozaVakcinacije",
    "kovidTest"
})
@XmlRootElement(name = "Sertifikat", namespace = "http://tim.robot/zeleni_sertifikat")
public class Sertifikat {

    @XmlElement(name = "Podaci_o_sertifikatu", namespace = "http://tim.robot/zeleni_sertifikat", required = true)
    protected Sertifikat.PodaciOSertifikatu podaciOSertifikatu;
    @XmlElement(name = "Podaci_o_pacijentu", namespace = "http://tim.robot/zeleni_sertifikat", required = true)
    protected Sertifikat.PodaciOPacijentu podaciOPacijentu;
    @XmlElement(name = "Doza_vakcinacije", namespace = "http://tim.robot/zeleni_sertifikat")
    protected List<TDozaVakcinacije> dozaVakcinacije;
    @XmlElement(name = "Kovid_test", namespace = "http://tim.robot/zeleni_sertifikat")
    protected List<TKovidTest> kovidTest;

    /**
     * Gets the value of the podaciOSertifikatu property.
     * 
     * @return
     *     possible object is
     *     {@link Sertifikat.PodaciOSertifikatu }
     *     
     */
    public Sertifikat.PodaciOSertifikatu getPodaciOSertifikatu() {
        return podaciOSertifikatu;
    }

    /**
     * Sets the value of the podaciOSertifikatu property.
     * 
     * @param value
     *     allowed object is
     *     {@link Sertifikat.PodaciOSertifikatu }
     *     
     */
    public void setPodaciOSertifikatu(Sertifikat.PodaciOSertifikatu value) {
        this.podaciOSertifikatu = value;
    }

    /**
     * Gets the value of the podaciOPacijentu property.
     * 
     * @return
     *     possible object is
     *     {@link Sertifikat.PodaciOPacijentu }
     *     
     */
    public Sertifikat.PodaciOPacijentu getPodaciOPacijentu() {
        return podaciOPacijentu;
    }

    /**
     * Sets the value of the podaciOPacijentu property.
     * 
     * @param value
     *     allowed object is
     *     {@link Sertifikat.PodaciOPacijentu }
     *     
     */
    public void setPodaciOPacijentu(Sertifikat.PodaciOPacijentu value) {
        this.podaciOPacijentu = value;
    }

    /**
     * Gets the value of the dozaVakcinacije property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the dozaVakcinacije property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDozaVakcinacije().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TDozaVakcinacije }
     * 
     * 
     */
    public List<TDozaVakcinacije> getDozaVakcinacije() {
        if (dozaVakcinacije == null) {
            dozaVakcinacije = new ArrayList<TDozaVakcinacije>();
        }
        return this.dozaVakcinacije;
    }

    /**
     * Gets the value of the kovidTest property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the kovidTest property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getKovidTest().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TKovidTest }
     * 
     * 
     */
    public List<TKovidTest> getKovidTest() {
        if (kovidTest == null) {
            kovidTest = new ArrayList<TKovidTest>();
        }
        return this.kovidTest;
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
     *         &lt;element name="Datum_rodjenja" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
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
        "pol",
        "datumRodjenja",
        "jmbg",
        "brojPasosa"
    })
    public static class PodaciOPacijentu {

        @XmlElement(name = "Ime", namespace = "http://tim.robot/zeleni_sertifikat", required = true)
        protected String ime;
        @XmlElement(name = "Prezime", namespace = "http://tim.robot/zeleni_sertifikat", required = true)
        protected String prezime;
        @XmlElement(name = "Pol", namespace = "http://tim.robot/zeleni_sertifikat", required = true)
        protected String pol;
        @XmlElement(name = "Datum_rodjenja", namespace = "http://tim.robot/zeleni_sertifikat", required = true)
        @XmlSchemaType(name = "date")
        protected XMLGregorianCalendar datumRodjenja;
        @XmlElement(name = "JMBG", namespace = "http://tim.robot/zeleni_sertifikat", required = true)
        protected String jmbg;
        @XmlElement(name = "Broj_pasosa", namespace = "http://tim.robot/zeleni_sertifikat", required = true)
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
     *         &lt;element name="Broj" type="{http://www.w3.org/2001/XMLSchema}positiveInteger"/&gt;
     *         &lt;element name="Datum_i_vreme" type="{http://www.w3.org/2001/XMLSchema}dateTime"/&gt;
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
        "broj",
        "datumIVreme"
    })
    public static class PodaciOSertifikatu {

        @XmlElement(name = "Broj", namespace = "http://tim.robot/zeleni_sertifikat", required = true)
        @XmlSchemaType(name = "positiveInteger")
        protected BigInteger broj;
        @XmlElement(name = "Datum_i_vreme", namespace = "http://tim.robot/zeleni_sertifikat", required = true)
        @XmlSchemaType(name = "dateTime")
        protected XMLGregorianCalendar datumIVreme;

        /**
         * Gets the value of the broj property.
         * 
         * @return
         *     possible object is
         *     {@link BigInteger }
         *     
         */
        public BigInteger getBroj() {
            return broj;
        }

        /**
         * Sets the value of the broj property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigInteger }
         *     
         */
        public void setBroj(BigInteger value) {
            this.broj = value;
        }

        /**
         * Gets the value of the datumIVreme property.
         * 
         * @return
         *     possible object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public XMLGregorianCalendar getDatumIVreme() {
            return datumIVreme;
        }

        /**
         * Sets the value of the datumIVreme property.
         * 
         * @param value
         *     allowed object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public void setDatumIVreme(XMLGregorianCalendar value) {
            this.datumIVreme = value;
        }

    }

}
