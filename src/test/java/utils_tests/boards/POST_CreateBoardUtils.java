package utils_tests.boards;

import base.TestBase;
import dto.boards.GET_GetBoardDto;
import dto.boards.POST_CreateBoardDto;

import static endpoints.boards.GET_GetBoard.getGetBoard;
import static org.assertj.core.api.Assertions.assertThat;
import static utils.UtilsCompare.compareObjects;
import static utils.ResponseUtils.deserializeAndValidate;
import static utils.ResponseUtils.deserializeJson;

public class POST_CreateBoardUtils extends TestBase {

    public static POST_CreateBoardDto prepareExpectedResponsePost(String expectedResponse, POST_CreateBoardDto responsePostDto, String boardName) {
        POST_CreateBoardDto expectedResponsePostDto = deserializeJson(expectedResponse, POST_CreateBoardDto.class);
        expectedResponsePostDto.name = boardName;
        expectedResponsePostDto.id = responsePostDto.id;
        expectedResponsePostDto.shortUrl = responsePostDto.shortUrl;
        expectedResponsePostDto.url = responsePostDto.url;
        return expectedResponsePostDto;
    }

    public static void validateGetAgainstPost(POST_CreateBoardDto responsePostDto) {
        responseGet = getGetBoard(responsePostDto.id);
        assertThat(responseGet.statusCode()).isEqualTo(200);

        GET_GetBoardDto responseGetDto = deserializeAndValidate(responseGet, GET_GetBoardDto.class);
        compareObjects(responsePostDto, responseGetDto, POST_CreateBoardDto.FIELD_LIMITS);
    }
}
