package endpoints;

import io.restassured.specification.RequestSpecification;

import java.util.Map;

import static configuration.ConfigRequestSpec.*;

public class BaseEndpoint {

    // ==========================================================================================================
    // METHODS – MAIN
    // ==========================================================================================================

    protected static RequestSpecification getSpecification() {
        return getRequestSpecification();
    }

    protected static RequestSpecification getSpecificationWithoutApiKey() {
        return getRequestSpecificationWithoutApiKey();
    }

    protected static RequestSpecification getSpecificationWithoutToken() {
        return getRequestSpecificationWithoutToken();
    }

    // ==========================================================================================================
    // UTILS
    // ==========================================================================================================

    protected static RequestSpecification applyQueryParams(
            RequestSpecification requestSpecification,
            Map<String, Object> queryParams
    ) {
        if (queryParams != null && !queryParams.isEmpty()) {
            requestSpecification.queryParams(queryParams);
        }
        return requestSpecification;
    }
}
