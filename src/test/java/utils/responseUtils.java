package utils;

import com.fasterxml.jackson.databind.ObjectMapper;

public class responseUtils {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static <T> T deserializeJson(String json, Class<T> clazz) {
        try {
            return objectMapper.readValue(json, clazz);
        } catch (Exception e) {
            throw new RuntimeException("JSON parsing failed", e);
        }
    }
}
