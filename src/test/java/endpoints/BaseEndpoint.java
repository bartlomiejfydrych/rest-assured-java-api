package endpoints;

import configuration.RequestSpecConfig;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

public class BaseEndpoint {

    // ==========================================================================================================
    // METHODS – MAIN
    // ==========================================================================================================

    protected static RequestSpecification getSpecification() {
        return RequestSpecConfig.getRequestSpecification();
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
