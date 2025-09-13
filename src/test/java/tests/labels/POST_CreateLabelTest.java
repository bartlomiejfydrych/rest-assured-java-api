package tests.labels;

import base.TestBase;
import dto.boards.POST_CreateBoardDto;
import dto.labels.POST_CreateLabelDto;
import org.junit.jupiter.api.*;
import payloads.labels.POST_CreateLabelPayload;

import java.util.Map;

import static endpoints.boards.DELETE_DeleteBoard.deleteDeleteBoard;
import static endpoints.boards.POST_CreateBoard.postCreateBoard;
import static endpoints.labels.POST_CreateLabel.postCreateLabel;
import static endpoints.labels.POST_CreateLabel.postCreateLabelAnyParams;
import static expected_responses.labels.POST_CreateLabelExpected.*;
import static org.assertj.core.api.Assertions.assertThat;
import static utils.UtilsCommon.*;
import static utils.UtilsCompare.compareObjects;
import static utils.UtilsCompare.compareObjectsJsonNode;
import static utils.UtilsResponse.deserializeAndValidate;
import static utils.UtilsResponse.deserializeJson;
import static utils_tests.boards.POST_CreateBoardUtils.generateRandomBoardName;
import static utils_tests.labels.POST_CreateLabelUtils.prepareExpectedResponsePost;
import static utils_tests.labels.POST_CreateLabelUtils.validateGetAgainstPost;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class POST_CreateLabelTest extends TestBase {

    private String boardIdPositive;
    private String boardIdNegative;
    private String labelName;
    private String labelColor;

    // FOR POSITIVE TESTS

    @BeforeEach
    public void setUpCreateBoardForPositiveTests(TestInfo testInfo) {
        if (testInfo.getTags().contains(testTagPositive)) {
            responsePost = postCreateBoard(generateRandomBoardName(), null);
            assertThat(responsePost.statusCode()).isEqualTo(200);
            boardIdPositive = deserializeJson(responsePost, POST_CreateBoardDto.class).id;
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
    public void setUpCreateBoardForNegativeTests() {
        responsePost = postCreateBoard(generateRandomBoardName(), null);
        assertThat(responsePost.statusCode()).isEqualTo(200);
        POST_CreateBoardDto responsePostDto = deserializeJson(responsePost, POST_CreateBoardDto.class);
        boardIdNegative = responsePostDto.id;
    }

    @AfterAll
    public void tearDownDeleteBoardForNegativeTests() {
        if (boardIdNegative != null) {
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
    public void P1_shouldCreateLabelWithCorrectValuesAndNameWithSpecialCharactersAndNumbers() {

        labelName = getAllCharactersSetInRandomOrder();
        labelColor = pickRandom("yellow", "purple", "blue", "red", "green", "orange", "black", "sky", "pink", "lime");

        // POST
        responsePost = postCreateLabel(boardIdPositive, labelName, labelColor);
        assertThat(responsePost.statusCode()).isEqualTo(200);
        POST_CreateLabelDto responsePostDto = deserializeAndValidate(responsePost, POST_CreateLabelDto.class);
        POST_CreateLabelDto expectedResponsePostDto = prepareExpectedResponsePost(
                P1ExpectedPostLabelResponse,
                responsePostDto,
                boardIdPositive,
                labelName,
                labelColor
        );
        compareObjects(responsePostDto, expectedResponsePostDto);
        // GET
        validateGetAgainstPost(responsePostDto);
    }

    @Test
    @Tag(testTagPositive)
    public void P2_shouldCreateLabelWhenNameHaveOneCharacterAndColorIsNull() {

        labelName = getRandomSingleChar();
        labelColor = null;

        // POST
        responsePost = postCreateLabel(boardIdPositive, labelName, labelColor);
        assertThat(responsePost.statusCode()).isEqualTo(200);
        POST_CreateLabelDto responsePostDto = deserializeAndValidate(responsePost, POST_CreateLabelDto.class);
        POST_CreateLabelDto expectedResponsePostDto = prepareExpectedResponsePost(
                P2ExpectedPostLabelResponse,
                responsePostDto,
                boardIdPositive,
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
        Map<String, Object> queryParams = payload.toQueryParams();

        responsePost = postCreateLabelAnyParams(queryParams);
        assertThat(responsePost.statusCode()).isEqualTo(400);
        compareObjectsJsonNode(responsePost, expectedPostLabelResponseInvalidId);
    }

    @Test
    public void N2_shouldNotCreateLabelWhenBoardIdIsNull() {
        responsePost = postCreateLabel(null, "N2 Label Name", "purple");
        assertThat(responsePost.statusCode()).isEqualTo(400);
        compareObjectsJsonNode(responsePost, expectedPostLabelResponseInvalidId);
    }

    @Test
    public void N3_shouldNotCreateLabelWhenBoardIdIsEmptyString() {
        responsePost = postCreateLabel("", "N2 Label Name", "purple");
        assertThat(responsePost.statusCode()).isEqualTo(400);
        compareObjectsJsonNode(responsePost, expectedPostLabelResponseInvalidId);
    }

    @Test
    public void N4_shouldNotCreateLabelWhenBoardIdNonExistent() {
        responsePost = postCreateLabel("999999", "N2 Label Name", "purple");
        assertThat(responsePost.statusCode()).isEqualTo(400);
        compareObjectsJsonNode(responsePost, expectedPostLabelResponseInvalidId);
    }

    @Test
    public void N5_shouldNotCreateLabelWhenBoardIdIsIncorrect() {
        responsePost = postCreateLabel("Text", "N2 Label Name", "purple");
        assertThat(responsePost.statusCode()).isEqualTo(400);
        compareObjectsJsonNode(responsePost, expectedPostLabelResponseInvalidId);
    }

    // name

    // color
}
