package tests.boards;

import base.TestBase;
import configuration.Config;
import dto.boards.PUT_UpdateBoardDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import payloads.boards.PUT_UpdateBoardPayload;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.Map;

import static endpoints.boards.DELETE_DeleteBoard.deleteDeleteBoard;
import static endpoints.boards.POST_CreateBoard.postCreateBoard;
import static endpoints.boards.PUT_UpdateBoard.putUpdateBoard;
import static expected_responses.boards.PUT_UpdateBoardExpected.P1ExpectedPutBoardResponse;
import static org.assertj.core.api.Assertions.assertThat;
import static utils.UtilsCommon.getAllCharactersSetInRandomOrder;
import static utils.UtilsResponse.deserializeAndValidate;
import static utils.UtilsResponse.deserializeJson;
import static utils_tests.boards.POST_CreateBoardUtils.generateRandomBoardName;

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public class PUT_UpdateBoardTest extends TestBase {

    private String trelloId = Config.getTrelloId();
    // POST changing variables
    private String boardId;
    private String boardName;
    private URL boardUrl;
    private URL boardShortUrl;

    @BeforeEach
    public void setUpCreateBoard() throws MalformedURLException {
        responsePost = postCreateBoard(generateRandomBoardName(), null);
        assertThat(responsePost.statusCode()).isEqualTo(200);
        boardId = responsePost.jsonPath().getString("id");
        boardName = responsePost.jsonPath().getString("name");
        boardUrl = URI.create(responsePost.jsonPath().getString("url")).toURL();
        boardShortUrl = URI.create(responsePost.jsonPath().getString("shortUrl")).toURL();
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
    public void P1_shouldUpdateBoardWhenMostStringParametersHaveSpecialCharactersAndBooleansAreTrue() {

        String allCharactersName = getAllCharactersSetInRandomOrder();
        String allCharactersDesc = getAllCharactersSetInRandomOrder();
        String allCharactersLabelNamesGreen = getAllCharactersSetInRandomOrder();
        String allCharactersLabelNamesYellow = getAllCharactersSetInRandomOrder();
        String allCharactersLabelNamesOrange = getAllCharactersSetInRandomOrder();
        String allCharactersLabelNamesRed = getAllCharactersSetInRandomOrder();
        String allCharactersLabelNamesPurple = getAllCharactersSetInRandomOrder();
        String allCharactersLabelNamesBlue = getAllCharactersSetInRandomOrder();

        PUT_UpdateBoardPayload payload = new PUT_UpdateBoardPayload.Builder()
                .setName(allCharactersName)
                .setDesc(allCharactersDesc)
                .setClosed(true)
                .setIdOrganization(trelloId)
                .setPrefsPermissionLevel("org")
                .setPrefsSelfJoin(true)
                .setPrefsCardCovers(true)
                .setPrefsHideVotes(true)
                .setPrefsInvitations("admins")
                .setPrefsVoting("disabled")
                .setPrefsComments("disabled")
                .setPrefsBackground("blue")
                .setPrefsCardAging("regular")
                .setPrefsCalendarFeedEnabled(true)
                .setLabelNamesGreen(allCharactersLabelNamesGreen)
                .setLabelNamesYellow(allCharactersLabelNamesYellow)
                .setLabelNamesOrange(allCharactersLabelNamesOrange)
                .setLabelNamesRed(allCharactersLabelNamesRed)
                .setLabelNamesPurple(allCharactersLabelNamesPurple)
                .setLabelNamesBlue(allCharactersLabelNamesBlue)
                .build();
        Map<String, Object> queryParams = payload.toQueryParams();

        // PUT
        responsePut = putUpdateBoard(boardId, queryParams);
        assertThat(responsePut.statusCode()).isEqualTo(200);
        PUT_UpdateBoardDto responsePutDto = deserializeAndValidate(responsePut, PUT_UpdateBoardDto.class);
        // TODO: Prepare expected response
        PUT_UpdateBoardDto expectedResponsePutDto = deserializeJson(P1ExpectedPutBoardResponse, PUT_UpdateBoardDto.class);
        expectedResponsePutDto.id = boardId;
        expectedResponsePutDto.url = boardUrl;
        expectedResponsePutDto.shortUrl = boardShortUrl;
        // TODO: Compare objects
        // GET
        // TODO: Validate Get against Put
    }
}
