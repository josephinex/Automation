package com.usm.lab2.problem1;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class StudentDomParser {
    public static void main(String[] args) {

        File input = new File("Labs/src/main/java/com/usm/lab2/problem1/students.xml");
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();

        DocumentBuilder documentBuilder = null;
        Document document = null;

        try {
            documentBuilder = documentBuilderFactory.newDocumentBuilder();
            document = documentBuilder.parse(input);
        } catch (IOException | SAXException | ParserConfigurationException e) {
            e.printStackTrace();
        }

        if (document != null) {
            document.getDocumentElement().normalize();

            NodeList nodeList = document.getElementsByTagName("student");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i); //looping through students

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    System.out.println("\nCurrent Element :" + element.getNodeName());
                    System.out.println("Student id: " + element.getAttribute("id"));

                    NodeList firstNames = element.getElementsByTagName("firstName");
                    NodeList lastNames = element.getElementsByTagName("lastName");
                    NodeList ages = element.getElementsByTagName("age");
                    NodeList yearOfStudy = element.getElementsByTagName("yearOfStudy");

                    System.out.println("Element name: " + firstNames.item(0).getNodeName() +
                            " : " + firstNames.item(0).getTextContent());
                    System.out.println("Element name: " + lastNames.item(0).getNodeName() +
                            " : " + lastNames.item(0).getTextContent());
                    System.out.println("Element name: " + ages.item(0).getNodeName() +
                            " : " + ages.item(0).getTextContent());
                    System.out.println("Element name: " + yearOfStudy.item(0).getNodeName() +
                            " : " + yearOfStudy.item(0).getTextContent());
                }
            }
        }
    }
}
