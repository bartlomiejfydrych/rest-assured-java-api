package tests.labels;

import base.TestBase;
import dto.boards.POST_CreateBoardDto;
import dto.labels.POST_CreateLabelDto;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static endpoints.boards.DELETE_DeleteBoard.deleteDeleteBoard;
import static endpoints.boards.POST_CreateBoard.postCreateBoard;
import static endpoints.labels.POST_CreateLabel.postCreateLabel;
import static expected_responses.labels.POST_CreateLabelExpected.P1ExpectedPostLabelResponse;
import static expected_responses.labels.POST_CreateLabelExpected.P2ExpectedPostLabelResponse;
import static org.assertj.core.api.Assertions.assertThat;
import static utils.UtilsCommon.*;
import static utils.UtilsCompare.compareObjects;
import static utils.UtilsResponse.deserializeAndValidate;
import static utils.UtilsResponse.deserializeJson;
import static utils_tests.boards.POST_CreateBoardUtils.generateRandomBoardName;
import static utils_tests.labels.POST_CreateLabelUtils.prepareExpectedResponsePost;
import static utils_tests.labels.POST_CreateLabelUtils.validateGetAgainstPost;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class POST_CreateLabelTest extends TestBase {

    private String boardId;
    private String labelName;
    private String labelColor;

    @BeforeAll
    public void setUpCreateBoard() {
        responsePost = postCreateBoard(generateRandomBoardName(), null);
        assertThat(responsePost.statusCode()).isEqualTo(200);
        POST_CreateBoardDto responsePostDto = deserializeJson(responsePost, POST_CreateBoardDto.class);
        boardId = responsePostDto.id;
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
        responsePost = postCreateLabel(boardId, labelName, labelColor);
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

    }

    // TODO: Wymyślić jak używać jednej tablicy na wszystkie testy negatywne, a dla pozytywnych osobne lub delete label

    // name

    // color
}
