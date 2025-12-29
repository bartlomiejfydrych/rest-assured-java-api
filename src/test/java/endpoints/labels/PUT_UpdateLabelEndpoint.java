package endpoints.labels;

import base.TestBase;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class PUT_UpdateLabelEndpoint extends TestBase {

    private static final String url = "/labels";

    public static Response putUpdateLabel(String id, Map<String, Object> queryParams) {

        RequestSpecification spec = given().
                spec(requestSpecificationCommon);

        if (queryParams != null && !queryParams.isEmpty()) {
            spec.queryParams(queryParams);
        }

        return spec.
                when().
                    put(url + "/" + id).
                then().
                    extract().
                    response();
    }
}
