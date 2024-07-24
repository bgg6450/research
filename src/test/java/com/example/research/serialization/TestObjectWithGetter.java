package com.example.research.serialization;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static com.fasterxml.jackson.databind.SerializationFeature.FAIL_ON_EMPTY_BEANS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * <p>getter만 가지고 있는 object</p>
 */
class TestObjectWithGetter {

    @Test
    void given_objectContainsGetter_when_serializeWithFailOnEmptyBeansFalse_then_success() throws JsonProcessingException {
        PersonWithGetter person = new PersonWithGetter("geon", "baek", 33);
        PersonWithGetter emptyPerson = new PersonWithGetter();

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(FAIL_ON_EMPTY_BEANS, false);
        String result_1 = mapper.writeValueAsString(person);
        String result_2 = mapper.writeValueAsString(emptyPerson);

        assertThat(result_1).isEqualTo("{\"firstName\":\"geon\",\"lastName\":\"baek\",\"age\":33}");
        assertThat(result_2).isEqualTo("{\"firstName\":null,\"lastName\":null,\"age\":0}");
    }

    @Test
    void given_objectContainsGetter_when_serializeWithFailOnEmptyBeansTrue_then_success() throws JsonProcessingException {
        PersonWithGetter person = new PersonWithGetter("geon", "baek", 33);
        PersonWithGetter emptyPerson = new PersonWithGetter();

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(FAIL_ON_EMPTY_BEANS, true);
        String result_1 = mapper.writeValueAsString(person);
        String result_2 = mapper.writeValueAsString(emptyPerson);

        assertThat(result_1).isEqualTo("{\"firstName\":\"geon\",\"lastName\":\"baek\",\"age\":33}");

        // 중요!! getter가 있기 때문에 빈 값이 아닌 디폴트 값으로 초기화 된 것으로 간주!! 따라서 예외 처리되지 않는다.
        assertThat(result_2).isEqualTo("{\"firstName\":null,\"lastName\":null,\"age\":0}");
    }
}