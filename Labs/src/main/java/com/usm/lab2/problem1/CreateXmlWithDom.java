package com.usm.lab2.problem1;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class CreateXmlWithDom {

    public static void main(String[] args) {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();

            //root element
            Element root = document.createElement("employee");
            document.appendChild(root);

            //managers layer
            Element managers = document.createElement("manager");
            root.appendChild(managers);

            Attr attr = document.createAttribute("level");
            attr.setValue("middle");
            managers.setAttributeNode(attr);

            //company name element
            Element companyName = document.createElement("company");
            companyName.appendChild(document.createTextNode("Google"));
            Attr comId = document.createAttribute("id");
            comId.setValue("5");
            companyName.setAttributeNode(comId);
            managers.appendChild(companyName);

            //floor element
            Element floor = document.createElement("floor");
            floor.appendChild(document.createTextNode("7"));
            managers.appendChild(floor);

            //HR layer
            Element hr = document.createElement("hr");
            root.appendChild(hr);

            Attr attr1 = document.createAttribute("level");
            attr1.setValue("entry");
            hr.setAttributeNode(attr1);

            //company name element
            Element companyName1 = document.createElement("company");
            companyName1.appendChild(document.createTextNode("Yahoo"));
            Attr companyId = document.createAttribute("id");
            companyId.setValue("1");
            companyName1.setAttributeNode(companyId);
            hr.appendChild(companyName1);

            //floor element
            Element floor1 = document.createElement("floor");
            floor1.appendChild(document.createTextNode("2"));
            hr.appendChild(floor1);

            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new File("Labs/src/main/java/com/usm/lab2/problem1/employees.xml"));
            transformer.transform(source, result);

        } catch (ParserConfigurationException | TransformerException e) {
            e.printStackTrace();
        }
    }
}
