package tests.labels;

import base.TestBase;
import dto.boards.POST_CreateBoardDto;
import dto.labels.POST_CreateLabelDto;
import org.junit.jupiter.api.*;

import static endpoints.boards.DELETE_DeleteBoard.deleteDeleteBoard;
import static endpoints.boards.POST_CreateBoard.postCreateBoard;
import static endpoints.labels.POST_CreateLabel.postCreateLabel;
import static org.assertj.core.api.Assertions.assertThat;
import static utils.UtilsResponse.deserializeJson;
import static utils_tests.boards.POST_CreateBoardUtils.generateRandomBoardName;
import static utils_tests.labels.POST_CreateLabelUtils.generateRandomLabelColor;
import static utils_tests.labels.POST_CreateLabelUtils.generateRandomLabelName;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PUT_UpdateLabelTest extends TestBase {

    private String boardId;
    private String labelId;
    private String labelName;
    private String labelColor;

    @BeforeAll
    public void setUpCreateBoardAndLabel() {
        responsePost = postCreateBoard(generateRandomBoardName(), null);
        assertThat(responsePost.statusCode()).isEqualTo(200);
        boardId = deserializeJson(responsePost, POST_CreateBoardDto.class).id;
        responsePost = postCreateLabel(boardId, generateRandomLabelName(), generateRandomLabelColor());
        assertThat(responsePost.statusCode()).isEqualTo(200);
        labelId = deserializeJson(responsePost, POST_CreateLabelDto.class).id;
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
