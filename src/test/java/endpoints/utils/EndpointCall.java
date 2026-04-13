package endpoints.utils;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

@FunctionalInterface
public interface EndpointCall {
    Response call(RequestSpecification spec);
}
