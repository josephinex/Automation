package com.usm.lab2.problem1;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class StudentHandler extends DefaultHandler{
    private boolean firstName;

    private boolean lastName;

    private boolean age;

    private boolean yearOfStudy;

    @Override
    public void startElement(String uri, String localName,
                             String qName, Attributes attributes) throws SAXException {
        if(qName.equalsIgnoreCase("student")){
            String id = attributes.getValue("id");
            System.out.println("Id is " + id);
        }else if(qName.equalsIgnoreCase("firstName")){
            firstName = true;
        }else if(qName.equalsIgnoreCase("lastName")){
            lastName = true;
        }else if(qName.equalsIgnoreCase("age")){
            age = true;
        }else if(qName.equalsIgnoreCase("yearOfStudy")){
            yearOfStudy = true;
        }
    };

    @Override
    public void endElement(String uri,
                           String localName, String qName) throws SAXException {

        if (qName.equalsIgnoreCase("student")) {
            System.out.println("End Element :" + qName);
        }
    }

    @Override
    public void characters(char ch[], int start, int length) throws SAXException {
        if (firstName) {
            System.out.println("First name: " + new String(ch, start, length));
            firstName = false;
        } else if (lastName) {
            System.out.println("Last name: " + new String(ch, start, length));
            lastName = false;
        } else if (age) {
            System.out.println("Age: " + new String(ch, start, length));
            age = false;
        } else if (yearOfStudy) {
            System.out.println("Year of Study: " + new String(ch, start, length));
            yearOfStudy = false;
        }
    }
}
