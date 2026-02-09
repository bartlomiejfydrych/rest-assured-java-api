package utils_tests.labels;

import base.TestBase;
import dto.labels.GET_GetLabelDto;
import dto.labels.POST_CreateLabelDto;
import io.restassured.response.Response;

import static endpoints.labels.GET_GetLabelEndpoint.getGetLabel;
import static org.assertj.core.api.Assertions.assertThat;
import static utils.UtilsCompare.compareObjects;
import static utils.UtilsRandom.pickRandom;
import static utils.response.UtilsResponseDeserializer.deserializeAndValidateJson;

public class POST_CreateLabelUtils extends TestBase {

    // ==========================================================================================================
    // METHODS – MAIN
    // ==========================================================================================================

    // --------------------------
    // GENERATE RANDOM LABEL NAME
    // --------------------------

    public static String generateRandomLabelName() {
        return faker.company().name() + " label " + System.nanoTime();
    }

    // ---------------------------
    // GENERATE RANDOM LABEL COLOR
    // ---------------------------

    public static String generateRandomLabelColor() {
        return pickRandom("yellow", "purple", "blue", "red", "green", "orange", "black", "sky", "pink", "lime");
    }

    // -------------------------
    // VALIDATE GET AGAINST POST
    // -------------------------

    public static void validateGetAgainstPost(POST_CreateLabelDto responsePostDto) {
        Response responseGet = getGetLabel(responsePostDto.id);
        assertThat(responseGet.statusCode()).isEqualTo(200);

        GET_GetLabelDto responseGetDto = deserializeAndValidateJson(responseGet, GET_GetLabelDto.class);
        compareObjects(responsePostDto, responseGetDto, POST_CreateLabelDto.FIELD_LIMITS);
    }
}
