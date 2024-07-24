package com.example.research.serialization;

import lombok.Getter;

@Getter
public class PersonWithGetter {
    private String firstName;
    private String lastName;
    private int age;

    public PersonWithGetter(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public PersonWithGetter() {
    }
}
