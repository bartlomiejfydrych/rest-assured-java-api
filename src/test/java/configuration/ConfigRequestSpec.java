package configuration;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class ConfigRequestSpec {

    // ==========================================================================================================
    // BUILDERS
    // ==========================================================================================================

    private static RequestSpecBuilder baseBuilder() {
        return new RequestSpecBuilder()
                .setBaseUri(BaseUrlBuilder.buildBaseUrl())
                .setContentType(ContentType.JSON);
    }

    public static RequestSpecification getRequestSpecification() {
        return baseBuilder()
                .addQueryParam("key", Config.getTrelloApiKey())
                .addQueryParam("token", Config.getTrelloToken())
                .build();
    }

    public static RequestSpecification getRequestSpecificationWithoutApiKey() {
        return baseBuilder()
                .addQueryParam("token", Config.getTrelloToken())
                .build();
    }

    public static RequestSpecification getRequestSpecificationWithoutToken() {
        return baseBuilder()
                .addQueryParam("key", Config.getTrelloApiKey())
                .build();
    }
}
