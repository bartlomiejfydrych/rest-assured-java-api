package base;

import configuration.RequestSpecConfig;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
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
        requestSpecificationCommon = RequestSpecConfig.getRequestSpecification();
    }
}
