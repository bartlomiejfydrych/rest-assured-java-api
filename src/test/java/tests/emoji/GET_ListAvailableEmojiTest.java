package tests.emoji;

import base.TestBase;
import dto.emoji.GET_ListAvailableEmojiDto;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import static endpoints.emoji.GET_ListAvailableEmoji.getListAvailableEmoji;
import static org.assertj.core.api.Assertions.assertThat;
import static utils.UtilsCompare.compareObjects;
import static utils.UtilsResponse.*;

public class GET_ListAvailableEmojiTest extends TestBase {

    @Test
    public void P1_shouldGetListAvailableEmojiWhenQueryParametersAreMissing() throws IOException {

        String expectedResponseJsonFile = Files.readString(Paths.get(
                "src/test/resources/expected_responses/emoji/GET_ListAvaiableEmojiExpected/P1_expectedGetListAvailableEmojiResponse.json"),
                StandardCharsets.UTF_8);

        // GET
        responseGet = getListAvailableEmoji(null);
        assertThat(responseGet.statusCode()).isEqualTo(200);
        GET_ListAvailableEmojiDto responseGetDto = deserializeAndValidate(responseGet, GET_ListAvailableEmojiDto.class);
        GET_ListAvailableEmojiDto expectedResponseGetDto = deserializeJson(expectedResponseJsonFile, GET_ListAvailableEmojiDto.class);
        compareObjects(responseGetDto, expectedResponseGetDto);
    }
}
