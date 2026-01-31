package tests.api_trello.labels;

import base.TestBase;
import dto.labels.POST_CreateLabelDto;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import payloads.labels.POST_CreateLabelPayload;

import static endpoints.boards.DEL_DeleteBoardEndpoint.deleteDeleteBoard;
import static endpoints.boards.POST_CreateBoardEndpoint.postCreateBoard;
import static endpoints.labels.POST_CreateLabelEndpoint.postCreateLabel;
import static endpoints.labels.POST_CreateLabelEndpoint.postCreateLabelWithAnyParams;
import static expected_responses.labels.POST_CreateLabelExpected.*;
import static org.assertj.core.api.Assertions.assertThat;
import static utils.UtilsCompare.compareObjects;
import static utils.UtilsCompare.compareResponseWithJson;
import static utils.UtilsRandom.pickRandom;
import static utils.UtilsString.getAllCharactersSetInRandomOrder;
import static utils.UtilsString.getRandomSingleCharAlphanumeric;
import static utils.response.UtilsResponseDeserializer.deserializeAndValidateJson;
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
        responsePost = postCreateBoard(generateRandomBoardName(), null);
        assertThat(responsePost.statusCode()).isEqualTo(200);
        boardId = responsePost.getBody().jsonPath().getString("id");
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
    public void P1_shouldCreateLabelWithCorrectValuesAndNameWithSpecialCharactersAndNumbers() {

        labelName = getAllCharactersSetInRandomOrder();
        labelColor = pickRandom("yellow", "purple", "blue", "red", "green", "orange", "black", "sky", "pink", "lime");

        // POST
        responsePost = postCreateLabel(boardId, labelName, labelColor);
        assertThat(responsePost.statusCode()).isEqualTo(200);
        POST_CreateLabelDto responsePostDto = deserializeAndValidateJson(responsePost, POST_CreateLabelDto.class);
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

        labelName = getRandomSingleCharAlphanumeric();
        labelColor = null;

        // POST
        responsePost = postCreateLabel(boardId, labelName, labelColor);
        assertThat(responsePost.statusCode()).isEqualTo(200);
        POST_CreateLabelDto responsePostDto = deserializeAndValidateJson(responsePost, POST_CreateLabelDto.class);
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

    // name

    @Test
    public void P3_shouldCreateLabelWhenLabelNameIsNull() {
        // NOTE: A label without a name is created, but it probably shouldn't be.

        labelName = null;
        labelColor = "purple";

        // POST
        responsePost = postCreateLabel(boardId, labelName, labelColor);
        assertThat(responsePost.statusCode()).isEqualTo(200);
        POST_CreateLabelDto responsePostDto = deserializeAndValidateJson(responsePost, POST_CreateLabelDto.class);
        POST_CreateLabelDto expectedResponsePostDto = prepareExpectedResponsePost(
                P3ExpectedPostLabelResponse,
                responsePostDto,
                boardId,
                "",
                labelColor
        );
        compareObjects(responsePostDto, expectedResponsePostDto);
        // GET
        validateGetAgainstPost(responsePostDto);
    }

    @Test
    public void P4_shouldCreateLabelWhenLabelNameIsEmptyString() {
        // NOTE: A label without a name is created, but it probably shouldn't be.

        labelName = "";
        labelColor = "purple";

        // POST
        responsePost = postCreateLabel(boardId, labelName, labelColor);
        assertThat(responsePost.statusCode()).isEqualTo(200);
        POST_CreateLabelDto responsePostDto = deserializeAndValidateJson(responsePost, POST_CreateLabelDto.class);
        POST_CreateLabelDto expectedResponsePostDto = prepareExpectedResponsePost(
                P4ExpectedPostLabelResponse,
                responsePostDto,
                boardId,
                labelName,
                labelColor
        );
        compareObjects(responsePostDto, expectedResponsePostDto);
        // GET
        validateGetAgainstPost(responsePostDto);
    }

    // color

    @Test
    public void P5_shouldCreateLabelWhenLabelColorIsMissing() {
        // NOTE: A label without a color is created, but it probably shouldn't be

        labelName = getRandomSingleCharAlphanumeric();

        POST_CreateLabelPayload payload = new POST_CreateLabelPayload.Builder()
                .setIdBoard(boardId)
                .setName(labelName)
                .build();

        // POST
        responsePost = postCreateLabelWithAnyParams(payload);
        assertThat(responsePost.getStatusCode()).isEqualTo(200);
        POST_CreateLabelDto responsePostDto = deserializeAndValidateJson(responsePost, POST_CreateLabelDto.class);
        POST_CreateLabelDto expectedResponsePostDto = prepareExpectedResponsePost(
                P5ExpectedPostLabelResponse,
                responsePostDto,
                boardId,
                labelName,
                null
        );
        compareObjects(responsePostDto, expectedResponsePostDto);
        // GET
        validateGetAgainstPost(responsePostDto);
    }

    @Test
    public void P6_shouldCreateLabelWhenLabelColorIsEmptyString() {
        // NOTE: A label without a color is created, but it probably shouldn't be

        labelName = getRandomSingleCharAlphanumeric();
        labelColor = "";

        // POST
        responsePost = postCreateLabel(boardId, labelName, labelColor);
        assertThat(responsePost.statusCode()).isEqualTo(200);
        POST_CreateLabelDto responsePostDto = deserializeAndValidateJson(responsePost, POST_CreateLabelDto.class);
        POST_CreateLabelDto expectedResponsePostDto = prepareExpectedResponsePost(
                P6ExpectedPostLabelResponse,
                responsePostDto,
                boardId,
                labelName,
                null
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

        responsePost = postCreateLabelWithAnyParams(payload);
        assertThat(responsePost.statusCode()).isEqualTo(400);
        compareResponseWithJson(responsePost, expectedPostLabelResponseInvalidId);
    }

    @Test
    public void N2_shouldNotCreateLabelWhenBoardIdIsNull() {
        responsePost = postCreateLabel(null, "N2 Label Name", "purple");
        assertThat(responsePost.statusCode()).isEqualTo(400);
        compareResponseWithJson(responsePost, expectedPostLabelResponseInvalidId);
    }

    @Test
    public void N3_shouldNotCreateLabelWhenBoardIdIsEmptyString() {
        responsePost = postCreateLabel("", "N3 Label Name", "purple");
        assertThat(responsePost.statusCode()).isEqualTo(400);
        compareResponseWithJson(responsePost, expectedPostLabelResponseInvalidId);
    }

    @Test
    public void N4_shouldNotCreateLabelWhenBoardIdNonExistent() {
        responsePost = postCreateLabel("999999", "N4 Label Name", "purple");
        assertThat(responsePost.statusCode()).isEqualTo(400);
        compareResponseWithJson(responsePost, expectedPostLabelResponseInvalidId);
    }

    @Test
    public void N5_shouldNotCreateLabelWhenBoardIdIsIncorrect() {
        responsePost = postCreateLabel("Text", "N5 Label Name", "purple");
        assertThat(responsePost.statusCode()).isEqualTo(400);
        compareResponseWithJson(responsePost, expectedPostLabelResponseInvalidId);
    }

    // name

    @Test
    public void N6_shouldNotCreateLabelWhenLabelNameIsMissing() {

        POST_CreateLabelPayload payload = new POST_CreateLabelPayload.Builder()
                .setIdBoard(boardId)
                .setColor("purple")
                .build();

        responsePost = postCreateLabelWithAnyParams(payload);
        assertThat(responsePost.statusCode()).isEqualTo(400);
        assertThat(responsePost.getBody().asString()).isEqualTo("invalid value for name");
    }

    // color

    @Test
    public void N7_shouldNotCreateLabelWhenLabelColorIsIncorrect() {
        responsePost = postCreateLabel(boardId, "N11 Label Name", "KEK123");
        assertThat(responsePost.getStatusCode()).isEqualTo(400);
        compareResponseWithJson(responsePost, expectedPostLabelResponseInvalidColor);
    }
}
