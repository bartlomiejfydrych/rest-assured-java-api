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
import static endpoints.lists.POST_CreateNewList.postCreateNewListAnyParams;
import static expected_responses.lists.POST_CreateNewListExpected.*;
import static org.assertj.core.api.Assertions.assertThat;
import static utils.UtilsCommon.getAllCharactersSetInRandomOrder;
import static utils.UtilsCommon.getRandomSingleChar;
import static utils.UtilsCompare.compareObjects;
import static utils.UtilsCompare.compareObjectsJsonNode;
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
        Response responsePost1 = postCreateNewList(boardId, listName1, null);
        assertThat(responsePost1.statusCode()).isEqualTo(200);
        POST_CreateNewListDto responsePostDto1 = deserializeAndValidate(responsePost1, POST_CreateNewListDto.class);
        Long responsePostPos1 = responsePostDto1.pos;
        // POST (add list 2)
        Response responsePost2 = postCreateNewList(boardId, listName2, queryParams2);
        assertThat(responsePost2.statusCode()).isEqualTo(200);
        POST_CreateNewListDto responsePostDto2 = deserializeAndValidate(responsePost2, POST_CreateNewListDto.class);
        Long responsePostPos2 = responsePostDto2.pos;
        POST_CreateNewListDto expectedResponsePostDto2 = prepareExpectedResponsePost(
                P4ExpectedPostNewListResponse2,
                responsePostDto2,
                listName2,
                boardId,
                responsePostDto2.pos
        );
        compareObjects(responsePostDto2, expectedResponsePostDto2);
        // GET
        validateGetAgainstPost(responsePostDto2);
        // POST (add list 3)
        Response responsePost3 = postCreateNewList(boardId, listName3, queryParams3);
        assertThat(responsePost3.statusCode()).isEqualTo(200);
        POST_CreateNewListDto responsePostDto3 = deserializeAndValidate(responsePost3, POST_CreateNewListDto.class);
        Long responsePostPos3 = responsePostDto3.pos;
        POST_CreateNewListDto expectedResponsePostDto3 = prepareExpectedResponsePost(
                P4ExpectedPostNewListResponse3,
                responsePostDto3,
                listName3,
                boardId,
                responsePostDto3.pos
        );
        compareObjects(responsePostDto3, expectedResponsePostDto3);
        // GET
        validateGetAgainstPost(responsePostDto3);
        // POST (add list 4)
        Response responsePost4 = postCreateNewList(boardId, listName4, queryParams4);
        assertThat(responsePost4.statusCode()).isEqualTo(200);
        POST_CreateNewListDto responsePostDto4 = deserializeAndValidate(responsePost4, POST_CreateNewListDto.class);
        Long responsePostPos4 = responsePostDto4.pos;
        POST_CreateNewListDto expectedResponsePostDto4 = prepareExpectedResponsePost(
                P4ExpectedPostNewListResponse4,
                responsePostDto4,
                listName4,
                boardId,
                responsePostDto4.pos
        );
        compareObjects(responsePostDto4, expectedResponsePostDto4);
        // GET
        validateGetAgainstPost(responsePostDto4);
        // POSITION VALIDATION
        assertThat(responsePostPos2)
                .as("The list with the \"top\" position should be higher (i.e. have a lower numerical value) than the first list.")
                .isLessThan(responsePostPos1);
        assertThat(responsePostPos3)
                .as("The list with the \"bottom\" position should be lower (i.e. have a higher numerical value) than the first list.")
                .isGreaterThan(responsePostPos1);
        assertThat(responsePostPos4)
                .as("The list with the \"numeric\" item should be higher (i.e. have a lower numerical value) than the first list.")
                .isLessThan(responsePostPos1);
    }

    // Other test cases

    @Test
    public void P5_shouldCreateNewListWhereOtherParametersAreEmptyStrings() {

        listName = generateRandomListName();
        listIdListSource = "";
        listPos = "";
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
                P5ExpectedPostNewListResponse,
                responsePostDto,
                listName,
                boardId,
                responsePostDto.pos
        );
        compareObjects(responsePostDto, expectedResponsePostDto);
        // GET
        validateGetAgainstPost(responsePostDto);
    }

    // --------------
    // NEGATIVE TESTS
    // --------------

    // name

    @Test
    public void N1_shouldNotCreateNewListWhenNameIsMissing() {

        POST_CreateNewListPayload payload = new POST_CreateNewListPayload.Builder()
                .setIdBoard(boardId)
                .build();
        Map<String, Object> queryParams = payload.toQueryParams();

        responsePost = postCreateNewListAnyParams(queryParams);
        assertThat(responsePost.statusCode()).isEqualTo(400);
        assertThat(responsePost.getBody().asString()).isEqualTo(expectedPostNewListResponseInvalidName);
    }

    @Test
    public void N2_shouldNotCreateNewListWhenNameIsNull() {

        listName = null;

        responsePost = postCreateNewList(boardId, listName, null);
        assertThat(responsePost.statusCode()).isEqualTo(400);
        assertThat(responsePost.getBody().asString()).isEqualTo(expectedPostNewListResponseInvalidName);
    }

    @Test
    public void N3_shouldNotCreateNewListWhenNameIsEmptyString() {

        listName = "";

        responsePost = postCreateNewList(boardId, listName, null);
        assertThat(responsePost.statusCode()).isEqualTo(400);
        assertThat(responsePost.getBody().asString()).isEqualTo(expectedPostNewListResponseInvalidName);
    }

    // idBoard

    @Test
    public void N4_shouldNotCreateNewListWhenIdBoardIsMissing() {

        POST_CreateNewListPayload paylod = new POST_CreateNewListPayload.Builder()
                .setName(generateRandomListName())
                .build();
        Map<String, Object> queryParams = paylod.toQueryParams();

        responsePost = postCreateNewListAnyParams(queryParams);
        assertThat(responsePost.statusCode()).isEqualTo(400);
        assertThat(responsePost.getBody().asString()).isEqualTo(expectedPostNewListResponseInvalidIdBoard);
    }

    @Test
    public void N5_shouldNotCreateNewListWhenIdBoardIsNull() {

        String idBoard = null;
        listName = generateRandomListName();

        responsePost = postCreateNewList(idBoard, listName, null);
        assertThat(responsePost.statusCode()).isEqualTo(400);
        assertThat(responsePost.getBody().asString()).isEqualTo(expectedPostNewListResponseInvalidIdBoard);
    }

    @Test
    public void N6_shouldNotCreateNewListWhenIdBoardIsEmptyString() {

        String idBoard = "";
        listName = generateRandomListName();

        responsePost = postCreateNewList(idBoard, listName, null);
        assertThat(responsePost.statusCode()).isEqualTo(400);
        assertThat(responsePost.getBody().asString()).isEqualTo(expectedPostNewListResponseInvalidIdBoard);
    }

    @Test
    public void N7_shouldNotCreateNewListWhenIdBoardIsNonExistent() {

        String idBoard = "999999999999999999999999";
        listName = generateRandomListName();

        responsePost = postCreateNewList(idBoard, listName, null);
        assertThat(responsePost.statusCode()).isEqualTo(401);
        assertThat(responsePost.getBody().asString()).isEqualTo("unauthorized board list requested " + idBoard);
    }

    @Test
    public void N8_shouldNotCreateNewListWhenIdBoardIsIncorrect() {

        String idBoard = "KeK 123";
        listName = generateRandomListName();

        responsePost = postCreateNewList(idBoard, listName, null);
        assertThat(responsePost.statusCode()).isEqualTo(400);
        assertThat(responsePost.getBody().asString()).isEqualTo(expectedPostNewListResponseInvalidIdBoard);
    }

    // idListSource

    @Test
    public void N9_shouldNotCreateNewListWhenIdListSourceIsNonExistent() {

        listName = generateRandomListName();
        listIdListSource = "999999999999999999999999";

        POST_CreateNewListPayload paylod = new POST_CreateNewListPayload.Builder()
                .setIdListSource(listIdListSource)
                .build();
        Map<String, Object> queryParams = paylod.toQueryParams();

        responsePost = postCreateNewList(boardId, listName, queryParams);
        assertThat(responsePost.statusCode()).isEqualTo(404);
        assertThat(responsePost.getBody().asString()).isEqualTo("List not found");
    }

    @Test
    public void N10_shouldNotCreateNewListWhenIdListSourceIsIncorrect() {

        listName = generateRandomListName();
        listIdListSource = "KeK 123";

        POST_CreateNewListPayload paylod = new POST_CreateNewListPayload.Builder()
                .setIdListSource(listIdListSource)
                .build();
        Map<String, Object> queryParams = paylod.toQueryParams();

        responsePost = postCreateNewList(boardId, listName, queryParams);
        assertThat(responsePost.statusCode()).isEqualTo(400);
        compareObjectsJsonNode(responsePost, N10ExpectedPostNewListResponse);
    }

    // pos

    @Test
    public void N11_shouldNotCreateNewListWhenPosIsIncorrect() {

        listName = generateRandomListName();
        listPos = "Kek 123";

        POST_CreateNewListPayload paylod = new POST_CreateNewListPayload.Builder()
                .setPos(listPos)
                .build();
        Map<String, Object> queryParams = paylod.toQueryParams();

        responsePost = postCreateNewList(boardId, listName, queryParams);
        assertThat(responsePost.statusCode()).isEqualTo(400);
        compareObjectsJsonNode(responsePost, N11ExpectedPostNewListResponse);
    }

    /*
    NOTE:
    According to the documentation, the specific position of list should be of type Number.
    A String value will also work.

    @Test
    public void N12_shouldNotCreateNewListWhenPosIsNumberAsString() {

        listName = generateRandomListName();
        listPos = "140737488322560";

        POST_CreateNewListPayload paylod = new POST_CreateNewListPayload.Builder()
                .setPos(listPos)
                .build();
        Map<String, Object> queryParams = paylod.toQueryParams();

        responsePost = postCreateNewList(boardId, listName, queryParams);
        assertThat(responsePost.statusCode()).isEqualTo(400);
        compareObjectsJsonNode(responsePost, N11ExpectedPostNewListResponse);
    }
    */
}
