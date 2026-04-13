package endpoints.utils;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class NamedEndpoint {

    private final String name;
    private final EndpointCall endpoint;

    public NamedEndpoint(String name, EndpointCall endpoint) {
        this.name = name;
        this.endpoint = endpoint;
    }

    public Response call(RequestSpecification spec) {
        return endpoint.call(spec);
    }

    @Override
    public String toString() {
        return name;
    }
}
