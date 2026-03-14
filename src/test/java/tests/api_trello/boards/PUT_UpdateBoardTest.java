package tests.api_trello.boards;

import base.TestBase;
import configuration.Config;
import dto.boards.GET_GetBoardDto;
import dto.boards.POST_CreateBoardDto;
import dto.boards.PUT_UpdateBoardDto;
import endpoints.boards.DEL_DeleteBoardEndpoint;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import payloads.boards.PUT_UpdateBoardPayload;
import utils.UtilsString;

import java.net.URL;

import static endpoints.boards.DEL_DeleteBoardEndpoint.deleteDeleteBoard;
import static endpoints.boards.GET_GetBoardEndpoint.getGetBoard;
import static endpoints.boards.POST_CreateBoardEndpoint.postCreateBoard;
import static endpoints.boards.PUT_UpdateBoardEndpoint.putUpdateBoard;
import static expected_responses.boards.PUT_UpdateBoardExpected.*;
import static org.assertj.core.api.Assertions.assertThat;
import static utils.UtilsCompare.compareObjects;
import static utils.UtilsCompare.compareResponseWithJson;
import static utils.UtilsRandom.pickRandom;
import static utils.UtilsString.getAllCharactersSetInRandomOrder;
import static utils.response.UtilsResponseDeserializer.deserializeAndValidateJson;
import static utils.response.UtilsResponseDeserializer.deserializeJson;
import static utils_tests.boards.POST_CreateBoardUtils.generateRandomBoardName;
import static utils_tests.boards.PUT_UpdateBoardUtils.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PUT_UpdateBoardTest extends TestBase {

    // ==========================================================================================================
    // FIELDS
    // ==========================================================================================================

    // --------
    // RESPONSE
    // --------

    private Response responsePost;
    private Response responsePut;
    private Response responseGet;
    private Response responseDelete;

    // ---------------
    // CLASS VARIABLES
    // ---------------

    private String trelloId = Config.getTrelloId();
    // BOARD (POST) – changing variables
    private String boardId;
    private String boardName;
    private URL boardUrl;
    private URL boardShortUrl;

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
        POST_CreateBoardDto responsePostDto = deserializeJson(responsePost, POST_CreateBoardDto.class);
        boardId = responsePostDto.id;
        boardName = responsePostDto.name;
        boardUrl = responsePostDto.url;
        boardShortUrl = responsePostDto.shortUrl;
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
    // DEBUG
    // ==========================================================================================================

    // @Test
    public void deleteBoard() {
        String yourBoardId = "68724f5bfffa6577a4dc0dbb";
        responseDelete = DEL_DeleteBoardEndpoint.deleteDeleteBoard(yourBoardId);
        assertThat(responseDelete.statusCode()).isEqualTo(200);
    }

    // ==========================================================================================================
    // POSITIVE TESTS
    // ==========================================================================================================

    @Test
    public void P1_shouldUpdateBoardWhenMostStringParametersHaveSpecialCharactersAndBooleansAreTrue() {
        // POST
        String boardId = null;
        // PUT
        boardName = getAllCharactersSetInRandomOrder();
        String desc = getAllCharactersSetInRandomOrder();

        PUT_UpdateBoardPayload payload = new PUT_UpdateBoardPayload.Builder()
                .setName(boardName)
                .setDesc(desc)
                .setClosed(true) // NOTE: Closed boards cannot edit. Discussed with Trello.
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

        // POST (We need to create a separate, independent board because it should not be editable once closed, so this step breaks the rest of the tests.)
        Response responsePost = postCreateBoard(generateRandomBoardName(), null);
        assertThat(responsePost.statusCode()).isEqualTo(200);
        try {
            POST_CreateBoardDto responsePostDto = deserializeJson(responsePost, POST_CreateBoardDto.class);
            boardId = responsePostDto.id;
            String postBoardName = responsePostDto.name;
            URL postBoardUrl = responsePostDto.url;
            URL postBoardShortUrl = responsePostDto.shortUrl;
            // PUT
            responsePut = putUpdateBoard(boardId, payload);
            assertThat(responsePut.statusCode()).isEqualTo(200);
            PUT_UpdateBoardDto responsePutDto = deserializeAndValidateJson(responsePut, PUT_UpdateBoardDto.class);
            assertThat(responsePutDto.url).isNotEqualTo(postBoardUrl);
            assertThat(stripBoardNameFromUrl(responsePutDto.url)).isEqualTo(stripBoardNameFromUrl(postBoardUrl));
            PUT_UpdateBoardDto expectedResponsePutDto = prepareExpectedResponsePut(P1ExpectedPutBoardResponse, boardId, boardName, responsePutDto.url, postBoardShortUrl);
            expectedResponsePutDto.desc = desc;
            expectedResponsePutDto.organization.memberships.getFirst().lastActive = responsePutDto.organization.memberships.getFirst().lastActive;
            compareObjects(responsePutDto, expectedResponsePutDto);
            // GET
            validateGetAgainstPut(responsePutDto);
        } finally {
            // DELETE
            if (boardId != null) {
                responseDelete = deleteDeleteBoard(boardId);
                assertThat(responseDelete.statusCode()).isEqualTo(200);
                boardId = null;
            }
        }
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

        // PUT
        responsePut = putUpdateBoard(boardId, payload);
        assertThat(responsePut.statusCode()).isEqualTo(200);
        PUT_UpdateBoardDto responsePutDto = deserializeAndValidateJson(responsePut, PUT_UpdateBoardDto.class);
        PUT_UpdateBoardDto expectedResponsePutDto = prepareExpectedResponsePut(P2ExpectedPutBoardResponse, boardId, boardName, boardUrl, boardShortUrl);
        compareObjects(responsePutDto, expectedResponsePutDto);
        // GET
        validateGetAgainstPut(responsePutDto);
    }

    @Test
    public void P3_shouldUpdateBoardWhenAllParametersAreMissing() {
        // GET (Get current status of {BOARD})
        responseGet = getGetBoard(boardId);
        assertThat(responseGet.statusCode()).isEqualTo(200);
        GET_GetBoardDto responseGetDto = deserializeAndValidateJson(responseGet, GET_GetBoardDto.class);
        // PUT
        responsePut = putUpdateBoard(boardId, null);
        assertThat(responsePut.statusCode()).isEqualTo(200);
        PUT_UpdateBoardDto responsePutDto = deserializeAndValidateJson(responsePut, PUT_UpdateBoardDto.class);
        compareObjects(responsePutDto, responseGetDto, PUT_UpdateBoardDto.FIELD_ORGANIZATION);
        // GET
        validateGetAgainstPut(responsePutDto);
    }

    @Test
    public void P4_shouldUpdateBoardWhenMostStringParametersHaveOnlyOneCharacterAndBooleansAreFalse() {

        boardName = UtilsString.getRandomSingleCharAlphanumeric();

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

        // PUT
        responsePut = putUpdateBoard(boardId, payload);
        assertThat(responsePut.statusCode()).isEqualTo(200);
        PUT_UpdateBoardDto responsePutDto = deserializeAndValidateJson(responsePut, PUT_UpdateBoardDto.class);
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

        // PUT
        responsePut = putUpdateBoard(boardId, payload);
        assertThat(responsePut.statusCode()).isEqualTo(200);
        PUT_UpdateBoardDto responsePutDto = deserializeAndValidateJson(responsePut, PUT_UpdateBoardDto.class);
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

    // ==========================================================================================================
    // NEGATIVE TESTS
    // ==========================================================================================================

    // --
    // id
    // --

    @Test
    public void N12_shouldNotUpdateBoardWithNonExistentId() {
        // ARRANGE
        String boardId = "999999999999999999999999";
        PUT_UpdateBoardPayload payload = new PUT_UpdateBoardPayload.Builder()
                .setName("Board name – negative test")
                .build();
        // ACT
        responsePut = putUpdateBoard(boardId, payload);
        // ASSERT
        assertThat(responsePut.statusCode()).isEqualTo(404);
        assertThat(responsePut.getBody().asString()).isEqualTo("The requested resource was not found.");
    }

    @Test
    public void N13_shouldNotUpdateBoardWithIncorrectId() {
        // ARRANGE
        String boardId = "Kek123";
        PUT_UpdateBoardPayload payload = new PUT_UpdateBoardPayload.Builder()
                .setName("Board name – negative test")
                .build();
        // ACT
        responsePut = putUpdateBoard(boardId, payload);
        // ASSERT
        assertThat(responsePut.statusCode()).isEqualTo(400);
        assertThat(responsePut.getBody().asString()).isEqualTo("invalid id");
    }

    // ----
    // name
    // ----

    @Test
    public void N1_shouldNotUpdateBoardWhenNameIsEmptyString() {
        // ARRANGE
        String expectedResponse = """
                {
                    "message": "invalid value for name",
                    "error": "ERROR"
                }
                """;
        PUT_UpdateBoardPayload payload = new PUT_UpdateBoardPayload.Builder()
                .setName("")
                .build();
        // ACT
        responsePut = putUpdateBoard(boardId, payload);
        // ASSERT
        assertThat(responsePut.statusCode()).isEqualTo(400);
        compareResponseWithJson(responsePut, expectedResponse);
    }

    // ----------
    // subscribed
    // ----------

    @Test
    public void N2_shouldNotUpdateBoardWhenSubscribedNotExist() {
        // ARRANGE
        PUT_UpdateBoardPayload payload = new PUT_UpdateBoardPayload.Builder()
                .setSubscribed("123456789098765432123456")
                .build();
        // ACT
        responsePut = putUpdateBoard(boardId, payload);
        // ASSERT
        assertThat(responsePut.statusCode()).isEqualTo(400);
        assertThat(responsePut.getBody().asString()).isEqualTo("invalid value for subscribed");
    }

    @Test
    public void N3_shouldNotUpdateBoardWhenSubscribedIsIncompatibleWithRegEx() {
        // ARRANGE
        PUT_UpdateBoardPayload payload = new PUT_UpdateBoardPayload.Builder()
                .setSubscribed("123abc")
                .build();
        // ACT
        responsePut = putUpdateBoard(boardId, payload);
        // ASSERT
        assertThat(responsePut.statusCode()).isEqualTo(400);
        assertThat(responsePut.getBody().asString()).isEqualTo("invalid value for subscribed");
    }

    // --------------
    // idOrganization
    // --------------

    @Test
    public void N4_shouldNotUpdateBoardWhenIdOrganizationNotExist() {
        // ARRANGE
        String expectedResponse = """
                {
                    "message": "unauthorized organization access"
                }
                """;
        PUT_UpdateBoardPayload payload = new PUT_UpdateBoardPayload.Builder()
                .setIdOrganization("123456789098765432123456")
                .build();
        // ACT
        responsePut = putUpdateBoard(boardId, payload);
        // ASSERT
        assertThat(responsePut.statusCode()).isEqualTo(401);
        compareResponseWithJson(responsePut, expectedResponse);
    }

    @Test
    public void N5_shouldNotUpdateBoardWhenIdOrganizationIsIncompatibleWithRegEx() {
        // ARRANGE
        String expectedResponse = """
                {
                    "message": "unauthorized organization."
                }
                """;
        PUT_UpdateBoardPayload payload = new PUT_UpdateBoardPayload.Builder()
                .setIdOrganization("123abc")
                .build();
        // ACT
        responsePut = putUpdateBoard(boardId, payload);
        // ASSERT
        assertThat(responsePut.statusCode()).isEqualTo(401);
        compareResponseWithJson(responsePut, expectedResponse);
    }

    // ---------------------
    // prefs/permissionLevel
    // ---------------------

    @Test
    public void N6_shouldNotUpdateBoardWhenPrefsPermissionLevelIsOtherString() {
        // ARRANGE
        PUT_UpdateBoardPayload payload = new PUT_UpdateBoardPayload.Builder()
                .setPrefsPermissionLevel("KeK")
                .build();
        // ACT
        responsePut = putUpdateBoard(boardId, payload);
        // ASSERT
        assertThat(responsePut.statusCode()).isEqualTo(400);
        assertThat(responsePut.getBody().asString()).isEqualTo("invalid value for prefs/permissionLevel");
    }

    // -----------------
    // prefs/invitations
    // -----------------

    @Test
    public void N7_shouldNotUpdateBoardWhenPrefsInvitationsIsOtherString() {
        // ARRANGE
        PUT_UpdateBoardPayload payload = new PUT_UpdateBoardPayload.Builder()
                .setPrefsInvitations("KeK")
                .build();
        // ACT
        responsePut = putUpdateBoard(boardId, payload);
        // ASSERT
        assertThat(responsePut.statusCode()).isEqualTo(400);
        assertThat(responsePut.getBody().asString()).isEqualTo("invalid value for prefs/invitations");
    }

    // ------------
    // prefs/voting
    // ------------

    @Test
    public void N8_shouldNotUpdateBoardWhenPrefsVotingIsOtherString() {
        // ARRANGE
        PUT_UpdateBoardPayload payload = new PUT_UpdateBoardPayload.Builder()
                .setPrefsVoting("KeK")
                .build();
        // ACT
        responsePut = putUpdateBoard(boardId, payload);
        // ASSERT
        assertThat(responsePut.statusCode()).isEqualTo(400);
        assertThat(responsePut.getBody().asString()).isEqualTo("invalid value for prefs/voting");
    }

    // prefs/comments

    @Test
    public void N9_shouldNotUpdateBoardWhenPrefsCommentsIsOtherString() {
        // ARRANGE
        PUT_UpdateBoardPayload payload = new PUT_UpdateBoardPayload.Builder()
                .setPrefsComments("KeK")
                .build();
        // ACT
        responsePut = putUpdateBoard(boardId, payload);
        // ASSERT
        assertThat(responsePut.statusCode()).isEqualTo(400);
        assertThat(responsePut.getBody().asString()).isEqualTo("invalid value for prefs/comments");
    }

    // ----------------
    // prefs/background
    // ----------------

    @Test
    public void N10_shouldNotUpdateBoardWhenPrefsBackgroundIsOtherString() {
        // ARRANGE
        String expectedResponse = """
                {
                    "message": "Invalid background",
                    "error": "ERROR"
                }
                """;
        PUT_UpdateBoardPayload payload = new PUT_UpdateBoardPayload.Builder()
                .setPrefsBackground("KeK")
                .build();
        // ACT
        responsePut = putUpdateBoard(boardId, payload);
        // ASSERT
        assertThat(responsePut.statusCode()).isEqualTo(400);
        compareResponseWithJson(responsePut, expectedResponse);
    }

    // ---------------
    // prefs/cardAging
    // ---------------

    @Test
    public void N11_shouldNotUpdateBoardWhenPrefsCardAgingIsOtherString() {
        // ARRANGE
        PUT_UpdateBoardPayload payload = new PUT_UpdateBoardPayload.Builder()
                .setPrefsCardAging("KeK")
                .build();
        // ACT
        responsePut = putUpdateBoard(boardId, payload);
        // ASSERT
        assertThat(responsePut.statusCode()).isEqualTo(400);
        assertThat(responsePut.getBody().asString()).isEqualTo("invalid value for prefs/cardAging");
    }
}
