# ☕Java — notatki

# 📑Spis treści

- [ENV — Zmienne środowiskowe](#env--zmienne-środowiskowe)
- [Enum](#enum)
- [Typ zmiennej – Long](#typ-zmiennej--long)
- [Set Up (setUp) czy Setup? – prawidłowe nazewnictwo](#set-up-setup-czy-setup--prawidłowe-nazewnictwo)
- [Interfejsy – metody abstrakcyjne](#interfejsy--metody-abstrakcyjne)
- [Interfejsy znacznikowe – co to jest?](#interfejsy-znacznikowe--co-to-jest)
- [Static initialization block – blok statycznej inicjalizacji](#static-initialization-block--blok-statycznej-inicjalizacji)
- [Resources vs. ścieżki plików](#resources-vs-ścieżki-plików)

---

# 📝Opis

## 📄ENV — Zmienne środowiskowe

Do ustawiania i zarządzania zmiennymi środowiskowymi możemy użyć biblioteki `dotenv-java`.

**Nazwa:**  
`Dotenv Java`

**Link do GitHub (dokumentacja):**  
https://github.com/cdimascio/dotenv-java

**Link do Maven:**  
https://mvnrepository.com/artifact/io.github.cdimascio/dotenv-java

1. Dodajemy ją w Maven (oczywiście aktualną wersję, poniżej tylko przykład):
    ```maven
    <!-- https://mvnrepository.com/artifact/io.github.cdimascio/dotenv-java -->
    <dependency>
        <groupId>io.github.cdimascio</groupId>
        <artifactId>dotenv-java</artifactId>
        <version>3.0.2</version>
    </dependency>
    ```
2. Musimy zdecydować gdzie będziemy chcieli umieścić plik `.env`:
    - Główny katalog z projektem:
        - Projekt używa wielu narzędzi, które domyślnie szukają .env w katalogu głównym (np. docker-compose, narzędzia CI/CD).
        - Chcesz wyraźnie oddzielić pliki środowiskowe od kodu aplikacji.
        - W zespole są deweloperzy korzystający z różnych języków, gdzie trzymanie .env w katalogu głównym jest standardem.
    - Katalog `/resources`:
        - Twój projekt jest ściśle związany z Javą i korzysta z ekosystemu JVM (np. Spring Boot).
        - Chcesz, aby pliki środowiskowe były automatycznie dostępne w classpath.
        - Potrzebujesz bardziej uporządkowanej struktury w projekcie.
    - Ważne uwagi:
        - Jeśli stworzymy sam plik `.env` w głównym katalogu z projektem lub w katalogu `src/main/resources`, to wtedy
          w deklaracji obiektu wystarczy samo:  
          `Dotenv dotenv = Dotenv.load();`
        - Natomiast jeśli utworzymy go gdzieś indziej lub umieścimy w jakimś pod-katalogu, to wtedy będzie konieczne podanie
          ścieżki do tego pliku w deklaracji obiektu:  
          `Dotenv dotenv = Dotenv.configure().directory("./environment").load();`
3. Przed dodaniem pliku musimy nasz `.env` dopisać w `.gitignore`:
   ```gitignore
   # Project environment
   environment/.env
   ```
4. Jeśli na zmienne środowiskowe stworzyliśmy osobny katalog warto dodać w nim plik `.env.example`.  
   Możemy do niego zapisywać puste zmienne środowiskowe, aby reszta członków zespołu miała do pobrania jakiś wzornik.
5. Tworzymy plik `.env` w głównym katalogu projektu lub gdzie chcemy np.:
    ```.env
    USERNAME=yourUsername
    PASSWORD=yourPassword
    ```
6. Wczytujemy zmienne środowiskowe z pliku `.env`:
    ```java
    import io.github.cdimascio.dotenv.Dotenv;
    
    public class EnvExample {
        public static void main(String[] args) {
            Dotenv dotenv = Dotenv.load();
            // lub
            Dotenv dotenv = Dotenv.configure().directory("./environment").load();
            String username = dotenv.get("USERNAME");
            String password = dotenv.get("PASSWORD");
    
            System.out.println("Username: " + username);
            System.out.println("Password: " + password);
        }
    }
    ```

---

## 📄Enum

Stosowaną praktyką dla enumów jest zapisywanie ich wielkimi literami jako stałe.  
Czasami jednak potrzebujemy je podawać z konkretną wielkością znaków.  
Najlepiej wtedy zrobić tak, aby metoda używała wartości enuma, a nie jego nazwy z kodu.

**Oto przykład:**

ENUM:
```java
package enums.labels;

public enum LabelField {
    NAME("name"),
    COLOR("color");

    private final String value;

    LabelField(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
```

PRZYPISANIE:
```java
public static Response putUpdateFieldOnLabel(String id, LabelField field, String value) {

    RequestSpecification spec = given().
            spec(requestSpecificationCommon).
            queryParam("value", value);

    return spec.
            when().
                put(url + "/" + id + "/" + field.getValue()).   <-----------------------
            then().
                extract().
                response();
}
```

WYWOŁANIE:
```java
responsePut = putUpdateFieldOnLabel(labelId, LabelField.NAME, labelFieldValue);
responsePut = putUpdateFieldOnLabel(labelId, LabelField.COLOR, labelFieldValue);
```

---

## 📄Typ zmiennej – Long

### Problem

Nie dało się zadeklarować takiej zmiennej:
```java
Long listPos4 = 140737488322560;
```

IDE podkreślało wartość na czerwono z dopiskiem:  
`Integer number too large`

### Rozwiązanie

Oznacza, że **Java domyślnie interpretuje liczby całkowite (bez sufiksu)** jako typ `int`, który ma zakres:
➡️ od **-2 147 483 648** do **2 147 483 647**.

Liczba:  
`140737488322560`  
jest **dużo większa** od maksymalnego `int`, więc kompilator zgłasza błąd.

#### ✅ **Rozwiązanie: dodaj sufiks `L` lub `l`**

Wystarczy dodać na końcu **literę `L` (dużą!)**, aby Java wiedziała, że chodzi o typ `long`:

```java
Long listPost4 = 140737488322560L;
```

#### 🧠 Dodatkowe uwagi:

* Zawsze używaj **dużej litery `L`**, bo małe `l` wygląda jak `1`.
* Możesz też użyć **podkreśleń** w liczbach dla czytelności:
  ```java
  Long listPost4 = 140_737_488_322_560L;
  ```

To nie zmienia wartości, ale poprawia czytelność.

#### 📌 **Podsumowanie:**

> ➜ Dodaj `L` na końcu dużych liczb, które mają być typu `long`.  
> ➜ `Long listPost4 = 140737488322560L;` — to poprawna i zalecana wersja.

---

## 📄Set Up (setUp) czy Setup? – prawidłowe nazewnictwo

W programowaniu piszemy:  
`Set up` czy `Setup`?

Albo nazwy metod w Java:  
`setUpAll()` czy `setupAll()`?

To bardzo dobre pytanie — i dobrze, że je zadajesz, bo tu **łatwo o niekonsekwencję**.

### 🔹 Skrótowa odpowiedź

**W Javie i w testach poprawnie piszemy:**

> ✅ **setUp**  
> ❌ setup

Czyli:

```java
setUp()
setUpAll()
tearDown()
tearDownAll()
```

### 🔍 Dlaczego **setUp**, a nie **setup**?

#### 1️⃣ Różnica językowa (angielski)

* **to set up** → **czasownik** (coś ustawić)
* **a setup** → **rzeczownik** (konfiguracja)

W kodzie:

* **metody = czynności (czasowniki)**
* **zmienne/klasy = rzeczy (rzeczowniki)**

Dlatego:

```java
setUp()      // ✔ ustawia środowisko
tearDown()  // ✔ sprząta środowisko
```

A nie:

```java
setup()     // ❌ brzmi jak rzeczownik
```

### 🧪 Konwencja testowa (JUnit, TestNG)

JUnit i TestNG **historycznie i konwencyjnie** używają:

```java
@BeforeEach
void setUp() {}

@AfterEach
void tearDown()

@BeforeAll
static void setUpAll()

@AfterAll
static void tearDownAll()
```

To jest **de facto standard branżowy**.

### 🚫 Kiedy można użyć `setup`?

Tylko gdy jest to **rzeczownik**, np.:

```java
class TestSetup {}
SetupConfiguration config;
```

Ale **nie** dla metod wykonujących akcję.

### ✅ Podsumowanie (do zapamiętania)

| Kontekst        | Poprawnie                  |
|-----------------|----------------------------|
| Metoda          | `setUp()`                  |
| Metoda          | `setUpAll()`               |
| Metoda          | `tearDown()`               |
| Metoda          | `tearDownAll()`            |
| Zmienna / klasa | `SetupConfig`, `TestSetup` |

---

## 📄Interfejsy – metody abstrakcyjne

Co oznacza ta linia:  
`String key();`

Świetne pytanie — to jest **bardzo ważny element**, który warto dobrze zrozumieć 👍  
Wyjaśnijmy to **krok po kroku**, na spokojnie.

### 1️⃣ Gdzie to występuje?

Linia:

```java
String key();
```

pojawia się u Ciebie **w interfejsie**, np.:

```java
public interface QueryParam {
    String key();
}
```

### 2️⃣ Co to dokładnie oznacza?

#### 🔹 To jest **deklaracja metody w interfejsie**

Czyli:

* **nie ma ciała metody**
* **nie ma implementacji**
* mówi tylko:  
  👉 *„Każda klasa / enum, który implementuje ten interfejs, MUSI mieć metodę `key()` zwracającą `String`”*

To jest **kontrakt**, nie implementacja.

### 3️⃣ Jak to się ma do enumów?

Spójrz na przykład enuma:

```java
public enum BoardCommonQueryParam implements QueryParam {

    NAME("name"),
    DESC("desc"),
    CLOSED("closed");

    private final String key;

    BoardCommonQueryParam(String key) {
        this.key = key;
    }

    @Override
    public String key() {
        return key;
    }
}
```

#### Co się tu dzieje?

* enum **implementuje `QueryParam`**
* więc **musi** zaimplementować metodę `key()`
* metoda `key()` zwraca **rzeczywistą nazwę parametru w API**

### 4️⃣ Dlaczego to nie jest np. pole `String key`?

Bo:

❌ pole:

```java
String key;
```

* **nie wymusza niczego** na implementujących klasach
* nie jest częścią kontraktu
* interfejsy nie trzymają stanu (pól instancyjnych)

✔ metoda:

```java
String key();
```

* **wymusza** istnienie tej metody
* pozwala na **polimorfizm**
* umożliwia używanie różnych enumów w jednym miejscu

### 5️⃣ Dlaczego to jest takie dobre w Twoim przypadku?

Dzięki temu możesz pisać:

```java
protected void putIfNotNull(
        Map<String, Object> params,
        QueryParam param,
        Object value
) {
    if (value != null) {
        params.put(param.key(), value);
    }
}
```

I **nie obchodzi Cię**, czy `param` pochodzi z:

* `BoardCommonQueryParam`
* `BoardPostQueryParam`
* `LabelQueryParam`
* `ListQueryParam`

Każdy z nich **ma metodę `key()`**, bo implementuje interfejs.

### 6️⃣ Jak to działa „pod maską”?

To jest klasyczny **polimorfizm**:

```java
QueryParam param = BoardCommonQueryParam.NAME;
String k = param.key(); // "name"
```

Java:

* patrzy na **rzeczywisty typ obiektu**
* wywołuje **konkretną implementację `key()`**
* nie interesuje jej, że zmienna jest typu `QueryParam`

### 7️⃣ Mentalny model (najważniejsze)

> # `String key();`
>
> **„Każdy query param MUSI wiedzieć, jak nazywa się w API”**

To wszystko 🙂

### 8️⃣ TL;DR

* `String key();` to **metoda abstrakcyjna w interfejsie**
* wymusza implementację w enumach
* umożliwia wspólną obsługę różnych parametrów
* to **fundament Twojej architektury payloadów**

---

## 📄Interfejsy znacznikowe – co to jest?

Już tłumaczę **DLACZEGO zaproponowałem**:

```java
public interface PostQueryParam extends QueryParam {}
public interface PutQueryParam extends QueryParam {}
```

i **kiedy to ma sens**, a kiedy **nie trzeba** tego robić.

### 1️⃣ Co to w ogóle jest?

To są **interfejsy znacznikowe drugiego poziomu**.

Schemat:

```
QueryParam
   ├── PostQueryParam
   └── PutQueryParam
```

One:

* **nic nie dodają technicznie**
* **nie mają metod**
* ale **niosą znaczenie semantyczne**

### 2️⃣ Jaki problem one rozwiązują?

#### Problem: te same parametry ≠ te same operacje

W Trello (i większości API):

| Parametr      | POST | PUT |
|---------------|------|-----|
| name          | ✅    | ✅   |
| desc          | ✅    | ✅   |
| idBoardSource | ✅    | ❌   |
| closed        | ❌    | ✅   |
| subscribed    | ❌    | ✅   |

Jeżeli wszystko wrzucisz do jednego worka:

```java
enum BoardQueryParam implements QueryParam { ... }
```

➡️ **nic Cię nie powstrzyma**, żeby:

* użyć POST-only parametru w PUT
* użyć PUT-only parametru w POST
* napisać test logicznie błędny, który *„przechodzi”*

### 3️⃣ Co dają `PostQueryParam` i `PutQueryParam`?

#### 🔹 1. Dokumentacja w kodzie (najważniejsze)

To jest **czytelniejsze niż Javadoc**:

```java
enum BoardPostQueryParam implements PostQueryParam {
    ID_BOARD_SOURCE("idBoardSource"),
    KEEP_FROM_SOURCE("keepFromSource");
}
```

Już z samej nazwy wiesz:  
➡️ *tego NIE używaj w PUT*

#### 🔹 2. Kompilator jako strażnik (opcjonalnie)

Możesz napisać:

```java
protected static void putIfNotNull(
        Map<String, Object> params,
        PutQueryParam param,
        Object value
) { ... }
```

I wtedy:

```java
putIfNotNull(params, BoardPostQueryParam.ID_BOARD_SOURCE, value);
```

❌ **nie skompiluje się**

To jest **złoto** w większym projekcie.

#### 🔹 3. Skalowalność frameworka testowego

Dzisiaj:

* POST
* PUT

Jutro:

* PATCH
* DELETE
* COPY
* MOVE

Dodajesz:

```java
interface PatchQueryParam extends QueryParam {}
```

i **nic nie psujesz**.

### 4️⃣ Czy to jest zawsze potrzebne?

#### ❌ NIE, jeśli:

* masz 2–3 endpointy
* testy piszesz sam
* API jest bardzo proste

#### ✅ TAK, jeśli:

* testów jest dużo
* API ma dużo wyjątków
* chcesz, żeby framework **bronił się sam**
* testy mają być utrzymywane miesiącami

Ty **już jesteś w tym drugim przypadku**.

### 5️⃣ Czy to jest overengineering?

👉 **Nie w Twoim kontekście**

Overengineering byłby wtedy, gdybyś:

* miał 1 payload
* 3 parametry
* 2 testy

U Ciebie:

* wiele endpointów
* wiele payloadów
* wiele kombinacji
* długofalowy framework

To jest **właściwy poziom abstrakcji**.

### 6️⃣ Jak to się składa w całość (pełny obraz)

```java
interface QueryParam {
    String key();
}

interface PostQueryParam extends QueryParam {}
interface PutQueryParam extends QueryParam {}

enum BoardCommonQueryParam implements PostQueryParam, PutQueryParam {
    NAME("name"),
    DESC("desc"),
    ID_ORGANIZATION("idOrganization");
}

enum BoardPostQueryParam implements PostQueryParam {
    ID_BOARD_SOURCE("idBoardSource"),
    KEEP_FROM_SOURCE("keepFromSource");
}

enum BoardPutQueryParam implements PutQueryParam {
    CLOSED("closed"),
    SUBSCRIBED("subscribed");
}
```

### 7️⃣ Dlaczego zaproponowałem to Tobie konkretnie?

Bo:

* już masz **BasePayload**
* już masz **buildery**
* już unikasz magicznych stringów
* już myślisz o **wspólnych helperach**

Ten krok:
➡️ **domyka architekturę**

### TL;DR

Zaproponowałem:

```java
PostQueryParam
PutQueryParam
```

bo one:

* kodują **zasady API** w typach
* zapobiegają błędom logicznym
* dokumentują różnice POST vs PUT
* pozwalają kompilatorowi pilnować testów
* skalują się wraz z projektem

---

## 📄Static initialization block – blok statycznej inicjalizacji

Świetnie, że o to pytasz — bo to **bardzo ważny, ale często niezrozumiały element Javy**.  
Wyjaśnię to jasno i „testersko”, bez akademickiego bełkotu.

### 🔹 Co to jest ta sekcja?

```java
static {
    System.out.println("Using {test.seed} = " + SEED);
}
```

To jest **blok statycznej inicjalizacji** (*static initialization block*).

### 🧠 Co to oznacza w praktyce?

#### 1️⃣ Kiedy ten kod się wykona?

➡️ **Dokładnie raz**  
➡️ **W momencie pierwszego załadowania klasy `RandomProvider` przez JVM**

Czyli:

* gdy **pierwszy raz** użyjesz:

  ```java
  RandomProvider.random();
  ```

  albo

  ```java
  RandomProvider.faker();
  ```

* **nie przy każdym teście**

* **nie przy każdym wywołaniu metody**

#### 2️⃣ Dlaczego on tam jest?

Ten blok służy do:

✔️ wykonania kodu *raz*  
✔️ po inicjalizacji pól statycznych  
✔️ zanim ktokolwiek zacznie korzystać z klasy

W Twoim przypadku:

```java
private static final long SEED = ...
private static final Random RANDOM = ...
private static final Faker FAKER = ...
```

➡️ **SEED jest już ustalony**  
➡️ **RANDOM i FAKER są już gotowe**  
➡️ dopiero wtedy wypisujesz informację

### 🧪 Po co logować seed?

#### Praktyczny sens (najważniejsze)

Jeśli test się wysypie na CI, w logach zobaczysz np.:

```
Using {test.seed} = 1715274012345
```

I możesz:

```bash
mvn test -Dtest.seed=1715274012345
```

➡️ odtworzyć dokładnie ten sam przebieg danych  
➡️ debugować lokalnie  
➡️ nie zgadywać

### 🔍 Dlaczego to NIE jest konstruktor?

Bo:

* klasa jest `final`
* konstruktor jest `private`
* **nie tworzysz instancji**

Nie ma:

```java
new RandomProvider();
```

➡️ więc **konstruktor nigdy by się nie wykonał**

Static block to **jedyny sensowny sposób** wykonania kodu „na start”.

### ⚠️ Co można, a czego nie powinno się robić w static block?

#### ✅ Dobre zastosowania

* logowanie konfiguracji
* walidacja zmiennych środowiskowych
* inicjalizacja statycznych zasobów
* sprawdzenie `test.seed`

#### ❌ Złe zastosowania

* logika testowa
* I/O (poza logami)
* wywołania API
* ciężkie obliczenia

### 🧠 Mała poprawka stylistyczna (polecam)

Teraz masz:

```java
System.out.println("Using {test.seed} = " + SEED);
```

Lepiej:

```java
System.out.println("Using test.seed = " + SEED);
```

Albo (jeszcze lepiej, jeśli masz logger):

```java
log.info("Using test.seed = {}", SEED);
```

### 🧾 TL;DR

* To jest **blok statyczny**
* Wykonuje się **raz**, przy ładowaniu klasy
* Służy do:
    * logowania
    * inicjalizacji
    * setupu globalnego
* W Twoim przypadku:
    * loguje seed
    * umożliwia debugowanie testów
* Jest tu **jak najbardziej na miejscu** 👍

---

## 📄Resources vs. ścieżki plików

### 🧠 O co chodzi: *resources* vs *ścieżki plików*

W Javie są **dwa różne światy**:

#### 1️⃣ File system (Paths / Files)

```java
Paths.get("src/test/resources/request.json")
Files.readString(...)
```

➡️ działa **tylko wtedy**, gdy:

* pliki faktycznie istnieją na dysku
* uruchamiasz testy z projektu (IDE, Maven)

❌ **nie działa**, gdy:

* aplikacja/testy są uruchomione z JAR-a
* resource jest w classpath, a nie na FS

#### 2️⃣ Classpath resources (to, czym są `resources`)

Pliki w:

```
src/main/resources
src/test/resources
```

Po buildzie:

* **nie są plikami**
* są wpisami w **classpath**
* mogą być:

    * w katalogu
    * w JAR
    * w JAR w JAR-ze (fat jar)

➡️ **Nie masz gwarancji ścieżki plikowej**

### 🔴 Co jest nie tak z Twoją metodą

Twoja metoda:

```java
Files.readString(Paths.get(relativePath), StandardCharsets.UTF_8);
```

Zakłada, że:

* `relativePath` istnieje na dysku
* `Paths.get()` ma sens

To jest **fałszywe założenie dla resources**.

### ❌ Konkretne problemy

#### ❌ 1. Nie działa w JAR

```text
java.nio.file.NoSuchFileException
```

bo:

* resource jest wewnątrz JAR
* nie ma realnej ścieżki

#### ❌ 2. Zależność od katalogu roboczego

```bash
mvn test
```

vs

```bash
java -jar tests.jar
```

➡️ inny working directory  
➡️ testy zaczynają losowo failować

### ✅ Jak powinno się czytać resources

#### ✔️ Poprawny sposób: ClassLoader

```java
InputStream is = ClassLoader
        .getSystemResourceAsStream("requests/createBoard.json");
```

albo:

```java
UtilsCommon.class
        .getClassLoader()
        .getResourceAsStream("requests/createBoard.json");
```

To:

* działa w IDE
* działa w Mavenie
* działa w JAR
* działa zawsze

### ✅ Poprawiona wersja Twojej metody

```java
public static String readResourceFileAsString(String resourcePath) {
    try (InputStream is = UtilsCommon.class
            .getClassLoader()
            .getResourceAsStream(resourcePath)) {

        if (is == null) {
            throw new IllegalArgumentException("Resource not found: " + resourcePath);
        }

        return new String(is.readAllBytes(), StandardCharsets.UTF_8);

    } catch (IOException e) {
        throw new RuntimeException("Failed to read resource: " + resourcePath, e);
    }
}
```

### 🧪 Jak tego używać w testach

Struktura:

```
src/test/resources/
└── requests/
    └── createBoard.json
```

Test:

```java
String body = readResourceFileAsString("requests/createBoard.json");
```

❌ NIE:

```java
"src/test/resources/requests/createBoard.json"
```

### 🎯 Dlaczego to jest ważne w testach API

W testach często:

* trzymasz request body w JSON
* trzymasz response fixtures
* porównujesz payloady

Jeśli używasz `Paths.get()`:

* testy są **kruche**
* działają „u mnie”
* padają na CI / Dockerze / JAR

### TL;DR

**Resources ≠ files**

❌ `Paths.get()` → file system  
✅ `ClassLoader.getResourceAsStream()` → classpath

Twoja metoda:

* działa lokalnie
* **nie jest future-proof**

Po poprawce:  
✔️ działa wszędzie  
✔️ odporna na JAR  
✔️ poprawna architektonicznie
