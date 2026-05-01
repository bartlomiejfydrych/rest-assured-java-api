package tests.base;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import configuration.Config;
import enums.configuration.LogsMode;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import loggers.UnifiedLoggingFilter;
import org.junit.jupiter.api.BeforeAll;
import providers.ProviderRandom;

import java.util.Random;

import static configuration.ConfigRequestSpec.getRequestSpecification;

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
    protected static final String testTagFlaky = "flaky";
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
        requestSpecificationCommon = getRequestSpecification();
    }

    // ==========================================================================================================
    // METHODS – SUB
    // ==========================================================================================================

    // --------------------
    // LOGS – CONFIGURATION
    // --------------------

    private static void configureLogging() {

        Config.validateLogsConfig();

        RestAssured.reset();

        if (Config.getLogsMode() == LogsMode.FULL) {

            RestAssured.filters(
                    new RequestLoggingFilter(),
                    new ResponseLoggingFilter(),
                    new UnifiedLoggingFilter(
                            Config.getLogsMode(),
                            Config.getLogsCustomOptional(),
                            Config.getLogsCustomColor()
                    )
            );

            return;
        }

        RestAssured.filters(
                new UnifiedLoggingFilter(
                        Config.getLogsMode(),
                        Config.getLogsCustomOptional(),
                        Config.getLogsCustomColor()
                )
        );
    }
}
