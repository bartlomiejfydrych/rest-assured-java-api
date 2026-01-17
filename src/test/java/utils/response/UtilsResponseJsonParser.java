package utils.response;

import com.fasterxml.jackson.databind.JsonNode;
import exceptions.ExceptionJsonParsing;

import static providers.ProviderObjectMapper.getObjectMapper;

public final class UtilsResponseJsonParser {

    // ==========================================================================================================
    // CONSTRUCTOR
    // ==========================================================================================================

    private UtilsResponseJsonParser() {
    }

    // ==========================================================================================================
    // METHODS – MAIN
    // ==========================================================================================================

    // --------
    // JsonNode
    // --------

    public static JsonNode parseStringToJsonNode(String jsonString) {

        if (jsonString == null || jsonString.isBlank()) {
            throw new ExceptionJsonParsing("JSON string is null or empty", null);
        }

        try {
            return getObjectMapper().readTree(jsonString);
        } catch (Exception e) {
            throw new ExceptionJsonParsing("Failed to parse String into JsonNode", e);
        }
    }
}
