package endpoints.lists;

import base.TestBase;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class POST_CreateNewListEndpoint extends TestBase {

    private static final String url = "/lists";

    public static Response postCreateNewList(String idBoard, String name, Map<String, Object> queryParams) {

        RequestSpecification spec = given().
                spec(requestSpecificationCommon).
                queryParam("idBoard", idBoard).
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

    public static Response postCreateNewListAnyParams(Map<String, Object> queryParams) {

        RequestSpecification spec = given().
                spec(requestSpecificationCommon);

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
}
