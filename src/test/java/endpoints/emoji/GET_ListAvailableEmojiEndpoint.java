package endpoints.emoji;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import payloads.emoji.GET_ListAvailableEmojiPayload;

import static io.restassured.RestAssured.given;

public class GET_ListAvailableEmojiEndpoint extends EmojiBaseEndpoint {

    // ==========================================================================================================
    // METHODS – MAIN
    // ==========================================================================================================

    // -----------------
    // WITH QUERY PARAMS
    // -----------------

    public static Response getListAvailableEmoji(GET_ListAvailableEmojiPayload payload) {

        RequestSpecification requestSpecification =
                given().
                    spec(getSpecification());

        if (payload != null) {
            applyQueryParams(requestSpecification, payload.toQueryParams());
        }

        return requestSpecification.
                when().
                    get(ENDPOINT_EMOJI).
                then().
                    extract().
                    response();
    }
}
