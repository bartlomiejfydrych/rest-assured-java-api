package endpoints.boards;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import payloads.boards.PUT_UpdateBoardPayload;

import static io.restassured.RestAssured.given;

public class PUT_UpdateBoardEndpoint extends BoardsBaseEndpoint {

    // ==========================================================================================================
    // METHODS – MAIN
    // ==========================================================================================================

    // -----------------
    // WITH QUERY PARAMS
    // -----------------

    public static Response updateBoard(String boardId, PUT_UpdateBoardPayload payload, RequestSpecification spec) {

        RequestSpecification requestSpecification =
                given().
                    spec(spec);

        if (payload != null) {
            applyQueryParams(requestSpecification, payload.toQueryParams());
        }

        return requestSpecification.
                when().
                    put(boardById(boardId)).
                then().
                    extract().
                    response();
    }

    public static Response putUpdateBoard(String boardId, PUT_UpdateBoardPayload payload) {
        return updateBoard(boardId, payload, getSpecification());
    }
}
