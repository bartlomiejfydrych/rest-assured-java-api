package tests.api_trello.labels;

import base.TestBase;
import dto.labels.POST_CreateLabelDto;
import dto.labels.PUT_UpdateFieldOnLabelDto;
import enums.query_parameters.labels.LabelBaseQueryParameters;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import utils.UtilsString;

import static endpoints.boards.DEL_DeleteBoardEndpoint.deleteDeleteBoard;
import static endpoints.boards.POST_CreateBoardEndpoint.postCreateBoard;
import static endpoints.labels.POST_CreateLabelEndpoint.postCreateLabel;
import static endpoints.labels.PUT_UpdateFieldOnLabelEndpoint.*;
import static org.assertj.core.api.Assertions.assertThat;
import static utils.UtilsCompare.compareObjects;
import static utils.UtilsCompare.compareResponseWithJson;
import static utils.UtilsRandom.pickRandom;
import static utils.UtilsString.getAllCharactersSetInRandomOrder;
import static utils.response.UtilsResponseDeserializer.deserializeAndValidateJson;
import static utils_tests.boards.POST_CreateBoardUtils.generateRandomBoardName;
import static utils_tests.labels.POST_CreateLabelUtils.generateRandomLabelColor;
import static utils_tests.labels.POST_CreateLabelUtils.generateRandomLabelName;
import static utils_tests.labels.PUT_UpdateFieldOnLabelUtils.validateGetAgainstPut;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PUT_UpdateFieldOnLabelTest extends TestBase {

    // ==========================================================================================================
    // FIELDS
    // ==========================================================================================================

    // --------
    // RESPONSE
    // --------

    private Response responsePost;
    private Response responsePut;
    private Response responseDelete;

    // ---------------
    // CLASS VARIABLES
    // ---------------

    // BOARD
    private String boardId;
    // LABEL
    private String labelId;
    private String labelFieldValue;
    // NOTE: The label object you create will act as our "expected response." We'll substitute the changed data into it.
    private POST_CreateLabelDto responsePostDto;

    // ==========================================================================================================
    // SETUP & TEARDOWN
    // ==========================================================================================================

    // ----------
    // BEFORE ALL
    // ----------

    @BeforeAll
    public void setUpCreateBoardAndLabel() {
        // BOARD
        responsePost = postCreateBoard(generateRandomBoardName(), null);
        assertThat(responsePost.statusCode()).isEqualTo(200);
        boardId = responsePost.getBody().jsonPath().getString("id");
        // LABEL
        responsePost = postCreateLabel(boardId, generateRandomLabelName(), generateRandomLabelColor());
        assertThat(responsePost.statusCode()).isEqualTo(200);
        responsePostDto = deserializeAndValidateJson(responsePost, POST_CreateLabelDto.class);
        labelId = responsePostDto.id;
    }

    // ---------
    // AFTER ALL
    // ---------

    @AfterAll
    public void tearDownDeleteBoardAndLabel() {
        if (boardId != null) {
            responseDelete = deleteDeleteBoard(boardId);
            assertThat(responseDelete.statusCode()).isEqualTo(200);
            boardId = null;
            labelId = null;
        }
    }

    // ==========================================================================================================
    // POSITIVE TESTS
    // ==========================================================================================================

    // ----
    // name
    // ----

    @Test
    public void P1_shouldUpdateLabelFieldNameWithSpecialCharactersAndNumbers() {

        labelFieldValue = getAllCharactersSetInRandomOrder();
        responsePostDto.name = labelFieldValue;

        // PUT
        responsePut = putUpdateFieldOnLabel(labelId, LabelBaseQueryParameters.NAME, labelFieldValue);
        assertThat(responsePut.statusCode()).isEqualTo(200);
        PUT_UpdateFieldOnLabelDto responsePutDto = deserializeAndValidateJson(responsePut, PUT_UpdateFieldOnLabelDto.class);
        compareObjects(responsePutDto, responsePostDto);
        // GET
        validateGetAgainstPut(responsePutDto);
    }

    @Test
    public void P2_shouldUpdateLabelFieldNameWithOneCharacter() {

        labelFieldValue = UtilsString.getRandomSingleCharAlphanumeric();
        responsePostDto.name = labelFieldValue;

        // PUT
        responsePut = putUpdateFieldOnLabel(labelId, LabelBaseQueryParameters.NAME, labelFieldValue);
        assertThat(responsePut.statusCode()).isEqualTo(200);
        PUT_UpdateFieldOnLabelDto responsePutDto = deserializeAndValidateJson(responsePut, PUT_UpdateFieldOnLabelDto.class);
        compareObjects(responsePutDto, responsePostDto);
        // GET
        validateGetAgainstPut(responsePutDto);
    }

    @Test
    public void P3_shouldUpdateLabelFieldNameWithEmptyString() {
        // WARNING: Flaky test – Data shouldn't change, but sometimes it does.

        labelFieldValue = "";
        responsePostDto.name = labelFieldValue;

        // PUT
        responsePut = putUpdateFieldOnLabel(labelId, LabelBaseQueryParameters.NAME, labelFieldValue);
        assertThat(responsePut.statusCode()).isEqualTo(200);
        PUT_UpdateFieldOnLabelDto responsePutDto = deserializeAndValidateJson(responsePut, PUT_UpdateFieldOnLabelDto.class);
        compareObjects(responsePutDto, responsePostDto);
        // GET
        validateGetAgainstPut(responsePutDto);
    }

    // -----
    // color
    // -----

    @Test
    public void P4_shouldUpdateLabelFieldColorWithOneOfCorrectColors() {

        labelFieldValue = pickRandom("yellow", "purple", "blue", "red", "green", "orange", "black", "sky", "pink", "lime");
        responsePostDto.color = labelFieldValue;

        // PUT
        responsePut = putUpdateFieldOnLabel(labelId, LabelBaseQueryParameters.COLOR, labelFieldValue);
        assertThat(responsePut.statusCode()).isEqualTo(200);
        PUT_UpdateFieldOnLabelDto responsePutDto = deserializeAndValidateJson(responsePut, PUT_UpdateFieldOnLabelDto.class);
        compareObjects(responsePutDto, responsePostDto);
        // GET
        validateGetAgainstPut(responsePutDto);
    }

    @Test
    public void P5_shouldUpdateLabelFieldColorWithNull() {

        labelFieldValue = null;
        responsePostDto.color = labelFieldValue;

        // PUT
        responsePut = putUpdateFieldOnLabel(labelId, LabelBaseQueryParameters.COLOR, labelFieldValue);
        assertThat(responsePut.statusCode()).isEqualTo(200);
        PUT_UpdateFieldOnLabelDto responsePutDto = deserializeAndValidateJson(responsePut, PUT_UpdateFieldOnLabelDto.class);
        compareObjects(responsePutDto, responsePostDto);
        // GET
        validateGetAgainstPut(responsePutDto);
    }

    @Test
    public void P6_shouldUpdateLabelFieldColorWithEmptyString() {

        labelFieldValue = "";
        responsePostDto.color = null;

        // PUT
        responsePut = putUpdateFieldOnLabel(labelId, LabelBaseQueryParameters.COLOR, labelFieldValue);
        assertThat(responsePut.statusCode()).isEqualTo(200);
        PUT_UpdateFieldOnLabelDto responsePutDto = deserializeAndValidateJson(responsePut, PUT_UpdateFieldOnLabelDto.class);
        compareObjects(responsePutDto, responsePostDto);
        // GET
        validateGetAgainstPut(responsePutDto);
    }

    @Test
    public void P7_shouldUpdateLabelFieldColorWithoutValue() {
        // NOTE: If we don't provide a value, it changes to 'null', and it probably shouldn't be changed.
        // PUT
        responsePut = putUpdateFieldOnLabelWithoutFieldValue(labelId, LabelBaseQueryParameters.COLOR);
        assertThat(responsePut.statusCode()).isEqualTo(200);
        PUT_UpdateFieldOnLabelDto responsePutDto = deserializeAndValidateJson(responsePut, PUT_UpdateFieldOnLabelDto.class);
        POST_CreateLabelDto expectedResponsePostDto = responsePostDto;
        expectedResponsePostDto.color = null;
        compareObjects(responsePutDto, expectedResponsePostDto);
        // GET
        validateGetAgainstPut(responsePutDto);
    }

    // ==========================================================================================================
    // NEGATIVE TESTS
    // ==========================================================================================================

    // --
    // id
    // --

    @Test
    public void N3_shouldNotUpdateLabelFieldWithNonExistentId() {
        // ARRANGE
        String labelId = "999999999999999999999999";
        // ACT
        responsePut = putUpdateFieldOnLabel(labelId, LabelBaseQueryParameters.NAME, "Update Label Field – negative test");
        // ASSERT
        assertThat(responsePut.statusCode()).isEqualTo(404);
        assertThat(responsePut.getBody().asString()).isEqualTo("The requested resource was not found.");
    }

    @Test
    public void N4_shouldNotUpdateLabelFieldWithIncorrectId() {
        // ARRANGE
        String labelId = "Kek123";
        // ACT
        responsePut = putUpdateFieldOnLabel(labelId, LabelBaseQueryParameters.NAME, "Update Label Field – negative test");
        // ASSERT
        assertThat(responsePut.statusCode()).isEqualTo(400);
        assertThat(responsePut.getBody().asString()).isEqualTo("invalid id");
    }

    // -----
    // field
    // -----

    @Test
    public void N5_shouldNotUpdateLabelFieldWithIncorrectFieldName() {
        // ARRANGE
        String incorrectFieldName = "uses";
        // ACT
        responsePut = putUpdateFieldOnLabelCustomField(labelId, incorrectFieldName, "Update Label Field – negative test");
        // ASSERT
        assertThat(responsePut.statusCode()).isEqualTo(404);
        assertThat(responsePut.getBody().asString()).startsWith("Cannot PUT /1/labels/" + labelId + "/");
    }

    // ----
    // name
    // ----

    @Test
    public void N1_shouldNotUpdateLabelFieldNameWithoutValue() {
        // ARRANGE
        String expectedResponse = """
                {
                  "message": "invalid value for value",
                  "error": "BAD_REQUEST_ERROR"
                }
                """;
        // ACT
        responsePut = putUpdateFieldOnLabelWithoutFieldValue(labelId, LabelBaseQueryParameters.NAME);
        // ASSERT
        assertThat(responsePut.statusCode()).isEqualTo(400);
        compareResponseWithJson(responsePut, expectedResponse);
    }

    // -----
    // color
    // -----

    @Test
    public void N2_shouldNotUpdateLabelFieldColorWithIncorrectValue() {
        // ARRANGE
        String expectedResponse = """
                {
                    "message": "invalid value for value",
                    "error": "ERROR"
                }
                """;
        // ACT
        responsePut = putUpdateFieldOnLabel(labelId, LabelBaseQueryParameters.COLOR, "KEK123");
        // ASSERT
        assertThat(responsePut.statusCode()).isEqualTo(400);
        compareResponseWithJson(responsePut, expectedResponse);
    }
}
