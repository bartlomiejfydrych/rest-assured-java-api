package loggers.formatter;

import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;

import java.net.URI;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static providers.ProviderObjectMapper.getObjectMapper;
import static utils.loggers.UtilsSensitiveDataMasker.maskAll;
import static utils.loggers.UtilsSensitiveDataMasker.sanitizeUrl;

public class AllureFormatter {

    public AllureAttachment format(
            FilterableRequestSpecification requestSpec,
            Response response,
            String responseBody
    ) {

        String url = sanitizeUrl(requestSpec.getURI());
        String method = requestSpec.getMethod();
        int statusCode = response.getStatusCode();

        boolean isError = statusCode >= 400;

        String title = buildTitle(method, statusCode, isError, url);
        String content = buildContent(requestSpec, response, responseBody);

        return new AllureAttachment(title, content);
    }

    // ==========================================================================================================
    // TITLE
    // ==========================================================================================================

    private String buildTitle(String method, int statusCode, boolean isError, String url) {

        String endpoint = extractEndpoint(url);

        if (isError) {
            return "❌ Response – " + statusCode + " | " + method + " | " + endpoint;
        }

        return "✅ Response – " + statusCode + " | " + method + " | " + endpoint;
    }

    private String extractEndpoint(String url) {

        try {
            URI uri = new URI(url);

            String path = uri.getPath();

            if (path == null || path.isBlank()) {
                return "/";
            }

            String[] parts = path.split("/");

            if (parts.length > 2) {
                return ".../" + parts[2] + "/...";
            }

            return path;

        } catch (Exception e) {
            return "[unknown-endpoint]";
        }
    }

    // ==========================================================================================================
    // CONTENT
    // ==========================================================================================================

    private String buildContent(
            FilterableRequestSpecification requestSpec,
            Response response,
            String responseBody
    ) {

        StringBuilder sb = new StringBuilder();

        var masked = maskAll(requestSpec);

        String method = requestSpec.getMethod();
        String url = masked.url;

        long timeMs = response.getTimeIn(TimeUnit.MILLISECONDS);

        int responseSize = responseBody != null
                ? responseBody.getBytes().length
                : 0;

        Map<String, String> maskedHeaders = masked.headers;
        Map<String, Object> maskedQueryParams = masked.queryParams;
        Map<String, String> responseHeaders = headersToMap(response.getHeaders());

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
        sb.append(formatHeaders(maskedHeaders)).append("\n\n");

        sb.append("-------------------\n");
        sb.append("Request path params\n");
        sb.append("-------------------\n\n");
        sb.append(formatPathParams(requestSpec)).append("\n\n");

        sb.append("------------------------\n");
        sb.append("Request query parameters\n");
        sb.append("------------------------\n\n");
        sb.append(formatQueryParams(maskedQueryParams)).append("\n\n");

        sb.append("-------------------\n");
        sb.append("Request form params\n");
        sb.append("-------------------\n\n");
        sb.append(formatFormParams(requestSpec)).append("\n\n");

        sb.append("------------\n");
        sb.append("Request body\n");
        sb.append("------------\n\n");
        sb.append(formatBody(
                requestSpec.getBody() != null ? requestSpec.getBody().toString() : null
        )).append("\n\n");

        sb.append("==========================================================================================================\n");
        sb.append("RESPONSE DATA\n");
        sb.append("==========================================================================================================\n\n");

        sb.append("-------------\n");
        sb.append("Response body\n");
        sb.append("-------------\n\n");
        sb.append(formatBody(
                response.getBody() != null ? responseBody : null
        )).append("\n\n");

        sb.append("----------------\n");
        sb.append("Response headers\n");
        sb.append("----------------\n\n");
        sb.append(formatHeaders(responseHeaders));

        return sb.toString();
    }

    // ==========================================================================================================
    // HELPERS
    // ==========================================================================================================

    private String formatSize(int bytes) {
        if (bytes < 1024) return bytes + " B";
        return String.format("%.2f KB", bytes / 1024.0);
    }

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
            return body;
        }
    }

    private Map<String, String> headersToMap(Iterable<Header> headers) {

        Map<String, String> map = new LinkedHashMap<>();

        if (headers == null) {
            return map;
        }

        for (Header h : headers) {
            map.put(h.getName(), h.getValue());
        }

        return map;
    }

    private String formatHeaders(Map<String, String> headers) {

        try {
            if (headers == null || headers.isEmpty()) {
                return "[NO HEADERS]";
            }

            return getObjectMapper()
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(headers);

        } catch (Exception e) {
            return "[FAILED TO FORMAT HEADERS]";
        }
    }

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

    private String formatQueryParams(Map<String, ?> queryParams) {

        try {
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
}
