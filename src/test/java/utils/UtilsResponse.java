package utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;

import java.util.List;
import java.util.Set;

public class UtilsResponse {

    // ---------------------
    // DTO - Deserialization
    // ---------------------

    // Deserialization settings

    private static final ObjectMapper objectMapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true)
            .configure(DeserializationFeature.FAIL_ON_MISSING_CREATOR_PROPERTIES, true) // require @JsonCreator inside DTO
            .configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, true);

    // OBJECT deserialization

    public static <T> T deserializeAndValidate(Response response, Class<T> clazz) {
        return deserializeAndValidate(response.asString(), clazz);
    }

    public static <T> T deserializeAndValidate(String json, Class<T> clazz) {
        T dto = deserializeJson(json, clazz);
        validateDto(dto);
        return dto;
    }

    public static <T> T deserializeJson(String json, Class<T> clazz) {
        try {
            return objectMapper.readValue(json, clazz);
        } catch (Exception e) {
            throw new RuntimeException("Error deserializing JSON to " + clazz.getSimpleName() + ": " + e.getMessage(), e);
        }
    }

    // LIST deserialization

    public static <T> List<T> deserializeAndValidateList(Response response, TypeReference<List<T>> typeRef) {
        return deserializeAndValidateList(response.asString(), typeRef);
    }

    public static <T> List<T> deserializeAndValidateList(String json, TypeReference<List<T>> typeRef) {
        List<T> list = deserializeJson(json, typeRef);
        for (T dto : list) {
            validateDto(dto);
        }
        return list;
    }

    public static <T> T deserializeJson(String json, TypeReference<T> typeRef) {
        try {
            return objectMapper.readValue(json, typeRef);
        } catch (Exception e) {
            throw new RuntimeException("Error deserializing JSON to generic type: " + e.getMessage(), e);
        }
    }

    // -------------
    // DTO Validator
    // -------------

    public static <T> void validateDto(T dto) {
        try (var factory = Validation.buildDefaultValidatorFactory()) {
            Validator validator = factory.getValidator();
            Set<ConstraintViolation<T>> violations = validator.validate(dto);

            if (!violations.isEmpty()) {
                StringBuilder message = new StringBuilder("Validation failed:\n");
                for (ConstraintViolation<T> v : violations) {
                    message.append(" - ").append(v.getPropertyPath()).append(": ").append(v.getMessage()).append("\n");
                }
                throw new RuntimeException(message.toString());
            }
        }
    }

    // --------
    // JsonNode
    // --------

    public static JsonNode parseStringToJsonNode(String jsonString) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readTree(jsonString);
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse String into object JsonNode", e);
        }
    }
}
