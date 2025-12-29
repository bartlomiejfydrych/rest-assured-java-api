package utils_tests.lists;

import base.TestBase;
import dto.lists.GET_GetListDto;
import dto.lists.PUT_UpdateListDto;
import io.restassured.response.Response;

import static endpoints.lists.GET_GetListEndpoint.getGetList;
import static expected_responses.lists.PUT_UpdateListExpected.BaseExpectedPutUpdateListResponse;
import static org.assertj.core.api.Assertions.assertThat;
import static utils.UtilsCompare.compareObjects;
import static utils.UtilsResponse.deserializeAndValidate;
import static utils.UtilsResponse.deserializeJson;

public class PUT_UpdateListUtils extends TestBase {

    public static PUT_UpdateListDto prepareUniversalExpectedResponsePut(
            String id,
            String name,
            Boolean closed,
            String color,
            String idBoard,
            Long pos,
            Boolean subscribed
    ) {
        PUT_UpdateListDto expectedResponsePutDto = deserializeJson(BaseExpectedPutUpdateListResponse, PUT_UpdateListDto.class);
        expectedResponsePutDto.id = id;
        expectedResponsePutDto.name = name;
        expectedResponsePutDto.closed = closed;
        expectedResponsePutDto.color = color;
        expectedResponsePutDto.idBoard = idBoard;
        expectedResponsePutDto.pos = pos;

        if (subscribed != null) {
            expectedResponsePutDto.subscribed = subscribed;
        }

        return expectedResponsePutDto;
    }

    public static PUT_UpdateListDto prepareExpectedResponsePut(
            String expectedResponse,
            String listId,
            String listName,
            String boardId,
            Long listPos
    ) {
        PUT_UpdateListDto expectedResponsePutDto = deserializeJson(expectedResponse, PUT_UpdateListDto.class);
        expectedResponsePutDto.id = listId;
        expectedResponsePutDto.name = listName;
        expectedResponsePutDto.idBoard = boardId;
        expectedResponsePutDto.pos = listPos;
        return expectedResponsePutDto;
    }

    public static PUT_UpdateListDto prepareExpectedResponsePut(
            String expectedResponse,
            String listId,
            String listName,
            String boardId,
            String listPos
    ) {
        long listPosParsed = Long.parseLong(listPos);

        PUT_UpdateListDto expectedResponsePutDto = deserializeJson(expectedResponse, PUT_UpdateListDto.class);
        expectedResponsePutDto.id = listId;
        expectedResponsePutDto.name = listName;
        expectedResponsePutDto.idBoard = boardId;
        expectedResponsePutDto.pos = listPosParsed;
        return expectedResponsePutDto;
    }

    public static void validateGetAgainstPut(PUT_UpdateListDto responsePutDto) {
        Response responseGet = getGetList(responsePutDto.id);
        assertThat(responseGet.statusCode()).isEqualTo(200);

        GET_GetListDto responseGetDto = deserializeAndValidate(responseGet, GET_GetListDto.class);
        compareObjects(
                responsePutDto,
                responseGetDto,
                PUT_UpdateListDto.FIELD_SUBSCRIBED,
                GET_GetListDto.FIELD_TYPE,
                GET_GetListDto.FIELD_DATASOURCE
        );
    }
}

/*
#######################################################################################################################

=========
MY NOTES:
=========

------------------------------------
NOTE 1 – Java Text Block + replace()
------------------------------------

I want to have this interesting way of substituting variables into a String/Text Block saved here.

public static PUT_UpdateListDto prepareUniversalExpectedResponsePut(
        String id,
        String name,
        Boolean closed,
        String color,
        String idBoard,
        String pos,
        Boolean subscribed
) {
    String expectedResponse = """
            {
                "id": "{id}",
                "name": "{name}",
                "closed": {closed},
                "color": "{color}",
                "idBoard": "{idBoard}",
                "pos": "{pos}",
                "subscribed": {subscribed}
            }
            """
            .replace("{id}", id)
            .replace("{name}", name)
            .replace("{closed}", String.valueOf(closed))
            .replace("{color}", color)
            .replace("{idBoard}", idBoard)
            .replace("{pos}", pos)
            .replace("{subscribed}", String.valueOf(subscribed));

    PUT_UpdateListDto expectedResponsePutDto = deserializeJson(expectedResponse, PUT_UpdateListDto.class);
    return expectedResponsePutDto;
}

*/
