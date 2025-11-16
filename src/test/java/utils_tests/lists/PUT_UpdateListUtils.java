package utils_tests.lists;

import base.TestBase;
import dto.lists.PUT_UpdateListDto;

import static utils.UtilsResponse.deserializeJson;

public class PUT_UpdateListUtils extends TestBase {

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
}
