package tests.lists;

import base.TestBase;
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
import static org.assertj.core.api.Assertions.assertThat;
import static utils.UtilsCommon.getAllCharactersSetInRandomOrder;
import static utils_tests.boards.POST_CreateBoardUtils.generateRandomBoardName;
import static utils_tests.lists.POST_CreateNewListUtils.generateRandomListName;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PUT_UpdateListTest extends TestBase {

    private String boardId;
    private String listId;
    private String listName;
    private Boolean listClosed;
    private String listPos;
    private Boolean listSubscribed;

    @BeforeAll
    public void setUpCreateBoardAndList() {
        responsePost = postCreateBoard(generateRandomBoardName(), null);
        assertThat(responsePost.statusCode()).isEqualTo(200);
        boardId = responsePost.getBody().jsonPath().getString("id");
        responsePost = postCreateNewList(boardId, generateRandomListName(), null);
        assertThat(responsePost.statusCode()).isEqualTo(200);
        listId = responsePost.getBody().jsonPath().getString("id");
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
        Map<String, Object> queryParams = payload.toQueryParams();

        // PUT
        responsePut = putUpdateList(boardId, listId, queryParams);
        assertThat(responsePut.statusCode()).isEqualTo(200);

    }
}
