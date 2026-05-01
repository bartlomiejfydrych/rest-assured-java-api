package loggers.core;

import enums.configuration.LogsMode;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import loggers.formatter.AllureFormatter;
import loggers.formatter.ConsoleFormatter;
import loggers.writer.AllureWriter;
import loggers.writer.ConsoleWriter;

public class HttpLogger {

    private final LogsMode mode;
    private final boolean logOptional;
    private final boolean colorEnabled;

    private final ConsoleFormatter consoleFormatter = new ConsoleFormatter();
    private final AllureFormatter allureFormatter = new AllureFormatter();

    private final ConsoleWriter consoleWriter = new ConsoleWriter();
    private final AllureWriter allureWriter = new AllureWriter();

    public HttpLogger(LogsMode mode, boolean logOptional, boolean colorEnabled) {
        this.mode = mode;
        this.logOptional = logOptional;
        this.colorEnabled = colorEnabled;
    }

    public void log(
            FilterableRequestSpecification request,
            Response response,
            String responseBody,
            long timeMs
    ) {

        switch (mode) {

            case CUSTOM:
                consoleFormatter.logFull(
                        request,
                        response,
                        responseBody,
                        timeMs,
                        logOptional,
                        colorEnabled
                );
                break;

            case SHORT:
                consoleFormatter.logShort(
                        request,
                        response,
                        responseBody
                );
                break;

            case FULL:
            case OFF:
                // ❌ brak console logów
                break;
        }

        // ✅ ALLURE zawsze działa
        allureWriter.write(
                allureFormatter.format(request, response, responseBody)
        );
    }

    public boolean isFullMode() {
        return mode == LogsMode.FULL;
    }
}
