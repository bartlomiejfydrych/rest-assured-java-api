package endpoints.boards;

import base.TestBase;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class GET_GetBoardEndpoint extends TestBase {

    private static final String url = "/boards";

    public static Response getGetBoard(String id) {
        return given().
                    spec(requestSpecificationCommon).
                when().
                    get(url + "/" + id).
                then().
                    extract().
                    response();
    }
}
