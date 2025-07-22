package utils_tests.boards;

import base.TestBase;
import dto.boards.GET_GetBoardDto;
import dto.boards.PUT_UpdateBoardDto;

import java.net.URL;

import static endpoints.boards.GET_GetBoard.getGetBoard;
import static org.assertj.core.api.Assertions.assertThat;
import static utils.UtilsCompare.compareObjects;
import static utils.UtilsResponse.deserializeAndValidate;
import static utils.UtilsResponse.deserializeJson;

public class PUT_UpdateBoardUtils extends TestBase {

    public static PUT_UpdateBoardDto prepareExpectedResponsePut(String expectedResponse, String boardId, URL boardUrl, URL boardShortUrl) {
        PUT_UpdateBoardDto expectedResponsePutDto = deserializeJson(expectedResponse, PUT_UpdateBoardDto.class);
        expectedResponsePutDto.id = boardId;
        expectedResponsePutDto.url = boardUrl;
        expectedResponsePutDto.shortUrl = boardShortUrl;
        return expectedResponsePutDto;
    }

    public static void validateGetAgainstPut(PUT_UpdateBoardDto responsePutDto) {
        responseGet = getGetBoard(responsePutDto.id);
        assertThat(responseGet.statusCode()).isEqualTo(200);

        GET_GetBoardDto responseGetDto = deserializeAndValidate(responseGet, GET_GetBoardDto.class);
        compareObjects(responsePutDto, responseGetDto);
    }
}
