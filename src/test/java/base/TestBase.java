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
    // Variable to save response
    protected static Response response;
    // Faker object to generate fake random data
    protected static Faker faker = new Faker();
    // Jackson class used to JSON conversion
    protected static ObjectMapper mapper = new ObjectMapper();

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
