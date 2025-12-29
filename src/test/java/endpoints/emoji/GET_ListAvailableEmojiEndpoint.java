package endpoints.emoji;

import base.TestBase;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class GET_ListAvailableEmojiEndpoint extends TestBase {

    private static final String url = "/emoji";

    public static Response getListAvailableEmoji(Map<String, Object> queryParams) {

        RequestSpecification spec = given().
                spec(requestSpecificationCommon);

        if (queryParams != null && !queryParams.isEmpty()) {
            spec.queryParams(queryParams);
        }

        return spec.
                when().
                    get(url).
                then().
                    extract().
                    response();
    }
}
