package endpoints.boards;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import payloads.boards.POST_CreateBoardPayload;

import static enums.query_parameters.boards.boards.BoardBaseQueryParameters.NAME;
import static io.restassured.RestAssured.given;

public class POST_CreateBoardEndpoint extends BoardsBaseEndpoint {

    // ==========================================================================================================
    // METHODS – MAIN
    // ==========================================================================================================

    // -----------------
    // WITH QUERY PARAMS
    // -----------------

    public static Response postCreateBoard(String boardName, POST_CreateBoardPayload payload) {

        RequestSpecification requestSpecification =
                given().
                    spec(getSpecification()).
                    queryParam(NAME.key(), boardName);

        if (payload != null) {
            applyQueryParams(requestSpecification, payload.toQueryParams());
        }

        return requestSpecification.
                when().
                    post(ENDPOINT_BOARDS).
                then().
                    extract().
                    response();
    }

    // --------------------
    // WITHOUT QUERY PARAMS
    // --------------------

    public static Response postCreateBoardMissingRequiredParameters() {
        return given().
                    spec(getSpecification()).
                when().
                    post(ENDPOINT_BOARDS).
                then().
                    extract().
                    response();
    }
}
