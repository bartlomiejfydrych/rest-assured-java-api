package endpoints.boards;

import base.TestBase;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class POST_CreateBoard extends TestBase {

    private static final String url = "/boards";

    public static Response postCreateBoard(String name, Map<String, Object> queryParams) {

        RequestSpecification spec = given().
                spec(requestSpecificationCommon).
                queryParam("name", name);

        if (queryParams != null && !queryParams.isEmpty()) {
            spec.queryParams(queryParams);
        }

        return spec.
                when().
                    post(url).
                then().
                    extract().
                    response();
    }

    public static Response postCreateBoard() {
        return given().
                    spec(requestSpecificationCommon).
                when().
                    post(url).
                then().
                    extract().
                    response();
    }
}
