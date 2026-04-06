package utils.loggers;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.specification.FilterableRequestSpecification;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

public final class UtilsSensitiveDataMasker {

    // ==========================================================================================================
    // FIELDS
    // ==========================================================================================================

    private static final Set<String> SENSITIVE_KEYS = Set.of(
            "key",
            "token",
            "authorization"
    );

    private static final String MASK = "*** MASKED ***";

    // ==========================================================================================================
    // CONSTRUCTOR
    // ==========================================================================================================

    private UtilsSensitiveDataMasker() {
    }

    // ==========================================================================================================
    // METHODS (MAIN)
    // ==========================================================================================================

    // ------------------------------
    // URL (ULTRA SAFE – URI PARSING)
    // ------------------------------

    public static String sanitizeUrl(String url) {
        if (url == null || url.isBlank()) {
            return url;
        }

        try {
            URI uri = new URI(url);

            String query = uri.getQuery();
            if (query == null) {
                return url;
            }

            StringBuilder newQuery = new StringBuilder();

            String[] params = query.split("&");

            for (int i = 0; i < params.length; i++) {
                String[] pair = params[i].split("=", 2);

                String key = decode(pair[0]);
                String value = pair.length > 1 ? pair[1] : "";

                if (isSensitive(key)) {
                    value = MASK;
                }

                newQuery.append(key).append("=").append(value);

                if (i < params.length - 1) {
                    newQuery.append("&");
                }
            }

            return new URI(
                    uri.getScheme(),
                    uri.getAuthority(),
                    uri.getPath(),
                    newQuery.toString(),
                    uri.getFragment()
            ).toString();

        } catch (URISyntaxException e) {
            // fallback → regex (last resort)
            return fallbackSanitize(url);
        }
    }

    private static String fallbackSanitize(String url) {
        String sanitized = url;

        for (String key : SENSITIVE_KEYS) {
            sanitized = sanitized.replaceAll(
                    "(" + key + "=)[^&]+",
                    "$1" + MASK
            );
        }

        return sanitized;
    }

    // -------
    // HEADERS
    // -------

    public static Map<String, String> maskHeaders(Headers headers) {

        Map<String, String> masked = new LinkedHashMap<>();

        if (headers == null || headers.asList().isEmpty()) {
            return masked;
        }

        for (Header header : headers) {
            String name = header.getName();
            String value = header.getValue();

            if (isSensitive(name)) {
                masked.put(name, MASK);
            } else {
                masked.put(name, value);
            }
        }

        return masked;
    }

    // ------------
    // QUERY PARAMS
    // ------------

    public static Map<String, Object> maskQueryParams(Map<String, ?> params) {

        if (params == null || params.isEmpty()) {
            return Map.of();
        }

        Map<String, Object> masked = new LinkedHashMap<>();

        params.forEach((k, v) -> {
            if (isSensitive(k)) {
                masked.put(k, MASK);
            } else {
                masked.put(k, v);
            }
        });

        return masked;
    }

    // ----------
    // ALL-IN-ONE
    // ----------

    public static MaskedRequest maskAll(FilterableRequestSpecification requestSpec) {

        return new MaskedRequest(
                sanitizeUrl(requestSpec.getURI()),
                maskHeaders(requestSpec.getHeaders()),
                maskQueryParams(requestSpec.getQueryParams())
        );
    }

    // ==========================================================================================================
    // METHODS (SUB)
    // ==========================================================================================================

    private static boolean isSensitive(String key) {
        return key != null && SENSITIVE_KEYS.contains(key.toLowerCase());
    }

    private static String decode(String value) {
        return URLDecoder.decode(value, StandardCharsets.UTF_8);
    }

    // ==========================================================================================================
    // DTO
    // ==========================================================================================================

    public static class MaskedRequest {
        public final String url;
        public final Map<String, String> headers;
        public final Map<String, Object> queryParams;

        public MaskedRequest(String url,
                             Map<String, String> headers,
                             Map<String, Object> queryParams) {
            this.url = url;
            this.headers = headers;
            this.queryParams = queryParams;
        }
    }
}
