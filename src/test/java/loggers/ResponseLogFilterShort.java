package loggers;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ResponseLogFilterShort implements Filter {

    // ==========================================================================================================
    // FIELDS
    // ==========================================================================================================

    private static final int MAX_BODY_LENGTH = 200;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    // ==========================================================================================================
    // METHODS – MAIN
    // ==========================================================================================================

    @Override
    public Response filter(
            FilterableRequestSpecification requestSpec,
            FilterableResponseSpecification responseSpec,
            FilterContext ctx) {

        Response response = ctx.next(requestSpec, responseSpec);

        log(requestSpec, response);

        return response;
    }

    // ==========================================================================================================
    // METHODS – SUB
    // ==========================================================================================================

    private void log(FilterableRequestSpecification requestSpec, Response response) {
        System.out.println("\nTIMESTAMP: " + LocalDateTime.now().format(FORMATTER));
        System.out.println("METHOD: " + requestSpec.getMethod());
        System.out.println("URL: " + requestSpec.getURI());
        System.out.println("STATUS CODE: " + response.getStatusCode());

        String body = response.getBody().asString();
        if (body.length() > MAX_BODY_LENGTH) {
            body = body.substring(0, MAX_BODY_LENGTH) + "...";
        }

        System.out.println("RESPONSE BODY:\n" + body + "\n");
    }
}
