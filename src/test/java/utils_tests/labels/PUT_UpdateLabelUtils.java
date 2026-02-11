package utils_tests.labels;

import base.TestBase;
import dto.labels.GET_GetLabelDto;
import dto.labels.PUT_UpdateLabelDto;
import io.restassured.response.Response;

import static endpoints.labels.GET_GetLabelEndpoint.getGetLabel;
import static org.assertj.core.api.Assertions.assertThat;
import static utils.UtilsCompare.compareObjects;
import static utils.response.UtilsResponseDeserializer.deserializeAndValidateJson;

public class PUT_UpdateLabelUtils extends TestBase {

    // ------------------------
    // VALIDATE GET AGAINST PUT
    // ------------------------

    public static void validateGetAgainstPut(PUT_UpdateLabelDto responsePutDto) {
        Response responseGet = getGetLabel(responsePutDto.id);
        assertThat(responseGet.statusCode()).isEqualTo(200);

        GET_GetLabelDto responseGetDto = deserializeAndValidateJson(responseGet, GET_GetLabelDto.class);
        compareObjects(responsePutDto, responseGetDto);
    }
}
