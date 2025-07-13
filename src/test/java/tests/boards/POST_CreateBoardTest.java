package tests.boards;

import base.TestBase;
import dto.boards.POST_CreateBoardDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import payloads.boards.POST_CreateBoardPayload;

import java.util.Map;

import static endpoints.boards.DELETE_DeleteBoard.deleteDeleteBoard;
import static endpoints.boards.POST_CreateBoard.postCreateBoard;
import static endpoints.boards.POST_CreateBoard.postCreateBoardMissingRequiredParameters;
import static expected_responses.boards.POST_CreateBoardExpected.*;
import static org.assertj.core.api.Assertions.assertThat;
import static utils.UtilsCommon.pickRandom;
import static utils.UtilsCompare.*;
import static utils.UtilsResponse.deserializeAndValidate;
import static utils_tests.boards.POST_CreateBoardUtils.*;

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public class POST_CreateBoardTest extends TestBase {

    private String boardId;
    // TODO: Przenieść 'trelloId' do jakiegoś pliku konfiguracyjnego
    private String trelloId = "67d9d5e34d7b900257deed0e";

    @AfterEach
    public void tearDown() {
        if (boardId != null) {
            responseDelete = deleteDeleteBoard(boardId);
            assertThat(responseDelete.statusCode()).isEqualTo(200);
        }
    }

    // --------------
    // POSITIVE TESTS
    // --------------

    @Test
    public void P1_shouldCreateBoardWhoseNameContainsSpecialCharactersAndNumbers() {

        String boardName = "ŻÓŁĆżółć 1234567890 ~`!@#$%^&*()_+{}[];':\",./<>?-=\\" + " " + faker.number().randomNumber();

        // POST
        responsePost = postCreateBoard(boardName, null);
        assertThat(responsePost.statusCode()).isEqualTo(200);
        boardId = responsePost.jsonPath().getString("id");
        POST_CreateBoardDto responsePostDto = deserializeAndValidate(responsePost, POST_CreateBoardDto.class);
        POST_CreateBoardDto expectedResponsePostDto = prepareExpectedResponsePost(P1ExpectedPostBoardResponse, responsePostDto, boardName);
        compareObjects(responsePostDto, expectedResponsePostDto);
        // GET
        validateGetAgainstPost(responsePostDto);
    }

    @Test
    public void P2_shouldCreateBoardWhenMostParametersAreGiven() {

        String boardName = "F";

        POST_CreateBoardPayload payload = new POST_CreateBoardPayload.Builder()
                .setDefaultLabels(true)
                .setDefaultLists(true)
                .setDesc("ŻÓŁĆżółć 1234567890 ~`!@#$%^&*()_+{}[];':\",./<>?-=\\")
                .setIdOrganization(trelloId)
                .setKeepFromSource("none")
                .setPowerUps("all")
                .setPrefsPermissionLevel("private")
                .setPrefsVoting("disabled")
                .setPrefsComments("members")
                .setPrefsInvitations("members")
                .setPrefsSelfJoin(true)
                .setPrefsCardCovers(true)
                .setPrefsBackground("blue")
                .setPrefsCardAging("regular")
                .build();
        Map<String, Object> queryParams = payload.toQueryParams();

        // POST
        responsePost = postCreateBoard(boardName, queryParams);
        assertThat(responsePost.statusCode()).isEqualTo(200);
        boardId = responsePost.jsonPath().getString("id");
        POST_CreateBoardDto responsePostDto = deserializeAndValidate(responsePost, POST_CreateBoardDto.class);
        POST_CreateBoardDto expectedResponsePostDto = prepareExpectedResponsePost(P2ExpectedPostBoardResponse, responsePostDto, boardName);
        compareObjects(responsePostDto, expectedResponsePostDto);
        // GET
        validateGetAgainstPost(responsePostDto);
    }

    @Test
    public void P3_shouldCreateBoardWhenMostParametersAreGiven() {

        String boardName = generateRandomBoardName();
        String desc = generateRandomDesc();

        POST_CreateBoardPayload payload = new POST_CreateBoardPayload.Builder()
                .setDefaultLabels(false)
                .setDefaultLists(false)
                .setDesc(desc)
                .setIdOrganization(null)
                .setIdBoardSource(null)
                .setKeepFromSource("cards")
                .setPowerUps("calendar")
                .setPrefsPermissionLevel("org")
                .setPrefsVoting("members")
                .setPrefsComments("observers")
                .setPrefsInvitations("admins")
                .setPrefsSelfJoin(false)
                .setPrefsCardCovers(false)
                .setPrefsBackground("orange")
                .setPrefsCardAging("pirate")
                .build();
        Map<String, Object> queryParams = payload.toQueryParams();

        // POST
        responsePost = postCreateBoard(boardName, queryParams);
        assertThat(responsePost.statusCode()).isEqualTo(200);
        boardId = responsePost.jsonPath().getString("id");
        POST_CreateBoardDto responsePostDto = deserializeAndValidate(responsePost, POST_CreateBoardDto.class);
        POST_CreateBoardDto expectedResponsePostDto = prepareExpectedResponsePost(P3ExpectedPostBoardResponse, responsePostDto, boardName);
        expectedResponsePostDto.desc = desc;
        compareObjects(responsePostDto, expectedResponsePostDto);
        // GET
        validateGetAgainstPost(responsePostDto);
    }

    @Test
    public void P4_shouldCreateBoardWhenMostParametersAreNull() {

        String boardName = generateRandomBoardName();

        POST_CreateBoardPayload payload = new POST_CreateBoardPayload.Builder()
                .setDefaultLabels(null)
                .setDefaultLists(null)
                .setDesc(null)
                .setKeepFromSource(null)
                .setPowerUps(null)
                .setPrefsPermissionLevel(null)
                .setPrefsVoting(null)
                .setPrefsComments(null)
                .setPrefsInvitations(null)
                .setPrefsSelfJoin(null)
                .setPrefsCardCovers(null)
                .setPrefsBackground(null)
                .setPrefsCardAging(null)
                .build();
        Map<String, Object> queryParams = payload.toQueryParams();

        // POST
        responsePost = postCreateBoard(boardName, queryParams);
        assertThat(responsePost.statusCode()).isEqualTo(200);
        boardId = responsePost.jsonPath().getString("id");
        POST_CreateBoardDto responsePostDto = deserializeAndValidate(responsePost, POST_CreateBoardDto.class);
        POST_CreateBoardDto expectedResponsePostDto = prepareExpectedResponsePost(P4ExpectedPostBoardResponse, responsePostDto, boardName);
        compareObjects(responsePostDto, expectedResponsePostDto);
        // GET
        validateGetAgainstPost(responsePostDto);
    }

    @Test
    public void P5_shouldCreateBoardWithRemainingRandomParameters() {

        String boardName = generateRandomBoardName();
        String powerUps = pickRandom("cardAging", "recap", "voting");
        String prefsVoting = pickRandom("observers", "org", "public");
        String prefsComments = pickRandom("disabled", "org", "public");
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

        POST_CreateBoardPayload payload = new POST_CreateBoardPayload.Builder()
                .setPowerUps(powerUps)
                .setPrefsPermissionLevel("public")
                .setPrefsVoting(prefsVoting)
                .setPrefsComments(prefsComments)
                .setPrefsBackground(prefsBackground)
                .build();
        Map<String, Object> queryParams = payload.toQueryParams();

        // POST
        responsePost = postCreateBoard(boardName, queryParams);
        assertThat(responsePost.statusCode()).isEqualTo(200);
        boardId = responsePost.jsonPath().getString("id");
        POST_CreateBoardDto responsePostDto = deserializeAndValidate(responsePost, POST_CreateBoardDto.class);
        POST_CreateBoardDto expectedResponsePostDto = prepareExpectedResponsePost(P5ExpectedPostBoardResponse, responsePostDto, boardName);
        expectedResponsePostDto.prefs.voting = prefsVoting;
        expectedResponsePostDto.prefs.comments = prefsComments;
        expectedResponsePostDto.prefs.background = prefsBackground;
        expectedResponsePostDto.prefs.backgroundColor = prefsBackgroundHex;
        expectedResponsePostDto.prefs.backgroundBottomColor = prefsBackgroundHex;
        expectedResponsePostDto.prefs.backgroundTopColor = prefsBackgroundHex;
        compareObjects(responsePostDto, expectedResponsePostDto);
        // GET
        validateGetAgainstPost(responsePostDto);
    }

    // --------------
    // NEGATIVE TESTS
    // --------------

    // name

    @Test
    public void N1_shouldNotCreateBoardWhenNameWasNotGiven() {
        responsePost = postCreateBoardMissingRequiredParameters();
        assertThat(responsePost.statusCode()).isEqualTo(400);
        compareObjectsJsonNode(responsePost, ExpectedPostBoardResponseInvalidName);
    }

    @Test
    public void N2_shouldNotCreateBoardWhenNameIsNull() {
        responsePost = postCreateBoard(null, null);
        assertThat(responsePost.statusCode()).isEqualTo(400);
        compareObjectsJsonNode(responsePost, ExpectedPostBoardResponseInvalidName);
    }

    @Test
    public void N3_shouldNotCreateBoardWhenNameIsEmptyString() {
        responsePost = postCreateBoard("", null);
        assertThat(responsePost.statusCode()).isEqualTo(400);
        compareObjectsJsonNode(responsePost, ExpectedPostBoardResponseInvalidName);
    }

    // idOrganization

    @Test
    public void N4_shouldNotCreateBoardWhenIdOrganizationNonExist() {

        POST_CreateBoardPayload payload = new POST_CreateBoardPayload.Builder()
                .setIdOrganization("123456789098765432123456")
                .build();
        Map<String, Object> queryParams = payload.toQueryParams();

        responsePost = postCreateBoard(generateRandomBoardName(), queryParams);
        assertThat(responsePost.statusCode()).isEqualTo(401);
        assertThat(responsePost.getBody().asString()).isEqualTo("unauthorized org access");
    }

    @Test
    public void N5_shouldNotCreateBoardWhenIdOrganizationIsIncompatibleWithRegEx() {

        POST_CreateBoardPayload payload = new POST_CreateBoardPayload.Builder()
                .setIdOrganization("123abc")
                .build();
        Map<String, Object> queryParams = payload.toQueryParams();

        responsePost = postCreateBoard(generateRandomBoardName(), queryParams);
        assertThat(responsePost.statusCode()).isEqualTo(401);
        assertThat(responsePost.getBody().asString()).isEqualTo("unauthorized organization.");
    }

    // idBoardSource

    @Test
    public void N6_shouldNotCreateBoardWhenIdBoardSourceNonExist() {

        POST_CreateBoardPayload payload = new POST_CreateBoardPayload.Builder()
                .setIdBoardSource("123456789098765432123456")
                .build();
        Map<String, Object> queryParams = payload.toQueryParams();

        responsePost = postCreateBoard(generateRandomBoardName(), queryParams);
        assertThat(responsePost.statusCode()).isEqualTo(404);
        assertThat(responsePost.getBody().asString()).isEqualTo("source board not found");
    }

    @Test
    public void N7_shouldNotCreateBoardWhenIdBoardSourceIsIncompatibleWithRegEx() {

        String exceptedResponse = """
                {
                    "message": "Invalid objectId",
                    "error": "ERROR"
                }
                """;

        POST_CreateBoardPayload payload = new POST_CreateBoardPayload.Builder()
                .setIdBoardSource("123abc")
                .build();
        Map<String, Object> queryParams = payload.toQueryParams();

        responsePost = postCreateBoard(generateRandomBoardName(), queryParams);
        assertThat(responsePost.statusCode()).isEqualTo(400);
        compareObjectsJsonNode(responsePost, exceptedResponse);
    }

    // prefs_permissionLevel

    @Test
    public void N8_shouldNotCreateBoardWhenPrefsPermissionLevelIsOtherString() {

        POST_CreateBoardPayload payload = new POST_CreateBoardPayload.Builder()
                .setPrefsPermissionLevel("other")
                .build();
        Map<String, Object> queryParams = payload.toQueryParams();

        responsePost = postCreateBoard(generateRandomBoardName(), queryParams);
        assertThat(responsePost.statusCode()).isEqualTo(400);
        assertThat(responsePost.getBody().asString()).isEqualTo("invalid value for prefs_permissionLevel");
    }

    // prefs_voting

    @Test
    public void N9_shouldNotCreateBoardWhenPrefsVotingIsOtherString() {

        POST_CreateBoardPayload payload = new POST_CreateBoardPayload.Builder()
                .setPrefsVoting("other")
                .build();
        Map<String, Object> queryParams = payload.toQueryParams();

        responsePost = postCreateBoard(generateRandomBoardName(), queryParams);
        assertThat(responsePost.statusCode()).isEqualTo(400);
        assertThat(responsePost.getBody().asString()).isEqualTo("invalid value for prefs_voting");
    }

    // prefs_comments

    @Test
    public void N10_shouldNotCreateBoardWhenPrefsCommentsIsOtherString() {

        POST_CreateBoardPayload payload = new POST_CreateBoardPayload.Builder()
                .setPrefsComments("other")
                .build();
        Map<String, Object> queryParams = payload.toQueryParams();

        responsePost = postCreateBoard(generateRandomBoardName(), queryParams);
        assertThat(responsePost.statusCode()).isEqualTo(400);
        assertThat(responsePost.getBody().asString()).isEqualTo("invalid value for prefs_comments");
    }

    // prefs_invitations

    @Test
    public void N11_shouldNotCreateBoardWhenPrefsInvitationsIsOtherString() {

        POST_CreateBoardPayload payload = new POST_CreateBoardPayload.Builder()
                .setPrefsInvitations("other")
                .build();
        Map<String, Object> queryParams = payload.toQueryParams();

        responsePost = postCreateBoard(generateRandomBoardName(), queryParams);
        assertThat(responsePost.statusCode()).isEqualTo(400);
        assertThat(responsePost.getBody().asString()).isEqualTo("invalid value for prefs_invitations");
    }

    // prefs_cardAging

    @Test
    public void N12_shouldNotCreateBoardWhenPrefsCardAgingIsOtherString() {

        POST_CreateBoardPayload payload = new POST_CreateBoardPayload.Builder()
                .setPrefsCardAging("other")
                .build();
        Map<String, Object> queryParams = payload.toQueryParams();

        responsePost = postCreateBoard(generateRandomBoardName(), queryParams);
        assertThat(responsePost.statusCode()).isEqualTo(400);
        assertThat(responsePost.getBody().asString()).isEqualTo("invalid value for prefs_cardAging");
    }

    // -----
    // DEBUG
    // -----

    //@Test
    public void deleteBoard() {
        responseDelete = deleteDeleteBoard("68724f5bfffa6577a4dc0dbb");
        assertThat(responseDelete.statusCode()).isEqualTo(200);
    }
}
