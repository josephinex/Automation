package com.usm.lab2.problem1;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

public class SaxStudentParser {
    public static void main(String[] args) {
        File input = new File("Labs/src/main/java/com/usm/lab2/problem1/students.xml");
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();

        try {
            SAXParser saxParser = saxParserFactory.newSAXParser();
            StudentHandler userHandler = new StudentHandler();
            saxParser.parse(input, userHandler);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }
}
