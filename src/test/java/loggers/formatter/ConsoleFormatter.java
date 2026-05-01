package loggers.formatter;

import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import loggers.colors.ConsoleColors;
import loggers.colors.JsonColorPrinter;
import utils.loggers.UtilsSensitiveDataMasker;

import static utils.loggers.UtilsSensitiveDataMasker.maskAll;
import static utils.loggers.UtilsSensitiveDataMasker.sanitizeUrl;

public class ConsoleFormatter {

    public void logFull(
            FilterableRequestSpecification request,
            Response response,
            String responseBody,
            long elapsedTimeMs,
            boolean logOptional,
            boolean colorEnabled
    ) {

        System.out.println("\n=============================================================================================================");
        System.out.println("NEW REQUEST!");
        System.out.println("=============================================================================================================");

        if (logOptional) {
            logOptional(request, colorEnabled);
        }

        System.out.println("\n-----------------");
        System.out.println("BASIC INFORMATION");
        System.out.println("-----------------\n");

        // REQUEST META
        System.out.println("Method: " + request.getMethod());
        System.out.println("URL:    " + sanitizeUrl(request.getURI()));

        // RESPONSE META
        ConsoleColors.green(
                "Status: " + response.getStatusCode() + " " + response.getStatusLine(),
                colorEnabled
        );

        System.out.println("Time:   " + elapsedTimeMs + " ms");

        int size = responseBody != null ? responseBody.getBytes().length : 0;
        System.out.println("Size:   " + size + " bytes");

        // BODIES
        logRequestBody(request, colorEnabled);
        logResponseBody(responseBody, colorEnabled);
    }

    public void logShort(
            FilterableRequestSpecification request,
            Response response,
            String responseBody
    ) {

        final int MAX_BODY_LENGTH = 200;
        final java.time.format.DateTimeFormatter FORMATTER =
                java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        System.out.println("\nTIMESTAMP: " +
                java.time.LocalDateTime.now().format(FORMATTER));

        System.out.println("METHOD: " + request.getMethod());
        System.out.println("URL: " + sanitizeUrl(request.getURI()));
        System.out.println("STATUS CODE: " + response.getStatusCode());

        String body = responseBody;

        if (body != null && body.length() > MAX_BODY_LENGTH) {
            body = body.substring(0, MAX_BODY_LENGTH) + "...";
        }

        System.out.println("RESPONSE BODY:\n" +
                (body != null ? body : "[EMPTY BODY]") +
                "\n");
    }

    // ==========================================================================================================
    // PRIVATE
    // ==========================================================================================================

    private void logOptional(
            FilterableRequestSpecification request,
            boolean colorEnabled
    ) {

        ConsoleColors.purple("\n-----------------------------", colorEnabled);
        ConsoleColors.purple("OPTIONAL REQUEST DATA – IS ON", colorEnabled);
        ConsoleColors.purple("-----------------------------", colorEnabled);

        UtilsSensitiveDataMasker.MaskedRequest masked = maskAll(request);

        printPretty("Query params", masked.queryParams, colorEnabled);
        printPretty("Headers", masked.headers, colorEnabled);
        printPretty("Cookies", request.getCookies(), colorEnabled);
    }

    private void logRequestBody(
            FilterableRequestSpecification request,
            boolean colorEnabled
    ) {

        Object body = request.getBody();
        if (body == null) return;

        System.out.println("\n------------");
        ConsoleColors.cyan("REQUEST BODY", colorEnabled);
        System.out.println("------------\n");

        JsonColorPrinter.print(body.toString(), colorEnabled);
    }

    private void logResponseBody(
            String responseBody,
            boolean colorEnabled
    ) {

        System.out.println("\n-------------");
        System.out.println("RESPONSE BODY");
        System.out.println("-------------\n");

        if (responseBody != null) {
            JsonColorPrinter.print(responseBody, colorEnabled);
        } else {
            System.out.println("[EMPTY BODY]");
        }
    }

    private void printPretty(
            String title,
            Object data,
            boolean colorEnabled
    ) {

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
