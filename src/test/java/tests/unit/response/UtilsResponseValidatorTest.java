package tests.unit.response;

import exceptions.ExceptionDtoValidation;
import org.junit.jupiter.api.Test;
import utils.response.UtilsResponseValidator;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UtilsResponseValidatorTest {

    // ==========================================================================================================
    // SINGLE DTO
    // ==========================================================================================================

    @Test
    void validate_whenDtoIsValid_shouldNotThrowException() {
        TestDto dto = new TestDto("valid name");

        assertDoesNotThrow(() ->
                UtilsResponseValidator.validateDto(dto)
        );
    }

    @Test
    void validate_whenDtoIsInvalid_shouldThrowExceptionDtoValidation() {
        TestDto dto = new TestDto(null);

        ExceptionDtoValidation exception =
                assertThrows(ExceptionDtoValidation.class,
                        () -> UtilsResponseValidator.validateDto(dto));

        assertTrue(exception.getMessage().contains("Validation failed"));
        assertTrue(exception.getMessage().contains("name"));
    }

    // ==========================================================================================================
    // DTO LIST
    // ==========================================================================================================

    @Test
    void validateAll_whenAllDtosAreValid_shouldNotThrowException() {
        List<TestDto> dtos = List.of(
                new TestDto("one"),
                new TestDto("two")
        );

        assertDoesNotThrow(() ->
                UtilsResponseValidator.validateDtoList(dtos)
        );
    }

    @Test
    void validateAll_whenOneDtoIsInvalid_shouldThrowException() {
        List<TestDto> dtos = List.of(
                new TestDto("ok"),
                new TestDto(null)
        );

        assertThrows(ExceptionDtoValidation.class,
                () -> UtilsResponseValidator.validateDtoList(dtos));
    }

    // ==========================================================================================================
    // EDGE CASES (DOCUMENTATION TESTS)
    // ==========================================================================================================

    @Test
    void validate_whenDtoHasMultipleViolations_shouldContainAllInMessage() {
        TestDto dto = new TestDto(null);

        ExceptionDtoValidation exception =
                assertThrows(ExceptionDtoValidation.class,
                        () -> UtilsResponseValidator.validateDto(dto));

        String message = exception.getMessage();
        assertTrue(message.startsWith("Validation failed"));
        assertTrue(message.contains("name"));
    }
}
