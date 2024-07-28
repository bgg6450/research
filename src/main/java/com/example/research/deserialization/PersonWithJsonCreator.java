package com.example.research.deserialization;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PersonWithJsonCreator {
    private final String firstName;
    private final String lastName;
    private final int age;

    @JsonCreator
    public PersonWithJsonCreator(@JsonProperty("first_name") String firstName,
                                 @JsonProperty("last_name") String lastName,
                                 @JsonProperty("age") int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }
}
