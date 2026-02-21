package utils_tests.boards;

import base.TestBase;
import dto.boards.GET_GetBoardDto;
import dto.boards.PUT_UpdateBoardDto;
import io.restassured.response.Response;

import java.net.URL;

import static endpoints.boards.GET_GetBoardEndpoint.getGetBoard;
import static org.assertj.core.api.Assertions.assertThat;
import static utils.UtilsCompare.compareObjects;
import static utils.response.UtilsResponseDeserializer.deserializeAndValidateJson;
import static utils.response.UtilsResponseDeserializer.deserializeJson;

public class PUT_UpdateBoardUtils extends TestBase {

    // ==========================================================================================================
    // METHODS – MAIN
    // ==========================================================================================================

    // -------------------------
    // PREPARE EXPECTED RESPONSE
    // -------------------------

    public static PUT_UpdateBoardDto prepareExpectedResponsePut(String expectedResponse, String boardId, String boardName, URL boardUrl, URL boardShortUrl) {
        // Converting JSON String to DTO Object
        PUT_UpdateBoardDto expectedResponsePutDto = deserializeJson(expectedResponse, PUT_UpdateBoardDto.class);
        // Before replacing, it is always a good idea to first check whether a field exists!
        assertThat(boardId).isNotNull();
        assertThat(boardName).isNotNull();
        assertThat(boardUrl).isNotNull();
        assertThat(boardShortUrl).isNotNull();
        // Value replacement
        expectedResponsePutDto.id = boardId;
        expectedResponsePutDto.name = boardName;
        expectedResponsePutDto.url = boardUrl;
        expectedResponsePutDto.shortUrl = boardShortUrl;
        return expectedResponsePutDto;
    }

    // --------------------------------------
    // VALIDATE GET AGAINST PREVIOUS RESPONSE
    // --------------------------------------

    public static void validateGetAgainstPut(PUT_UpdateBoardDto responsePutDto) {
        Response responseGet = getGetBoard(responsePutDto.id);
        assertThat(responseGet.statusCode()).isEqualTo(200);

        GET_GetBoardDto responseGetDto = deserializeAndValidateJson(responseGet, GET_GetBoardDto.class);
        compareObjects(responsePutDto, responseGetDto, PUT_UpdateBoardDto.FIELD_ORGANIZATION);
    }

    // -------------------------
    // STRIP BOARD NAME FROM URL
    // -------------------------

    public static String stripBoardNameFromUrl(URL url) {
        String[] parts = url.getPath().split("/");
        if (parts.length >= 3) {
            return url.getProtocol() + "://" + url.getHost() + "/b/" + parts[2];
        }
        return url.toString();
    }
}
