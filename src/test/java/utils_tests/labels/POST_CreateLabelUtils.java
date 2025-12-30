package utils_tests.labels;

import base.TestBase;
import dto.labels.GET_GetLabelDto;
import dto.labels.POST_CreateLabelDto;
import io.restassured.response.Response;

import static endpoints.labels.GET_GetLabelEndpoint.getLabel;
import static org.assertj.core.api.Assertions.assertThat;
import static utils.UtilsCommon.pickRandom;
import static utils.UtilsCompare.compareObjects;
import static utils.UtilsResponse.deserializeAndValidate;
import static utils.UtilsResponse.deserializeJson;

public class POST_CreateLabelUtils extends TestBase {

    public static POST_CreateLabelDto prepareExpectedResponsePost(
            String expectedResponse,
            POST_CreateLabelDto responsePostDto,
            String boardId,
            String labelName,
            String labelColor
    ) {
        POST_CreateLabelDto expectedResponsePostDto = deserializeJson(expectedResponse, POST_CreateLabelDto.class);
        expectedResponsePostDto.id = responsePostDto.id;
        expectedResponsePostDto.idBoard = boardId;
        expectedResponsePostDto.name = labelName;
        expectedResponsePostDto.color = labelColor;
        return expectedResponsePostDto;
    }

    public static void validateGetAgainstPost(POST_CreateLabelDto responsePostDto) {
        Response responseGet = getLabel(responsePostDto.id);
        assertThat(responseGet.statusCode()).isEqualTo(200);

        GET_GetLabelDto responseGetDto = deserializeAndValidate(responseGet, GET_GetLabelDto.class);
        compareObjects(responsePostDto, responseGetDto, POST_CreateLabelDto.FIELD_LIMITS);
    }

    public static String generateRandomLabelName() {
        return faker.company().name() + " label " + System.nanoTime();
    }

    public static String generateRandomLabelColor() {
        return pickRandom("yellow", "purple", "blue", "red", "green", "orange", "black", "sky", "pink", "lime");
    }
}
