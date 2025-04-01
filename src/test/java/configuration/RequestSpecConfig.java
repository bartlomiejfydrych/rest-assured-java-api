package configuration;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class RequestSpecConfig {

    private static final RequestSpecification requestSpecification = new RequestSpecBuilder()
            .addQueryParam("key", Config.getTrelloApiKey())
            .addQueryParam("token", Config.getTrelloToken())
            .setBaseUri(BaseUrlBuilder.buildBaseUrl())
            .setContentType(ContentType.JSON)
            .build();

    public static RequestSpecification getRequestSpecification() {
        return requestSpecification;
    }
}
