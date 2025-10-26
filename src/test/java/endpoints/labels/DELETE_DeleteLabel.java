package endpoints.labels;

import base.TestBase;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class DELETE_DeleteLabel extends TestBase {

    private static final String url = "/labels";

    public static Response deleteDeleteLabel(String id) {
        return given().
                    spec(requestSpecificationCommon).
                when().
                    delete(url + "/" + id).
                then().
                    extract().
                    response();
    }
}
