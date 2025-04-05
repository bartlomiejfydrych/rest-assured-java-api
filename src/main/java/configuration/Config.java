package configuration;

import io.github.cdimascio.dotenv.Dotenv;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.Properties;

public class Config {

    private static final Properties properties = new Properties();
    private static final Dotenv dotenv = Dotenv.configure().directory("./environment").load();

    // ----------------------------------------------------------
    // Method that loads a configuration file (config.properties)
    // ----------------------------------------------------------

    /*
    NOTE FOR ME:
    Mechanizm, który zapewni, że plik z config.properties będzie wczytany tylko raz i później re-używany
    do wszystkich metod, które pobierają informacje z tego pliku konfiguracyjnego.
    */

    // Static initializer to load the configuration file
    static {
        try (InputStream inputStream = Config.class.getClassLoader().getResourceAsStream("configs/config.properties")) {
            if (inputStream == null) {
                throw new IllegalStateException("Configuration file 'config.properties' not found");
            }
            properties.load(inputStream);
        } catch (IOException e) {
            throw new IllegalStateException("Error loading configuration file", e);
        }
    }

    // -----
    // Utils
    // -----

    // STRING

    // config.properties – Utility method to get string property value with optional defaults
    private static String getConfigProperty(String key, String defaultValue) {
        return Optional.ofNullable(properties.getProperty(key))
                .map(String::trim)
                .orElse(defaultValue != null ? defaultValue : "ERROR: Missing required key from 'config.properties' file: " + key);
    }

    // .env – Utility method to get string property value with optional defaults
    private static String getEnvProperty(String key, String defaultValue) {
        return Optional.ofNullable(dotenv.get(key))
                .map(String::trim)
                .orElse(defaultValue != null ? defaultValue : "ERROR: Missing required key from '.env' file: " + key);
    }

    // BOOLEAN

    // config.properties – Utility method to get boolean property value with optional defaults
    private static boolean getConfigPropertyBoolean(String key, Boolean defaultValue) {
        return Optional.ofNullable(properties.getProperty(key))
                .map(String::trim)
                .map(Boolean::parseBoolean)
                .orElseGet(() -> {
                    if (defaultValue != null) {
                        return defaultValue;
                    } else {
                        throw new IllegalStateException("ERROR: Missing required key from 'config.properties' file: " + key);
                    }
                });
    }

    // .env – Utility method to get boolean property value with optional defaults
    private static boolean getEnvPropertyBoolean(String key, Boolean defaultValue) {
        return Optional.ofNullable(dotenv.get(key))
                .map(String::trim)
                .map(Boolean::parseBoolean)
                .orElseGet(() -> {
                    if (defaultValue != null) {
                        return defaultValue;
                    } else {
                        throw new IllegalStateException("ERROR: Missing required key from '.env' file: " + key);
                    }
                });
    }

    // --------------------------------------------------------
    // config.properties – Methods that retrieve data from file
    // --------------------------------------------------------

    // BASE URL

    // Get API base URL
    public static String getBaseUrl() {
        return getConfigProperty("baseUrl", "https://api.trello.com/1");
    }

    // Get API base URL Protocol
    public static String getBaseUrlProtocol() {
        return getConfigProperty("baseUrlProtocol", "https");
    }

    // Get API base URL Subdomain
    public static String getBaseUrlSubdomain() {
        return getConfigProperty("baseUrlSubdomain", "api");
    }

    // Get API base URL Domain
    public static String getBaseUrlDomain() {
        return getConfigProperty("baseUrlDomain", "trello");
    }

    // Get API base URL TLD
    public static String getBaseUrlTLD() {
        return getConfigProperty("baseUrlTLD", "com");
    }

    // Get API base URL Number
    public static String getBaseUrlNumber() {
        return getConfigProperty("baseUrlNumber", "1");
    }

    // -------------------------------------------
    // .env – Methods that retrieve data from file
    // -------------------------------------------

    // LOGS MANAGEMENT

    // Get Logs when Fail
    public static boolean getLogsWhenFail() {
        return getEnvPropertyBoolean("LOGS_WHEN_FAIL", true);
    }

    // Get Logs when Fail
    public static boolean getLogsAlways() {
        return getEnvPropertyBoolean("LOGS_ALWAYS", false);
    }

    // TRELLO API KEY & TOKEN

    // Get Trello API key
    public static String getTrelloApiKey() {
        return getEnvProperty("TRELLO_API_KEY", null);
    }

    // Get Trello token
    public static String getTrelloToken() {
        return getEnvProperty("TRELLO_TOKEN", null);
    }
}
