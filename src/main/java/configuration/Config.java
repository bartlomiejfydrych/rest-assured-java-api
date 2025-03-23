package configuration;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.Properties;

public class Config {

    private static final Properties properties = new Properties();

    // --------------------------------------
    // Method that loads a configuration file
    // --------------------------------------

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

    // Utility method to get property values with optional defaults
    private static String getProperty(String key, String defaultValue) {
        return Optional.ofNullable(properties.getProperty(key)).orElse(defaultValue).trim();
    }

    private static String getRequiredProperty(String key) {
        return Optional.ofNullable(properties.getProperty(key))
                .map(String::trim)
                .orElseThrow(() -> new IllegalStateException("Missing required configuration key: " + key));
    }

    // ------------------------------------------------------
    // Methods that retrieve data from the configuration file
    // ------------------------------------------------------

    // Get API base URL, mandatory
    public static String getBaseUrl() {
        return getRequiredProperty("baseUrl");
    }
}
