package tests.labels;

import base.TestBase;
import dto.boards.POST_CreateBoardDto;
import dto.labels.POST_CreateLabelDto;
import org.junit.jupiter.api.*;

import static endpoints.boards.DELETE_DeleteBoard.deleteDeleteBoard;
import static endpoints.boards.POST_CreateBoard.postCreateBoard;
import static endpoints.labels.POST_CreateLabel.postCreateLabel;
import static org.assertj.core.api.Assertions.assertThat;
import static utils.UtilsResponse.deserializeJson;
import static utils_tests.boards.POST_CreateBoardUtils.generateRandomBoardName;
import static utils_tests.labels.POST_CreateLabelUtils.generateRandomLabelColor;
import static utils_tests.labels.POST_CreateLabelUtils.generateRandomLabelName;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PUT_UpdateLabelTest extends TestBase {

    private String boardIdPositive;
    private String boardIdNegative;
    private String labelIdPositive;
    private String labelIdNegative;
    private String labelName;
    private String labelColor;

    // FOR POSITIVE TESTS

    @BeforeEach
    public void setUpCreateBoardAndLabelForPositiveTests(TestInfo testInfo) {
        if (testInfo.getTags().contains(testTagPositive)) {
            responsePost = postCreateBoard(generateRandomBoardName(), null);
            assertThat(responsePost.statusCode()).isEqualTo(200);
            boardIdPositive = deserializeJson(responsePost, POST_CreateBoardDto.class).id;
            responsePost = postCreateLabel(boardIdPositive, generateRandomLabelName(), generateRandomLabelColor());
            assertThat(responsePost.statusCode()).isEqualTo(200);
            labelIdPositive = deserializeJson(responsePost, POST_CreateLabelDto.class).id;
        }
    }

    @AfterEach
    public void tearDownDeleteBoardAndLabelForPositiveTests(TestInfo testInfo) {
        if (testInfo.getTags().contains(testTagPositive) && boardIdPositive != null) {
            responseDelete = deleteDeleteBoard(boardIdPositive);
            assertThat(responseDelete.statusCode()).isEqualTo(200);
            boardIdPositive = null;
            labelIdPositive = null;
        }
    }

    // FOR NEGATIVE TESTS

    @BeforeAll
    public void setUpCreateBoardAndLabelForNegativeTests(TestInfo testInfo) {
        if (testInfo.getTags().contains(testTagNegative)) {
            responsePost = postCreateBoard(generateRandomBoardName(), null);
            assertThat(responsePost.statusCode()).isEqualTo(200);
            boardIdNegative = deserializeJson(responsePost, POST_CreateBoardDto.class).id;
            responsePost = postCreateLabel(boardIdNegative, generateRandomLabelName(), generateRandomLabelColor());
            assertThat(responsePost.statusCode()).isEqualTo(200);
            labelIdNegative = deserializeJson(responsePost, POST_CreateLabelDto.class).id;
        }
    }

    @AfterAll
    public void tearDownDeleteBoardAndLabelForNegativeTests(TestInfo testInfo) {
        if (testInfo.getTags().contains(testTagNegative) && boardIdNegative != null) {
            responseDelete = deleteDeleteBoard(boardIdNegative);
            assertThat(responseDelete.statusCode()).isEqualTo(200);
            boardIdNegative = null;
            labelIdNegative = null;
        }
    }

    // --------------
    // POSITIVE TESTS
    // --------------


}
