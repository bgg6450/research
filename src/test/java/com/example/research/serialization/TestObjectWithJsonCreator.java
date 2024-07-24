package com.example.research.serialization;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.InvalidDefinitionException;
import org.junit.jupiter.api.Test;

import static com.fasterxml.jackson.databind.SerializationFeature.FAIL_ON_EMPTY_BEANS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * <p>getter와 jsonCreator 가지고 있는 object</p>
 */
class TestObjectWithJsonCreator {

    @Test
    void given_objectWithJsonProperty_when_serializeWithFailOnEmptyBeansFalse_then_success() throws JsonProcessingException {
        PersonWithJsonProperty person = new PersonWithJsonProperty("geon", "baek", 33);
        PersonWithJsonProperty emptyPerson = new PersonWithJsonProperty();

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(FAIL_ON_EMPTY_BEANS, false);
        String result_1 = mapper.writeValueAsString(person);
        String result_2 = mapper.writeValueAsString(emptyPerson);

        // 중요!! getter가 없는 age 필드는 초기화가 되지 않았으므로 역직렬화 대상에서 제외됨!
        assertThat(result_1).isEqualTo("{\"first_name\":\"geon\",\"last_name\":\"baek\"}");
        assertThat(result_2).isEqualTo("{\"first_name\":null,\"last_name\":null}");
    }

    @Test
    void given_objectWithJsonProperty_when_serializeWithFailOnEmptyBeansTrue_then_success() throws JsonProcessingException {
        PersonWithJsonProperty person = new PersonWithJsonProperty("geon", "baek", 33);
        PersonWithJsonProperty emptyPerson = new PersonWithJsonProperty();

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(FAIL_ON_EMPTY_BEANS, true);
        String result_1 = mapper.writeValueAsString(person);
        String result_2 = mapper.writeValueAsString(emptyPerson);

        assertThat(result_1).isEqualTo("{\"first_name\":\"geon\",\"last_name\":\"baek\"}");
        assertThat(result_2).isEqualTo("{\"first_name\":null,\"last_name\":null}");
    }
}