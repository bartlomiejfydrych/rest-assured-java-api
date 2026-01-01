package endpoints.lists;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import payloads.lists.POST_CreateNewListPayload;

import static io.restassured.RestAssured.given;

public class POST_CreateNewListEndpoint extends ListsBaseEndpoint {

    // ==========================================================================================================
    // METHODS – MAIN
    // ==========================================================================================================

    // -----------------
    // WITH QUERY PARAMS
    // -----------------

    public static Response postCreateNewList(String boardId, String listName, POST_CreateNewListPayload payload) {

        RequestSpecification requestSpecification =
                given().
                    spec(getSpecification()).
                    queryParam("idBoard", boardId).
                    queryParam("name", listName);

        if (payload != null) {
            applyQueryParams(requestSpecification, payload.toQueryParams());
        }

        return requestSpecification.
                when().
                    post(ENDPOINT_LISTS).
                then().
                    extract().
                    response();
    }

    // ---------------
    // WITH ANY PARAMS
    // ---------------

    public static Response postCreateNewListWithAnyParams(POST_CreateNewListPayload payload) {

        RequestSpecification requestSpecification =
                given().
                    spec(getSpecification());

        if (payload != null) {
            applyQueryParams(requestSpecification, payload.toQueryParams());
        }

        return requestSpecification.
                when().
                    post(ENDPOINT_LISTS).
                then().
                    extract().
                    response();
    }

    // --------------------
    // WITHOUT QUERY PARAMS
    // --------------------

    public static Response postCreateNewListMissingRequiredParameters() {
        return given().
                    spec(getSpecification()).
                when().
                    post(ENDPOINT_LISTS).
                then().
                    extract().
                    response();
    }
}
