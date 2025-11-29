package tests.boards;

import base.TestBase;
import configuration.Config;
import dto.boards.GET_GetBoardDto;
import dto.boards.POST_CreateBoardDto;
import dto.boards.PUT_UpdateBoardDto;
import org.junit.jupiter.api.*;
import payloads.boards.PUT_UpdateBoardPayload;

import java.net.URL;
import java.util.Map;

import static endpoints.boards.DELETE_DeleteBoard.deleteDeleteBoard;
import static endpoints.boards.GET_GetBoard.getGetBoard;
import static endpoints.boards.POST_CreateBoard.postCreateBoard;
import static endpoints.boards.PUT_UpdateBoard.putUpdateBoard;
import static expected_responses.boards.PUT_UpdateBoardExpected.*;
import static org.assertj.core.api.Assertions.assertThat;
import static utils.UtilsCommon.*;
import static utils.UtilsCompare.compareObjects;
import static utils.UtilsCompare.compareObjectsJsonNode;
import static utils.UtilsResponse.deserializeAndValidate;
import static utils.UtilsResponse.deserializeJson;
import static utils_tests.boards.POST_CreateBoardUtils.generateRandomBoardName;
import static utils_tests.boards.PUT_UpdateBoardUtils.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PUT_UpdateBoardTest extends TestBase {

    private String trelloId = Config.getTrelloId();
    // POST changing variables
    private String boardId;
    private String boardName;
    private URL boardUrl;
    private URL boardShortUrl;

    @BeforeAll
    public void setUpCreateBoard() {
        responsePost = postCreateBoard(generateRandomBoardName(), null);
        assertThat(responsePost.statusCode()).isEqualTo(200);
        POST_CreateBoardDto responsePostDto = deserializeJson(responsePost, POST_CreateBoardDto.class);
        boardId = responsePostDto.id;
        boardName = responsePostDto.name;
        boardUrl = responsePostDto.url;
        boardShortUrl = responsePostDto.shortUrl;
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
    public void P1_shouldUpdateBoardWhenMostStringParametersHaveSpecialCharactersAndBooleansAreTrue() {

        boardName = getAllCharactersSetInRandomOrder();
        String desc = getAllCharactersSetInRandomOrder();

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
                //.setPrefsBackground("blue") // NOTE: The value "blue" stopped working (500) | Reported: https://ecosystem.atlassian.net/servicedesk/customer/portal/34/ECOHELP-99809?created=true
                .setPrefsCardAging("regular")
                .setPrefsCalendarFeedEnabled(true)
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
        expectedResponsePutDto.organization.memberships.getFirst().lastActive = responsePutDto.organization.memberships.getFirst().lastActive;
        compareObjects(responsePutDto, expectedResponsePutDto);
        // GET
        validateGetAgainstPut(responsePutDto);
    }

    @Test
    public void P2_shouldUpdateBoardWhenMostParametersAreNull() {

        PUT_UpdateBoardPayload payload = new PUT_UpdateBoardPayload.Builder()
                .setName(null)
                .setDesc(null)
                .setClosed(null)
                .setSubscribed(null)
                .setIdOrganization(null)
                .setPrefsPermissionLevel(null)
                .setPrefsSelfJoin(null)
                .setPrefsCardCovers(null)
                .setPrefsHideVotes(null)
                .setPrefsInvitations(null)
                .setPrefsVoting(null)
                .setPrefsComments(null)
                .setPrefsBackground(null)
                .setPrefsCardAging(null)
                .setPrefsCalendarFeedEnabled(null)
                .build();
        Map<String, Object> queryParams = payload.toQueryParams();

        // PUT
        responsePut = putUpdateBoard(boardId, queryParams);
        assertThat(responsePut.statusCode()).isEqualTo(200);
        PUT_UpdateBoardDto responsePutDto = deserializeAndValidate(responsePut, PUT_UpdateBoardDto.class);
        PUT_UpdateBoardDto expectedResponsePutDto = prepareExpectedResponsePut(P2ExpectedPutBoardResponse, boardId, boardName, boardUrl, boardShortUrl);
        compareObjects(responsePutDto, expectedResponsePutDto);
        // GET
        validateGetAgainstPut(responsePutDto);
    }

    @Test
    public void P3_shouldUpdateBoardWhenAllParametersAreMissing() {
        // (We need to retrieve the current state of the previously created board to have something to compare it with,
        // to ensure that nothing has changed in this test after editing.)
        // GET
        responseGet = getGetBoard(boardId);
        assertThat(responseGet.statusCode()).isEqualTo(200);
        GET_GetBoardDto responseGetDto = deserializeAndValidate(responseGet, GET_GetBoardDto.class);
        // PUT
        responsePut = putUpdateBoard(boardId, null);
        assertThat(responsePut.statusCode()).isEqualTo(200);
        PUT_UpdateBoardDto responsePutDto = deserializeAndValidate(responsePut, PUT_UpdateBoardDto.class);
        compareObjects(responsePutDto, responseGetDto, PUT_UpdateBoardDto.FIELD_ORGANIZATION);
        // GET
        validateGetAgainstPut(responsePutDto);
    }

    @Test
    public void P4_shouldUpdateBoardWhenMostStringParametersHaveOnlyOneCharacterAndBooleansAreFalse() {

        boardName = getRandomSingleChar();

        PUT_UpdateBoardPayload payload = new PUT_UpdateBoardPayload.Builder()
                .setName(boardName)
                .setClosed(false)
                .setPrefsPermissionLevel("private")
                .setPrefsSelfJoin(false)
                .setPrefsCardCovers(false)
                .setPrefsHideVotes(false)
                .setPrefsInvitations("members")
                .setPrefsVoting("members")
                .setPrefsComments("members")
                .setPrefsBackground("orange")
                .setPrefsCardAging("pirate")
                .setPrefsCalendarFeedEnabled(false)
                .build();
        Map<String, Object> queryParams = payload.toQueryParams();

        // PUT
        responsePut = putUpdateBoard(boardId, queryParams);
        assertThat(responsePut.statusCode()).isEqualTo(200);
        PUT_UpdateBoardDto responsePutDto = deserializeAndValidate(responsePut, PUT_UpdateBoardDto.class);
        assertThat(responsePutDto.url).isNotEqualTo(boardUrl);
        assertThat(stripBoardNameFromUrl(responsePutDto.url)).isEqualTo(stripBoardNameFromUrl(boardUrl));
        PUT_UpdateBoardDto expectedResponsePutDto = prepareExpectedResponsePut(P4ExpectedPutBoardResponse, boardId, boardName, responsePutDto.url, boardShortUrl);
        expectedResponsePutDto.organization.memberships.getFirst().lastActive = responsePutDto.organization.memberships.getFirst().lastActive;
        compareObjects(responsePutDto, expectedResponsePutDto);
        // GET
        validateGetAgainstPut(responsePutDto);
    }

    @Test
    public void P5_shouldUpdateBoardWhenRemainingParametersAreProvidedRandomly() {

        String prefsVoting = pickRandom("org", "public");
        String prefsComments = pickRandom("org", "public");
        String prefsBackground = pickRandom("green", "red", "purple", "pink", "lime", "sky", "grey");
        String prefsBackgroundHex = switch (prefsBackground) {
            case "green" -> "#519839";
            case "red" -> "#B04632";
            case "purple" -> "#89609E";
            case "pink" -> "#CD5A91";
            case "lime" -> "#4BBF6B";
            case "sky" -> "#00AECC";
            case "grey" -> "#838C91";
            default -> "#0079BF"; // "blue" if not matched
        };

        PUT_UpdateBoardPayload payload = new PUT_UpdateBoardPayload.Builder()
                .setPrefsPermissionLevel("public")
                .setPrefsVoting(prefsVoting)
                .setPrefsComments(prefsComments)
                .setPrefsBackground(prefsBackground)
                .build();
        Map<String, Object> queryParams = payload.toQueryParams();

        // PUT
        responsePut = putUpdateBoard(boardId, queryParams);
        assertThat(responsePut.statusCode()).isEqualTo(200);
        PUT_UpdateBoardDto responsePutDto = deserializeAndValidate(responsePut, PUT_UpdateBoardDto.class);
        PUT_UpdateBoardDto expectedResponsePutDto = prepareExpectedResponsePut(P5ExpectedPutBoardResponse, boardId, boardName, boardUrl, boardShortUrl);
        expectedResponsePutDto.prefs.voting = prefsVoting;
        expectedResponsePutDto.prefs.comments = prefsComments;
        expectedResponsePutDto.prefs.background = prefsBackground;
        expectedResponsePutDto.prefs.backgroundColor = prefsBackgroundHex;
        expectedResponsePutDto.prefs.backgroundBottomColor = prefsBackgroundHex;
        expectedResponsePutDto.prefs.backgroundTopColor = prefsBackgroundHex;
        compareObjects(responsePutDto, expectedResponsePutDto);
        // GET
        validateGetAgainstPut(responsePutDto);
    }

    // --------------
    // NEGATIVE TESTS
    // --------------

    // name

    @Test
    public void N1_shouldNotUpdateBoardWhenNameIsEmptyString() {

        String expectedResponse = """
                {
                    "message": "invalid value for name",
                    "error": "ERROR"
                }
                """;

        PUT_UpdateBoardPayload payload = new PUT_UpdateBoardPayload.Builder()
                .setName("")
                .build();
        Map<String, Object> queryParams = payload.toQueryParams();

        // PUT
        responsePut = putUpdateBoard(boardId, queryParams);
        assertThat(responsePut.statusCode()).isEqualTo(400);
        compareObjectsJsonNode(responsePut, expectedResponse);
    }

    // subscribed

    @Test
    public void N2_shouldNotUpdateBoardWhenSubscribedNotExist() {

        PUT_UpdateBoardPayload payload = new PUT_UpdateBoardPayload.Builder()
                .setSubscribed("123456789098765432123456")
                .build();
        Map<String, Object> queryParams = payload.toQueryParams();

        // PUT
        responsePut = putUpdateBoard(boardId, queryParams);
        assertThat(responsePut.statusCode()).isEqualTo(400);
        assertThat(responsePut.getBody().asString()).isEqualTo("invalid value for subscribed");
    }

    @Test
    public void N3_shouldNotUpdateBoardWhenSubscribedIsIncompatibleWithRegEx() {

        PUT_UpdateBoardPayload payload = new PUT_UpdateBoardPayload.Builder()
                .setSubscribed("123abc")
                .build();
        Map<String, Object> queryParams = payload.toQueryParams();

        // PUT
        responsePut = putUpdateBoard(boardId, queryParams);
        assertThat(responsePut.statusCode()).isEqualTo(400);
        assertThat(responsePut.getBody().asString()).isEqualTo("invalid value for subscribed");
    }

    // idOrganization

    @Test
    public void N4_shouldNotUpdateBoardWhenIdOrganizationNotExist() {

        String expectedResponse = """
                {
                    "message": "unauthorized organization access"
                }
                """;

        PUT_UpdateBoardPayload payload = new PUT_UpdateBoardPayload.Builder()
                .setIdOrganization("123456789098765432123456")
                .build();
        Map<String, Object> queryParams = payload.toQueryParams();

        // PUT
        responsePut = putUpdateBoard(boardId, queryParams);
        assertThat(responsePut.statusCode()).isEqualTo(401);
        compareObjectsJsonNode(responsePut, expectedResponse);
    }

    @Test
    public void N5_shouldNotUpdateBoardWhenIdOrganizationIsIncompatibleWithRegEx() {

        String expectedResponse = """
                {
                    "message": "unauthorized organization."
                }
                """;

        PUT_UpdateBoardPayload payload = new PUT_UpdateBoardPayload.Builder()
                .setIdOrganization("123abc")
                .build();
        Map<String, Object> queryParams = payload.toQueryParams();

        // PUT
        responsePut = putUpdateBoard(boardId, queryParams);
        assertThat(responsePut.statusCode()).isEqualTo(401);
        compareObjectsJsonNode(responsePut, expectedResponse);
    }

    // prefs/permissionLevel

    @Test
    public void N6_shouldNotUpdateBoardWhenPrefsPermissionLevelIsOtherString() {

        PUT_UpdateBoardPayload payload = new PUT_UpdateBoardPayload.Builder()
                .setPrefsPermissionLevel("KeK")
                .build();
        Map<String, Object> queryParams = payload.toQueryParams();

        // PUT
        responsePut = putUpdateBoard(boardId, queryParams);
        assertThat(responsePut.statusCode()).isEqualTo(400);
        assertThat(responsePut.getBody().asString()).isEqualTo("invalid value for prefs/permissionLevel");
    }

    // prefs/invitations

    @Test
    public void N7_shouldNotUpdateBoardWhenPrefsInvitationsIsOtherString() {

        PUT_UpdateBoardPayload payload = new PUT_UpdateBoardPayload.Builder()
                .setPrefsInvitations("KeK")
                .build();
        Map<String, Object> queryParams = payload.toQueryParams();

        // PUT
        responsePut = putUpdateBoard(boardId, queryParams);
        assertThat(responsePut.statusCode()).isEqualTo(400);
        assertThat(responsePut.getBody().asString()).isEqualTo("invalid value for prefs/invitations");
    }

    // prefs/voting

    @Test
    public void N8_shouldNotUpdateBoardWhenPrefsVotingIsOtherString() {

        PUT_UpdateBoardPayload payload = new PUT_UpdateBoardPayload.Builder()
                .setPrefsVoting("KeK")
                .build();
        Map<String, Object> queryParams = payload.toQueryParams();

        // PUT
        responsePut = putUpdateBoard(boardId, queryParams);
        assertThat(responsePut.statusCode()).isEqualTo(400);
        assertThat(responsePut.getBody().asString()).isEqualTo("invalid value for prefs/voting");
    }

    // prefs/comments

    @Test
    public void N9_shouldNotUpdateBoardWhenPrefsCommentsIsOtherString() {

        PUT_UpdateBoardPayload payload = new PUT_UpdateBoardPayload.Builder()
                .setPrefsComments("KeK")
                .build();
        Map<String, Object> queryParams = payload.toQueryParams();

        // PUT
        responsePut = putUpdateBoard(boardId, queryParams);
        assertThat(responsePut.statusCode()).isEqualTo(400);
        assertThat(responsePut.getBody().asString()).isEqualTo("invalid value for prefs/comments");
    }

    // prefs/background

    @Test
    public void N10_shouldNotUpdateBoardWhenPrefsBackgroundIsOtherString() {

        String expectedResponse = """
                {
                    "message": "Invalid background",
                    "error": "ERROR"
                }
                """;

        PUT_UpdateBoardPayload payload = new PUT_UpdateBoardPayload.Builder()
                .setPrefsBackground("KeK")
                .build();
        Map<String, Object> queryParams = payload.toQueryParams();

        // PUT
        responsePut = putUpdateBoard(boardId, queryParams);
        assertThat(responsePut.statusCode()).isEqualTo(400);
        compareObjectsJsonNode(responsePut, expectedResponse);
    }

    // prefs/cardAging

    @Test
    public void N11_shouldNotUpdateBoardWhenPrefsCardAgingIsOtherString() {

        PUT_UpdateBoardPayload payload = new PUT_UpdateBoardPayload.Builder()
                .setPrefsCardAging("KeK")
                .build();
        Map<String, Object> queryParams = payload.toQueryParams();

        // PUT
        responsePut = putUpdateBoard(boardId, queryParams);
        assertThat(responsePut.statusCode()).isEqualTo(400);
        assertThat(responsePut.getBody().asString()).isEqualTo("invalid value for prefs/cardAging");
    }
}
