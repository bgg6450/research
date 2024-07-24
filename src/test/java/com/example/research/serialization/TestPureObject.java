package com.example.research.serialization;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.InvalidDefinitionException;
import org.junit.jupiter.api.Test;

import static com.fasterxml.jackson.databind.SerializationFeature.FAIL_ON_EMPTY_BEANS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * <p>getter 없이 상태만 가지고 있는 순수 object</p>
 * <p>필드를 인식할 방법이 없기 때문에 직렬화가 불가하다</p>
 */
class TestPureObject {

    private final static String SERIALIZED_EMPTY_OBJECT = "{}";

    @Test
    void given_pureObject_when_serializeWithFailOnEmptyBeansFalse_then_success() throws JsonProcessingException {
        Person person = new Person("geon", "baek", 33);
        Person emptyPerson = new Person();

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(FAIL_ON_EMPTY_BEANS, false);
        String result_1 = mapper.writeValueAsString(person);
        String result_2 = mapper.writeValueAsString(emptyPerson);
        String result_3 = mapper.writeValueAsString(null);

        assertThat(result_1).isEqualTo(SERIALIZED_EMPTY_OBJECT);
        assertThat(result_2).isEqualTo(SERIALIZED_EMPTY_OBJECT);
        assertThat(result_3).isEqualTo("null"); // null 문자열 반환
    }

    @Test
    void given_pureObject_when_serializeWithFailOnEmptyBeansTrue_then_throwsException() {
        Person person = new Person("geon", "baek", 33);
        Person emptyPerson = new Person();

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(FAIL_ON_EMPTY_BEANS, true);

        assertThatThrownBy(() -> mapper.writeValueAsString(person))
                .isInstanceOf(InvalidDefinitionException.class);
        assertThatThrownBy(() -> mapper.writeValueAsString(emptyPerson))
                .isInstanceOf(InvalidDefinitionException.class);
    }
}