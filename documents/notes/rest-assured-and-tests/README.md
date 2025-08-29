# 🌐REST Assured i testy – notatki

# 📑Spis treści

- [START – rozpoczęcie pisania testów](#start_writing_tests)
  - [Dostępy](#start_wt_accesses)
  - [config.properties](#start_wt_config_p)
  - [.env](#start_wt_env)
  - [Config](#start_wt_config)
  - [BaseUrlBuilder](#start_wt_base_url)
  - [RequestSpecConfig](#start_wt_request_spec)
  - [TestBase](#start_wt_test_base)
  - [UtilsCompare (compare objects)](#start_wt_utils_compare)
  - [Endpoints](#start_wt_endpoints)
  - [Payloads](#start_wt_payloads)
  - [Endpoints – pozostałe](#start_wt_endpoints_others)
  - [Test – mały](#start_wt_test_small)
  - [Expected responses](#start_wt_expected_responses)
  - [UtilsResponse (DTO)](#start_wt_utils_response)
  - [DTO](#start_wt_dto)
  - [Utils Tests](#start_wt_utils_tests)
  - [Test – ostateczny](#start_wt_test_final)
  - [Dokumentacja](#start_wt_documentation)
- [Boolean – testy](#boolean_tests)
- [RequestSpecification](#request_specification)
- [RestAssured.filters()](#rest_assured_filters)
- [RequestSpecBuilder](#request_spec_builder)
- [RestAssured.enableLoggingOfRequestAndResponseIfValidationFails()](#enable_log_fail)
- [RecursiveComparisonConfiguration()](#assertj_recursive_comparison_configuration)
- [Rest Assured – przykłady pisania testów z kursu Sii](#rest_assured_test_examples_from_course)
- [ID – czyszczenie zmiennej po wysłaniu DELETE](#id_clean_after_delete)
- [UtilsCompare.java – opis kodu](#utils_compare_java)
- [UtilsResponse.java – opis kodu](#utils_response_java)
- [DTO – opcjonalne parametry](#dto_optional_parameters)
- [Struktura JSON – JsonSchema vs. DTO/POJO](#json_schema_dto_pojo)
- [Porównywanie JSON'ów – wyzwania, podejścia, praktyki](#json_compare_intro)
- [Porównywanie JSON'ów – ObjectMapper](#json_compare_object_mapper)
- [Porównywanie JSON'ów – JsonNode](#json_compare_json_node)
- [REST Assured – asercja dla pustego obiektu](#rest_assured_assert_empty_object)

---

# 📝Opis

## 📄START – rozpoczęcie pisania testów <a name="start_writing_tests"></a>

### Dostępy <a name="start_wt_accesses"></a>

1. Zakładamy `konta` i inne `dostępy`
    - W przypadku tego projektu zakładamy `konto` oraz zdobywamy `API key` oraz `token` na stronie **Trello**
    - Szczegóły w `README` katalogu `📂trello-configuration`

### config.properties <a name="start_wt_config_p"></a>

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

### .env <a name="start_wt_env"></a>

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

### Config <a name="start_wt_config"></a>

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

### BaseUrlBuilder <a name="start_wt_base_url"></a>

11. W katalogu `src/main/java/configuration` tworzymy plik `BaseUrlBuilder`
12. W pliku `BaseUrlBuilder` piszemy budowanie naszego URL ze zmiennych konfiguracyjnych projektu

### RequestSpecConfig <a name="start_wt_request_spec"></a>

13. W katalogu `src/test/java` tworzymy katalog package o nazwie `configuration`
14. W katalogu `src/test/java/configuration` tworzymy plik `RequestSpecConfig`  
    Dlaczego tutaj, a nie w `main`?  
    Ponieważ `REST Assured` jest używane tylko do testów i jego specyfikacja tak zaleca.  
    Żeby obejść to ograniczenie można też w `pom.xml` usunąć wiersz z `<scope>test</scope>`.
15. W pliku `RequestSpecConfig` piszemy naszą wspólną konfigurację dla wszystkich requestów oraz metodę ją zwracającą

### TestBase <a name="start_wt_test_base"></a>

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

### UtilsCompare (compare objects) <a name="start_wt_utils_compare"></a>

19. W katalogu `src/test/java` tworzymy katalog o nazwie `utils`  
    Dlaczego w test? Ponieważ `AssertJ` ma ustawiony <scope> na ten katalog w `pom.xml`.  
    Można by go usunąć, ale jak tak zalecają to lepiej nie ruszać.
20. W katalogu `src/test/java/utils` tworzymy plik o nazwie `UtilsCompare`
21. W pliku `UtilsCompare` tworzymy metodę, która bazując na dependency `AssertJ` będzie:
    - porównywać obiekty
    - a jeśli podamy jako `String` parametry np. `"id"` to będą one pomijane przy porównywaniu

### Endpoints <a name="start_wt_endpoints"></a>

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
    - Deklarujemy zmienną `url`, która będzie zawierała nasz endpoint np. `/boards` 
    - Tworzymy metodę lub metody wywołujące ten request i używające jako argumentów podawanych przez nas parametrów, lub
      payloadów

### Payloads <a name="start_wt_payloads"></a>

26. W katalogu `src/test/java` tworzymy katalog o nazwie `payloads`  
    **Wyjaśnienie:**  
    - Nie każdy endpoint będzie miał osobny plik na payload/parametry.  
    - W przypadku małej ilości parametrów dane te będą podawane jako argumenty na bieżąco w testach.
27. W katalogu `src/test/java/payloads` tworzymy katalog o nazwie `boards`
28. W katalogu `src/test/java/payloads/boards` tworzymy plik o nazwie `POST_CreateBoardPayload`
29. W pliku `POST_CreateBoardPayload` piszemy:
    - Zmienne/Parametry, jakie posiada
    - Metodę pomocniczą, która konwertuje nasze dane na `queryParams`
    - Konstruktor dla Buildera
    - Gettery
    - Builder
    - Settery do ustawiania zmiennych w Builderze
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

### Endpoints – pozostałe <a name="start_wt_endpoints_others"></a>

30. W katalogu z `endpoints` tworzymy plik `GET_GetBoard`  
    Aby sprawdzać, czy dane dodawane przez POST rzeczywiście są prawidłowe
31. W katalogu z `endpoints` tworzymy plik `DELETE_DeleteBoard`  
    Aby pod koniec testu usuwać zasób dodawany przez POST

### Test – mały <a name="start_wt_test_small"></a>

32. Mając przygotowanego naszego pierwszego mini CRUD'a w katalogu `src/test/java` tworzymy katalog o nazwie `tests`
33. W nim tworzymy katalog o nazwie sekcji/kontrolera z dokumentacji. W tym przypadku `boards`
34. Następnie tworzymy plik `POST_CreateBoardTest`
35. (Opcjonalne) W pliku `POST_CreateBoardTest` piszemy najprostszy, byle jaki test-request, aby móc skopiować zwracany
    response (jeśli nie ma takiego w dokumentacji)
    ```java
    public class POST_CreateBoardTest extends TestBase {
    
        private static String boardId;
    
        @Test
        public void shouldCreateBoard() {
    
            // POST
            response = POST_CreateBoard.postCreateBoard("Nazwa tablicy 1", null);
            boardId = response.jsonPath().getString("id");
            // GET
            response = GET_GetBoard.getGetBoard(boardId);
            // DELETE
            response = DELETE_DeleteBoard.deleteDeleteBoard(boardId);
        }
    }
    ```

### Expected responses <a name="start_wt_expected_responses"></a>

36. W katalogu `src/test/java` tworzymy katalog o nazwie `expected_responses`
37. W katalogu tym tworzymy pod-katalog zgodny z układem w dokumentacji API, w tym przypadku `boards`
38. W katalogu tym tworzymy klasę z nazwą zgodną z endpointem, dla którego będziemy trzymać w niej oczekiwane respons'y,
    w tym przypadku `POST_CreateBoardExpected`
39. W klasie tej tworzymy zmienną typu String, w której umieszczamy nasz oczekiwany JSON pomiędzy takimi znakami `"""{json}"""`

### UtilsResponse (DTO) <a name="start_wt_utils_response"></a>

40. W katalogu `src/test/java/utils` tworzymy plik `UtilsResponse`
41. W pliku `UtilsResponse` definiujemy:
    - Ustawienia deserializacji:
      - obiekt `objectMapper` z biblioteki `Jackson`
      - ustawienia tego obiektu:
        - żeby deserializacja wywalała się, gdy pojawią się jakieś nadmiarowe pola
        - żeby deserializacja wywalała się, gdy brakuje jakichś oczekiwanych pól (wymaga @JsonCreator w DTO)
        - żeby deserializacja wywalała się, gdy w prymitywnych parametrach wystąpi `null`:
          - W Javie prymitywy (np. int, boolean, double) nie mogą przyjmować wartości `null`
          - Gdy nie ma włączonego tego ustawienia, to `null` dla takiego `int` jest zamieniane na `0`
          - Gdy chcemy, aby taki prymityw mógł być `null'em` należy zrobić tak:
            - Zamiast: `int id;`
            - Wpisać: `Integer id`
            - Pozostałe klasy opakowujące: `Integer, Boolean, Double` itp.
    - Metody deserializujące i walidujące obiekty:
      - deserializacja i walidacja obiektu typu `Response`
      - deserializacja i walidacja obiektu typu `String`
      - metoda do samej deserializacji JSON (String)
    - Metody deserializujące i walidujące listy obiektów:
      - deserializacja i walidacja listy obiektów typu `Response`
      - deserializacja i walidacja listy obiektów typu `String`
      - metoda do samej deserializacji JSON, który jest listą obiektów (String)
    - Metodę do walidacji obiektów DTO z biblioteki `Jakarta`

### DTO <a name="start_wt_dto"></a>

42. W katalogu `src/test/java` tworzymy katalog o nazwie `dto`
43. W katalogu `src/test/java/dto` tworzymy katalog zgodny z nazwą grupy endpointów w dokumentacji np. `boards`
44. W katalogu `src/test/java/dto/boards` jeśli zwracane odpowiedzi z naszego CRUD'a różnią się ilością parametrów,
    ale mają większość elementów wspólnych, to tworzymy plik, który będzie najpierw przechowywał te elementy wspólne
    np. `BoardBaseDto`
45. Wklejamy w `Czat GPT` nasz wcześniej skopiowany respons'e oraz dopisujemy, jakie są warunki dla pól, jeśli takie znamy
    i prosimy go o przerobienie tego na DTO.  
    Podajemy:
    - informację, że chcemy to na DTO
    - response
    - warunki dla pól
    - wszystkie pola mają być wymagane
    - ma być wykrywany brak jakiegoś pola
    - ma być wykrywane, jeśli pojawią się jakieś nadmiarowe pola
46. Takie DTO składa się z:
    - Adnotacji walidujących z biblioteki Jakarta
      - `@NotNull`
      - `@Pattern(regexp = "^[0-9a-fA-F]{24}$")`
      - `@Size(min = 1, max = 16384, message = "'name' must be between {min} and {max} characters long")`
    - Zmiennych/Parametrów
    - Konstruktora, który jest opakowany w `@JsonCreator` służącego do sprawdzania, czy nie brakuje jakiegoś pola np.:
      ```java
          @JsonCreator
          public BoardBaseDto(
                  @JsonProperty(value = "id", required = true) String id,
      ```
    - Pusty konstruktor, który jest potrzebny do przypisywania wartości ręcznie np.  
      `obiekt1.name = obiekt2.name;`
47. Jeśli response ma w sobie inne klasy/obiekty to na nie też zakładamy osobne DTO.  
    Najlepiej w jakimś wspólnym katalogu np. `board`
48. Jeśli jakiś obiekt/klasa ma w sobie kolejny obiekt/klasę to wewnątrz tego zakładamy kolejny katalog np. `prefs`
49. Mając bazowe DTO, robimy teraz DTO dla respons'ów konkretnych endpointów:
    - `POST_CreateBoardDto`
    - `GET_GetBoardDto`
50. Response `GET` nie ma żadnych dodatkowych pól, więc po prostu dziedziczy po bazowym DTO, ALE musimy do konstruktora
    kopiować wartości związane z `@JsonCreator`
51. Response `POST` ma jedno dodatkowe pole `limits`, więc musimy je dopisać ORAZ do konstruktora skopiować wartości
    związane z `@JsonCreator`.  
    Dodatkowo, jeśli przy porównywaniu responsów będziemy chcieli pomijać jakieś pola, to żeby uniknąć podawania ich
    jako `String` (wtedy trzeba będzie ręcznie dokonywać jego aktualizacji w każdym miejscu występowania) warto je
    w tym DTO zapisywać jako zmienne np. `public static final String FIELD_LIMITS = "limits";` dzięki czemu jak je tak
    wywołamy `compareObjects(responsePostDto, responseGetDto, POST_CreateBoardDto.FIELD_LIMITS);` to jak coś się tu zmieni,
    wtedy IDE dokona tej zmiany wszędzie.

### Utils Tests <a name="start_wt_utils_tests"></a>

52. W `src/test/java` tworzymy katalog `utils_tests`  
    Katalog ten będzie służył do zbierania metod pomocniczych dla konkretnych klas z testami.
53. W `scr/test/java/utils_tests` tworzymy plik `POST_CreateBoardUtils`
54. W pliku `POST_CreateBoardUtils` dodajemy takie metody jak:
    - Metoda przygotowująca oczekiwany response POST
      - Przerabia (tylko deserializacja, bez walidacji) nasz oczekiwany String z responsem na obiekt DTO
      - Zrównuje różniące się zazwyczaj pola np.:
        - `expectedResponsePostDto.name = boardName;`
        - `expectedResponsePostDto.id = responsePostDto.id;`
      - I tak przygotowany obiekt jest zwracany i gotowy do porównywania w asercji
    - Metodę do weryfikacji zgodności naszego POST z requestem GET:
      - Wysyłany jest request GET
      - Sprawdzany jest status code
      - Response jest deserializowane i walidowane na obiekt DTO
      - Porównywany jest obiekt response POST z obiektem response GET oraz pomijane są pola, których nie chcemy porównywać
    - Metodę generującą losową nazwę tablicy
      - Dzięki `nanoTime()` jest mniejsza szansa na duplikację niż przy użyciu `number().randomNumber()`

### Test – ostateczny <a name="start_wt_test_final"></a>

55. W katalogu `src/test/java/tests/boards` otwieramy nasz plik z pierwszymi testami o nazwie `POST_CreateBoardTest`
56. Nad nazwą klasy piszemy `@TestInstance(TestInstance.Lifecycle.PER_METHOD)`  
    Oznacza, że JUnit 5 będzie tworzył nową instancję klasy testowej dla każdego testu (metody testowej).
57. Na całą klasę deklarujemy zmienne, jakich będziemy re-używać np. ID. W tym przypadku `private String boardId;`
58. Piszemy metodę `tearDown()` z adnotacją `@AfterEach`
    - Zawsze, po każdym teście będzie wywoływana i odpowiedzialna za sprzątanie/usuwanie zasobu (tablicy)
    - Sprawdza, czy `boardId` jest różna `null`
    - Jeśli tak, to wysyłany jest request DELETE pod to ID
    - Sprawdzane jest, czy status code = 200
59. Dodajemy pierwszy test o nazwie `P1_shouldCreateBoardWithOnlyRequiredParameters()` z adnotacją `@Test`
    - Stwierdziłem, że fajnie będzie oznaczać jakoś testy np. w przypadku mierzenia pokrycia, wiedzieć który test co pokrywa
    - `P1, P2, P3` itd. oznaczenie dla testów pozytywnych
    - `N1, N2, N3` itd. oznaczenie dla testów negatywnych
60. W teście `P1_shouldCreateBoardWithOnlyRequiredParameters()` piszemy następujące rzeczy:
    - Na samej górze deklarujemy zmienne np. losową nazwę tablicy
    - Wysyłamy request POST wraz z parametrami/body i zapisujemy do zmiennej typu `response`
    - Sprawdzamy `status code`
    - Zapisujemy `ID` zasobu do zmiennej
    - Deserializujemy i walidujemy ten response na obiekt DTO `POST_CreateBoardDto`
    - Przygotowujemy oczekiwany response POST:
      - Importujemy go jako String
      - Deserializujemy do DTO `POST_CreateBoardDto`
      - Dla pól, które zawsze się różnią przypisujemy tutaj te z response POST np. `expectedResponsePostDto.id = responsePostDto.id;`
    - Porównujemy oba obiekty
    - Wysyłamy request GET, który jako metoda pomocnicza sprawdza zgodność z responsem POST
    - Metoda `tearDown()` z adnotacją `@AfterEach` automatycznie usuwa stworzony zasób wysyłając request DELETE

### Dokumentacja <a name="start_wt_documentation"></a>

61. Przygotowujemy sobie dokumentację testową dla danego requesta/ednpointa
62. W katalogu `src/test` tworzymy katalog o nazwie `documentation`
63. W katalogu `src/test/documentation` tworzymy katalog o nazwie `endpoints`
64. W katalogu `src/test/documentation/endpoints` tworzymy katalog o nazwie `boards` (zgodnie ze strukturą dokumentacji API)
65. W katalogu `src/test/documentation/endpoints/boards` tworzymy plik o nazwie `POST_CreateBoard.md`
66. W przypadku słabego prowadzenia lub nawet braku głównej dokumentacji API w projekcie testerzy mogą w takich plikach
    prowadzić własne notatki np.:
    - Metoda – nazwa endpointu
    - Endpoint (URL)
    - Opis
    - Ważne notatki i uwagi
    - Pokrycie testami:
      - Wklejamy cały payload lub listę wszystkich możliwych parametrów, jakie możemy podać w body
      - Pod każdym parametrem tworzymy sekcję na przypadki pozytywne i negatywne
      - Rozpisujemy wszystkie możliwe przypadki, jakie możemy podać w ramach testów
      - Przed każdym z nich wpisujemy oznaczenie testu, który pokrywa dany przypadek np. `[P1] Podanie tylko tego, wymaganego parametru`
      - Dla GET'ów, na które mogą mieć wpływ różne kombinacje endpointów/danych wklejamy response
      - I tu również pod każdym parametrem rozpisujemy przypadki testowe, czyli możliwe dane, jakie mogą/powinny wpadać
    - Query params / Payload
    - Response

---

## 📄Boolean – testy <a name="boolean_tests"></a>

Jeżeli pole nie jest związane z czymś wrażliwym lub Security, to sprawdzamy wartości:
- true
- false
- null
- brak

Jeżeli pole jest związane z czymś wrażliwym lub Security, to sprawdzamy wartości:
- true
- false
- null
- brak
- 1
- ""
- "tekst"
- []
- {}

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

## 📄Rest Assured – przykłady pisania testów z kursu Sii <a name="rest_assured_test_examples_from_course"></a>

### Przykład 1

Przypisujemy cały JSON/body do zmiennej, a następnie wstawiamy ją w requeście i piszemy w nim asercje.

```java
private String body = """
    {
            "name": "Mateusz Tadla",
            "username": "mtadla",
            "email": "mtadl@april.biz",
            "address": {
                "street": "Kulas Light",
                "suite": "Apt. 556",
                "city": "Lublin",
                "zipcode": "92998-3874",
                "geo": {
                    "lat": "-37.3159",
                    "lng": "81.1496"
                }
            },
            "phone": "1-770-736-8031 x56442",
            "website": "hildegard.org",
            "company": {
                "name": "Romaguera-Crona",
                "catchPhrase": "Multi-layered client-server neural-net",
                "bs": "harness real-time e-markets"
            }
    }
    """;

@Test
public void shouldCreateNewUser() {
    given().
        body(body).
        contentType(ContentType.JSON).
        baseUri(baseUrl).
    when().
        post(users).
    then().
        statusCode(201)
        .body("id", equalTo(11))
        .body("address.city", equalTo("Lublin"));
}
```

### Przykład 2

Tworzenie/Przygotowanie JSON'a za pomocą HashMap'y.  
Wymagana biblioteka **Jackson**.

Więcej tutaj:  
https://github.com/rest-assured/rest-assured/wiki/Usage#content-type-based-serialization

```java
@Test
public void shouldCreateNewUserV2() {

    Map<String, Object> address = new HashMap<>();
    address.put("street", "Warszawska");
    address.put("city", "Lublin");
    
    Map<String, Object> user = new HashMap<>();
    user.put("name", "Mateusz Tadla");
    user.put("username", "mtadla");
    user.put("email", "mtadl@april.biz");
    user.put("address", address);
    
    given().
            body(user).
            contentType(ContentType.JSON).
            baseUri(baseUrl).
    when().
            post(users).
    then().
            statusCode(201).
            body("id", equalTo(11)).
            body("username", equalTo("mtadla")).
            body("address.city", equalTo("Lublin"));
}
```

### Przykład 3

Tworzenie JSON'a za pomocą przygotowanych wcześniej **Builder'ów**.

#### Tworzymy Buildery

Używanie **Lombok'a** nie jest zalecane, ponieważ generuje wiele rzeczy podczas kompilacji i zawsze jest ryzyko, że coś się zepsuje podczas tego.

```java
package models.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Address {
    private String street;
    private String suite;
    private String city;
    private String zipcode;
    private Geo geo;
}
```

```java
package models.user;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
   private int id;
   private String name;
   private String username;
   private String email;
   private Address address;
   private String phone;
   private String website;
   private Company company;
}
```

#### Używamy ich do przygotowania naszego JSON'a

```java
@Test
public void shouldCreateNewUserV3() {

    Address address = Address.builder()
            .city("Lublin")
            .street("Warszawska")
            .build();

    User user = User.builder()
            .name("Mateusz Tadla")
            .email("mtadl@april.biz")
            .username("mtadla")
            .address(address)
            .build();
    
    given().
            body(user).
            contentType(ContentType.JSON).
            baseUri(baseUrl).
    when().
            post(users).
    then().
            statusCode(201).
            body("id", equalTo(11)).
            body("username", equalTo("mtadla")).
            body("address.city", equalTo("Lublin"));
}
```

### Przykład 4

Tworzenie JSON'a za pomocą przygotowanych wcześniej **Builder'ów**, ale mamy z nich utworzone metody, które tworzą już gotowe obiekty.

#### Tworzymy metody z builder'ów

```java
package provier;

import models.user.Address;

public class AddressProvider {
    public static Address getFullAddress() {
        return
                Address.builder()
                        .street("Main St")
                        .suite("Apt. 1")
                        .city("Example City")
                        .zipcode("12345")
                        .geo(GeoProvider.getFullGeo())
                        .build();
    }
}
```

```java
package provier;

import models.user.Company;

public class CompanyProvider {
    public static Company getFullCompanyData() {
        return
                Company.builder()
                        .name("Example Company")
                        .catchPhrase("We lead, others follow.")
                        .bs("innovative solutions")
                        .build();
    }
}
```

```java
package provier;

import io.UserProperty;
import models.user.User;

public class UserProvider {
    public static User getFullUserData() {
        return
                User.builder()
                        .name(UserProperty.get("name"))
                        .username(UserProperty.get("username"))
                        .email(UserProperty.get("email"))
                        .address(AddressProvider.getFullAddress())
                        .phone(UserProperty.get("phone"))
                        .website(UserProperty.get("website"))
                        .company(CompanyProvider.getFullCompanyData())
                        .build();
    }
}
```

#### Używamy ich do przygotowania naszego JSON'a

```java
@Test
public void shouldCreateNewUserV6() {
    
    User expectedUser = UserProvider.getFullUserData();

    User reponseUser =
            given().
                    body(expectedUser).
                    contentType(ContentType.JSON).
                    baseUri(baseUrl).
            when().
                    post(users).
            then().
                    statusCode(201)
                    .extract()
                    .as(User.class);

    expectedUser.setId(reponseUser.getId());
    assertThat(reponseUser, equalTo(expectedUser));
}
```

### Przykład 5

Przerabianie payloadu i response'a na DTO, by następnie móc porównywać oba obiekty i odnosić się do ich parametrów za
pomocą getterów i setterów lub ich zmiennych/parametrów bezpośrednio.

```java
@Test
public void shouldCreateNewUserV7() {
    
    User expectedUser = UserProvider.getFullUserData();

    User reponseUser =
            given().
                    body(expectedUser).
                    contentType(ContentType.JSON).
                    baseUri(baseUrl).
            when().
                    post(users).
            then().
                    statusCode(201)
                    .extract()
                    .as(User.class);
    
    // Poniżej 3 linijki specjalnie edytują reponseUser, aby sfailować compare
    reponseUser.getAddress().getGeo().setLat("123123123312123");
    reponseUser.setName("qweqewqe");
    reponseUser.getCompany().setName("qwe");
    compareObjectWithoutId(reponseUser, expectedUser);
}
```

---

## 📄ID – czyszczenie zmiennej po wysłaniu DELETE <a name="id_clean_after_delete"></a>

**Miałem taki kod:**

```java
private String boardId;
private String trelloId = Config.getTrelloId();

@BeforeEach
public void setUpCreateBoard() {
    responsePost = postCreateBoard(generateRandomBoardName(), null);
    assertThat(responsePost.statusCode()).isEqualTo(200);
    boardId = responsePost.jsonPath().getString("id");
}

@AfterEach
public void tearDownDeleteBoard() {
    if (boardId != null) {
        responseDelete = deleteDeleteBoard(boardId);
        assertThat(responseDelete.statusCode()).isEqualTo(200);
    }
}
```

**Zapytałem czat GPT:**  
*Czy po wysłaniu requesta `DELETE` zmienna `boardId` nie powinna być jakoś "czyszczona" lub zmieniana na `null`?*

**Otrzymałem taką odpowiedź:**

Tak, masz rację — **dobrą praktyką** po wykonaniu `@AfterEach` (czyli `tearDownDeleteBoard`) byłoby **wyzerowanie
(`null`) zmiennej `boardId`**, szczególnie jeśli klasa testowa jest współdzielona między testami (np. w testach
parametryzowanych lub z różnymi instancjami).

Chociaż większość frameworków testowych (np. JUnit 5) **tworzy nową instancję klasy testowej dla każdego testu**, co
powoduje, że pola instancyjne jak `boardId` są odświeżane automatycznie — to:

🔹 **Jeśli kiedykolwiek zmienisz cykl życia klasy (`@TestInstance(Lifecycle.PER_CLASS)`) lub zaczniesz współdzielić
stan między testami** – wtedy brak czyszczenia `boardId` może powodować trudne do znalezienia błędy.

### ✅ Poprawiona wersja `tearDownDeleteBoard()`:

```java
@AfterEach
public void tearDownDeleteBoard() {
    if (boardId != null) {
        responseDelete = deleteDeleteBoard(boardId);
        assertThat(responseDelete.statusCode()).isEqualTo(200);
        boardId = null; // czyszczenie zmiennej
    }
}
```

### 🔎 Podsumowanie

* ✔ **Nie jest to konieczne w większości przypadków z JUnit 5**, ale:
* ✅ **Jest to dobra praktyka defensywna**, zwłaszcza jeśli testy będą rozwijane, refaktoryzowane lub cykl życia klasy zmieniony.
* 🧼 Pomaga też w utrzymaniu testów "czystych" i odpornych na przyszłe błędy.

---

## 📄UtilsCompare.java – opis kodu <a name="utils_compare_java"></a>

Poniżej znajduje się **szczegółowe omówienie** działania klasy `UtilsCompare` linia po linii:

### `package utils;`

```java
package utils;
```

* Definiuje pakiet, w którym znajduje się ta klasa.
* Oznacza, że klasa `UtilsCompare` należy do przestrzeni nazw `utils`, co ułatwia organizację kodu.

### Importy

```java
import org.assertj.core.api.Assertions;
import org.assertj.core.api.recursive.comparison.RecursiveComparisonConfiguration;
```

* **`Assertions`**: udostępnia statyczne metody do asercji, m.in. `.assertThat()`, używane przez AssertJ do porównań obiektów.
* **`RecursiveComparisonConfiguration`**: służy do konfiguracji sposobu porównywania obiektów w sposób rekurencyjny
(czyli porównywane są wszystkie pola i podpola).

### Deklaracja klasy

```java
public class UtilsCompare {
```

* Definiuje publiczną klasę narzędziową (utility class) o nazwie `UtilsCompare`.
* Można z niej korzystać z innych klas, jeśli są w tym samym projekcie lub mają dostęp do pakietu `utils`.

### Metoda `compareObjects`

```java
public static void compareObjects(Object actualObject, Object expectedObject, String... fieldsToIgnore) {
```

* **`public`**: metoda dostępna z innych klas.
* **`static`**: można ją wywołać bez tworzenia instancji `UtilsCompare`.
* **`Object actualObject` / `expectedObject`**: dwa obiekty dowolnego typu, które chcesz porównać.
* **`String... fieldsToIgnore`**: zmienna liczba argumentów — pola, które mają zostać pominięte w porównaniu.
Jeśli nic nie podasz, `fieldsToIgnore.length == 0`.

### Blok warunkowy: czy ignorować jakieś pola?

```java
if (fieldsToIgnore != null && fieldsToIgnore.length > 0) {
```

* Sprawdza, czy użytkownik przekazał jakiekolwiek pola do zignorowania.
* Zabezpieczenie przed `NullPointerException` oraz niepotrzebnym tworzeniem konfiguracji.

### Jeśli przekazano pola do pominięcia

```java
RecursiveComparisonConfiguration config = new RecursiveComparisonConfiguration();
config.ignoreFields(fieldsToIgnore);
```

* Tworzy nową konfigurację porównywania rekurencyjnego.
* **`ignoreFields(fieldsToIgnore)`**: dodaje pola, które mają być ignorowane podczas porównania (np. pola techniczne
typu `id`, `createdAt`, `limits` itp.).

```java
Assertions.assertThat(actualObject)
        .usingRecursiveComparison(config)
        .isEqualTo(expectedObject);
```

* Porównuje obiekt `actualObject` z `expectedObject`, **ignorując wskazane pola**.
* AssertJ porównuje wszystkie pola w sposób rekurencyjny (w tym zagnieżdżone obiekty).

### Jeśli **nie przekazano** pól do zignorowania

```java
} else {
    Assertions.assertThat(actualObject)
            .usingRecursiveComparison()
            .isEqualTo(expectedObject);
}
```

* W przypadku braku przekazanych pól do pominięcia:

    * Tworzy domyślną konfigurację (czyli: **porównuje wszystko**).
    * Wykonuje rekurencyjne porównanie każdego pola, bez wyjątków.

### Podsumowanie — co robi ta metoda?

* Porównuje dwa obiekty dowolnego typu, pole po polu (rekurencyjnie).
* Jeśli przekażesz nazwę pola (np. `"id"` lub `"limits"`), to je pominie.
* Idealne do porównywania DTO, gdzie niektóre pola (np. generowane przez serwer) mogą się różnić.

### Przykład użycia:

```java
UtilsCompare.compareObjects(actualDto, expectedDto, "id", "createdAt");
```

➡️ Porównuje wszystkie pola z wyjątkiem `"id"` i `"createdAt"`.

---

## 📄UtilsResponse.java – opis kodu <a name="utils_response_java"></a>

Poniżej znajduje się **szczegółowe omówienie działania klasy `UtilsResponse` linia po linii**. Klasa ta służy jako
narzędzie pomocnicze do deserializacji odpowiedzi REST (np. z biblioteki RestAssured) oraz do ich walidacji przy użyciu
JSR 380 (Bean Validation API – `jakarta.validation`).

### 📦 Pakiet i Importy

```java
package utils;
```

* Określa, że klasa należy do pakietu `utils`.

```java
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;

import java.util.List;
import java.util.Set;
```

* **`ObjectMapper`**: biblioteka Jackson do serializacji i deserializacji JSON.
* **`DeserializationFeature`**: umożliwia konfigurację deserializacji.
* **`TypeReference`**: pozwala na typy generyczne (np. `List<MyDto>`).
* **`Response`**: obiekt odpowiedzi z RestAssured.
* **`ConstraintViolation`**, **`Validation`**, **`Validator`**: służą do walidacji obiektów zgodnie z adnotacjami (np. `@NotNull`).
* **`Set`**, **`List`**: kolekcje Java.

### 🔧 Konfiguracja `ObjectMapper`

```java
private static final ObjectMapper objectMapper = new ObjectMapper()
        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true)
        .configure(DeserializationFeature.FAIL_ON_MISSING_CREATOR_PROPERTIES, true)
        .configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, true);
```

* Tworzy statyczny `ObjectMapper` używany do deserializacji.
* **`FAIL_ON_UNKNOWN_PROPERTIES` = true**: rzuć wyjątek, jeśli JSON zawiera pola, których nie ma w DTO.
* **`FAIL_ON_MISSING_CREATOR_PROPERTIES` = true**: rzuć wyjątek, jeśli DTO z `@JsonCreator` ma nieprzypisane pola.
* **`FAIL_ON_NULL_FOR_PRIMITIVES` = true**: rzuć wyjątek, jeśli pole typu prymitywnego (`int`, `boolean`, itd.) ma wartość `null`.

### 🔄 Deserializacja obiektów (JSON → DTO)

```java
public static <T> T deserializeAndValidate(Response response, Class<T> clazz) {
    return deserializeAndValidate(response.asString(), clazz);
}
```

* Wersja przyjmująca obiekt `Response`.
* Wyciąga JSON jako `String` i przekazuje dalej.

```java
public static <T> T deserializeAndValidate(String json, Class<T> clazz) {
    T dto = deserializeJson(json, clazz);
    validateDto(dto);
    return dto;
}
```

* Deserializuje JSON do obiektu typu `T`.
* Waliduje wynikowy obiekt DTO.
* Zwraca poprawny obiekt.

```java
public static <T> T deserializeJson(String json, Class<T> clazz) {
    try {
        return objectMapper.readValue(json, clazz);
    } catch (Exception e) {
        throw new RuntimeException("Error deserializing JSON to " + clazz.getSimpleName() + ": " + e.getMessage(), e);
    }
}
```

* Obsługuje deserializację z `String` do konkretnej klasy (`clazz`).
* W przypadku błędu deserializacji, rzuca `RuntimeException` z komunikatem.

### 🔁 Deserializacja list (JSON → List<DTO>)

```java
public static <T> List<T> deserializeAndValidateList(Response response, TypeReference<List<T>> typeRef) {
    return deserializeAndValidateList(response.asString(), typeRef);
}
```

* Przyjmuje `Response` zawierający JSON z listą obiektów.
* Przekazuje dalej jako `String`.

```java
public static <T> List<T> deserializeAndValidateList(String json, TypeReference<List<T>> typeRef) {
    List<T> list = deserializeJson(json, typeRef);
    for (T dto : list) {
        validateDto(dto);
    }
    return list;
}
```

* Deserializuje JSON do listy obiektów.
* Każdy element listy przechodzi walidację.
* Jeśli jakikolwiek DTO jest niepoprawny, zostanie rzucony wyjątek.

```java
public static <T> T deserializeJson(String json, TypeReference<T> typeRef) {
    try {
        return objectMapper.readValue(json, typeRef);
    } catch (Exception e) {
        throw new RuntimeException("Error deserializing JSON to generic type: " + e.getMessage(), e);
    }
}
```

* Umożliwia deserializację generyczną np. `List<MyDto>`.
* Obsługuje typy złożone (np. `Map<String, List<MyDto>>`).

### ✅ Walidacja DTO

```java
public static <T> void validateDto(T dto) {
    try (var factory = Validation.buildDefaultValidatorFactory()) {
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<T>> violations = validator.validate(dto);

        if (!violations.isEmpty()) {
            StringBuilder message = new StringBuilder("Validation failed:\n");
            for (ConstraintViolation<T> v : violations) {
                message.append(" - ").append(v.getPropertyPath()).append(": ").append(v.getMessage()).append("\n");
            }
            throw new RuntimeException(message.toString());
        }
    }
}
```

* Tworzy instancję walidatora (implementacja JSR 380 / Bean Validation).
* **`validator.validate(dto)`**: sprawdza, czy DTO spełnia wszystkie adnotacje walidacyjne (`@NotNull`, `@Size`, itd.).
* Jeśli są błędy:

    * Tworzy komunikat zbiorczy zawierający listę pól i błędów.
    * Rzuca `RuntimeException` z czytelnym opisem problemów.

### 🧠 Podsumowanie

#### Co robi `UtilsResponse`?

* ✅ Deserializuje odpowiedzi HTTP z JSON do obiektów i list DTO.
* ✅ Waliduje je za pomocą adnotacji (np. `@NotNull`).
* ✅ Wyrzuca szczegółowy błąd, jeśli JSON jest niepoprawny lub obiekt nie przechodzi walidacji.
* ✅ Obsługuje zarówno pojedyncze obiekty, jak i listy.

---

## 📄DTO – opcjonalne parametry <a name="dto_optional_parameters"></a>

### Kontekst

Czasami zdarza się tak, że response raz zwraca jakiś parametr, a raz nie.  
W takiej sytuacji trzeba go zapisać nieco inaczej, aby walidator go nie wymagał.

### Kroki

#### 1️⃣Zmieniamy zapis deklaracji tej zmiennej:

```java
// PRZED:
@NotNull
public SkinVariations skinVariations;

// PO:
@Valid
@JsonProperty("skinVariations")
public SkinVariations skinVariations;
```

**Wyjaśnienie:**

🔹 `@JsonProperty` zapisujemy tutaj, ponieważ w następnym kroku będziemy to usuwać z `@JsonCreator`.

🔹 `@NotNull` (np. `import jakarta.validation.constraints.NotNull;`)

* To **constraint** (ograniczenie) z pakietu Bean Validation.
* Służy do **walidacji prostej wartości** — sprawdza, czy dane pole **nie jest `null`**.
* Dotyczy:

    * prostych typów (`String`, `Integer`, `Boolean`, itp.),
    * obiektów (`Organization`, `Prefs` itp.).
* Jeśli adnotacja jest obecna, a pole = `null` → walidator rzuci błąd.

👉 Przykład:

```java
public class UserDto {
    @NotNull
    public String username;
}
```

JSON bez `username` → błąd walidacji: *"username must not be null"*.

🔹 `@Valid` (np. `import jakarta.validation.Valid;`)

* To **instrukcja dla walidatora**, że ma wejść **rekurencyjnie** do wnętrza tego obiektu i sprawdzić jego pola (jeśli obiekt nie jest nullem).
* Nie waliduje **samej wartości** — jeśli pole jest `null`, to walidator po prostu pomija sprawdzanie.
* Działa **tylko na obiektach zagnieżdżonych** (DTO w DTO, listy DTO itp.).

👉 Przykład:

```java
public class UserDto {
    @Valid
    public AddressDto address;
}

public class AddressDto {
    @NotNull
    public String city;
}
```

* Jeśli `address = null` → OK (brak błędu, bo `@Valid` nie wymusza istnienia obiektu).
* Jeśli `address` istnieje, ale `city = null` → błąd walidacji: *"city must not be null"*.

🔑 Podsumowanie

* `@NotNull` → **pole samo w sobie nie może być nullem**.
* `@Valid` → **waliduj pola w środku obiektu**, jeśli obiekt istnieje.

Często łączy się je razem:

```java
@NotNull
@Valid
public Organization organization;
```

➡️ wtedy wymagamy, żeby `organization` **było obecne** i żeby jego **pola też były poprawne**.

#### 2️⃣Usuwamy parametr z konstruktora `@JsonCreator`:

```java
// PRZED:
@JsonCreator
public Trello(
        @JsonProperty(value = "unified", required = true) String unified,
        @JsonProperty(value = "name", required = true) String name,
        @JsonProperty(value = "native", required = true) String nativeChar,
        @JsonProperty(value = "shortName", required = true) String shortName,
        @JsonProperty(value = "shortNames", required = true) List<String> shortNames,
        @JsonProperty(value = "text", required = true) String text,
        @JsonProperty(value = "texts") List<String> texts,
        @JsonProperty(value = "category", required = true) String category,
        @JsonProperty(value = "sheetX", required = true) Integer sheetX,
        @JsonProperty(value = "sheetY", required = true) Integer sheetY,
        @JsonProperty(value = "skinVariation", required = true) String skinVariation,
        @JsonProperty(value = "skinVariations", required = true) String skinVariations
) {
    this.unified = unified;
    this.name = name;
    this.nativeChar = nativeChar;
    this.shortName = shortName;
    this.shortNames = shortNames;
    this.text = text;
    this.texts = texts;
    this.category = category;
    this.sheetX = sheetX;
    this.sheetY = sheetY;
    this.skinVariation = skinVariation;
    this.skinVariations = skinVariations;
}

// PO:
@JsonCreator
public Trello(
        @JsonProperty(value = "unified", required = true) String unified,
        @JsonProperty(value = "name", required = true) String name,
        @JsonProperty(value = "native", required = true) String nativeChar,
        @JsonProperty(value = "shortName", required = true) String shortName,
        @JsonProperty(value = "shortNames", required = true) List<String> shortNames,
        @JsonProperty(value = "text", required = true) String text,
        @JsonProperty(value = "texts") List<String> texts,
        @JsonProperty(value = "category", required = true) String category,
        @JsonProperty(value = "sheetX", required = true) Integer sheetX,
        @JsonProperty(value = "sheetY", required = true) Integer sheetY,
        @JsonProperty(value = "skinVariation", required = true) String skinVariation
) {
    this.unified = unified;
    this.name = name;
    this.nativeChar = nativeChar;
    this.shortName = shortName;
    this.shortNames = shortNames;
    this.text = text;
    this.texts = texts;
    this.category = category;
    this.sheetX = sheetX;
    this.sheetY = sheetY;
    this.skinVariation = skinVariation;
}
```

#### 3️⃣Musimy pamiętać, aby wszystkie parametry należące do opcjonalnego parametru/klasy/obiektu też nie były wymagane:

```java
package dto.emoji.list_available_emoji.trello;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import dto.emoji.list_available_emoji.trello.skin_variations.SkinVariationEntry;
import jakarta.validation.Valid;

@JsonIgnoreProperties(ignoreUnknown = false)
public class SkinVariations {

    @Valid
    @JsonProperty("1F3FB")
    public SkinVariationEntry oneF3FB;

    @Valid
    @JsonProperty("1F3FC")
    public SkinVariationEntry oneF3FC;

    @Valid
    @JsonProperty("1F3FD")
    public SkinVariationEntry oneF3FD;

    @Valid
    @JsonProperty("1F3FE")
    public SkinVariationEntry oneF3FE;

    @Valid
    @JsonProperty("1F3FF")
    public SkinVariationEntry oneF3FF;

    public SkinVariations() {
    }
}
```

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
