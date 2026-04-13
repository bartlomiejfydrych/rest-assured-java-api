package endpoints.labels;

import enums.query_parameters.labels.LabelBaseQueryParameters;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static enums.query_parameters.labels.PUT_UpdateFieldOnLabelQueryParameters.*;
import static io.restassured.RestAssured.given;

public class PUT_UpdateFieldOnLabelEndpoint extends LabelsBaseEndpoint {

    // ==========================================================================================================
    // METHODS – SUB
    // ==========================================================================================================

    // -----------
    // CORE METHOD
    // -----------

    private static Response updateFieldOnLabel(String requestPath, String fieldValue, RequestSpecification spec) {

        RequestSpecification requestSpecification =
                given().
                    spec(spec);

        if (fieldValue != null) {
            requestSpecification.queryParam(VALUE.key(), fieldValue);
        }

        return requestSpecification.
                when().
                    put(requestPath).
                then().
                    extract().
                    response();
    }

    // -----
    // UTILS
    // -----

    private static String labelFieldById(String labelId, LabelBaseQueryParameters labelField) {
        return labelById(labelId) + "/" + labelField.key();
    }

    // ==========================================================================================================
    // METHODS – MAIN
    // ==========================================================================================================

    // --------
    // POSITIVE
    // --------

    public static Response putUpdateFieldOnLabel(String labelId, LabelBaseQueryParameters labelField, String fieldValue) {
        return updateFieldOnLabel(labelFieldById(labelId, labelField), fieldValue, getSpecification());
    }

    // --------
    // NEGATIVE
    // --------

    public static Response putUpdateFieldOnLabelCustomField(String labelId, String labelField, String fieldValue) {
        return updateFieldOnLabel(labelById(labelId) + "/" + labelField, fieldValue, getSpecification());
    }

    public static Response putUpdateFieldOnLabelWithoutFieldValue(String labelId, LabelBaseQueryParameters labelField) {
        return updateFieldOnLabel(labelFieldById(labelId, labelField), null, getSpecification());
    }

    public static Response putUpdateFieldOnLabelWithoutLabelField(String labelId) {
        return updateFieldOnLabel(labelById(labelId), null, getSpecification());
    }
}
