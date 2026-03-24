package enums.configuration;

public enum LogsMode {

    // ==========================================================================================================
    // ENUMS
    // ==========================================================================================================

    OFF,
    FULL,
    SHORT,
    CUSTOM;

    // ==========================================================================================================
    // VALIDATOR
    // ==========================================================================================================

    public static LogsMode from(String value) {
        try {
            return LogsMode.valueOf(value.toUpperCase());
        } catch (Exception e) {
            throw new IllegalStateException(
                    "(CONFIG) Invalid LOGS_MODE value: " + value + ". Allowed: OFF, FULL, SHORT, CUSTOM"
            );
        }
    }
}
