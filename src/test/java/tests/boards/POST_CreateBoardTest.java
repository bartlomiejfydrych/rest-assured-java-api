package tests.boards;

import base.TestBase;
import dto.boards.GET_GetBoardDto;
import dto.boards.POST_CreateBoardDto;
import endpoints.boards.DELETE_DeleteBoard;
import endpoints.boards.GET_GetBoard;
import endpoints.boards.POST_CreateBoard;
import expected_responses.boards.POST_CreateBoardExpected;
import org.junit.jupiter.api.Test;
import utils.responseUtils;

import java.io.File;
import java.nio.file.Paths;
import java.util.Collections;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static utils.ObjectComparator.compareObjectsWithIgnoredFields;

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
        boardId = responsePost.jsonPath().getString("id");
        try {
            responsePost.then().assertThat().body(matchesJsonSchema(postCreateBoardSchema));
            responsePost.then().body("name", equalTo(boardName));
            responsePost.then().body("limits", equalTo(Collections.emptyMap()));
            POST_CreateBoardDto expectedResponsePost = responseUtils.deserializeJson(
                    POST_CreateBoardExpected.P1ExpectedPostBoardResponse,
                    POST_CreateBoardDto.class
            );
            POST_CreateBoardDto actualResponsePost = responsePost.as(POST_CreateBoardDto.class);
            compareObjectsWithIgnoredFields(actualResponsePost, expectedResponsePost, "id", "name", "shortUrl", "url");
            // GET
            responseGet = GET_GetBoard.getGetBoard(boardId);
            assertThat(responseGet.statusCode()).isEqualTo(200);
            responseGet.then().assertThat().body(matchesJsonSchema(getGetBoardSchema));
            responseGet.then().body("name", equalTo(boardName));
            GET_GetBoardDto actualResponseGet = responseGet.as(GET_GetBoardDto.class);
            compareObjectsWithIgnoredFields(actualResponsePost, actualResponseGet, "limits");
        } finally {
            // DELETE
            responseDelete = DELETE_DeleteBoard.deleteDeleteBoard(boardId);
            assertThat(responseDelete.statusCode()).isEqualTo(200);
        }
    }
}
