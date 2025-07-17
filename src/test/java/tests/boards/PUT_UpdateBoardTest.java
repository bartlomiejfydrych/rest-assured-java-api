package tests.boards;

import base.TestBase;
import configuration.Config;
import dto.boards.PUT_UpdateBoardDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import payloads.boards.PUT_UpdateBoardPayload;

import java.util.Map;

import static endpoints.boards.DELETE_DeleteBoard.deleteDeleteBoard;
import static endpoints.boards.POST_CreateBoard.postCreateBoard;
import static endpoints.boards.PUT_UpdateBoard.putUpdateBoard;
import static org.assertj.core.api.Assertions.assertThat;
import static utils.UtilsResponse.deserializeAndValidate;
import static utils_tests.boards.POST_CreateBoardUtils.generateRandomBoardName;

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public class PUT_UpdateBoardTest extends TestBase {

    private String boardId;
    private String trelloId = Config.getTrelloId();

    @BeforeEach
    public void setUpCreateBoard() {
        responsePost = postCreateBoard(generateRandomBoardName(), null);
        assertThat(responsePost.statusCode()).isEqualTo(200);
        boardId = responsePost.jsonPath().getString("id");
    }

    @AfterEach
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
    public void P1_shouldUpdateBoardWhenMostStringParametersHaveSpecialCharacters() {

        String allCharacters = "!\"#$%&\\'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\\\]^_`abcdefghijklmnopqrstuvwxyz{|}~ ęĘóÓąĄśŚłŁżŻźŹćĆńŃ";
        // TODO: Na czacie metoda pomocnicza do losowego tasowania tych znaków

        PUT_UpdateBoardPayload payload = new PUT_UpdateBoardPayload.Builder()
                .setName()
                .setDesc()
                .setClosed()
                .setSubscribed()
                .setIdOrganization()
                .setPrefsPermissionLevel()
                .setPrefsSelfJoin()
                .setPrefsCardCovers()
                .setPrefsHideVotes()
                .setPrefsInvitations()
                .setPrefsVoting()
                .setPrefsComments()
                .setPrefsBackground()
                .setPrefsCardAging()
                .setPrefsCalendarFeedEnabled()
                .setLabelNamesGreen()
                .setLabelNamesYellow()
                .setLabelNamesOrange()
                .setLabelNamesRed()
                .setLabelNamesPurple()
                .setLabelNamesBlue()
                .build();
        Map<String, Object> queryParams = payload.toQueryParams();

        // PUT
        responsePut = putUpdateBoard(boardId, queryParams);
        assertThat(responsePut.statusCode()).isEqualTo(200);
        PUT_UpdateBoardDto responsePutDto = deserializeAndValidate(responsePut, PUT_UpdateBoardDto.class);
        // TODO: Prepare expected response
        // TODO: Compare objects
        // GET
        // TODO: Validate Get against Put
    }
}
