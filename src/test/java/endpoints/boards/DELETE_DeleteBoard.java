package endpoints.boards;

import base.TestBase;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class DELETE_DeleteBoard extends TestBase {

    private static final String url = "/boards";

    public static Response deleteDeleteBoard(String id) {
        return given().
                    spec(requestSpecificationCommon).
                when().
                    delete(url + "/" + id).
                then().
                    extract().
                    response();
    }
}
