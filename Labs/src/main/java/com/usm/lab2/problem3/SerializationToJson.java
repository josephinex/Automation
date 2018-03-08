package com.usm.lab2.problem3;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SerializationToJson {

    public static void main(String[] args) throws IOException {
        Map<String, Integer> marks = new HashMap<String, Integer>(){{
            put("Autocad", 8);
            put("SQL", 9);
            put("Cpp", 9);
            put("C", 10);
            put("Java", 10);
            put("VisualCpp", 7);
            put("Physics", 9);
            put("Maths", 7);
            put("Testing", 10);

        }};

        List<String> favouriteClasses = Arrays.asList("Java", "Sql", "PL/SQL", "Networks");

        String[] failedLabs = {"Prolog", "Networks"};

        Student student = new Student.Builder()
                .withLastName("Bob")
                .withFirstName("Jones")
                .withAge(21)
                .withMarks(marks)
                .withFavouriteClasses(favouriteClasses)
                .withFailedLabs(failedLabs)
                .build();

        ObjectMapper objectMapper = new ObjectMapper();

        objectMapper.writeValue(new File("Labs/src/main/java/com/usm/lab2/problem3/student.json"), student);
    }
}
