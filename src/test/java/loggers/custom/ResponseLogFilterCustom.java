package loggers.custom;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

public class ResponseLogFilterCustom implements Filter {

    private final boolean logOptional;
    private final boolean colorEnabled;

    // =====================================================================
    // CONSTRUCTOR
    // =====================================================================

    public ResponseLogFilterCustom(boolean logOptional, boolean colorEnabled) {
        this.logOptional = logOptional;
        this.colorEnabled = colorEnabled;
    }

    // =====================================================================
    // FILTER
    // =====================================================================

    @Override
    public Response filter(
            FilterableRequestSpecification request,
            FilterableResponseSpecification responseSpec,
            FilterContext ctx) {

        long startTime = System.currentTimeMillis();
        Response response = ctx.next(request, responseSpec);
        long elapsedTime = System.currentTimeMillis() - startTime;

        logBase(request, response, elapsedTime);

        if (logOptional) {
            logOptional(request);
        }

        return response;
    }

    // =====================================================================
    // BASE LOGGING
    // =====================================================================

    private void logBase(
            FilterableRequestSpecification request,
            Response response,
            long elapsedTimeMs) {

        System.out.println("\n==================== API CALL ====================");

        // REQUEST META
        System.out.println("REQUEST:");
        System.out.println("  Method: " + request.getMethod());
        System.out.println("  URL:    " + request.getURI());

        // RESPONSE META
        ConsoleColors.green(
                "\nStatus: " + response.getStatusCode() + " " + response.getStatusLine(),
                colorEnabled
        );
        System.out.println("Time:   " + elapsedTimeMs + " ms");
        System.out.println("Size:   " + response.getBody().asByteArray().length + " bytes");

        // BODIES
        logRequestBody(request);
        logResponseBody(response);

        System.out.println("=================================================");
    }

    // =====================================================================
    // OPTIONAL LOGGING
    // =====================================================================

    private void logOptional(FilterableRequestSpecification request) {

        ConsoleColors.purple("\nOPTIONAL REQUEST DATA:", colorEnabled);

        printPretty("Query params", request.getQueryParams());
        printPretty("Headers", request.getHeaders().asList());
        printPretty("Cookies", request.getCookies());
    }

    // =====================================================================
    // BODY HANDLING
    // =====================================================================

    private void logRequestBody(FilterableRequestSpecification request) {
        Object body = request.getBody();
        if (body == null) {
            return;
        }

        ConsoleColors.cyan("\nRequest body:", colorEnabled);
        JsonColorPrinter.print(body.toString(), colorEnabled);
    }

    private void logResponseBody(Response response) {
        System.out.println("\nResponse body:");
        JsonColorPrinter.print(response.getBody().asString(), colorEnabled);
    }

    // =====================================================================
    // HELPERS
    // =====================================================================

    private void printPretty(String title, Object data) {
        if (data == null || data.toString().isEmpty()) {
            return;
        }

        ConsoleColors.purple("\n" + title + ":", colorEnabled);
        JsonColorPrinter.print(data.toString(), colorEnabled);
    }
}
