package main.java.com.usm.lab2.problem1;

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

public class DomParser {

    public static void main(String[] args) {

        File input = new File("Labs/src/main/java/com/usm/lab2/problem1/books.xml");
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


            NodeList nodeList = document.getElementsByTagName("BOOK");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i); //looping through books

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    System.out.println(node.getNodeName() + " " + i);
                    Element element = (Element) node;
                    System.out.println("Book Genre: " + element.getAttribute("genre"));

                    NodeList titleElems = element.getElementsByTagName("TITLE");
                    NodeList authorElems = element.getElementsByTagName("AUTHOR");
                    NodeList countryElems = element.getElementsByTagName("COUNTRY");
                    NodeList priceElems = element.getElementsByTagName("PRICE");
                    NodeList yearElems = element.getElementsByTagName("YEAR");

                    System.out.println("Element name: " + titleElems.item(0).getNodeName() +
                            " : " + titleElems.item(0).getTextContent());
                    System.out.println("Element name: " + authorElems.item(0).getNodeName() +
                            " : " + authorElems.item(0).getTextContent());
                    System.out.println("Element name: " + countryElems.item(0).getNodeName() +
                            " : " + countryElems.item(0).getTextContent());
                    System.out.println("Element name: " + priceElems.item(0).getNodeName() +
                            " : " + priceElems.item(0).getTextContent());

                    if (priceElems.item(0).getAttributes().item(0) != null) {
                        System.out.println(priceElems.item(0).getAttributes().item(0));
                    }

                    System.out.println("Element name: " + yearElems.item(0).getNodeName() +
                            " : " + yearElems.item(0).getTextContent());
                }
            }
        }
    }
}
