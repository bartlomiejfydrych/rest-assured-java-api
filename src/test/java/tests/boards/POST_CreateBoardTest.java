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
import static expected_responses.boards.POST_CreateBoardExpected.*;
import static org.assertj.core.api.Assertions.assertThat;
import static utils.UtilsCommon.pickRandom;
import static utils.UtilsCompare.*;
import static utils.UtilsResponse.deserializeAndValidate;
import static utils_tests.boards.POST_CreateBoardUtils.*;

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public class POST_CreateBoardTest extends TestBase {

    private String boardId;
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
        // GET
        validateGetAgainstPost(responsePostDto);
    }

    // --------------
    // NEGATIVE TESTS
    // --------------

    // -----
    // DEBUG
    // -----

//    @Test
//    public void deleteBoard() {
//        responseDelete = deleteDeleteBoard("6828686326ecb74053638a5e");
//        assertThat(responseDelete.statusCode()).isEqualTo(200);
//    }
}
