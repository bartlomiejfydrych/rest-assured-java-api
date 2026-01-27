package tests.unit.response;

import base.UnitTestBase;
import com.fasterxml.jackson.databind.JsonNode;
import exceptions.ExceptionJsonParsing;
import org.junit.jupiter.api.Test;
import utils.response.UtilsResponseJsonParser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UtilsResponseJsonParserTest extends UnitTestBase {

    // ==========================================================================================================
    // TESTS
    // ==========================================================================================================

    @Test
    void parseStringToJsonNode_whenNull_shouldThrowException() {
        assertThrows(ExceptionJsonParsing.class,
                () -> UtilsResponseJsonParser.parseStringToJsonNode(null));
    }

    @Test
    void parseStringToJsonNode_whenBlank_shouldThrowException() {
        assertThrows(ExceptionJsonParsing.class,
                () -> UtilsResponseJsonParser.parseStringToJsonNode("   "));
    }

    @Test
    void parseStringToJsonNode_whenValidJson_shouldReturnJsonNode() {
        JsonNode node = UtilsResponseJsonParser
                .parseStringToJsonNode("{\"name\":\"board\"}");

        assertEquals("board", node.get("name").asText());
    }
}
