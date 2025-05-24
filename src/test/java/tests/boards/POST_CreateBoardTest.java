package tests.boards;

import base.TestBase;
import dto.boards.GET_GetBoardDto;
import dto.boards.POST_CreateBoardDto;
import endpoints.boards.DELETE_DeleteBoard;
import endpoints.boards.GET_GetBoard;
import endpoints.boards.POST_CreateBoard;
import expected_responses.boards.POST_CreateBoardExpected;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import utils.ResponseUtils;

import java.io.File;
import java.nio.file.Paths;
import java.util.Collections;

import static endpoints.boards.DELETE_DeleteBoard.deleteDeleteBoard;
import static endpoints.boards.GET_GetBoard.getGetBoard;
import static endpoints.boards.POST_CreateBoard.postCreateBoard;
import static expected_responses.boards.POST_CreateBoardExpected.P1ExpectedPostBoardResponse;
import static java.util.Collections.emptyMap;
import static org.assertj.core.api.Assertions.assertThat;
import static utils.ObjectComparator.*;
import static utils.ResponseUtils.deserializeAndValidate;
import static utils.ResponseUtils.deserializeJson;
import static utils_tests.boards.POST_CreateBoardUtils.prepareExpectedResponsePost;
import static utils_tests.boards.POST_CreateBoardUtils.validateGetBoardResponseAgainstPost;

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public class POST_CreateBoardTest extends TestBase {

    private String boardId;

    @AfterEach
    public void tearDown() {
        if (boardId != null) {
            responseDelete = deleteDeleteBoard(boardId);
            assertThat(responseDelete.statusCode()).isEqualTo(200);
        }
    }

    @Test
    public void OLD_P1_shouldCreateBoardWithOnlyRequiredParameters() {

        String boardName = faker.company().name() + " board" + " " + faker.number().randomNumber();

        // POST
        responsePost = postCreateBoard(boardName, null);
        assertThat(responsePost.statusCode()).isEqualTo(200);
        boardId = responsePost.jsonPath().getString("id");
        try {
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
            //        expectedResponsePostDto.name = boardName;
//        expectedResponsePostDto.id = responsePostDto.id;
//        expectedResponsePostDto.shortUrl = responsePostDto.shortUrl;
//        expectedResponsePostDto.url = responsePostDto.url;
            expectedResponsePostDto.id = responsePostDto.id;
            expectedResponsePostDto.name = responsePostDto.name;
            expectedResponsePostDto.shortUrl = responsePostDto.shortUrl;
            expectedResponsePostDto.url = responsePostDto.url;
            // assertThat(responsePostDto).isEqualTo(expectedResponsePostDto);
            assertThat(responsePostDto)
                    .usingRecursiveComparison()
                    .isEqualTo(expectedResponsePostDto);
            // GET
            responseGet = getGetBoard(boardId);
            assertThat(responseGet.statusCode()).isEqualTo(200);
            // GET_GetBoardDto actualResponseGet = responseGet.as(GET_GetBoardDto.class);
            GET_GetBoardDto responseGetDto = deserializeAndValidate(responseGet, GET_GetBoardDto.class);
            responsePostDto.limits = null;
            assertThat(responsePostDto)
                    .usingRecursiveComparison()
                    .ignoringFields(POST_CreateBoardDto.FIELD_LIMITS)
                    .isEqualTo(responseGetDto);
//            compareObjectsWithIgnoredFields(actualResponseGet, responsePostDto,
//                    getters(getter(POST_CreateBoardDto::getLimits))
//            );
            // responseGet.then().body("name", equalTo(boardName));
            // compareObjectsWithIgnoredFields(responsePostDto, actualResponseGet, "limits");
            //        responseGet = getGetBoard(boardId);
//        assertThat(responseGet.statusCode()).isEqualTo(200);
//        GET_GetBoardDto responseGetDto = deserializeAndValidate(responseGet, GET_GetBoardDto.class);
//        compareObjects(responsePostDto, responseGetDto, POST_CreateBoardDto.FIELD_LIMITS);
        } finally {
            // DELETE
            responseDelete = deleteDeleteBoard(boardId);
            assertThat(responseDelete.statusCode()).isEqualTo(200);
        }
    }

    @Test
    public void P1_shouldCreateBoardWithOnlyRequiredParameters() {

        String boardName = faker.company().name() + " board" + " " + faker.number().randomNumber();

        // POST
        responsePost = postCreateBoard(boardName, null);
        assertThat(responsePost.statusCode()).isEqualTo(200);
        boardId = responsePost.jsonPath().getString("id");
        POST_CreateBoardDto responsePostDto = deserializeAndValidate(responsePost, POST_CreateBoardDto.class);
        POST_CreateBoardDto expectedResponsePostDto = prepareExpectedResponsePost(P1ExpectedPostBoardResponse, responsePostDto, boardName);
        compareObjects(responsePostDto, expectedResponsePostDto);
        // GET
        validateGetBoardResponseAgainstPost(responsePostDto);
    }

    @Test
    public void deleteBoard() {
        responseDelete = deleteDeleteBoard("6828686326ecb74053638a5e");
        assertThat(responseDelete.statusCode()).isEqualTo(200);
    }
}
