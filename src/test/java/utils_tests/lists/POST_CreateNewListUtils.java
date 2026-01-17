package utils_tests.lists;

import base.TestBase;
import dto.lists.GET_GetListDto;
import dto.lists.POST_CreateNewListDto;
import io.restassured.response.Response;

import static endpoints.lists.GET_GetListEndpoint.getGetList;
import static org.assertj.core.api.Assertions.assertThat;
import static utils.UtilsCompare.compareObjects;
import static utils.response.UtilsResponseDeserializer.deserializeAndValidateJson;
import static utils.response.UtilsResponseDeserializer.deserializeJson;

public class POST_CreateNewListUtils extends TestBase {

    public static POST_CreateNewListDto prepareExpectedResponsePost(
            String expectedResponse,
            POST_CreateNewListDto responsePostDto,
            String listName,
            String boardId,
            Long listPos
    ) {
        POST_CreateNewListDto expectedResponsePostDto = deserializeJson(expectedResponse, POST_CreateNewListDto.class);
        expectedResponsePostDto.id = responsePostDto.id;
        expectedResponsePostDto.name = listName;
        expectedResponsePostDto.idBoard = boardId;
        expectedResponsePostDto.pos = listPos;
        return expectedResponsePostDto;
    }

    public static void validateGetAgainstPost(POST_CreateNewListDto responsePostDto) {
        Response responseGet = getGetList(responsePostDto.id);
        assertThat(responseGet.statusCode()).isEqualTo(200);

        GET_GetListDto responseGetDto = deserializeAndValidateJson(responseGet, GET_GetListDto.class);
        compareObjects(responsePostDto, responseGetDto, POST_CreateNewListDto.FIELD_LIMITS);
    }

    public static String generateRandomListName() {
        return "List - " + faker.company().name() + " | Number: " + System.nanoTime();
    }
}
