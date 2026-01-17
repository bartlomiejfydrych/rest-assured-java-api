package utils.response;

import exceptions.ExceptionDtoValidation;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;

import java.util.Set;

public final class UtilsResponseValidator {

    // ==========================================================================================================
    // CONSTRUCTOR
    // ==========================================================================================================

    private UtilsResponseValidator() {
    }

    // ==========================================================================================================
    // FIELDS
    // ==========================================================================================================

    @SuppressWarnings("resource")
    private static final Validator VALIDATOR = Validation.buildDefaultValidatorFactory().getValidator();

    // ==========================================================================================================
    // METHODS – MAIN
    // ==========================================================================================================

    // ----------
    // DTO – LIST
    // ----------

    public static <T> void validateDtoList(Iterable<T> dtos) {
        for (T dto : dtos) {
            validateDto(dto);
        }
    }

    // ------------
    // DTO – SINGLE
    // ------------

    public static <T> void validateDto(T dto) {
        Set<ConstraintViolation<T>> violations = VALIDATOR.validate(dto);

        if (!violations.isEmpty()) {
            StringBuilder message = new StringBuilder("Validation failed:\n");
            for (ConstraintViolation<T> v : violations) {
                message.append(" - ")
                        .append(v.getPropertyPath())
                        .append(": ")
                        .append(v.getMessage())
                        .append("\n");
            }
            throw new ExceptionDtoValidation(message.toString());
        }
    }
}

/*
########################################################################################################################
MY ADDITIONAL NOTES
########################################################################################################################

---------
Validator
---------

ValidatorFactory implements AutoCloseable, but in test context we intentionally
DO NOT close it.

Reason:
- ValidatorFactory is designed to be a heavyweight, application-wide singleton
- It should live for the entire lifecycle of the JVM
- Closing it would invalidate all Validators created from it
- In test execution, JVM terminates after tests, so resources are safely released

IDE may report a warning about missing try-with-resources — this is a known
false-positive in this context and can be safely ignored.

References:
- Hibernate Validator documentation
- Common practice in Spring / Jakarta / test environments

*/
