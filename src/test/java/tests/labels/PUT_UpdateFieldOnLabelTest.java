package tests.labels;

import base.TestBase;
import dto.labels.POST_CreateLabelDto;
import dto.labels.PUT_UpdateFieldOnLabelDto;
import enums.labels.LabelField;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static endpoints.boards.DEL_DeleteBoardEndpoint.deleteBoard;
import static endpoints.boards.POST_CreateBoardEndpoint.createBoard;
import static endpoints.labels.POST_CreateLabelEndpoint.createLabel;
import static endpoints.labels.PUT_UpdateFieldOnLabelEndpoint.putUpdateFieldOnLabel;
import static endpoints.labels.PUT_UpdateFieldOnLabelEndpoint.putUpdateFieldOnLabelWithoutValue;
import static org.assertj.core.api.Assertions.assertThat;
import static utils.UtilsCommon.*;
import static utils.UtilsCompare.compareObjects;
import static utils.UtilsCompare.compareObjectsJsonNode;
import static utils.UtilsResponse.deserializeAndValidate;
import static utils_tests.boards.POST_CreateBoardUtils.generateRandomBoardName;
import static utils_tests.labels.POST_CreateLabelUtils.generateRandomLabelColor;
import static utils_tests.labels.POST_CreateLabelUtils.generateRandomLabelName;
import static utils_tests.labels.PUT_UpdateFieldOnLabelUtils.validateGetAgainstPut;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PUT_UpdateFieldOnLabelTest extends TestBase {

    private Response responsePost;
    private Response responsePut;
    private Response responseGet;
    private Response responseDelete;

    private String boardId;
    private String labelId;
    private String labelFieldValue;
    // NOTE: Created label object will act as our "expected response"
    private POST_CreateLabelDto responsePostDto;

    @BeforeAll
    public void setUpCreateBoardAndLabel() {
        responsePost = createBoard(generateRandomBoardName(), null);
        assertThat(responsePost.statusCode()).isEqualTo(200);
        boardId = responsePost.getBody().jsonPath().getString("id");
        responsePost = createLabel(boardId, generateRandomLabelName(), generateRandomLabelColor());
        assertThat(responsePost.statusCode()).isEqualTo(200);
        responsePostDto = deserializeAndValidate(responsePost, POST_CreateLabelDto.class);
        labelId = responsePostDto.id;
    }

    @AfterAll
    public void tearDownDeleteBoardAndLabel() {
        if (boardId != null) {
            responseDelete = deleteBoard(boardId);
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

    @Test
    public void P2_shouldUpdateLabelFieldNameWithOneCharacter() {

        labelFieldValue = getRandomSingleChar();
        responsePostDto.name = labelFieldValue;

        // PUT
        responsePut = putUpdateFieldOnLabel(labelId, LabelField.NAME, labelFieldValue);
        assertThat(responsePut.statusCode()).isEqualTo(200);
        PUT_UpdateFieldOnLabelDto responsePutDto = deserializeAndValidate(responsePut, PUT_UpdateFieldOnLabelDto.class);
        compareObjects(responsePutDto, responsePostDto);
        // GET
        validateGetAgainstPut(responsePutDto);
    }

    @Test
    public void P3_shouldUpdateLabelFieldNameWithEmptyString() {

        labelFieldValue = "";
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

    @Test
    public void P4_shouldUpdateLabelFieldColorWithOneOfCorrectColors() {

        labelFieldValue = pickRandom("yellow", "purple", "blue", "red", "green", "orange", "black", "sky", "pink", "lime");
        responsePostDto.color = labelFieldValue;

        // PUT
        responsePut = putUpdateFieldOnLabel(labelId, LabelField.COLOR, labelFieldValue);
        assertThat(responsePut.statusCode()).isEqualTo(200);
        PUT_UpdateFieldOnLabelDto responsePutDto = deserializeAndValidate(responsePut, PUT_UpdateFieldOnLabelDto.class);
        compareObjects(responsePutDto, responsePostDto);
        // GET
        validateGetAgainstPut(responsePutDto);
    }

    @Test
    public void P5_shouldUpdateLabelFieldColorWithNull() {

        labelFieldValue = null;
        responsePostDto.color = labelFieldValue;

        // PUT
        responsePut = putUpdateFieldOnLabel(labelId, LabelField.COLOR, labelFieldValue);
        assertThat(responsePut.statusCode()).isEqualTo(200);
        PUT_UpdateFieldOnLabelDto responsePutDto = deserializeAndValidate(responsePut, PUT_UpdateFieldOnLabelDto.class);
        compareObjects(responsePutDto, responsePostDto);
        // GET
        validateGetAgainstPut(responsePutDto);
    }

    @Test
    public void P6_shouldUpdateLabelFieldColorWithEmptyString() {

        labelFieldValue = "";
        responsePostDto.color = null;

        // PUT
        responsePut = putUpdateFieldOnLabel(labelId, LabelField.COLOR, labelFieldValue);
        assertThat(responsePut.statusCode()).isEqualTo(200);
        PUT_UpdateFieldOnLabelDto responsePutDto = deserializeAndValidate(responsePut, PUT_UpdateFieldOnLabelDto.class);
        compareObjects(responsePutDto, responsePostDto);
        // GET
        validateGetAgainstPut(responsePutDto);
    }

    // --------------
    // NEGATIVE TESTS
    // --------------

    // name

    @Test
    public void N1_shouldNotUpdateLabelFieldNameWithoutValue() {
        // PUT
        responsePut = putUpdateFieldOnLabelWithoutValue(labelId, LabelField.NAME);
        assertThat(responsePut.statusCode()).isEqualTo(400);
        assertThat(responsePut.getBody().asString()).isEqualTo("invalid value for value");
    }

    // color

    /*
    // NOTE: Request passes without changing value

    @Test
    public void N2_shouldNotUpdateLabelFieldColorWithoutValue() {
        // PUT
        responsePut = putUpdateFieldOnLabelWithoutValue(labelId, LabelField.COLOR);
        assertThat(responsePut.statusCode()).isEqualTo(400);
        assertThat(responsePut.getBody().asString()).isEqualTo("invalid value for value");

        String actualResponse = """
                {
                    "id": "6903d70cf60dce2dc344e24f",
                    "idBoard": "6903d70b363c3fe37f468c4c",
                    "name": "Ryan Group label 22756030059000",
                    "color": null,
                    "uses": 0
                }
                """;
    }
    */

    @Test
    public void N3_shouldNotUpdateLabelFieldColorWithIncorrectValue() {

        String expectedResponse = """
                {
                    "message": "invalid value for value",
                    "error": "ERROR"
                }
                """;

        // PUT
        responsePut = putUpdateFieldOnLabel(labelId, LabelField.COLOR, "KEK123");
        assertThat(responsePut.statusCode()).isEqualTo(400);
        compareObjectsJsonNode(responsePut, expectedResponse);
    }
}
