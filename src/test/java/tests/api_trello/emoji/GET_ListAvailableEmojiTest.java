package tests.api_trello.emoji;

import tests.base.TestBase;
import dto.emoji.GET_ListAvailableEmojiDto;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import payloads.emoji.GET_ListAvailableEmojiPayload;

import static endpoints.emoji.GET_ListAvailableEmojiEndpoint.getListAvailableEmoji;
import static org.assertj.core.api.Assertions.assertThat;
import static utils.UtilsCompare.compareObjects;
import static utils.response.UtilsResponseDeserializer.deserializeAndValidateJson;
import static utils_tests.emoji.GET_ListAvailableEmojiUtils.getExpectedResponseDto;

public class GET_ListAvailableEmojiTest extends TestBase {

    // ==========================================================================================================
    // FIELDS
    // ==========================================================================================================

    // --------
    // RESPONSE
    // --------

    private Response responseGet;

    // ---------------
    // CLASS VARIABLES
    // ---------------

    String commonFileName = "ExpectedGetListAvailableEmojiResponse.json";

    // ==========================================================================================================
    // POSITIVE TESTS
    // ==========================================================================================================

    @Test
    public void P1_shouldGetListAvailableEmojiWhenQueryParametersAreMissing() {
        // GET
        responseGet = getListAvailableEmoji(null);
        assertThat(responseGet.statusCode()).isEqualTo(200);
        GET_ListAvailableEmojiDto responseGetDto = deserializeAndValidateJson(responseGet, GET_ListAvailableEmojiDto.class);
        GET_ListAvailableEmojiDto expectedResponseGetDto = getExpectedResponseDto(commonFileName);
        compareObjects(responseGetDto, expectedResponseGetDto);
    }

    @Test
    public void P3_shouldGetListAvailableEmojiWhenSpritesheetsIsFalse() {

        GET_ListAvailableEmojiPayload payload = new GET_ListAvailableEmojiPayload.Builder()
                .setSpritesheets(false)
                .build();

        // GET
        responseGet = getListAvailableEmoji(payload);
        assertThat(responseGet.statusCode()).isEqualTo(200);
        GET_ListAvailableEmojiDto responseGetDto = deserializeAndValidateJson(responseGet, GET_ListAvailableEmojiDto.class);
        GET_ListAvailableEmojiDto expectedResponseGetDto = getExpectedResponseDto(commonFileName);
        compareObjects(responseGetDto, expectedResponseGetDto);
    }

    @Test
    public void P4_shouldGetListAvailableEmojiWithOtherLocaleAndWhenSpritesheetsIsTrue() {

        String fileName = "P4_ExpectedGetListAvailableEmojiResponse.json";
        GET_ListAvailableEmojiPayload payload = new GET_ListAvailableEmojiPayload.Builder()
                .setLocale("en-US")
                .setSpritesheets(true)
                .build();

        // GET
        responseGet = getListAvailableEmoji(payload);
        assertThat(responseGet.statusCode()).isEqualTo(200);
        GET_ListAvailableEmojiDto responseGetDto = deserializeAndValidateJson(responseGet, GET_ListAvailableEmojiDto.class);
        GET_ListAvailableEmojiDto expectedResponseGetDto = getExpectedResponseDto(fileName);
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
