package com.example.research.serialization;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PersonWithJsonProperty {
    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("last_name")
    private String lastName;
    private int age;

    public PersonWithJsonProperty(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public PersonWithJsonProperty() {
    }
}
