package com.usm.lab2.problem1;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;

public class CreateXmlWithStax {

    public static void main(String[] args) {

        StringWriter stringWriter = new StringWriter();

        try {
            XMLOutputFactory xmlOutputFactory = XMLOutputFactory.newInstance();
            XMLStreamWriter xmlStreamWriter = xmlOutputFactory
                    .createXMLStreamWriter(new FileWriter("Labs/src/main/java/com/usm/lab2/problem1/empolyees_stax.xml"));


            xmlStreamWriter.writeStartDocument();
            xmlStreamWriter.writeStartElement("employee");

            xmlStreamWriter.writeStartElement("manager");
            xmlStreamWriter.writeAttribute("level", "middle");

            xmlStreamWriter.writeStartElement("company");
            xmlStreamWriter.writeAttribute("id", "5");
            xmlStreamWriter.writeCharacters("Google");
            xmlStreamWriter.writeEndElement();
            xmlStreamWriter.writeEndElement();

            xmlStreamWriter.writeStartElement("hr");
            xmlStreamWriter.writeAttribute("level", "entry");
            xmlStreamWriter.writeStartElement("company");
            xmlStreamWriter.writeAttribute("id", "1");
            xmlStreamWriter.writeCharacters("Yahoo");
            xmlStreamWriter.writeEndElement();
            xmlStreamWriter.writeEndElement();

            xmlStreamWriter.writeEndElement();
            xmlStreamWriter.writeEndDocument();

            xmlStreamWriter.flush();
            xmlStreamWriter.close();

            String xmlString = stringWriter.getBuffer().toString();

            stringWriter.close();

            System.out.println(xmlString);

        } catch (XMLStreamException | IOException e) {
            e.printStackTrace();
        }
    }
}
