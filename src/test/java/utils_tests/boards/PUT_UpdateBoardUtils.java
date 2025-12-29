package utils_tests.boards;

import base.TestBase;
import dto.boards.GET_GetBoardDto;
import dto.boards.PUT_UpdateBoardDto;
import io.restassured.response.Response;

import java.net.URL;

import static endpoints.boards.GET_GetBoardEndpoint.getBoard;
import static org.assertj.core.api.Assertions.assertThat;
import static utils.UtilsCompare.compareObjects;
import static utils.UtilsResponse.deserializeAndValidate;
import static utils.UtilsResponse.deserializeJson;

public class PUT_UpdateBoardUtils extends TestBase {

    // ------------
    // MAIN METHODS
    // ------------

    public static PUT_UpdateBoardDto prepareExpectedResponsePut(String expectedResponse, String boardId, String boardName, URL boardUrl, URL boardShortUrl) {
        PUT_UpdateBoardDto expectedResponsePutDto = deserializeJson(expectedResponse, PUT_UpdateBoardDto.class);
        expectedResponsePutDto.id = boardId;
        expectedResponsePutDto.name = boardName;
        expectedResponsePutDto.url = boardUrl;
        expectedResponsePutDto.shortUrl = boardShortUrl;
        return expectedResponsePutDto;
    }

    public static void validateGetAgainstPut(PUT_UpdateBoardDto responsePutDto) {
        Response responseGet = getBoard(responsePutDto.id);
        assertThat(responseGet.statusCode()).isEqualTo(200);

        GET_GetBoardDto responseGetDto = deserializeAndValidate(responseGet, GET_GetBoardDto.class);
        compareObjects(responsePutDto, responseGetDto, PUT_UpdateBoardDto.FIELD_ORGANIZATION);
    }

    // -----------
    // SUB METHODS
    // -----------

    // URL

    public static String stripBoardNameFromUrl(URL url) {
        String[] parts = url.getPath().split("/");
        if (parts.length >= 3) {
            return url.getProtocol() + "://" + url.getHost() + "/b/" + parts[2];
        }
        return url.toString();
    }
}
