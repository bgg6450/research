package com.example.research.deserialization;

import lombok.Setter;

@Setter
public class PersonWithSetter {
    private String firstName;
    private String lastName;
    private int age;

    public PersonWithSetter(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public PersonWithSetter() {
    }
}
