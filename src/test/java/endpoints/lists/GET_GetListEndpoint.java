package endpoints.lists;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class GET_GetListEndpoint extends ListsBaseEndpoint {

    // ==========================================================================================================
    // METHODS – MAIN
    // ==========================================================================================================

    public static Response getList(String listId, RequestSpecification spec) {
        return given().
                    spec(spec).
                when().
                    get(listById(listId)).
                then().
                    extract().
                    response();
    }

    public static Response getGetList(String listId) {
        return getList(listId, getSpecification());
    }
}
