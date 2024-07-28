package com.example.research.deserialization;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;
import static com.fasterxml.jackson.databind.SerializationFeature.FAIL_ON_EMPTY_BEANS;
import static org.assertj.core.api.Assertions.assertThat;

class TestPureObject {
    @Test
    @DisplayName("getter 없이 jsonCreator만 선언된 객체에 대한 역직렬화 테스트: mapping case 일치")
    void given_jsonSnakeCase_when_deserialize_then_success() throws JsonProcessingException, NoSuchFieldException, IllegalAccessException {
        String jsonSnakeCase = "{\"first_name\":\"geon\",\"last_name\":\"baek\",\"age\":33}";

        // getter가 선
        PersonWithJsonCreator personWithJsonCreator = deserialize(jsonSnakeCase);
        String firstName = getFirstNameWithReflection(personWithJsonCreator);
        String lastName = getLastNameWithReflection(personWithJsonCreator);
        int age = getAgeWithReflection(personWithJsonCreator);

        assertThat(firstName).isEqualTo("geon");
        assertThat(lastName).isEqualTo("baek");
        assertThat(age).isEqualTo(33);
    }

    @Test
    @DisplayName("getter 없이 jsonCreator만 선언된 객체에 대한 역직렬화 테스트: mapping case 불일치")
    void given_jsonCamelCase_when_deserialize_then_fail() throws JsonProcessingException, NoSuchFieldException, IllegalAccessException {
        String jsonCamelCase = "{\"firstName\":\"geon\",\"lastName\":\"baek\",\"age\":33}";

        PersonWithJsonCreator personWithJsonCreator = deserialize(jsonCamelCase);
        String firstName = getFirstNameWithReflection(personWithJsonCreator);
        String lastName = getLastNameWithReflection(personWithJsonCreator);
        int age = getAgeWithReflection(personWithJsonCreator);

        assertThat(firstName).isNull();
        assertThat(lastName).isNull();
        assertThat(age).isEqualTo(33);
    }

    private static int getAgeWithReflection(PersonWithJsonCreator personWithJsonCreator) throws NoSuchFieldException, IllegalAccessException {
        Field ageField = PersonWithJsonCreator.class.getDeclaredField("age");
        ageField.setAccessible(true);
        return (int) ageField.get(personWithJsonCreator);
    }

    private static String getLastNameWithReflection(PersonWithJsonCreator personWithJsonCreator) throws NoSuchFieldException, IllegalAccessException {
        Field lastNameField = PersonWithJsonCreator.class.getDeclaredField("lastName");
        lastNameField.setAccessible(true);
        return (String) lastNameField.get(personWithJsonCreator);
    }

    private static String getFirstNameWithReflection(PersonWithJsonCreator personWithJsonCreator) throws NoSuchFieldException, IllegalAccessException {
        Field firstNameField = PersonWithJsonCreator.class.getDeclaredField("firstName");
        firstNameField.setAccessible(true);
        return (String) firstNameField.get(personWithJsonCreator);
    }

    private static PersonWithJsonCreator deserialize(String jsonSnakeCase) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(FAIL_ON_UNKNOWN_PROPERTIES, false);
        return mapper.readValue(jsonSnakeCase, PersonWithJsonCreator.class);
    }
}
