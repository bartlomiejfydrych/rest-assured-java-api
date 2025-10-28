package tests.labels;

import base.TestBase;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;

import static endpoints.boards.DELETE_DeleteBoard.deleteDeleteBoard;
import static endpoints.boards.POST_CreateBoard.postCreateBoard;
import static endpoints.labels.POST_CreateLabel.postCreateLabel;
import static org.assertj.core.api.Assertions.assertThat;
import static utils_tests.boards.POST_CreateBoardUtils.generateRandomBoardName;
import static utils_tests.labels.POST_CreateLabelUtils.generateRandomLabelColor;
import static utils_tests.labels.POST_CreateLabelUtils.generateRandomLabelName;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PUT_UpdateFieldOnLabelTest extends TestBase {

    private String boardId;
    private String labelId;
    private String labelFieldName;
    private String labelFieldColor;
    private String labelFieldValue;

    @BeforeAll
    public void setUpCreateBoardAndLabel() {
        responsePost = postCreateBoard(generateRandomBoardName(), null);
        assertThat(responsePost.statusCode()).isEqualTo(200);
        boardId = responsePost.getBody().jsonPath().getString("id");
        responsePost = postCreateLabel(boardId, generateRandomLabelName(), generateRandomLabelColor());
        assertThat(responsePost.statusCode()).isEqualTo(200);
        labelId = responsePost.getBody().jsonPath().getString("id");
    }

    @AfterAll
    public void tearDownDeleteBoardAndLabel() {
        if (boardId != null) {
            responseDelete = deleteDeleteBoard(boardId);
            assertThat(responseDelete.statusCode()).isEqualTo(200);
            boardId = null;
            labelId = null;
        }
    }

    // --------------
    // POSITIVE TESTS
    // --------------


}
