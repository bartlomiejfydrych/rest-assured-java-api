package tests.unit;

import base.UnitTestBase;
import io.restassured.builder.ResponseBuilder;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import utils.UtilsCompare;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UtilsCompareTest extends UnitTestBase {

    // ==========================================================================================================
    // OBJECT COMPARISON – HARD
    // ==========================================================================================================

    @Test
    void compareObjects_whenObjectsAreEqual_shouldPass() {
        CompareTestDto actual = new CompareTestDto("test", 10);
        CompareTestDto expected = new CompareTestDto("test", 10);

        assertDoesNotThrow(() ->
                UtilsCompare.compareObjects(actual, expected)
        );
    }

    @Test
    void compareObjects_whenObjectsDiffer_shouldThrowAssertionError() {
        CompareTestDto actual = new CompareTestDto("test", 10);
        CompareTestDto expected = new CompareTestDto("test", 99);

        assertThrows(AssertionError.class, () ->
                UtilsCompare.compareObjects(actual, expected)
        );
    }

    @Test
    void compareObjects_whenIgnoringField_shouldPass() {
        CompareTestDto actual = new CompareTestDto("test", 10);
        CompareTestDto expected = new CompareTestDto("test", 99);

        assertDoesNotThrow(() ->
                UtilsCompare.compareObjects(actual, expected, "value")
        );
    }

    // ==========================================================================================================
    // OBJECT COMPARISON – HARD (LISTS)
    // ==========================================================================================================

    @Test
    void compareObjects_whenListsAreEqual_shouldPass() {
        var actual = List.of(
                new CompareTestDto("A", 1),
                new CompareTestDto("B", 2)
        );

        var expected = List.of(
                new CompareTestDto("A", 1),
                new CompareTestDto("B", 2)
        );

        assertDoesNotThrow(() ->
                UtilsCompare.compareObjects(actual, expected)
        );
    }

    @Test
    void compareObjects_whenListsDiffer_shouldThrowAssertionError() {
        var actual = List.of(
                new CompareTestDto("A", 1),
                new CompareTestDto("B", 2)
        );

        var expected = List.of(
                new CompareTestDto("A", 1),
                new CompareTestDto("B", 99)
        );

        assertThrows(AssertionError.class, () ->
                UtilsCompare.compareObjects(actual, expected)
        );
    }

    @Test
    void compareObjects_whenListsDifferButIgnoringField_shouldPass() {
        var actual = List.of(
                new CompareTestDto("A", 1),
                new CompareTestDto("B", 2)
        );

        var expected = List.of(
                new CompareTestDto("A", 99),
                new CompareTestDto("B", 88)
        );

        assertDoesNotThrow(() ->
                UtilsCompare.compareObjects(actual, expected, "value")
        );
    }

    // ==========================================================================================================
    // OBJECT COMPARISON – SOFT
    // ==========================================================================================================

    @Test
    void compareObjectsSoft_whenObjectsAreEqual_shouldPass() {
        CompareTestDto actual = new CompareTestDto("test", 10);
        CompareTestDto expected = new CompareTestDto("test", 10);

        assertDoesNotThrow(() ->
                UtilsCompare.compareObjectsSoft(actual, expected)
        );
    }

    @Test
    void compareObjectsSoft_whenObjectsDiffer_shouldThrowAssertionErrorOnAssertAll() {
        CompareTestDto actual = new CompareTestDto("test", 10);
        CompareTestDto expected = new CompareTestDto("test", 99);

        assertThrows(AssertionError.class, () ->
                UtilsCompare.compareObjectsSoft(actual, expected)
        );
    }

    // ==========================================================================================================
    // OBJECT COMPARISON – SOFT (LISTS)
    // ==========================================================================================================

    @Test
    void compareObjectsSoft_whenListsAreEqual_shouldPass() {
        var actual = List.of(
                new CompareTestDto("A", 1),
                new CompareTestDto("B", 2)
        );

        var expected = List.of(
                new CompareTestDto("A", 1),
                new CompareTestDto("B", 2)
        );

        assertDoesNotThrow(() ->
                UtilsCompare.compareObjectsSoft(actual, expected)
        );
    }

    @Test
    void compareObjectsSoft_whenListsDiffer_shouldThrowAssertionError() {
        var actual = List.of(
                new CompareTestDto("A", 1),
                new CompareTestDto("B", 2)
        );

        var expected = List.of(
                new CompareTestDto("A", 1),
                new CompareTestDto("B", 99)
        );

        assertThrows(AssertionError.class, () ->
                UtilsCompare.compareObjectsSoft(actual, expected)
        );
    }

    // ==========================================================================================================
    // JSON COMPARISON – OBJECT
    // ==========================================================================================================

    @Test
    void compareResponseWithJson_whenJsonObjectIsEqual_shouldPass() {
        String json = """
                {
                  "name": "test",
                  "value": 10
                }
                """;

        Response response = responseWithBody(json);

        assertDoesNotThrow(() ->
                UtilsCompare.compareResponseWithJson(response, json)
        );
    }

    @Test
    void compareResponseWithJson_whenJsonObjectDiffers_shouldThrowAssertionError() {
        String actualJson = """
                {
                  "name": "test",
                  "value": 10
                }
                """;

        String expectedJson = """
                {
                  "name": "test",
                  "value": 99
                }
                """;

        Response response = responseWithBody(actualJson);

        assertThrows(AssertionError.class, () ->
                UtilsCompare.compareResponseWithJson(response, expectedJson)
        );
    }

    @Test
    void compareResponseWithJson_whenIgnoringFieldInObject_shouldPass() {
        String actualJson = """
                {
                  "name": "test",
                  "value": 10
                }
                """;

        String expectedJson = """
                {
                  "name": "test",
                  "value": 99
                }
                """;

        Response response = responseWithBody(actualJson);

        assertDoesNotThrow(() ->
                UtilsCompare.compareResponseWithJson(response, expectedJson, "value")
        );
    }

    // ==========================================================================================================
    // JSON COMPARISON – ARRAY
    // ==========================================================================================================

    @Test
    void compareResponseWithJson_whenJsonArrayIsEqual_shouldPass() {
        String json = """
                [
                  { "id": 1, "name": "A" },
                  { "id": 2, "name": "B" }
                ]
                """;

        Response response = responseWithBody(json);

        assertDoesNotThrow(() ->
                UtilsCompare.compareResponseWithJson(response, json)
        );
    }

    @Test
    void compareResponseWithJson_whenJsonArrayDiffers_shouldThrowAssertionError() {
        String actualJson = """
                [
                  { "id": 1, "name": "A" },
                  { "id": 2, "name": "B" }
                ]
                """;

        String expectedJson = """
                [
                  { "id": 1, "name": "A" },
                  { "id": 99, "name": "B" }
                ]
                """;

        Response response = responseWithBody(actualJson);

        assertThrows(AssertionError.class, () ->
                UtilsCompare.compareResponseWithJson(response, expectedJson)
        );
    }

    @Test
    void compareResponseWithJson_whenIgnoringFieldInArray_shouldPass() {
        String actualJson = """
                [
                  { "id": 1, "name": "A" },
                  { "id": 2, "name": "B" }
                ]
                """;

        String expectedJson = """
                [
                  { "id": 99, "name": "A" },
                  { "id": 88, "name": "B" }
                ]
                """;

        Response response = responseWithBody(actualJson);

        assertDoesNotThrow(() ->
                UtilsCompare.compareResponseWithJson(response, expectedJson, "id")
        );
    }

    // ==========================================================================================================
    // JSON COMPARISON – NESTED
    // ==========================================================================================================

    @Test
    void compareResponseWithJson_whenIgnoringNestedField_shouldPass() {
        String actualJson = """
                {
                  "board": {
                    "id": "123",
                    "cards": [
                      { "id": "a", "name": "Card A" },
                      { "id": "b", "name": "Card B" }
                    ]
                  }
                }
                """;

        String expectedJson = """
                {
                  "board": {
                    "id": "999",
                    "cards": [
                      { "id": "x", "name": "Card A" },
                      { "id": "y", "name": "Card B" }
                    ]
                  }
                }
                """;

        Response response = responseWithBody(actualJson);

        assertDoesNotThrow(() ->
                UtilsCompare.compareResponseWithJson(response, expectedJson, "id")
        );
    }

    // ==========================================================================================================
    // JSON COMPARISON – SOFT
    // ==========================================================================================================

    @Test
    void compareResponseWithJsonSoft_whenJsonDiffers_shouldThrowAssertionError() {
        String actualJson = """
                {
                  "name": "test",
                  "value": 10
                }
                """;

        String expectedJson = """
                {
                  "name": "test",
                  "value": 99
                }
                """;

        Response response = responseWithBody(actualJson);

        assertThrows(AssertionError.class, () ->
                UtilsCompare.compareResponseWithJsonSoft(response, expectedJson)
        );
    }

    // ==========================================================================================================
    // HELPERS
    // ==========================================================================================================

    private Response responseWithBody(String body) {
        return new ResponseBuilder()
                .setBody(body)
                .build();
    }
}
