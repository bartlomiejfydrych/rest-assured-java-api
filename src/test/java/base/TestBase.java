package base;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import configuration.Config;
import configuration.RequestSpecConfig;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;
import utils.UtilsRandomProvider;

import java.util.Random;

public class TestBase {

    // ==========================================================================================================
    // FIELDS
    // ==========================================================================================================

    // --------
    // REQUESTS
    // --------

    // Object containing all request settings
    protected static RequestSpecification requestSpecificationCommon;
    // Mapper object to transform response String into JsonNode object
    protected static ObjectMapper objectMapper = new ObjectMapper();

    // -----
    // TESTS
    // -----

    // Test tags
    protected static final String testTagPositive = "positive";
    protected static final String testTagNegative = "negative";

    // -------
    // HELPERS
    // -------

    // Faker object to generate fake random data
    protected static final Faker faker = UtilsRandomProvider.faker();
    // Random object to used to select a random element
    protected static final Random random = UtilsRandomProvider.random();

    // ==========================================================================================================
    // SET UP
    // ==========================================================================================================

    @BeforeAll
    public static void setUpAll() {
        // LOGS
        configureLogging();
        // CONFIGURATION – REQUEST
        // Class that allows you to configure API requests in a readable and reusable way
        requestSpecificationCommon = RequestSpecConfig.getRequestSpecification();
    }

    // ==========================================================================================================
    // METHODS – SUB
    // ==========================================================================================================

    // --------------------
    // LOGS – CONFIGURATION
    // --------------------

    private static void configureLogging() {
        // Always print in console all request and response data
        if (Config.getLogsAlways()) {
            RestAssured.filters(
                    new RequestLoggingFilter(),
                    new ResponseLoggingFilter()
            );
        }
        // Only when test fail print in console all request and response data
        if (Config.getLogsWhenFail()) {
            RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        }
    }
}
