package utils_tests.labels;

import base.TestBase;
import dto.labels.GET_GetLabelDto;
import dto.labels.PUT_UpdateLabelDto;
import io.restassured.response.Response;

import static endpoints.labels.GET_GetLabelEndpoint.getLabel;
import static org.assertj.core.api.Assertions.assertThat;
import static utils.UtilsCompare.compareObjects;
import static utils.UtilsResponse.deserializeAndValidate;
import static utils.UtilsResponse.deserializeJson;

public class PUT_UpdateLabelUtils extends TestBase {

    public static PUT_UpdateLabelDto prepareExpectedResponsePut(
            String expectedResponse,
            PUT_UpdateLabelDto responsePutDto,
            String boardId,
            String labelName,
            String labelColor
    ) {
        PUT_UpdateLabelDto expectedResponsePutDto = deserializeJson(expectedResponse, PUT_UpdateLabelDto.class);
        expectedResponsePutDto.id = responsePutDto.id;
        expectedResponsePutDto.idBoard = boardId;
        expectedResponsePutDto.name = labelName;
        expectedResponsePutDto.color = labelColor;
        return expectedResponsePutDto;
    }

    public static void validateGetAgainstPut(PUT_UpdateLabelDto responsePutDto) {
        Response responseGet = getLabel(responsePutDto.id);
        assertThat(responseGet.statusCode()).isEqualTo(200);

        GET_GetLabelDto responseGetDto = deserializeAndValidate(responseGet, GET_GetLabelDto.class);
        compareObjects(responsePutDto, responseGetDto);
    }
}
