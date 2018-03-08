package com.usm.lab2.problem3;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SerializationToXml {

    public static void main(String[] args) throws IOException {

        Map<String, Integer> marks = new HashMap<String, Integer>(){{
           put("Autocad", 8);
           put("SQL", 9);
           put("Cpp", 9);
           put("C", 10);
           put("Java", 10);
           put("VisualCpp", 8);
           put("Physics", 8);
           put("Maths", 7);
           put("Testing", 7);

        }};

        List<String> favouriteClasses = Arrays.asList("Java", "Sql", "PL/SQL", "Networks");

        String[] failedLabs = {"Prolog", "Networks"};

        Student student = new Student.Builder()
                .withLastName("Pascal")
                .withFirstName("Tatiana")
                .withAge(30)
                .withMarks(marks)
                .withFavouriteClasses(favouriteClasses)
                .withFailedLabs(failedLabs)
                .build();

        XmlMapper xmlMapper = new XmlMapper();

        xmlMapper.writeValue(new File("Labs/src/main/java/com/usm/lab2/problem3/student.xml"), student);

    }
}
