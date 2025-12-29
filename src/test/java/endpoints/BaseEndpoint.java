package endpoints;

import configuration.RequestSpecConfig;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

public class BaseEndpoint {

    // ==========================================================================================================
    // METHODS – MAIN
    // ==========================================================================================================

    protected static RequestSpecification spec() {
        return RequestSpecConfig.getRequestSpecification();
    }

    // ==========================================================================================================
    // UTILS
    // ==========================================================================================================

    protected static RequestSpecification applyQueryParams(
            RequestSpecification spec,
            Map<String, Object> queryParams
    ) {
        if (queryParams != null && !queryParams.isEmpty()) {
            spec.queryParams(queryParams);
        }
        return spec;
    }
}
