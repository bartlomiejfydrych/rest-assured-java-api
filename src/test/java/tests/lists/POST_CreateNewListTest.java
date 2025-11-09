package tests.lists;

import base.TestBase;
import dto.lists.POST_CreateNewListDto;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static endpoints.boards.DELETE_DeleteBoard.deleteDeleteBoard;
import static endpoints.boards.POST_CreateBoard.postCreateBoard;
import static endpoints.lists.POST_CreateNewList.postCreateNewList;
import static expected_responses.lists.POST_CreateNewListExpected.P1ExpectedPostNewListResponse;
import static org.assertj.core.api.Assertions.assertThat;
import static utils.UtilsCommon.getAllCharactersSetInRandomOrder;
import static utils.UtilsCompare.compareObjects;
import static utils.UtilsResponse.deserializeAndValidate;
import static utils_tests.boards.POST_CreateBoardUtils.generateRandomBoardName;
import static utils_tests.lists.POST_CreateNewListUtils.prepareExpectedResponsePost;
import static utils_tests.lists.POST_CreateNewListUtils.validateGetAgainstPost;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class POST_CreateNewListTest extends TestBase {

    private String boardId;
    private String listName;
    private String listIdListSource;
    private String listPos;

    @BeforeAll
    public void setUpCreateBoard() {
        responsePost = postCreateBoard(generateRandomBoardName(), null);
        assertThat(responsePost.statusCode()).isEqualTo(200);
        boardId = responsePost.getBody().jsonPath().getString("id");
    }

    @AfterAll
    public void tearDownDeleteBoard() {
        if (boardId != null) {
            responseDelete = deleteDeleteBoard(boardId);
            assertThat(responseDelete.statusCode()).isEqualTo(200);
            boardId = null;
        }
    }

    // --------------
    // POSITIVE TESTS
    // --------------

    @Test
    public void P1_shouldCreateNewListWhereNameIsWithSpecialCharactersAndNumbers() {

        listName = getAllCharactersSetInRandomOrder();

        // POST
        responsePost = postCreateNewList(boardId, listName, null);
        assertThat(responsePost.statusCode()).isEqualTo(200);
        POST_CreateNewListDto responsePostDto = deserializeAndValidate(responsePost, POST_CreateNewListDto.class);
        POST_CreateNewListDto expectedResponsePostDto = prepareExpectedResponsePost(
                P1ExpectedPostNewListResponse,
                responsePostDto,
                listName,
                boardId,
                responsePostDto.pos
        );
        compareObjects(responsePostDto, expectedResponsePostDto);
        // GET
        validateGetAgainstPost(responsePostDto);
    }
}
