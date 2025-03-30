package base;

import configuration.BaseUrlBuilder;
import configuration.Config;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;

public class TestBase {
    // Object containing all request settings
    protected static RequestSpecification requestSpecificationCommon;

    @BeforeAll
    public static void setUpAll() {
        // Print in console all request and response data
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
        // Class that allows you to configure API requests in a readable and reusable way
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.addQueryParam("key", Config.getTrelloApiKey());
        requestSpecBuilder.addQueryParam("token", Config.getTrelloToken());
        requestSpecBuilder.setBaseUri(BaseUrlBuilder.buildBaseUrl());
        requestSpecBuilder.setContentType(ContentType.JSON);
        requestSpecificationCommon = requestSpecBuilder.build();
    }
}
