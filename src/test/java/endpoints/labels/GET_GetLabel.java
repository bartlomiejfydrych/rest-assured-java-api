package endpoints.labels;

import base.TestBase;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class GET_GetLabel extends TestBase {

    private static final String url = "/labels";

    public static Response getGetLabel(String id) {
        return given().
                    spec(requestSpecificationCommon).
                when().
                    get(url + "/" + id).
                then().
                    extract().
                    response();
    }
}
