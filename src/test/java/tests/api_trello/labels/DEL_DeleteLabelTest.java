package tests.api_trello.labels;

import base.TestBase;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static endpoints.boards.DEL_DeleteBoardEndpoint.deleteDeleteBoard;
import static endpoints.boards.POST_CreateBoardEndpoint.postCreateBoard;
import static endpoints.labels.DEL_DeleteLabelEndpoint.deleteDeleteLabel;
import static endpoints.labels.GET_GetLabelEndpoint.getGetLabel;
import static endpoints.labels.POST_CreateLabelEndpoint.postCreateLabel;
import static org.assertj.core.api.Assertions.assertThat;
import static utils.UtilsCompare.compareResponseWithJson;
import static utils_tests.boards.POST_CreateBoardUtils.generateRandomBoardName;

public class DEL_DeleteLabelTest extends TestBase {

    private Response responsePost;
    private Response responsePut;
    private Response responseGet;
    private Response responseDelete;

    private String boardId;
    private String labelId;
    private String labelName;
    private String labelColor;

    @BeforeEach
    public void setUpCreateBoard() {
        responsePost = postCreateBoard(generateRandomBoardName(), null);
        assertThat(responsePost.statusCode()).isEqualTo(200);
        boardId = responsePost.getBody().jsonPath().getString("id");
    }

    @AfterEach
    public void tearDownDeleteBoard() {
        if (boardId != null) {
            responseDelete = deleteDeleteBoard(boardId);
            assertThat(responseDelete.statusCode()).isEqualTo(200);
            boardId = null;
        }
    }

    // --------------
    // POSITIVE TESTS
    // --------------

    @Test
    public void P1_shouldDeleteLabel() {

        labelName = "TEST – Delete Label";
        labelColor = null;
        String expectedResponse = """
                {
                    "limits": {
                
                    }
                }
                """;

        // POST
        responsePost = postCreateLabel(boardId, labelName, labelColor);
        assertThat(responsePost.statusCode()).isEqualTo(200);
        labelId = responsePost.getBody().jsonPath().getString("id");
        // DELETE
        responseDelete = deleteDeleteLabel(labelId);
        assertThat(responseDelete.statusCode()).isEqualTo(200);
        compareResponseWithJson(responseDelete, expectedResponse);
        // GET
        responseGet = getGetLabel(labelId);
        assertThat(responseGet.statusCode()).isEqualTo(404);
        assertThat(responseGet.getBody().asString()).isEqualTo("The requested resource was not found.");
    }
}
