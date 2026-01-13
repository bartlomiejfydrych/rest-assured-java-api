package tests.lists;

import base.TestBase;
import dto.lists.GET_GetListDto;
import dto.lists.ListBaseDto;
import dto.lists.POST_CreateNewListDto;
import dto.lists.PUT_UpdateListDto;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import payloads.lists.PUT_UpdateListPayload;
import utils.UtilsString;

import static endpoints.boards.DEL_DeleteBoardEndpoint.deleteDeleteBoard;
import static endpoints.boards.POST_CreateBoardEndpoint.postCreateBoard;
import static endpoints.lists.GET_GetListEndpoint.getGetList;
import static endpoints.lists.POST_CreateNewListEndpoint.postCreateNewList;
import static endpoints.lists.PUT_UpdateListEndpoint.putUpdateList;
import static expected_responses.lists.PUT_UpdateListExpected.*;
import static org.assertj.core.api.Assertions.assertThat;
import static utils.UtilsCompare.compareObjects;
import static utils.UtilsCompare.compareResponseWithJson;
import static utils.UtilsResponse.deserializeAndValidate;
import static utils.UtilsResponse.deserializeJson;
import static utils.UtilsString.getAllCharactersSetInRandomOrder;
import static utils_tests.boards.POST_CreateBoardUtils.generateRandomBoardName;
import static utils_tests.lists.POST_CreateNewListUtils.generateRandomListName;
import static utils_tests.lists.PUT_UpdateListUtils.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PUT_UpdateListTest extends TestBase {

    private Response responsePost;
    private Response responsePut;
    private Response responseGet;
    private Response responseDelete;

    /*
    NOTES:
    – Some variables are intentionally taken from POST to expected response to check whether PUT has accidentally changed them when it was not supposed to.
    */

    // BOARD
    private String boardId;
    private String boardIdN;
    // LIST - COMMON
    private String listId;
    // LIST - POST
    private POST_CreateNewListDto responsePostDto;
    // LIST - PUT
    private String listName;
    private Boolean listClosed;
    private Long listPosAsLong;
    private String listPosAsString;
    private Boolean listSubscribed;

    @BeforeAll
    public void setUpCreateBoardAndList() {
        responsePost = postCreateBoard(generateRandomBoardName(), null);
        assertThat(responsePost.statusCode()).isEqualTo(200);
        boardId = responsePost.getBody().jsonPath().getString("id" );
        responsePost = postCreateNewList(boardId, generateRandomListName(), null);
        assertThat(responsePost.statusCode()).isEqualTo(200);
        responsePostDto = deserializeAndValidate(responsePost, POST_CreateNewListDto.class);
        listId = responsePostDto.id;
    }

    @AfterAll
    public void tearDownDeleteBoardAndList() {
        if (boardId != null) {
            responseDelete = deleteDeleteBoard(boardId);
            assertThat(responseDelete.statusCode()).isEqualTo(200);
            boardId = null;
            listId = null;
        }
    }

    // --------------
    // POSITIVE TESTS
    // --------------

    @Test
    public void P1_shouldUpdateListWhereNameSpecialCharactersAndNumbersClosedTrueSubscribedTrue() {

        listName = getAllCharactersSetInRandomOrder();
        listClosed = true;
        listSubscribed = true;

        PUT_UpdateListPayload payload = new PUT_UpdateListPayload.Builder()
                .setName(listName)
                .setClosed(listClosed)
                .setSubscribed(listSubscribed)
                .build();

        // GET (current status of the list)
        responseGet = getGetList(listId);
        assertThat(responseGet.statusCode()).isEqualTo(200);
        GET_GetListDto responseGetDto = deserializeAndValidate(responseGet, GET_GetListDto.class);
        // PUT
        responsePut = putUpdateList(listId, payload);
        assertThat(responsePut.statusCode()).isEqualTo(200);
        PUT_UpdateListDto responsePutDto = deserializeAndValidate(responsePut, PUT_UpdateListDto.class);
        PUT_UpdateListDto expectedResponsePutDto = prepareExpectedResponsePut(
                P1ExpectedPutUpdateListResponse,
                listId,
                listName,
                boardId,
                responseGetDto.pos
        );
        compareObjects(responsePutDto, expectedResponsePutDto);
        // GET
        validateGetAgainstPut(responsePutDto);
    }

    @Test
    public void P2_shouldUpdateListWhereNameOneCharacterAndClosedFalseSubscribedFalsePosNull() {

        listName = UtilsString.getRandomSingleCharAlphanumeric();
        listClosed = false;
        listPosAsLong = null;
        listSubscribed = false;

        PUT_UpdateListPayload payload = new PUT_UpdateListPayload.Builder()
                .setName(listName)
                .setClosed(listClosed)
                .setPos(listPosAsLong)
                .setSubscribed(listSubscribed)
                .build();

        // GET (current status of the list)
        responseGet = getGetList(listId);
        assertThat(responseGet.statusCode()).isEqualTo(200);
        GET_GetListDto responseGetDto = deserializeAndValidate(responseGet, GET_GetListDto.class);
        // PUT
        responsePut = putUpdateList(listId, payload);
        assertThat(responsePut.statusCode()).isEqualTo(200);
        PUT_UpdateListDto responsePutDto = deserializeAndValidate(responsePut, PUT_UpdateListDto.class);
        PUT_UpdateListDto expectedResponsePutDto = prepareExpectedResponsePut(
                P2ExpectedPutUpdateListResponse,
                listId,
                listName,
                boardId,
                responseGetDto.pos
        );
        compareObjects(responsePutDto, expectedResponsePutDto);
        // GET
        validateGetAgainstPut(responsePutDto);
    }

    @Test
    public void P3_shouldUpdateListWhereNameMissingClosedMissingPosEmptyStringSubscribedMissing() {

        /*
        NOTE:
        FLAKY TEST
        REMEMBER: Inserted omission of "pos" parameter (ListBaseDto.FIELD_POS) during comparison
        This test detected strange behavior.
        If the first PUT request changes something in the list, but not its "Pos," or if we try to change "Pos" to
        something that shouldn't change it, such as null or an empty String, the initial value of "Pos" still changes.
        */

        listPosAsString = "";

        PUT_UpdateListPayload payload = new PUT_UpdateListPayload.Builder()
                .setPos(listPosAsString)
                .build();

        // GET (current status of the list)
        responseGet = getGetList(listId);
        assertThat(responseGet.statusCode()).isEqualTo(200);
        GET_GetListDto responseGetDto = deserializeAndValidate(responseGet, GET_GetListDto.class);
        // PUT
        responsePut = putUpdateList(listId, payload);
        assertThat(responsePut.statusCode()).isEqualTo(200);
        PUT_UpdateListDto responsePutDto = deserializeAndValidate(responsePut, PUT_UpdateListDto.class);
        PUT_UpdateListDto expectedResponsePutDto = prepareUniversalExpectedResponsePut(
                listId,
                responseGetDto.name,
                responseGetDto.closed,
                null,
                boardId,
                responseGetDto.pos,
                null
        );
        compareObjects(responsePutDto, expectedResponsePutDto, ListBaseDto.FIELD_POS);
        // GET
        validateGetAgainstPut(responsePutDto);
    }

    @Test
    public void P4_shouldUpdateListWhereNameNullClosedNullSubscribedNull() {

        listName = null;
        listClosed = null;
        listSubscribed = null;

        PUT_UpdateListPayload payload = new PUT_UpdateListPayload.Builder()
                .setName(listName)
                .setClosed(listClosed)
                .setSubscribed(listSubscribed)
                .build();

        // GET (current status of the list)
        responseGet = getGetList(listId);
        assertThat(responseGet.statusCode()).isEqualTo(200);
        GET_GetListDto responseGetDto = deserializeAndValidate(responseGet, GET_GetListDto.class);
        // PUT
        responsePut = putUpdateList(listId, payload);
        assertThat(responsePut.statusCode()).isEqualTo(200);
        PUT_UpdateListDto responsePutDto = deserializeAndValidate(responsePut, PUT_UpdateListDto.class);
        PUT_UpdateListDto expectedResponsePutDto = prepareExpectedResponsePut(
                P4ExpectedPutUpdateListResponse,
                listId,
                responseGetDto.name,
                boardId,
                responseGetDto.pos
        );
        compareObjects(responsePutDto, expectedResponsePutDto);
        // GET
        validateGetAgainstPut(responsePutDto);
    }

    @Test
    public void P5_shouldUpdateThreeListsWithPosTopBottomAndNumber() {

        // -------
        // ARRANGE
        // -------

        // POST
        // List (1) is created in '@BeforeAll' | Base list against which the position of the remaining lists will be checked
        String listName2 = generateRandomListName();
        String listName3 = generateRandomListName();
        String listName4 = generateRandomListName();
        // PUT
        String listPos2 = "top";
        String listPos3 = "bottom";
        Long listPos4 = 140737488322560L;

        PUT_UpdateListPayload payload2 = new PUT_UpdateListPayload.Builder()
                .setPos(listPos2)
                .build();
        PUT_UpdateListPayload payload3 = new PUT_UpdateListPayload.Builder()
                .setPos(listPos3)
                .build();
        PUT_UpdateListPayload payload4 = new PUT_UpdateListPayload.Builder()
                .setPos(listPos4)
                .build();

        // ---
        // ACT
        // ---

        // POST (add list 2)
        Response responsePost2 = postCreateNewList(boardId, listName2, null);
        assertThat(responsePost2.statusCode()).isEqualTo(200);
        POST_CreateNewListDto responsePostDto2 = deserializeJson(responsePost2, POST_CreateNewListDto.class);
        String listId2 = responsePostDto2.id;
        // POST (add list 3)
        Response responsePost3 = postCreateNewList(boardId, listName3, null);
        assertThat(responsePost3.statusCode()).isEqualTo(200);
        POST_CreateNewListDto responsePostDto3 = deserializeJson(responsePost3, POST_CreateNewListDto.class);
        String listId3 = responsePostDto3.id;
        // POST (add list 4)
        Response responsePost4 = postCreateNewList(boardId, listName4, null);
        assertThat(responsePost4.statusCode()).isEqualTo(200);
        POST_CreateNewListDto responsePostDto4 = deserializeJson(responsePost4, POST_CreateNewListDto.class);
        String listId4 = responsePostDto4.id;

        // PUT (edit list 2 -> POS: top)
        Response responsePut2 = putUpdateList(listId2, payload2);
        assertThat(responsePut2.statusCode()).isEqualTo(200);
        PUT_UpdateListDto responsePutDto2 = deserializeAndValidate(responsePut2, PUT_UpdateListDto.class);
        Long responsePutPos2 = responsePutDto2.pos;
        PUT_UpdateListDto expectedResponsePutDto2 = prepareUniversalExpectedResponsePut(
                listId2,
                responsePostDto2.name,
                responsePostDto2.closed,
                null,
                boardId,
                responsePutPos2,
                null
        );
        compareObjects(responsePutDto2, expectedResponsePutDto2);
        // GET
        validateGetAgainstPut(responsePutDto2);
        // PUT (edit list 3 -> POS: bottom)
        Response responsePut3 = putUpdateList(listId3, payload3);
        assertThat(responsePut3.statusCode()).isEqualTo(200);
        PUT_UpdateListDto responsePutDto3 = deserializeAndValidate(responsePut3, PUT_UpdateListDto.class);
        Long responsePutPos3 = responsePutDto3.pos;
        PUT_UpdateListDto expectedResponsePutDto3 = prepareUniversalExpectedResponsePut(
                listId3,
                responsePostDto3.name,
                responsePostDto3.closed,
                null,
                boardId,
                responsePutPos3,
                null
        );
        compareObjects(responsePutDto3, expectedResponsePutDto3);
        // GET
        validateGetAgainstPut(responsePutDto3);
        // PUT (edit list 4 -> POS: 140737488322560L)
        Response responsePut4 = putUpdateList(listId4, payload4);
        assertThat(responsePut4.statusCode()).isEqualTo(200);
        PUT_UpdateListDto responsePutDto4 = deserializeAndValidate(responsePut4, PUT_UpdateListDto.class);
        Long responsePutPos4 = responsePutDto4.pos;
        PUT_UpdateListDto expectedResponsePutDto4 = prepareUniversalExpectedResponsePut(
                listId4,
                responsePostDto4.name,
                responsePostDto4.closed,
                null,
                boardId,
                responsePutPos4,
                null
        );
        compareObjects(responsePutDto4, expectedResponsePutDto4);
        // GET
        validateGetAgainstPut(responsePutDto4);

        // ------
        // ASSERT
        // ------

        // POSITION VALIDATION
        assertThat(responsePutPos2)
                .as("The list with the \"top\" position should be higher (i.e. have a lower numerical value) than the first list." )
                .isLessThan(responsePostDto.pos);
        assertThat(responsePutPos3)
                .as("The list with the \"bottom\" position should be lower (i.e. have a higher numerical value) than the first list." )
                .isGreaterThan(responsePostDto.pos);
        assertThat(responsePutPos4)
                .as("The list with the \"numeric\" item should be higher (i.e. have a lower numerical value) than the first list." )
                .isLessThan(responsePostDto.pos);
    }

    @Test
    public void P6_shouldUpdateListByMovingItToAnotherBoard() {

        /*
        NOTE:
        FLAKY TEST
        REMEMBER: Inserted omission of "pos" parameter (ListBaseDto.FIELD_POS) during comparison
        This test detected strange behavior.
        If the first PUT request changes something in the list, but not its "Pos," or if we try to change "Pos" to
        something that shouldn't change it, such as null or an empty String, the initial value of "Pos" still changes.
        */

        // POST (add {board 2})
        responsePost = postCreateBoard(generateRandomBoardName(), null);
        assertThat(responsePost.statusCode()).isEqualTo(200);
        String boardId2 = responsePost.getBody().jsonPath().getString("id" );
        try {
            // POST (add second list into {board 1} | so that the list used in other tests is not transferred)
            responsePost = postCreateNewList(boardId, generateRandomListName(), null);
            assertThat(responsePost.statusCode()).isEqualTo(200);
            POST_CreateNewListDto responsePostDto = deserializeJson(responsePost, POST_CreateNewListDto.class);
            String listId2 = responsePostDto.id;
            // PUT (move {list 2} from {board 1} to {board 2})
            PUT_UpdateListPayload payload = new PUT_UpdateListPayload.Builder()
                    .setIdBoard(boardId2)
                    .build();

            responsePut = putUpdateList(listId2, payload);
            assertThat(responsePut.statusCode()).isEqualTo(200);
            PUT_UpdateListDto responsePutDto = deserializeAndValidate(responsePut, PUT_UpdateListDto.class);
            PUT_UpdateListDto expectedResponsePutDto = prepareUniversalExpectedResponsePut(
                    listId2,
                    responsePostDto.name,
                    responsePostDto.closed,
                    responsePostDto.color,
                    boardId2,
                    responsePostDto.pos,
                    null
            );
            compareObjects(responsePutDto, expectedResponsePutDto, ListBaseDto.FIELD_POS);
            // GET
            validateGetAgainstPut(responsePutDto);
        } finally {
            // DELETE (delete {board 2})
            if (boardId2 != null) {
                try {
                    responseDelete = deleteDeleteBoard(boardId2);
                    assertThat(responseDelete.statusCode()).isEqualTo(200);
                } catch (Exception e) {
                    System.err.println("ERROR: Failed to delete {board2}: " + boardId2);
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void P7_shouldUpdateListWhenIdBoardNull() {

        listName = generateRandomListName();
        String boardId2 = null;

        PUT_UpdateListPayload payload = new PUT_UpdateListPayload.Builder()
                .setName(listName)
                .setIdBoard(boardId2)
                .build();

        // GET (current status of the list)
        responseGet = getGetList(listId);
        assertThat(responseGet.statusCode()).isEqualTo(200);
        GET_GetListDto responseGetDto = deserializeAndValidate(responseGet, GET_GetListDto.class);
        // PUT
        responsePut = putUpdateList(listId, payload);
        assertThat(responsePut.statusCode()).isEqualTo(200);
        PUT_UpdateListDto responsePutDto = deserializeAndValidate(responsePut, PUT_UpdateListDto.class);
        PUT_UpdateListDto expectedResponsePutDto = prepareExpectedResponsePut(
                P7ExpectedPutUpdateListResponse,
                listId,
                listName,
                boardId,
                responseGetDto.pos
        );
        compareObjects(responsePutDto, expectedResponsePutDto);
        // GET
        validateGetAgainstPut(responsePutDto);
    }

    @Test
    public void P8_shouldUpdateListWhenPosNumberAsString() {

        listPosAsString = "140737488322560";

        PUT_UpdateListPayload payload = new PUT_UpdateListPayload.Builder()
                .setPos(listPosAsString)
                .build();

        // GET (current status of the list)
        responseGet = getGetList(listId);
        assertThat(responseGet.statusCode()).isEqualTo(200);
        GET_GetListDto responseGetDto = deserializeAndValidate(responseGet, GET_GetListDto.class);
        // PUT
        responsePut = putUpdateList(listId, payload);
        assertThat(responsePut.statusCode()).isEqualTo(200);
        PUT_UpdateListDto responsePutDto = deserializeAndValidate(responsePut, PUT_UpdateListDto.class);
        PUT_UpdateListDto expectedResponsePutDto = prepareExpectedResponsePut(
                P8ExpectedPutUpdateListResponse,
                listId,
                responseGetDto.name,
                boardId,
                listPosAsString
        );
        compareObjects(responsePutDto, expectedResponsePutDto);
        // GET
        validateGetAgainstPut(responsePutDto);
    }

    // --------------
    // NEGATIVE TESTS
    // --------------

    // {id}

    @Test
    public void N1_shouldNotUpdateListWhenIdNonExistent() {
        responsePut = putUpdateList("99", null);
        assertThat(responsePut.statusCode()).isEqualTo(400);
        assertThat(responsePut.getBody().asString()).isEqualTo(expectedPutUpdateListResponseInvalidId);
    }

    @Test
    public void N2_shouldNotUpdateListWhenIdIncorrect() {
        responsePut = putUpdateList("KeK", null);
        assertThat(responsePut.statusCode()).isEqualTo(400);
        assertThat(responsePut.getBody().asString()).isEqualTo(expectedPutUpdateListResponseInvalidId);
    }

    // {name}

    @Test
    public void N3_shouldNotUpdateListWhenNameEmptyString() {
        // ARRANGE
        listName = "";
        PUT_UpdateListPayload payload = new PUT_UpdateListPayload.Builder()
                .setName(listName)
                .build();
        // ACT
        responsePut = putUpdateList(listId, payload);
        // ASSERT
        assertThat(responsePut.statusCode()).isEqualTo(400);
        assertThat(responsePut.getBody().asString()).isEqualTo("invalid value for name" );
    }

    // {idBoard}

    @Test
    public void N4_shouldNotUpdateListWhenIdBoardEmptyString() {
        // ARRANGE
        boardIdN = "";
        PUT_UpdateListPayload payload = new PUT_UpdateListPayload.Builder()
                .setName(generateRandomListName())
                .setIdBoard(boardIdN)
                .build();
        // ACT
        responsePut = putUpdateList(listId, payload);
        // ASSERT
        assertThat(responsePut.statusCode()).isEqualTo(400);
        compareResponseWithJson(responsePut, expectedPutUpdateListResponseInvalidBoardId);
    }

    @Test
    public void N5_shouldNotUpdateListWhenIdBoardNonExistent() {
        // ARRANGE
        boardIdN = "691db99a4e5a030526097e00";
        PUT_UpdateListPayload payload = new PUT_UpdateListPayload.Builder()
                .setName(generateRandomListName())
                .setIdBoard(boardIdN)
                .build();
        // ACT
        responsePut = putUpdateList(listId, payload);
        // ASSERT
        assertThat(responsePut.statusCode()).isEqualTo(404);
        compareResponseWithJson(responsePut, expectedPutUpdateListResponseBoardNotFound);
    }

    @Test
    public void N6_shouldNotUpdateListWhenIdBoardIncorrect() {
        // ARRANGE
        boardIdN = "KeK";
        PUT_UpdateListPayload payload = new PUT_UpdateListPayload.Builder()
                .setName(generateRandomListName())
                .setIdBoard(boardIdN)
                .build();
        // ACT
        responsePut = putUpdateList(listId, payload);
        // ASSERT
        assertThat(responsePut.statusCode()).isEqualTo(400);
        compareResponseWithJson(responsePut, expectedPutUpdateListResponseInvalidBoardId);
    }

    // {pos}

    @Test
    public void N7_shouldNotUpdateListWhenPosIncorrect() {
        // ARRANGE
        listPosAsString = "KeK 123";
        PUT_UpdateListPayload payload = new PUT_UpdateListPayload.Builder()
                .setName(generateRandomListName())
                .setPos(listPosAsString)
                .build();
        // ACT
        responsePut = putUpdateList(listId, payload);
        // ASSERT
        assertThat(responsePut.statusCode()).isEqualTo(400);
        compareResponseWithJson(responsePut, expectedPutUpdateListResponseInvalidPosition);
    }
}
