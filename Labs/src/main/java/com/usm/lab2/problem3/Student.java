package com.usm.lab2.problem3;

import java.util.*;

public class Student {

    private String firstName;

    private String lastName;

    private int age;

    private List<String> favoriteClasses;

    private Map<String, Integer> marks;

    private String[] failedLabs;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public List<String> getFavoriteClasses() {
        return favoriteClasses;
    }

    public Map<String, Integer> getMarks() {
        return marks;
    }

    public String[] getFailedLabs() {
        return failedLabs;
    }

    public Student(){
        //default constructor
    }

    private Student(Builder builder) {
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.age = builder.age;
        this.favoriteClasses = builder.favoriteClasses;
        this.marks = builder.marks;
        this.failedLabs = builder.failedLabs;
    }

    @Override
    public String toString() {
        return "Student{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", favoriteClasses=" + favoriteClasses +
                ", marks=" + marks +
                ", failedLabs=" + Arrays.toString(failedLabs) +
                '}';
    }

    public static class Builder{

        private String firstName;

        private String lastName;

        private int age;

        private List<String> favoriteClasses;

        private Map<String, Integer> marks;

        private String[] failedLabs;

        public Builder withFirstName(String firstName){
            this.firstName = firstName;
            return this;
        }

        public Builder withLastName(String lastName){
            this.lastName = lastName;
            return this;
        }

        public Builder withAge(int age){
            this.age = age;
            return this;
        }

        public Builder withFavouriteClasses(List<String> favoriteClasses){
            this.favoriteClasses = new ArrayList<>(favoriteClasses);
            return this;
        }

        public Builder withMarks(Map<String, Integer> marks){
            this.marks = new HashMap<String, Integer>(marks);
            return this;
        }

        public Builder withFailedLabs(String[] failedLabs){
            this.failedLabs = failedLabs;
            return this;
        }

       public Student build(){
            return new Student(this);
       }

    }
}
