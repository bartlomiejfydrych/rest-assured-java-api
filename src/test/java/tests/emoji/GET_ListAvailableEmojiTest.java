package tests.emoji;

import base.TestBase;
import dto.emoji.GET_ListAvailableEmojiDto;
import org.junit.jupiter.api.Test;

import static endpoints.emoji.GET_ListAvailableEmoji.getListAvailableEmoji;
import static expected_responses.emoji.GET_ListAvailableEmojiExpected.P1_expectedGetListAvailableEmojiResponse;
import static org.assertj.core.api.Assertions.assertThat;
import static utils.UtilsCompare.compareObjects;
import static utils.UtilsResponse.*;

public class GET_ListAvailableEmojiTest extends TestBase {

    @Test
    public void P1_shouldGetListAvailableEmojiWhenQueryParametersAreMissing() {
        // GET
        responseGet = getListAvailableEmoji(null);
        assertThat(responseGet.statusCode()).isEqualTo(200);
        GET_ListAvailableEmojiDto responseGetDto = deserializeAndValidate(responseGet, GET_ListAvailableEmojiDto.class);
        GET_ListAvailableEmojiDto expectedResponseGetDto = deserializeJson(P1_expectedGetListAvailableEmojiResponse, GET_ListAvailableEmojiDto.class);
        compareObjects(responseGetDto, expectedResponseGetDto);
    }
}
