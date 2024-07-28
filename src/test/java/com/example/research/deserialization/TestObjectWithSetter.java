package com.example.research.deserialization;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * <strong>알 수 있는 점</strong>
 * <p>1. setter는 기본적으로 camel case만 매핑한다.</p>
 * <p>2. @JsonCreator가 없는 경우 반드시 기본 생성자가 필요하다.</p>
 */
class TestObjectWithSetter {
    //
    @Test
    @DisplayName("setter만 선언된 객체에 대한 역직렬화 테스트: snake case")
    void given_jsonSnakeCase_when_deserialize_then_success() throws JsonProcessingException, NoSuchFieldException, IllegalAccessException {
        String jsonSnakeCase = "{\"first_name\":\"geon\",\"last_name\":\"baek\",\"age\":33}";

        PersonWithSetter personWithSetter = deserialize(jsonSnakeCase);
        String firstName = getFirstNameWithReflection(personWithSetter);
        String lastName = getLastNameWithReflection(personWithSetter);
        int age = getAgeWithReflection(personWithSetter);

        assertThat(firstName).isNull();
        assertThat(lastName).isNull();
        assertThat(age).isEqualTo(33);
    }

    @Test
    @DisplayName("setter만 선언된 객체에 대한 역직렬화 테스트: camel case")
    void given_jsonCamelCase_when_deserialize_then_fail() throws JsonProcessingException, NoSuchFieldException, IllegalAccessException {
        String jsonCamelCase = "{\"firstName\":\"geon\",\"lastName\":\"baek\",\"age\":33}";

        PersonWithSetter personWithSetter = deserialize(jsonCamelCase);
        String firstName = getFirstNameWithReflection(personWithSetter);
        String lastName = getLastNameWithReflection(personWithSetter);
        int age = getAgeWithReflection(personWithSetter);

        assertThat(firstName).isEqualTo("geon");
        assertThat(lastName).isEqualTo("baek");
        assertThat(age).isEqualTo(33);
    }

    private static int getAgeWithReflection(PersonWithSetter personWithSetter) throws NoSuchFieldException, IllegalAccessException {
        Field ageField = PersonWithSetter.class.getDeclaredField("age");
        ageField.setAccessible(true);
        return (int) ageField.get(personWithSetter);
    }

    private static String getLastNameWithReflection(PersonWithSetter personWithSetter) throws NoSuchFieldException, IllegalAccessException {
        Field lastNameField = PersonWithSetter.class.getDeclaredField("lastName");
        lastNameField.setAccessible(true);
        return (String) lastNameField.get(personWithSetter);
    }

    private static String getFirstNameWithReflection(PersonWithSetter personWithSetter) throws NoSuchFieldException, IllegalAccessException {
        Field firstNameField = PersonWithSetter.class.getDeclaredField("firstName");
        firstNameField.setAccessible(true);
        return (String) firstNameField.get(personWithSetter);
    }

    private static PersonWithSetter deserialize(String jsonSnakeCase) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(FAIL_ON_UNKNOWN_PROPERTIES, false);
        return mapper.readValue(jsonSnakeCase, PersonWithSetter.class);
    }
}
