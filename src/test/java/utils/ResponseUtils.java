package utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;

import java.util.Set;

public class ResponseUtils {

    private static final ObjectMapper objectMapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true)
            .configure(DeserializationFeature.FAIL_ON_MISSING_CREATOR_PROPERTIES, true) // require @JsonCreator inside DTO
            .configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, true);

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
}
