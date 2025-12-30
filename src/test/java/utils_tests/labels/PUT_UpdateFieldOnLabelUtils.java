package utils_tests.labels;

import base.TestBase;
import dto.labels.GET_GetLabelDto;
import dto.labels.PUT_UpdateFieldOnLabelDto;
import io.restassured.response.Response;

import static endpoints.labels.GET_GetLabelEndpoint.getLabel;
import static org.assertj.core.api.Assertions.assertThat;
import static utils.UtilsCompare.compareObjects;
import static utils.UtilsResponse.deserializeAndValidate;

public class PUT_UpdateFieldOnLabelUtils extends TestBase {

    public static void validateGetAgainstPut(PUT_UpdateFieldOnLabelDto responsePutDto) {
        Response responseGet = getLabel(responsePutDto.id);
        assertThat(responseGet.statusCode()).isEqualTo(200);

        GET_GetLabelDto responseGetDto = deserializeAndValidate(responseGet, GET_GetLabelDto.class);
        compareObjects(responsePutDto, responseGetDto);
    }
}
