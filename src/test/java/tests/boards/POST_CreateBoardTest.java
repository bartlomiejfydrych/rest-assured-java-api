package tests.boards;

import base.TestBase;
import dto.boards.POST_CreateBoardDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static endpoints.boards.DELETE_DeleteBoard.deleteDeleteBoard;
import static endpoints.boards.POST_CreateBoard.postCreateBoard;
import static expected_responses.boards.POST_CreateBoardExpected.P1ExpectedPostBoardResponse;
import static org.assertj.core.api.Assertions.assertThat;
import static utils.UtilsCompare.*;
import static utils.UtilsResponse.deserializeAndValidate;
import static utils_tests.boards.POST_CreateBoardUtils.*;

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

    // --------------
    // POSITIVE TESTS
    // --------------

    @Test
    public void P1_shouldCreateBoardWhoseNameContainsSpecialCharactersAndNumbers() {

        String boardName = "ŻÓŁĆżółć 1234567890 ~`!@#$%^&*()_+{}[];':\",./<>?-=\\" + " " + faker.number().randomNumber();

        // POST
        responsePost = postCreateBoard(boardName, null);
        assertThat(responsePost.statusCode()).isEqualTo(200);
        boardId = responsePost.jsonPath().getString("id");
        POST_CreateBoardDto responsePostDto = deserializeAndValidate(responsePost, POST_CreateBoardDto.class);
        POST_CreateBoardDto expectedResponsePostDto = prepareExpectedResponsePost(P1ExpectedPostBoardResponse, responsePostDto, boardName);
        compareObjects(responsePostDto, expectedResponsePostDto);
        // GET
        validateGetAgainstPost(responsePostDto);
    }

    // --------------
    // NEGATIVE TESTS
    // --------------

    // -----
    // DEBUG
    // -----

//    @Test
//    public void deleteBoard() {
//        responseDelete = deleteDeleteBoard("6828686326ecb74053638a5e");
//        assertThat(responseDelete.statusCode()).isEqualTo(200);
//    }
}
