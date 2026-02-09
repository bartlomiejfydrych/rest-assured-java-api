package utils_tests.lists;

import base.TestBase;
import dto.lists.GET_GetListDto;
import dto.lists.PUT_UpdateListDto;
import io.restassured.response.Response;

import static endpoints.lists.GET_GetListEndpoint.getGetList;
import static org.assertj.core.api.Assertions.assertThat;
import static utils.UtilsCompare.compareObjects;
import static utils.response.UtilsResponseDeserializer.deserializeAndValidateJson;

public class PUT_UpdateListUtils extends TestBase {

    // ==========================================================================================================
    // METHODS – MAIN
    // ==========================================================================================================

    // -------------------------
    // VALIDATE GET AGAINST POST
    // -------------------------

    public static void validateGetAgainstPut(PUT_UpdateListDto responsePutDto) {
        Response responseGet = getGetList(responsePutDto.id);
        assertThat(responseGet.statusCode()).isEqualTo(200);

        GET_GetListDto responseGetDto = deserializeAndValidateJson(responseGet, GET_GetListDto.class);
        compareObjects(
                responsePutDto,
                responseGetDto,
                PUT_UpdateListDto.FIELD_SUBSCRIBED,
                GET_GetListDto.FIELD_TYPE,
                GET_GetListDto.FIELD_DATASOURCE
        );
    }
}
