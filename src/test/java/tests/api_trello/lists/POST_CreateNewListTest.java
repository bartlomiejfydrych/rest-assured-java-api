package tests.api_trello.lists;

import base.TestBase;
import dto.lists.POST_CreateNewListDto;
import expected_responses.lists.POST_CreateNewListExpected;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import payloads.lists.POST_CreateNewListPayload;
import utils.UtilsString;

import static endpoints.boards.DEL_DeleteBoardEndpoint.deleteDeleteBoard;
import static endpoints.boards.POST_CreateBoardEndpoint.postCreateBoard;
import static endpoints.lists.POST_CreateNewListEndpoint.postCreateNewList;
import static endpoints.lists.POST_CreateNewListEndpoint.postCreateNewListWithAnyParams;
import static expected_responses.lists.POST_CreateNewListExpected.*;
import static org.assertj.core.api.Assertions.assertThat;
import static utils.UtilsCompare.compareObjects;
import static utils.UtilsCompare.compareResponseWithJson;
import static utils.UtilsString.getAllCharactersSetInRandomOrder;
import static utils.response.UtilsResponseDeserializer.deserializeAndValidateJson;
import static utils_tests.boards.POST_CreateBoardUtils.generateRandomBoardName;
import static utils_tests.lists.POST_CreateNewListUtils.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class POST_CreateNewListTest extends TestBase {

    // ==========================================================================================================
    // FIELDS
    // ==========================================================================================================

    // --------
    // RESPONSE
    // --------

    private Response responsePost;
    private Response responseDelete;

    // ---------------
    // CLASS VARIABLES
    // ---------------

    // BOARD
    private String boardId;
    // LIST
    private String listName;
    private String listIdListSource;
    private String listPos;

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
        boardId = responsePost.getBody().jsonPath().getString("id");
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
    public void P1_shouldCreateNewListWhereNameIsWithSpecialCharactersAndNumbers() {

        listName = getAllCharactersSetInRandomOrder();

        // POST
        responsePost = postCreateNewList(boardId, listName, null);
        assertThat(responsePost.statusCode()).isEqualTo(200);
        POST_CreateNewListDto responsePostDto = deserializeAndValidateJson(responsePost, POST_CreateNewListDto.class);
        POST_CreateNewListDto expectedResponsePostDto =
                POST_CreateNewListExpected.base()
                        .withId(responsePostDto.id)
                        .withName(listName)
                        .withBoardId(boardId)
                        .withPos(responsePostDto.pos)
                        .build();
        compareObjects(responsePostDto, expectedResponsePostDto);
        // GET
        validateGetAgainstPost(responsePostDto);
    }

    @Test
    public void P2_shouldCreateNewListWhereNameHaveOnlyOneCharacterAndOtherParametersAreNull() {

        listName = UtilsString.getRandomSingleCharAlphanumeric();
        listIdListSource = null;
        listPos = null;

        POST_CreateNewListPayload payload = new POST_CreateNewListPayload.Builder()
                .setIdListSource(listIdListSource)
                .setPos(listPos)
                .build();

        // POST
        responsePost = postCreateNewList(boardId, listName, payload);
        assertThat(responsePost.statusCode()).isEqualTo(200);
        POST_CreateNewListDto responsePostDto = deserializeAndValidateJson(responsePost, POST_CreateNewListDto.class);
        POST_CreateNewListDto expectedResponsePostDto =
                POST_CreateNewListExpected.base()
                        .withId(responsePostDto.id)
                        .withName(listName)
                        .withBoardId(boardId)
                        .withPos(responsePostDto.pos)
                        .build();
        compareObjects(responsePostDto, expectedResponsePostDto);
        // GET
        validateGetAgainstPost(responsePostDto);
    }

    @Test
    public void P3_shouldCreateNewListWithCorrectIdListSource() {

        String listName1 = generateRandomListName();
        String listName2 = generateRandomListName();

        // POST (Add {LIST 1})
        Response responsePost1 = postCreateNewList(boardId, listName1, null);
        assertThat(responsePost1.statusCode()).isEqualTo(200);
        POST_CreateNewListDto responsePostDto1 = deserializeAndValidateJson(responsePost1, POST_CreateNewListDto.class);

        // POST (Add {LIST 2} and assign it {idListSource} from {LIST 1})
        POST_CreateNewListPayload payload = new POST_CreateNewListPayload.Builder()
                .setIdListSource(responsePostDto1.id)
                .build();
        Response responsePost2 = postCreateNewList(boardId, listName2, payload);
        assertThat(responsePost2.statusCode()).isEqualTo(200);
        POST_CreateNewListDto responsePostDto2 = deserializeAndValidateJson(responsePost2, POST_CreateNewListDto.class);
        POST_CreateNewListDto expectedResponsePostDto2 =
                POST_CreateNewListExpected.base()
                        .withId(responsePostDto2.id)
                        .withName(listName2)
                        .withBoardId(boardId)
                        .withPos(responsePostDto2.pos)
                        .build();
        compareObjects(responsePostDto2, expectedResponsePostDto2);
        // GET
        validateGetAgainstPost(responsePostDto2);
    }

    @Test
    public void P4_shouldCreateThreeNewListsWithPosTopBottomAndNumber() {

        // -------
        // ARRANGE
        // -------

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
        POST_CreateNewListPayload payload3 = new POST_CreateNewListPayload.Builder()
                .setPos(listPos3)
                .build();
        POST_CreateNewListPayload payload4 = new POST_CreateNewListPayload.Builder()
                .setPos(listPos4)
                .build();

        // ---
        // ACT
        // ---

        // POST (Add {LIST 1})
        Response responsePost1 = postCreateNewList(boardId, listName1, null);
        assertThat(responsePost1.statusCode()).isEqualTo(200);
        POST_CreateNewListDto responsePostDto1 = deserializeAndValidateJson(responsePost1, POST_CreateNewListDto.class);
        Long responsePostPos1 = responsePostDto1.pos;

        // POST (Add {LIST 2})
        Response responsePost2 = postCreateNewList(boardId, listName2, payload2);
        assertThat(responsePost2.statusCode()).isEqualTo(200);
        POST_CreateNewListDto responsePostDto2 = deserializeAndValidateJson(responsePost2, POST_CreateNewListDto.class);
        Long responsePostPos2 = responsePostDto2.pos;
        POST_CreateNewListDto expectedResponsePostDto2 =
                POST_CreateNewListExpected.base()
                        .withId(responsePostDto2.id)
                        .withName(listName2)
                        .withBoardId(boardId)
                        .withPos(responsePostDto2.pos)
                        .build();
        compareObjects(responsePostDto2, expectedResponsePostDto2);
        // GET
        validateGetAgainstPost(responsePostDto2);

        // POST (Add {LIST 3})
        Response responsePost3 = postCreateNewList(boardId, listName3, payload3);
        assertThat(responsePost3.statusCode()).isEqualTo(200);
        POST_CreateNewListDto responsePostDto3 = deserializeAndValidateJson(responsePost3, POST_CreateNewListDto.class);
        Long responsePostPos3 = responsePostDto3.pos;
        POST_CreateNewListDto expectedResponsePostDto3 =
                POST_CreateNewListExpected.base()
                        .withId(responsePostDto3.id)
                        .withName(listName3)
                        .withBoardId(boardId)
                        .withPos(responsePostDto3.pos)
                        .build();
        compareObjects(responsePostDto3, expectedResponsePostDto3);
        // GET
        validateGetAgainstPost(responsePostDto3);

        // POST (Add {LIST 4})
        Response responsePost4 = postCreateNewList(boardId, listName4, payload4);
        assertThat(responsePost4.statusCode()).isEqualTo(200);
        POST_CreateNewListDto responsePostDto4 = deserializeAndValidateJson(responsePost4, POST_CreateNewListDto.class);
        Long responsePostPos4 = responsePostDto4.pos;
        POST_CreateNewListDto expectedResponsePostDto4 =
                POST_CreateNewListExpected.base()
                        .withId(responsePostDto4.id)
                        .withName(listName4)
                        .withBoardId(boardId)
                        .withPos(responsePostDto4.pos)
                        .build();
        compareObjects(responsePostDto4, expectedResponsePostDto4);
        // GET
        validateGetAgainstPost(responsePostDto4);

        // ------
        // ASSERT
        // ------

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

    @Test
    public void P5_shouldCreateNewListWhereOtherParametersAreEmptyStrings() {

        listName = generateRandomListName();
        listIdListSource = "";
        listPos = "";

        POST_CreateNewListPayload payload = new POST_CreateNewListPayload.Builder()
                .setIdListSource(listIdListSource)
                .setPos(listPos)
                .build();

        // POST
        responsePost = postCreateNewList(boardId, listName, payload);
        assertThat(responsePost.statusCode()).isEqualTo(200);
        POST_CreateNewListDto responsePostDto = deserializeAndValidateJson(responsePost, POST_CreateNewListDto.class);
        POST_CreateNewListDto expectedResponsePostDto =
                POST_CreateNewListExpected.base()
                        .withId(responsePostDto.id)
                        .withName(listName)
                        .withBoardId(boardId)
                        .withPos(responsePostDto.pos)
                        .build();
        compareObjects(responsePostDto, expectedResponsePostDto);
        // GET
        validateGetAgainstPost(responsePostDto);
    }

    @Test
    public void P6_shouldCreateNewListWhenPosIsNumberAsString() {
        /*
        NOTE:
        According to the documentation, the specific position of list should be of type Number.
        A String value will also work.
        */

        listName = generateRandomListName();
        listPos = "140737488326656";

        POST_CreateNewListPayload payload = new POST_CreateNewListPayload.Builder()
                .setPos(listPos)
                .build();

        // POST
        responsePost = postCreateNewList(boardId, listName, payload);
        assertThat(responsePost.statusCode()).isEqualTo(200);
        POST_CreateNewListDto responsePostDto = deserializeAndValidateJson(responsePost, POST_CreateNewListDto.class);
        POST_CreateNewListDto expectedResponsePostDto =
                POST_CreateNewListExpected.base()
                        .withId(responsePostDto.id)
                        .withName(listName)
                        .withBoardId(boardId)
                        .withPos(Long.parseLong(listPos))
                        .build();
        compareObjects(responsePostDto, expectedResponsePostDto);
        // GET
        validateGetAgainstPost(responsePostDto);
    }

    // ==========================================================================================================
    // NEGATIVE TESTS
    // ==========================================================================================================

    // ----
    // name
    // ----

    @Test
    public void N1_shouldNotCreateNewListWhenNameIsMissing() {
        // ARRANGE
        POST_CreateNewListPayload payload = new POST_CreateNewListPayload.Builder()
                .setIdBoard(boardId)
                .build();
        // ACT
        responsePost = postCreateNewListWithAnyParams(payload);
        // ASSERT
        assertThat(responsePost.statusCode()).isEqualTo(400);
        assertThat(responsePost.getBody().asString()).isEqualTo(expectedPostNewListResponseInvalidName);
    }

    @Test
    public void N2_shouldNotCreateNewListWhenNameIsNull() {
        // ARRANGE
        listName = null;
        // ACT
        responsePost = postCreateNewList(boardId, listName, null);
        // ASSERT
        assertThat(responsePost.statusCode()).isEqualTo(400);
        assertThat(responsePost.getBody().asString()).isEqualTo(expectedPostNewListResponseInvalidName);
    }

    @Test
    public void N3_shouldNotCreateNewListWhenNameIsEmptyString() {
        // ARRANGE
        listName = "";
        // ACT
        responsePost = postCreateNewList(boardId, listName, null);
        // ASSERT
        assertThat(responsePost.statusCode()).isEqualTo(400);
        assertThat(responsePost.getBody().asString()).isEqualTo(expectedPostNewListResponseInvalidName);
    }

    // -------
    // idBoard
    // -------

    @Test
    public void N4_shouldNotCreateNewListWhenIdBoardIsMissing() {
        // ARRANGE
        POST_CreateNewListPayload payload = new POST_CreateNewListPayload.Builder()
                .setName(generateRandomListName())
                .build();
        // ACT
        responsePost = postCreateNewListWithAnyParams(payload);
        // ASSERT
        assertThat(responsePost.statusCode()).isEqualTo(400);
        assertThat(responsePost.getBody().asString()).isEqualTo(expectedPostNewListResponseInvalidIdBoard);
    }

    @Test
    public void N5_shouldNotCreateNewListWhenIdBoardIsNull() {
        // ARRANGE
        String idBoard = null;
        listName = generateRandomListName();
        // ACT
        responsePost = postCreateNewList(idBoard, listName, null);
        // ASSERT
        assertThat(responsePost.statusCode()).isEqualTo(400);
        assertThat(responsePost.getBody().asString()).isEqualTo(expectedPostNewListResponseInvalidIdBoard);
    }

    @Test
    public void N6_shouldNotCreateNewListWhenIdBoardIsEmptyString() {
        // ARRANGE
        String idBoard = "";
        listName = generateRandomListName();
        // ACT
        responsePost = postCreateNewList(idBoard, listName, null);
        // ASSERT
        assertThat(responsePost.statusCode()).isEqualTo(400);
        assertThat(responsePost.getBody().asString()).isEqualTo(expectedPostNewListResponseInvalidIdBoard);
    }

    @Test
    public void N7_shouldNotCreateNewListWhenIdBoardIsNonExistent() {
        // ARRANGE
        String idBoard = "999999999999999999999999";
        listName = generateRandomListName();
        // ACT
        responsePost = postCreateNewList(idBoard, listName, null);
        // ASSERT
        assertThat(responsePost.statusCode()).isEqualTo(401);
        assertThat(responsePost.getBody().asString()).isEqualTo("unauthorized board list requested " + idBoard);
    }

    @Test
    public void N8_shouldNotCreateNewListWhenIdBoardIsIncorrect() {
        // ARRANGE
        String idBoard = "KeK 123";
        listName = generateRandomListName();
        // ACT
        responsePost = postCreateNewList(idBoard, listName, null);
        // ASSERT
        assertThat(responsePost.statusCode()).isEqualTo(400);
        assertThat(responsePost.getBody().asString()).isEqualTo(expectedPostNewListResponseInvalidIdBoard);
    }

    // ------------
    // idListSource
    // ------------

    @Test
    public void N9_shouldNotCreateNewListWhenIdListSourceIsNonExistent() {
        // ARRANGE
        listName = generateRandomListName();
        listIdListSource = "999999999999999999999999";

        POST_CreateNewListPayload payload = new POST_CreateNewListPayload.Builder()
                .setIdListSource(listIdListSource)
                .build();
        // ACT
        responsePost = postCreateNewList(boardId, listName, payload);
        // ASSERT
        assertThat(responsePost.statusCode()).isEqualTo(404);
        assertThat(responsePost.getBody().asString()).isEqualTo("List not found");
    }

    @Test
    public void N10_shouldNotCreateNewListWhenIdListSourceIsIncorrect() {
        // ARRANGE
        listName = generateRandomListName();
        listIdListSource = "KeK 123";

        POST_CreateNewListPayload payload = new POST_CreateNewListPayload.Builder()
                .setIdListSource(listIdListSource)
                .build();
        // ACT
        responsePost = postCreateNewList(boardId, listName, payload);
        // ASSERT
        assertThat(responsePost.statusCode()).isEqualTo(400);
        compareResponseWithJson(responsePost, expectedPostNewListResponseInvalidIdListSource);
    }

    // ---
    // pos
    // ---

    @Test
    public void N11_shouldNotCreateNewListWhenPosIsIncorrect() {
        // ARRANGE
        listName = generateRandomListName();
        listPos = "Kek 123";

        POST_CreateNewListPayload payload = new POST_CreateNewListPayload.Builder()
                .setPos(listPos)
                .build();
        // ACT
        responsePost = postCreateNewList(boardId, listName, payload);
        // ASSERT
        assertThat(responsePost.statusCode()).isEqualTo(400);
        compareResponseWithJson(responsePost, expectedPostNewListResponseInvalidPos);
    }
}
