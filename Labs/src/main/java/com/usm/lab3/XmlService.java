package com.usm.lab3;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

public class XmlService {

    @GET
    @Path("/get")
    @Produces(MediaType.APPLICATION_XML)
    public Valute getCurrencyRates(){
        Valute currencyRate = new Valute();

        return currencyRate;
    }
}
