# 🌐REST Assured i testy – notatki

# 📑Spis treści

- [START – rozpoczęcie pisania testów](#start_writing_tests)
- [RequestSpecification](#request_specification)
- [RestAssured.filters()](#rest_assured_filters)
- [RequestSpecBuilder](#request_spec_builder)
- [RestAssured.enableLoggingOfRequestAndResponseIfValidationFails()](#enable_log_fail)
- [RecursiveComparisonConfiguration()](#assertj_recursive_comparison_configuration)
- [Struktura JSON – JsonSchema vs. DTO/POJO](#json_schema_dto_pojo)
- [Porównywanie JSON'ów – wyzwania, podejścia, praktyki](#json_compare_intro)
- [Porównywanie JSON'ów – ObjectMapper](#json_compare_object_mapper)
- [Porównywanie JSON'ów – JsonNode](#json_compare_json_node)
- [REST Assured – asercja dla pustego obiektu](#rest_assured_assert_empty_object)

---

# 📝Opis

## 📄START – rozpoczęcie pisania testów <a name="start_writing_tests"></a>

1. Zakładamy `konta` i inne `dostępy`
    - W przypadku tego projektu zakładamy `konto` oraz zdobywamy `API key` oraz `token` na stronie **Trello**
    - Szczegóły w `README` katalogu `📂trello-configuration`
2. W katalogu `src/main/resources` tworzymy katalog `configs`, a w nim plik o nazwie `config.properties`  
   Wszelkie ustawienia projektu warto trzymać i odczytywać z osobnego pliku, aby nie musieć nic zmieniać w samym kodzie.  
   Zapisujemy w nim takie rzeczy jak:
    - bazowy URL w całości
    - bazowy URL rozbity na osobne segmenty:
      - protokół
      - subdomena
      - domena
      - TLD
      - Numer
3. Sprawdzamy, czy mamy w `pom.xml` dodane dependecy o nazwie `Dotenv Java`
4. Otwieramy plik `.gitignore` i dopisujemy w nim: `environment/.env`
5. W głównym katalogu projektu tworzymy katalog o nazwie `environment`
6. W nim tworzymy dwa pliki:  
   - `.env` (tutaj będziemy przechowywać nasze prawdziwe zmienne)
   - `.env.example` (tutaj będzie pusty wzór dostępny na repozytorium)
7. W plikach `.env` definiujemy:
    - Sekcję do zarządzania logami:
      - czy wyświetlać logi, kiedy test się wywali
      - czy zawsze wyświetlać logi
    - Sekcję na klucz API oraz token:
      - api key
      - token
8. W katalogu `src/main/java` tworzymy katalog o nazwie `configuration`
9. W katalogu `configuration` tworzymy plik java class o nazwie `Config.java`
10. W pliku `Config.java` definiujemy następujące rzeczy:
    - Obiekty:
      - `properties` (do wczytywania zmiennych z `config.properties`)
      - `dotenv` (do wczytywania zmiennych z `.env`)
    - Inicjator, który wczytuje plik konfiguracyjny `config.properties`
    - Metody pomocnicze:
      - metoda pobierająca właściwość typu `String` z pliku `config.properties`
      - metoda pobierająca właściwość typu `String` z pliku `.env`
      - metoda pobierająca właściwość typu `boolean` z pliku `config.properties`
      - metoda pobierająca właściwość typu `boolean` z pliku `.env`
    - Metody pobierające dane z pliku `config.properties`
      - bazowy URL w całości
      - protokół
      - subdomena
      - domena
      - TLD
      - Numer
    - Metody pobierające dane z pliku `.env`
      - czy wyświetlać logi, kiedy test się wywali
      - czy zawsze wyświetlać logi
      - api key
      - token
11. W katalogu `src/main/java/configuration` tworzymy plik `BaseUrlBuilder`
12. W pliku `BaseUrlBuilder` piszemy budowanie naszego URL ze zmiennych konfiguracyjnych projektu
13. W katalogu `src/test/java` tworzymy katalog package o nazwie `configuration`
14. W katalogu `src/test/java/configuration` tworzymy plik `RequestSpecConfig`  
    Dlaczego tutaj, a nie w `main`?  
    Ponieważ `REST Assured` jest używane tylko do testów i jego specyfikacja tak zaleca.  
    Żeby obejść to ograniczenie można też w `pom.xml` usunąć wiersz z `<scope>test</scope>`.
15. W pliku `RequestSpecConfig` piszemy naszą wspólną konfigurację dla wszystkich requestów oraz metodę ją zwracającą
16. W katalogu `src/test/java` tworzymy katalog o nazwie `base`
17. W katalogu `src/test/java/base` tworzymy plik o nazwie `TestBase`
18. W pliku `TestBase` tworzymy wstępną konfigurację:
    - Deklarujemy zmienne i obiekty:
      - requestSpecificationCommon
      - responsePost
      - responsePut
      - responseGet
      - responseDelete
      - faker
    - Ustawiamy `setUpAll()` dla `@BeforeAll`:
      - czy mają być wyświetlane logi zawsze
      - czy mają być wyświetlane tylko, jeśli test się wywali
      - pobieranie konfiguracji requestów i przypisywanie jej do naszej zmiennej  
        (później klasy z endpointami będą dziedziczyły tą zmienną po klasie `TestBase`)
19. W katalogu `src/test/java` tworzymy katalog o nazwie `utils`  
    Dlaczego w test? Ponieważ `AssertJ` ma ustawiony <scope> na ten katalog w `pom.xml`.  
    Można by go usunąć, ale jak tak zalecają to lepiej nie ruszać.
20. W katalogu `src/test/java/utils` tworzymy plik o nazwie `UtilsCompare`
21. W pliku `UtilsCompare` tworzymy metodę, która bazując na dependency `AssertJ` będzie:
    - porównywać obiekty
    - a jeśli podamy jako `String` parametry np. `"id"` to będą one pomijane przy porównywaniu
22. W katalogu `src/test/java` tworzymy katalog o nazwie `endpoints`
23. W katalogu `src/test/java/endpoints` tworzymy katalog o nazwie `boards` (na wzór dokumentacji)  
    **Wyjaśnienie:**  
    W zależności od formatu dokumentacji (Swagger lub to, czego używa Trello) tworzymy strukturę katalogów i klas,
    która będzie zgodna z nią np. jeśli w Swaggerze endpoint jest zgrupowany w jeden nieduży controller to wszystkie jego
    warianty (POST, PATCH/PUT, GET, DELETE) tworzymy w jednym pliku np. `Boards` od `/boards`.  
    W sytuacji, w której controller dla tego endpointa jest duży lub tak jak w dokumentacji Trello wiele endpointów jest
    zgrupowane w jednej ogólnej sekcji `Boards` tworzymy wtedy pod każdą metodę HTTP danego endpointa osobny plik/klasę.  
    Przykłady: `POST_CreateBoard`, `PUT_UpdateBoard`, `DELETE_DeleteBoard` itd.
24. W katalogu tym tworzymy plik pod nasz pierwszy endpoint o nazwie `POST_CreateBoard`  
    **Wyjaśnienie:**  
    Z reguły konwencja nazw klas w Java nie zaleca używania `_` natomiast w niczym to nie przeszkadza (potwierdzone
    przez czat GPT), zwłaszcza w testach API, a w tym przypadku fajnie zwiększa to czytelność. Zgodnie z konwencją ten
    plik nazywałby się wtedy `PostCreateBoard`.
25. W pliku `POST_CreateBoard`:
    - Dziedziczymy/Rozszerzamy tę klasę po `TestBase`, żeby nasz endpoint mógł używać wspólnej konfiguracji `requestSpecificationCommon`  
      Jeżeli byśmy tego nie zrobili, to musielibyśmy tutaj i w każdym kolejnym pliku wywoływać tę konfigurację:  
      ```java
      RequestSpecification requestSpecificationCommon = RequestSpecConfig.getRequestSpecification();
      ```
    - Tworzymy metodę lub metody wywołujące ten request i używające jako argumentów podawanych przez nas parametrów lub
      payloadów
    ```java
    package endpoints.boards;
    
    import base.TestBase;
    import io.restassured.response.Response;
    import io.restassured.specification.RequestSpecification;
    
    import java.util.Map;
    
    import static io.restassured.RestAssured.given;
    
    public class POST_CreateBoard extends TestBase {
    
        private static final String url = "/boards";
    
        public static Response postCreateBoard(String name, Map<String, Object> queryParams) {
    
            RequestSpecification spec = given().
                    spec(requestSpecificationCommon)
                    .queryParam("name", name);
    
            if (queryParams != null && !queryParams.isEmpty()) {
                spec.queryParams(queryParams);
            }
    
            return spec.
                    when().
                        post(url).
                    then().
                        extract().
                        response();
        }
    }
    ```
26. W katalogu `src/test` tworzymy katalog o nazwie `documentation`
27. W katalogu `src/test/documentation` tworzymy katalog o nazwie `endpoints`
28. W katalogu `src/test/documentation/endpoints` tworzymy katalog o nazwie `boards` (zgodnie ze strukturą dokumentacji API)
29. W katalogu `src/test/documentation/endpoints/boards` tworzymy plik o nazwie `POST_CreateBoard.md`
30. W przypadku słabego prowadzenia lub nawet braku głównej dokumentacji API w projekcie testerzy mogą w takich plikach
    prowadzić własne "notatki" np.:
    - Opis działania
    - Uwagi i informacje
    - URL
    - Obsługiwane parametry
    - Przykładowy payload
    - Przykładowy response
31. W katalogu `src/test/java` tworzymy katalog o nazwie `payloads`  
    **Wyjaśnienie:**  
    Nie każdy endpoint będzie miał osobny plik na payload/parametry.  
    W przypadku małej ilości parametrów dane te będą podawane jako argumenty na bieżąco w testach.
32. W katalogu `src/test/java/payloads` tworzymy katalog o nazwie `boards`
33. W katalogu `src/test/java/payloads/boards` tworzymy plik o nazwie `POST_CreateBoardPayload`
34. W pliku `POST_CreateBoardPayload` piszemy:
    - Zmienne/Parametry jakie posiada
    - Metodę pomocniczą, która konwertuje nasze dane na queryParams
    - Konstruktor dla Buildera
    - Gettery
    - Builder
    - Settery do ustawiania zmiennych w Builderze
    - Poniżej powycinane fragmenty kodu:
    ```java
    // Zmienne
    private final String name;
    
    // Helper przerabiający zmienne na queryParams
    public Map<String, Object> toQueryParams() {
        Map<String, Object> params = new HashMap<>();
    
        if (name != null) params.put("name", name);
        // CDN
    
        return params;
    }
    
    // Konstruktor
    private POST_CreateBoardPayload(Builder builder) {
        this.name = builder.name;
        // CDN
    }
    
    // Gettery
    public String getName() {
        return name;
    }
    // CDN
    
    // Builder
    public static class Builder {
        // Jego zmienne
        private String name;
        // CDN
    
        // Settery
        public Builder setName(String name) {
            this.name = name;
            return this;
        }
        // CDN
    
        // Build
        public POST_CreateBoardPayload build() {
            return new POST_CreateBoardPayload(this);
        }
    }
    ```
    - Przykład użycia
    ```java
    POST_CreateBoardPayload payload = new POST_CreateBoardPayload.Builder()
        .setName("Tablica API")
        .setDesc("Testowa tablica")
        .setDefaultLabels(false)
        .setPrefs_background("blue")
        .build();
    
    Map<String, Object> queryParams = payload.toQueryParams();
    ```
35. W katalogu z `endpoints` tworzymy plik `GET_GetBoard`:
    ```java
    package endpoints.boards;
    
    import base.TestBase;
    import io.restassured.response.Response;
    
    import static io.restassured.RestAssured.given;
    
    public class GET_GetBoard extends TestBase {
    
        private static final String url = "/boards";
    
        public static Response getGetBoard(String id) {
            return given().
                        spec(requestSpecificationCommon).
                    when().
                        get(url + "/" + id).
                    then().
                        extract().
                        response();
        }
    }
    ```
36. W katalogu z `endpoints` tworzymy plik `DELETE_DeleteBoard`:
    ```java
    package endpoints.boards;
    
    import base.TestBase;
    import io.restassured.response.Response;
    
    import static io.restassured.RestAssured.given;
    
    public class DELETE_DeleteBoard extends TestBase {
    
        private static final String url = "/boards";
    
        public static Response deleteDeleteBoard(String id) {
            return given().
                        spec(requestSpecificationCommon).
                    when().
                        delete(url + "/" + id).
                    then().
                        extract().
                        response();
        }
    }
    ```
37. Mając przygotowanego naszego pierwszego mini CRUD'a w katalogu `src/test/java` tworzymy katalog o nazwie `tests`
38. W nim tworzymy katalog o nazwie sekcji/kontrolera z dokumentacji. W tym przypadku `boards`
39. Następnie tworzymy plik `POST_CreateBoardTest`
40. W pliku `POST_CreateBoardTest` piszemy nasz pierwszy test:
   <🔴dokończyć>
41. W katalogu `src/test/java` tworzymy katalog o nazwie `expected_responses`
42. W katalogu tym tworzymy pod-katalog zgodny z układem w dokumentacji API, w tym przypadku `boards`
43. W katalogu tym tworzymy klasę z nazwą zgodną z endpointem, dla którego będziemy trzymać w niej oczekiwane respons'y,
    w tym przypadku `POST_CreateBoardExpected`
44. W klasie tej tworzymy zmienną typu String, w której umieszczamy nasz oczekiwany JSON pomiędzy takimi znakami `"""{json}"""` 
45. 🔴JsonUtils <dokończyć>

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

---

## 📄Struktura JSON – JsonSchema vs. DTO/POJO <a name="json_schema_dto_pojo"></a>

Świetne pytanie — i odpowiedź zależy głównie od **celu**, jaki chcesz osiągnąć (np. testy, walidacja, serializacja, dokumentacja API itp.).  
Poniżej przedstawiam **porównanie**: `JsonSchema` vs `DTO/POJO` — co to jest, do czego służy, różnice i kiedy co wybrać.

### 🔷 1. **POJO/DTO (Plain Old Java Object / Data Transfer Object)**

#### ✅ Co to:
- Klasa w Javie (lub inny obiekt w języku programowania) z polami, getterami/setterami, opcjonalnie adnotacjami
  (`@JsonProperty`, `@NotNull`, itd.).
- Używane do:
    - serializacji / deserializacji JSON ↔ obiekt Java (np. przez Jackson lub Gson),
    - walidacji danych (np. Hibernate Validator),
    - operacji na danych (np. logika biznesowa),
    - komunikacji między warstwami aplikacji.

#### ✅ Zalety:
- Typy statyczne – kompilator wykryje błędy.
- IDE podpowiada pola, adnotacje.
- Łatwe testowanie i refaktoryzacja.
- Lepsza integracja z frameworkami (Spring, Hibernate itp.).

#### ❌ Wady:
- Język-specyficzne (Java, Kotlin, itp.).
- Potrzeba rekompilacji po zmianach.
- Brak automatycznej walidacji struktury JSON bez dodatkowych bibliotek.

### 🔷 2. **JSON Schema**

#### ✅ Co to:
- Niezależny od języka opis **struktury i ograniczeń JSON-a**, w formacie JSON.
- Definiuje:
    - jakie pola są wymagane,
    - typy danych (`string`, `number`, `array`, itp.),
    - zakresy (`minimum`, `maxLength`),
    - struktury zagnieżdżone (`properties`, `definitions`),
    - wzorce walidacyjne (`pattern`, `enum`, itp.).

#### ✅ Zalety:
- Język-agnostyczny (można używać z dowolnym językiem).
- Doskonały do walidacji wejścia/wyjścia (np. w REST API).
- Może służyć jako kontrakt w API.
- Wspierany przez narzędzia takie jak:
    - OpenAPI / Swagger,
    - Postman, Insomnia,
    - JSON Schema Validator, ajv (JS), everit (Java), itp.

#### ❌ Wady:
- Brak bezpośredniej integracji z kodem (chyba że wygenerujesz klasy z JSON Schema).
- Mniej wygodne w edycji / refaktoryzacji niż kod Java.
- Trudniejsze w testowaniu logiki aplikacyjnej.

### 🔁 Różnice (head-to-head)

| Cecha                      | DTO/POJO (Java)          | JSON Schema                  |
|----------------------------|--------------------------|------------------------------|
| Format                     | Kod Java                 | JSON                         |
| Typowanie                  | Kompilator               | Runtime                      |
| Walidacja struktury        | Z adnotacjami lub ręczna | Automatyczna na wejściu      |
| Integracja z Spring        | Bardzo dobra             | Wymaga adapterów             |
| Przenośność                | Tylko Java               | Dowolny język                |
| Czy może być "kontraktem"? | Raczej nie               | Tak (np. dla API lub testów) |
| Narzędzia developerskie    | IDE, debug, refactor     | Lintery, generatory, CLI     |

### 🧠 Co wybrać?

| Potrzebujesz...                             | Wybierz         |
|---------------------------------------------|-----------------|
| Operować na danych w Javie                  | POJO/DTO        |
| Walidować strukturę danych (np. response)   | JSON Schema     |
| Tworzyć dokumentację REST API               | JSON Schema     |
| Obsługiwać wiele formatów językowych        | JSON Schema     |
| Testować backend + typowanie + refactor     | DTO/POJO        |

### 🔧 Pro tip:
Można łączyć oba podejścia:
- Używasz **DTO** w aplikacji,
- a do walidacji requestów/response’ów (np. w testach kontraktowych lub API Gateway) używasz **JSON Schema**.

---

## 📄Porównywanie JSON'ów – wyzwania, podejścia, praktyki <a name="json_compare_intro"></a>

**Link do źródła:**  
https://medium.com/@keployio/diff-json-a-complete-guide-to-comparing-json-data-7e536533c514

### 📌Uwagi

Tutaj, standardowe porównywanie JSON'ów zwraca wartość `true` lub `false`.  
W celu porównywania wraz ze zwracaniem różnic w konsoli należy użyć np. `JsonAssert`.

### 🗻Wyzwania w porównywaniu JSON

Porównywanie obiektów JSON może wydawać się proste, ale może być trudne, szczególnie w przypadku:

- **Struktury zagnieżdżone:** dane JSON mogą być głęboko zagnieżdżone, co sprawia, że ręczne porównywanie jest żmudne
i podatne na błędy.
- **Wrażliwość na kolejność:** tablice w formacie JSON są wrażliwe na kolejność, co oznacza, że [1,2] i [2,1] nie są równe,
nawet jeśli zawierają te same elementy.
- **Niezgodności typów danych:** Wartość zapisana jako „1” (ciąg) w jednym obiekcie JSON może wymagać porównania
z wartością 1 (liczba) w innym obiekcie.
- **Dynamiczne struktury danych:** Gdy dane JSON często się zmieniają (np. odpowiedzi API), śledzenie różnic może być skomplikowane.

Wyzwania te podkreślają potrzebę stosowania skutecznych narzędzi do porównywania plików JSON lub niestandardowej logiki porównawczej.

### 🔑Kluczowe podejścia do różnicowania danych JSON

Istnieje wiele sposobów porównywania danych JSON w zależności od przypadku użycia i wymaganego poziomu precyzji:

1. **Strict Equality Comparison:**  
   To podejście zapewnia **dokładne dopasowanie** kluczy, wartości i typów danych. Jest przydatne w sytuacjach, w których
   nawet drobne zmiany mają znaczenie, takich jak testowanie API.
2. **Porównanie strukturalne:**  
   Tutaj struktura (tj. hierarchia kluczy) jest porównywana bez skupiania się na konkretnych wartościach.
   Jest to przydatne, gdy **układ ma większe znaczenie** niż rzeczywiste dane, takie jak walidacja schematu.
3. **Częściowe porównanie:**  
   W tym podejściu **porównywane są tylko określone klucze lub pola.** Jest to korzystne w przypadku dynamicznych
   odpowiedzi JSON, w których weryfikacji wymagają tylko niektóre części (np. kody statusu).

Wybór odpowiedniego podejścia gwarantuje, że porównanie JSON będzie zgodne ze szczególnymi wymaganiami danego zadania.

### 👨‍💻Jak porównać JSON za pomocą kodu

Przykład w Javie (z wykorzystaniem Jacksona):
```java
ObjectMapper mapper = new ObjectMapper (); 
JsonNode json1 = mapper.readTree( "{ \" name \" : \" Alice \" , \" age \" :25}" ); 
JsonNode json2 = mapper.readTree( "{ \" name \" : \" Alice \" , \" age \" :30}" ); 
boolean isEqual = json1.equals(json2); 
System .out.println( "Czy pliki JSON są równe? "  + isEqual);
```

### 🏆Najlepsze praktyki dotyczące różnic JSON

Aby zapewnić wiarygodność porównania JSON, postępuj zgodnie z poniższymi najlepszymi praktykami:

- **Ignoruj kolejność, jeśli to możliwe:** Jeśli kolejność nie ma znaczenia, unikaj ścisłego porównywania tablic,
  aby zapobiec niepotrzebnym niezgodnościom.
- **Odpowiednie obchodzenie się z polami opcjonalnymi:** stosuj tolerancyjną logikę porównawczą, aby uwzględnić pola
  opcjonalne lub struktury dynamiczne.
- **Skuteczne rejestrowanie różnic:** W przypadku wykrycia różnic **należy je wyraźnie zarejestrować,** co ułatwi rozwiązywanie
  problemów.
- **Zautomatyzuj porównywanie JSON:** Zintegruj narzędzia lub biblioteki do porównywania JSON z **procesami CI/CD** w celu
  automatycznego testowania i walidacji.

Przestrzeganie tych zasad pomoże Ci uniknąć typowych pułapek i usprawnić Twój przepływ pracy.

---

## 📄Porównywanie JSON'ów – ObjectMapper <a name="json_compare_object_mapper"></a>

`ObjectMapper` to **klasa z biblioteki Jackson (`com.fasterxml.jackson.databind`)**, która służy do **konwersji między
obiektami Java a JSON-em**.

### 🔁 Główne zastosowania `ObjectMapper`:

| Czynność                     | Co robi                                                  |
|------------------------------|----------------------------------------------------------|
| `readValue()` / `readTree()` | 📥 Parsuje JSON → na obiekt Java (`POJO`) lub `JsonNode` |
| `writeValueAsString()`       | 📤 Obiekt Java → na JSON jako `String`                   |
| `writeValue()`               | 📝 Obiekt Java → zapisany bezpośrednio do pliku          |

### ✅ Przykłady

#### 1. **JSON na obiekt Java (deserializacja)**

```java
String json = "{\"name\":\"Test Board\",\"closed\":false}";

ObjectMapper mapper = new ObjectMapper();
Board board = mapper.readValue(json, Board.class);
```

#### 2. **Obiekt Java na JSON (serializacja)**

```java
Board board = new Board("Test Board", false);

String json = mapper.writeValueAsString(board);
// => {"name":"Test Board","closed":false}
```

#### 3. **JSON jako drzewo (JsonNode)** – przydatne np. do testów

```java
JsonNode node = mapper.readTree(json);
String name = node.get("name").asText();  // => "Test Board"
```

### ✅ Dlaczego `ObjectMapper` jest fajny?

- Bardzo elastyczny
- Obsługuje adnotacje (`@JsonProperty`, `@JsonIgnore`, itd.)
- Możesz nim parsować JSON **nawet bez tworzenia klasy modelowej**, np. do `Map`, `List`, `JsonNode`
- Używany w **Springu**, RestAssured, testach, itp.

---

## 📄Porównywanie JSON'ów – JsonNode <a name="json_compare_json_node"></a>

### 🔍 `JsonNode` – co to jest?

`JsonNode` to **reprezentacja struktury JSON-a w Javie** – konkretnie w bibliotece **Jackson**
(`com.fasterxml.jackson.databind.JsonNode`). Dzięki niej możesz **parsować, odczytywać, modyfikować i porównywać dane
JSON-owe** jak obiekty, bez potrzeby mapowania ich na klasy POJO.

### ✅ Przykład działania:

```java
ObjectMapper mapper = new ObjectMapper();
String json = "{ \"name\": \"Ala\", \"age\": 25 }";

// Parsowanie na JsonNode
JsonNode rootNode = mapper.readTree(json);

// Odczyt pól
String name = rootNode.get("name").asText();   // "Ala"
int age = rootNode.get("age").asInt();         // 25
```

### 🔧 Co można z tym robić?

| Co chcesz zrobić          | Jak to wygląda w kodzie                        |
|---------------------------|------------------------------------------------|
| Parsować JSON jako drzewo | `JsonNode node = mapper.readTree(jsonString);` |
| Odczytać wartość          | `node.get("field").asText()`                   |
| Iterować po polach        | `node.fields()` albo `node.fieldNames()`       |
| Usuwać pola               | `((ObjectNode) node).remove("id")`             |
| Porównywać JSON-y         | `node1.equals(node2)`                          |
| Zmieniać JSON             | `((ObjectNode) node).put("key", "newValue")`   |

### 🔁 Porównywanie JSON-ów

To właśnie dzięki `JsonNode` możesz zrobić coś takiego:

```java
boolean areEqual = node1.equals(node2);
```

I to **porównuje całe struktury JSON**, nie tylko tekst.

### 🌪 Iterowanie po tablicach, filtrowanie po wartościach, zmienianie zagnieżdżonych danych

#### ✅ 1. Parsowanie JSON-a

```java
String json = """
{
  "name": "Ala",
  "age": 25,
  "skills": ["Java", "REST", "JSON"]
}
""";

ObjectMapper mapper = new ObjectMapper();
JsonNode rootNode = mapper.readTree(json);
```

#### 🔍 2. Odczyt wartości

```java
String name = rootNode.get("name").asText();       // "Ala"
int age = rootNode.get("age").asInt();             // 25
JsonNode skillsNode = rootNode.get("skills");
```

#### 🔁 3. Iteracja po tablicy (`ArrayNode`)

```java
for (JsonNode skill : skillsNode) {
    System.out.println("Skill: " + skill.asText());
}
// Output:
// Skill: Java
// Skill: REST
// Skill: JSON
```

#### 🧭 4. Iteracja po polach obiektu

```java
Iterator<Map.Entry<String, JsonNode>> fields = rootNode.fields();
while (fields.hasNext()) {
    Map.Entry<String, JsonNode> field = fields.next();
    System.out.println(field.getKey() + ": " + field.getValue());
}
```

#### 🧹 5. Usuwanie pola

```java
((ObjectNode) rootNode).remove("age");
```

#### 🧪 6. Porównanie dwóch JSON-ów

```java
String json1 = "{\"name\": \"Ala\", \"age\": 25}";
String json2 = "{\"name\": \"Ala\", \"age\": 25}";

JsonNode node1 = mapper.readTree(json1);
JsonNode node2 = mapper.readTree(json2);

System.out.println(node1.equals(node2));  // true
```

#### 🧱 7. Dodawanie / modyfikowanie pól

```java
((ObjectNode) rootNode).put("age", 26);
((ObjectNode) rootNode).put("newField", "addedValue");
```

#### 📥 8. Ładowanie JSON-a z pliku

```java
JsonNode jsonFromFile = mapper.readTree(new File("src/test/resources/response.json"));
```

---

## 📄REST Assured – asercja dla pustego obiektu <a name="rest_assured_assert_empty_object"></a>

Response zwraca taki oto pusty obiekt:
```json
"limits": {
    
}
```

Taki test nie przejdzie:
```java
responsePost.then().body("limits", equalTo("{}"));
```

Asercja zwróci takie coś:
```java
java.lang.AssertionError: 1 expectation failed.
JSON path limits doesn't match.
Expected: {}
  Actual: <{}>
```

Ten problem wynika z nieprecyzyjnego porównania pustego obiektu `{}` w JSON-ie przy pomocy `equalTo("{}")`, co
**porównuje typy niewłaściwie** — `equalTo("{}")` porównuje pusty obiekt z literalnym stringiem `"{}"`.

✅ **Rozwiązanie: użyj pustej mapy zamiast stringa**

Zamiast porównywać do `"{}"` jako stringa, porównaj do **pustej mapy**, co dokładnie odwzorowuje pusty obiekt JSON:
```java
import java.util.Collections;

responsePost.then().body("limits", equalTo(Collections.emptyMap()));
```

🔍 Dlaczego to działa?

W JSON:
```json
"limits": {}
```

To jest pusty obiekt → w Javie odwzorowuje się jako `Map<String, Object>`.

Używając `equalTo("{}")`, porównujesz `Map` z `String`, co nigdy nie przejdzie (nawet jeśli zawartość wygląda na taką samą).
