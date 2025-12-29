package tests.boards;

import base.TestBase;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static endpoints.boards.DEL_DeleteBoardEndpoint.deleteBoard;
import static endpoints.boards.GET_GetBoardEndpoint.getBoard;
import static endpoints.boards.POST_CreateBoardEndpoint.postCreateBoard;
import static org.assertj.core.api.Assertions.assertThat;
import static utils.UtilsCompare.compareObjectsJsonNode;
import static utils_tests.boards.POST_CreateBoardUtils.generateRandomBoardName;

public class DEL_DeleteBoardTest extends TestBase {

    private Response responsePost;
    private Response responsePut;
    private Response responseGet;
    private Response responseDelete;

    private String boardId;

    @Test
    public void P1_shouldDeleteBoard() {

        String expectedResponse = """
                {
                    "_value": null
                }
                """;

        // POST
        responsePost = postCreateBoard(generateRandomBoardName(), null);
        assertThat(responsePost.statusCode()).isEqualTo(200);
        boardId = responsePost.getBody().jsonPath().getString("id");
        // DELETE
        responseDelete = deleteBoard(boardId);
        assertThat(responseDelete.statusCode()).isEqualTo(200);
        compareObjectsJsonNode(responseDelete, expectedResponse);
        // GET
        responseGet = getBoard(boardId);
        assertThat(responseGet.statusCode()).isEqualTo(404);
        assertThat(responseGet.getBody().asString()).isEqualTo("The requested resource was not found.");
    }
}
