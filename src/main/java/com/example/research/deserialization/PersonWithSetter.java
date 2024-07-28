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

    // 중요! @JsonCreator를 쓰지 않을 경우 jackson은 먼저 기본생성자를 통해 빈 객체를 생성하므로 반드시 기본 생성자를 만들어줘야 한다.
    public PersonWithSetter() {
    }
}
