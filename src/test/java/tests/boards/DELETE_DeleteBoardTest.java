package tests.boards;

import base.TestBase;
import org.junit.jupiter.api.Test;

import static endpoints.boards.DELETE_DeleteBoard.deleteDeleteBoard;
import static endpoints.boards.GET_GetBoard.getGetBoard;
import static endpoints.boards.POST_CreateBoard.postCreateBoard;
import static org.assertj.core.api.Assertions.assertThat;
import static utils.UtilsCompare.compareObjectsJsonNode;
import static utils_tests.boards.POST_CreateBoardUtils.generateRandomBoardName;

public class DELETE_DeleteBoardTest extends TestBase {

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
        responseDelete = deleteDeleteBoard(boardId);
        assertThat(responseDelete.statusCode()).isEqualTo(200);
        compareObjectsJsonNode(responseDelete, expectedResponse);
        // GET
        responseGet = getGetBoard(boardId);
        assertThat(responseGet.statusCode()).isEqualTo(404);
        assertThat(responseGet.getBody().asString()).isEqualTo("The requested resource was not found.");
    }
}
