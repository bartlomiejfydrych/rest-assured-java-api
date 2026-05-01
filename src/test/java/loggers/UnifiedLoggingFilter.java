package loggers;

import enums.configuration.LogsMode;
import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import loggers.core.HttpLogger;

public class UnifiedLoggingFilter implements Filter {

    private final HttpLogger logger;

    public UnifiedLoggingFilter(LogsMode mode, boolean logOptional, boolean colorEnabled) {
        this.logger = new HttpLogger(mode, logOptional, colorEnabled);
    }

    @Override
    public Response filter(
            FilterableRequestSpecification request,
            FilterableResponseSpecification responseSpec,
            FilterContext ctx) {

        long start = System.currentTimeMillis();

        Response response = ctx.next(request, responseSpec);

        long time = System.currentTimeMillis() - start;

        String responseBody = null;
        try {
            if (response.getBody() != null) {
                responseBody = response.getBody().asString();
            }
        } catch (Exception ignored) {}

        // 🔥 zawsze loguj (HttpLogger zdecyduje co robić)
        logger.log(request, response, responseBody, time);

        return response;
    }
}
