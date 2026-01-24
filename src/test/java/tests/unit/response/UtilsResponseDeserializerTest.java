package tests.unit.response;

import com.fasterxml.jackson.core.type.TypeReference;
import exceptions.ExceptionDtoValidation;
import exceptions.ExceptionJsonDeserialization;
import org.junit.jupiter.api.Test;
import utils.response.UtilsResponseDeserializer;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UtilsResponseDeserializerTest {

    // ==========================================================================================================
    // TESTS
    // ==========================================================================================================

    @Test
    void deserializeJson_whenValidJson_shouldReturnDto() {
        String json = "{\"name\":\"test\"}";

        TestDto dto = UtilsResponseDeserializer
                .deserializeJson(json, TestDto.class);

        assertEquals("test", dto.getName());
    }

    @Test
    void deserializeJson_whenInvalidJson_shouldThrowException() {
        String json = "{invalid json}";

        assertThrows(ExceptionJsonDeserialization.class,
                () -> UtilsResponseDeserializer.deserializeJson(json, TestDto.class));
    }

    @Test
    void deserializeAndValidateJson_whenDtoIsValid_shouldPass() {
        String json = "{\"name\":\"valid\"}";

        assertDoesNotThrow(() ->
                UtilsResponseDeserializer
                        .deserializeAndValidateJson(json, TestDto.class));
    }

    @Test
    void deserializeAndValidateJson_whenDtoIsInvalid_shouldThrowValidationException() {
        String json = "{\"name\":null}";

        assertThrows(ExceptionDtoValidation.class,
                () -> UtilsResponseDeserializer
                        .deserializeAndValidateJson(json, TestDto.class));
    }

    @Test
    void deserializeAndValidateJson_whenListContainsInvalidDto_shouldThrowException() {
        String json = """
                [
                    {"name":"ok"},
                    {"name":null}
                ]
                """;

        assertThrows(ExceptionDtoValidation.class,
                () -> UtilsResponseDeserializer.deserializeAndValidateJson(
                        json,
                        new TypeReference<List<TestDto>>() {
                        }
                ));
    }
}
