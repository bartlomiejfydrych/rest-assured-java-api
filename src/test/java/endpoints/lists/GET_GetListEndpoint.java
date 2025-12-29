package endpoints.lists;

import base.TestBase;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class GET_GetListEndpoint extends TestBase {

    private static final String url = "/lists";

    public static Response getGetList(String id) {
        return given().
                    spec(requestSpecificationCommon).
                when().
                    get(url + "/" + id).
                then().
                    extract().
                    response();
    }
}
