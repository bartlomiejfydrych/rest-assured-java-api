package utils;

import com.fasterxml.jackson.databind.JsonNode;
import io.restassured.response.Response;
import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.recursive.comparison.RecursiveComparisonConfiguration;

import static org.assertj.core.api.Assertions.assertThat;
import static utils.UtilsResponse.parseStringToJsonNode;

public final class UtilsCompare {

    // ==========================================================================================================
    // METHODS – MAIN
    // ==========================================================================================================

    // ---------------
    // COMPARE OBJECTS
    // ---------------

    // HARD ASSERTIONS

    public static void compareObjects(Object actualObject, Object expectedObject, String... fieldsToIgnore) {
        assertThat(actualObject)
                .usingRecursiveComparison(configWithIgnoredFields(fieldsToIgnore))
                .isEqualTo(expectedObject);
    }

    // SOFT ASSERTIONS

    public static void compareObjectsSoft(Object actualObject, Object expectedObject, String... fieldsToIgnore) {
        SoftAssertions softly = new SoftAssertions();

        softly.assertThat(actualObject)
                .usingRecursiveComparison(configWithIgnoredFields(fieldsToIgnore))
                .isEqualTo(expectedObject);

        softly.assertAll();
    }

    // ------------------------
    // COMPARE JSON – JSON NODE
    // ------------------------

    // HARD ASSERTIONS

    public static void compareResponseWithJson(Response response, String expectedResponseJsonString, String... fieldsToIgnore) {
        compareJsonStrings(
                response.getBody().asString(),
                expectedResponseJsonString,
                fieldsToIgnore
        );
    }

    // SOFT ASSERTIONS

    public static void compareResponseWithJsonSoft(Response response, String expectedResponseJsonString, String... fieldsToIgnore) {
        compareJsonStringsSoft(
                response.getBody().asString(),
                expectedResponseJsonString,
                fieldsToIgnore
        );
    }

    // ==========================================================================================================
    // METHODS – SUB
    // ==========================================================================================================

    // ------------
    // JSON STRINGS
    // ------------

    // HARD ASSERTIONS

    private static void compareJsonStrings(String actualJson, String expectedJson, String... fieldsToIgnore) {
        JsonNode actualJN = parseStringToJsonNode(actualJson);
        JsonNode expectedJN = parseStringToJsonNode(expectedJson);

        assertThat(actualJN)
                .usingRecursiveComparison(configWithIgnoredFields(fieldsToIgnore))
                .isEqualTo(expectedJN);
    }

    // SOFT ASSERTIONS

    private static void compareJsonStringsSoft(String actualJson, String expectedJson, String... fieldsToIgnore) {
        JsonNode actualJN = parseStringToJsonNode(actualJson);
        JsonNode expectedJN = parseStringToJsonNode(expectedJson);

        SoftAssertions softly = new SoftAssertions();

        softly.assertThat(actualJN)
                .usingRecursiveComparison(configWithIgnoredFields(fieldsToIgnore))
                .isEqualTo(expectedJN);

        softly.assertAll();
    }

    // -------
    // CONFIGS
    // -------

    private static RecursiveComparisonConfiguration configWithIgnoredFields(String... fieldsToIgnore) {
        RecursiveComparisonConfiguration config = new RecursiveComparisonConfiguration();
        config.ignoreFields(fieldsToIgnore);
        return config;
    }
}
