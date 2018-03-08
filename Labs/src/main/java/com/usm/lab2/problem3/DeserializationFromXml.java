package com.usm.lab2.problem3;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.*;

public class DeserializationFromXml {

    public static void main(String[] args) throws IOException {

        File file = new File("Labs/src/main/java/com/usm/lab2/problem3/student.xml");

        XmlMapper xmlMapper = new XmlMapper();

        String xml = inputStreamToString(new FileInputStream(file));

        Student student = xmlMapper.readValue(xml, Student.class);

        System.out.println(student.toString());
    }

    private static String inputStreamToString(FileInputStream fileInputStream) throws IOException {
        StringBuilder sb = new StringBuilder();

        String line;
        BufferedReader br = new BufferedReader(new InputStreamReader(fileInputStream));

        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        br.close();

        return sb.toString();
    }
}
