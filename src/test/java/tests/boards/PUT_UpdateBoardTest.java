package tests.boards;

import base.TestBase;
import configuration.Config;
import dto.boards.POST_CreateBoardDto;
import dto.boards.PUT_UpdateBoardDto;
import org.junit.jupiter.api.*;
import payloads.boards.PUT_UpdateBoardPayload;

import java.net.URL;
import java.util.Map;

import static endpoints.boards.DELETE_DeleteBoard.deleteDeleteBoard;
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
    private String boardIdPositive;
    private String boardIdNegative;
    private String boardName;
    private URL boardUrl;
    private URL boardShortUrl;

    // FOR POSITIVE TESTS

    @BeforeEach
    public void setUpCreateBoardForPositiveTests(TestInfo testInfo) {
        if (testInfo.getTags().contains(testTagPositive)) {
            responsePost = postCreateBoard(generateRandomBoardName(), null);
            assertThat(responsePost.statusCode()).isEqualTo(200);
            POST_CreateBoardDto responsePostDto = deserializeJson(responsePost, POST_CreateBoardDto.class);
            boardIdPositive = responsePostDto.id;
            boardName = responsePostDto.name;
            boardUrl = responsePostDto.url;
            boardShortUrl = responsePostDto.shortUrl;
        }
    }

    @AfterEach
    public void tearDownDeleteBoardForPositiveTests(TestInfo testInfo) {
        if (testInfo.getTags().contains(testTagPositive) && boardIdPositive != null) {
            responseDelete = deleteDeleteBoard(boardIdPositive);
            assertThat(responseDelete.statusCode()).isEqualTo(200);
            boardIdPositive = null;
        }
    }

    // FOR NEGATIVE TESTS

    @BeforeAll
    public void setUpCreateBoardForNegativeTests(TestInfo testInfo) {
        if (testInfo.getTags().contains(testTagNegative)) {
            responsePost = postCreateBoard(generateRandomBoardName(), null);
            assertThat(responsePost.statusCode()).isEqualTo(200);
            boardIdNegative = deserializeJson(responsePost, POST_CreateBoardDto.class).id;
        }
    }

    @AfterAll
    public void tearDownDeleteBoardForNegativeTests(TestInfo testInfo) {
        if (testInfo.getTags().contains(testTagNegative) && boardIdNegative != null) {
            responseDelete = deleteDeleteBoard(boardIdNegative);
            assertThat(responseDelete.statusCode()).isEqualTo(200);
            boardIdNegative = null;
        }
    }

    // --------------
    // POSITIVE TESTS
    // --------------

    @Test
    @Tag(testTagPositive)
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
                .setPrefsBackground("blue")
                .setPrefsCardAging("regular")
                .setPrefsCalendarFeedEnabled(true)
                .build();
        Map<String, Object> queryParams = payload.toQueryParams();

        // PUT
        responsePut = putUpdateBoard(boardIdPositive, queryParams);
        assertThat(responsePut.statusCode()).isEqualTo(200);
        PUT_UpdateBoardDto responsePutDto = deserializeAndValidate(responsePut, PUT_UpdateBoardDto.class);
        assertThat(responsePutDto.url).isNotEqualTo(boardUrl);
        assertThat(stripBoardNameFromUrl(responsePutDto.url)).isEqualTo(stripBoardNameFromUrl(boardUrl));
        PUT_UpdateBoardDto expectedResponsePutDto = prepareExpectedResponsePut(P1ExpectedPutBoardResponse, boardIdPositive, boardName, responsePutDto.url, boardShortUrl);
        expectedResponsePutDto.desc = desc;
        expectedResponsePutDto.organization.memberships.getFirst().lastActive = responsePutDto.organization.memberships.getFirst().lastActive;
        compareObjects(responsePutDto, expectedResponsePutDto);
        // GET
        validateGetAgainstPut(responsePutDto);
    }

    @Test
    @Tag(testTagPositive)
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
        responsePut = putUpdateBoard(boardIdPositive, queryParams);
        assertThat(responsePut.statusCode()).isEqualTo(200);
        PUT_UpdateBoardDto responsePutDto = deserializeAndValidate(responsePut, PUT_UpdateBoardDto.class);
        PUT_UpdateBoardDto expectedResponsePutDto = prepareExpectedResponsePut(P2ExpectedPutBoardResponse, boardIdPositive, boardName, boardUrl, boardShortUrl);
        compareObjects(responsePutDto, expectedResponsePutDto);
        // GET
        validateGetAgainstPut(responsePutDto);
    }

    @Test
    @Tag(testTagPositive)
    public void P3_shouldUpdateBoardWhenAllParametersAreMissing() {

        // PUT
        responsePut = putUpdateBoard(boardIdPositive, null);
        assertThat(responsePut.statusCode()).isEqualTo(200);
        PUT_UpdateBoardDto responsePutDto = deserializeAndValidate(responsePut, PUT_UpdateBoardDto.class);
        PUT_UpdateBoardDto expectedResponsePutDto = prepareExpectedResponsePut(P3ExpectedPutBoardResponse, boardIdPositive, boardName, boardUrl, boardShortUrl);
        compareObjects(responsePutDto, expectedResponsePutDto);
        // GET
        validateGetAgainstPut(responsePutDto);
    }

    @Test
    @Tag(testTagPositive)
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
        responsePut = putUpdateBoard(boardIdPositive, queryParams);
        assertThat(responsePut.statusCode()).isEqualTo(200);
        PUT_UpdateBoardDto responsePutDto = deserializeAndValidate(responsePut, PUT_UpdateBoardDto.class);
        assertThat(responsePutDto.url).isNotEqualTo(boardUrl);
        assertThat(stripBoardNameFromUrl(responsePutDto.url)).isEqualTo(stripBoardNameFromUrl(boardUrl));
        PUT_UpdateBoardDto expectedResponsePutDto = prepareExpectedResponsePut(P4ExpectedPutBoardResponse, boardIdPositive, boardName, responsePutDto.url, boardShortUrl);
        expectedResponsePutDto.organization.memberships.getFirst().lastActive = responsePutDto.organization.memberships.getFirst().lastActive;
        compareObjects(responsePutDto, expectedResponsePutDto);
        // GET
        validateGetAgainstPut(responsePutDto);
    }

    @Test
    @Tag(testTagPositive)
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
        responsePut = putUpdateBoard(boardIdPositive, queryParams);
        assertThat(responsePut.statusCode()).isEqualTo(200);
        PUT_UpdateBoardDto responsePutDto = deserializeAndValidate(responsePut, PUT_UpdateBoardDto.class);
        PUT_UpdateBoardDto expectedResponsePutDto = prepareExpectedResponsePut(P5ExpectedPutBoardResponse, boardIdPositive, boardName, boardUrl, boardShortUrl);
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
    @Tag(testTagNegative)
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
        responsePut = putUpdateBoard(boardIdNegative, queryParams);
        assertThat(responsePut.statusCode()).isEqualTo(400);
        compareObjectsJsonNode(responsePut, expectedResponse);
    }

    // subscribed

    @Test
    @Tag(testTagNegative)
    public void N2_shouldNotUpdateBoardWhenSubscribedNotExist() {

        PUT_UpdateBoardPayload payload = new PUT_UpdateBoardPayload.Builder()
                .setSubscribed("123456789098765432123456")
                .build();
        Map<String, Object> queryParams = payload.toQueryParams();

        // PUT
        responsePut = putUpdateBoard(boardIdNegative, queryParams);
        assertThat(responsePut.statusCode()).isEqualTo(400);
        assertThat(responsePut.getBody().asString()).isEqualTo("invalid value for subscribed");
    }

    @Test
    @Tag(testTagNegative)
    public void N3_shouldNotUpdateBoardWhenSubscribedIsIncompatibleWithRegEx() {

        PUT_UpdateBoardPayload payload = new PUT_UpdateBoardPayload.Builder()
                .setSubscribed("123abc")
                .build();
        Map<String, Object> queryParams = payload.toQueryParams();

        // PUT
        responsePut = putUpdateBoard(boardIdNegative, queryParams);
        assertThat(responsePut.statusCode()).isEqualTo(400);
        assertThat(responsePut.getBody().asString()).isEqualTo("invalid value for subscribed");
    }

    // idOrganization

    @Test
    @Tag(testTagNegative)
    public void N4_shouldNotUpdateBoardWhenIdOrganizationNotExist() {

        PUT_UpdateBoardPayload payload = new PUT_UpdateBoardPayload.Builder()
                .setIdOrganization("123456789098765432123456")
                .build();
        Map<String, Object> queryParams = payload.toQueryParams();

        // PUT
        responsePut = putUpdateBoard(boardIdNegative, queryParams);
        assertThat(responsePut.statusCode()).isEqualTo(400);
        assertThat(responsePut.getBody().asString()).isEqualTo("invalid id");
    }

    @Test
    @Tag(testTagNegative)
    public void N5_shouldNotUpdateBoardWhenIdOrganizationIsIncompatibleWithRegEx() {

        PUT_UpdateBoardPayload payload = new PUT_UpdateBoardPayload.Builder()
                .setIdOrganization("123abc")
                .build();
        Map<String, Object> queryParams = payload.toQueryParams();

        // PUT
        responsePut = putUpdateBoard(boardIdNegative, queryParams);
        assertThat(responsePut.statusCode()).isEqualTo(400);
        assertThat(responsePut.getBody().asString()).isEqualTo("invalid id");
    }

    // prefs/permissionLevel

    @Test
    @Tag(testTagNegative)
    public void N6_shouldNotUpdateBoardWhenPrefsPermissionLevelIsOtherString() {

        PUT_UpdateBoardPayload payload = new PUT_UpdateBoardPayload.Builder()
                .setPrefsPermissionLevel("KeK")
                .build();
        Map<String, Object> queryParams = payload.toQueryParams();

        // PUT
        responsePut = putUpdateBoard(boardIdNegative, queryParams);
        assertThat(responsePut.statusCode()).isEqualTo(400);
        assertThat(responsePut.getBody().asString()).isEqualTo("invalid value for prefs/permissionLevel");
    }

    // prefs/invitations

    @Test
    @Tag(testTagNegative)
    public void N7_shouldNotUpdateBoardWhenPrefsInvitationsIsOtherString() {

        PUT_UpdateBoardPayload payload = new PUT_UpdateBoardPayload.Builder()
                .setPrefsInvitations("KeK")
                .build();
        Map<String, Object> queryParams = payload.toQueryParams();

        // PUT
        responsePut = putUpdateBoard(boardIdNegative, queryParams);
        assertThat(responsePut.statusCode()).isEqualTo(400);
        assertThat(responsePut.getBody().asString()).isEqualTo("invalid value for prefs/invitations");
    }

    // prefs/voting

    @Test
    @Tag(testTagNegative)
    public void N8_shouldNotUpdateBoardWhenPrefsVotingIsOtherString() {

        PUT_UpdateBoardPayload payload = new PUT_UpdateBoardPayload.Builder()
                .setPrefsVoting("KeK")
                .build();
        Map<String, Object> queryParams = payload.toQueryParams();

        // PUT
        responsePut = putUpdateBoard(boardIdNegative, queryParams);
        assertThat(responsePut.statusCode()).isEqualTo(400);
        assertThat(responsePut.getBody().asString()).isEqualTo("invalid value for prefs/voting");
    }

    // prefs/comments

    @Test
    @Tag(testTagNegative)
    public void N9_shouldNotUpdateBoardWhenPrefsCommentsIsOtherString() {

        PUT_UpdateBoardPayload payload = new PUT_UpdateBoardPayload.Builder()
                .setPrefsComments("KeK")
                .build();
        Map<String, Object> queryParams = payload.toQueryParams();

        // PUT
        responsePut = putUpdateBoard(boardIdNegative, queryParams);
        assertThat(responsePut.statusCode()).isEqualTo(400);
        assertThat(responsePut.getBody().asString()).isEqualTo("invalid value for prefs/comments");
    }

    // prefs/background

    @Test
    @Tag(testTagNegative)
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
        responsePut = putUpdateBoard(boardIdNegative, queryParams);
        assertThat(responsePut.statusCode()).isEqualTo(400);
        compareObjectsJsonNode(responsePut, expectedResponse);
    }

    // prefs/cardAging

    @Test
    @Tag(testTagNegative)
    public void N11_shouldNotUpdateBoardWhenPrefsCardAgingIsOtherString() {

        PUT_UpdateBoardPayload payload = new PUT_UpdateBoardPayload.Builder()
                .setPrefsCardAging("KeK")
                .build();
        Map<String, Object> queryParams = payload.toQueryParams();

        // PUT
        responsePut = putUpdateBoard(boardIdNegative, queryParams);
        assertThat(responsePut.statusCode()).isEqualTo(400);
        assertThat(responsePut.getBody().asString()).isEqualTo("invalid value for prefs/cardAging");
    }
}
