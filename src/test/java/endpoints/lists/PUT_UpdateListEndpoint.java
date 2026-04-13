package endpoints.lists;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import payloads.lists.PUT_UpdateListPayload;

import static io.restassured.RestAssured.given;

public class PUT_UpdateListEndpoint extends ListsBaseEndpoint {

    // ==========================================================================================================
    // METHODS – MAIN
    // ==========================================================================================================

    // -----------------
    // WITH QUERY PARAMS
    // -----------------

    public static Response updateList(String listId, PUT_UpdateListPayload payload, RequestSpecification spec) {

        RequestSpecification requestSpecification =
                given().
                    spec(spec);

        if (payload != null) {
            applyQueryParams(requestSpecification, payload.toQueryParams());
        }

        return requestSpecification.
                when().
                    put(listById(listId)).
                then().
                    extract().
                    response();
    }

    public static Response putUpdateList(String listId, PUT_UpdateListPayload payload) {
        return updateList(listId, payload, getSpecification());
    }

    // -------------------
    // WITHOUT ID & PARAMS
    // -------------------

    public static Response putUpdateListWithoutIdAndParams() {
        return given().
                    spec(getSpecification()).
                when().
                    put(ENDPOINT_LISTS).
                then().
                    extract().
                    response();
    }
}
