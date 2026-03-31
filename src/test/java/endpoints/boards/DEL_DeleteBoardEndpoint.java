package endpoints.boards;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class DEL_DeleteBoardEndpoint extends BoardsBaseEndpoint {

    // ==========================================================================================================
    // METHODS – MAIN
    // ==========================================================================================================

    public static Response deleteDeleteBoard(String boardId) {
        return given().
                    spec(getSpecification()).
                when().
                    delete(boardById(boardId)).
                then().
                    extract().
                    response();
    }

    public static Response deleteDeleteBoardWithoutApiKey(String boardId) {
        return given().
                    spec(getSpecificationWithoutApiKey()).
                when().
                    delete(boardById(boardId)).
                then().
                    extract().
                    response();
    }

    public static Response deleteDeleteBoardWithoutToken(String boardId) {
        return given().
                    spec(getSpecificationWithoutToken()).
                when().
                    delete(boardById(boardId)).
                then().
                    extract().
                    response();
    }
}
