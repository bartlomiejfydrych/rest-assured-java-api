package endpoints.lists;

import base.TestBase;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class PUT_UpdateList extends TestBase {

    private static final String url = "/lists";

    public static Response putUpdateList(String idBoard, String idList, Map<String, Object> queryParams) {

        RequestSpecification spec = given().
                spec(requestSpecificationCommon).
                queryParam("idBoard", idBoard);

        if (queryParams != null && !queryParams.isEmpty()) {
            spec.queryParams(queryParams);
        }

        return spec.
                when().
                    put(url + "/" + idList).
                then().
                    extract().
                    response();
    }

    public static Response postUpdateListAnyParams(String idList, Map<String, Object> queryParams) {

        RequestSpecification spec = given().
                spec(requestSpecificationCommon);

        if (queryParams != null && !queryParams.isEmpty()) {
            spec.queryParams(queryParams);
        }

        return spec.
                when().
                    put(url + "/" + idList).
                then().
                    extract().
                    response();
    }
}
