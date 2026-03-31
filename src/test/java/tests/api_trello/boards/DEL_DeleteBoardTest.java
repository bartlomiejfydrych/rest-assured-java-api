package tests.api_trello.boards;

import dto.boards.POST_CreateBoardDto;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import tests.base.TestBase;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static endpoints.boards.DEL_DeleteBoardEndpoint.*;
import static endpoints.boards.GET_GetBoardEndpoint.getGetBoard;
import static endpoints.boards.POST_CreateBoardEndpoint.postCreateBoard;
import static org.assertj.core.api.Assertions.assertThat;
import static utils.UtilsCompare.compareResponseWithJson;
import static utils.response.UtilsResponseDeserializer.deserializeJson;
import static utils_tests.boards.POST_CreateBoardUtils.generateRandomBoardName;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DEL_DeleteBoardTest extends TestBase {

    // ==========================================================================================================
    // FIELDS
    // ==========================================================================================================

    // --------
    // RESPONSE
    // --------

    private Response responsePost;
    private Response responseGet;
    private Response responseDelete;

    // ---------------
    // CLASS VARIABLES
    // ---------------

    // BOARD
    private String boardId;

    // ==========================================================================================================
    // SETUP & TEARDOWN
    // ==========================================================================================================

    // ----------
    // BEFORE ALL
    // ----------

    @BeforeAll
    public void setUpCreateBoard() {
        responsePost = postCreateBoard(generateRandomBoardName(), null);
        assertThat(responsePost.statusCode()).isEqualTo(200);
        POST_CreateBoardDto responsePostDto = deserializeJson(responsePost, POST_CreateBoardDto.class);
        boardId = responsePostDto.id;
    }

    // ---------
    // AFTER ALL
    // ---------

    @AfterAll
    public void tearDownDeleteBoard() {
        if (boardId != null) {
            responseDelete = deleteDeleteBoard(boardId);
            assertThat(responseDelete.statusCode()).isEqualTo(200);
            boardId = null;
        }
    }

    // ==========================================================================================================
    // POSITIVE TESTS
    // ==========================================================================================================

    @Test
    public void P1_shouldDeleteBoard() {

        String expectedResponse = """
                {
                    "_value": null
                }
                """;

        // POST
        responsePost = postCreateBoard(generateRandomBoardName(), null);
        assertThat(responsePost.statusCode()).isEqualTo(200);
        String boardId = responsePost.getBody().jsonPath().getString("id");
        // DELETE
        responseDelete = deleteDeleteBoard(boardId);
        assertThat(responseDelete.statusCode()).isEqualTo(200);
        compareResponseWithJson(responseDelete, expectedResponse);
        // GET
        responseGet = getGetBoard(boardId);
        assertThat(responseGet.statusCode()).isEqualTo(404);
        assertThat(responseGet.getBody().asString()).isEqualTo("The requested resource was not found.");
    }

    // ==========================================================================================================
    // NEGATIVE TESTS
    // ==========================================================================================================

    @Test
    public void N1_shouldNotDeleteBoardWhenApiKeyIsMissing() {
        // ACT
        responseDelete = deleteDeleteBoardWithoutApiKey(boardId);
        // ASSERT
        assertThat(responseDelete.statusCode()).isEqualTo(401);
        assertThat(responseDelete.getBody().asString()).isEqualTo("invalid key");
    }

    @Test
    public void N2_shouldNotDeleteBoardWhenTokenIsMissing() {
        // ARRANGE
        String expectedResponse = """
                {
                  "message": "missing scopes"
                }
                """;
        // ACT
        responseDelete = deleteDeleteBoardWithoutToken(boardId);
        // ASSERT
        assertThat(responseDelete.statusCode()).isEqualTo(401);
        compareResponseWithJson(responseDelete, expectedResponse);
    }

    @Test
    public void N3_shouldNotDeleteBoardWithIdDoesNotHaveAccessTo() {
        // ARRANGE
        String boardId = "5f5127e8f150fe5f98bb1267";
        // ACT
        responseDelete = deleteDeleteBoard(boardId);
        // ASSERT
        assertThat(responseDelete.statusCode()).isEqualTo(401);
        assertThat(responseDelete.getBody().asString()).isEqualTo("unauthorized permission requested");
    }

    @Test
    public void N4_shouldNotDeleteNonExistentBoard() {
        // ARRANGE
        String boardId = "68063bdc4bdbd152d658851a";
        // ACT
        responseDelete = deleteDeleteBoard(boardId);
        // ASSERT
        assertThat(responseDelete.statusCode()).isEqualTo(404);
        assertThat(responseDelete.getBody().asString()).isEqualTo("The requested resource was not found.");
    }
}
