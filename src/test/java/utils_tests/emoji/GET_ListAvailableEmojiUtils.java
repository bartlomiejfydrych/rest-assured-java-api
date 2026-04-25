package utils_tests.emoji;

import dto.emoji.GET_ListAvailableEmojiDto;
import utils.UtilsResources;

public class GET_ListAvailableEmojiUtils {

    // ==========================================================================================================
    // FIELDS
    // ==========================================================================================================

    private static final String BASE_PATH = "tests/expected_responses/emoji/GET_ListAvailableEmojiExpected/";

    // ==========================================================================================================
    // METHODS – MAIN
    // ==========================================================================================================

    public static GET_ListAvailableEmojiDto getExpectedResponseDto(String fileName) {
        return UtilsResources.readJsonFileAsObject(
                BASE_PATH + fileName,
                GET_ListAvailableEmojiDto.class
        );
    }
}
