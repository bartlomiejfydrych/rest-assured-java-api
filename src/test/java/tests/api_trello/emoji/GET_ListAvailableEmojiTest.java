package tests.api_trello.emoji;

import base.TestBase;
import dto.emoji.GET_ListAvailableEmojiDto;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import payloads.emoji.GET_ListAvailableEmojiPayload;

import static endpoints.emoji.GET_ListAvailableEmojiEndpoint.getListAvailableEmoji;
import static org.assertj.core.api.Assertions.assertThat;
import static utils.UtilsCompare.compareObjects;
import static utils.UtilsFile.readResourceFileAsString;
import static utils.response.UtilsResponseDeserializer.deserializeAndValidateJson;
import static utils.response.UtilsResponseDeserializer.deserializeJson;

public class GET_ListAvailableEmojiTest extends TestBase {

    // ==========================================================================================================
    // FIELDS
    // ==========================================================================================================

    // --------
    // RESPONSE
    // --------

    private Response responseGet;

    // ==========================================================================================================
    // POSITIVE TESTS
    // ==========================================================================================================

    @Test
    public void P1_shouldGetListAvailableEmojiWhenQueryParametersAreMissing() {

        String resourcePath = "tests/expected_responses/emoji/GET_ListAvailableEmojiExpected/ExpectedGetListAvailableEmojiResponse.json";
        String expectedResponseJsonFile = readResourceFileAsString(resourcePath);

        // GET
        responseGet = getListAvailableEmoji(null);
        assertThat(responseGet.statusCode()).isEqualTo(200);
        GET_ListAvailableEmojiDto responseGetDto = deserializeAndValidateJson(responseGet, GET_ListAvailableEmojiDto.class);
        GET_ListAvailableEmojiDto expectedResponseGetDto = deserializeJson(expectedResponseJsonFile, GET_ListAvailableEmojiDto.class);
        compareObjects(responseGetDto, expectedResponseGetDto);
    }

    @Test
    public void P2_shouldGetListAvailableEmojiWhenQueryParametersAreNull() {

        String resourcePath = "tests/expected_responses/emoji/GET_ListAvailableEmojiExpected/ExpectedGetListAvailableEmojiResponse.json";
        String expectedResponseJsonFile = readResourceFileAsString(resourcePath);

        GET_ListAvailableEmojiPayload payload = new GET_ListAvailableEmojiPayload.Builder()
                .setLocale(null)
                .setSpritesheets(null)
                .build();

        // GET
        responseGet = getListAvailableEmoji(payload);
        assertThat(responseGet.statusCode()).isEqualTo(200);
        GET_ListAvailableEmojiDto responseGetDto = deserializeAndValidateJson(responseGet, GET_ListAvailableEmojiDto.class);
        GET_ListAvailableEmojiDto expectedResponseGetDto = deserializeJson(expectedResponseJsonFile, GET_ListAvailableEmojiDto.class);
        compareObjects(responseGetDto, expectedResponseGetDto);
    }

    @Test
    public void P3_shouldGetListAvailableEmojiWhenSpritesheetsIsFalse() {

        String resourcePath = "tests/expected_responses/emoji/GET_ListAvailableEmojiExpected/ExpectedGetListAvailableEmojiResponse.json";
        String expectedResponseJsonFile = readResourceFileAsString(resourcePath);

        GET_ListAvailableEmojiPayload payload = new GET_ListAvailableEmojiPayload.Builder()
                .setSpritesheets(false)
                .build();

        // GET
        responseGet = getListAvailableEmoji(payload);
        assertThat(responseGet.statusCode()).isEqualTo(200);
        GET_ListAvailableEmojiDto responseGetDto = deserializeAndValidateJson(responseGet, GET_ListAvailableEmojiDto.class);
        GET_ListAvailableEmojiDto expectedResponseGetDto = deserializeJson(expectedResponseJsonFile, GET_ListAvailableEmojiDto.class);
        compareObjects(responseGetDto, expectedResponseGetDto);
    }

    @Test
    public void P4_shouldGetListAvailableEmojiWithOtherLocaleAndWhenSpritesheetsIsTrue() {

        String resourcePath = "tests/expected_responses/emoji/GET_ListAvailableEmojiExpected/P4_ExpectedGetListAvailableEmojiResponse.json";
        String expectedResponseJsonFile = readResourceFileAsString(resourcePath);

        GET_ListAvailableEmojiPayload payload = new GET_ListAvailableEmojiPayload.Builder()
                .setLocale("en-US")
                .setSpritesheets(true)
                .build();

        // GET
        responseGet = getListAvailableEmoji(payload);
        assertThat(responseGet.statusCode()).isEqualTo(200);
        GET_ListAvailableEmojiDto responseGetDto = deserializeAndValidateJson(responseGet, GET_ListAvailableEmojiDto.class);
        GET_ListAvailableEmojiDto expectedResponseGetDto = deserializeJson(expectedResponseJsonFile, GET_ListAvailableEmojiDto.class);
        compareObjects(responseGetDto, expectedResponseGetDto);
    }

    // ==========================================================================================================
    // NEGATIVE TESTS
    // ==========================================================================================================

    @Test
    public void N1_shouldNotGetListAvailableEmojiWhenLocaleHasIncorrectValue() {

        GET_ListAvailableEmojiPayload payload = new GET_ListAvailableEmojiPayload.Builder()
                .setLocale("ABCDabcdĄŚąś1234!@#$")
                .build();

        // GET
        responseGet = getListAvailableEmoji(payload);
        assertThat(responseGet.statusCode()).isEqualTo(400);
        assertThat(responseGet.getBody().asString()).isEqualTo("invalid value for locale");
    }
}
