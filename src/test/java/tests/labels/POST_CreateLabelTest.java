package tests.labels;

import base.TestBase;
import dto.labels.POST_CreateLabelDto;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import payloads.labels.POST_CreateLabelPayload;

import java.util.Map;

import static endpoints.boards.DEL_DeleteBoardEndpoint.deleteBoard;
import static endpoints.boards.POST_CreateBoardEndpoint.createBoard;
import static endpoints.labels.POST_CreateLabelEndpoint.createLabel;
import static endpoints.labels.POST_CreateLabelEndpoint.createLabelWithAnyParams;
import static expected_responses.labels.POST_CreateLabelExpected.*;
import static org.assertj.core.api.Assertions.assertThat;
import static utils.UtilsCommon.*;
import static utils.UtilsCompare.compareObjects;
import static utils.UtilsCompare.compareObjectsJsonNode;
import static utils.UtilsResponse.deserializeAndValidate;
import static utils_tests.boards.POST_CreateBoardUtils.generateRandomBoardName;
import static utils_tests.labels.POST_CreateLabelUtils.prepareExpectedResponsePost;
import static utils_tests.labels.POST_CreateLabelUtils.validateGetAgainstPost;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class POST_CreateLabelTest extends TestBase {

    private Response responsePost;
    private Response responsePut;
    private Response responseGet;
    private Response responseDelete;

    private String boardId;
    private String labelName;
    private String labelColor;

    @BeforeAll
    public void setUpCreateBoard() {
        responsePost = createBoard(generateRandomBoardName(), null);
        assertThat(responsePost.statusCode()).isEqualTo(200);
        boardId = responsePost.getBody().jsonPath().getString("id");
    }

    @AfterAll
    public void tearDownDeleteBoard() {
        if (boardId != null) {
            responseDelete = deleteBoard(boardId);
            assertThat(responseDelete.statusCode()).isEqualTo(200);
            boardId = null;
        }
    }

    // --------------
    // POSITIVE TESTS
    // --------------

    @Test
    public void P1_shouldCreateLabelWithCorrectValuesAndNameWithSpecialCharactersAndNumbers() {

        labelName = getAllCharactersSetInRandomOrder();
        labelColor = pickRandom("yellow", "purple", "blue", "red", "green", "orange", "black", "sky", "pink", "lime");

        // POST
        responsePost = createLabel(boardId, labelName, labelColor);
        assertThat(responsePost.statusCode()).isEqualTo(200);
        POST_CreateLabelDto responsePostDto = deserializeAndValidate(responsePost, POST_CreateLabelDto.class);
        POST_CreateLabelDto expectedResponsePostDto = prepareExpectedResponsePost(
                P1ExpectedPostLabelResponse,
                responsePostDto,
                boardId,
                labelName,
                labelColor
        );
        compareObjects(responsePostDto, expectedResponsePostDto);
        // GET
        validateGetAgainstPost(responsePostDto);
    }

    @Test
    public void P2_shouldCreateLabelWhenNameHaveOneCharacterAndColorIsNull() {

        labelName = getRandomSingleChar();
        labelColor = null;

        // POST
        responsePost = createLabel(boardId, labelName, labelColor);
        assertThat(responsePost.statusCode()).isEqualTo(200);
        POST_CreateLabelDto responsePostDto = deserializeAndValidate(responsePost, POST_CreateLabelDto.class);
        POST_CreateLabelDto expectedResponsePostDto = prepareExpectedResponsePost(
                P2ExpectedPostLabelResponse,
                responsePostDto,
                boardId,
                labelName,
                labelColor
        );
        compareObjects(responsePostDto, expectedResponsePostDto);
        // GET
        validateGetAgainstPost(responsePostDto);
    }

    // --------------
    // NEGATIVE TESTS
    // --------------

    // idBoard

    @Test
    public void N1_shouldNotCreateLabelWhenBoardIdIsMissing() {

        POST_CreateLabelPayload payload = new POST_CreateLabelPayload.Builder()
                .setName("N1 Label Name")
                .setColor("yellow")
                .build();

        responsePost = createLabelWithAnyParams(payload);
        assertThat(responsePost.statusCode()).isEqualTo(400);
        compareObjectsJsonNode(responsePost, expectedPostLabelResponseInvalidId);
    }

    @Test
    public void N2_shouldNotCreateLabelWhenBoardIdIsNull() {
        responsePost = createLabel(null, "N2 Label Name", "purple");
        assertThat(responsePost.statusCode()).isEqualTo(400);
        compareObjectsJsonNode(responsePost, expectedPostLabelResponseInvalidId);
    }

    @Test
    public void N3_shouldNotCreateLabelWhenBoardIdIsEmptyString() {
        responsePost = createLabel("", "N3 Label Name", "purple");
        assertThat(responsePost.statusCode()).isEqualTo(400);
        compareObjectsJsonNode(responsePost, expectedPostLabelResponseInvalidId);
    }

    @Test
    public void N4_shouldNotCreateLabelWhenBoardIdNonExistent() {
        responsePost = createLabel("999999", "N4 Label Name", "purple");
        assertThat(responsePost.statusCode()).isEqualTo(400);
        compareObjectsJsonNode(responsePost, expectedPostLabelResponseInvalidId);
    }

    @Test
    public void N5_shouldNotCreateLabelWhenBoardIdIsIncorrect() {
        responsePost = createLabel("Text", "N5 Label Name", "purple");
        assertThat(responsePost.statusCode()).isEqualTo(400);
        compareObjectsJsonNode(responsePost, expectedPostLabelResponseInvalidId);
    }

    // name

    @Test
    public void N6_shouldNotCreateLabelWhenLabelNameIsMissing() {

        POST_CreateLabelPayload payload = new POST_CreateLabelPayload.Builder()
                .setIdBoard(boardId)
                .setColor("purple")
                .build();

        responsePost = createLabelWithAnyParams(payload);
        assertThat(responsePost.statusCode()).isEqualTo(400);
        assertThat(responsePost.getBody().asString()).isEqualTo("invalid value for name");
    }

    /*
    NOTE:
    The test passes when it shouldn't.
    The label is created when it shouldn't.

    @Test
    public void N7_shouldNotCreateLabelWhenLabelNameIsNull() {
        responsePost = postCreateLabel(boardId, null, "purple");
        assertThat(responsePost.statusCode()).isEqualTo(400);
        assertThat(responsePost.getBody().asString()).isEqualTo("invalid value for name");

        String actualResponse = """
                {
                    "id": "68c8732ca92b5463450a2753",
                    "idBoard": "68c8732ca92b5463450a2714",
                    "name": "",
                    "color": "purple",
                    "uses": 0,
                    "limits": {
                
                    }
                }
                """;
    }
    */

    /*
    NOTE:
    The test passes when it shouldn't.
    The label is created when it shouldn't.

    @Test
    public void N8_shouldNotCreateLabelWhenLabelNameIsEmptyString() {
        responsePost = postCreateLabel(boardId, "", "purple");
        assertThat(responsePost.statusCode()).isEqualTo(400);
        assertThat(responsePost.getBody().asString()).isEqualTo("invalid value for name");

        String actualResponse = """
                {
                    "id": "68d43e6808b089b5e5936dd5",
                    "idBoard": "68d43e6708b089b5e5936da0",
                    "name": "",
                    "color": "purple",
                    "uses": 0,
                    "limits": {

                    }
                }
                """;
    }
    */

    // color

    /*
    NOTE:
    The test passes when it shouldn't.
    The label is created when it shouldn't.

    @Test
    public void N9_shouldNotCreateLabelWhenLabelColorIsMissing() {

        POST_CreateLabelPayload payload = new POST_CreateLabelPayload.Builder()
                .setIdBoard(boardId)
                .setName("N9 Label Name")
                .build();
        Map<String, Object> queryParams = payload.toQueryParams();

        responsePost = postCreateLabelAnyParams(queryParams);
        assertThat(responsePost.getStatusCode()).isEqualTo(400);
        compareObjectsJsonNode(responsePost, expectedPostLabelResponseInvalidColor);

        String actualResponse = """
                {
                    "id": "68d442ce71b8473d654d2158",
                    "idBoard": "68d442cd1d9e55efb7c2017a",
                    "name": "N9 Label Name",
                    "color": null,
                    "uses": 0,
                    "limits": {
                
                    }
                }
                """;
    }
    */

    /*
    NOTE:
    The test passes when it shouldn't.
    The label is created when it shouldn't.

    @Test
    public void N10_shouldNotCreateLabelWhenLabelColorIsEmptyString() {
        responsePost = postCreateLabel(boardId, "N10 Label Name", "");
        assertThat(responsePost.getStatusCode()).isEqualTo(400);
        compareObjectsJsonNode(responsePost, expectedPostLabelResponseInvalidColor);

        String actualResponse = """
                {
                    "id": "68d444438f5bd970f7fb6ce0",
                    "idBoard": "68d44442abe064c42602303b",
                    "name": "N10 Label Name",
                    "color": null,
                    "uses": 0,
                    "limits": {

                    }
                }
                """;
    }
    */

    @Test
    public void N11_shouldNotCreateLabelWhenLabelColorIsIncorrect() {
        responsePost = createLabel(boardId, "N11 Label Name", "KEK123");
        assertThat(responsePost.getStatusCode()).isEqualTo(400);
        compareObjectsJsonNode(responsePost, expectedPostLabelResponseInvalidColor);
    }
}
