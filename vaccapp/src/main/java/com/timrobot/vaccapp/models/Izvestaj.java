//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.2 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2022.07.19 at 09:05:19 AM CEST 
//


package com.timrobot.vaccapp.models;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;
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
 *         &lt;element name="Period_izvestaja"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="Datum_od"&gt;
 *                     &lt;complexType&gt;
 *                       &lt;simpleContent&gt;
 *                         &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema&gt;date"&gt;
 *                           &lt;attribute name="property" use="required" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" fixed="pred:datum_od" /&gt;
 *                         &lt;/extension&gt;
 *                       &lt;/simpleContent&gt;
 *                     &lt;/complexType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="Datum_do"&gt;
 *                     &lt;complexType&gt;
 *                       &lt;simpleContent&gt;
 *                         &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema&gt;date"&gt;
 *                           &lt;attribute name="property" use="required" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" fixed="pred:datum_do" /&gt;
 *                         &lt;/extension&gt;
 *                       &lt;/simpleContent&gt;
 *                     &lt;/complexType&gt;
 *                   &lt;/element&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="Podnetih_interesovanja_imunizacije" type="{http://www.w3.org/2001/XMLSchema}positiveInteger"/&gt;
 *         &lt;element name="Zahtevi_za_zeleni_sertifikat" type="{http://www.w3.org/2001/XMLSchema}positiveInteger"/&gt;
 *         &lt;element name="Izdatih_zelenih_sertifikata" type="{http://www.w3.org/2001/XMLSchema}positiveInteger"/&gt;
 *         &lt;element name="Podaci_o_datim_dozama"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="Ukupno_doza" type="{http://www.w3.org/2001/XMLSchema}positiveInteger"/&gt;
 *                   &lt;element name="Broj_date_prve_doze" type="{http://www.w3.org/2001/XMLSchema}positiveInteger"/&gt;
 *                   &lt;element name="Broj_date_druge_doze" type="{http://www.w3.org/2001/XMLSchema}positiveInteger"/&gt;
 *                   &lt;element name="Broj_date_trece_doze" type="{http://www.w3.org/2001/XMLSchema}positiveInteger"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="Raspodela_po_proizvodjacima"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="Broj_pfizer_vakcina" type="{http://www.w3.org/2001/XMLSchema}positiveInteger"/&gt;
 *                   &lt;element name="Broj_sinopharm_vakcina" type="{http://www.w3.org/2001/XMLSchema}positiveInteger"/&gt;
 *                   &lt;element name="Broj_sputnik_vakcina" type="{http://www.w3.org/2001/XMLSchema}positiveInteger"/&gt;
 *                   &lt;element name="Broj_az_vakcina" type="{http://www.w3.org/2001/XMLSchema}positiveInteger"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="Datum"&gt;
 *           &lt;complexType&gt;
 *             &lt;simpleContent&gt;
 *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema&gt;date"&gt;
 *                 &lt;attribute name="property" use="required" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" fixed="pred:datum_zahtevan" /&gt;
 *               &lt;/extension&gt;
 *             &lt;/simpleContent&gt;
 *           &lt;/complexType&gt;
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
    "periodIzvestaja",
    "podnetihInteresovanjaImunizacije",
    "zahteviZaZeleniSertifikat",
    "izdatihZelenihSertifikata",
    "podaciODatimDozama",
    "raspodelaPoProizvodjacima",
    "datum"
})
@XmlRootElement(name = "Izvestaj", namespace = "http://tim.robot/izvestaj_o_imunizaciji")
public class Izvestaj {

    @XmlElement(name = "Period_izvestaja", namespace = "http://tim.robot/izvestaj_o_imunizaciji", required = true)
    protected Izvestaj.PeriodIzvestaja periodIzvestaja;
    @XmlElement(name = "Podnetih_interesovanja_imunizacije", namespace = "http://tim.robot/izvestaj_o_imunizaciji", required = true)
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger podnetihInteresovanjaImunizacije;
    @XmlElement(name = "Zahtevi_za_zeleni_sertifikat", namespace = "http://tim.robot/izvestaj_o_imunizaciji", required = true)
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger zahteviZaZeleniSertifikat;
    @XmlElement(name = "Izdatih_zelenih_sertifikata", namespace = "http://tim.robot/izvestaj_o_imunizaciji", required = true)
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger izdatihZelenihSertifikata;
    @XmlElement(name = "Podaci_o_datim_dozama", namespace = "http://tim.robot/izvestaj_o_imunizaciji", required = true)
    protected Izvestaj.PodaciODatimDozama podaciODatimDozama;
    @XmlElement(name = "Raspodela_po_proizvodjacima", namespace = "http://tim.robot/izvestaj_o_imunizaciji", required = true)
    protected Izvestaj.RaspodelaPoProizvodjacima raspodelaPoProizvodjacima;
    @XmlElement(name = "Datum", namespace = "http://tim.robot/izvestaj_o_imunizaciji", required = true)
    protected Izvestaj.Datum datum;

    /**
     * Gets the value of the periodIzvestaja property.
     * 
     * @return
     *     possible object is
     *     {@link Izvestaj.PeriodIzvestaja }
     *     
     */
    public Izvestaj.PeriodIzvestaja getPeriodIzvestaja() {
        return periodIzvestaja;
    }

    /**
     * Sets the value of the periodIzvestaja property.
     * 
     * @param value
     *     allowed object is
     *     {@link Izvestaj.PeriodIzvestaja }
     *     
     */
    public void setPeriodIzvestaja(Izvestaj.PeriodIzvestaja value) {
        this.periodIzvestaja = value;
    }

    /**
     * Gets the value of the podnetihInteresovanjaImunizacije property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getPodnetihInteresovanjaImunizacije() {
        return podnetihInteresovanjaImunizacije;
    }

    /**
     * Sets the value of the podnetihInteresovanjaImunizacije property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setPodnetihInteresovanjaImunizacije(BigInteger value) {
        this.podnetihInteresovanjaImunizacije = value;
    }

    /**
     * Gets the value of the zahteviZaZeleniSertifikat property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getZahteviZaZeleniSertifikat() {
        return zahteviZaZeleniSertifikat;
    }

    /**
     * Sets the value of the zahteviZaZeleniSertifikat property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setZahteviZaZeleniSertifikat(BigInteger value) {
        this.zahteviZaZeleniSertifikat = value;
    }

    /**
     * Gets the value of the izdatihZelenihSertifikata property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getIzdatihZelenihSertifikata() {
        return izdatihZelenihSertifikata;
    }

    /**
     * Sets the value of the izdatihZelenihSertifikata property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setIzdatihZelenihSertifikata(BigInteger value) {
        this.izdatihZelenihSertifikata = value;
    }

    /**
     * Gets the value of the podaciODatimDozama property.
     * 
     * @return
     *     possible object is
     *     {@link Izvestaj.PodaciODatimDozama }
     *     
     */
    public Izvestaj.PodaciODatimDozama getPodaciODatimDozama() {
        return podaciODatimDozama;
    }

    /**
     * Sets the value of the podaciODatimDozama property.
     * 
     * @param value
     *     allowed object is
     *     {@link Izvestaj.PodaciODatimDozama }
     *     
     */
    public void setPodaciODatimDozama(Izvestaj.PodaciODatimDozama value) {
        this.podaciODatimDozama = value;
    }

    /**
     * Gets the value of the raspodelaPoProizvodjacima property.
     * 
     * @return
     *     possible object is
     *     {@link Izvestaj.RaspodelaPoProizvodjacima }
     *     
     */
    public Izvestaj.RaspodelaPoProizvodjacima getRaspodelaPoProizvodjacima() {
        return raspodelaPoProizvodjacima;
    }

    /**
     * Sets the value of the raspodelaPoProizvodjacima property.
     * 
     * @param value
     *     allowed object is
     *     {@link Izvestaj.RaspodelaPoProizvodjacima }
     *     
     */
    public void setRaspodelaPoProizvodjacima(Izvestaj.RaspodelaPoProizvodjacima value) {
        this.raspodelaPoProizvodjacima = value;
    }

    /**
     * Gets the value of the datum property.
     * 
     * @return
     *     possible object is
     *     {@link Izvestaj.Datum }
     *     
     */
    public Izvestaj.Datum getDatum() {
        return datum;
    }

    /**
     * Sets the value of the datum property.
     * 
     * @param value
     *     allowed object is
     *     {@link Izvestaj.Datum }
     *     
     */
    public void setDatum(Izvestaj.Datum value) {
        this.datum = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;simpleContent&gt;
     *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema&gt;date"&gt;
     *       &lt;attribute name="property" use="required" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" fixed="pred:datum_zahtevan" /&gt;
     *     &lt;/extension&gt;
     *   &lt;/simpleContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "value"
    })
    public static class Datum {

        @XmlValue
        @XmlSchemaType(name = "date")
        protected XMLGregorianCalendar value;
        @XmlAttribute(name = "property", required = true)
        @XmlSchemaType(name = "anySimpleType")
        protected String property;

        /**
         * Gets the value of the value property.
         * 
         * @return
         *     possible object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public XMLGregorianCalendar getValue() {
            return value;
        }

        /**
         * Sets the value of the value property.
         * 
         * @param value
         *     allowed object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public void setValue(XMLGregorianCalendar value) {
            this.value = value;
        }

        /**
         * Gets the value of the property property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getProperty() {
            if (property == null) {
                return "pred:datum_zahtevan";
            } else {
                return property;
            }
        }

        /**
         * Sets the value of the property property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setProperty(String value) {
            this.property = value;
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
     *         &lt;element name="Datum_od"&gt;
     *           &lt;complexType&gt;
     *             &lt;simpleContent&gt;
     *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema&gt;date"&gt;
     *                 &lt;attribute name="property" use="required" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" fixed="pred:datum_od" /&gt;
     *               &lt;/extension&gt;
     *             &lt;/simpleContent&gt;
     *           &lt;/complexType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="Datum_do"&gt;
     *           &lt;complexType&gt;
     *             &lt;simpleContent&gt;
     *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema&gt;date"&gt;
     *                 &lt;attribute name="property" use="required" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" fixed="pred:datum_do" /&gt;
     *               &lt;/extension&gt;
     *             &lt;/simpleContent&gt;
     *           &lt;/complexType&gt;
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
        "datumOd",
        "datumDo"
    })
    public static class PeriodIzvestaja {

        @XmlElement(name = "Datum_od", namespace = "http://tim.robot/izvestaj_o_imunizaciji", required = true)
        protected Izvestaj.PeriodIzvestaja.DatumOd datumOd;
        @XmlElement(name = "Datum_do", namespace = "http://tim.robot/izvestaj_o_imunizaciji", required = true)
        protected Izvestaj.PeriodIzvestaja.DatumDo datumDo;

        /**
         * Gets the value of the datumOd property.
         * 
         * @return
         *     possible object is
         *     {@link Izvestaj.PeriodIzvestaja.DatumOd }
         *     
         */
        public Izvestaj.PeriodIzvestaja.DatumOd getDatumOd() {
            return datumOd;
        }

        /**
         * Sets the value of the datumOd property.
         * 
         * @param value
         *     allowed object is
         *     {@link Izvestaj.PeriodIzvestaja.DatumOd }
         *     
         */
        public void setDatumOd(Izvestaj.PeriodIzvestaja.DatumOd value) {
            this.datumOd = value;
        }

        /**
         * Gets the value of the datumDo property.
         * 
         * @return
         *     possible object is
         *     {@link Izvestaj.PeriodIzvestaja.DatumDo }
         *     
         */
        public Izvestaj.PeriodIzvestaja.DatumDo getDatumDo() {
            return datumDo;
        }

        /**
         * Sets the value of the datumDo property.
         * 
         * @param value
         *     allowed object is
         *     {@link Izvestaj.PeriodIzvestaja.DatumDo }
         *     
         */
        public void setDatumDo(Izvestaj.PeriodIzvestaja.DatumDo value) {
            this.datumDo = value;
        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType&gt;
         *   &lt;simpleContent&gt;
         *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema&gt;date"&gt;
         *       &lt;attribute name="property" use="required" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" fixed="pred:datum_do" /&gt;
         *     &lt;/extension&gt;
         *   &lt;/simpleContent&gt;
         * &lt;/complexType&gt;
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "value"
        })
        public static class DatumDo {

            @XmlValue
            @XmlSchemaType(name = "date")
            protected XMLGregorianCalendar value;
            @XmlAttribute(name = "property", required = true)
            @XmlSchemaType(name = "anySimpleType")
            protected String property;

            /**
             * Gets the value of the value property.
             * 
             * @return
             *     possible object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public XMLGregorianCalendar getValue() {
                return value;
            }

            /**
             * Sets the value of the value property.
             * 
             * @param value
             *     allowed object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public void setValue(XMLGregorianCalendar value) {
                this.value = value;
            }

            /**
             * Gets the value of the property property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getProperty() {
                if (property == null) {
                    return "pred:datum_do";
                } else {
                    return property;
                }
            }

            /**
             * Sets the value of the property property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setProperty(String value) {
                this.property = value;
            }

        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType&gt;
         *   &lt;simpleContent&gt;
         *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema&gt;date"&gt;
         *       &lt;attribute name="property" use="required" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" fixed="pred:datum_od" /&gt;
         *     &lt;/extension&gt;
         *   &lt;/simpleContent&gt;
         * &lt;/complexType&gt;
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "value"
        })
        public static class DatumOd {

            @XmlValue
            @XmlSchemaType(name = "date")
            protected XMLGregorianCalendar value;
            @XmlAttribute(name = "property", required = true)
            @XmlSchemaType(name = "anySimpleType")
            protected String property;

            /**
             * Gets the value of the value property.
             * 
             * @return
             *     possible object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public XMLGregorianCalendar getValue() {
                return value;
            }

            /**
             * Sets the value of the value property.
             * 
             * @param value
             *     allowed object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public void setValue(XMLGregorianCalendar value) {
                this.value = value;
            }

            /**
             * Gets the value of the property property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getProperty() {
                if (property == null) {
                    return "pred:datum_od";
                } else {
                    return property;
                }
            }

            /**
             * Sets the value of the property property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setProperty(String value) {
                this.property = value;
            }

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
     *         &lt;element name="Ukupno_doza" type="{http://www.w3.org/2001/XMLSchema}positiveInteger"/&gt;
     *         &lt;element name="Broj_date_prve_doze" type="{http://www.w3.org/2001/XMLSchema}positiveInteger"/&gt;
     *         &lt;element name="Broj_date_druge_doze" type="{http://www.w3.org/2001/XMLSchema}positiveInteger"/&gt;
     *         &lt;element name="Broj_date_trece_doze" type="{http://www.w3.org/2001/XMLSchema}positiveInteger"/&gt;
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
        "ukupnoDoza",
        "brojDatePrveDoze",
        "brojDateDrugeDoze",
        "brojDateTreceDoze"
    })
    public static class PodaciODatimDozama {

        @XmlElement(name = "Ukupno_doza", namespace = "http://tim.robot/izvestaj_o_imunizaciji", required = true)
        @XmlSchemaType(name = "positiveInteger")
        protected BigInteger ukupnoDoza;
        @XmlElement(name = "Broj_date_prve_doze", namespace = "http://tim.robot/izvestaj_o_imunizaciji", required = true)
        @XmlSchemaType(name = "positiveInteger")
        protected BigInteger brojDatePrveDoze;
        @XmlElement(name = "Broj_date_druge_doze", namespace = "http://tim.robot/izvestaj_o_imunizaciji", required = true)
        @XmlSchemaType(name = "positiveInteger")
        protected BigInteger brojDateDrugeDoze;
        @XmlElement(name = "Broj_date_trece_doze", namespace = "http://tim.robot/izvestaj_o_imunizaciji", required = true)
        @XmlSchemaType(name = "positiveInteger")
        protected BigInteger brojDateTreceDoze;

        /**
         * Gets the value of the ukupnoDoza property.
         * 
         * @return
         *     possible object is
         *     {@link BigInteger }
         *     
         */
        public BigInteger getUkupnoDoza() {
            return ukupnoDoza;
        }

        /**
         * Sets the value of the ukupnoDoza property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigInteger }
         *     
         */
        public void setUkupnoDoza(BigInteger value) {
            this.ukupnoDoza = value;
        }

        /**
         * Gets the value of the brojDatePrveDoze property.
         * 
         * @return
         *     possible object is
         *     {@link BigInteger }
         *     
         */
        public BigInteger getBrojDatePrveDoze() {
            return brojDatePrveDoze;
        }

        /**
         * Sets the value of the brojDatePrveDoze property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigInteger }
         *     
         */
        public void setBrojDatePrveDoze(BigInteger value) {
            this.brojDatePrveDoze = value;
        }

        /**
         * Gets the value of the brojDateDrugeDoze property.
         * 
         * @return
         *     possible object is
         *     {@link BigInteger }
         *     
         */
        public BigInteger getBrojDateDrugeDoze() {
            return brojDateDrugeDoze;
        }

        /**
         * Sets the value of the brojDateDrugeDoze property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigInteger }
         *     
         */
        public void setBrojDateDrugeDoze(BigInteger value) {
            this.brojDateDrugeDoze = value;
        }

        /**
         * Gets the value of the brojDateTreceDoze property.
         * 
         * @return
         *     possible object is
         *     {@link BigInteger }
         *     
         */
        public BigInteger getBrojDateTreceDoze() {
            return brojDateTreceDoze;
        }

        /**
         * Sets the value of the brojDateTreceDoze property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigInteger }
         *     
         */
        public void setBrojDateTreceDoze(BigInteger value) {
            this.brojDateTreceDoze = value;
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
     *         &lt;element name="Broj_pfizer_vakcina" type="{http://www.w3.org/2001/XMLSchema}positiveInteger"/&gt;
     *         &lt;element name="Broj_sinopharm_vakcina" type="{http://www.w3.org/2001/XMLSchema}positiveInteger"/&gt;
     *         &lt;element name="Broj_sputnik_vakcina" type="{http://www.w3.org/2001/XMLSchema}positiveInteger"/&gt;
     *         &lt;element name="Broj_az_vakcina" type="{http://www.w3.org/2001/XMLSchema}positiveInteger"/&gt;
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
        "brojPfizerVakcina",
        "brojSinopharmVakcina",
        "brojSputnikVakcina",
        "brojAzVakcina"
    })
    public static class RaspodelaPoProizvodjacima {

        @XmlElement(name = "Broj_pfizer_vakcina", namespace = "http://tim.robot/izvestaj_o_imunizaciji", required = true)
        @XmlSchemaType(name = "positiveInteger")
        protected BigInteger brojPfizerVakcina;
        @XmlElement(name = "Broj_sinopharm_vakcina", namespace = "http://tim.robot/izvestaj_o_imunizaciji", required = true)
        @XmlSchemaType(name = "positiveInteger")
        protected BigInteger brojSinopharmVakcina;
        @XmlElement(name = "Broj_sputnik_vakcina", namespace = "http://tim.robot/izvestaj_o_imunizaciji", required = true)
        @XmlSchemaType(name = "positiveInteger")
        protected BigInteger brojSputnikVakcina;
        @XmlElement(name = "Broj_az_vakcina", namespace = "http://tim.robot/izvestaj_o_imunizaciji", required = true)
        @XmlSchemaType(name = "positiveInteger")
        protected BigInteger brojAzVakcina;

        /**
         * Gets the value of the brojPfizerVakcina property.
         * 
         * @return
         *     possible object is
         *     {@link BigInteger }
         *     
         */
        public BigInteger getBrojPfizerVakcina() {
            return brojPfizerVakcina;
        }

        /**
         * Sets the value of the brojPfizerVakcina property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigInteger }
         *     
         */
        public void setBrojPfizerVakcina(BigInteger value) {
            this.brojPfizerVakcina = value;
        }

        /**
         * Gets the value of the brojSinopharmVakcina property.
         * 
         * @return
         *     possible object is
         *     {@link BigInteger }
         *     
         */
        public BigInteger getBrojSinopharmVakcina() {
            return brojSinopharmVakcina;
        }

        /**
         * Sets the value of the brojSinopharmVakcina property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigInteger }
         *     
         */
        public void setBrojSinopharmVakcina(BigInteger value) {
            this.brojSinopharmVakcina = value;
        }

        /**
         * Gets the value of the brojSputnikVakcina property.
         * 
         * @return
         *     possible object is
         *     {@link BigInteger }
         *     
         */
        public BigInteger getBrojSputnikVakcina() {
            return brojSputnikVakcina;
        }

        /**
         * Sets the value of the brojSputnikVakcina property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigInteger }
         *     
         */
        public void setBrojSputnikVakcina(BigInteger value) {
            this.brojSputnikVakcina = value;
        }

        /**
         * Gets the value of the brojAzVakcina property.
         * 
         * @return
         *     possible object is
         *     {@link BigInteger }
         *     
         */
        public BigInteger getBrojAzVakcina() {
            return brojAzVakcina;
        }

        /**
         * Sets the value of the brojAzVakcina property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigInteger }
         *     
         */
        public void setBrojAzVakcina(BigInteger value) {
            this.brojAzVakcina = value;
        }

    }

}
