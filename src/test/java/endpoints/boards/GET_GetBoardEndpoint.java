package endpoints.boards;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class GET_GetBoardEndpoint extends BoardsBaseEndpoint {

    // ==========================================================================================================
    // METHODS – MAIN
    // ==========================================================================================================

    public static Response getBoard(String boardId) {
        return given().
                    spec(spec()).
                when().
                    get(boardById(boardId)).
                then().
                    extract().
                    response();
    }
}
