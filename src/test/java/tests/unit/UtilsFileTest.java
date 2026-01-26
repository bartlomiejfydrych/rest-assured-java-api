package tests.unit;

import org.junit.jupiter.api.Test;
import utils.UtilsFile;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UtilsFileTest {

    // ==========================================================================================================
    // SUCCESS
    // ==========================================================================================================

    @Test
    void readResourceFileAsString_whenResourceExists_shouldReturnContent() {
        String resourcePath = "tests/unit/sample.txt";

        String content = UtilsFile.readResourceFileAsString(resourcePath);

        assertThat(content)
                .contains("Hello test file!")
                .contains("Line 2");
    }

    // ==========================================================================================================
    // ERRORS
    // ==========================================================================================================

    @Test
    void readResourceFileAsString_whenResourceDoesNotExist_shouldThrowIllegalArgumentException() {
        String resourcePath = "not-existing-file.txt";

        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> UtilsFile.readResourceFileAsString(resourcePath)
        );

        assertThat(ex.getMessage())
                .contains("Resource not found")
                .contains(resourcePath);
    }
}
