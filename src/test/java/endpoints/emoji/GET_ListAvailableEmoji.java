package endpoints.emoji;

import base.TestBase;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class GET_ListAvailableEmoji extends TestBase {

    private static final String url = "/emoji";

    public static Response getListAvailableEmoji() {
        return given().
                    spec(requestSpecificationCommon).
                when().
                    get(url).
                then().
                    extract().
                    response();
    }
}
