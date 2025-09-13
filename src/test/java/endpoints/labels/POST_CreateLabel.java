package endpoints.labels;

import base.TestBase;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

import static io.restassured.RestAssured.given;


public class POST_CreateLabel extends TestBase {

    private static final String url = "/labels";

    public static Response postCreateLabel(String idBoard, String name, String color) {

        RequestSpecification spec = given().
                spec(requestSpecificationCommon).
                queryParam("idBoard", idBoard).
                queryParam("name", name).
                queryParam("color", color);

        return spec.
                when().
                    post(url).
                then().
                    extract().
                    response();
    }

    public static Response postCreateLabelAnyParams(Map<String, Object> queryParams) {

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
