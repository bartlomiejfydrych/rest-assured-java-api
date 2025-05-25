# 🚮Archiwalny kod – notatki

# 📑Spis treści

- [JsonSchema Validation](#json_schema_validation)
- [DTO – pierwsze słabe próby deserializacji i walidacji](#dto_first)

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

Statyczne pole `VALIDATOR` tworzy singletona `Validator`, którego można używać do sprawdzania zgodności obiektów z adnotacjami typu `@NotNull`, `@Size`, `@Email` itd.

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

💡 Idealna do **automatycznych testów API**, by upewnić się, że odpowiedzi serwera są nie tylko poprawne formalnie (statusy HTTP), ale również zgodne z oczekiwanym modelem danych.
