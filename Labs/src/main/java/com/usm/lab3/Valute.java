package com.usm.lab3;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;

class Valute {

    @JacksonXmlProperty
    private int NumCode;

    @JacksonXmlProperty
    private String CharCode;

    @JacksonXmlProperty
    private int Nominal;

    @JacksonXmlProperty
    private String Name;

    @JacksonXmlProperty
    private double Value;

    @JacksonXmlProperty(isAttribute = true)
    private int ID;

    /*Getters and Setters*/

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getNumCode() {
        return NumCode;
    }

    public void setNumCode(int numCode) {
        NumCode = numCode;
    }

    public String getCharCode() {
        return CharCode;
    }

    public void setCharCode(String charCode) {
        CharCode = charCode;
    }

    public int getNominal() {
        return Nominal;
    }

    public void setNominal(int nominal) {
        Nominal = nominal;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public double getValue() {
        return Value;
    }

    public void setValue(double value) {
        Value = value;
    }
}
