package tests.boards;

import base.TestBase;
import endpoints.boards.DELETE_DeleteBoard;
import endpoints.boards.GET_GetBoard;
import endpoints.boards.POST_CreateBoard;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class POST_CreateBoardTest extends TestBase {

    private static String boardId;

    @Test
    public void P1_shouldCreateBoardWithOnlyRequiredParameters() {

        String boardName = faker.company().name() + " board" + " " + faker.number().randomNumber();

        // POST
        response = POST_CreateBoard.postCreateBoard(boardName, null);
        try {
            assertThat(response.statusCode()).isEqualTo(200);
            boardId = response.jsonPath().getString("id");
            // TODO: sprawdzanie struktury i typów danych response (DTO/Schema)
            // TODO: porównywanie zmiennych elementów response
            // TODO: porównywanie pozostałych elementów response z oczekiwanym response
            // GET
            response = GET_GetBoard.getGetBoard(boardId);
            assertThat(response.statusCode()).isEqualTo(200);
            // TODO: sprawdzanie struktury i typów danych response (DTO/Schema)
            // TODO: porównywanie zmiennych elementów response
            // TODO: porównywanie pozostałych elementów response z oczekiwanym response
        } finally {
            // DELETE
            response = DELETE_DeleteBoard.deleteDeleteBoard(boardId);
            assertThat(response.statusCode()).isEqualTo(200);
        }
    }
}
