# 🌐REST Assured i testy – notatki

# 📑Spis treści

- [START – rozpoczęcie pisania testów](#start_writing_tests)
- [RequestSpecification](#request_specification)
- [RestAssured.filters()](#rest_assured_filters)
- [RequestSpecBuilder](#request_spec_builder)
- [RestAssured.enableLoggingOfRequestAndResponseIfValidationFails()](#enable_log_fail)
- [RecursiveComparisonConfiguration()](#assertj_recursive_comparison_configuration)

---

# 📝Opis

## 📄START – rozpoczęcie pisania testów <a name="start_writing_tests"></a>

1. Zakładamy `konta` i inne `dostępy`
    - W przypadku tego projektu zakładamy `konto` oraz zdobywamy `API key` oraz `token` na stronie **Trello**
    - Szczegóły w `README` katalogu `📂trello-configuration`
2. W katalogu `src/main/resources` tworzymy katalog `configs`, a w nim plik o nazwie `config.properties`  
   Wszelkie ustawienia projektu warto trzymać i odczytywać z osobnego pliku, aby nie musieć nic zmieniać w samym kodzie.  
   Zapisujemy w nim takie rzeczy jak:
   ```properties
   # File config.properties - project variables
   
   # BASE URL
   baseUrl=https://api.trello.com/1
   baseUrlProtocol=https
   baseUrlSubdomain=api
   baseUrlDomain=trello
   baseUrlTLD=com
   baseUrlNumber=1
   ```
3. Sprawdzamy, czy mamy w `pom.xml` dodane dependecy o nazwie `Dotenv Java`
4. Otwieramy plik `.gitignore` i dopisujemy w nim:  
   ```ignore
   ### MY FILES

   # environment
   environment/.env
   ```
5. W głównym katalogu projektu tworzymy katalog o nazwie `environment`
6. W nim tworzymy dwa pliki:  
   - `.env` (tutaj będziemy przechowywać nasze prawdziwe zmienne)
   - `.env.example` (tutaj będzie pusty wzór dostępny na repozytorium)
7. W plikach `.env` definiujemy:  
   - Sekcję na włączanie logów przy failach testów oraz pokazywania logów zawsze
   - Sekcję na `API key` oraz `token`
   ```properties
   # File .env – environment variables
    
   # LOGS MANAGEMENT
   LOGS_WHEN_FAIL=true/false
   LOGS_ALWAYS=true/false
    
   # TRELLO API KEY & TOKEN
   TRELLO_API_KEY=yourTrelloApiKey
   TRELLO_TOKEN=yourTrelloToken
   ```
8. W katalogu `src/main/java` tworzymy katalog o nazwie `configuration`
9. W katalogu `configuration` tworzymy plik java class o nazwie `Config.java`
10. W pliku `Config.java` dopisujemy następujące rzeczy:  
    ```java
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
    ```
11. W katalogu `src/main/java/configuration` tworzymy plik `BaseUrlBuilder`
12. W pliku `BaseUrlBuilder` piszemy budowanie naszego URL ze zmiennych konfiguracyjnych projektu:  
   ```java
   package configuration;
   
   public class BaseUrlBuilder {
   
       public static String buildBaseUrl() {
           return String.format("%s://%s.%s.%s/%s",
                   Config.getBaseUrlProtocol(),
                   Config.getBaseUrlSubdomain(),
                   Config.getBaseUrlDomain(),
                   Config.getBaseUrlTLD(),
                   Config.getBaseUrlNumber()
           );
       }
   }
   ```
13. W katalogu `src/test/java` tworzymy katalog package o nazwie `configuration`
14. W katalogu `src/test/java/configuration` tworzymy plik `RequestSpecConfig`  
    Dlaczego tutaj, a nie w `main`?  
    Ponieważ `REST Assured` jest używane tylko do testów i jego specyfikacja tak zaleca.  
    Żeby obejść to ograniczenie można też w `pom.xml` usunąć wiersz z `<scope>test</scope>`.
15. W pliku `RequestSpecConfig` piszemy naszą wspólną konfigurację dla wszystkich requestów:
    ```java
    package configuration;
    
    import io.restassured.builder.RequestSpecBuilder;
    import io.restassured.http.ContentType;
    import io.restassured.specification.RequestSpecification;
    
    public class RequestSpecConfig {
    
        private static final RequestSpecification requestSpecification = new RequestSpecBuilder()
                .addQueryParam("key", Config.getTrelloApiKey())
                .addQueryParam("token", Config.getTrelloToken())
                .setBaseUri(BaseUrlBuilder.buildBaseUrl())
                .setContentType(ContentType.JSON)
                .build();
    
        public static RequestSpecification getRequestSpecification() {
            return requestSpecification;
        }
    }
    ```
16. W katalogu `src/test/java` tworzymy katalog o nazwie `base`
17. W katalogu `src/test/java/base` tworzymy plik o nazwie `TestBase.java`
18. W pliku `TestBase.java` tworzymy wstępną konfigurację:  
    ```java
    package base;
    
    import configuration.Config;
    import configuration.RequestSpecConfig;
    import io.restassured.RestAssured;
    import io.restassured.filter.log.RequestLoggingFilter;
    import io.restassured.filter.log.ResponseLoggingFilter;
    import io.restassured.specification.RequestSpecification;
    import org.junit.jupiter.api.BeforeAll;
    
    public class TestBase {
        // Object containing all request settings
        protected static RequestSpecification requestSpecificationCommon;
    
        @BeforeAll
        public static void setUpAll() {
    
            // LOGS
    
            // Always print in console all request and response data
            if (Config.getLogsAlways()) {
                RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
            }
            // Only when test fail print in console all request and response data
            if (Config.getLogsWhenFail()) {
                RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
            }
    
            // OTHERS
    
            // Class that allows you to configure API requests in a readable and reusable way
            requestSpecificationCommon = RequestSpecConfig.getRequestSpecification();
        }
    }
    ```

---

## 📄RequestSpecification <a name="request_specification"></a>

### **`RequestSpecification` w REST Assured – co to jest?**

🔹 **`RequestSpecification`** to **interfejs** w bibliotece **REST Assured**, który pozwala na **konfigurowanie
i wielokrotne używanie ustawień dla żądań HTTP**. Dzięki niemu można **uniknąć powtarzania kodu** w testach API.

### **Jak działa `RequestSpecification`?**
Zamiast każdorazowego ustawiania nagłówków, parametrów i innych właściwości requestu w każdym teście, można to zrobić
**raz**, a potem używać w wielu miejscach.

#### **Przykład bez `RequestSpecification` (duplikacja kodu)**
```java
given()
    .baseUri("https://api.example.com")
    .header("Authorization", "Bearer token123")
    .contentType(ContentType.JSON)
.when()
    .get("/users")
.then()
    .statusCode(200);
```
Jeśli masz wiele testów, musisz za każdym razem powtarzać te same ustawienia.

#### **Przykład z `RequestSpecification` (lepsza organizacja kodu)**
```java
RequestSpecification requestSpec = new RequestSpecBuilder()
    .setBaseUri("https://api.example.com")
    .addHeader("Authorization", "Bearer token123")
    .setContentType(ContentType.JSON)
    .build();

given()
    .spec(requestSpec)
.when()
    .get("/users")
.then()
    .statusCode(200);
```
Tutaj konfiguracja requestu jest **zapisywana w `requestSpec`**, co sprawia, że testy są **czystsze i bardziej modułowe**.

### **Co można skonfigurować w `RequestSpecification`?**
✅ **Nagłówki (`Headers`)** – np. `Authorization`, `Content-Type`  
✅ **Parametry zapytań (`Query Parameters`)** – np. `?key=value`  
✅ **Ciało żądania (`Body`)** – np. JSON dla metod `POST` czy `PUT`  
✅ **Bazowy URL (`Base URI`)** – np. `https://api.example.com`  
✅ **Ścieżki (`Base Path`)** – np. `/v1/users`  
✅ **Pliki (`MultiPart Requests`)** – np. `uploading files`

### **Jak tworzyć `RequestSpecification`?**
Do tworzenia specyfikacji używa się **klasy pomocniczej `RequestSpecBuilder`**, a na końcu wywołuje `.build()`, aby
utworzyć finalny obiekt `RequestSpecification`.

#### **Przykład tworzenia `RequestSpecification`**
```java
RequestSpecification requestSpec = new RequestSpecBuilder()
    .setBaseUri("https://api.example.com")
    .setBasePath("/v1")
    .addQueryParam("lang", "en")
    .addHeader("Authorization", "Bearer token123")
    .setContentType(ContentType.JSON)
    .build();
```

### **Podsumowanie**
📌 **`RequestSpecification`** pomaga **uniknąć powtarzania konfiguracji requestów** i sprawia, że testy REST API są
**bardziej czytelne, modułowe i łatwiejsze w utrzymaniu**.  
📌 **Można go używać z `RequestSpecBuilder`**, aby w łatwy sposób definiować specyfikację żądań.  
📌 **Dzięki niemu testy są bardziej przejrzyste, krótsze i łatwiejsze w zarządzaniu.** 🚀

---

## 📄RestAssured.filters() <a name="rest_assured_filters"></a>

### **`RestAssured.filters()` – Co to jest i jak działa?**

#### 🔹 **Co to jest?**
Metoda `RestAssured.filters()` w bibliotece **REST Assured** umożliwia dodawanie filtrów do wszystkich żądań HTTP, które
są wykonywane w ramach testów API.

Filtry pozwalają na **przechwytywanie, modyfikowanie i analizowanie** zarówno **żądań (`Request`)**, jak
i **odpowiedzi (`Response`)**.

### **🔹 Możliwości `RestAssured.filters()`**
Metoda ta przyjmuje **dowolną liczbę obiektów implementujących interfejs `Filter`**, co oznacza, że możemy dodać kilka
filtrów jednocześnie.

#### **1️⃣ Logowanie requestów i response'ów (`RequestLoggingFilter` & `ResponseLoggingFilter`)**
##### **Opis**
Loguje szczegóły **wysyłanych zapytań HTTP** i **otrzymanych odpowiedzi**.

##### **Przykład użycia**
```java
RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
```
##### **Co się stanie?**
Po uruchomieniu testu w konsoli zobaczysz logi requestu i odpowiedzi:
```
Request method: GET
Request URI: https://api.example.com/users
Request headers: Accept=*/*
Response status: 200
Response body: { "id": 1, "name": "John Doe" }
```

#### **2️⃣ Filtrujące requesty (`RequestFilter`)**
##### **Opis**
Pozwala **modyfikować żądanie** przed jego wysłaniem, np. dynamicznie zmieniać nagłówki.

##### **Przykład użycia**
```java
RestAssured.filters((requestSpec, responseSpec, ctx) -> {
    requestSpec.header("X-Custom-Header", "CustomValue");
    return ctx.next(requestSpec, responseSpec);
});
```
##### **Co się stanie?**
Każde żądanie API otrzyma dodatkowy nagłówek:
```
X-Custom-Header: CustomValue
```

#### **3️⃣ Filtrujące odpowiedzi (`ResponseFilter`)**
##### **Opis**
Pozwala **modyfikować lub analizować odpowiedź**, np. logować błędy.

##### **Przykład użycia**
```java
RestAssured.filters((requestSpec, responseSpec, ctx) -> {
    var response = ctx.next(requestSpec, responseSpec);
    
    if (response.statusCode() >= 400) {
        System.out.println("Error Response: " + response.getBody().asString());
    }
    
    return response;
});
```
##### **Co się stanie?**
Jeśli serwer zwróci status **400+**, zobaczysz w konsoli szczegóły błędu.

#### **4️⃣ Kombinacja wielu filtrów jednocześnie**
Można dodać kilka filtrów naraz:
```java
RestAssured.filters(
    new RequestLoggingFilter(),
    new ResponseLoggingFilter(),
    (requestSpec, responseSpec, ctx) -> {
        System.out.println("Before request: " + requestSpec.getURI());
        return ctx.next(requestSpec, responseSpec);
    }
);
```

### **🔹 Podsumowanie**
📌 `RestAssured.filters()` umożliwia przechwytywanie i modyfikowanie requestów i response'ów.  
📌 Można używać gotowych filtrów (`RequestLoggingFilter`, `ResponseLoggingFilter`) lub tworzyć własne
(`RequestFilter`, `ResponseFilter`).  
📌 Przydatne do **logowania, analizy błędów i dynamicznej modyfikacji zapytań**. 🚀

---

## 📄RequestSpecBuilder <a name="request_spec_builder"></a>

### **🔹 Co to jest `RequestSpecBuilder`?**
`RequestSpecBuilder` to klasa w bibliotece **REST Assured**, która służy do **konfigurowania requestów HTTP** w sposób
modularny i wielokrotnego użytku.

Zamiast każdorazowego powtarzania konfiguracji dla każdego testu API, można stworzyć **jedną specyfikację
(`RequestSpecification`)**, a następnie wielokrotnie ją używać.

### **🔹 Główne zalety `RequestSpecBuilder`**
✅ **Unikanie powtarzania kodu** – cała konfiguracja requestów jest w jednym miejscu.  
✅ **Łatwiejsze zarządzanie testami** – gdy zmieniają się parametry requestów, edytujesz tylko specyfikację.  
✅ **Modularność** – można tworzyć różne specyfikacje dla różnych części API.

### **🔹 Jak działa `RequestSpecBuilder`?**
Najpierw tworzymy specyfikację requestu za pomocą `RequestSpecBuilder`, konfigurujemy ją, a następnie wywołujemy
`.build()`, aby uzyskać finalny obiekt `RequestSpecification`.

```java
RequestSpecification requestSpec = new RequestSpecBuilder()
    .setBaseUri("https://api.example.com")
    .setBasePath("/users")
    .addQueryParam("lang", "en")
    .addHeader("Authorization", "Bearer token123")
    .setContentType(ContentType.JSON)
    .build();
```
Teraz możemy używać `requestSpec` w wielu testach:
```java
given()
    .spec(requestSpec)
.when()
    .get()
.then()
    .statusCode(200);
```

### **🔹 Pełna lista metod `RequestSpecBuilder`**
#### **1️⃣ Ustawienie bazowego URL (`setBaseUri`)**
```java
.setBaseUri("https://api.example.com")
```
🔹 Definiuje **główny adres API**, do którego będą kierowane wszystkie requesty.

#### **2️⃣ Ustawienie ścieżki bazowej (`setBasePath`)**
```java
.setBasePath("/v1/users")
```
🔹 Dodaje **domyślną ścieżkę** do bazowego URL.  
🔹 Przykładowy efekt: `https://api.example.com/v1/users`

#### **3️⃣ Dodawanie parametrów query (`addQueryParam`)**
```java
.addQueryParam("lang", "en")
.addQueryParam("sort", "asc")
```
🔹 Dodaje **parametry do zapytania** (`?lang=en&sort=asc`).  
🔹 Można je także dynamicznie zmieniać w testach.

#### **4️⃣ Dodawanie parametrów ścieżki (`addPathParam`)**
```java
.addPathParam("userId", "123")
```
🔹 Używane w przypadku **dynamicznych URL-i**, np.:
```java
given()
    .spec(requestSpec)
.when()
    .get("/{userId}")
```
🔹 Wygeneruje URL: `https://api.example.com/123`

#### **5️⃣ Dodawanie nagłówków (`addHeader`)**
```java
.addHeader("Authorization", "Bearer token123")
.addHeader("Accept", "application/json")
```
🔹 Ustawia **nagłówki HTTP**, np. tokeny autoryzacyjne.

#### **6️⃣ Ustawienie typu treści (`setContentType`)**
```java
.setContentType(ContentType.JSON)
```
🔹 Definiuje format danych requestu (`JSON`, `XML`, `TEXT`).

#### **7️⃣ Ustawienie ciała requestu (`setBody`)**
```java
.setBody("{ \"name\": \"John Doe\" }")
```
🔹 Wysyła dane w **metodach `POST`, `PUT`, `PATCH`**.

#### **8️⃣ Ustawienie czasu oczekiwania (`setConfig`)**
```java
.setConfig(RestAssured.config().httpClient(HttpClientConfig.httpClientConfig()
    .setParam(CoreConnectionPNames.CONNECTION_TIMEOUT, 5000)
    .setParam(CoreConnectionPNames.SO_TIMEOUT, 5000)))
```
🔹 Ustawia **timeouty** i inne opcje HTTP.

#### **9️⃣ Budowanie specyfikacji (`build`)**
```java
.build();
```
🔹 **Tworzy finalny obiekt `RequestSpecification`**, gotowy do użycia w testach.

### **🔹 Podsumowanie**
📌 **`RequestSpecBuilder`** pozwala konfigurować **bazowy URL, nagłówki, parametry, body i inne ustawienia requestów**.  
📌 **Zwiększa czytelność kodu**, eliminując powtarzające się konfiguracje w testach API.  
📌 **Pozwala na wielokrotne użycie tej samej specyfikacji**, co sprawia, że testy są **modularne i łatwiejsze w utrzymaniu**. 🚀

---

## 📄RestAssured.enableLoggingOfRequestAndResponseIfValidationFails() <a name="enable_log_fail"></a>

📌 **Co robi ta metoda?**  
Metoda **`RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();`** włącza automatyczne logowanie żądań
(**request**) i odpowiedzi (**response**) **tylko w przypadku, gdy asercja testowa zakończy się niepowodzeniem**.

### **🔹 Jak to działa?**
1. Jeśli test przejdzie pomyślnie → **nie loguje requestu i response'u**.
2. Jeśli test zakończy się błędem (np. zwróci inny status HTTP lub inne dane) → **wtedy loguje request i response**.

### **🔹 Przykład użycia**
```java
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class TestExample {

    public static void main(String[] args) {

        // Włączenie logowania TYLKO gdy test nie przejdzie
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        given()
            .baseUri("https://jsonplaceholder.typicode.com")
            .basePath("/posts/1")
        .when()
            .get()
        .then()
            .statusCode(200)  // Jeśli zwróci np. 404, to zostanie zalogowany cały request/response
            .body("title", equalTo("Niepoprawny tytuł"));  // Celowy błąd -> logowanie
    }
}
```
💡 Jeśli test nie przejdzie, w konsoli zobaczysz np.:
```
Request method: GET
Request URI: https://jsonplaceholder.typicode.com/posts/1
Response status code: 200
Response body: { "userId": 1, "id": 1, "title": "słuszny tytuł", ... }
java.lang.AssertionError: JSON path title doesn't match. Expected: Niepoprawny tytuł, but was: słuszny tytuł
```
Natomiast jeśli test przejdzie **bez błędu**, nie zobaczysz żadnego loga.

### **🔹 Zalety tej metody**
✅ **Redukuje ilość logów** – logi pojawiają się tylko, gdy są potrzebne.  
✅ **Pomaga w debugowaniu** – od razu widzisz szczegóły błędnego requestu.  
✅ **Łatwa implementacja** – wystarczy **jedna linia kodu**.

### **🔹 Kiedy stosować?**
✔ W **większości testów API** jako domyślne ustawienie.  
✔ Gdy **nie chcesz zaśmiecać logów** niepotrzebnymi requestami.  
✔ Gdy testujesz **duże API** i ważne jest przejrzyste logowanie tylko błędów.

### **🔹 Alternatywy**
1️⃣ **Pełne logowanie requestów i response'ów zawsze:**
```java
RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
```
➡️ Loguje **wszystkie żądania i odpowiedzi**, niezależnie od wyniku testu.

2️⃣ **Logowanie tylko requestów:**
```java
RestAssured.filters(new RequestLoggingFilter());
```

3️⃣ **Logowanie tylko response'ów:**
```java
RestAssured.filters(new ResponseLoggingFilter());
```

### **🔹 Podsumowanie**
- **RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();** to **inteligentne logowanie**, które włącza się
tylko w przypadku **niepowodzenia testu**.
- Świetne rozwiązanie dla **czytelnych logów i efektywnego debugowania** testów API.
- Jest **zalecaną praktyką** w testach automatycznych REST Assured. 🚀

---

## 📄RecursiveComparisonConfiguration() <a name="assertj_recursive_comparison_configuration"></a>

### **`RecursiveComparisonConfiguration` – Co to jest?**

`RecursiveComparisonConfiguration` to klasa z **AssertJ**, która pozwala na **rekurencyjne porównywanie obiektów**
w testach. Dzięki niej możemy szczegółowo kontrolować, jak działa porównywanie, np.:
- Pomijanie niektórych pól,
- Ignorowanie różnic w typach,
- Dostosowywanie sposobu porównywania kolekcji i map,
- Porównywanie pól w sposób niestandardowy.

### **🔹 Przykład użycia:**
Załóżmy, że mamy dwie instancje klasy `User`, które chcemy porównać:

#### **1️⃣ Standardowe porównanie (`equals()`)**
```java
User expectedUser = new User("Mateusz", "Tadla", "mtadla@example.com");
User actualUser = new User("Mateusz", "Tadla", "mtadla@example.com");

assertThat(actualUser).isEqualTo(expectedUser);
```
Jeśli **obiekty są identyczne**, test przejdzie. Ale jeśli np. `User` ma inne ID, test się wywali.

#### **2️⃣ Porównanie rekurencyjne (pomijając `id`)**
```java
import org.assertj.core.api.recursive.comparison.RecursiveComparisonConfiguration;

RecursiveComparisonConfiguration config = new RecursiveComparisonConfiguration();
config.ignoreFields("id"); // Ignorujemy pole "id"

assertThat(actualUser)
    .usingRecursiveComparison(config)
    .isEqualTo(expectedUser);
```
✔️ **Dzięki temu test przejdzie, nawet jeśli `id` jest inne!**

### **🔹 Główne możliwości `RecursiveComparisonConfiguration`**
#### 1️⃣ **Ignorowanie pól**
Możesz pominąć konkretne pola, np. `id`, `createdAt`:
```java
config.ignoreFields("id", "createdAt");
```

#### 2️⃣ **Ignorowanie pól tylko w jednym obiekcie**
Jeśli np. pole `timestamp` jest w `actualUser`, ale nie w `expectedUser`, możesz je pominąć:
```java
config.ignoreFieldsOfTypes(LocalDateTime.class);
```

#### 3️⃣ **Ignorowanie kolejności w kolekcjach**
```java
config.ignoreCollectionOrder();
```
✔️ Dzięki temu `[1,2,3]` i `[3,2,1]` będą uznane za **równe**.

#### 4️⃣ **Porównywanie pól niestandardowo**
Możesz określić własny sposób porównywania np. `BigDecimal` (aby ignorować precyzję dziesiętną):
```java
config.withComparatorForType(BigDecimal::compareTo, BigDecimal.class);
```

### **🔹 Przykładowy test z `RecursiveComparisonConfiguration`**
```java
@Test
void shouldCompareUsersIgnoringId() {
    User expectedUser = new User("Mateusz", "Tadla", "mtadla@example.com");
    User actualUser = new User("Mateusz", "Tadla", "mtadla@example.com");
    actualUser.setId(999); // Różne ID

    RecursiveComparisonConfiguration config = new RecursiveComparisonConfiguration();
    config.ignoreFields("id"); // Ignorujemy ID

    assertThat(actualUser)
        .usingRecursiveComparison(config)
        .isEqualTo(expectedUser);
}
```
✔️ **Test przejdzie pomimo różnicy w `id`!**

### **🔹 Podsumowanie**
| Możliwość                                                        | Opis                                             |
|------------------------------------------------------------------|--------------------------------------------------|
| `ignoreFields("id")`                                             | Pomija konkretne pola                            |
| `ignoreFieldsOfTypes(LocalDateTime.class)`                       | Pomija pola określonego typu                     |
| `ignoreCollectionOrder()`                                        | Ignoruje kolejność w listach i zbiorach          |
| `withComparatorForType(BigDecimal::compareTo, BigDecimal.class)` | Niestandardowe porównywanie dla określonego typu |

Dzięki `RecursiveComparisonConfiguration` możesz **uniknąć problemów z `equals()`**, dostosować sposób porównywania
i **uniknąć niepotrzebnych failów** w testach. 🚀🔥
