package base;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import configuration.Config;
import configuration.RequestSpecConfig;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;

public class TestBase {
    // Object containing all request settings
    protected static RequestSpecification requestSpecificationCommon;
    protected static Response response;
    protected static Faker faker = new Faker();
    protected static ObjectMapper mapper;

    @BeforeAll
    public static void setUpAll() {

        // LOGS

        // Always print in console all request and response data
        if (Config.getLogsAlways()) {
            RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
        }
        // Only when test fail print in console all request and response data
        if (Config.getLogsWhenFail()) {
            RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        }

        // OTHERS

        // Class that allows you to configure API requests in a readable and reusable way
        requestSpecificationCommon = RequestSpecConfig.getRequestSpecification();
    }
}
