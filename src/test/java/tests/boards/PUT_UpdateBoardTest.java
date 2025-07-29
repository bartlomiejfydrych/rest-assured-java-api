package tests.boards;

import base.TestBase;
import configuration.Config;
import dto.boards.POST_CreateBoardDto;
import dto.boards.PUT_UpdateBoardDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import payloads.boards.PUT_UpdateBoardPayload;

import java.net.URL;
import java.util.Map;

import static endpoints.boards.DELETE_DeleteBoard.deleteDeleteBoard;
import static endpoints.boards.POST_CreateBoard.postCreateBoard;
import static endpoints.boards.PUT_UpdateBoard.putUpdateBoard;
import static expected_responses.boards.PUT_UpdateBoardExpected.P1ExpectedPutBoardResponse;
import static org.assertj.core.api.Assertions.assertThat;
import static utils.UtilsCommon.getAllCharactersSetInRandomOrder;
import static utils.UtilsCompare.compareObjects;
import static utils.UtilsResponse.deserializeAndValidate;
import static utils.UtilsResponse.deserializeJson;
import static utils_tests.boards.POST_CreateBoardUtils.generateRandomBoardName;
import static utils_tests.boards.PUT_UpdateBoardUtils.*;

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public class PUT_UpdateBoardTest extends TestBase {

    private String trelloId = Config.getTrelloId();
    // POST changing variables
    private String boardId;
    private String boardName;
    private URL boardUrl;
    private URL boardShortUrl;

    @BeforeEach
    public void setUpCreateBoard() {
        responsePost = postCreateBoard(generateRandomBoardName(), null);
        assertThat(responsePost.statusCode()).isEqualTo(200);
        POST_CreateBoardDto responsePostDto = deserializeJson(responsePost, POST_CreateBoardDto.class);
        boardId = responsePostDto.id;
        boardName = responsePostDto.name;
        boardUrl = responsePostDto.url;
        boardShortUrl = responsePostDto.shortUrl;
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

        boardName = getAllCharactersSetInRandomOrder();
        String desc = getAllCharactersSetInRandomOrder();
        String labelNamesGreen = getAllCharactersSetInRandomOrder();
        String labelNamesYellow = getAllCharactersSetInRandomOrder();
        String labelNamesOrange = getAllCharactersSetInRandomOrder();
        String labelNamesRed = getAllCharactersSetInRandomOrder();
        String labelNamesPurple = getAllCharactersSetInRandomOrder();
        String labelNamesBlue = getAllCharactersSetInRandomOrder();

        PUT_UpdateBoardPayload payload = new PUT_UpdateBoardPayload.Builder()
                .setName(boardName)
                .setDesc(desc)
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
                .setLabelNamesGreen(labelNamesGreen)
                .setLabelNamesYellow(labelNamesYellow)
                .setLabelNamesOrange(labelNamesOrange)
                .setLabelNamesRed(labelNamesRed)
                .setLabelNamesPurple(labelNamesPurple)
                .setLabelNamesBlue(labelNamesBlue)
                .build();
        Map<String, Object> queryParams = payload.toQueryParams();

        // PUT
        responsePut = putUpdateBoard(boardId, queryParams);
        assertThat(responsePut.statusCode()).isEqualTo(200);
        PUT_UpdateBoardDto responsePutDto = deserializeAndValidate(responsePut, PUT_UpdateBoardDto.class);
        assertThat(responsePutDto.url).isNotEqualTo(boardUrl);
        assertThat(stripBoardNameFromUrl(responsePutDto.url)).isEqualTo(stripBoardNameFromUrl(boardUrl));
        PUT_UpdateBoardDto expectedResponsePutDto = prepareExpectedResponsePut(P1ExpectedPutBoardResponse, boardId, boardName, responsePutDto.url, boardShortUrl);
        expectedResponsePutDto.desc = desc;
        expectedResponsePutDto.labelNames.green = labelNamesGreen;
        expectedResponsePutDto.labelNames.yellow = labelNamesYellow;
        expectedResponsePutDto.labelNames.orange = labelNamesOrange;
        expectedResponsePutDto.labelNames.red = labelNamesRed;
        expectedResponsePutDto.labelNames.purple = labelNamesPurple;
        expectedResponsePutDto.labelNames.blue = labelNamesBlue;
        expectedResponsePutDto.organization.memberships.getFirst().lastActive = responsePutDto.organization.memberships.getFirst().lastActive;
        compareObjects(responsePutDto, expectedResponsePutDto);
        // GET
        validateGetAgainstPut(responsePutDto);
    }
}
