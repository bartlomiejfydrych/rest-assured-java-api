package loggers;

import io.qameta.allure.Allure;
import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

import java.net.URI;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static providers.ProviderObjectMapper.getObjectMapper;

public class AllureRestAssuredEnhanced implements Filter {

    // ==========================================================================================================
    // METHODS – MAIN
    // ==========================================================================================================

    @Override
    public Response filter(
            FilterableRequestSpecification requestSpec,
            FilterableResponseSpecification responseSpec,
            FilterContext ctx) {

        Response response = ctx.next(requestSpec, responseSpec);

        String url = requestSpec.getURI();
        String method = requestSpec.getMethod();
        int statusCode = response.getStatusCode();

        boolean isError = statusCode >= 400;

        String title = buildTitle(method, statusCode, isError, url);

        String content = buildContent(requestSpec, response);

        Allure.addAttachment(
                title,
                "application/json",
                content,
                ".json"
        );

        return response;
    }

    // ==========================================================================================================
    // METHODS – SUB
    // ==========================================================================================================

    // -----
    // TITLE
    // -----

    private String buildTitle(String method, int statusCode, boolean isError, String url) {

        String endpoint = extractEndpoint(url);

        if (isError) {
            return "❌ Response – " + statusCode + " | " + method + " | " + endpoint;
        }

        return "✅ Response – " + statusCode + " | " + method + " | " + endpoint;
    }

    // ----------------
    // EXTRACT ENDPOINT
    // ----------------

    private String extractEndpoint(String url) {

        try {
            URI uri = new URI(url);

            String path = uri.getPath();

            if (path == null || path.isBlank()) {
                return "/";
            }

            // Opcjonalnie: usuń pierwszy segment wersji API (/1)
            String[] parts = path.split("/");

            if (parts.length > 2) {
                return ".../" + parts[2] + "/...";
            }

            return path;

        } catch (Exception e) {
            return "[unknown-endpoint]";
        }
    }

    // -------
    // CONTENT
    // -------

    private String buildContent(
            FilterableRequestSpecification requestSpec,
            Response response) {

        StringBuilder sb = new StringBuilder();

        String method = requestSpec.getMethod();
        String url = requestSpec.getURI();
        long timeMs = response.getTimeIn(TimeUnit.MILLISECONDS);
        int responseSize = 0;

        if (response.getBody() != null) {
            responseSize = response.getBody().asByteArray().length;
        }

        sb.append("==========================================================================================================\n");
        sb.append("HTTP CALL\n");
        sb.append("==========================================================================================================\n\n");

        sb.append("STATUS: ").append(response.getStatusLine()).append("\n");
        sb.append(method).append(" – ").append(url).append("\n");
        sb.append("TIME: ").append(timeMs).append(" ms\n");
        sb.append("SIZE: ").append(formatSize(responseSize)).append("\n\n");

        sb.append("==========================================================================================================\n");
        sb.append("REQUEST DATA\n");
        sb.append("==========================================================================================================\n\n");

        sb.append("---------------\n");
        sb.append("Request headers\n");
        sb.append("---------------\n\n");
        sb.append(formatHeaders(maskHeaders(requestSpec))).append("\n\n");

        sb.append("-------------------\n");
        sb.append("Request path params\n");
        sb.append("-------------------\n\n");
        sb.append(formatPathParams(requestSpec)).append("\n\n");

        sb.append("------------------------\n");
        sb.append("Request query parameters\n");
        sb.append("------------------------\n\n");
        sb.append(formatQueryParams(requestSpec)).append("\n\n");

        sb.append("-------------------\n");
        sb.append("Request form params\n");
        sb.append("-------------------\n\n");
        sb.append(formatFormParams(requestSpec)).append("\n\n");

        sb.append("------------\n");
        sb.append("Request body\n");
        sb.append("------------\n\n");
        sb.append(formatBody(requestSpec.getBody() != null
                ? requestSpec.getBody().toString()
                : null)).append("\n\n");

        sb.append("==========================================================================================================\n");
        sb.append("RESPONSE DATA\n");
        sb.append("==========================================================================================================\n\n");

        sb.append("-------------\n");
        sb.append("Response body\n");
        sb.append("-------------\n\n");
        sb.append(formatBody(response.getBody() != null
                ? response.getBody().asString()
                : null)).append("\n\n");

        sb.append("----------------\n");
        sb.append("Response headers\n");
        sb.append("----------------\n\n");
        sb.append(formatHeaders(response));

        return sb.toString();
    }

    // -----------
    // FORMAT SIZE
    // -----------

    private String formatSize(int bytes) {
        if (bytes < 1024) return bytes + " B";
        return String.format("%.2f KB", bytes / 1024.0);
    }

    // -----------------------
    // FORMAT BODY (JSON SAFE)
    // -----------------------

    private String formatBody(String body) {

        if (body == null || body.isBlank()) {
            return "[EMPTY BODY]";
        }

        try {
            Object json = getObjectMapper().readValue(body, Object.class);

            return getObjectMapper()
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(json);

        } catch (Exception e) {
            // fallback jeśli JSON jest zepsuty
            return body;
        }
    }

    // --------------
    // FORMAT HEADERS
    // --------------

    private String formatHeaders(FilterableRequestSpecification requestSpec) {
        try {
            Map<String, String> map = new LinkedHashMap<>();
            for (Header h : requestSpec.getHeaders()) {
                map.put(h.getName(), h.getValue());
            }

            return getObjectMapper()
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(map);

        } catch (Exception e) {
            return "[FAILED TO FORMAT HEADERS]";
        }
    }

    private String formatHeaders(Response response) {
        try {
            Map<String, String> map = new LinkedHashMap<>();
            for (Header h : response.getHeaders()) {
                map.put(h.getName(), h.getValue());
            }

            return getObjectMapper()
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(map);

        } catch (Exception e) {
            return "[FAILED TO FORMAT HEADERS]";
        }
    }

    // ----------------------
    // FORMAT PATH PARAMETERS
    // ----------------------

    private String formatPathParams(FilterableRequestSpecification requestSpec) {

        try {
            Map<String, ?> pathParams = requestSpec.getPathParams();

            if (pathParams == null || pathParams.isEmpty()) {
                return "[NO PATH PARAMETERS]";
            }

            return getObjectMapper()
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(pathParams);

        } catch (Exception e) {
            return "[FAILED TO FORMAT PATH PARAMETERS]";
        }
    }

    // -----------------------
    // FORMAT QUERY PARAMETERS
    // -----------------------

    private String formatQueryParams(FilterableRequestSpecification requestSpec) {

        try {
            Map<String, ?> queryParams = requestSpec.getQueryParams();

            if (queryParams == null || queryParams.isEmpty()) {
                return "[NO QUERY PARAMETERS]";
            }

            return getObjectMapper()
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(queryParams);

        } catch (Exception e) {
            return "[FAILED TO FORMAT QUERY PARAMETERS]";
        }
    }

    // ----------------------
    // FORMAT FORM PARAMETERS
    // ----------------------

    private String formatFormParams(FilterableRequestSpecification requestSpec) {

        try {
            Map<String, ?> formParams = requestSpec.getFormParams();

            if (formParams == null || formParams.isEmpty()) {
                return "[NO FORM PARAMETERS]";
            }

            return getObjectMapper()
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(formParams);

        } catch (Exception e) {
            return "[FAILED TO FORMAT FORM PARAMETERS]";
        }
    }

    // -------------
    // TOKEN MASKING
    // -------------

    private FilterableRequestSpecification maskHeaders(FilterableRequestSpecification requestSpec) {

        requestSpec.getHeaders().forEach(header -> {
            if (header.getName().equalsIgnoreCase("Authorization")
                    || header.getName().toLowerCase().contains("token")) {

                requestSpec.removeHeader(header.getName());
                requestSpec.header(header.getName(), "*** MASKED ***");
            }
        });

        return requestSpec;
    }
}
