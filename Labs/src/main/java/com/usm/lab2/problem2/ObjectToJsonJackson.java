package com.usm.lab2.problem2;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class ObjectToJsonJackson {

    public static void main(String[] args) {
        ObjectToJsonJackson obj = new ObjectToJsonJackson();
        obj.convertObject();
    }

    private void convertObject() {
        ObjectMapper mapper = new ObjectMapper();

       Employee employee = createEmployee();

        try {
            // Convert object to JSON string and save into a file directly
            mapper.writeValue(new File("Labs/src/main/java/com/usm/lab2/problem2/employee.json"), employee);

            // Convert object to JSON string and pretty print
            String jsonInString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(employee);
            System.out.println(jsonInString);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Employee createEmployee() {

        Employee employee = new Employee();
        employee.setAge(30);
        employee.setCompany("Google");
        employee.setFirstName("John");
        employee.setLastName("Smith");
        employee.setPosition("Developer");

        return employee;
    }

}
