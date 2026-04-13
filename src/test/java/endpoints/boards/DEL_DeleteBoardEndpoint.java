package endpoints.boards;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class DEL_DeleteBoardEndpoint extends BoardsBaseEndpoint {

    // ==========================================================================================================
    // METHODS – MAIN
    // ==========================================================================================================

    public static Response deleteBoard(String boardId, RequestSpecification spec) {
        return given()
                    .spec(spec)
                .when()
                    .delete(boardById(boardId))
                .then()
                    .extract()
                    .response();
    }

    public static Response deleteDeleteBoard(String boardId) {
        return deleteBoard(boardId, getSpecification());
    }

    // TODO: Usunąć jeśli test parametryzowany na Auth zadziała

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
