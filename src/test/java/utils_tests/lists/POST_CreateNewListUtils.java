package utils_tests.lists;

import base.TestBase;
import dto.lists.GET_GetListDto;
import dto.lists.POST_CreateNewListDto;
import io.restassured.response.Response;

import static endpoints.lists.GET_GetListEndpoint.getGetList;
import static org.assertj.core.api.Assertions.assertThat;
import static utils.UtilsCompare.compareObjects;
import static utils.response.UtilsResponseDeserializer.deserializeAndValidateJson;

public class POST_CreateNewListUtils extends TestBase {

    // ==========================================================================================================
    // METHODS – MAIN
    // ==========================================================================================================

    // -------------------------
    // GENERATE RANDOM LIST NAME
    // -------------------------

    public static String generateRandomListName() {
        return "List - " + faker.company().name() + " | Number: " + System.nanoTime();
    }

    // -------------------------
    // VALIDATE GET AGAINST POST
    // -------------------------

    public static void validateGetAgainstPost(POST_CreateNewListDto responsePostDto) {
        Response responseGet = getGetList(responsePostDto.id);
        assertThat(responseGet.statusCode()).isEqualTo(200);

        GET_GetListDto responseGetDto = deserializeAndValidateJson(responseGet, GET_GetListDto.class);
        compareObjects(responsePostDto, responseGetDto, POST_CreateNewListDto.FIELD_LIMITS);
    }
}
