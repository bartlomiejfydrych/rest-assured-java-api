package configuration;

import io.github.cdimascio.dotenv.Dotenv;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.Properties;

public class Config {

    private static final Properties properties = new Properties();
    private static final Dotenv dotenv = Dotenv.load();

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

    // config.properties – Utility method to get property value with optional defaults
    private static String getProperty(String key, String defaultValue) {
        return Optional.ofNullable(properties.getProperty(key)).orElse(defaultValue).trim();
    }

    // config.properties – Utility method to get required property value, returning information if such a property does not exist
    private static String getRequiredConfigProperty(String key) {
        return Optional.ofNullable(properties.getProperty(key))
                .map(String::trim)
                .orElseThrow(() -> new IllegalStateException("Missing required key from 'config.properties' file: " + key));
    }

    // .env – Utility method to get required property value, returning information if such a property does not exist
    private static String getRequiredEnvProperty(String key) {
        return Optional.ofNullable(dotenv.get(key))
                .map(String::trim)
                .orElseThrow(() -> new IllegalStateException("Missing required key from '.env' file: " + key));
    }

    // --------------------------------------------------------
    // config.properties – Methods that retrieve data from file
    // --------------------------------------------------------

    // Get API base URL, mandatory
    public static String getBaseUrl() {
        return getRequiredConfigProperty("baseUrl");
    }

    // -------------------------------------------
    // .env – Methods that retrieve data from file
    // -------------------------------------------

    // Get Trello API key, mandatory
    public static String getTrelloApiKey() {
        return getRequiredEnvProperty("TRELLO_API_KEY");
    }

    // Get Trello token, mandatory
    public static String getTrelloToken() {
        return getRequiredEnvProperty("TRELLO_TOKEN");
    }
}
