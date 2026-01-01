package endpoints.lists;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class GET_GetListEndpoint extends ListsBaseEndpoint {

    // ==========================================================================================================
    // METHODS – MAIN
    // ==========================================================================================================

    public static Response getGetList(String listId) {
        return given().
                    spec(getSpecification()).
                when().
                    get(listById(listId)).
                then().
                    extract().
                    response();
    }
}
