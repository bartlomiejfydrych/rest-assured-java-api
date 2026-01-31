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
import static endpoints.labels.PUT_UpdateFieldOnLabelEndpoint.putUpdateFieldOnLabel;
import static endpoints.labels.PUT_UpdateFieldOnLabelEndpoint.putUpdateFieldOnLabelWithoutFieldValue;
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
        responsePost = postCreateBoard(generateRandomBoardName(), null);
        assertThat(responsePost.statusCode()).isEqualTo(200);
        boardId = responsePost.getBody().jsonPath().getString("id");
        responsePost = postCreateLabel(boardId, generateRandomLabelName(), generateRandomLabelColor());
        assertThat(responsePost.statusCode()).isEqualTo(200);
        responsePostDto = deserializeAndValidateJson(responsePost, POST_CreateLabelDto.class);
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

    // color

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

    // --------------
    // NEGATIVE TESTS
    // --------------

    // name

    @Test
    public void N1_shouldNotUpdateLabelFieldNameWithoutValue() {
        // PUT
        responsePut = putUpdateFieldOnLabelWithoutFieldValue(labelId, LabelBaseQueryParameters.NAME);
        assertThat(responsePut.statusCode()).isEqualTo(400);
        assertThat(responsePut.getBody().asString()).isEqualTo("invalid value for value");
    }

    // color

    @Test
    public void N2_shouldNotUpdateLabelFieldColorWithIncorrectValue() {

        String expectedResponse = """
                {
                    "message": "invalid value for value",
                    "error": "ERROR"
                }
                """;

        // PUT
        responsePut = putUpdateFieldOnLabel(labelId, LabelBaseQueryParameters.COLOR, "KEK123");
        assertThat(responsePut.statusCode()).isEqualTo(400);
        compareResponseWithJson(responsePut, expectedResponse);
    }
}
