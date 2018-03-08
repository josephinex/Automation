package com.usm.lab2.problem1;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.FileWriter;
import java.io.IOException;

public class CreateStudentXmlWithStax {
    public static void main(String[] args) {

        try {

            XMLOutputFactory xmlOutputFactory = XMLOutputFactory.newInstance();
            XMLStreamWriter xmlStreamWriter = xmlOutputFactory
                    .createXMLStreamWriter(new FileWriter("Labs/src/main/java/com/usm/lab2/problem1/students_stax.xml"));

            xmlStreamWriter.writeStartDocument();
            xmlStreamWriter.writeStartElement("students");

            xmlStreamWriter.writeStartElement("student");
            xmlStreamWriter.writeAttribute("id", "1");

            xmlStreamWriter.writeStartElement("firstName");
            xmlStreamWriter.writeCharacters("John");
            xmlStreamWriter.writeEndElement();

            xmlStreamWriter.writeStartElement("lastName");
            xmlStreamWriter.writeCharacters("Smith");
            xmlStreamWriter.writeEndElement();

            xmlStreamWriter.writeStartElement("Age");
            xmlStreamWriter.writeCharacters("21");
            xmlStreamWriter.writeEndElement();

            xmlStreamWriter.writeStartElement("yearOfStudy");
            xmlStreamWriter.writeCharacters("3");
            xmlStreamWriter.writeEndElement();

            xmlStreamWriter.writeEndElement();

            xmlStreamWriter.writeStartElement("student");
            xmlStreamWriter.writeAttribute("id", "2");

            xmlStreamWriter.writeStartElement("firstName");
            xmlStreamWriter.writeCharacters("Bob");
            xmlStreamWriter.writeEndElement();

            xmlStreamWriter.writeStartElement("lastName");
            xmlStreamWriter.writeCharacters("Jones");
            xmlStreamWriter.writeEndElement();

            xmlStreamWriter.writeStartElement("Age");
            xmlStreamWriter.writeCharacters("22");
            xmlStreamWriter.writeEndElement();

            xmlStreamWriter.writeStartElement("yearOfStudy");
            xmlStreamWriter.writeCharacters("4");
            xmlStreamWriter.writeEndElement();

            xmlStreamWriter.writeEndElement();

            xmlStreamWriter.writeEndElement();
            xmlStreamWriter.writeEndDocument();

            xmlStreamWriter.flush();
            xmlStreamWriter.close();

        } catch (XMLStreamException | IOException e) {
            e.printStackTrace();
        }
    }
}
