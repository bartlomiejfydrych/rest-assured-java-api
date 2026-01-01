package endpoints.labels;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import payloads.labels.PUT_UpdateLabelPayload;

import static io.restassured.RestAssured.given;

public class PUT_UpdateLabelEndpoint extends LabelsBaseEndpoint {

    // ==========================================================================================================
    // METHODS – MAIN
    // ==========================================================================================================

    // -----------------
    // WITH QUERY PARAMS
    // -----------------

    public static Response putUpdateLabel(String labelId, PUT_UpdateLabelPayload payload) {

        RequestSpecification requestSpecification =
                given().
                    spec(getSpecification());

        if (payload != null) {
            applyQueryParams(requestSpecification, payload.toQueryParams());
        }

        return requestSpecification.
                when().
                    put(labelById(labelId)).
                then().
                    extract().
                    response();
    }

    // -------------------
    // WITHOUT ID & PARAMS
    // -------------------

    public static Response putUpdateLabelWithoutIdAndParams() {

        return given().
                    spec(getSpecification()).
                when().
                    put(ENDPOINT_LABELS).
                then().
                    extract().
                    response();
    }
}
