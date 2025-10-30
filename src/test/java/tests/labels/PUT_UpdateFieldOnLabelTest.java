package tests.labels;

import base.TestBase;
import dto.labels.POST_CreateLabelDto;
import dto.labels.PUT_UpdateFieldOnLabelDto;
import enums.labels.LabelField;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static endpoints.boards.DELETE_DeleteBoard.deleteDeleteBoard;
import static endpoints.boards.POST_CreateBoard.postCreateBoard;
import static endpoints.labels.POST_CreateLabel.postCreateLabel;
import static endpoints.labels.PUT_UpdateFieldOnLabel.putUpdateFieldOnLabel;
import static org.assertj.core.api.Assertions.assertThat;
import static utils.UtilsCommon.getAllCharactersSetInRandomOrder;
import static utils.UtilsCompare.compareObjects;
import static utils.UtilsResponse.deserializeAndValidate;
import static utils_tests.boards.POST_CreateBoardUtils.generateRandomBoardName;
import static utils_tests.labels.POST_CreateLabelUtils.generateRandomLabelColor;
import static utils_tests.labels.POST_CreateLabelUtils.generateRandomLabelName;
import static utils_tests.labels.PUT_UpdateFieldOnLabelUtils.validateGetAgainstPut;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PUT_UpdateFieldOnLabelTest extends TestBase {

    private String boardId;
    private String labelId;
    private String labelFieldValue;

    private POST_CreateLabelDto responsePostDto;

    @BeforeAll
    public void setUpCreateBoardAndLabel() {
        responsePost = postCreateBoard(generateRandomBoardName(), null);
        assertThat(responsePost.statusCode()).isEqualTo(200);
        boardId = responsePost.getBody().jsonPath().getString("id");
        responsePost = postCreateLabel(boardId, generateRandomLabelName(), generateRandomLabelColor());
        assertThat(responsePost.statusCode()).isEqualTo(200);
        responsePostDto = deserializeAndValidate(responsePost, POST_CreateLabelDto.class);
        labelId = responsePostDto.id;
    }

    @AfterAll
    public void tearDownDeleteBoardAndLabel() {
        if (boardId != null) {
            responseDelete = deleteDeleteBoard(boardId);
            assertThat(responseDelete.statusCode()).isEqualTo(200);
            boardId = null;
            labelId = null;
        }
    }

    // --------------
    // POSITIVE TESTS
    // --------------

    // name

    @Test
    public void P1_shouldUpdateLabelFieldNameWithSpecialCharactersAndNumbers() {

        labelFieldValue = getAllCharactersSetInRandomOrder();
        responsePostDto.name = labelFieldValue;

        // PUT
        responsePut = putUpdateFieldOnLabel(labelId, LabelField.NAME, labelFieldValue);
        assertThat(responsePut.statusCode()).isEqualTo(200);
        PUT_UpdateFieldOnLabelDto responsePutDto = deserializeAndValidate(responsePut, PUT_UpdateFieldOnLabelDto.class);
        compareObjects(responsePutDto, responsePostDto);
        // GET
        validateGetAgainstPut(responsePutDto);
    }

    // color


    // --------------
    // NEGATIVE TESTS
    // --------------

    // color


}
