package com.usm.lab2.problem1;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

class UserHandler extends DefaultHandler {

    private boolean title;

    private boolean author;

    private boolean country;

    private boolean price;

    private boolean year;

    @Override
    public void startElement(String uri, String localName,
                             String qName, Attributes attributes) throws SAXException{
        if(qName.equalsIgnoreCase("book")){
            String genre = attributes.getValue("genre");
            System.out.println("Book genre is " + genre);
        }else if(qName.equalsIgnoreCase("title")){
            title = true;
        }else if(qName.equalsIgnoreCase("author")){
            author = true;
        }else if(qName.equalsIgnoreCase("country")){
            country = true;
        }else if(qName.equalsIgnoreCase("price")){
            price = true;
        }else if(qName.equalsIgnoreCase("year")){
            year = true;
        }
    };

    @Override
    public void endElement(String uri,
                           String localName, String qName) throws SAXException {

        if (qName.equalsIgnoreCase("book")) {
            System.out.println("End Element :" + qName);
        }
    }

    @Override
    public void characters(char ch[], int start, int length) throws SAXException {
        if (title) {
            System.out.println("Title: " + new String(ch, start, length));
            title = false;
        } else if (author) {
            System.out.println("Author: " + new String(ch, start, length));
            author = false;
        } else if (country) {
            System.out.println("Country: " + new String(ch, start, length));
            country = false;
        } else if (price) {
            System.out.println("Price: " + new String(ch, start, length));
            price = false;
        }else if(year){
            System.out.println("Year: " + new String(ch, start, length));
            year = false;
        }
    }
}
