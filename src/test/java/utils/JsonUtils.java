package utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.skyscreamer.jsonassert.*;
import org.skyscreamer.jsonassert.comparator.CustomComparator;

import java.util.Set;

/**
 * Utility class for JSON comparison operations with options for ignoring specific fields
 * and supporting both strict and lenient modes.
 */
public class JsonUtils {

    // ------------
    // MAIN METHODS
    // ------------

    /**
     * Asserts that two JSON strings are equal while ignoring specified fields.
     *
     * @param expectedJson   the expected JSON string
     * @param actualJson     the actual JSON string
     * @param fieldsToIgnore set of field paths to ignore during comparison (supports wildcards like "prefs.*")
     * @param strict         if true, enforces strict order and values; if false, allows lenient comparison
     * @throws JSONException  if the JSON parsing or comparison fails
     * @throws AssertionError if the JSONs are not equal according to the comparison criteria
     */
    public static void assertJsonEqualsIgnoringFields(String expectedJson, String actualJson, Set<String> fieldsToIgnore, boolean strict) throws JSONException {
        JSONCompareResult result = compareJsonIgnoringFields(expectedJson, actualJson, fieldsToIgnore, strict);
        if (!result.passed()) {
            throw new AssertionError(formatError(result));
        }
    }

    /**
     * Asserts that two JSON strings are exactly equal, without ignoring any fields.
     *
     * @param expectedJson the expected JSON string
     * @param actualJson   the actual JSON string
     * @param strict       if true, enforces strict order and values; if false, allows lenient comparison
     * @throws JSONException  if the JSON parsing or comparison fails
     * @throws AssertionError if the JSONs are not equal according to the comparison criteria
     */
    public static void assertJsonEquals(String expectedJson, String actualJson, boolean strict) throws JSONException {
        JSONCompareResult result = compareJson(expectedJson, actualJson, strict);
        if (!result.passed()) {
            throw new AssertionError(formatError(result));
        }
    }

    // --------------
    // HELPER METHODS
    // --------------

    // BIG HELPERS

    /**
     * Compares two JSON strings while ignoring specific fields.
     *
     * @param expectedJson   the expected JSON string
     * @param actualJson     the actual JSON string
     * @param fieldsToIgnore set of field paths to ignore during comparison
     * @param strict         if true, uses strict comparison; otherwise uses lenient mode
     * @return JSONCompareResult containing the comparison result
     * @throws JSONException if JSON parsing fails
     */
    private static JSONCompareResult compareJsonIgnoringFields(String expectedJson, String actualJson, Set<String> fieldsToIgnore, boolean strict) throws JSONException {
        JSONCompareMode mode = strict ? JSONCompareMode.STRICT : JSONCompareMode.LENIENT;

        Customization[] customizations = fieldsToIgnore.stream()
                .map(JsonUtils::createDeepCustomization)
                .toArray(Customization[]::new);

        CustomComparator comparator = new CustomComparator(mode, customizations);

        Object expected = parseJson(expectedJson, "expectedJson");
        Object actual = parseJson(actualJson, "actualJson");

        return JSONCompare.compareJSON(expected.toString(), actual.toString(), comparator);
    }

    /**
     * Compares two JSON strings without ignoring any fields.
     *
     * @param expectedJson the expected JSON string
     * @param actualJson   the actual JSON string
     * @param strict       if true, uses strict comparison; otherwise uses lenient mode
     * @return JSONCompareResult containing the comparison result
     * @throws JSONException if JSON parsing fails
     */
    private static JSONCompareResult compareJson(String expectedJson, String actualJson, boolean strict) throws JSONException {
        JSONCompareMode mode = strict ? JSONCompareMode.STRICT : JSONCompareMode.LENIENT;
        Object expected = parseJson(expectedJson, "expectedJson");
        Object actual = parseJson(actualJson, "actualJson");

        return JSONCompare.compareJSON(expected.toString(), actual.toString(), mode);
    }

    // SMALL HELPERS

    /**
     * Parses a JSON string into either a {@link JSONObject} or {@link JSONArray}.
     * <p>
     * In case of parsing failure or invalid structure, throws a {@link RuntimeException}
     * containing details about the specific input (label) that caused the error.
     * <p>
     * Designed to help quickly identify issues with malformed JSON inputs during tests.
     *
     * @param json  the JSON string to parse
     * @param label a label (e.g., "expectedJson" or "actualJson") used for clearer error reporting
     * @return a parsed {@link JSONObject} or {@link JSONArray}
     * @throws RuntimeException if parsing fails or if the parsed object is neither a JSONObject nor JSONArray
     */
    private static Object parseJson(String json, String label) {
        try {
            Object parsed = JSONParser.parseJSON(json);
            if (parsed instanceof JSONObject || parsed instanceof JSONArray) {
                return parsed;
            } else {
                throw new IllegalArgumentException("Provided " + label + " is neither an object nor an array.");
            }
        } catch (Exception e) {
            throw new RuntimeException("\nFailed to parse " + label + ".\nInput:\n" + json + "\nError: " + e.getMessage(), e);
        }
    }

    /**
     * Creates a Customization object for field ignoring.
     * Supports both exact field ignoring and wildcard section ignoring (e.g., "prefs.*").
     *
     * @param path the field path to ignore
     * @return Customization that matches the provided field path
     */
    private static Customization createDeepCustomization(String path) {
        if (path.endsWith(".*")) {
            String prefix = path.substring(0, path.length() - 2);
            return new Customization(prefix, (o1, o2) -> true);
        } else {
            return new Customization(path, (o1, o2) -> true);
        }
    }

    // LOGS

    /**
     * Formats the differences found during JSON comparison into a readable string.
     *
     * @param result the JSONCompareResult containing differences
     * @return a formatted String listing all differences
     */
    private static String formatError(JSONCompareResult result) {
        StringBuilder sb = new StringBuilder();
        sb.append("Differences:\n");

        for (FieldComparisonFailure failure : result.getFieldFailures()) {
            sb.append("- Field: ").append(failure.getField())
                    .append("\n  Expected: ").append(failure.getExpected())
                    .append("\n  Actual:   ").append(failure.getActual())
                    .append("\n\n");
        }

        return sb.toString();
    }
}
