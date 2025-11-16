package tests.lists;

import base.TestBase;
import dto.lists.POST_CreateNewListDto;
import dto.lists.PUT_UpdateListDto;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import payloads.lists.PUT_UpdateListPayload;

import java.util.Map;

import static endpoints.boards.DELETE_DeleteBoard.deleteDeleteBoard;
import static endpoints.boards.POST_CreateBoard.postCreateBoard;
import static endpoints.lists.POST_CreateNewList.postCreateNewList;
import static endpoints.lists.PUT_UpdateList.putUpdateList;
import static expected_responses.lists.PUT_UpdateListExpected.P1ExpectedPutUpdateListResponse;
import static org.assertj.core.api.Assertions.assertThat;
import static utils.UtilsCommon.getAllCharactersSetInRandomOrder;
import static utils.UtilsCompare.compareObjects;
import static utils.UtilsResponse.deserializeAndValidate;
import static utils_tests.boards.POST_CreateBoardUtils.generateRandomBoardName;
import static utils_tests.lists.POST_CreateNewListUtils.generateRandomListName;
import static utils_tests.lists.PUT_UpdateListUtils.prepareExpectedResponsePut;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PUT_UpdateListTest extends TestBase {

    /*
    NOTES:
    – {listPos} is intentionally taken from POST to check if PUT accidentally changes it
    */

    private String boardId;
    private String listId;
    private String listName;
    private Boolean listClosed;
    private Long listPos;
    private Boolean listSubscribed;

    @BeforeAll
    public void setUpCreateBoardAndList() {
        responsePost = postCreateBoard(generateRandomBoardName(), null);
        assertThat(responsePost.statusCode()).isEqualTo(200);
        boardId = responsePost.getBody().jsonPath().getString("id");
        responsePost = postCreateNewList(boardId, generateRandomListName(), null);
        assertThat(responsePost.statusCode()).isEqualTo(200);
        POST_CreateNewListDto responsePostDto = deserializeAndValidate(responsePost, POST_CreateNewListDto.class);
        listId = responsePostDto.id;
        listPos = responsePostDto.pos;
    }

    @AfterAll
    public void tearDownDeleteBoardAndList() {
        if (boardId != null) {
            responseDelete = deleteDeleteBoard(boardId);
            assertThat(responseDelete.statusCode()).isEqualTo(200);
            boardId = null;
            listId = null;
            listPos = null;
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
        Map<String, Object> queryParams = payload.toQueryParams();

        // PUT
        responsePut = putUpdateList(boardId, listId, queryParams);
        assertThat(responsePut.statusCode()).isEqualTo(200);
        PUT_UpdateListDto responsePutDto = deserializeAndValidate(responsePut, PUT_UpdateListDto.class);
        PUT_UpdateListDto expectedResponsePutDto = prepareExpectedResponsePut(
                P1ExpectedPutUpdateListResponse,
                listId,
                listName,
                boardId,
                listPos
        );
        compareObjects(responsePutDto, expectedResponsePutDto);
        // GET
        // TODO
    }
}
