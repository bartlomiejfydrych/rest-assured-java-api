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
- [TestBase – dlaczego lepiej nie deklarować w nim zmiennych 'response' i innych rzeczy](#test_base_variables)
- [Boolean – testy](#boolean_tests)
- [RequestSpecification](#request_specification)
- [RestAssured.filters()](#rest_assured_filters)
- [RequestSpecBuilder](#request_spec_builder)
- [RestAssured.enableLoggingOfRequestAndResponseIfValidationFails()](#enable_log_fail)
- [REST Assured – przesyłanie pustych Stringów](#rest_assured_send_empty_string)
- [RecursiveComparisonConfiguration()](#assertj_recursive_comparison_configuration)
- [Rest Assured – przykłady pisania testów z kursu Sii](#rest_assured_test_examples_from_course)
- [ID – czyszczenie zmiennej po wysłaniu DELETE](#id_clean_after_delete)
- [UtilsCompare.java – opis kodu](#utils_compare_java)
- [UtilsResponse.java – opis kodu](#utils_response_java)
- [DTO – opcjonalne parametry](#dto_optional_parameters)
- [DTO – opcjonalne parametry 2](#dto_optional_parameters_2)
- [DTO – (value = ...)](#dto_value)
- [Struktura JSON – JsonSchema vs. DTO/POJO](#json_schema_dto_pojo)
- [Porównywanie JSON'ów – wyzwania, podejścia, praktyki](#json_compare_intro)
- [Porównywanie JSON'ów – ObjectMapper](#json_compare_object_mapper)
- [Porównywanie JSON'ów – JsonNode](#json_compare_json_node)
- [REST Assured – asercja dla pustego obiektu](#rest_assured_assert_empty_object)
- [JUnit – tagi dla testów](#junit_test_tags)
- [Junit – TestInstance.Lifecycle](#junit_testinstance_lifecycle)
- [AssertJ – porównywanie obiektów i list](#assertj_object_compare)
- [AssertJ – dodawanie komentarzy/logów do testów](#assertj_comments_logs)
- [AssertJ – soft assertions](#assertj_soft_assertions)
- [Zmienne – podstawianie pod String/Text Block](#variables_string_text_block)
- [Response (expected, universal) – opcjonalne parametry](#response_expected_universal_optional_parameters)
- [Number/Liczba jako String – czy powinna przechodzić (Query Params vs. JSON body)](#number_as_string)
- [Long vs. long (prymitywy) – autoboxing, czyli automatyczne pakowanie prymitywu w obiekt](primitives_long)
- [Dokumentacja testów – możliwe formy](#test_documentation_forms)
- [URL – kodowanie znaków](#url_encoded)
- [SEED – informacje](#seed_information)
- [JUnit – rozdzielenie uruchamiania testów API i jednostkowych](#junit_api_unit_tests)

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
      - responsePost (tylko, jeśli testy nie będą uruchamiane równolegle!)
      - responsePut (tylko, jeśli testy nie będą uruchamiane równolegle!)
      - responseGet (tylko, jeśli testy nie będą uruchamiane równolegle!)
      - responseDelete (tylko, jeśli testy nie będą uruchamiane równolegle!)
      - faker (opcjonalne)
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

## 📄TestBase – dlaczego lepiej nie deklarować w nim zmiennych 'response' i innych rzeczy <a name="test_base_variables"></a>

To **bardzo dobre pytanie** – i ważne, bo tu nie ma odpowiedzi „czarno-białej”.
Krótko: **to nie jest „źle”**, ale **ma konkretne konsekwencje**, o których warto świadomie wiedzieć.

Poniżej masz **uczciwą, praktyczną odpowiedź** + **rekomendację**, jak to robić „po inżyniersku”, a nie „książkowo”.

### 1️⃣ Czy to źle, że trzymasz to wszystko w `TestBase`?

#### ❌ Nie – to NIE jest błąd

To **bardzo częsty pattern w testach API**.

W praktyce:

* 70–80% projektów testowych robi dokładnie to samo
* szczególnie w testach **E2E / integracyjnych**

Więc:

> ❗ **To nie jest anty-pattern. To jest kompromis.**

### 2️⃣ Dlaczego ludzie jednak ostrzegają przed takim podejściem?

Bo **mieszasz różne rodzaje odpowiedzialności** w jednym miejscu.

Popatrzmy na Twoje pola i podzielmy je na kategorie 👇

#### 🟢 1. Rzeczy, które są OK w `TestBase`

##### ✅ RequestSpecification

```java
protected static RequestSpecification requestSpecificationCommon;
```

✔️ **Jak najbardziej OK**  
To jest **konfiguracja infrastruktury testów**

##### ✅ Test tags

```java
protected static final String testTagPositive = "positive";
protected static final String testTagNegative = "negative";
```

✔️ OK  
Stałe wspólne dla testów – dobra lokalizacja

##### ✅ ObjectMapper

```java
protected static ObjectMapper objectMapper = new ObjectMapper();
```

✔️ OK  
Stateless, thread-safe, często współdzielony

#### 🟡 2. Rzeczy „na granicy” (nie złe, ale ryzykowne)

##### ⚠️ Response

```java
protected static Response responsePost;
protected static Response responsePut;
protected static Response responseGet;
protected static Response responseDelete;
```

**Dlaczego to bywa problematyczne?**

* to jest **stan konkretnego testu**
* może zostać nadpisany przez inny test
* przy równoległych testach → ❌ chaos

Ale:  
✔️ **jeśli testy są sekwencyjne**  
✔️ **jeśli wiesz, że nie używasz parallel execution**

→ to **jest akceptowalne**

> 📌 W wielu projektach testowych tak się robi – świadomie.

#### 🟡 3. Faker i Random

```java
protected static Faker faker = new Faker();
protected static Random random = new Random();
```

To **nie jest błąd**, ale:

* to nie jest konfiguracja testów
* to są **narzędzia pomocnicze**

📌 Lepsze miejsce:

* `UtilsCommon`
* albo `TestDataFactory`

ALE:  
✔️ jeśli używasz ich **bardzo często**  
✔️ i chcesz mieć szybki dostęp

→ **nie jest to grzech**

### 3️⃣ Prawdziwy problem: STATYCZNOŚĆ + STAN

Twoje pola są:

```java
protected static ...
```

To oznacza:

* wspólne dla **WSZYSTKICH testów**
* jeden test może wpłynąć na drugi

#### Przykład ryzyka:

```java
responsePost = postCreateLabel(...);
// inny test nadpisuje responsePost
```

Jeśli:

* uruchomisz testy równolegle
* lub zmienisz kolejność

➡️ testy mogą się losowo wysypywać

### 4️⃣ Dlaczego mimo to często się tak robi?

Bo w testach API:

* **czytelność > purystyczna architektura**
* **szybkość pisania testów > idealny design**
* testy i tak:
    * nie są częścią produkcji
    * mają inny cykl życia

> 🧠 **Test code ≠ Production code**

### 5️⃣ Rozsądna rekomendacja (BEST PRACTICE w praktyce)

#### ✅ ZOSTAW w `TestBase`:

```java
RequestSpecification
ObjectMapper
testTagPositive / testTagNegative
```

#### 🟡 MOŻESZ zostawić:

```java
Response responseX
Faker
Random
```

**ALE pod warunkiem:**
* testy nie są uruchamiane równolegle
* masz spójną konwencję użycia
* wiesz, że to „shared mutable state”

### 6️⃣ Najlepszy kompromis (polecam 👇)

#### 🔥 Rozwiązanie HYBRYDOWE

```java
public abstract class TestBase {

    protected static RequestSpecification requestSpec;
    protected static ObjectMapper objectMapper;

    protected static final String POSITIVE = "positive";
    protected static final String NEGATIVE = "negative";
}
```

A w testach:

```java
Response responsePost;
Response responsePut;
```

I w utilsach:

```java
UtilsCommon.getRandomSingleChar();
UtilsCommon.pickRandom(...);
```

➡️ **maksymalna czytelność**  
➡️ **minimum powtórzeń**  
➡️ **brak ukrytych side-effectów**

### 7️⃣ TL;DR – krótko i konkretnie

❓ *Czy to źle?*  
👉 **Nie**

❓ *Czy idealnie?*  
👉 **Też nie**

❓ *Czy akceptowalne w testach API?*  
👉 **Jak najbardziej**

❓ *Czy rozumiem, dlaczego tak zrobiłeś?*  
👉 **100% tak**

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

## 📄REST Assured – przesyłanie pustych Stringów <a name="rest_assured_send_empty_string"></a>

### Różnica

Jak przekazujemy w query parameters puste Stringi bezpośrednio (`.setName(""); .setColor("");`), to są one ignorowane przez REST Assured.

Jak przekazujemy w query parameters puste Stringi za pomocą zmiennych, to dopiero wtedy są one przesyłane:
```java
String name = "";
String color = "";

spec.queryParam("name", name);
spec.queryParam("color", color);
```

### Wyjaśnienie

RestAssured **domyślnie nie dodaje parametrów o pustej wartości ("") do requestu**.  
Tzn. te parametry **nie trafią w ogóle do żądania HTTP**.

Czyli finalny request, który idzie do API, **nie zawiera ani `name`, ani `color`** —  
więc serwer nie ma czego zaktualizować i nie zmienia niczego w zasobie.

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

## 📄DTO – opcjonalne parametry 2 <a name="dto_optional_parameters_2"></a>

### Pytanie

Przypomnij mi, dlaczego tutaj pole "organization" zostawiliśmy osobno, nie podając go z pozostałymi parametrami w @JsonCreator?  
Z tego, co pamiętam, to obiekt ten raz się pojawia w response, a raz nie.  
I dlaczego ma on @JsonProperty(FIELD_ORGANIZATION) nad sobą?

```java
@Valid // <-- validates nested fields if object exists
@JsonProperty(FIELD_ORGANIZATION)
public Organization organization;
```

### Odpowiedź

Bardzo dobre pytanie — i dobrze, że do tego wracasz, bo to **jedna z ważniejszych decyzji projektowych w tym DTO**.
Przypomnę Ci to **krok po kroku**, logicznie, „dlaczego tak, a nie inaczej”.

#### TL;DR (krótkie przypomnienie)

**Pole `organization`:**

* **raz występuje w response, raz nie**
* **nie jest wymagane zawsze**
* **ma być walidowane tylko wtedy, gdy się pojawi**
* **nie może powodować błędu deserializacji, gdy go brak**

➡️ dlatego:

* **NIE jest w `@JsonCreator`**
* **JEST polem z `@JsonProperty`**
* **MA `@Valid`, ale NIE `@NotNull`**

#### 1️⃣ Dlaczego `organization` NIE jest w `@JsonCreator`

##### Co robi `@JsonCreator`?

Gdy masz:

```java
@JsonCreator
public PUT_UpdateBoardDto(
    @JsonProperty(value = "id", required = true) String id,
    ...
)
```

to **Jackson mówi**:

> „Aby stworzyć obiekt, muszę dostać WSZYSTKIE parametry konstruktora”

Jeśli **jakiegokolwiek parametru zabraknie**, a masz:

```java
FAIL_ON_MISSING_CREATOR_PROPERTIES = true
```

➡️ **deserializacja WYBUCHA**

##### Co by się stało, gdybyś dodał tam `organization`?

```java
@JsonProperty(value = "organization", required = false) Organization organization
```

❌ Nadal problem:

* Jackson **i tak oczekuje tego parametru w JSON**
* „required = false” **nie ratuje przy creatorze**
* brak pola →
  **Missing creator property 'organization'**

Czyli dokładnie ten błąd, który już kiedyś widziałeś.

##### Wniosek

> `@JsonCreator` **nie nadaje się do pól, które są opcjonalne w response**

Dlatego:  
✔️ **`organization` MUSI być poza konstruktorem**

#### 2️⃣ Dlaczego `organization` jest polem, a nie parametrem konstruktora

Jackson ma **dwa tryby deserializacji**:

| Tryb                         | Kiedy                  |
|------------------------------|------------------------|
| Konstruktor (`@JsonCreator`) | pola **zawsze obecne** |
| Field / setter               | pola **opcjonalne**    |

Ty zrobiłeś dokładnie to, co trzeba:

```java
@JsonProperty(FIELD_ORGANIZATION)
public Organization organization;
```

Co to daje?

* jeśli **pole jest w JSON** → Jackson je ustawi
* jeśli **pola nie ma** → `organization == null`
* **brak błędu**
* walidacja działa tylko, gdy obiekt istnieje

✔️ dokładnie to, czego potrzebujesz

#### 3️⃣ Dlaczego `@JsonProperty(FIELD_ORGANIZATION)` nad polem?

To jest **kluczowy detal**, który często się pomija.

##### Po co to jest?

```java
@JsonProperty("organization")
public Organization organization;
```

Zapewnia:

1. **Jawne mapowanie**
    * nawet jeśli nazwa pola w Javie się zmieni
2. **Spójność z `FIELD_ORGANIZATION`**
3. **Czytelność**
    * od razu widać, że to pole pochodzi z JSON-a
4. **Brak zależności od naming strategy**

Bez tego:

* Jackson mapowałby „domyślnie”
* ale **Twoje DTO straciłoby precyzję**

➡️ W testach API **jawność > magia**

#### 4️⃣ Dlaczego `@Valid`, ale bez `@NotNull`

```java
@Valid
@JsonProperty(FIELD_ORGANIZATION)
public Organization organization;
```

##### Co to dokładnie oznacza?

| Sytuacja                         | Efekt                 |
|----------------------------------|-----------------------|
| `organization` **nie ma w JSON** | `null`, OK            |
| `organization` **jest w JSON**   | walidowane            |
| `organization` jest `null`       | `@Valid` nic nie robi |

Czyli:

> **Waliduj, ale tylko jeśli istnieje**

To jest **idealne zachowanie dla pól opcjonalnych**.

#### 5️⃣ Dlaczego to rozwiązanie jest „właściwe”

Masz jednocześnie:

* `FAIL_ON_UNKNOWN_PROPERTIES = true`
* `FAIL_ON_MISSING_CREATOR_PROPERTIES = true`
* pełną walidację Bean Validation
* brak fałszywych błędów

To jest **najbezpieczniejsza i najbardziej przewidywalna konfiguracja**.

#### 6️⃣ Jak zapamiętać tę zasadę na przyszłość

Możesz zapamiętać prostą regułę:

> **Jeśli pole:**
>
> * zawsze występuje → `@JsonCreator`
> * czasami występuje → pole/setter + `@JsonProperty`
> * ma być walidowane, tylko gdy jest → `@Valid` bez `@NotNull`

#### 7️⃣ Podsumowanie w jednym zdaniu

**`organization` jest poza `@JsonCreator`, bo jest opcjonalne w response i nie może powodować błędu deserializacji,
a `@JsonProperty` zapewnia jawne mapowanie i walidację tylko wtedy, gdy pole się pojawi.**

---

## 📄DTO – (value = ...) <a name="dto_value"></a>

### Pytanie

Dlaczego niektóre pola nie mają (value = ...)?

### Odpowiedź

Bardzo dobre pytanie — i dobrze, że to zauważyłeś 👍  
Krótka odpowiedź: **`value =` jest opcjonalne**, a brak `value =` **nie zmienia zachowania adnotacji**.

Poniżej pełne, uporządkowane wyjaśnienie 👇

#### 1️⃣ Dlaczego czasem jest `@JsonProperty("x")`, a czasem `@JsonProperty(value = "x")`?

Bo **`value` jest domyślnym (pierwszym) parametrem adnotacji**.

##### Te dwie wersje są **IDENTYCZNE**:

```java
@JsonProperty("backgroundImage")
```

```java
@JsonProperty(value = "backgroundImage")
```

➡️ Kompilator Javy traktuje je dokładnie tak samo.

#### 2️⃣ Kiedy MUSISZ użyć `value =`?

Tylko wtedy, gdy **używasz więcej niż jednego parametru adnotacji**, np.:

```java
@JsonProperty(value = "name", required = true)
```

Nie możesz wtedy napisać:

```java
@JsonProperty("name", true) // ❌ nielegalne
```

#### 3️⃣ Dlaczego w Twoim kodzie część pól ma `value =`, a część nie?

##### Przykłady:

```java
@JsonProperty(FIELD_AUTO_ARCHIVE)
Object autoArchive;
```

```java
@JsonProperty(value = FIELD_PERMISSION_LEVEL, required = true)
String permissionLevel;
```

To **świadomy wybór stylistyczny**:

| Przypadek           | Dlaczego                     |
|---------------------|------------------------------|
| Bez `required`      | krótszy zapis, czytelniej    |
| Z `required = true` | trzeba jawnie nazwać `value` |

#### 4️⃣ Czy to wpływa na działanie Jacksona?

❌ **Nie ma absolutnie żadnego wpływu**.

Jackson:

* tak samo mapuje JSON → Java
* tak samo waliduje obecność pól (`required = true`)
* tak samo serializuje obiekt

To **czysto składniowa różnica**.

#### 5️⃣ Czy warto to ujednolicić?

##### Opcja A – spójność i czytelność (polecane)

```java
@JsonProperty(value = FIELD_BACKGROUND, required = true)
```

```java
@JsonProperty(value = FIELD_AUTO_ARCHIVE)
```

✔ czytelne  
✔ jednoznaczne  
✔ łatwiejsze code review

##### Opcja B – krócej tam, gdzie się da

```java
@JsonProperty(FIELD_AUTO_ARCHIVE)
```

```java
@JsonProperty(value = FIELD_BACKGROUND, required = true)
```

✔ mniej „szumu”  
✔ często spotykane w projektach

#### 6️⃣ Moja rekomendacja dla Twojego projektu

Biorąc pod uwagę:

* testy API
* DTO jako **kontrakt**
* nacisk na czytelność i walidację

👉 **trzymaj się jednej konwencji**:

> **Zawsze używaj `value = ...`, nawet gdy jest tylko jeden parametr**

To bardzo dobrze skaluje się przy:

* refaktoryzacjach
* dodawaniu `required`
* porównywaniu DTO

### TL;DR 🧠

* `value` to **domyślny parametr adnotacji**
* `@JsonProperty("x")` == `@JsonProperty(value = "x")`
* `value =` jest wymagane tylko, gdy są inne parametry
* brak `value =` **nie jest błędem**
* **konsekwencja > skrótowość**

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

---

## 📄JUnit – tagi dla testów <a name="junit_test_tags"></a>

Gdy chcemy, aby w zależności od okoliczności lub konfiguracji były uruchamiane różne testy, można je oznaczyć tagami.

Najpierw dla testów ustawiamy tagi np. `@Tag("positive")`.
```java
@Test
@Tag("positive")
public void P1_shouldCreateLabelWithCorrectValues() {
    String labelName = getAllCharactersSetInRandomOrder();
    String labelColor = pickRandom("yellow", "purple", "blue", "red", "green", "orange", "black", "sky", "pink", "lime");

    responsePost = postCreateLabel(tempBoardId, labelName, labelColor);
    assertThat(responsePost.statusCode()).isEqualTo(200);
    // dalsza logika...
}
```

Następnie umieszczamy `IF'a` w jednym z `Before...`/`After...`, który będzie odnosił się dla testów z tym tagiem np.
`if (testInfo.getTags().contains("positive")) { }`.
```java
@BeforeEach
public void setUpTempBoardForPositiveTests(TestInfo testInfo) {
    if (testInfo.getTags().contains("positive")) {
        responsePost = postCreateBoard(generateRandomBoardName(), null);
        assertThat(responsePost.statusCode()).isEqualTo(200);
        tempBoardId = deserializeJson(responsePost, POST_CreateBoardDto.class).id;
    }
}
```

---

## 📄Junit – TestInstance.Lifecycle <a name="junit_testinstance_lifecycle"></a>

### 🧩 1️⃣ Co to jest `@TestInstance`

`@TestInstance` to adnotacja w **JUnit 5 (Jupiter)**, która określa **cykl życia instancji klasy testowej**.

Normalnie (domyślnie), JUnit **tworzy nową instancję klasy testowej dla każdego testu** — to zachowanie jest bardzo bezpieczne, bo testy nie mają wspólnego stanu (czyli nie wpływają na siebie nawzajem).

Ale czasami (np. przy kosztownym setupie lub testach sekwencyjnych) chcemy **zachować stan pomiędzy testami** — wtedy możemy zmienić cykl życia instancji.

### ⚙️ 2️⃣ Dostępne tryby cyklu życia (`Lifecycle`)

Adnotacja `@TestInstance` ma parametr `Lifecycle`, który przyjmuje dwa warianty:

| Tryb                      | Opis                                                                                                        |
|---------------------------|-------------------------------------------------------------------------------------------------------------|
| `PER_METHOD` *(domyślny)* | Dla każdego testu (`@Test`) JUnit tworzy **nowy obiekt** klasy testowej.                                    |
| `PER_CLASS`               | Dla **wszystkich testów w klasie** tworzony jest **jeden wspólny obiekt** (jedna instancja klasy testowej). |

### 🔍 3️⃣ Przykład — różnica między `PER_METHOD` i `PER_CLASS`

#### 🧪 Domyślny tryb: `PER_METHOD`

```java
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
class ExampleTest {

    private int counter = 0;

    @Test
    void test1() {
        counter++;
        System.out.println("Test1 counter = " + counter);
    }

    @Test
    void test2() {
        counter++;
        System.out.println("Test2 counter = " + counter);
    }
}
```

➡️ Wynik:

```
Test1 counter = 1
Test2 counter = 1
```

Dlaczego?
Bo **dla każdego testu tworzona jest nowa instancja klasy**, więc `counter` zawsze startuje od 0.

#### 🧪 Tryb: `PER_CLASS`

```java
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ExampleTest {

    private int counter = 0;

    @Test
    void test1() {
        counter++;
        System.out.println("Test1 counter = " + counter);
    }

    @Test
    void test2() {
        counter++;
        System.out.println("Test2 counter = " + counter);
    }
}
```

➡️ Wynik:

```
Test1 counter = 1
Test2 counter = 2
```

Dlaczego?
Bo **wszystkie testy korzystają z tej samej instancji klasy**, więc `counter` zachowuje wartość pomiędzy testami.

### 🧰 4️⃣ Kiedy JUnit wywołuje metody `@BeforeAll`, `@BeforeEach`, `@AfterEach`, `@AfterAll`

| Adnotacja     | PER_METHOD                                        | PER_CLASS                      |
|---------------|---------------------------------------------------|--------------------------------|
| `@BeforeAll`  | musi być **static** (bo nie ma instancji jeszcze) | może być **nie-static**        |
| `@BeforeEach` | wywoływana przed każdym testem                    | wywoływana przed każdym testem |
| `@AfterEach`  | wywoływana po każdym teście                       | wywoływana po każdym teście    |
| `@AfterAll`   | musi być **static**                               | może być **nie-static**        |

Przykład:

```java
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ExampleTest {

    @BeforeAll
    void beforeAll() {
        System.out.println("Before all tests");
    }

    @BeforeEach
    void beforeEach() {
        System.out.println("Before each test");
    }

    @Test
    void test1() {
        System.out.println("Running test1");
    }

    @Test
    void test2() {
        System.out.println("Running test2");
    }

    @AfterEach
    void afterEach() {
        System.out.println("After each test");
    }

    @AfterAll
    void afterAll() {
        System.out.println("After all tests");
    }
}
```

➡️ Wynik:

```
Before all tests
Before each test
Running test1
After each test
Before each test
Running test2
After each test
After all tests
```

### 📦 5️⃣ Podsumowanie różnic

| Cecha                                 | PER_METHOD *(domyślny)*      | PER_CLASS                                           |
|---------------------------------------|------------------------------|-----------------------------------------------------|
| Liczba instancji testów               | Jedna instancja **na test**  | Jedna instancja **na klasę**                        |
| Współdzielenie pól                    | ❌ Nie (każdy test ma własne) | ✅ Tak (pola zachowują stan między testami)          |
| @BeforeAll/@AfterAll muszą być static | ✅ Tak                        | ❌ Nie                                               |
| Izolacja testów                       | ✅ Pełna                      | ⚠️ Może być zaburzona                               |
| Wydajność (np. drogi setup)           | ❌ wolniejszy                 | ✅ szybszy (jeden setup)                             |
| Kiedy stosować                        | domyślnie — testy niezależne | gdy chcesz utrzymać stan (np. jeden board w Trello) |

### 💡 6️⃣ Kiedy używać którego trybu

#### ✅ `PER_METHOD` — najczęściej

* Testy **niezależne od siebie**
* Każdy test ma czysty stan
* Unikasz efektu ubocznego
* Bezpieczny dla testów równoległych

#### ✅ `PER_CLASS` — czasami

* Gdy chcesz **zachować stan** pomiędzy testami (np. jeden utworzony zasób)
* Gdy **inicjalizacja jest kosztowna** (np. logowanie, setup środowiska)
* Gdy chcesz, by `@BeforeAll` / `@AfterAll` mogły być niestatyczne (czyli mieć dostęp do pól klasy)

### 🧠 7️⃣ Dodatkowe wskazówki

* Możesz mieszać `PER_CLASS` z `@TestMethodOrder` (np. ustalić kolejność wykonywania testów, gdy zachowujesz stan).
* Nie łącz `PER_CLASS` z równoległym uruchamianiem testów (`@Execution(CONCURRENT)`), jeśli testy modyfikują wspólny stan.
* W dużych projektach REST API:

    * **GET/POST/PUT/DELETE testy** często używają `PER_CLASS` — bo jeden obiekt testowy (np. board) może być tworzony raz i testowany różnymi metodami.
    * Testy walidacji / błędów — zwykle `PER_METHOD` (żeby mieć czyste środowisko).

### 📘 TL;DR — skrót notatkowy

| Pojęcie                    | Znaczenie                                                      |
|----------------------------|----------------------------------------------------------------|
| `@TestInstance`            | Steruje cyklem życia instancji klasy testowej                  |
| `Lifecycle.PER_METHOD`     | Domyślnie — nowa instancja dla każdego testu                   |
| `Lifecycle.PER_CLASS`      | Jedna instancja dla całej klasy testowej                       |
| `@BeforeAll` / `@AfterAll` | Muszą być static przy `PER_METHOD`, nie muszą przy `PER_CLASS` |
| Użycie `PER_CLASS`         | Gdy testy mają współdzielony stan lub setup jest kosztowny     |
| Użycie `PER_METHOD`        | Gdy testy mają być w pełni izolowane                           |

---

## 📄AssertJ – porównywanie obiektów i list <a name="assertj_object_compare"></a>

Mamy 2 prawie identyczne obiekty.  
Różnią się tym, że jeden ma dodatkowe pole o nazwie `"limits"`.  

Dziwne jest to, że **AssertJ** podczas ich porównywania nie zwraca błędu o tej różnicy.

### 🧩 Co się dzieje dokładnie

Metoda:

```java
assertThat(actualObject)
    .usingRecursiveComparison()
    .isEqualTo(expectedObject);
```

wykonuje **rekurencyjne porównanie pól dwóch obiektów** w stylu deep-equals.

I teraz kluczowy szczegół:

> AssertJ nie porównuje pól na podstawie **definicji klas**, tylko na podstawie **ich nazw i wartości wspólnych pól**.

Czyli:

* patrzy, jakie **pola występują w obu obiektach**,
* porównuje **wartości tych pól**,
* **ignoruje pola**, które są **tylko w jednej z klas**.

### 🔍 Co to oznacza w Twoim przypadku

Masz:

```java
POST_CreateLabelDto {
    id,
    idBoard,
    name,
    color,
    uses,
    limits   ← tylko tutaj
}
```

i

```java
PUT_UpdateFieldOnLabelDto {
    id,
    idBoard,
    name,
    color,
    uses
}
```

Wspólne pola:
`id`, `idBoard`, `name`, `color`, `uses`.

AssertJ więc porówna tylko te pięć.
Pole `limits` zostanie **pominięte**, bo `PUT_UpdateFieldOnLabelDto` go nie posiada.
Dlatego **nie dostajesz żadnego błędu**.

### 🧠 Jak to można zmienić (jeśli chcesz wymusić błąd)

Jeśli chcesz, żeby porównanie **wymagało idealnego dopasowania pól (klas)**,
musiałbyś użyć innego podejścia — np.:

#### 🔸 1. Porównanie przez serializację do JSON-a

```java
ObjectMapper mapper = new ObjectMapper();
String actualJson = mapper.writeValueAsString(responsePutDto);
String expectedJson = mapper.writeValueAsString(responsePostDto);

assertThat(actualJson).isEqualTo(expectedJson);
```

To wymusi identyczność struktury i wartości.
Pole `limits` spowoduje błąd, bo nie istnieje w jednym z obiektów.

#### 🔸 2. Lub włączyć ścisły tryb porównania w AssertJ (ręcznie)

Niestety, AssertJ nie ma natywnej opcji typu „fail on missing fields”,
ale możesz napisać mały helper, który sprawdzi listę pól klas:

```java
public static void assertSameFields(Class<?> class1, Class<?> class2) {
    var fields1 = Arrays.stream(class1.getDeclaredFields())
            .map(Field::getName)
            .collect(Collectors.toSet());
    var fields2 = Arrays.stream(class2.getDeclaredFields())
            .map(Field::getName)
            .collect(Collectors.toSet());

    if (!fields1.equals(fields2)) {
        throw new AssertionError("Classes have different fields: " +
                Sets.symmetricDifference(fields1, fields2));
    }
}
```

### 🧩 Podsumowanie

| Zjawisko                             | Dlaczego tak się dzieje                            |
|--------------------------------------|----------------------------------------------------|
| `compareObjects()` nie zgłasza błędu | AssertJ porównuje tylko wspólne pola               |
| Pole `limits` jest ignorowane        | Bo nie istnieje w obu klasach                      |
| Jak wymusić błąd                     | Porównaj JSON-y lub napisz dodatkowy walidator pól |

### Porównywanie list

Tak 🙂 — **już teraz masz taką możliwość**.  
I to jest dobra wiadomość, bo **nic nie musisz zmieniać**, żeby porównywać **listy obiektów**.

Poniżej rozbijmy to na konkrety, żeby nie było wątpliwości.

#### ✅ Krótka odpowiedź

> **Tak — `compareObjects(...)` i `compareObjectsSoft(...)` działają również dla list obiektów.**

Dlaczego?  
Bo **AssertJ `usingRecursiveComparison()` obsługuje kolekcje natywnie**.

#### 🧠 Jak to działa pod spodem

Gdy wywołasz:

```java
assertThat(actualList)
        .usingRecursiveComparison(...)
        .isEqualTo(expectedList);
```

AssertJ:
1. Sprawdza, że oba obiekty są kolekcjami
2. Porównuje:
    * rozmiar list
    * elementy **indeks po indeksie**
3. Każdy element listy:
    * porównuje **rekurencyjnie**
    * stosuje `ignoreFields(...)` do **każdego elementu**

Czyli masz:
* ✔ listy
* ✔ listy DTO
* ✔ listy zagnieżdżone
* ✔ ignorowanie pól wewnątrz elementów listy

#### 🧪 Przykład — LISTA DTO

##### DTO

```java
class UserDto {
    String id;
    String name;
}
```

#### Test

```java
List<UserDto> actual = List.of(
        new UserDto("1", "Alice"),
        new UserDto("2", "Bob")
);

List<UserDto> expected = List.of(
        new UserDto("X", "Alice"),
        new UserDto("Y", "Bob")
);

UtilsCompare.compareObjects(actual, expected, "id");
```

✔ Test przejdzie  
✔ `id` ignorowane w każdym elemencie listy

#### ⚠️ WAŻNE ograniczenia (świadome)

##### 1️⃣ Kolejność MA znaczenie

```java
List.of(A, B) ≠ List.of(B, A)
```

Jeśli API nie gwarantuje kolejności → test się wywali.

➡️ Rozwiązanie:
* sortowanie przed porównaniem
* albo comparator bez kolejności (za chwilę)

##### 2️⃣ Listy różnej długości = FAIL

To jest **pożądane zachowanie** w testach API.

##### 3️⃣ Ignorowanie działa po nazwach pól, nie po ścieżkach

```java
ignoreFields("id")
```

ignoruje:
* `id` w każdym obiekcie
* również zagnieżdżonym

#### 🚀 Jeśli chcesz porównywać listy BEZ kolejności

Masz **dwie bardzo dobre opcje**.

##### 🟢 OPCJA 1 — AssertJ `ignoringCollectionOrder()`

```java
assertThat(actualList)
        .usingRecursiveComparison()
        .ignoringCollectionOrder()
        .isEqualTo(expectedList);
```

Możesz to łatwo dodać jako nową metodę utila:

```java
public static void compareObjectsIgnoringOrder(
        Object actual,
        Object expected,
        String... fieldsToIgnore) {

    assertThat(actual)
            .usingRecursiveComparison(configWithIgnoredFields(fieldsToIgnore))
            .ignoringCollectionOrder()
            .isEqualTo(expected);
}
```

##### 🟡 OPCJA 2 — Sortowanie przed porównaniem (często lepsze)

```java
actual.sort(comparing(UserDto::getName));
expected.sort(comparing(UserDto::getName));
```

✔ czytelniejsze  
✔ lepsze błędy  
✔ pełna kontrola

#### 🧠 Rekomendacja architektoniczna

Dla testów API:
* **domyślnie** → kolejność ma znaczenie
* **opcjonalnie** → osobna metoda „ignore order”

Czyli:

```java
compareObjects(...)
compareObjectsIgnoringOrder(...)
```

#### TL;DR

✔ Twoje metody **już obsługują listy obiektów**  
✔ Ignorowanie pól działa dla każdego elementu listy  
⚠️ Kolejność elementów ma znaczenie  
🚀 Możesz łatwo dodać wersję „ignore order”

---

## 📄AssertJ – dodawanie komentarzy/logów do testów <a name="assertj_comments_logs"></a>

W **AssertJ** da się dodawać **własny komunikat błędu**, który zostanie wyświetlony, gdy asercja się wywali 💡.

### ✅ Poprawna składnia:

W AssertJ możesz użyć metody:

```java
.as(String description)
```

lub

```java
.withFailMessage(String message)
```

### 📘 Przykłady:

#### 🔹 1. `as()` — opis asercji (pojawia się jako część raportu):

```java
assertThat(responsePostPos2)
    .as("Pozycja jest niżej, a nie wyżej")
    .isLessThan(responsePostPos1);
```

➡️ Wyświetli w raporcie:
`[Pozycja jest niżej, a nie wyżej]` jeśli test się nie powiedzie.

#### 🔹 2. `withFailMessage()` — pełny komunikat błędu:

```java
assertThat(responsePostPos2)
    .withFailMessage("Pozycja jest niżej, a nie wyżej — oczekiwano, że %s < %s", responsePostPos2, responsePostPos1)
    .isLessThan(responsePostPos1);
```

➡️ Ten komunikat całkowicie zastępuje domyślny komunikat błędu AssertJ.

### 💡 Różnica:

| Metoda              | Kiedy używać                    | Co robi                            |
|---------------------|---------------------------------|------------------------------------|
| `as()`              | Do krótkiego opisu celu testu   | Dodaje opis do komunikatu błędu    |
| `withFailMessage()` | Do pełnej kontroli treści błędu | Zastępuje domyślny komunikat błędu |

### ✅ Najczęściej stosowana konwencja:

W projektach używa się częściej `as()`, np.:

```java
assertThat(responsePostPos2)
    .as("Pozycja elementu powinna być mniejsza niż poprzednia")
    .isLessThan(responsePostPos1);
```

---

## 📄AssertJ – soft assertions <a name="assertj_soft_assertions"></a>

### 🧪 SoftAssertions (AssertJ) – notatka praktyczna

### 1️⃣ Czym są SoftAssertions?

`SoftAssertions` to mechanizm z biblioteki **AssertJ**, który pozwala:

* wykonywać **wiele asercji w jednym teście**
* **nie przerywać testu przy pierwszym błędzie**
* zebrać wszystkie błędy
* zgłosić je **razem na końcu**

W przeciwieństwie do standardowych (hard) asercji:

```java
assertThat(a).isEqualTo(b); // przerywa test przy pierwszym błędzie
```

`SoftAssertions` pozwalają zobaczyć **pełny obraz różnic**.

### 2️⃣ Hard assertions vs Soft assertions

#### 🔴 Hard assertions (domyślne)

```java
assertThat(actual).isEqualTo(expected);
assertThat(statusCode).isEqualTo(200);
```

* test kończy się **przy pierwszym failu**
* dobre do:
    * status code
    * autoryzacji
    * smoke testów
    * warunków krytycznych

#### 🟢 Soft assertions

```java
SoftAssertions softly = new SoftAssertions();

softly.assertThat(actual).isEqualTo(expected);
softly.assertThat(statusCode).isEqualTo(200);

softly.assertAll();
```

* test **wykonuje wszystkie asercje**
* raportuje **wszystkie błędy naraz**
* idealne do:
    * porównań JSON
    * dużych obiektów
    * testów regresji

### 3️⃣ Złota zasada SoftAssertions ⚠️

> **Zawsze musisz wywołać `assertAll()`**

```java
softly.assertAll();
```

Jeśli zapomnisz:
* test **ZAWSZE przejdzie**
* nawet jeśli były błędy ❌

➡️ To najczęstszy i najgroźniejszy błąd.

### 4️⃣ Typowy wzorzec użycia

```java
SoftAssertions softly = new SoftAssertions();

softly.assertThat(value1).isEqualTo(expected1);
softly.assertThat(value2).isNotNull();
softly.assertThat(list).hasSize(3);

softly.assertAll();
```

### 5️⃣ SoftAssertions + RecursiveComparison (bardzo częsty case)

```java
SoftAssertions softly = new SoftAssertions();

softly.assertThat(actualObject)
        .usingRecursiveComparison(config)
        .isEqualTo(expectedObject);

softly.assertAll();
```

Efekt:
* dostajesz **pełny diff obiektu**
* zamiast jednego błędu

### 6️⃣ SoftAssertions w testach API

#### Przykład: porównanie response JSON

```java
SoftAssertions softly = new SoftAssertions();

softly.assertThat(response.getStatusCode()).isEqualTo(200);
softly.assertThat(actualJson)
        .usingRecursiveComparison(config)
        .isEqualTo(expectedJson);

softly.assertAll();
```

👉 Uwaga:
* **status code** często lepiej sprawdzać hard assertem
* **body** → soft assertions

### 7️⃣ Kiedy używać SoftAssertions? ✅

#### Używaj, gdy:

* porównujesz **duże struktury danych**
* testujesz **regresję**
* chcesz zobaczyć **wszystkie różnice**
* test nie ma „jednego krytycznego warunku”

#### Przykłady:

* porównanie JSON response
* porównanie DTO ↔ expected object
* snapshot tests

### 8️⃣ Kiedy NIE używać SoftAssertions? ❌

Nie używaj, gdy:
* test ma tylko jedną asercję
* failure powinien **natychmiast przerwać test**
* testujesz:
    * autoryzację
    * status code
    * dostępność endpointu
* piszesz **smoke / health-check**

### 9️⃣ SoftAssertions a utils / helper classes

#### ❗ Dwie szkoły

##### 1️⃣ SoftAssertions w testach (klasyczna)

```java
SoftAssertions softly = new SoftAssertions();
```

✔️ pełna kontrola w teście  
✔️ jasne zakończenie `assertAll()`

##### 2️⃣ SoftAssertions w utils (jak u Ciebie)

```java
compareResponseWithJsonSoft(...)
```

✔️ prostsze testy  
✔️ mniej boilerplate  
✔️ OK dla porównań typu „1 duża asercja”

➡️ **Twoje rozwiązanie jest poprawne**, bo:
* soft assert dotyczy jednej logicznej operacji
* `assertAll()` jest zawsze wołane

### 🔟 SoftAssertionsExtension (JUnit 5)

JUnit 5 oferuje:

```java
@ExtendWith(SoftAssertionsExtension.class)
```

Wtedy:
* `SoftAssertions` są wstrzykiwane
* `assertAll()` jest wołane automatycznie

⚠️ **Nie używaj tego w utilsach**  
✔️ tylko w testach

### 1️⃣1️⃣ Najczęstsze błędy ❌

* ❌ brak `assertAll()`
* ❌ statyczny `SoftAssertions`
* ❌ reużywanie jednego obiektu w wielu testach
* ❌ mieszanie hard i soft bez świadomości
* ❌ soft assertions dla status code

### 1️⃣2️⃣ Best practices (skrót)

✔️ SoftAssertions = duże porównania  
✔️ Hard assertions = warunki krytyczne  
✔️ `assertAll()` zawsze na końcu  
✔️ Nie używaj softów „wszędzie”  
✔️ Bądź świadomy wyboru

### 1️⃣3️⃣ TL;DR

> **SoftAssertions to narzędzie diagnostyczne, nie zamiennik zwykłych asercji**

Dobrze użyte:
* przyspieszają debugowanie
* poprawiają czytelność raportów
* zwiększają wartość testów regresyjnych

Źle użyte:
* maskują błędy
* dają fałszywie zielone testy

---

## 📄Zmienne – podstawianie pod String/Text Block <a name="variables_string_text_block"></a>

### Problem/Zagadnienie

Czy jest możliwe zrobienie w JAVA czegoś takiego:

```java
public static PUT_UpdateListDto prepareUniversalExpectedResponsePut(
        String id,
        String name,
        Boolean closed,
        String color,
        String idBoard,
        String pos,
        Boolean subscribed
) {
    String expectedResponse = """
            {
                "id": {id},
                "name": {name},
                "closed": {closed},
                "color": {idBoard},
                "idBoard": {idBoard},
                "pos": {pos},
                "subscribed": {subscribed}
            }
            """;
    PUT_UpdateListDto expectedResponsePutDto = deserializeJson(expectedResponse, PUT_UpdateListDto.class);
    return expectedResponsePutDto;
}
```

Chodzi o bezpośrednie wstawianie zmiennych do Stringa/JSON'a.

### Rozwiązanie/Wyjaśnienie

Krótko: **NIE — Java nie pozwala bezpośrednio wstawiać zmiennych do tekstu tak jak np. JavaScript (`${var}`) albo Python (`f"{var}"`)**.  
Ale **TAK — można to osiągnąć innymi sposobami**, np.:

#### ✅ 1. Java Text Block + `replace()` (najprostszy sposób)

```java
String expectedResponse = """
        {
            "id": "{id}",
            "name": "{name}",
            "closed": {closed},
            "color": "{color}",
            "idBoard": "{idBoard}",
            "pos": "{pos}",
            "subscribed": {subscribed}
        }
        """
        .replace("{id}", id)
        .replace("{name}", name)
        .replace("{closed}", String.valueOf(closed))
        .replace("{color}", color)
        .replace("{idBoard}", idBoard)
        .replace("{pos}", pos)
        .replace("{subscribed}", String.valueOf(subscribed));
```

✔ działa  
✔ łatwe  
✔ wspiera **text blocks**

#### ✅ 2. Java `String.format()`

Musisz zmienić JSON na format z `%s` :

```java
String expectedResponse = String.format("""
        {
            "id": "%s",
            "name": "%s",
            "closed": %s,
            "color": "%s",
            "idBoard": "%s",
            "pos": "%s",
            "subscribed": %s
        }
        """,
        id, name, closed, color, idBoard, pos, subscribed
);
```

✔ czytelne  
❗ musisz zachować kolejność parametrów

#### ✅ 3. Najlepsze dla JSON: użyć DTO i ObjectMapper (polecane!)

Po prostu tworzysz obiekt i serializujesz:

```java
ObjectMapper mapper = new ObjectMapper();

PUT_UpdateListDto dto = new PUT_UpdateListDto();
dto.id = id;
dto.name = name;
dto.closed = closed;
dto.color = color;
dto.idBoard = idBoard;
dto.pos = pos;
dto.subscribed = subscribed;

String json = mapper.writeValueAsString(dto);
```

✔ bez błędów w JSON  
✔ automatyczna walidacja  
✔ zero ręcznego formatowania stringów

### 🎯 Podsumowanie

#### Czy można zrobić „magiczne wstawianie zmiennych jak w Twoim przykładzie”?

➡ **Nie bezpośrednio**, Java tego nie wspiera.

#### Czy można to osiągnąć?

➡ **Tak — trzema sposobami:**

1. `replace()`
2. `String.format()`
3. (najlepsze) budowa DTO i serializacja JSON

---

## 📄Response (expected, universal) – opcjonalne parametry <a name="response_expected_universal_optional_parameters"></a>

### Problem/Zagadnienie

Mam taki "uniwersalny" oczekiwany response:

```java
public static final String BaseExpectedPutUpdateListResponse = """
        {
            "id": "6918c0e928328bf5ad423af0",
            "name": "Updated list name",
            "closed": true,
            "color": null,
            "idBoard": "6918c0e725eebf9505f994b3",
            "pos": 140737488338944,
            "subscribed": true
        }
        """;
```

Jak requestem podajemy/zmieniamy parametr "subscribed", to on pojawia się w response.
Jak nic z nim nie robimy, to go nie ma.

Mam taką metodę:
```java
public static PUT_UpdateListDto prepareUniversalExpectedResponsePut(
        String id,
        String name,
        Boolean closed,
        String color,
        String idBoard,
        Long pos,
        Boolean subscribed
) {
    PUT_UpdateListDto expectedResponsePutDto = deserializeJson(BaseExpectedPutUpdateListResponse, PUT_UpdateListDto.class);
    expectedResponsePutDto.id = id;
    expectedResponsePutDto.name = name;
    expectedResponsePutDto.closed = closed;
    expectedResponsePutDto.color = color;
    expectedResponsePutDto.idBoard = idBoard;
    expectedResponsePutDto.pos = pos;
    expectedResponsePutDto.subscribed = subscribed;
    return expectedResponsePutDto;
}
```

Czy lepiej usunąć to "subscribed" z oczekiwanego response'a i później w razie potrzeby dodawać go jakoś w metodzie,
czy może zostawić go w oczekiwanym response, ale np. jak w metodzie podamy go jako `null`, to zostanie on jakoś usunięty?
Które rozwiązanie będzie lepsze? A może jest jeszcze jakieś inne?

### Rozwiązanie/Wyjaśnienie

Twoje pytanie jest **bardzo dobre**, bo dotyczy **strategii budowania oczekiwanych response’ów** w testach — zwłaszcza przy API,
gdzie pewne pola pojawiają się *tylko wtedy, gdy je wysyłasz*.

W Twoim przypadku:

* `subscribed` **występuje w response tylko wtedy**, gdy był wysłany jako query param.
* jeśli nie został wysłany → pole **nie pojawia się w response**.

To oznacza, że **Twoje oczekiwane DTO musi dynamicznie odzwierciedlać strukturę prawdziwego response**.

#### ✅ Najlepsze rozwiązanie

##### **Usuń pole `subscribed` z `BaseExpectedPutUpdateListResponse`**

I w metodzie *dodawaj je tylko wtedy*, gdy test rzeczywiście go wysłał.

Dlaczego?

##### ✔ Opcjonalne pola NIE powinny znajdować się w bazowym (uniwersalnym) expected JSON

Oczekiwany response bazowy powinien zawierać **tylko pola obowiązkowe**, które na pewno będą w każdym response.

`subscribed` *nie jest obowiązkowe*, więc **nie powinno znajdować się w base JSON-ie**.

##### ✔ Jeśli pole się pojawia → dokładasz je manualnie

I to jest najlepsze, najczytelniejsze, najbardziej kontrolowane rozwiązanie.

#### ❌ Dlaczego nie zostawiać `subscribed` w base response i ustawiać `null`?

##### Bo to **nie działa**:

* Jeśli zostawisz `"subscribed": null` w JSON → Jackson **zawsze wczyta to pole**, więc Twoje DTO będzie je mieć.
* API jednak **nie zwraca pola `subscribed` z wartością null**, tylko NIE zwraca go wcale.
* To spowoduje różnicę między expected a actual → testy będą fałszywie czerwone.

Czyli:

```csharp
API response:
{
   "id": "...",
   "name": "...",
   ...
}

Expected (z null):
{
   "id": "...",
   "name": "...",
   ...
   "subscribed": null   ← tego pole wcale nie ma w API
}
```

Porównanie = ❌ fail.

#### 🚀 Najlepsza wersja Twojej metody

##### 1️⃣ Usuń `"subscribed"` z base JSON-a

Czyli pozostaw:

```java
public static final String BaseExpectedPutUpdateListResponse = """
        {
            "id": "6918c0e928328bf5ad423af0",
            "name": "Updated list name",
            "closed": true,
            "color": null,
            "idBoard": "6918c0e725eebf9505f994b3",
            "pos": 140737488338944
        }
        """;
```

##### 2️⃣ W metodzie dokładaj `subscribed` tylko gdy nie jest null:

```java
public static PUT_UpdateListDto prepareUniversalExpectedResponsePut(
        String id,
        String name,
        Boolean closed,
        String color,
        String idBoard,
        Long pos,
        Boolean subscribed
) {
    PUT_UpdateListDto expectedResponsePutDto =
            deserializeJson(BaseExpectedPutUpdateListResponse, PUT_UpdateListDto.class);

    expectedResponsePutDto.id = id;
    expectedResponsePutDto.name = name;
    expectedResponsePutDto.closed = closed;
    expectedResponsePutDto.color = color;
    expectedResponsePutDto.idBoard = idBoard;
    expectedResponsePutDto.pos = pos;

    if (subscribed != null) {
        expectedResponsePutDto.subscribed = subscribed;
    }

    return expectedResponsePutDto;
}
```

#### 👍 Dlaczego to podejście jest najlepsze?

✔ **Najbliżej realnego API**  
✔ **DTO odzwierciedla dokładny response**  
✔ **Jeden base JSON = prostsze testy**  
✔ **Opcjonalne pola pojawiają się tylko wtedy, gdy faktycznie istnieją**  
✔ **Brak fałszywych failów**  
✔ **Najbardziej przejrzysta logika**

---

## 📄Number/Liczba jako String – czy powinna przechodzić (Query Params vs. JSON body) <a name="number_as_string"></a>

### ❓ Pytanie

Czy podczas pisania/tworzenia API programiści często robią tak, że zarówno wartość liczbowa typu Number np. 1234,
jak i liczba typu String "1234" są obie prawidłowe naprzemiennie czy jednak po to stosuje się konkretne typy, aby się
ich trzymać?

Według dokumentacji parametr "pos" przyjmuje takie wartości:
```
pos | oneOf [number, string]
New position for the list: top, bottom, or a positive floating point number
```

Po podaniu liczby jako typ String, a nie Number request i tak przechodzi.

Czy to jest prawidłowe zachowanie, czy jednak nie powinno być to możliwe?

### ❗ Odpowiedź

Świetne pytanie — **dotyczy praktyk projektowania API**, których jako tester API absolutnie warto być świadomym.
Wyjaśnię Ci to **krótko, praktycznie, i w oparciu o to, co faktycznie spotyka się w prawdziwych API** (REST, GraphQL,
publiczne API takich firm jak Google, Stripe, Trello, GitHub itp.).

#### 🔍 **Czy API często pozwalają na:**

* `pos = 1234` (number)
  **i jednocześnie**
* `pos = "1234"` (string)?

👉 **TAK — częściej, niż mogłoby się wydawać.**

Ale *dlaczego*?

#### 🧠 Dlaczego API często akceptują *zarówno number, jak i string?*

##### 1️⃣ **HTTP query parameters są ZAWSZE tekstem**

`?pos=1234`  
`?pos=top`

W URL-u *nie istnieją typy liczbowe*.  
Wszystko jest stringiem. Bez wyjątku.

To serwer musi, później zdecyduje:

* czy `"1234"` traktować jako liczbę,
* czy `"top"` traktować jako słowo,
* czy `"1.25"` traktować jako float.

Dlatego API, które przyjmuje:

```
pos: number | string
```

robi to **świadomie**, ponieważ:

🔹 liczba przyjdzie jako `"1234"`  
🔹 wartość specjalna (`top`, `bottom`) też przyjdzie jako `"top"`

Technicznie *nie da się* odróżnić „liczba wysłana jako liczba” od „liczba wysłana jako string”, bo w query param
wszystko jest tekstem.

##### 2️⃣ **Leniwa konwersja typów (Type Coercion)**

Wiele API stosuje podejście:

> Jeśli wygląda jak liczba i da się sparsować → traktujemy jako liczbę.  
> Jeśli wygląda jak string → traktujemy jako komendę typu top/bottom.

To jest bardzo powszechne m.in. w:

* Node.js (Express + body/query parsers)
* Python Flask / FastAPI
* Ruby on Rails
* Java Spring (konwersja typów w ParamResolver)
* Go Gin/Gonic (string → float64 jeśli trzeba)
* API Trello / Atlassian

##### 3️⃣ **Backward compatibility**

Załóżmy, że stara wersja API pozwalała tylko na:

```
pos = "top"
pos = "bottom"
pos = "1.234"
```

Czyli *zawsze string*.

Nowa wersja API chce przyjmować:

```
pos = 5.2
```

Gdyby nagle API *wymagało numeru*, to zepsułoby aplikacje klientów, które wysyłały `"5.2"` jako tekst.

Dlatego API często wspierają dwa typy:

👉 **string**  
👉 **number**

Po to, żeby nie psuć zgodności wstecznej.

##### 4️⃣ **JSON body a query params to dwie różne rzeczy**

W **JSON body** temat byłby inny — tam typy są jednoznaczne:

```json
"pos": "1234"   ❌
"pos": 1234     ✔️
```

Ale Ty mówisz o:  
`PUT /lists/{id}?pos=1234`

A wszystkie query-paramy są stringami.

#### 🎯 Czy takie zachowanie jest prawidłowe?

##### ✔️ Tak — **jest bardzo typowe i często wręcz konieczne.**

##### ❌ Nie — jeśli dokumentacja obiecuje ścisłe typowanie dla query paramów (co jest rzadkością).

#### 🧪 Jako tester API — jak powinieneś to traktować?

Jeśli dokumentacja mówi:

```
pos: oneOf [number, string]
```

to oznacza:

* API świadomie akceptuje oba typy
* i jest to zachowanie **zamierzone**, nie błąd.

Twoje testy powinny to uwzględnić jako:

##### ✔️ Test pozytywny:

* `pos = "top"`
* `pos = "bottom"`
* `pos = "12345"`
* `pos = 12345` (jeśli używasz JSON body)
* `pos = "1.25"`
* `pos = 1.25`

##### ❌ Test negatywny:

* `pos = "abc"`
* `pos = "-12"` (jeśli API nie dopuszcza)
* `pos = ""`
* brak parametru (jeśli jest wymagany)

#### 🤔 Czy API powinno odrzucać liczbę wysłaną jako string?

W **query paramach** — nie.

W **JSON body** — tak.

### 📌 Podsumowanie

#### ✔ API akceptujące `pos` jako number **i** string to normalne, poprawne i częste.

#### ✔ Wynika to z natury query-paramów (które *zawsze są stringami*).

#### ✔ Wynika to też z backward compatibility.

#### ✔ Twoje testy powinny traktować to jako expected behavior.

---

## 📄Long vs. long (prymitywy) – autoboxing, czyli automatyczne pakowanie prymitywu w obiekt <a name="primitives_long"></a>

Piszę to w notatkach z testów, a nie w JAVIE, ponieważ ma to kluczowe znaczenie przy ewentualnym sprawdzaniu wartości `null`.

### ❓ Pytanie

W tej linii:
```java
Long posParsed = Long.parseLong(listPosAsString);
```

IDE podkreśla na żółto `Long` przed `posParsed` z informacją:  
`Type may be primitive`

Z opcją do kliknięcia:  
`Convert wrapper type to primitive`  
I zamienia to na `long`.

Dlaczego tak?  
Użyć tej podpowiedzi?

### ❗ Odpowiedź

To ostrzeżenie jest **całkowicie normalne** — i **możesz bezpiecznie użyć podpowiedzi**, ale tylko jeśli rozumiesz, co się zmieni.

Wyjaśniam:

#### 🔍 **Dlaczego IDE sugeruje zmianę `Long` → `long`?**

Masz:

```java
Long posParsed = Long.parseLong(listPosAsString);
```

Metoda:

```java
Long.parseLong(...) 
```

zwraca **prymityw typu long**.

Czyli:

* prawej stronie masz `long`
* lewej stronie masz referencję `Long`

Czyli zachodzi **autoboxing** (automatyczne pakowanie prymitywu w obiekt):

```
long → Long
```

IDE mówi:

> *Hej, skoro i tak dostajesz `long`, i nie masz powodu trzymać opakowanego obiektu `Long`, możesz uprościć typ do prymitywu `long`.*

#### 🔍 **Czy warto skorzystać z sugestii?**

##### ✔ Tak — jeśli:

* **nie potrzebujesz możliwości trzymania wartości null**
* typ `posParsed` zawsze ma być liczbą

Czyli:

```java
long posParsed = Long.parseLong(listPosAsString);
```

To jest całkowicie poprawne i nawet *minimalnie bardziej wydajne*.

#### ❗ Kiedy NIE używać sugestii?

Jeśli później chciałbyś zrobić coś takiego:

```java
expectedResponsePutDto.pos = posParsed; // gdzie pos jest typu Long
```

To nadal działa (autoboxing w drugą stronę), ale:

Jeśli *kiedykolwiek* zechcesz:

* przypisać `null`
* sprawdzić `pos == null`
* używać typu obiektowego (np. w kolekcjach, mapach, frameworkach)

— to wtedy **typ `long` byłby błędny**, bo prymityw nie może być `null`.

#### ✔ W twoim przypadku — można zmienić na `long`

Tutaj `posParsed` powstaje wyłącznie przez `Long.parseLong(string)` → więc **nie może być null**.

Dalej przekazujesz go do:

```java
expectedResponsePutDto.pos = posParsed;
```

A to pole ma typ `Long`, więc:

```
long → Long
```

i autoboxing działa automatycznie.

##### ✔ Wniosek:

**Możesz bezpiecznie kliknąć "Convert wrapper type to primitive".**

#### 🔎 Krótkie podsumowanie

| Typ    | Może być null? | Kiedy używać                                    |
|--------|----------------|-------------------------------------------------|
| `long` | ❌ nie          | proste liczby, performance, brak null           |
| `Long` | ✔ tak          | DTO, JSON, bazy danych, null jako brak wartości |

---

## 📄Dokumentacja testów – możliwe formy <a name="test_documentation_forms"></a>

### ✅ **1. „Ładniejsza” i całkowicie ujednolicona wersja Twojego pliku**

Poniżej znajduje się **nowa wersja sekcji „Test coverage” i „Query parameters”**, spójna stylistycznie, z szablonem,
tabelami i pełną czytelnością.

🔎 **Uwaga:**
– zachowuję Twoją treść i pokrycie,  
– dodaję brakujące przypadki,  
– całość jest teraz ujednolicona, uporządkowana, czytelna i skalowalna.

### ✅ **☑ Test coverage (ulepszona wersja + pełna struktura)**

##### 🔧 Legend

* **[P#]** – Positive
* **[N#]** – Negative
* **[X#]** – Technically untestable (e.g. URI > 2000 chars)
* **D** – Default applies
* **I** – Invalid but ignored by API

#### 💠 name `string` (required, 1–16384 chars)

##### 🔹 Summary

| Property   | Value |
|------------|-------|
| Required   | ✔     |
| Min length | 1     |
| Max length | 16384 |
| Affects    | `url` |

##### ✅ **Positive**

* **[P1]** Special characters
* **[P2]** 1 character
* **[P3]** Leading/trailing spaces (check trimming)
* **[P4]** Unicode (emoji, PL chars)
* **[P5]** URL-unsafe characters (encoded)
* **[P6]** Max-length boundary (≈2000 chars due to URI limit)

##### ❌ **Negative**

* **[N1]** Missing
* **[N2]** null
* **[N3]** Empty string
* **[N4]** Only spaces
* **[N5]** Invalid UTF-8
* **[N6]** Non-string type (number, boolean, JSON)
* **[X1]** >2000 chars (URI limit)

### ✅ **2. Gotowy szablon, którego możesz używać dla KAŻDEGO nowego endpointu**

Możesz wkleić to jako template do swoich dokumentów:

#### **📌 Parameter test template**

#### 💠 {name} `{type}` {constraints}

##### 🔹 Summary

| Property | Value |
|----------|-------|
| Required | ✔/❌   |
| Min      | —     |
| Max      | —     |
| Pattern  | —     |
| Default  | —     |
| Notes    | —     |

##### ✅ Positive

* **[P1]** Valid input (baseline)
* **[P2]** Missing (default applies)
* **[P3]** null
* **[P4]** Boundary min
* **[P5]** Boundary max
* **[P6]** Unicode / special characters
* **[P7]** Encoded characters
* **[P8]** Valid alternative representation

##### ❌ Negative

* **[N1]** Empty string
* **[N2]** Wrong type (boolean/number/object)
* **[N3]** Invalid format
* **[N4]** Too short
* **[N5]** Too long
* **[N6]** Unsupported value
* **[N7]** Wrong casing
* **[N8]** Invalid encoding
* **[N9]** Impossible enum combination

##### ❌ Technical / Impossible

* **[X1]** >2000 chars (URI limit)
* **[X2]** Payload too large
* **[X3]** Unsupported structure

### ✅ **3. Proponuję trzy style formatowania testów — możesz wybrać ulubiony**

#### ⭐ **STYLE A – Twój styl, ale ujednolicony (najlepszy do API testing)**

```
- 💠name `string`
  - Positive:
    - [P1] Special characters
    - [P2] 1 character
    - [P3] Unicode
  - Negative:
    - [N1] Missing
    - [N2] Empty
    - [N3] Invalid UTF-8
```

Świetny do pracy *technicznej*, bardzo szybki w czytaniu.

#### ⭐ STYLE B – Tabelaryczny (najbardziej „ładny” dla dokumentacji)

##### name `string`

| Case | Type    | Input         | Expected |
|------|---------|---------------|----------|
| P1   | Valid   | special chars | 200      |
| P2   | Valid   | unicode       | 200      |
| N1   | Invalid | empty         | 400      |
| N2   | Invalid | null          | 400      |
| N3   | Invalid | too long      | X        |

Najbardziej formalny. Idealny, jeśli będzie czytać *ktoś inny*.

#### ⭐ STYLE C – Instrukcja testowa (najbardziej „klikalny”)

```sql
1. Set name = "" → Expect 400
2. Set name = null → Expect 400
3. Set name = "abc" → Expect 200
4. Set name = encoded "%2F%2F" → Expect 200
```

Dobry np. dla manualnych testerów.

---

## 📄URL – kodowanie znaków <a name="url_encoded"></a>

Poniżej masz **wszystkie te znaki zakodowane URL-owo** oraz jeden **łączny string zawierający je wszystkie zakodowane po kolei** ✔

### 🔐 **URL-encoded każdy znak osobno**

| Znak | URL-encoded |
|------|-------------|
| `/`  | `%2F`       |
| `?`  | `%3F`       |
| `#`  | `%23`       |
| `<`  | `%3C`       |
| `>`  | `%3E`       |
| `"`  | `%22`       |
| `'`  | `%27`       |
| `{`  | `%7B`       |
| `}`  | `%7D`       |
| `[`  | `%5B`       |
| `]`  | `%5D`       |
| `%`  | `%25`       |

### 🔗 **JEDEN string zawierający wszystkie zakodowane znaki w kolejności jak podałeś**

```
%2F%3F%23%3C%3E%22%27%7B%7D%5B%5D%25
```

---

## 📄SEED – informacje <a name="seed_information"></a>

```text
Wytłumacz mi dokładniej o co chodzi w tym punkcie:
2️⃣ Random / Faker – deterministyczność testów 🎯
```

Świetne pytanie — to **bardzo ważny temat w testach automatycznych**, a często pomijany 👍  
Wyjaśnię to **krok po kroku**, na przykładach z życia testera API.

### 🔴 Problem: „losowość” w testach

Masz w kodzie:

```java
private static final Random RANDOM = new Random();
private static final Faker faker = new Faker();
```

To oznacza, że:

* **każde uruchomienie testów generuje inne dane**
* test, który dziś przechodzi, **jutro może się wywalić**
* jeśli test padnie → **nie jesteś w stanie odtworzyć danych**, które go zepsuły

#### Przykład z testu API

```java
String email = faker.internet().emailAddress();

given()
    .body(new User(email))
.when()
    .post("/users")
.then()
    .statusCode(201);
```

Dziś:

```
email = john_1987@test.com → 201 OK
```

Jutro:

```
email = admin@test.com → 409 Conflict
```

➡️ Test „losowo” zaczyna failować.

### 🟡 Co to jest deterministyczność?

**Test deterministyczny**:

> Dla tych samych danych wejściowych → zawsze ten sam wynik

Czyli:

* te same randomy
* te same dane
* te same requesty

### 🧠 Seed – klucz do kontroli losowości

#### Co to jest seed?

**Seed** to liczba startowa dla generatora losowego.

```java
new Random(12345);
```

Dla tego samego seed:

* `nextInt()`
* `nextBoolean()`
* `faker.name().firstName()`

👉 **zawsze dadzą ten sam wynik**

### 🔁 Bez seeda vs z seedem

#### ❌ Bez seeda

```java
Random r = new Random();

System.out.println(r.nextInt(100)); // za każdym razem inna liczba
```

#### ✅ Z seedem

```java
Random r = new Random(42);

System.out.println(r.nextInt(100)); // ZAWSZE ta sama liczba
```

### 🧪 Dlaczego to jest krytyczne w testach?

#### 1️⃣ Debugowanie

Test failuje na CI:

```
Expected status code 201 but was 400
```

Masz seed w logach:

```
Using test.seed=12345
```

Uruchamiasz lokalnie:

```bash
mvn test -Dtest.seed=12345
```

➡️ Dostajesz **identyczne dane**  
➡️ Możesz **odtworzyć błąd 1:1**

#### 2️⃣ Stabilność testów

Bez seeda:

* flaki
* niereprodukowalne błędy
* „u mnie działa”

Z seedem:

* fail = realny problem
* brak losowych konfliktów danych
* przewidywalność

### 🧰 Jak to wdrożyć w praktyce

#### Centralny seed

```java
public final class RandomUtils {

    private static final long SEED =
            Long.getLong("test.seed", System.currentTimeMillis());

    public static final Random RANDOM = new Random(SEED);
    public static final Faker FAKER = new Faker(RANDOM);

    static {
        System.out.println("Using test.seed=" + SEED);
    }

    private RandomUtils() {}
}
```

#### Użycie w testach

```java
String username = RandomUtils.FAKER.name().username();
int age = RandomUtils.RANDOM.nextInt(18, 65);
```

### 🟢 A co z pełną losowością?

Czasem **chcesz chaos** (np. testy fuzzingowe).

Rozwiązanie:

* domyślnie seed
* opcjonalnie wyłączalny

```bash
mvn test -Dtest.seed=$(date +%s)
```

Albo:

```java
-Dtest.seed=RANDOM
```

### 🧠 Złota zasada testera

> **Losowość jest dobra do generowania danych  
> Determinizm jest niezbędny do debugowania**

### TL;DR

* Faker i Random bez seeda = niestabilne testy
* Seed = powtarzalność
* Powtarzalność = debugowalność
* Najlepiej:

    * seed z `-Dtest.seed`
    * logowanie seeda
    * jeden `Random` / `Faker` globalnie

```text
Właśnie po to używam tego bez seed'a żeby dane były losowe.
Chyba tak powinno być
```

Masz **rację co do intencji** 👍 — i bardzo dobrze ją czujesz.  
Losowe dane **są potrzebne** w testach API.  
Problem nie polega na *tym, że używasz losowości*, tylko **na braku kontroli nad nią**.

Pozwól, że to precyzyjnie rozdzielę, bo tu jest kluczowa różnica, o którą często toczy się ta dyskusja.

### ✅ Masz rację: losowość jest dobra (a często konieczna)

Używasz randomów, bo chcesz:

* unikać konfliktów danych (`email`, `username`)
* testować różne kombinacje inputów
* łapać edge case’y
* nie testować w kółko „John / [test@test.com](mailto:test@test.com)”

To jest **100% poprawne** podejście.

### ❌ Ale: „losowość bez kontroli” to problem

Aktualnie masz:

```java
new Random();
new Faker();
```

Czyli:

* **losowość nieodtwarzalna**
* brak możliwości cofnięcia czasu
* fail na CI = zagadka

#### Przykład realnego problemu

Test padł:

```
Expected 201 but was 400
```

W logach nie masz:

* użytego emaila
* username
* danych requestu
* seeda

➡️ **nie jesteś w stanie odtworzyć scenariusza**

### 🧠 Kluczowe rozróżnienie

#### ❌ „Albo losowość, albo deterministyczność”

To fałszywa alternatywa.

#### ✅ Prawidłowe podejście:

> **Losowość + możliwość jej odtworzenia**

Seed **nie wyłącza losowości**  
Seed **umożliwia jej powtórzenie**

### 🔁 Co naprawdę daje seed (bez zabierania losowości)

#### Domyślnie (jak teraz)

```bash
mvn test
```

* dane są losowe ✔️
* każdy run inny ✔️

#### Gdy test się wywali

Z logów:

```
Using test.seed=1715274012345
```

I wtedy:

```bash
mvn test -Dtest.seed=1715274012345
```

➡️ **ten sam chaos**  
➡️ **ten sam błąd**  
➡️ **debugowanie możliwe**

### 🧪 To jest dokładnie ten sam model, którego używają:

* testy property-based
* fuzzing
* QuickCheck
* Hypothesis
* duże zespoły QA/Backend

Losowość ≠ brak kontroli.

### ⚠️ Co się dzieje bez seeda (praktyka zespołowa)

Po czasie:

* testy flaky
* retry na CI
* ignorowanie faili
* „odpal jeszcze raz”
* brak zaufania do testów

To **nie teoria**, tylko klasyka projektów testowych.

### 🟢 Najlepszy kompromis (polecany)

```java
long seed = Long.getLong("test.seed", System.currentTimeMillis());
Random random = new Random(seed);
Faker faker = new Faker(random);

log.info("Test seed: {}", seed);
```

* 99% czasu → pełna losowość
* 1% czasu → pełna powtarzalność
* 0% frustracji

### 🧠 Złota zasada (praktyczna, testerska)

> **Losowe dane są dobre  
> Nieodtwarzalne testy są złe**

Ty **robisz dobrą rzecz** używając randomów.  
Seed to tylko **pas bezpieczeństwa**, nie kaganiec.

---

## 📄JUnit – rozdzielenie uruchamiania testów API i jednostkowych <a name="junit_api_unit_tests"></a>

> Powiedz mi jak teraz oddzielić testy jednostkowe od testów api?  
> Wydaje mi się, że nie powinny być zawsze wszystkie uruchamiane?

Świetne pytanie — to jest **dokładnie ten moment**, w którym projekt testowy zaczyna być *dojrzały*, a nie „wszystko w jednym worku” 👌  
Masz rację: **nie powinny być zawsze uruchamiane wszystkie testy**.

Poniżej masz **praktyczny, sprawdzony sposób**, który idealnie pasuje do JUnit + REST Assured.

### TL;DR – jak to zrobić dobrze

**Oddzielasz testy na 2 poziomach:**
1. **struktura pakietów**
2. **tagi JUnit (`@Tag`) + konfiguracja builda**

### 1️⃣ Struktura pakietów (fundament)

Ty już jesteś bardzo blisko ideału 👍

#### Rekomendowana struktura

```text
src
└── test
    └── java
        ├── tests
        │   ├── unit
        │   │   ├── utils
        │   │   └── response
        │   └── api
        │       ├── boards
        │       ├── cards
        │       └── lists
```

#### Co tu jest ważne?

* **unit** – szybkie, bez sieci, bez REST Assured
* **api** – wolniejsze, sieć, tokeny, environment

To już samo w sobie daje czytelność, ale **nie steruje wykonaniem**.

### 2️⃣ Tagi JUnit 5 – klucz do kontroli uruchamiania

#### Oznaczasz testy:

##### Testy jednostkowe

```java
@Tag("unit")
class UtilsStringTest {
}
```

##### Testy API

```java
@Tag("api")
class BoardsApiTest {
}
```

Możesz też tagować **metody**, ale **klasy są czytelniejsze**.

### 3️⃣ Maven – sterowanie co się uruchamia

Zakładam Maven (jeśli Gradle – powiedz, dam wersję gradle).

#### Domyślnie: tylko testy jednostkowe

```xml
<build>
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-surefire-plugin</artifactId>
            <version>3.2.5</version>
            <configuration>
                <groups></groups>
                <excludedGroups></excludedGroups>
                <properties>
                    <includeTags>unit</includeTags>
                </properties>
            </configuration>
        </plugin>
    </plugins>
</build>
```

👉 `mvn test`  
➡️ **tylko unit**

#### Profil: testy API (świadomie)

```xml
<profiles>
    <profile>
        <id>api-tests</id>
        <build>
            <plugins>
                <plugin>
                    <groupId>org.apache.maven.plugins</groupId>
                    <artifactId>maven-surefire-plugin</artifactId>
                    <configuration>
                        <properties>
                            <includeTags>api</includeTags>
                        </properties>
                    </configuration>
                </plugin>
            </plugins>
        </build>
    </profile>
</profiles>
```

👉 Uruchomienie:

```bash
mvn test -Papi-tests
```

### 4️⃣ IDE (IntelliJ / Eclipse)

#### IntelliJ

* Run → Edit Configurations
* JUnit
* **Tags**:
    * `unit`
    * albo `api`

Masz 2 konfiguracje:
* 🧪 Unit Tests
* 🌐 API Tests

### 5️⃣ Naming (opcjonalnie, ale polecam)

Nie zamiast tagów, tylko **obok**.

| Typ  | Przykład          |
|------|-------------------|
| Unit | `UtilsStringTest` |
| API  | `BoardsApiTest`   |

### 6️⃣ Co ZYSKUJESZ

✔ szybkie feedback loop  
✔ `mvn test` = bezpieczne  
✔ API tylko gdy chcesz  
✔ brak przypadkowych requestów  
✔ CI może:
* na PR: unit
* nightly: api

### 7️⃣ Najczęstszy błąd (którego u Ciebie nie będzie)

❌ uruchamianie API testów lokalnie „bo są w tym samym module”  
❌ brak tagów  
❌ REST Assured w testach unit

Ty już jesteś **po właściwej stronie tej granicy** 😄

### Moja rekomendacja finalna

**Minimalny zestaw obowiązkowy:**
* pakiety `tests.unit` / `tests.api`
* `@Tag("unit")`, `@Tag("api")`
* Maven profile
