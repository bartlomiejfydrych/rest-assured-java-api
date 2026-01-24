package tests.unit.response;

import exceptions.ExceptionDtoValidation;
import org.junit.jupiter.api.Test;
import utils.response.UtilsResponseValidator;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UtilsResponseValidatorTest {

    // ==========================================================================================================
    // SINGLE DTO
    // ==========================================================================================================

    @Test
    void validate_whenDtoIsValid_shouldNotThrowException() {
        // GIVEN
        TestDto dto = new TestDto("valid name");
        // WHEN/THEN
        assertDoesNotThrow(() ->
                UtilsResponseValidator.validateDto(dto)
        );
    }

    @Test
    void validate_whenDtoIsInvalid_shouldThrowExceptionDtoValidation() {
        // GIVEN
        TestDto dto = new TestDto(null);
        // WHEN
        ExceptionDtoValidation exception =
                assertThrows(ExceptionDtoValidation.class,
                        () -> UtilsResponseValidator.validateDto(dto));
        // THEN
        assertTrue(exception.getMessage().contains("Validation failed"));
        assertTrue(exception.getMessage().contains("name"));
    }

    // ==========================================================================================================
    // DTO LIST
    // ==========================================================================================================

    @Test
    void validateAll_whenAllDtosAreValid_shouldNotThrowException() {
        // GIVEN
        List<TestDto> dtos = List.of(
                new TestDto("one"),
                new TestDto("two")
        );
        // WHEN/THEN
        assertDoesNotThrow(() ->
                UtilsResponseValidator.validateDtoList(dtos)
        );
    }

    @Test
    void validateAll_whenOneDtoIsInvalid_shouldThrowException() {
        // GIVEN
        List<TestDto> dtos = List.of(
                new TestDto("ok"),
                new TestDto(null)
        );
        // WHEN/THEN
        assertThrows(ExceptionDtoValidation.class,
                () -> UtilsResponseValidator.validateDtoList(dtos));
    }

    // ==========================================================================================================
    // EDGE CASES (DOCUMENTATION TESTS)
    // ==========================================================================================================

    @Test
    void validate_whenDtoHasMultipleViolations_shouldContainAllInMessage() {
        // GIVEN
        TestDto dto = new TestDto(null);
        // WHEN
        ExceptionDtoValidation exception =
                assertThrows(ExceptionDtoValidation.class,
                        () -> UtilsResponseValidator.validateDto(dto));
        // THEN
        String message = exception.getMessage();
        assertTrue(message.startsWith("Validation failed"));
        assertTrue(message.contains("name"));
    }
}
