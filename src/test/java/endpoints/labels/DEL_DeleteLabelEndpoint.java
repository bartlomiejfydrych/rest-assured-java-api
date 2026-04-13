package endpoints.labels;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class DEL_DeleteLabelEndpoint extends LabelsBaseEndpoint {

    // ==========================================================================================================
    // METHODS – MAIN
    // ==========================================================================================================

    public static Response deleteLabel(String labelId, RequestSpecification spec) {
        return given().
                    spec(spec).
                when().
                    delete(labelById(labelId)).
                then().
                    extract().
                    response();
    }

    public static Response deleteDeleteLabel(String labelId) {
        return deleteLabel(labelId, getSpecification());
    }
}
