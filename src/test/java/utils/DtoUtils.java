package utils;

import io.restassured.response.Response;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Utility class for working with Data Transfer Objects (DTOs) in REST-assured tests.
 * <p>
 * Provides functionality for:
 * <ul>
 *     <li>Deserializing REST responses into single DTO objects or lists of DTOs</li>
 *     <li>Validating DTO structure and constraints using Bean Validation (Jakarta Validation / Hibernate Validator)</li>
 *     <li>Throwing informative assertion errors if the structure or constraints are violated</li>
 * </ul>
 */
public class DtoUtils {

    /**
     * Validator instance used to perform Bean Validation on DTOs.
     * <p>
     * Marked with {@code @SuppressWarnings("resource")} because the factory is AutoCloseable
     * but used here as a static singleton during the lifetime of the test JVM.
     */
    @SuppressWarnings("resource")
    private static final Validator VALIDATOR = Validation
            .buildDefaultValidatorFactory()
            .getValidator();

    // ------------
    // MAIN METHODS
    // ------------

    /**
     * Deserializes a REST-assured response body to a single DTO instance.
     *
     * @param response the REST-assured response
     * @param dtoClass the DTO class type to deserialize to
     * @param <T>      the DTO type
     * @return an instance of the DTO populated from the JSON response
     */
    public static <T> T deserializeResponse(Response response, Class<T> dtoClass) {
        return response.as(dtoClass);
    }

    /**
     * Deserializes a REST-assured response body to a list of DTO instances.
     * <p>
     * Handles dynamic array instantiation via reflection to avoid type erasure issues.
     * Uses {@code @SuppressWarnings("unchecked")} due to the cast of generic array.
     *
     * @param response the REST-assured response
     * @param dtoClass the DTO class type to deserialize to
     * @param <T>      the DTO type
     * @return a list of DTOs populated from the JSON response array
     */
    @SuppressWarnings("unchecked")
    public static <T> List<T> deserializeResponseList(Response response, Class<T> dtoClass) {
        T[] array = (T[]) java.lang.reflect.Array.newInstance(dtoClass, 0);
        return Arrays.asList(response.as((Class<T[]>) array.getClass()));
    }

    /**
     * Validates a single DTO object against its annotated Bean Validation constraints.
     * <p>
     * Throws an {@link AssertionError} if any constraint violations are found, and lists all violations.
     *
     * @param dto the DTO object to validate
     * @param <T> the DTO type
     * @throws AssertionError if validation fails
     */
    public static <T> void validateDto(T dto) {
        Set<ConstraintViolation<T>> violations = VALIDATOR.validate(dto);

        if (!violations.isEmpty()) {
            throw new AssertionError("DTO validation failed:\n\n" + formatViolations(violations));
        }
    }

    /**
     * Validates each DTO object in a list against its annotated Bean Validation constraints.
     * <p>
     * Throws an {@link AssertionError} with a full report of all constraint violations across the list.
     *
     * @param dtoList the list of DTO objects to validate
     * @param <T>     the DTO type
     * @throws AssertionError if validation fails for any item in the list
     */
    public static <T> void validateDtoList(List<T> dtoList) {
        StringBuilder allErrors = new StringBuilder();
        boolean hasErrors = false;

        for (int i = 0; i < dtoList.size(); i++) {
            T item = dtoList.get(i);
            Set<ConstraintViolation<T>> violations = VALIDATOR.validate(item);
            if (!violations.isEmpty()) {
                hasErrors = true;
                allErrors.append("Item #").append(i).append(":\n")
                        .append(formatViolations(violations)).append("\n");
            }
        }

        if (hasErrors) {
            throw new AssertionError("DTO list validation failed:\n\n" + allErrors);
        }
    }

    // --------------
    // HELPER METHODS
    // --------------

    /**
     * Formats a set of constraint violations into a readable string for test output.
     * Each violation will include the field name, rejected value, and message.
     *
     * @param violations the set of constraint violations
     * @param <T>        the type of the validated object
     * @return a formatted string of all violations
     */
    private static <T> String formatViolations(Set<ConstraintViolation<T>> violations) {
        return violations.stream()
                .map(v -> "- Field: " + v.getPropertyPath()
                        + "\n  Rejected value: " + v.getInvalidValue()
                        + "\n  Violation: " + v.getMessage())
                .collect(Collectors.joining("\n\n"));
    }
}
