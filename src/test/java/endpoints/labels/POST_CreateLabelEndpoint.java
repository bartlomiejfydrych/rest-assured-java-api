package endpoints.labels;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import payloads.labels.POST_CreateLabelPayload;

import static io.restassured.RestAssured.given;


public class POST_CreateLabelEndpoint extends LabelsBaseEndpoint {

    // ==========================================================================================================
    // METHODS – MAIN
    // ==========================================================================================================

    // --------------------------
    // WITH REQUIRED QUERY PARAMS
    // --------------------------

    public static Response createLabel(String idBoard, String name, String color) {

        RequestSpecification requestSpecification =
                given().
                    spec(getSpecification()).
                    queryParam("idBoard", idBoard).
                    queryParam("name", name).
                    queryParam("color", color);

        return requestSpecification.
                when().
                    post(ENDPOINT_LABELS).
                then().
                    extract().
                    response();
    }

    // ---------------
    // WITH ANY PARAMS
    // ---------------

    public static Response createLabelWithAnyParams(POST_CreateLabelPayload payload) {

        RequestSpecification requestSpecification =
                given().
                    spec(getSpecification());

        if (payload != null) {
            applyQueryParams(requestSpecification, payload.toQueryParams());
        }

        return requestSpecification.
                when().
                    post(ENDPOINT_LABELS).
                then().
                    extract().
                    response();
    }
}
