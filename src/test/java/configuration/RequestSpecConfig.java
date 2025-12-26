package configuration;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class RequestSpecConfig {

    // ==========================================================================================================
    // FIELDS
    // ==========================================================================================================

    private static final RequestSpecification requestSpecification = new RequestSpecBuilder()
            .addQueryParam("key", Config.getTrelloApiKey())
            .addQueryParam("token", Config.getTrelloToken())
            .setBaseUri(BaseUrlBuilder.buildBaseUrl())
            .setContentType(ContentType.JSON)
            .build();

    // ==========================================================================================================
    // METHODS – MAIN
    // ==========================================================================================================

    public static RequestSpecification getRequestSpecification() {
        return requestSpecification;
    }
}
