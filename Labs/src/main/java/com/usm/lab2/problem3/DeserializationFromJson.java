package com.usm.lab2.problem3;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class DeserializationFromJson {

    public static void main(String[] args) throws IOException {

        ObjectMapper mapper = new ObjectMapper();

        Student student = mapper.readValue(new File("Labs/src/main/java/com/usm/lab2/problem3/student.json"), Student.class);
        System.out.println(student.toString());
    }
}
