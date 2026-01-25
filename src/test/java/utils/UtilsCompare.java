package utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.restassured.response.Response;
import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.recursive.comparison.RecursiveComparisonConfiguration;

import static org.assertj.core.api.Assertions.assertThat;
import static utils.response.UtilsResponseJsonParser.parseStringToJsonNode;

public final class UtilsCompare {

    // ==========================================================================================================
    // COMPARE OBJECTS (WORKS FOR LISTS TOO!)
    // ==========================================================================================================

    // ---------------
    // HARD ASSERTIONS
    // ---------------

    public static void compareObjects(Object actualObject, Object expectedObject, String... fieldsToIgnore) {
        assertThat(actualObject)
                .usingRecursiveComparison(configWithIgnoredFields(fieldsToIgnore))
                .isEqualTo(expectedObject);
    }

    // ---------------
    // SOFT ASSERTIONS
    // ---------------

    public static void compareObjectsSoft(Object actualObject, Object expectedObject, String... fieldsToIgnore) {
        SoftAssertions softly = new SoftAssertions();

        softly.assertThat(actualObject)
                .usingRecursiveComparison(configWithIgnoredFields(fieldsToIgnore))
                .isEqualTo(expectedObject);

        softly.assertAll();
    }

    // ==========================================================================================================
    // COMPARE JSON – JSON NODE (WORKS FOR LISTS TOO!)
    // ==========================================================================================================

    // ---------------
    // HARD ASSERTIONS
    // ---------------

    public static void compareResponseWithJson(Response response, String expectedResponseJsonString, String... fieldsToIgnore) {
        JsonNode actualJN = parseStringToJsonNode(response.getBody().asString());
        JsonNode expectedJN = parseStringToJsonNode(expectedResponseJsonString);

        removeFieldsRecursively(actualJN, fieldsToIgnore);
        removeFieldsRecursively(expectedJN, fieldsToIgnore);

        assertThat(actualJN).isEqualTo(expectedJN);
    }

    // ---------------
    // SOFT ASSERTIONS
    // ---------------

    public static void compareResponseWithJsonSoft(Response response, String expectedResponseJsonString, String... fieldsToIgnore) {
        JsonNode actualJN = parseStringToJsonNode(response.getBody().asString());
        JsonNode expectedJN = parseStringToJsonNode(expectedResponseJsonString);

        removeFieldsRecursively(actualJN, fieldsToIgnore);
        removeFieldsRecursively(expectedJN, fieldsToIgnore);

        SoftAssertions softly = new SoftAssertions();

        softly.assertThat(actualJN)
                .as("JSON comparison after ignoring fields %s", String.join(", ", fieldsToIgnore))
                .isEqualTo(expectedJN);

        softly.assertAll();
    }

    // ==========================================================================================================
    // HELPERS
    // ==========================================================================================================

    /**
     * Removes given fields from JsonNode recursively.
     * Works for:
     * - ObjectNode
     * - ArrayNode
     * - nested structures
     */
    private static void removeFieldsRecursively(JsonNode node, String... fieldsToIgnore) {

        if (node == null || fieldsToIgnore == null || fieldsToIgnore.length == 0) {
            return;
        }

        if (node.isObject()) {
            ObjectNode objectNode = (ObjectNode) node;

            // Remove fields at this level
            for (String field : fieldsToIgnore) {
                objectNode.remove(field);
            }

            // Go deeper
            objectNode.fields().forEachRemaining(entry ->
                    removeFieldsRecursively(entry.getValue(), fieldsToIgnore)
            );
        }

        if (node.isArray()) {
            node.forEach(element ->
                    removeFieldsRecursively(element, fieldsToIgnore)
            );
        }
    }

    // ==========================================================================================================
    // CONFIGS
    // ==========================================================================================================

    private static RecursiveComparisonConfiguration configWithIgnoredFields(String... fieldsToIgnore) {
        RecursiveComparisonConfiguration config = new RecursiveComparisonConfiguration();
        config.ignoreFields(fieldsToIgnore);
        return config;
    }
}
