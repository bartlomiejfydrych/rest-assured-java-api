package utils.allure;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;

public class UtilsAllure {

    // ==========================================================================================================
    // FIELDS
    // ==========================================================================================================

    private static final Path ALLURE_RESULTS_DIR = Paths.get("target", "allure-results");

    // ==========================================================================================================
    // METHODS – MAIN
    // ==========================================================================================================

    // --------------------------------------------
    // Method for cleaning data from previous tests
    // --------------------------------------------

    public static void cleanAllureResultsDirectory() {

        try {
            if (Files.exists(ALLURE_RESULTS_DIR)) {

                try (var paths = Files.walk(ALLURE_RESULTS_DIR)) {

                    paths.sorted(Comparator.reverseOrder())
                            .forEach(path -> {
                                try {
                                    Files.delete(path);
                                } catch (IOException e) {
                                    throw new RuntimeException(
                                            "Failed to delete: " + path, e);
                                }
                            });
                }
            }

            Files.createDirectories(ALLURE_RESULTS_DIR);

        } catch (IOException e) {
            throw new RuntimeException(
                    "Failed to clean Allure results directory", e);
        }
    }
}
