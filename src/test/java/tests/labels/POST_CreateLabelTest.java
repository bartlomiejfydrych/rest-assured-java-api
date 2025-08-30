package tests.labels;

import base.TestBase;
import dto.boards.POST_CreateBoardDto;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static endpoints.boards.DELETE_DeleteBoard.deleteDeleteBoard;
import static endpoints.boards.POST_CreateBoard.postCreateBoard;
import static org.assertj.core.api.Assertions.assertThat;
import static utils.UtilsResponse.deserializeJson;
import static utils_tests.boards.POST_CreateBoardUtils.generateRandomBoardName;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class POST_CreateLabelTest extends TestBase {

    private String boardId;
    private String labelId;

    @BeforeAll
    public void setUpCreateBoard() {
        responsePost = postCreateBoard(generateRandomBoardName(), null);
        assertThat(responsePost.statusCode()).isEqualTo(200);
        POST_CreateBoardDto responsePostDto = deserializeJson(responsePost, POST_CreateBoardDto.class);
        boardId = responsePostDto.id;
    }

    @AfterAll
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
    public void P1_shouldCreateLabelWithCorrectValuesAndNameWithSpecialCharactersAndNumbers() {

    }
}
