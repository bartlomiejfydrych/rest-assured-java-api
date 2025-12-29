package endpoints;

import io.restassured.specification.RequestSpecification;

import static configuration.RequestSpecConfig.getRequestSpecification;

public class BaseEndpoint {

    // ==========================================================================================================
    // METHODS – MAIN
    // ==========================================================================================================

    protected static RequestSpecification spec() {
        return getRequestSpecification();
    }
}
