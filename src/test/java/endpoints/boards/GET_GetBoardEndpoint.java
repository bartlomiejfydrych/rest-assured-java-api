package endpoints.boards;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class GET_GetBoardEndpoint extends BoardsBaseEndpoint {

    // ==========================================================================================================
    // METHODS – MAIN
    // ==========================================================================================================

    public static Response getBoard(String boardId, RequestSpecification spec) {
        return given()
                    .spec(spec)
                .when()
                    .get(boardById(boardId))
                .then()
                    .extract()
                    .response();
    }

    public static Response getGetBoard(String boardId) {
        return getBoard(boardId, getSpecification());
    }
}
