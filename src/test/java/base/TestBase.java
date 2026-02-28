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

        List<Filter> additionalFilters = new ArrayList<>();

        // LOGS – FULL
        if (Config.getLogsFull()) {
            additionalFilters.add(new RequestLoggingFilter());
            additionalFilters.add(new ResponseLoggingFilter());
        }

        // LOGS – CUSTOM
        else if (Config.getLogsCustomBase()) {
            additionalFilters.add(
                    new ResponseLogFilterCustom(
                            Config.getLogsCustomOptional(),
                            Config.getLogsCustomColor()
                    )
            );
        }

        // LOGS – SHORT
        else if (Config.getLogsShort()) {
            additionalFilters.add(new ResponseLogFilterShort());
        }

        if (!additionalFilters.isEmpty()) {

            // Get current filters (e.g. Allure added globally)
            List<Filter> currentFilters = new ArrayList<>(RestAssured.filters());

            // Add new
            currentFilters.addAll(additionalFilters);

            // Set everything up again
            RestAssured.filters(currentFilters);
        }
    }
}
