package endpoints.labels;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class GET_GetLabelEndpoint extends LabelsBaseEndpoint {

    // ==========================================================================================================
    // METHODS – MAIN
    // ==========================================================================================================

    public static Response getLabel(String labelId) {

        return given().
                    spec(getSpecification()).
                when().
                    get(labelById(labelId)).
                then().
                    extract().
                    response();
    }
}
