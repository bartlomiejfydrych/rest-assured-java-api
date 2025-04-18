package tests.boards;

import base.TestBase;
import endpoints.boards.DELETE_DeleteBoard;
import endpoints.boards.GET_GetBoard;
import endpoints.boards.POST_CreateBoard;
import org.junit.jupiter.api.Test;

public class POST_CreateBoardTest extends TestBase {

    private static String boardId;

    @Test
    public void shouldCreateBoard() {

        // POST
        response = POST_CreateBoard.postCreateBoard("Nazwa tablicy 1", null);
        boardId = response.jsonPath().getString("id");
        // assertion compare response with expected response
        // GET
        response = GET_GetBoard.getGetBoard(boardId);
        // assertion compare response with expected response
        // DELETE
        response = DELETE_DeleteBoard.deleteDeleteBoard(boardId);
        // assertion compare response with expected response
        // get board by id
    }
}
