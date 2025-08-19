package tests.emoji;

import base.TestBase;
import dto.emoji.GET_ListAvailableEmojiDto;
import org.junit.jupiter.api.Test;
import payloads.emoji.GET_ListAvailableEmojiPayload;

import java.util.Map;

import static endpoints.emoji.GET_ListAvailableEmoji.getListAvailableEmoji;
import static org.assertj.core.api.Assertions.assertThat;
import static utils.UtilsCommon.readResourceFileAsString;
import static utils.UtilsCompare.compareObjects;
import static utils.UtilsResponse.*;

public class GET_ListAvailableEmojiTest extends TestBase {

    // --------------
    // POSITIVE TESTS
    // --------------

    @Test
    public void P1_shouldGetListAvailableEmojiWhenQueryParametersAreMissing() {

        String expectedResponseJsonFile = readResourceFileAsString(
                "src/test/resources/expected_responses/emoji/GET_ListAvaiableEmojiExpected/ExpectedGetListAvailableEmojiResponse.json"
        );

        // GET
        responseGet = getListAvailableEmoji(null);
        assertThat(responseGet.statusCode()).isEqualTo(200);
        GET_ListAvailableEmojiDto responseGetDto = deserializeAndValidate(responseGet, GET_ListAvailableEmojiDto.class);
        GET_ListAvailableEmojiDto expectedResponseGetDto = deserializeJson(expectedResponseJsonFile, GET_ListAvailableEmojiDto.class);
        compareObjects(responseGetDto, expectedResponseGetDto);
    }

    @Test
    public void P2_shouldGetListAvailableEmojiWhenQueryParametersAreNull() {

        String expectedResponseJsonFile = readResourceFileAsString(
                "src/test/resources/expected_responses/emoji/GET_ListAvaiableEmojiExpected/ExpectedGetListAvailableEmojiResponse.json"
        );

        GET_ListAvailableEmojiPayload payload = new GET_ListAvailableEmojiPayload.Builder()
                .setLocale(null)
                .setSpritesheets(null)
                .build();
        Map<String, Object> queryParams = payload.toQueryParams();

        // GET
        responseGet = getListAvailableEmoji(queryParams);
        assertThat(responseGet.statusCode()).isEqualTo(200);
        GET_ListAvailableEmojiDto responseGetDto = deserializeAndValidate(responseGet, GET_ListAvailableEmojiDto.class);
        GET_ListAvailableEmojiDto expectedResponseGetDto = deserializeJson(expectedResponseJsonFile, GET_ListAvailableEmojiDto.class);
        compareObjects(responseGetDto, expectedResponseGetDto);
    }

    @Test
    public void P3_shouldGetListAvailableEmojiWhenSpritesheetsIsFalse() {

        String expectedResponseJsonFile = readResourceFileAsString(
                "src/test/resources/expected_responses/emoji/GET_ListAvaiableEmojiExpected/ExpectedGetListAvailableEmojiResponse.json"
        );

        GET_ListAvailableEmojiPayload payload = new GET_ListAvailableEmojiPayload.Builder()
                .setSpritesheets(false)
                .build();
        Map<String, Object> queryParams = payload.toQueryParams();

        // GET
        responseGet = getListAvailableEmoji(queryParams);
        assertThat(responseGet.statusCode()).isEqualTo(200);
        GET_ListAvailableEmojiDto responseGetDto = deserializeAndValidate(responseGet, GET_ListAvailableEmojiDto.class);
        GET_ListAvailableEmojiDto expectedResponseGetDto = deserializeJson(expectedResponseJsonFile, GET_ListAvailableEmojiDto.class);
        compareObjects(responseGetDto, expectedResponseGetDto);
    }

    @Test
    public void P4_shouldGetListAvailableEmojiWithOtherLocaleAndWhenSpritesheetsIsTrue() {

        String expectedResponseJsonFile = readResourceFileAsString(
                "src/test/resources/expected_responses/emoji/GET_ListAvaiableEmojiExpected/P4_ExpectedGetListAvailableEmojiResponse.json"
        );

        GET_ListAvailableEmojiPayload payload = new GET_ListAvailableEmojiPayload.Builder()
                .setLocale("en-US")
                .setSpritesheets(true)
                .build();
        Map<String, Object> queryParams = payload.toQueryParams();

        // GET
        responseGet = getListAvailableEmoji(queryParams);
        assertThat(responseGet.statusCode()).isEqualTo(200);
        GET_ListAvailableEmojiDto responseGetDto = deserializeAndValidate(responseGet, GET_ListAvailableEmojiDto.class);
        GET_ListAvailableEmojiDto expectedResponseGetDto = deserializeJson(expectedResponseJsonFile, GET_ListAvailableEmojiDto.class);
        compareObjects(responseGetDto, expectedResponseGetDto);
    }

    // --------------
    // NEGATIVE TESTS
    // --------------


}
