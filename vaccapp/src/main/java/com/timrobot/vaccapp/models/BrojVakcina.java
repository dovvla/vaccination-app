//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.2 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2022.07.10 at 12:07:04 AM CEST 
//


package com.timrobot.vaccapp.models;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


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
 *         &lt;element name="Broj" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="Vakcina" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
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
    "vakcina"
})
@XmlRootElement(name = "Broj_vakcina", namespace = "http://tim.robot/broj-vakcina")
public class BrojVakcina {

    @XmlElement(name = "Broj", namespace = "http://tim.robot/broj-vakcina")
    protected int broj;
    @XmlElement(name = "Vakcina", namespace = "http://tim.robot/broj-vakcina", required = true)
    protected String vakcina;

    /**
     * Gets the value of the broj property.
     * 
     */
    public int getBroj() {
        return broj;
    }

    /**
     * Sets the value of the broj property.
     * 
     */
    public void setBroj(int value) {
        this.broj = value;
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
