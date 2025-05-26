# 🚮Archiwalny kod – notatki

# 📑Spis treści

- [JsonSchema Validation](#json_schema_validation)
- [DTO – pierwsze słabe próby deserializacji i walidacji](#dto_first)
- [JSON – porównywanie Stringów](#json_compare_as_string)
- [Obiekty – porównywanie z pomijaniem pól-getterów](#object_comparator)

# 📝Opis

## 📄JsonSchema Validation <a name="json_schema_validation"></a>

1. Pobieramy dependency **Json Schema Validator** od **Rest Assured**:
    ```xml
    <!-- https://mvnrepository.com/artifact/io.rest-assured/json-schema-validator -->
    <dependency>
        <groupId>io.rest-assured</groupId>
        <artifactId>json-schema-validator</artifactId>
        <version>${jsonSchemaValidatorRestAssured.version}</version>
    </dependency>
    ```
2. W katalogu `src/test` tworzymy katalog o nazwie `resources`
3. W katalogu `scr/test/resources` tworzymy katalog o nazwie `schema`
4. W nim tworzymy plik, któr będzie przechowywał nasz schema np. `POST_create_board_schema.json`
5. Oto jego przykładowa zawartość:
    ```json
    {
      "$schema": "https://json-schema.org/draft/2020-12/schema",
      "type": "object",
      "additionalProperties": false,
      "required": [
        "id", "name", "desc", "descData", "closed", "idOrganization", "idEnterprise",
        "pinned", "url", "shortUrl", "prefs", "labelNames", "limits"
      ],
      "properties": {
        "id": { "type": "string" },
        "name": {
          "type": "string",
          "minLength": 1,
          "maxLength": 16384
        },
        "desc": {
          "type": "string",
          "minLength": 0,
          "maxLength": 16384
        },
        "descData": { "type": ["object", "null"] },
        "closed": { "type": "boolean" },
        "idOrganization": {
          "type": "string",
          "pattern": "^[0-9a-fA-F]{24}$"
        },
        "idEnterprise": { "type": ["string", "null"] },
        "pinned": { "type": "boolean" },
        "url": { "type": "string", "format": "uri" },
        "shortUrl": { "type": "string", "format": "uri" },
        "prefs": {
          "type": "object",
          "additionalProperties": false,
          "required": [
            "permissionLevel", "hideVotes", "voting", "comments", "invitations", "selfJoin",
            "cardCovers", "showCompleteStatus", "cardCounts", "isTemplate", "cardAging",
            "calendarFeedEnabled", "hiddenPluginBoardButtons", "switcherViews", "autoArchive",
            "background", "backgroundColor", "backgroundDarkColor", "backgroundImage",
            "backgroundDarkImage", "backgroundImageScaled", "backgroundTile", "backgroundBrightness",
            "sharedSourceUrl", "backgroundBottomColor", "backgroundTopColor", "canBePublic",
            "canBeEnterprise", "canBeOrg", "canBePrivate", "canInvite"
          ],
          "properties": {
            "permissionLevel": {
              "type": "string",
              "enum": ["org", "private", "public"]
            },
            "hideVotes": { "type": "boolean" },
            "voting": {
              "type": "string",
              "enum": ["org", "private", "public", "disabled"]
            },
            "comments": {
              "type": "string",
              "enum": ["disabled", "members", "observers", "org", "public"]
            },
            "invitations": {
              "type": "string",
              "enum": ["members", "admins"]
            },
            "selfJoin": { "type": "boolean" },
            "cardCovers": { "type": "boolean" },
            "showCompleteStatus": { "type": "boolean" },
            "cardCounts": { "type": "boolean" },
            "isTemplate": { "type": "boolean" },
            "cardAging": {
              "type": "string",
              "enum": ["pirate", "regular"]
            },
            "calendarFeedEnabled": { "type": "boolean" },
            "hiddenPluginBoardButtons": {
              "type": "array",
              "items": { "type": "string" }
            },
            "switcherViews": {
              "type": "array",
              "items": {
                "type": "object",
                "required": ["viewType", "enabled"],
                "properties": {
                  "viewType": { "type": "string" },
                  "enabled": { "type": "boolean" }
                },
                "additionalProperties": false
              }
            },
            "autoArchive": { "type": ["boolean", "null"] },
            "background": {
              "type": "string",
              "enum": ["blue", "orange", "green", "red", "purple", "pink", "lime", "sky", "grey"]
            },
            "backgroundColor": { "type": "string" },
            "backgroundDarkColor": { "type": ["string", "null"] },
            "backgroundImage": { "type": ["string", "null"] },
            "backgroundDarkImage": { "type": ["string", "null"] },
            "backgroundImageScaled": { "type": ["array", "null"] },
            "backgroundTile": { "type": "boolean" },
            "backgroundBrightness": { "type": "string" },
            "sharedSourceUrl": { "type": ["string", "null"] },
            "backgroundBottomColor": { "type": "string" },
            "backgroundTopColor": { "type": "string" },
            "canBePublic": { "type": "boolean" },
            "canBeEnterprise": { "type": "boolean" },
            "canBeOrg": { "type": "boolean" },
            "canBePrivate": { "type": "boolean" },
            "canInvite": { "type": "boolean" }
          }
        },
        "labelNames": {
          "type": "object",
          "additionalProperties": { "type": "string" }
        },
        "limits": {
          "type": "object",
          "additionalProperties": false
        }
      }
    }
    ```
6. W klasie `TestBase` definiujemy zmienną z bazową ścieżką do naszych schematów:
    ```java
    public class TestBase {
        // Base path to all schemas
        protected static final String baseSchemaPath = "src/test/resources/schema";
    }
    ```
7. W klasie z naszymi testami np. `POST_CreateBoardTest` definiujemy zmienne ze ścieżkami do naszych schematów np.:
    ```java
    public class POST_CreateBoardTest extends TestBase {
    
        private final File postCreateBoardSchema = Paths.get(baseSchemaPath, "boards", "POST_create_board_schema.json").toFile();
        private final File getGetBoardSchema = Paths.get(baseSchemaPath, "boards", "GET_get_board.json").toFile();
    }
    ```
8. W samym teście wywołujemy metodę o nazwie `matchesJsonSchema()`, która odpowiada za sprawdzanie, czy nasz response
   jest zgodny z przekazanym Json Schema:
    ```java
    responsePost.then().assertThat().body(matchesJsonSchema(postCreateBoardSchema));
    ```

---

## 📄DTO – pierwsze słabe próby deserializacji i walidacji <a name="dto_first"></a>

Kod ten znajdował się w `scr/test/java/utils` w pliku o nazwie `DtoUtils`.

### Kod:

```java
package utils;

import io.restassured.response.Response;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Utility class for working with Data Transfer Objects (DTOs) in REST-assured tests.
 * <p>
 * Provides functionality for:
 * <ul>
 *     <li>Deserializing REST responses into single DTO objects or lists of DTOs</li>
 *     <li>Validating DTO structure and constraints using Bean Validation (Jakarta Validation / Hibernate Validator)</li>
 *     <li>Throwing informative assertion errors if the structure or constraints are violated</li>
 * </ul>
 */
public class DtoUtils {

    /**
     * Validator instance used to perform Bean Validation on DTOs.
     * <p>
     * Marked with {@code @SuppressWarnings("resource")} because the factory is AutoCloseable
     * but used here as a static singleton during the lifetime of the test JVM.
     */
    @SuppressWarnings("resource")
    private static final Validator VALIDATOR = Validation
            .buildDefaultValidatorFactory()
            .getValidator();

    // ------------
    // MAIN METHODS
    // ------------

    /**
     * Deserializes a REST-assured response body to a single DTO instance.
     *
     * @param response the REST-assured response
     * @param dtoClass the DTO class type to deserialize to
     * @param <T>      the DTO type
     * @return an instance of the DTO populated from the JSON response
     */
    public static <T> T deserializeResponse(Response response, Class<T> dtoClass) {
        return response.as(dtoClass);
    }

    /**
     * Deserializes a REST-assured response body to a list of DTO instances.
     * <p>
     * Handles dynamic array instantiation via reflection to avoid type erasure issues.
     * Uses {@code @SuppressWarnings("unchecked")} due to the cast of generic array.
     *
     * @param response the REST-assured response
     * @param dtoClass the DTO class type to deserialize to
     * @param <T>      the DTO type
     * @return a list of DTOs populated from the JSON response array
     */
    @SuppressWarnings("unchecked")
    public static <T> List<T> deserializeResponseList(Response response, Class<T> dtoClass) {
        T[] array = (T[]) java.lang.reflect.Array.newInstance(dtoClass, 0);
        return Arrays.asList(response.as((Class<T[]>) array.getClass()));
    }

    /**
     * Validates a single DTO object against its annotated Bean Validation constraints.
     * <p>
     * Throws an {@link AssertionError} if any constraint violations are found, and lists all violations.
     *
     * @param dto the DTO object to validate
     * @param <T> the DTO type
     * @throws AssertionError if validation fails
     */
    public static <T> void validateDto(T dto) {
        Set<ConstraintViolation<T>> violations = VALIDATOR.validate(dto);

        if (!violations.isEmpty()) {
            throw new AssertionError("DTO validation failed:\n\n" + formatViolations(violations));
        }
    }

    /**
     * Validates each DTO object in a list against its annotated Bean Validation constraints.
     * <p>
     * Throws an {@link AssertionError} with a full report of all constraint violations across the list.
     *
     * @param dtoList the list of DTO objects to validate
     * @param <T>     the DTO type
     * @throws AssertionError if validation fails for any item in the list
     */
    public static <T> void validateDtoList(List<T> dtoList) {
        StringBuilder allErrors = new StringBuilder();
        boolean hasErrors = false;

        for (int i = 0; i < dtoList.size(); i++) {
            T item = dtoList.get(i);
            Set<ConstraintViolation<T>> violations = VALIDATOR.validate(item);
            if (!violations.isEmpty()) {
                hasErrors = true;
                allErrors.append("Item #").append(i).append(":\n")
                        .append(formatViolations(violations)).append("\n");
            }
        }

        if (hasErrors) {
            throw new AssertionError("DTO list validation failed:\n\n" + allErrors);
        }
    }

    // --------------
    // HELPER METHODS
    // --------------

    /**
     * Formats a set of constraint violations into a readable string for test output.
     * Each violation will include the field name, rejected value, and message.
     *
     * @param violations the set of constraint violations
     * @param <T>        the type of the validated object
     * @return a formatted string of all violations
     */
    private static <T> String formatViolations(Set<ConstraintViolation<T>> violations) {
        return violations.stream()
                .map(v -> "- Field: " + v.getPropertyPath()
                        + "\n  Rejected value: " + v.getInvalidValue()
                        + "\n  Violation: " + v.getMessage())
                .collect(Collectors.joining("\n\n"));
    }
}
```

### Opis kodu:

Poniżej znajduje się dokładna analiza działania klasy `DtoUtils` zawartej w podanym kodzie w języku Java:

### 📦 **Pakiet i Importy**

```java
package utils;
```

Klasa `DtoUtils` należy do pakietu `utils`, co sugeruje, że pełni funkcję pomocniczą (utility class) w projekcie.

Importowane są biblioteki:

* `io.restassured.response.Response` – REST-assured do testowania REST API.
* `jakarta.validation.*` – biblioteka do walidacji Beanów (np. Hibernate Validator).
* `java.util.*` i `java.util.stream.Collectors` – typowe kolekcje i operacje strumieniowe.

### 🎯 **Cel klasy**

Dokumentacja w Javadoc wyjaśnia, że `DtoUtils`:

* Deserializuje odpowiedzi HTTP do obiektów DTO (Data Transfer Object).
* Waliduje te obiekty z wykorzystaniem anotacji walidacyjnych (Bean Validation).
* Rzuca wyjątki `AssertionError`, jeśli coś jest nie tak – przydatne w testach automatycznych.

### 🛠️ **Polimorficzny Walidator**

```java
private static final Validator VALIDATOR = Validation
        .buildDefaultValidatorFactory()
        .getValidator();
```

Statyczne pole `VALIDATOR` tworzy singletona `Validator`, którego można używać do sprawdzania zgodności obiektów
z adnotacjami typu `@NotNull`, `@Size`, `@Email` itd.

### 🔄 **Deserializacja DTO z odpowiedzi HTTP**

#### 1. `deserializeResponse(...)`

```java
public static <T> T deserializeResponse(Response response, Class<T> dtoClass)
```

* Używa REST-assured (`response.as(...)`) do przekształcenia JSON-a z odpowiedzi HTTP w pojedynczy obiekt klasy `T`.
* Generyczne, bezpieczne typowo.

#### 2. `deserializeResponseList(...)`

```java
public static <T> List<T> deserializeResponseList(Response response, Class<T> dtoClass)
```

* Deserializuje **listę DTO** z JSON-a zawierającego tablicę.
* Tworzy pustą tablicę typu `T[]` dynamicznie przez refleksję.
* Potem wykonuje `response.as(...)`, przekazując klasę tablicy.
* Wynik jest konwertowany do listy przez `Arrays.asList`.

### ✅ **Walidacja Beanów (DTO)**

#### 3. `validateDto(...)`

```java
public static <T> void validateDto(T dto)
```

* Weryfikuje pojedynczy obiekt DTO przy użyciu walidatora.
* Jeśli wystąpią naruszenia (np. pole `null`, ale adnotacja `@NotNull`), rzuca `AssertionError`.
* Treść błędu zawiera pełną listę naruszeń sformatowaną przez `formatViolations(...)`.

#### 4. `validateDtoList(...)`

```java
public static <T> void validateDtoList(List<T> dtoList)
```

* Iteruje po liście obiektów DTO i waliduje każdy z nich.
* Gromadzi wszystkie błędy w `StringBuilder`.
* Jeśli którykolwiek obiekt narusza reguły, rzuca `AssertionError` z dokładnym raportem (indeks elementu i jego błędy).

### 🧹 **Pomocnicza metoda formatująca błędy**

#### 5. `formatViolations(...)`

```java
private static <T> String formatViolations(Set<ConstraintViolation<T>> violations)
```

* Dla każdego naruszenia zwraca:

   * Nazwę pola (`v.getPropertyPath()`),
   * Niepoprawną wartość (`v.getInvalidValue()`),
   * Komunikat walidacyjny (`v.getMessage()`).
* Łączy te dane w czytelny string.

### 🧪 Przykład użycia w teście

Wyobraźmy sobie test:

```java
Response response = given().get("/api/users/1");
UserDto user = DtoUtils.deserializeResponse(response, UserDto.class);
DtoUtils.validateDto(user);
```

* Deserializujemy odpowiedź do `UserDto`.
* Walidujemy obiekt zgodnie z adnotacjami jak `@NotNull`.

### 🧠 Podsumowanie

**`DtoUtils`** to klasa pomocnicza do:

* Deserializacji JSON-ów do DTO z REST-assured,
* Walidacji tych DTO z użyciem Jakarta Bean Validation,
* Rzucania czytelnych błędów w testach, jeśli walidacja nie powiedzie się.

💡 Idealna do **automatycznych testów API**, by upewnić się, że odpowiedzi serwera są nie tylko poprawne formalnie
(statusy HTTP), ale również zgodne z oczekiwanym modelem danych.

---

## 📄JSON – porównywanie Stringów <a name="json_compare_as_string"></a>

Kod ten znajdował się w `scr/test/java/utils` w pliku o nazwie `JsonUtils`.

### Kod:

```java
package utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.skyscreamer.jsonassert.*;
import org.skyscreamer.jsonassert.comparator.CustomComparator;

import java.util.Set;

/**
 * Utility class for JSON comparison operations with options for ignoring specific fields
 * and supporting both strict and lenient modes.
 */
public class JsonUtils {

    // ------------
    // MAIN METHODS
    // ------------

    /**
     * Asserts that two JSON strings are equal while ignoring specified fields.
     *
     * @param expectedJson   the expected JSON string
     * @param actualJson     the actual JSON string
     * @param fieldsToIgnore set of field paths to ignore during comparison (supports wildcards like "prefs.*")
     * @param strict         if true, enforces strict order and values; if false, allows lenient comparison
     * @throws JSONException  if the JSON parsing or comparison fails
     * @throws AssertionError if the JSONs are not equal according to the comparison criteria
     */
    public static void assertJsonEqualsIgnoringFields(String expectedJson, String actualJson, Set<String> fieldsToIgnore, boolean strict) throws JSONException {
        JSONCompareResult result = compareJsonIgnoringFields(expectedJson, actualJson, fieldsToIgnore, strict);
        if (!result.passed()) {
            throw new AssertionError(formatError(result));
        }
    }

    /**
     * Asserts that two JSON strings are exactly equal, without ignoring any fields.
     *
     * @param expectedJson the expected JSON string
     * @param actualJson   the actual JSON string
     * @param strict       if true, enforces strict order and values; if false, allows lenient comparison
     * @throws JSONException  if the JSON parsing or comparison fails
     * @throws AssertionError if the JSONs are not equal according to the comparison criteria
     */
    public static void assertJsonEquals(String expectedJson, String actualJson, boolean strict) throws JSONException {
        JSONCompareResult result = compareJson(expectedJson, actualJson, strict);
        if (!result.passed()) {
            throw new AssertionError(formatError(result));
        }
    }

    // --------------
    // HELPER METHODS
    // --------------

    // BIG HELPERS

    /**
     * Compares two JSON strings while ignoring specific fields.
     *
     * @param expectedJson   the expected JSON string
     * @param actualJson     the actual JSON string
     * @param fieldsToIgnore set of field paths to ignore during comparison
     * @param strict         if true, uses strict comparison; otherwise uses lenient mode
     * @return JSONCompareResult containing the comparison result
     * @throws JSONException if JSON parsing fails
     */
    private static JSONCompareResult compareJsonIgnoringFields(String expectedJson, String actualJson, Set<String> fieldsToIgnore, boolean strict) throws JSONException {
        JSONCompareMode mode = strict ? JSONCompareMode.STRICT : JSONCompareMode.LENIENT;

        Customization[] customizations = fieldsToIgnore.stream()
                .map(JsonUtils::createDeepCustomization)
                .toArray(Customization[]::new);

        CustomComparator comparator = new CustomComparator(mode, customizations);

        Object expected = parseJson(expectedJson, "expectedJson");
        Object actual = parseJson(actualJson, "actualJson");

        return JSONCompare.compareJSON(expected.toString(), actual.toString(), comparator);
    }

    /**
     * Compares two JSON strings without ignoring any fields.
     *
     * @param expectedJson the expected JSON string
     * @param actualJson   the actual JSON string
     * @param strict       if true, uses strict comparison; otherwise uses lenient mode
     * @return JSONCompareResult containing the comparison result
     * @throws JSONException if JSON parsing fails
     */
    private static JSONCompareResult compareJson(String expectedJson, String actualJson, boolean strict) throws JSONException {
        JSONCompareMode mode = strict ? JSONCompareMode.STRICT : JSONCompareMode.LENIENT;
        Object expected = parseJson(expectedJson, "expectedJson");
        Object actual = parseJson(actualJson, "actualJson");

        return JSONCompare.compareJSON(expected.toString(), actual.toString(), mode);
    }

    // SMALL HELPERS

    /**
     * Parses a JSON string into either a {@link JSONObject} or {@link JSONArray}.
     * <p>
     * In case of parsing failure or invalid structure, throws a {@link RuntimeException}
     * containing details about the specific input (label) that caused the error.
     * <p>
     * Designed to help quickly identify issues with malformed JSON inputs during tests.
     *
     * @param json  the JSON string to parse
     * @param label a label (e.g., "expectedJson" or "actualJson") used for clearer error reporting
     * @return a parsed {@link JSONObject} or {@link JSONArray}
     * @throws RuntimeException if parsing fails or if the parsed object is neither a JSONObject nor JSONArray
     */
    private static Object parseJson(String json, String label) {
        try {
            Object parsed = JSONParser.parseJSON(json);
            if (parsed instanceof JSONObject || parsed instanceof JSONArray) {
                return parsed;
            } else {
                throw new IllegalArgumentException("Provided " + label + " is neither an object nor an array.");
            }
        } catch (Exception e) {
            throw new RuntimeException("\nFailed to parse " + label + ".\nInput:\n" + json + "\nError: " + e.getMessage(), e);
        }
    }

    /**
     * Creates a Customization object for field ignoring.
     * Supports both exact field ignoring and wildcard section ignoring (e.g., "prefs.*").
     *
     * @param path the field path to ignore
     * @return Customization that matches the provided field path
     */
    private static Customization createDeepCustomization(String path) {
        if (path.endsWith(".*")) {
            String prefix = path.substring(0, path.length() - 2);
            return new Customization(prefix, (o1, o2) -> true);
        } else {
            return new Customization(path, (o1, o2) -> true);
        }
    }

    // LOGS

    /**
     * Formats the differences found during JSON comparison into a readable string.
     *
     * @param result the JSONCompareResult containing differences
     * @return a formatted String listing all differences
     */
    private static String formatError(JSONCompareResult result) {
        StringBuilder sb = new StringBuilder();
        sb.append("Differences:\n");

        for (FieldComparisonFailure failure : result.getFieldFailures()) {
            sb.append("- Field: ").append(failure.getField())
                    .append("\n  Expected: ").append(failure.getExpected())
                    .append("\n  Actual:   ").append(failure.getActual())
                    .append("\n\n");
        }

        return sb.toString();
    }
}
```

### Opis kodu:

Poniżej znajduje się **szczegółowa analiza klasy `JsonUtils`**. Klasa ta jest narzędziem do **porównywania JSON-ów**
w testach, z możliwością pomijania wskazanych pól oraz trybem porównania ścisłego lub luźnego.

### 📦 Pakiet i biblioteki

```java
package utils;
```

Klasa należy do pakietu `utils` – czyli zestawu narzędzi pomocniczych.

**Wykorzystywane biblioteki:**

* `org.skyscreamer.jsonassert` – biblioteka `JSONAssert`, umożliwiająca porównywanie JSON-ów.
* `org.json.*` – biblioteki do pracy z JSON-em.
* `Set` – kolekcje Java.
* `CustomComparator`, `Customization` – pozwalają dostosowywać sposób porównania pól.

### 🎯 Główne zadania `JsonUtils`

1. Porównuje dwa JSON-y w testach.
2. Pozwala ignorować wybrane pola (np. `"timestamp"`, `"prefs.*"`).
3. Obsługuje tryb **ścisły** (kolejność i wartości muszą być dokładnie takie same) oraz **luźny** (kolejność elementów
   nie ma znaczenia).
4. Rzuca `AssertionError` z dokładnym raportem różnic, jeśli JSON-y nie są równe.

### 🛠️ Publiczne metody

#### 1. `assertJsonEqualsIgnoringFields(...)`

```java
public static void assertJsonEqualsIgnoringFields(String expectedJson, String actualJson, Set<String> fieldsToIgnore, boolean strict)
```

* Porównuje dwa JSON-y, **ignorując podane pola**.
* Parametry:

    * `expectedJson`, `actualJson` – JSON-y jako tekst.
    * `fieldsToIgnore` – zestaw pól do zignorowania (np. `Set.of("id", "timestamp")`).
    * `strict` – `true`: porównanie ścisłe, `false`: porównanie luźne.
* W przypadku różnic – rzuca `AssertionError` z czytelnym opisem.

#### 2. `assertJsonEquals(...)`

```java
public static void assertJsonEquals(String expectedJson, String actualJson, boolean strict)
```

* Porównuje JSON-y **bez ignorowania jakichkolwiek pól**.
* Wykorzystuje metodę pomocniczą `compareJson(...)`.
* Tryb porównania kontrolowany przez `strict`.

### 🔧 Metody pomocnicze

#### 3. `compareJsonIgnoringFields(...)`

```java
private static JSONCompareResult compareJsonIgnoringFields(...)
```

* Właściwe porównanie dwóch JSON-ów z pomijaniem pól.
* Tworzy tablicę `Customization[]`, gdzie każde pominięcie jest zdefiniowane jako warunek "ignoruj to pole".
* Używa `CustomComparator` z trybem `STRICT` lub `LENIENT`.

#### 4. `compareJson(...)`

```java
private static JSONCompareResult compareJson(...)
```

* Porównuje JSON-y bez ignorowania pól.
* Również obsługuje tryb `STRICT` / `LENIENT`.

#### 5. `parseJson(...)`

```java
private static Object parseJson(String json, String label)
```

* Parsuje JSON string do obiektu `JSONObject` lub `JSONArray`.
* Używa `JSONParser.parseJSON(...)`.
* Jeśli JSON jest nieprawidłowy – rzuca `RuntimeException` z etykietą (`expectedJson` lub `actualJson`), co ułatwia
  debugowanie w testach.

### 🔍 Pomocnicze elementy ignorowania pól

#### 6. `createDeepCustomization(...)`

```java
private static Customization createDeepCustomization(String path)
```

* Tworzy obiekt `Customization`, który ignoruje pole o zadanej nazwie lub z użyciem wildcardów (`.*`).

    * `"prefs.*"` – zignoruje wszystkie podpola w obiekcie `prefs`.
    * `"id"` – zignoruje tylko konkretne pole.
* `Customization` opiera się na zasadzie, że porównywane pola zawsze zwracają `true` – czyli są "równe" niezależnie od wartości.

### 📋 Formatowanie różnic

#### 7. `formatError(...)`

```java
private static String formatError(JSONCompareResult result)
```

* Tworzy przyjazny człowiekowi raport różnic, np.:

```
Differences:
- Field: name
  Expected: "John"
  Actual:   "Jane"
```

### 🧪 Przykład użycia w teście

```java
String expected = "{ \"name\": \"John\", \"timestamp\": 123456 }";
String actual = "{ \"name\": \"John\", \"timestamp\": 987654 }";

JsonUtils.assertJsonEqualsIgnoringFields(expected, actual, Set.of("timestamp"), true);
```

W powyższym przykładzie:

* Test przejdzie, bo `timestamp` jest ignorowany.
* `strict = true` wymusza zgodność co do kolejności i wartości pozostałych pól.

### 🧠 Podsumowanie

| Cecha             | Opis                                                                                   |
|-------------------|----------------------------------------------------------------------------------------|
| **Cel**           | Porównywanie JSON-ów w testach                                                         |
| **Tryby**         | `strict` – porównuje dokładnie (łącznie z kolejnością), `lenient` – ignoruje kolejność |
| **Pomijanie pól** | Tak, z obsługą wildcardów (np. `"prefs.*"`)                                            |
| **Biblioteka**    | JSONAssert (`org.skyscreamer.jsonassert`)                                              |
| **Zastosowanie**  | Testy integracyjne, testy API, porównanie snapshotów                                   |

---

## 📄Obiekty – porównywanie z pomijaniem pól-getterów <a name="object_comparator"></a>

Kod ten znajdował się w `scr/test/java/utils` w pliku o nazwie `ObjectComparator`.

### Kod:

```java
package utils;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.recursive.comparison.RecursiveComparisonConfiguration;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ObjectComparator {

    private ObjectComparator() {
        throw new UnsupportedOperationException("Utility class should not be instantiated");
    }

    @FunctionalInterface
    public interface Getter<T, R> extends Function<T, R>, Serializable {
    }

    @FunctionalInterface
    public interface GetterChainComponent<T, R> extends Function<T, R>, Serializable {
    }

    // Pomocnicza metoda do podawania getterów bez rzutowania
    public static <T, R> GetterChainComponent<T, R> getter(GetterChainComponent<T, R> g) {
        return g;
    }

    @SafeVarargs
    public static <T> void compareObjectsWithIgnoredFields(
            T actual,
            T expected,
            GetterChain<T>... ignoredGetterChains
    ) {
        List<String> ignoredPaths = Arrays.stream(ignoredGetterChains)
                .map(GetterChain::toFieldPath)
                .toList();

        RecursiveComparisonConfiguration config = new RecursiveComparisonConfiguration();
        config.ignoreFields(ignoredPaths.toArray(new String[0]));

        Assertions.assertThat(actual)
                .usingRecursiveComparison(config)
                .isEqualTo(expected);
    }

    public static <T> GetterChain<T> getters(GetterChainComponent<?, ?>... chain) {
        return new GetterChain<>(chain);
    }

    public static class GetterChain<T> {
        private final List<GetterChainComponent<?, ?>> getters;

        public GetterChain(GetterChainComponent<?, ?>... getters) {
            this.getters = Arrays.asList(getters);
        }

        public String toFieldPath() {
            return getters.stream()
                    .map(ObjectComparator::extractFieldName)
                    .collect(Collectors.joining("."));
        }
    }

    private static String extractFieldName(Function<?, ?> getter) {
        try {
            Method writeReplace = getter.getClass().getDeclaredMethod("writeReplace");
            writeReplace.setAccessible(true);
            SerializedLambda lambda = (SerializedLambda) writeReplace.invoke(getter);
            String methodName = lambda.getImplMethodName();

            if (methodName.startsWith("get")) {
                return decapitalize(methodName.substring(3));
            } else if (methodName.startsWith("is")) {
                return decapitalize(methodName.substring(2));
            } else {
                throw new IllegalStateException("Unsupported getter method: " + methodName);
            }
        } catch (Exception e) {
            throw new RuntimeException("Could not extract field name from lambda", e);
        }
    }

    private static String decapitalize(String name) {
        if (name == null || name.isEmpty()) return name;
        return Character.toLowerCase(name.charAt(0)) + name.substring(1);
    }

    public static <T> void compareObjectsIgnoringId(T actual, T expected) {
        compareObjectsWithIgnoredFields(actual, expected,
                getters(getter(ObjectComparator.<T>castGetter(ObjectComparator::getId)))
        );
    }

    // Pomocnicza metoda do rzutowania referencji do gettera
    @SuppressWarnings("unchecked")
    private static <T> GetterChainComponent<T, ?> castGetter(Function<?, ?> getter) {
        return (GetterChainComponent<T, ?>) getter;
    }

    // Zakładamy, że wszystkie obiekty mają metodę getId()
    private static Object getId(Object o) {
        try {
            Method method = o.getClass().getMethod("getId");
            return method.invoke(o);
        } catch (Exception e) {
            throw new RuntimeException("Could not call getId()", e);
        }
    }
}

/*

Example of use:

compareObjectsWithIgnoredFields(actualResponsePost, expectedResponsePost,
getters(getter(POST_CreateBoardDto::getId)),
getters(getter(POST_CreateBoardDto::getPrefs), getter(Prefs::isTemplate)),
getters(getter(POST_CreateBoardDto::getName)),
getters(getter(POST_CreateBoardDto::getShortUrl)),
getters(getter(POST_CreateBoardDto::getUrl))
);

*/
```

### Opis kodu:

Kod klasy `ObjectComparator` to **zaawansowane narzędzie do porównywania obiektów w testach** z użyciem biblioteki
AssertJ, z możliwością ignorowania wskazanych pól (nawet zagnieżdżonych!). Kod ten jest przemyślaną implementacją
porównywania *rekursywnego* (`usingRecursiveComparison`) i opiera się na refleksji i analizie wyrażeń lambda.

### 🎯 Główne cele `ObjectComparator`

* Porównywanie dwóch obiektów (`actual`, `expected`) z pominięciem wskazanych pól.
* Umożliwia ignorowanie zagnieżdżonych pól poprzez przekazanie *łańcucha getterów*.
* Wspiera API w stylu fluent/testowym z małym ryzykiem błędów typów.
* Oparty na bibliotece [AssertJ](https://assertj.github.io/doc/), w szczególności na `RecursiveComparisonConfiguration`.

### 📦 Używane biblioteki i klasy

* `AssertJ` – do porównań z `assertThat(actual).usingRecursiveComparison(...)`.
* `SerializedLambda` – pozwala analizować wyrażenia lambda (np. `POST::getId`) i uzyskać nazwę metody.
* `Function`, `Serializable` – umożliwiają przekazywanie bezpiecznych lambda-getterów jako argumentów.
* `RecursiveComparisonConfiguration` – konfigurator z AssertJ do porównań z pomijaniem pól.

### 🧱 Budowa klasy

#### 🔐 Konstruktor prywatny

```java
private ObjectComparator() {
    throw new UnsupportedOperationException("Utility class should not be instantiated");
}
```

To klasa narzędziowa – **nie powinna być instancjonowana**.

### 🧩 Kluczowe interfejsy funkcyjne

#### `Getter<T, R>` i `GetterChainComponent<T, R>`

```java
@FunctionalInterface
public interface Getter<T, R> extends Function<T, R>, Serializable
```

* Oba rozszerzają `Function` i `Serializable`, co umożliwia ich introspekcję przez `SerializedLambda`.
* **Służą jako typy lambda-wyrażeń** wskazujących pola obiektów (np. `x -> x.getId()`).

### 🛠 Główna metoda

#### `compareObjectsWithIgnoredFields(...)`

```java
@SafeVarargs
public static <T> void compareObjectsWithIgnoredFields(
        T actual,
        T expected,
        GetterChain<T>... ignoredGetterChains
)
```

* Porównuje dwa obiekty rekursywnie.
* Argumenty:

    * `actual`, `expected` – obiekty do porównania.
    * `ignoredGetterChains` – lista pól do zignorowania (w formie łańcucha getterów).
* Internie:

    1. Tworzy listę ścieżek pól (`List<String>`) jak `"prefs.template"` z `GetterChain`.
    2. Tworzy konfigurację `RecursiveComparisonConfiguration` z tymi ścieżkami.
    3. Używa `AssertJ` do porównania.

### 🧠 Mechanizm budowania ścieżek pól

#### `GetterChain<T>`

```java
public static class GetterChain<T>
```

* Przyjmuje tablicę getterów (`Function`s).
* `toFieldPath()` analizuje każdy getter i buduje np. `prefs.template` z `POST::getPrefs` + `Prefs::isTemplate`.

##### `extractFieldName(...)`

```java
private static String extractFieldName(Function<?, ?> getter)
```

* Używa `SerializedLambda` do odczytu nazwy metody (np. `getName` → `name`).
* Obsługuje `getX()` i `isX()`.

### 📦 Pomocnicze metody

#### `getter(...)`

```java
public static <T, R> GetterChainComponent<T, R> getter(GetterChainComponent<T, R> g)
```

* Ułatwia przekazywanie wyrażeń lambda bez rzutowania.

#### `getters(...)`

```java
public static <T> GetterChain<T> getters(GetterChainComponent<?, ?>... chain)
```

* Tworzy obiekt `GetterChain<T>` z przekazanych getterów (jeden lub więcej).
* Dzięki temu można zdefiniować ścieżki takie jak:

  ```java
  getters(getter(POST::getPrefs), getter(Prefs::isTemplate))
  ```

### 🔁 Skrócona metoda dla `getId`

#### `compareObjectsIgnoringId(...)`

```java
public static <T> void compareObjectsIgnoringId(T actual, T expected)
```

* Skrócony wariant porównania ignorujący tylko `getId()`.
* Używa `getId()` (refleksyjnego) jako getter.

### 🧪 Przykład użycia

```java
compareObjectsWithIgnoredFields(actual, expected,
    getters(getter(POST::getId)),
    getters(getter(POST::getPrefs), getter(Prefs::isTemplate)),
    getters(getter(POST::getShortUrl))
);
```

* Ignoruje `id`, `prefs.template` i `shortUrl`.
* Porównanie głębokie (rekursywne).
* Bardzo czytelne w testach jednostkowych lub integracyjnych.

### ✅ Podsumowanie

| Element               | Opis                                                                        |
|-----------------------|-----------------------------------------------------------------------------|
| **Cel**               | Porównanie obiektów z możliwością ignorowania wskazanych pól                |
| **Wsparcie dla**      | Getterów lambda, pól zagnieżdżonych                                         |
| **Technologie**       | AssertJ (`RecursiveComparisonConfiguration`), `SerializedLambda`, refleksja |
| **Funkcja specjalna** | `compareObjectsIgnoringId()` – skrót dla typowego przypadku                 |
| **Zastosowanie**      | Testy jednostkowe/modelowe DTO, porównywanie danych JSON/API/ORM            |

---
