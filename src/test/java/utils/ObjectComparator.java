package utils;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.recursive.comparison.RecursiveComparisonConfiguration;

public class ObjectComparator {

    // Private constructor - prevents instance creation
    private ObjectComparator() {
        throw new UnsupportedOperationException("Utility class should not be instantiated");
    }

    // Comparison of two objects with the option to specify fields to ignore
    public static void compareObjectsWithIgnoredFields(Object actualObject, Object expectedObject, String... fieldsToIgnore) {
        RecursiveComparisonConfiguration config = new RecursiveComparisonConfiguration();
        config.ignoreFields(fieldsToIgnore);

        Assertions.assertThat(actualObject)
                .usingRecursiveComparison(config)
                .isEqualTo(expectedObject);
    }

    // Comparing two objects ignoring only the "id" field
    public static void compareObjectsWithoutId(Object actualObject, Object expectedObject) {
        compareObjectsWithIgnoredFields(actualObject, expectedObject, "id");
    }
}
