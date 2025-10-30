package endpoints.labels;

import base.TestBase;
import enums.labels.LabelField;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class PUT_UpdateFieldOnLabel extends TestBase {

    private static final String url = "/labels";

    public static Response putUpdateFieldOnLabel(String id, LabelField field, String value) {

        RequestSpecification spec = given().
                spec(requestSpecificationCommon).
                queryParam("value", value);

        return spec.
                when().
                    put(url + "/" + id + "/" + field.getValue()).
                then().
                    extract().
                    response();
    }

    public static Response putUpdateFieldOnLabelWithoutValue(String id, LabelField field) {

        RequestSpecification spec = given().
                spec(requestSpecificationCommon);

        return spec.
                when().
                put(url + "/" + id + "/" + field.getValue()).
                then().
                extract().
                response();
    }
}
