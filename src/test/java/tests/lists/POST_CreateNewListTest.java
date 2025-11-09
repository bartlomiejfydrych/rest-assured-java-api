package tests.lists;

import base.TestBase;
import dto.lists.POST_CreateNewListDto;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import payloads.lists.POST_CreateNewListPayload;

import java.util.Map;

import static endpoints.boards.DELETE_DeleteBoard.deleteDeleteBoard;
import static endpoints.boards.POST_CreateBoard.postCreateBoard;
import static endpoints.lists.POST_CreateNewList.postCreateNewList;
import static expected_responses.lists.POST_CreateNewListExpected.*;
import static org.assertj.core.api.Assertions.assertThat;
import static utils.UtilsCommon.getAllCharactersSetInRandomOrder;
import static utils.UtilsCommon.getRandomSingleChar;
import static utils.UtilsCompare.compareObjects;
import static utils.UtilsResponse.deserializeAndValidate;
import static utils_tests.boards.POST_CreateBoardUtils.generateRandomBoardName;
import static utils_tests.lists.POST_CreateNewListUtils.*;

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

    // name

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

    @Test
    public void P2_shouldCreateNewListWhereNameHaveOnlyOneCharacterAndOtherParametersAreNull() {

        listName = getRandomSingleChar();
        listIdListSource = null;
        listPos = null;
        POST_CreateNewListPayload payload = new POST_CreateNewListPayload.Builder()
                .setIdListSource(listIdListSource)
                .setPos(listPos)
                .build();
        Map<String, Object> queryParams = payload.toQueryParams();

        // POST
        responsePost = postCreateNewList(boardId, listName, queryParams);
        assertThat(responsePost.statusCode()).isEqualTo(200);
        POST_CreateNewListDto responsePostDto = deserializeAndValidate(responsePost, POST_CreateNewListDto.class);
        POST_CreateNewListDto expectedResponsePostDto = prepareExpectedResponsePost(
                P2ExpectedPostNewListResponse,
                responsePostDto,
                listName,
                boardId,
                responsePostDto.pos
        );
        compareObjects(responsePostDto, expectedResponsePostDto);
        // GET
        validateGetAgainstPost(responsePostDto);
    }

    // idListSource

    @Test
    public void P3_shouldCreateNewListWithCorrectIdListSource() {

        String listName1 = generateRandomListName();
        String listName2 = generateRandomListName();

        // POST (add list 1)
        Response responsePost1 = postCreateNewList(boardId, listName1, null);
        assertThat(responsePost1.statusCode()).isEqualTo(200);
        POST_CreateNewListDto responsePostDto1 = deserializeAndValidate(responsePost1, POST_CreateNewListDto.class);

        // POST (add list 2)
        POST_CreateNewListPayload payload = new POST_CreateNewListPayload.Builder()
                .setIdListSource(responsePostDto1.id)
                .build();
        Map<String, Object> queryParams = payload.toQueryParams();

        Response responsePost2 = postCreateNewList(boardId, listName2, queryParams);
        assertThat(responsePost2.statusCode()).isEqualTo(200);
        POST_CreateNewListDto responsePostDto2 = deserializeAndValidate(responsePost2, POST_CreateNewListDto.class);
        POST_CreateNewListDto expectedResponsePostDto2 = prepareExpectedResponsePost(
                P3ExpectedPostNewListResponse,
                responsePostDto2,
                listName2,
                boardId,
                responsePostDto2.pos
        );
        compareObjects(responsePostDto2, expectedResponsePostDto2);
        // GET
        validateGetAgainstPost(responsePostDto2);
    }

    // pos

    @Test
    public void P4_shouldCreateThreeNewListsWithPosTopBottomAndNumber() {

        String listName1 = generateRandomListName(); // Base list against which the position of the remaining lists will be checked
        String listName2 = generateRandomListName();
        String listName3 = generateRandomListName();
        String listName4 = generateRandomListName();
        String listPos2 = "top";
        String listPos3 = "bottom";
        Long listPos4 = 140737488322560L;

        POST_CreateNewListPayload payload2 = new POST_CreateNewListPayload.Builder()
                .setPos(listPos2)
                .build();
        Map<String, Object> queryParams2 = payload2.toQueryParams();

        POST_CreateNewListPayload payload3 = new POST_CreateNewListPayload.Builder()
                .setPos(listPos3)
                .build();
        Map<String, Object> queryParams3 = payload3.toQueryParams();

        POST_CreateNewListPayload payload4 = new POST_CreateNewListPayload.Builder()
                .setPos(listPos4)
                .build();
        Map<String, Object> queryParams4 = payload4.toQueryParams();

        // POST (add list 1)

    }

    // Other test cases

    // Skopiować i przerobić P2

}
