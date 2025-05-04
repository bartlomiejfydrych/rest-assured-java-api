package tests.boards;

import base.TestBase;
import endpoints.boards.DELETE_DeleteBoard;
import endpoints.boards.GET_GetBoard;
import endpoints.boards.POST_CreateBoard;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Paths;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.assertj.core.api.Assertions.assertThat;

public class POST_CreateBoardTest extends TestBase {

    private String boardId;
    private final File boardSchema = Paths.get(baseSchemaPath, "boards", "board_schema.json").toFile();

    @Test
    public void P1_shouldCreateBoardWithOnlyRequiredParameters() {

        String boardName = faker.company().name() + " board" + " " + faker.number().randomNumber();

        // POST
        response = POST_CreateBoard.postCreateBoard(boardName, null);
        try {
            assertThat(response.statusCode()).isEqualTo(200);
            boardId = response.jsonPath().getString("id");
            response.then().assertThat().body(matchesJsonSchema(boardSchema));
            // TODO: porównywanie zmiennych elementów response
            // name
            // shortUrl
            // url
            // TODO: porównywanie pozostałych elementów response z oczekiwanym response
            // GET
            response = GET_GetBoard.getGetBoard(boardId);
            assertThat(response.statusCode()).isEqualTo(200);
            response.then().assertThat().body(matchesJsonSchema(boardSchema));
            // TODO: porównywanie zmiennych elementów response
            // TODO: porównywanie pozostałych elementów response z oczekiwanym response
        } finally {
            // DELETE
            response = DELETE_DeleteBoard.deleteDeleteBoard(boardId);
            assertThat(response.statusCode()).isEqualTo(200);
        }
    }
}
