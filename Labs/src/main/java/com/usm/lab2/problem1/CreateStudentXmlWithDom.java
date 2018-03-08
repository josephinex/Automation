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

public class CreateStudentXmlWithDom {

    public static void main(String[] args) {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();

        DocumentBuilder documentBuilder = null;

        try {
            documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();

            //root element
            Element root = document.createElement("students");
            document.appendChild(root);

            //student 1
            Element student = document.createElement("student");
            root.appendChild(student);

            Attr attr = document.createAttribute("id");
            attr.setValue("1");
            student.setAttributeNode(attr);

            Element firstName = document.createElement("firstName");
            firstName.appendChild(document.createTextNode("John"));
            student.appendChild(firstName);

            Element lastName = document.createElement("lastName");
            lastName.appendChild(document.createTextNode("Smith"));
            student.appendChild(lastName);

            Element age = document.createElement("age");
            age.appendChild(document.createTextNode("21"));
            student.appendChild(age);

            Element yearOfStudy = document.createElement("yearOfStudy");
            yearOfStudy.appendChild(document.createTextNode("3"));
            student.appendChild(yearOfStudy);


            //student 2
            Element student2 = document.createElement("student");
            root.appendChild(student2);

            Attr attr1 = document.createAttribute("id");
            attr1.setValue("2");
            student2.setAttributeNode(attr1);

            Element firstName1 = document.createElement("firstName");
            firstName1.appendChild(document.createTextNode("Bob"));
            student2.appendChild(firstName1);

            Element lastName1 = document.createElement("lastName");
            lastName1.appendChild(document.createTextNode("Jones"));
            student2.appendChild(lastName1);

            Element age1 = document.createElement("age");
            age1.appendChild(document.createTextNode("22"));
            student2.appendChild(age1);

            Element yearOfStudy1 = document.createElement("yearOfStudy");
            yearOfStudy1.appendChild(document.createTextNode("4"));
            student2.appendChild(yearOfStudy1);

            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new File("Labs/src/main/java/com/usm/lab2/problem1/students.xml"));
            transformer.transform(source, result);

        } catch (ParserConfigurationException | TransformerException e) {
            e.printStackTrace();
        }

    }
}
