package com.usm.lab2.problem2;

import com.google.gson.Gson;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class JsonToObjectWithGson {
    public static void main(String[] args) {

        Gson gson = new Gson();

        try (Reader reader = new FileReader("Labs/src/main/java/com/usm/lab2/problem2/employee.json")) {

            // Convert JSON to Java Object
            Employee employee = gson.fromJson(reader, Employee.class);
            System.out.println(
                    "First Name: " + employee.getFirstName() + "\n" +
                    "Last Name: " + employee.getLastName() + "\n" +
                    "Age: " + employee.getAge() + "\n" +
                    "Position: " + employee.getPosition() + "\n" +
                    "Company: " + employee.getCompany()
            );

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
