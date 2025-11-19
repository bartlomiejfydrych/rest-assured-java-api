package utils_tests.lists;

import base.TestBase;
import dto.lists.GET_GetListDto;
import dto.lists.PUT_UpdateListDto;

import static endpoints.lists.GET_GetList.getGetList;
import static org.assertj.core.api.Assertions.assertThat;
import static utils.UtilsCompare.compareObjects;
import static utils.UtilsResponse.deserializeAndValidate;
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

    public static void validateGetAgainstPut(PUT_UpdateListDto responsePutDto) {
        responseGet = getGetList(responsePutDto.id);
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
