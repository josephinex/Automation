package com.usm.lab3;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "ValCurs")
public class ValCurs {

    @JacksonXmlProperty(localName="Valute")
    @JacksonXmlElementWrapper(useWrapping = false)
    private Valute[] currencies;

    @JacksonXmlProperty(isAttribute = true)
    private String Date;

    @JacksonXmlProperty(isAttribute = true)
    private String Name;

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Valute[] getCurrencies() {
        return currencies;
    }

    public void setCurrencies(Valute[] currencies) {
        this.currencies = currencies;
    }

}
