package tests.boards;

import base.TestBase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static endpoints.boards.DELETE_DeleteBoard.deleteDeleteBoard;
import static endpoints.boards.POST_CreateBoard.postCreateBoard;
import static endpoints.boards.PUT_UpdateBoard.putUpdateBoard;
import static org.assertj.core.api.Assertions.assertThat;
import static utils_tests.boards.POST_CreateBoardUtils.generateRandomBoardName;

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public class PUT_UpdateBoardTest extends TestBase {

    private String boardId;
    // TODO: Przenieść 'trelloId' do jakiegoś pliku konfiguracyjnego
    private String trelloId = "67d9d5e34d7b900257deed0e";

    @BeforeEach
    public void setUp() {
        responsePost = postCreateBoard(generateRandomBoardName(), null);
        assertThat(responsePost.statusCode()).isEqualTo(200);
        boardId = responsePost.jsonPath().getString("id");
    }

    @AfterEach
    public void tearDown() {
        if (boardId != null) {
            responseDelete = deleteDeleteBoard(boardId);
            assertThat(responseDelete.statusCode()).isEqualTo(200);
        }
    }

    // --------------
    // POSITIVE TESTS
    // --------------

    @Test
    public void P1_firstPutTest() {

        responsePut = putUpdateBoard(boardId, null);
    }
}
