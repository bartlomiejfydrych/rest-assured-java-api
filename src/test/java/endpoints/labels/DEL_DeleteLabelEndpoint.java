package endpoints.labels;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class DEL_DeleteLabelEndpoint extends LabelsBaseEndpoint {

    // ==========================================================================================================
    // METHODS – MAIN
    // ==========================================================================================================

    public static Response deleteLabel(String labelId) {

        return given().
                    spec(getSpecification()).
                when().
                    delete(labelById(labelId)).
                then().
                    extract().
                    response();
    }
}
