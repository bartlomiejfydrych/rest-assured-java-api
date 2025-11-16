package utils;

import com.fasterxml.jackson.databind.JsonNode;
import io.restassured.response.Response;
import org.assertj.core.api.recursive.comparison.RecursiveComparisonConfiguration;

import static org.assertj.core.api.Assertions.assertThat;
import static utils.UtilsResponse.parseStringToJsonNode;

public class UtilsCompare {

    public static void compareObjects(Object actualObject, Object expectedObject, String... fieldsToIgnore) {
        if (fieldsToIgnore != null && fieldsToIgnore.length > 0) {
            RecursiveComparisonConfiguration config = new RecursiveComparisonConfiguration();
            config.ignoreFields(fieldsToIgnore);

            assertThat(actualObject)
                    .usingRecursiveComparison(config)
                    .isEqualTo(expectedObject);
        } else {
            assertThat(actualObject)
                    .usingRecursiveComparison()
                    .isEqualTo(expectedObject);
        }
    }

    public static void compareObjectsJsonNode(Response response, String expectedResponseString) {
        String responseString = response.getBody().asString();
        JsonNode actualResponse = parseStringToJsonNode(responseString);
        JsonNode expectedResponse = parseStringToJsonNode(expectedResponseString);
        assertThat(actualResponse).isEqualTo(expectedResponse);
    }
}
