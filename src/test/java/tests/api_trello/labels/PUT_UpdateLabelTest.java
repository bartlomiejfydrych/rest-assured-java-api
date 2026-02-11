package tests.api_trello.labels;

import base.TestBase;
import dto.labels.GET_GetLabelDto;
import dto.labels.PUT_UpdateLabelDto;
import expected_responses.labels.PUT_UpdateLabelExpected;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import payloads.labels.PUT_UpdateLabelPayload;
import utils.UtilsString;

import static endpoints.boards.DEL_DeleteBoardEndpoint.deleteDeleteBoard;
import static endpoints.boards.POST_CreateBoardEndpoint.postCreateBoard;
import static endpoints.labels.GET_GetLabelEndpoint.getGetLabel;
import static endpoints.labels.POST_CreateLabelEndpoint.postCreateLabel;
import static endpoints.labels.PUT_UpdateLabelEndpoint.putUpdateLabel;
import static expected_responses.labels.PUT_UpdateLabelExpected.*;
import static org.assertj.core.api.Assertions.assertThat;
import static utils.UtilsCompare.compareObjects;
import static utils.UtilsCompare.compareResponseWithJson;
import static utils.UtilsRandom.pickRandom;
import static utils.UtilsString.getAllCharactersSetInRandomOrder;
import static utils.response.UtilsResponseDeserializer.deserializeAndValidateJson;
import static utils_tests.boards.POST_CreateBoardUtils.generateRandomBoardName;
import static utils_tests.labels.POST_CreateLabelUtils.generateRandomLabelColor;
import static utils_tests.labels.POST_CreateLabelUtils.generateRandomLabelName;
import static utils_tests.labels.PUT_UpdateLabelUtils.validateGetAgainstPut;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PUT_UpdateLabelTest extends TestBase {

    // ==========================================================================================================
    // FIELDS
    // ==========================================================================================================

    // --------
    // RESPONSE
    // --------

    private Response responsePost;
    private Response responsePut;
    private Response responseGet;
    private Response responseDelete;

    // ---------------
    // CLASS VARIABLES
    // ---------------

    // BOARD
    private String boardId;
    // LABEL
    private String labelId;
    private String labelName;
    private String labelColor;

    // ==========================================================================================================
    // SETUP & TEARDOWN
    // ==========================================================================================================

    // ----------
    // BEFORE ALL
    // ----------

    @BeforeAll
    public void setUpCreateBoardAndLabel() {
        responsePost = postCreateBoard(generateRandomBoardName(), null);
        assertThat(responsePost.statusCode()).isEqualTo(200);
        boardId = responsePost.getBody().jsonPath().getString("id");
        responsePost = postCreateLabel(boardId, generateRandomLabelName(), generateRandomLabelColor());
        assertThat(responsePost.statusCode()).isEqualTo(200);
        labelId = responsePost.getBody().jsonPath().getString("id");
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

    @Test
    public void P1_shouldUpdateLabelWithCorrectValuesAndNameWithSpecialCharactersAndNumbers() {

        labelName = getAllCharactersSetInRandomOrder();
        labelColor = pickRandom("yellow", "purple", "blue", "red", "green", "orange", "black", "sky", "pink", "lime");

        PUT_UpdateLabelPayload payload = new PUT_UpdateLabelPayload.Builder()
                .setName(labelName)
                .setColor(labelColor)
                .build();

        // PUT
        responsePut = putUpdateLabel(labelId, payload);
        assertThat(responsePut.statusCode()).isEqualTo(200);
        PUT_UpdateLabelDto responsePutDto = deserializeAndValidateJson(responsePut, PUT_UpdateLabelDto.class);
        PUT_UpdateLabelDto expectedResponsePutDto =
                PUT_UpdateLabelExpected.base()
                        .withId(responsePutDto.id)
                        .withBoardId(boardId)
                        .withName(labelName)
                        .withColor(labelColor)
                        .build();
        compareObjects(responsePutDto, expectedResponsePutDto);
        // GET
        validateGetAgainstPut(responsePutDto);
    }

    @Test
    public void P2_shouldUpdateLabelWhenNameHaveOneCharacter() {

        labelName = UtilsString.getRandomSingleCharAlphanumeric();
        labelColor = pickRandom("yellow", "purple", "blue", "red", "green", "orange", "black", "sky", "pink", "lime");

        PUT_UpdateLabelPayload payload = new PUT_UpdateLabelPayload.Builder()
                .setName(labelName)
                .setColor(labelColor)
                .build();

        // PUT
        responsePut = putUpdateLabel(labelId, payload);
        assertThat(responsePut.statusCode()).isEqualTo(200);
        PUT_UpdateLabelDto responsePutDto = deserializeAndValidateJson(responsePut, PUT_UpdateLabelDto.class);
        PUT_UpdateLabelDto expectedResponsePutDto =
                PUT_UpdateLabelExpected.base()
                        .withId(responsePutDto.id)
                        .withBoardId(boardId)
                        .withName(labelName)
                        .withColor(labelColor)
                        .build();
        compareObjects(responsePutDto, expectedResponsePutDto);
        // GET
        validateGetAgainstPut(responsePutDto);
    }

    @Test
    public void P3_shouldUpdateLabelWhenNameAndColorAreMissing() {
        // GET (Get current status of {LABEL})
        responseGet = getGetLabel(labelId);
        assertThat(responseGet.statusCode()).isEqualTo(200);
        GET_GetLabelDto responseGetDto = deserializeAndValidateJson(responseGet, GET_GetLabelDto.class);
        // PUT
        responsePut = putUpdateLabel(labelId, null);
        assertThat(responsePut.statusCode()).isEqualTo(200);
        PUT_UpdateLabelDto responsePutDto = deserializeAndValidateJson(responsePut, PUT_UpdateLabelDto.class);
        compareObjects(responsePutDto, responseGetDto);
        // GET
        validateGetAgainstPut(responsePutDto);
    }

    @Test
    public void P4_shouldUpdateLabelWhenNameAndColorAreNull() {

        PUT_UpdateLabelPayload payload = new PUT_UpdateLabelPayload.Builder()
                .setName(null)
                .setColor(null)
                .build();

        // GET (Get current status of {LABEL})
        responseGet = getGetLabel(labelId);
        assertThat(responseGet.statusCode()).isEqualTo(200);
        GET_GetLabelDto responseGetDto = deserializeAndValidateJson(responseGet, GET_GetLabelDto.class);
        // PUT
        responsePut = putUpdateLabel(labelId, payload);
        assertThat(responsePut.statusCode()).isEqualTo(200);
        PUT_UpdateLabelDto responsePutDto = deserializeAndValidateJson(responsePut, PUT_UpdateLabelDto.class);
        compareObjects(responsePutDto, responseGetDto);
        // GET
        validateGetAgainstPut(responsePutDto);
    }

    /*
    @Test
    public void P5_shouldUpdateLabelWhenNameAndColorAreEmptyString() {
        // NOTE: Flaky test. Sometimes the fields become empty/null, sometimes they are not changed at all.

        // NOTE: When we insert empty strings directly in parameters, REST Assured ignores them. When we insert them using variables, it does not.
        labelName = "";
        labelColor = "";

        PUT_UpdateLabelPayload payload = new PUT_UpdateLabelPayload.Builder()
                .setName(labelName)
                .setColor(labelColor)
                .build();

        // GET (We need to retrieve the current state of the label)
        responseGet = getGetLabel(labelId);
        assertThat(responseGet.statusCode()).isEqualTo(200);
        GET_GetLabelDto responseGetDto = deserializeAndValidateJson(responseGet, GET_GetLabelDto.class);
        // PUT
        responsePut = putUpdateLabel(labelId, payload);
        assertThat(responsePut.statusCode()).isEqualTo(200);
        PUT_UpdateLabelDto responsePutDto = deserializeAndValidateJson(responsePut, PUT_UpdateLabelDto.class);
        // IF DATA NOT CHANGE (Except label {id})
        responseGetDto.id =  responsePutDto.id;
        // IF DATA CHANGE
        PUT_UpdateLabelDto expectedResponsePutDto =
                PUT_UpdateLabelExpected.base()
                        .withId(responsePutDto.id)
                        .withBoardId(boardId)
                        .withName(labelName)
                        .withColor(null)
                        .build();
        assertThat(responsePutDto).satisfiesAnyOf(
                dto -> compareObjects(dto, expectedResponsePutDto),   // IF DATA CHANGE
                dto -> compareObjects(dto, responseGetDto)            // IF DATA NOT CHANGE (Except label {id})
        );
        // GET
        Response responseGetAfterPut = getGetLabel(labelId);
        assertThat(responseGetAfterPut.statusCode()).isEqualTo(200);
        GET_GetLabelDto responseGetDtoAfterPut = deserializeAndValidateJson(responseGet, GET_GetLabelDto.class);
        assertThat(responseGetDtoAfterPut).satisfiesAnyOf(
                dto -> compareObjects(dto, expectedResponsePutDto),   // IF DATA CHANGE
                dto -> compareObjects(dto, responseGetDto)            // IF DATA NOT CHANGE (Except label {id})
        );
    }
    */

    // ==========================================================================================================
    // NEGATIVE TESTS
    // ==========================================================================================================

    // -----
    // color
    // -----

    @Test
    public void N1_shouldNotUpdateLabelWhenLabelColorIsIncorrect() {
        // ARRANGE
        PUT_UpdateLabelPayload payload = new PUT_UpdateLabelPayload.Builder()
                .setColor("N1KeK123")
                .build();
        // ACT
        responsePut = putUpdateLabel(boardId, payload);
        // ASSERT
        assertThat(responsePut.getStatusCode()).isEqualTo(400);
        compareResponseWithJson(responsePut, expectedPutLabelResponseInvalidColor);
    }
}
