package tests.unit.response;

import tests.base.UnitTestBase;
import com.fasterxml.jackson.core.type.TypeReference;
import exceptions.ExceptionDtoValidation;
import exceptions.ExceptionJsonDeserialization;
import org.junit.jupiter.api.Test;
import utils.response.UtilsResponseDeserializer;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Nested;

class UtilsResponseDeserializerTest extends UnitTestBase {

    // ==========================================================================================================
    // OBJECT DESERIALIZATION
    // ==========================================================================================================

    @Nested
    class ObjectTests {

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
        void deserializeJson_whenJsonContainsUnknownField_shouldThrowException() {
            String json = """
                    {
                        "name": "test",
                        "unknown": "value"
                    }
                    """;

            assertThrows(ExceptionJsonDeserialization.class,
                    () -> UtilsResponseDeserializer.deserializeJson(json, TestDto.class));
        }

        @Test
        void deserializeJson_whenWrongType_shouldThrowException() {
            String json = """
                    {
                        "name": 123
                    }
                    """;

            assertThrows(ExceptionJsonDeserialization.class,
                    () -> UtilsResponseDeserializer.deserializeJson(json, TestDto.class));
        }

        @Test
        void deserializeJson_whenEmptyString_shouldThrowException() {
            String json = "";

            assertThrows(ExceptionJsonDeserialization.class,
                    () -> UtilsResponseDeserializer.deserializeJson(json, TestDto.class));
        }

        @Test
        void deserializeJson_whenNullJson_shouldThrowException() {
            assertThrows(ExceptionJsonDeserialization.class,
                    () -> UtilsResponseDeserializer.deserializeJson((String) null, TestDto.class));
        }
    }

    // ==========================================================================================================
    // VALIDATION
    // ==========================================================================================================

    @Nested
    class ValidationTests {

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
        void deserializeAndValidateJson_whenMissingField_shouldThrowValidationException() {
            String json = "{}";

            assertThrows(ExceptionDtoValidation.class,
                    () -> UtilsResponseDeserializer.deserializeAndValidateJson(json, TestDto.class));
        }

        // ----------
        // NESTED DTO
        // ----------

        @Test
        void deserializeAndValidateJson_whenNestedValid_shouldPass() {
            String json = """
                    {
                        "nested": {
                            "value": "ok"
                        }
                    }
                    """;

            assertDoesNotThrow(() ->
                    UtilsResponseDeserializer.deserializeAndValidateJson(json, ParentDto.class));
        }

        @Test
        void deserializeAndValidateJson_whenNestedFieldMissing_shouldThrowException() {
            String json = """
                    {
                        "nested": {}
                    }
                    """;

            assertThrows(ExceptionDtoValidation.class,
                    () -> UtilsResponseDeserializer.deserializeAndValidateJson(json, ParentDto.class));
        }

        @Test
        void deserializeAndValidateJson_whenNestedInvalid_shouldThrowException() {
            String json = """
                    {
                        "nested": {
                            "value": null
                        }
                    }
                    """;

            assertThrows(ExceptionDtoValidation.class,
                    () -> UtilsResponseDeserializer.deserializeAndValidateJson(json, ParentDto.class));
        }

        // -----------------
        // FIELD CONSTRAINTS
        // -----------------

        @Test
        void deserializeAndValidateJson_whenNullFieldInValidatedDto_shouldThrowException() {
            String json = """
                    {
                        "name": null,
                        "number": "123"
                    }
                    """;

            assertThrows(ExceptionDtoValidation.class,
                    () -> UtilsResponseDeserializer.deserializeAndValidateJson(json, ValidatedDto.class));
        }

        @Test
        void deserializeAndValidateJson_whenSizeInvalid_shouldThrowException() {
            String json = """
                    {
                        "name": "ab",
                        "number": "123"
                    }
                    """;

            assertThrows(ExceptionDtoValidation.class,
                    () -> UtilsResponseDeserializer.deserializeAndValidateJson(json, ValidatedDto.class));
        }

        @Test
        void deserializeAndValidateJson_whenPatternInvalid_shouldThrowException() {
            String json = """
                    {
                        "name": "valid",
                        "number": "abc"
                    }
                    """;

            assertThrows(ExceptionDtoValidation.class,
                    () -> UtilsResponseDeserializer.deserializeAndValidateJson(json, ValidatedDto.class));
        }
    }

    // ==========================================================================================================
    // LISTS
    // ==========================================================================================================

    @Nested
    class ListTests {

        @Test
        void deserializeAndValidateJson_whenListValid_shouldPass() {
            String json = """
                    [
                        {"name":"a"},
                        {"name":"b"}
                    ]
                    """;

            List<TestDto> list = UtilsResponseDeserializer.deserializeAndValidateJson(
                    json,
                    new TypeReference<List<TestDto>>() {
                    }
            );

            assertEquals(2, list.size());
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

        @Test
        void deserializeAndValidateJson_whenEmptyList_shouldPass() {
            String json = "[]";

            List<TestDto> list = UtilsResponseDeserializer.deserializeAndValidateJson(
                    json,
                    new TypeReference<List<TestDto>>() {
                    }
            );

            assertTrue(list.isEmpty());
        }

        @Test
        void deserializeAndValidateJson_whenListContainsNull_shouldThrowException() {
            String json = """
                    [
                        {"name":"ok"},
                        null
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
}
