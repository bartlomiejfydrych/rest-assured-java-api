package base;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import configuration.Config;
import configuration.ConfigRequestSpec;
import io.restassured.RestAssured;
import io.restassured.filter.Filter;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import loggers.AllureRestAssuredEnhanced;
import loggers.ResponseLogFilterShort;
import loggers.custom.ResponseLogFilterCustom;
import org.junit.jupiter.api.BeforeAll;
import providers.ProviderRandom;

import java.util.ArrayList;
import java.util.List;
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
    protected static final Faker faker = ProviderRandom.faker();
    // Random object to used to select a random element
    protected static final Random random = ProviderRandom.random();

    // ==========================================================================================================
    // SET UP
    // ==========================================================================================================

    @BeforeAll
    public static void setUpAll() {
        // LOGS
        configureLogging();
        // CONFIGURATION – REQUEST
        // Class that allows you to configure API requests in a readable and reusable way
        requestSpecificationCommon = ConfigRequestSpec.getRequestSpecification();
    }

    // ==========================================================================================================
    // METHODS – SUB
    // ==========================================================================================================

    // --------------------
    // LOGS – CONFIGURATION
    // --------------------

    private static void configureLogging() {

        // We clean the filters at the start (important for several test runs)
        RestAssured.filters();

        List<Filter> filters = new ArrayList<>();

        // ALLURE REPORT
        if (Config.getAllureReport()) {
            filters.add(new AllureRestAssuredEnhanced());
        }

        // LOGS – FULL
        if (Config.getLogsFull()) {
            filters.add(new RequestLoggingFilter());
            filters.add(new ResponseLoggingFilter());
        }

        // LOGS – CUSTOM
        else if (Config.getLogsCustomBase()) {
            filters.add(
                    new ResponseLogFilterCustom(
                            Config.getLogsCustomOptional(),
                            Config.getLogsCustomColor()
                    )
            );
        }

        // LOGS – SHORT
        else if (Config.getLogsShort()) {
            filters.add(new ResponseLogFilterShort());
        }

        // We set everything up once
        if (!filters.isEmpty()) {
            RestAssured.filters(filters);
        }
    }
}
