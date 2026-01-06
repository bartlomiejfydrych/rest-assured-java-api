package endpoints.labels;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import payloads.labels.POST_CreateLabelPayload;

import static enums.query_parameters.labels.LabelBaseQueryParameters.*;
import static enums.query_parameters.labels.POST_CreateLabelQueryParameters.*;
import static io.restassured.RestAssured.given;


public class POST_CreateLabelEndpoint extends LabelsBaseEndpoint {

    // ==========================================================================================================
    // METHODS – MAIN
    // ==========================================================================================================

    // --------------------------
    // WITH REQUIRED QUERY PARAMS
    // --------------------------

    public static Response postCreateLabel(String boardId, String labelName, String labelColor) {

        RequestSpecification requestSpecification =
                given().
                    spec(getSpecification()).
                    queryParam(ID_BOARD.key(), boardId).
                    queryParam(NAME.key(), labelName).
                    queryParam(COLOR.key(), labelColor);

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

    public static Response postCreateLabelWithAnyParams(POST_CreateLabelPayload payload) {

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
