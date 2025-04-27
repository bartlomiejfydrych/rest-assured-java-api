package utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.skyscreamer.jsonassert.*;
import org.skyscreamer.jsonassert.comparator.CustomComparator;

import java.util.Set;

public class JsonUtils {

    // ------------
    // MAIN METHODS
    // ------------

    public static void assertJsonEqualsIgnoringFields(String expectedJson, String actualJson, Set<String> fieldsToIgnore, boolean strict) throws JSONException {
        JSONCompareResult result = compareJsonIgnoringFields(expectedJson, actualJson, fieldsToIgnore, strict);
        if (!result.passed()) {
            throw new AssertionError(formatError(expectedJson, actualJson, result));
        }
    }

    public static void assertJsonEquals(String expectedJson, String actualJson, boolean strict) throws JSONException {
        JSONCompareResult result = compareJson(expectedJson, actualJson, strict);
        if (!result.passed()) {
            throw new AssertionError(formatError(expectedJson, actualJson, result));
        }
    }

    // --------------
    // HELPER METHODS
    // --------------

    // BIG HELPERS

    // Compares JSONs ignoring the fields we provide
    private static JSONCompareResult compareJsonIgnoringFields(String expectedJson, String actualJson, Set<String> fieldsToIgnore, boolean strict) {
        JSONCompareMode mode = strict ? JSONCompareMode.STRICT : JSONCompareMode.LENIENT;

        Customization[] customizations = fieldsToIgnore.stream()
                .map(JsonUtils::createDeepCustomization)
                .toArray(Customization[]::new);

        CustomComparator comparator = new CustomComparator(mode, customizations);

        Object expected = parseJson(expectedJson);
        Object actual = parseJson(actualJson);

        return JSONCompare.compareJSON(expected, actual, comparator);
    }

    // Compares JSONs without skipping fields
    private static JSONCompareResult compareJson(String expectedJson, String actualJson, boolean strict) {
        JSONCompareMode mode = strict ? JSONCompareMode.STRICT : JSONCompareMode.LENIENT;
        Object expected = parseJson(expectedJson);
        Object actual = parseJson(actualJson);

        return JSONCompare.compareJSON(expected, actual, mode);
    }

    // SMALL HELPERS

    // Converts a JSON object or JSON array to an object
    private static Object parseJson(String json) {
        try {
            Object parsed = JSONParser.parseJSON(json);
            if (parsed instanceof JSONObject || parsed instanceof JSONArray) {
                return parsed;
            } else {
                throw new IllegalArgumentException("Provided JSON is neither an object nor an array.");
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse JSON", e);
        }
    }

    // Allows ignoring entire nested sections, e.g. "prefs.switcherViews.*"
    private static Customization createDeepCustomization(String path) {
        if (path.endsWith(".*")) {
            String prefix = path.substring(0, path.length() - 2);
            return new Customization((fullPath, o1, o2) -> fullPath.startsWith(prefix));
        } else {
            return new Customization((fullPath, o1, o2) -> fullPath.equals(path));
        }
    }

    // LOGS

    // Displays expected and actual JSON along with their differences
    private static String formatError(String expectedJson, String actualJson, JSONCompareResult result) throws JSONException {
        StringBuilder sb = new StringBuilder();
        sb.append("\nExpected JSON:\n").append(prettyPrintJson(parseJson(expectedJson)))
                .append("\n\nActual JSON:\n").append(prettyPrintJson(parseJson(actualJson)))
                .append("\n\nDifferences:\n");

        for (FieldComparisonFailure failure : result.getFieldFailures()) {
            sb.append("Field: ").append(failure.getField())
                    .append("\n  Expected: ").append(failure.getExpected())
                    .append("\n  Actual:   ").append(failure.getActual())
                    .append("\n\n");
        }

        return sb.toString();
    }

    // Displays JSONs in multi-line format instead of single line
    private static String prettyPrintJson(Object json) throws JSONException {
        if (json instanceof JSONObject) {
            return ((JSONObject) json).toString(2);
        } else if (json instanceof JSONArray) {
            return ((JSONArray) json).toString(2);
        } else {
            return String.valueOf(json);
        }
    }
}
