package configuration;

import io.github.cdimascio.dotenv.Dotenv;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {

    // ==========================================================================================================
    // VARIABLES
    // ==========================================================================================================

    private static final Properties properties = new Properties();
    private static final Dotenv dotenv = Dotenv.configure()
            .directory("./environment")
            .ignoreIfMissing()
            .load();

    private static boolean loaded = false;

    // ==========================================================================================================
    // LOAD CONFIGURATION FILE (config.properties)
    // ==========================================================================================================

    /*
    NOTE FOR ME:
    Mechanizm, który zapewni, że plik z config.properties będzie wczytany tylko raz i później re-używany
    dla wszystkich metod, które pobierają informacje z tego pliku konfiguracyjnego.
    */

    private static void loadProperties() {
        if (loaded) return; // Prevents multiple readings
        loaded = true;

        try (InputStream input = Config.class.getClassLoader()
                .getResourceAsStream("configuration/config.properties")) {

            if (input != null) {
                properties.load(input);
            } else {
                System.err.println("(CONFIG) File {config.properties} not found in resources.");
            }

        } catch (IOException e) {
            throw new RuntimeException("(CONFIG) Unable to load file {config.properties}", e);
        }
    }

    // ==========================================================================================================
    // PROPERTY READER
    // ==========================================================================================================

    // ------
    // STRING
    // ------

    private static String getProperty(String key, String defaultValue) {
        loadProperties();

        // 1. {system} – Get system environment variables
        String envValue = System.getenv(key);
        if (envValue != null) {
            return envValue.trim();
        }

        // 2. {.env} – Get properties from file
        String dotenvValue = dotenv.get(key);
        if (dotenvValue != null) {
            return dotenvValue.trim();
        }

        // 3. {config.properties} – Get properties from file
        String propValue = properties.getProperty(key);
        if (propValue != null) {
            return propValue.trim();
        }

        // 4. {default} – Get default property (if was provided)
        if (defaultValue != null) {
            System.out.println(
                    "[WARNING] (CONFIG) Using default value for missing configuration key '" + key + "': " + defaultValue
            );
            return defaultValue;
        }

        // 5. {missing} – Get error if property is missing
        throw new IllegalStateException(
                "(CONFIG) Missing required configuration key: '" + key +
                        "'. Checked {system environment}, {.env} and {config.properties}."
        );
    }

    // -------
    // BOOLEAN
    // -------

    private static boolean getProperty(String key, boolean defaultValue) {
        String raw = getProperty(key, String.valueOf(defaultValue));
        return Boolean.parseBoolean(raw);
    }

    // -------
    // INTEGER
    // -------

    private static int getProperty(String key, int defaultValue) {
        String raw = getProperty(key, String.valueOf(defaultValue));
        try {
            return Integer.parseInt(raw);
        } catch (NumberFormatException e) {
            throw new IllegalStateException(
                    "(CONFIG) Invalid integer value for key '" + key + "': " + raw
            );
        }
    }

    // ----
    // LONG
    // ----

    private static long getProperty(String key, long defaultValue) {
        String raw = getProperty(key, String.valueOf(defaultValue));
        try {
            return Long.parseLong(raw);
        } catch (NumberFormatException e) {
            throw new IllegalStateException(
                    "(CONFIG) Invalid long value for key '" + key + "': " + raw
            );
        }
    }

    // ------
    // DOUBLE
    // ------

    private static double getProperty(String key, double defaultValue) {
        String raw = getProperty(key, String.valueOf(defaultValue));
        try {
            return Double.parseDouble(raw);
        } catch (NumberFormatException e) {
            throw new IllegalStateException(
                    "(CONFIG) Invalid double value for key '" + key + "': " + raw
            );
        }
    }

    // ==========================================================================================================
    // PUBLIC CONFIG GETTERS
    // ==========================================================================================================

    // -----------------
    // config.properties
    // -----------------

    // BASE URL

    // Get API base {URL}
    public static String getBaseUrl() {
        return getProperty("baseUrl", "https://api.trello.com/1");
    }

    // Get API base URL {Protocol}
    public static String getBaseUrlProtocol() {
        return getProperty("baseUrlProtocol", "https");
    }

    // Get API base URL {Subdomain}
    public static String getBaseUrlSubdomain() {
        return getProperty("baseUrlSubdomain", "api");
    }

    // Get API base URL {Domain}
    public static String getBaseUrlDomain() {
        return getProperty("baseUrlDomain", "trello");
    }

    // Get API base URL {TLD}
    public static String getBaseUrlTLD() {
        return getProperty("baseUrlTLD", "com");
    }

    // Get API base URL {Number}
    public static String getBaseUrlNumber() {
        return getProperty("baseUrlNumber", "1");
    }

    // OTHER VARIABLES

    // Get {Trello ID}
    public static String getTrelloId() {
        return getProperty("trelloId", "67d9d5e34d7b900257deed0e");
    }

    // ----
    // .env
    // ----

    // LOGS MANAGEMENT

    // Get Logs {FULL}
    public static boolean getLogsFull() {
        return getProperty("LOGS_FULL", false);
    }

    // Get Logs {SHORT}
    public static boolean getLogsShort() {
        return getProperty("LOGS_SHORT", true);
    }

    // TRELLO API KEY & TOKEN

    // Get Trello {API key}
    public static String getTrelloApiKey() {
        return getProperty("TRELLO_API_KEY", null);
    }

    // Get Trello {token}
    public static String getTrelloToken() {
        return getProperty("TRELLO_TOKEN", null);
    }
}

/*
########################################################################################################################
MY ADDITIONAL NOTES
########################################################################################################################

------------------
.ignoreIfMissing()
------------------

Metoda **`.ignoreIfMissing()`** w bibliotece **Dotenv** oznacza:

# ✅ **Jeśli plik `.env` nie istnieje → NIE wyrzucaj błędu. Po prostu go pomiń.**

# 🔍 **Co by było bez `.ignoreIfMissing()`?**

Jeśli napiszesz:

```java
Dotenv dotenv = Dotenv.load();
```

a w katalogu nie ma pliku `.env`, to:

❌ Dotenv wyrzuci błąd
❌ aplikacja się nie uruchomi

# ✨ **Co daje `.ignoreIfMissing()`?**

Jeśli napiszesz:

```java
Dotenv dotenv = Dotenv.configure()
        .ignoreIfMissing()
        .load();
```

to:

✔ jeśli jest `.env` → zostanie wczytany
✔ jeśli **nie ma** `.env` → zostanie zignorowany
✔ program nadal działa
✔ wartości będą pobierane z env albo z config.properties

# 📌 **Dlaczego to poleciłem?**

Bo w projektach, gdzie masz *opcjonalny* `.env`:

* w CI/CD nie masz `.env`
* na produkcji nie masz `.env`
* na cudzych komputerach nie masz `.env`
* ale *chcesz*, żeby aplikacja mimo to działała

Dzięki temu masz elastyczność:

> Jeśli `.env` jest — super.
> Jeśli go nie ma — nie ma tragedii.

# ⚡ Podsumowując

✔ `.ignoreIfMissing()` = **opcjonalny plik `.env`**
✔ Zapobiega błędom
✔ Sprawia, że aplikacja jest bardziej przenośna
✔ Ułatwia debugowanie i deployment

*/
