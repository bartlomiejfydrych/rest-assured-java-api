package tests.labels;

import base.TestBase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static endpoints.boards.DELETE_DeleteBoard.deleteDeleteBoard;
import static endpoints.boards.POST_CreateBoard.postCreateBoard;
import static endpoints.labels.DELETE_DeleteLabel.deleteDeleteLabel;
import static endpoints.labels.GET_GetLabel.getGetLabel;
import static endpoints.labels.POST_CreateLabel.postCreateLabel;
import static org.assertj.core.api.Assertions.assertThat;
import static utils.UtilsCompare.compareObjectsJsonNode;
import static utils_tests.boards.POST_CreateBoardUtils.generateRandomBoardName;

public class DELETE_DeleteLabelTest extends TestBase {

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
        compareObjectsJsonNode(responseDelete, expectedResponse);
        // GET
        responseGet = getGetLabel(labelId);
        assertThat(responseGet.statusCode()).isEqualTo(404);
        assertThat(responseGet.getBody().asString()).isEqualTo("The requested resource was not found.");
    }
}
