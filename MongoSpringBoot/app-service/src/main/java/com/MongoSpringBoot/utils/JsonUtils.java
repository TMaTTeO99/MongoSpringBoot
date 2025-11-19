package com.MongoSpringBoot.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class JsonUtils {

    private JsonUtils() {
        // private constructor
    }

    public static String convertPayload(Object data) {
        String kafkaEventTypeHeaderValue;

        if (data instanceof String) {
            kafkaEventTypeHeaderValue = (String) data;
        } else if (data instanceof byte[]) {
            kafkaEventTypeHeaderValue = new String((byte[]) data, StandardCharsets.UTF_8);
        } else throw new RuntimeException("Unknown payload type");

        return kafkaEventTypeHeaderValue;
    }

    public static <T> T fromJSon(String jsonPayload, Class<T> targetClass) {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        T result;
        try {
            result = objectMapper.reader().readValue(jsonPayload, targetClass);
        } catch (IOException e) {
            throw new RuntimeException("Cannot unmarshall json payload", e);
        }

        return result;
    }

    public static String toJson(Object o) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        String result;

        try {
            result = objectMapper.writer().writeValueAsString(o);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Cannot marshall Class into json payload", e);
        }

        return result;
    }
}
