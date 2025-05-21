package tests.boards;

import base.TestBase;
import dto.boards.GET_GetBoardDto;
import dto.boards.POST_CreateBoardDto;
import endpoints.boards.DELETE_DeleteBoard;
import endpoints.boards.GET_GetBoard;
import endpoints.boards.POST_CreateBoard;
import expected_responses.boards.POST_CreateBoardExpected;
import org.junit.jupiter.api.Test;
import utils.ResponseUtils;

import java.io.File;
import java.nio.file.Paths;
import java.util.Collections;

import static endpoints.boards.DELETE_DeleteBoard.deleteDeleteBoard;
import static endpoints.boards.GET_GetBoard.getGetBoard;
import static endpoints.boards.POST_CreateBoard.postCreateBoard;
import static expected_responses.boards.POST_CreateBoardExpected.P1ExpectedPostBoardResponse;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static java.util.Collections.emptyMap;
import static org.assertj.core.api.Assertions.assertThat;
import static utils.ObjectComparator.*;
import static utils.ResponseUtils.deserializeAndValidate;
import static utils.ResponseUtils.deserializeJson;

public class POST_CreateBoardTest extends TestBase {

    private String boardId;
    private final File postCreateBoardSchema = Paths.get(baseSchemaPath, "boards", "POST_create_board_schema.json").toFile();
    private final File getGetBoardSchema = Paths.get(baseSchemaPath, "boards", "GET_get_board.json").toFile();

    @Test
    public void P1_shouldCreateBoardWithOnlyRequiredParameters() {

        String boardName = faker.company().name() + " board" + " " + faker.number().randomNumber();

        // POST
        responsePost = postCreateBoard(boardName, null);
        assertThat(responsePost.statusCode()).isEqualTo(200);
        boardId = responsePost.jsonPath().getString("id");
        try {
            // responsePost.then().assertThat().body(matchesJsonSchema(postCreateBoardSchema));
            // POST_CreateBoardDto responsePostDto = responsePost.as(POST_CreateBoardDto.class);
            POST_CreateBoardDto responsePostDto = deserializeAndValidate(responsePost, POST_CreateBoardDto.class);
            assertThat(responsePostDto.name).isEqualTo(boardName);
            assertThat(responsePostDto.limits).isEqualTo(emptyMap());
            // responsePost.then().body("name", equalTo(boardName));
            // responsePost.then().body("limits", equalTo(emptyMap()));
            POST_CreateBoardDto expectedResponsePostDto = deserializeJson(P1ExpectedPostBoardResponse, POST_CreateBoardDto.class);
//            compareObjectsWithIgnoredFields(responsePostDto, expectedResponsePostDto,
//                    getters(getter(POST_CreateBoardDto::getId)),
//                    getters(getter(POST_CreateBoardDto::getName)),
//                    getters(getter(POST_CreateBoardDto::getShortUrl)),
//                    getters(getter(POST_CreateBoardDto::getUrl))
//            );
            // compareObjectsWithIgnoredFields(responsePostDto, expectedResponsePostDto, "id", "name", "shortUrl", "url");
            expectedResponsePostDto.id = responsePostDto.id;
            expectedResponsePostDto.name = responsePostDto.name;
            expectedResponsePostDto.shortUrl = responsePostDto.shortUrl;
            expectedResponsePostDto.url = responsePostDto.url;
            assertThat(responsePostDto).isEqualTo(expectedResponsePostDto);
            // GET
            responseGet = getGetBoard(boardId);
            assertThat(responseGet.statusCode()).isEqualTo(200);
            // responseGet.then().assertThat().body(matchesJsonSchema(getGetBoardSchema));
            // GET_GetBoardDto actualResponseGet = responseGet.as(GET_GetBoardDto.class);
            GET_GetBoardDto responseGetDto = deserializeAndValidate(responseGet, GET_GetBoardDto.class);
//            compareObjectsWithIgnoredFields(actualResponseGet, responsePostDto,
//                    getters(getter(POST_CreateBoardDto::getLimits))
//            );
            // responseGet.then().body("name", equalTo(boardName));
            // compareObjectsWithIgnoredFields(responsePostDto, actualResponseGet, "limits");
        } finally {
            // DELETE
            responseDelete = deleteDeleteBoard(boardId);
            assertThat(responseDelete.statusCode()).isEqualTo(200);
        }
    }

    @Test
    public void deleteBoard() {
        responseDelete = deleteDeleteBoard("6828686326ecb74053638a5e");
        assertThat(responseDelete.statusCode()).isEqualTo(200);
    }
}
