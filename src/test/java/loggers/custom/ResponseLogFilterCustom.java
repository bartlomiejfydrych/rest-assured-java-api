package loggers.custom;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import utils.loggers.UtilsSensitiveDataMasker;

import static utils.loggers.UtilsSensitiveDataMasker.maskAll;
import static utils.loggers.UtilsSensitiveDataMasker.sanitizeUrl;

public class ResponseLogFilterCustom implements Filter {

    private final boolean logOptional;
    private final boolean colorEnabled;

    // ==========================================================================================================
    // CONSTRUCTOR
    // ==========================================================================================================

    public ResponseLogFilterCustom(boolean logOptional, boolean colorEnabled) {
        this.logOptional = logOptional;
        this.colorEnabled = colorEnabled;
    }

    // ==========================================================================================================
    // FILTER
    // ==========================================================================================================

    @Override
    public Response filter(
            FilterableRequestSpecification request,
            FilterableResponseSpecification responseSpec,
            FilterContext ctx) {

        long startTime = System.currentTimeMillis();
        Response response = ctx.next(request, responseSpec);
        long elapsedTime = System.currentTimeMillis() - startTime;

        logBase(request, response, elapsedTime);

        return response;
    }

    // ==========================================================================================================
    // BASE LOGGING
    // ==========================================================================================================

    private void logBase(
            FilterableRequestSpecification request,
            Response response,
            long elapsedTimeMs) {

        System.out.println("\n=============================================================================================================");
        System.out.println("NEW REQUEST!");
        System.out.println("=============================================================================================================");

        if (logOptional) {
            logOptional(request);
        }

        System.out.println("\n-----------------");
        System.out.println("BASIC INFORMATION");
        System.out.println("-----------------\n");

        // REQUEST META
        System.out.println("Method: " + request.getMethod());
        System.out.println("URL:    " + sanitizeUrl(request.getURI()));

        // RESPONSE META
        ConsoleColors.green("Status: " + response.getStatusCode() + " " + response.getStatusLine(), colorEnabled);
        System.out.println("Time:   " + elapsedTimeMs + " ms");
        System.out.println("Size:   " + response.getBody().asByteArray().length + " bytes");

        // BODIES
        logRequestBody(request);
        logResponseBody(response);
    }

    // ==========================================================================================================
    // OPTIONAL LOGGING
    // ==========================================================================================================

    private void logOptional(FilterableRequestSpecification request) {

        ConsoleColors.purple("\n-----------------------------", colorEnabled);
        ConsoleColors.purple("OPTIONAL REQUEST DATA – IS ON", colorEnabled);
        ConsoleColors.purple("-----------------------------", colorEnabled);

        UtilsSensitiveDataMasker.MaskedRequest masked = maskAll(request);

        printPretty("Query params", masked.queryParams);
        printPretty("Headers", masked.headers);
        printPretty("Cookies", request.getCookies());
    }

    // ==========================================================================================================
    // BODY HANDLING
    // ==========================================================================================================

    private void logRequestBody(FilterableRequestSpecification request) {
        Object body = request.getBody();
        if (body == null) {
            return;
        }

        System.out.println("\n------------");
        ConsoleColors.cyan("REQUEST BODY", colorEnabled);
        System.out.println("------------\n");
        JsonColorPrinter.print(body.toString(), colorEnabled);
    }

    private void logResponseBody(Response response) {
        System.out.println("\n-------------");
        System.out.println("RESPONSE BODY");
        System.out.println("-------------\n");
        JsonColorPrinter.print(response.getBody().asString(), colorEnabled);
    }

    // ==========================================================================================================
    // HELPERS
    // ==========================================================================================================

    private void printPretty(String title, Object data) {
        if (data == null) return;

        ConsoleColors.purple("\n" + title + ":", colorEnabled);

        try {
            String json = new com.fasterxml.jackson.databind.ObjectMapper()
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(data);

            JsonColorPrinter.print(json, colorEnabled);

        } catch (Exception e) {
            JsonColorPrinter.print(data.toString(), colorEnabled);
        }
    }
}
