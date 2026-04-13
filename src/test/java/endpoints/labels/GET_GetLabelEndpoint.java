package endpoints.labels;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class GET_GetLabelEndpoint extends LabelsBaseEndpoint {

    // ==========================================================================================================
    // METHODS – MAIN
    // ==========================================================================================================

    public static Response getLabel(String labelId, RequestSpecification spec) {
        return given().
                    spec(spec).
                when().
                    get(labelById(labelId)).
                then().
                    extract().
                    response();
    }

    public static Response getGetLabel(String labelId) {
        return getLabel(labelId, getSpecification());
    }
}
