package endpoints.labels;

import enums.labels.LabelField;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class PUT_UpdateFieldOnLabelEndpoint extends LabelsBaseEndpoint {

    // ==========================================================================================================
    // METHODS – SUB
    // ==========================================================================================================

    // -----------
    // CORE METHOD
    // -----------

    private static Response put(String requestPath, String fieldValue) {

        RequestSpecification requestSpecification =
                given().
                    spec(getSpecification());

        if (fieldValue != null) {
            requestSpecification.queryParam("value", fieldValue);
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

    private static String labelFieldById(String labelId, LabelField labelField) {
        return labelById(labelId) + "/" + labelField.getValue();
    }

    // ==========================================================================================================
    // METHODS – MAIN
    // ==========================================================================================================

    // --------
    // POSITIVE
    // --------

    public static Response updateFieldOnLabel(String labelId, LabelField labelField, String fieldValue) {
        return put(labelFieldById(labelId, labelField), fieldValue);
    }

    // --------
    // NEGATIVE
    // --------

    public static Response updateFieldOnLabelWithoutFieldValue(String labelId, LabelField labelField) {
        return put(labelFieldById(labelId, labelField), null);
    }

    public static Response updateFieldOnLabelWithoutLabelField(String labelId) {
        return put(labelById(labelId), null);
    }

    public static Response updateFieldOnLabelWithoutLabelId() {
        return put(ENDPOINT_LABELS, null);
    }
}
