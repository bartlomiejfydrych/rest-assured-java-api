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
    private final File postCreateBoardSchema = Paths.get(baseSchemaPath, "boards", "POST_create_board_schema.json").toFile();
    private final File getGetBoardSchema = Paths.get(baseSchemaPath, "boards", "GET_get_board.json").toFile();

    @Test
    public void P1_shouldCreateBoardWithOnlyRequiredParameters() {

        String boardName = faker.company().name() + " board" + " " + faker.number().randomNumber();

        // POST
        responsePost = POST_CreateBoard.postCreateBoard(boardName, null);
        assertThat(responsePost.statusCode()).isEqualTo(200);
        try {
            boardId = responsePost.jsonPath().getString("id");
            responsePost.then().assertThat().body(matchesJsonSchema(postCreateBoardSchema));
            assertThat(responsePost.jsonPath().getString("name")).isEqualTo(boardName);
            // assertJsonEqualsIgnoringFields(P1ExpectedPostBoardResponse, responsePost.toString(), Set.of("name", "shortUrl", "url"), true);
            // GET
            responseGet = GET_GetBoard.getGetBoard(boardId);
            assertThat(responseGet.statusCode()).isEqualTo(200);
            responseGet.then().assertThat().body(matchesJsonSchema(getGetBoardSchema));
            assertThat(responseGet.jsonPath().getString("name")).isEqualTo(boardName);
            // assertJsonEqualsIgnoringFields(responsePost.toString(), responseGet.toString(), Set.of("limits"), true);
        } finally {
            // DELETE
            responseDelete = DELETE_DeleteBoard.deleteDeleteBoard(boardId);
            assertThat(responseDelete.statusCode()).isEqualTo(200);
            // assertJsonEqualsIgnoringFields(expectedDeleteBoardSuccessfulResponse, responseDelete.toString(), Set.of("name", "shortUrl", "url"), true);
        }
    }
}
