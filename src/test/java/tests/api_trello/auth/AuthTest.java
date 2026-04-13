package tests.api_trello.auth;

import endpoints.utils.NamedEndpoint;
import endpoints.utils.providers.AllEndpointsProvider;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import tests.base.TestBase;

import java.util.stream.Stream;

import static configuration.ConfigRequestSpec.getRequestSpecificationWithoutApiKey;
import static configuration.ConfigRequestSpec.getRequestSpecificationWithoutToken;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AuthTest extends TestBase {

    static Stream<Arguments> authProvider() {
        return Stream.of(
                Arguments.of(getRequestSpecificationWithoutApiKey(), 401),
                Arguments.of(getRequestSpecificationWithoutToken(), 401)
        );
    }

    @DisplayName("Auth validation for all endpoints")
    @ParameterizedTest(name = "[{index}] {0} → {1}")
    @MethodSource("providers")
    void shouldReturn401ForUnauthorizedRequests(
            NamedEndpoint endpoint,
            String authCase,
            RequestSpecification spec
    ) {
        Response response = endpoint.call(spec);

        assertThat(response.statusCode()).isEqualTo(401);
    }

    static Stream<Arguments> providers() {
        return AllEndpointsProvider.all()
                .flatMap(endpoint ->
                        Stream.of(
                                Arguments.of(endpoint, "Missing API Key", getRequestSpecificationWithoutApiKey()),
                                Arguments.of(endpoint, "Missing Token", getRequestSpecificationWithoutToken())
                        )
                );
    }
}
