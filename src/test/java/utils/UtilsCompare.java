package utils;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.recursive.comparison.RecursiveComparisonConfiguration;

public class UtilsCompare {

    public static void compareObjects(Object actualObject, Object expectedObject, String... fieldsToIgnore) {
        if (fieldsToIgnore != null && fieldsToIgnore.length > 0) {
            RecursiveComparisonConfiguration config = new RecursiveComparisonConfiguration();
            config.ignoreFields(fieldsToIgnore);

            Assertions.assertThat(actualObject)
                    .usingRecursiveComparison(config)
                    .isEqualTo(expectedObject);
        } else {
            Assertions.assertThat(actualObject)
                    .usingRecursiveComparison()
                    .isEqualTo(expectedObject);
        }
    }
}
