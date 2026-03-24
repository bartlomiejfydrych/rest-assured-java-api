# 🛠Konfiguracja projektu – notatki

# 📑Spis treści

1. [🔧Konfiguracja](#konfiguracja)
   - [Tworzenie nowego projektu](#tworzenie-nowego-projektu)
   - [Instalacja pluginów](#instalacja-pluginów)
   - [Dodawanie projektu do GitHub](#dodawanie-projektu-do-github)
   - [Dodawanie dependencies do Maven](#dodawanie-dependencies-do-maven)
   - [Dalsze kroki — rozpoczęcie pisania testów](#dalsze-kroki--rozpoczęcie-pisania-testów)
2. [🧩Dodatkowe](#dodatkowe)
   - [Typo — poprawienie błędów w tekście dla plików (głównie Markdown) pisanych w języku polskim](#typo--poprawienie-błędów-w-tekście-dla-plików-głównie-markdown-pisanych-w-języku-polskim)
   - [Markdown — wyłączenie podkreślania błędów we fragmentach kodu](#markdown--wyłączenie-podkreślania-błędów-we-fragmentach-kodu)
   - [Maven dependencies — ustawianie zmiennych dla numerów wersji](#ⓜmaven-dependencies--ustawianie-zmiennych-dla-numerów-wersji)
   - [Jackson Databind vs. Jakarta JSON Processing API (JSON-P) – porównanie](#jackson-databind-vs-jakarta-json-processing-api-json-p--porównanie)
3. [🔌Pluginy do IDE — opis](#pluginy-do-ide--opis)
   - [.ignore](#ignore)
   - [Rainbow Brackets](#rainbow-brackets)
   - [Key Promoter X](#key-promoter-x)
   - [Lombok](#lombok)
   - [Allure Report](#allure-report)
4. [📚Dependencies — opis](#dependencies--opis)
   - [⭐Uniwersalne](#uniwersalne)
     - [JUnit Jupiter (Aggregator)](#junit-jupiter-aggregator)
     - [JUnit Platform Suite (Aggregator)](#junit-platform-suite-aggregator)
     - [AssertJ Core](#assertj-core)
     - [Java Faker](#java-faker)
     - [Dotenv Java](#dotenv-java)
     - [Allure Report](#allure-report)
     - [Project Lombok](#project-lombok)
   - [🌐Backend](#backend)
     - [REST Assured](#rest-assured)
     - [Hibernate Validator Engine](#hibernate-validator-engine)
     - [Jakarta Validation API](#jakarta-validation-api)
     - [Jakarta Expression Language Implementation](#jakarta-expression-language-implementation)
     - [Jakarta Expression Language API](#jakarta-expression-language-api)
     - [JSONassert](#jsonassert)
     - [JSON Schema Validator](#json-schema-validator)
     - [Project Lombok](#project-lombok)
     - [Jackson Databind](#jackson-databind)
     - [Jakarta JSON Processing API (JSON-P)](#jakarta-json-processing-api-json-p)

---

# 🔧Konfiguracja

## Tworzenie nowego projektu

1. Instalujemy `IntelliJ IDEA`
    - `JDK` — Jest już wbudowane w IDE. Nie trzeba go pobierać osobno tak jak kiedyś
    - `Zmienne środowiskowe` — nimi również nie musimy się przejmować tak jak kiedyś
2. Klikamy **utworzenie nowego projektu**
    - Jeżeli już jesteśmy w jakimś projekcie to:
        - Klikamy na `Hamburger Menu` w lewym, górnym rogu
        - Klikamy `New`
        - Klikamy `Project`
3. Podajemy **nazwę projektu**
    - Dla projektów Git najlepiej stosować format `nazwa-mojego-projektu`
4. Wybieramy **lokalizację** projektu
5. Pomijamy `Create new Git repository`, ponieważ lepiej to zrobić jak będziemy mieć już skonfigurowany plik `.gitignore`
6. W kolumnie po lewej w sekcji `New project` mamy mieć zaznaczone `Java`
7. Wybieramy `Build system: Maven`
    - Maven jest fajny do zarządzania frameworkami
8. Wybieramy `JDK`
    - Jeżeli nie ma na liście, to pobieramy  
      Może być dowolne np. z Amazona.  
      W tym projekcie użyłem `Amazon Corretto 21.0.6`.  
      Z Oracle może być ten problem, że mają inne prawa autorskie oraz są powiązane z Chinami (podobno).
    - `JDK` warto wybierać jak najnowsze (ale też nie takie, które dopiero co wyszło) oraz wybierać wersję
      z długotrwałym wsparciem, czyli oznaczone jako **(LTS) Long Term Support**
9. Można zaznaczyć `Add sample code`
    - Żeby lepiej się orientować w katalogach. Potem się go usunie
10. Kasujemy plik `.gitignore`
    - Stworzymy za chwilę własny za pomocą pluginu `.ignore`
    - Możemy też zostawić i wkleić do niego gotowy szablon z internetu

## Instalacja pluginów

11. Instalujemy **pluginy**:
    - Klikamy `Hamburger Menu`
    - Klikamy `Settings`
    - Wybieramy z menu po lewej `Plugins`
    - W zakładce `Marketplace` wyszukujemy pluginy, które nas interesują
    - Klikamy `Install` przy wybranym pluginie
    - W zakładce `Installed` mamy listę pluginów, które są już zainstalowane
    - Wyszukujemy i instalujemy następujące **pluginy**:
      - Wymagane:
        - .ignore
        - Rainbow Brackets
        - Allure Report
      - Opcjonalne:
        - Lombok
        - Key Promoter X

## Dodawanie projektu do GitHub

12. Generujemy plik `.gitignore` za pomocą pluginu `.ignore`
    - Klikamy `prawym na katalog z projektem`
    - Klikamy `New`
    - Klikamy `.ignore File`
    - Klikamy `.gitignore File (Git)`
    - Na liście wyszukujemy i zaznaczamy następujące templatki:  
      (Na liście będą pojawiać się podwójnie, to wystarczy wybrać tylko jedną. Warto zerknąć czy obie się nie różnią.)
        - `IntelliJ (JetBrains też można sprawdzić)`
        - `Java`
        - `Maven`
    - Klikamy `Generate`
13. Wrzucamy projekt na `GitHub`
    - Klikamy `Hamburger Menu`
    - Klikamy `VCS`
    - Klikamy `Share Project on GitHub`
    - Podajemy `nazwę` repozytorium
    - Zaznaczamy/Odznaczamy `Private`
    - Możemy dodać `opis`, ale jeżeli mamy już plik `README` w projekcie to lepiej zostawić puste
    - Klikamy `Share`
14. Po tym wszystkim Git może chcieć pushować nieśledzony plik o nazwie `vcs.xml`
    - Zaznaczamy/Dodajemy go i pushujemy

## Dodawanie dependencies do Maven

15. Wpisujemy w google `maven repository` lub wchodzimy na stronę:  
    https://mvnrepository.com/
    - Wyszukujemy interesujący nas framework
    - Klikamy na niego
    - Klikamy w najbardziej aktualną i stabilną wersję
    - Kopiujemy `<dependencies>` z zakładki `Maven`
16. Otwieramy plik `pom.xml` i tam to wklejamy
    - Pod `<properties>` musimy dodać `<dependencies></dependencies>`
    - Pomiędzy `<dependencies>` wklejamy nasze `<dependency>` z repozytorium `Maven`
17. Wrzucamy następujące `<dependencies>`:
    - **Uniwersalne**
      - Wymagane:
        - JUnit Jupiter (Aggregator)
        - JUnit Platform Suite (Aggregator)
        - Java Faker
        - AssertJ Core
        - Dotenv Java
        - Allure Report
        - Logback Classic (przydatne, żeby nie denerwowały nas warningi `SLF4J`, które może powodować `Allure Report`)
      - Opcjonalne:
        - Project Lombok (dla lepszej czytelności klas DTO)
    - **Backend**
      - Wymagane:
        - REST Assured
        - Jackson Databind (do deserializacji JSON na DTO)
        - Razem muszą być:
          - Hibernate Validator Engine (do walidacji DTO)
          - Jakarta Validation API (do walidacji DTO)
          - Jakarta Expression Language Implementation (do lepszych komunikatów DTO)
          - Jakarta Expression Language API (do lepszych komunikatów DTO)
      - Opcjonalne:
        - JSON Schema Validator (ten od REST Assured)
        - JSONassert (do porównywania JSON'ów wraz z wyświetlaniem różnic)
        - Jakarta JSON Processing API (taki troszkę słabszy Jackson)
        - Project Lombok (generuje takie rzeczy jak gettery i settery "w locie")
18. Jeżeli chcemy, możemy w `<properties>` zdefiniować sobie zmienne dla numerów wersji naszych dependencies  
    (Instrukcja jak to zrobić jest w niższych sekcjach tego dokumentu)
19. Po wklejeniu naszych dependencies gdzieś w okolicach prawego, górnego rogu powinna pojawić się `ikona Mavena`.  
    Klikamy w nią.  
    Sprawi to, że `dependencies` zostaną **pobrane i zainstalowane** do naszego projektu.  
    Niektóre `dependencies` będą podkreślone z informacjami, że mają jakieś **luki w zabezpieczeniach**.  
    No ale **nic** się z tym za bardzo **nie zrobi**.
20. Warto się upewnić czy `dependencies` zostały dodane do projektu:
    - Klikamy na pasku po prawej na `ikonę Mavena (m)`
    - Rozwijamy katalog `Dependencies`
    - Patrzymy czy są wszystkie, które podaliśmy w `pom.xml`
21. Możemy **rozpocząć pisanie testów**

## Dalsze kroki — rozpoczęcie pisania testów

Dalsze kroki opisujące jak rozpocząć pisanie testów znajdują się w:  
📁rest-assured-java-api (projekt)  
&emsp;📁documents  
&emsp;&emsp;📁notes  
&emsp;&emsp;&emsp;📂rest-assured-and-tests

---

# 🧩Dodatkowe

## ✔Typo — poprawienie błędów w tekście dla plików (głównie Markdown) pisanych w języku polskim

1. Klikamy `Hamburger Menu` w lewym, górnym rogu
2. Klikamy `File`
3. Klikamy `Settings`
4. Rozwijamy `Editor`
5. Klikamy `Natural Languages`
6. Klikamy `+`
7. Szukamy na liście `Polski`
8. Klikamy `Apply`
9. Klikamy `OK`

## ⬇Markdown — wyłączenie podkreślania błędów we fragmentach kodu

1. Najeżdżamy kursorem na czerwone podkreślenie
2. Powinna pojawić się ikonka (żarówka chyba)
3. Szukamy w niej opcji wyłączenia podkreślania błędów kodu dla Markdown
4. Git będzie chciał, żeby dodać i pushnąć plik `markdown.xml`. Dodajemy i pushujemy

## ⓂMaven dependencies — ustawianie zmiennych dla numerów wersji


1. W sekcji z `<properties>` dodajemy coś zgodnie z poniższym przykładem:
    ```Java
    <properties>
        <<u góry jakieś rzeczy od Mavena, które były już w pliku>>
        
        <selenium.version>4.16.1</selenium.version>
        <testng.version>7.9.0</testng.version>
        <assertJ.version>3.25.3</assertJ.version>
        <javafaker.version>1.0.2</javafaker.version>
    </properties>
    ```
2. `<selenium.version>` to nazwa naszej zmiennej.
3. Podstawiamy ją w naszym `dependency` w miejscu numeru wersji jako `${selenium.version}`:  
   Przed:
    ```Java
    <dependency>
        <groupId>org.seleniumhq.selenium</groupId>
        <artifactId>selenium-java</artifactId>
        <version>4.19.1</version>
    </dependency>
    ```
   Po:
    ```Java
    <dependency>
        <groupId>org.seleniumhq.selenium</groupId>
        <artifactId>selenium-java</artifactId>
        <version>${selenium.version}</version>
    </dependency>
    ```

## 🏆Jackson Databind vs. Jakarta JSON Processing API (JSON-P) – porównanie

Obie biblioteki służą do przetwarzania JSON w Java, ale mają różne zastosowania i poziomy popularności.

### **1️⃣ Jackson Databind – Najpopularniejszy wybór 🚀**
**📌 Zalety:**  
✅ **Najpopularniejsza biblioteka** – używana w Spring Boot, Micronaut, Quarkus  
✅ **Konwersja JSON ↔️ Java Objects (POJO)** – bez potrzeby pisania kodu parsującego  
✅ **Bardzo szybka i wydajna**  
✅ **Obsługuje różne formaty (JSON, XML, YAML, CBOR itp.)**  
✅ **Łatwa konfiguracja i rozszerzalność**

**📌 Przykład:**
```java
ObjectMapper objectMapper = new ObjectMapper();
Person person = objectMapper.readValue(jsonString, Person.class);
```
👉 **Jackson Databind to standard de facto w Spring Boot i API REST.**

### **2️⃣ Jakarta JSON Processing API (JSON-P) – Standard Jakarta EE**
**📌 Zalety:**  
✅ **Część oficjalnej specyfikacji Jakarta EE**  
✅ **Lżejsza niż Jackson – nie wymaga dodatkowych zależności**  
✅ **Obsługuje zarówno model obiektowy, jak i API strumieniowe (lepsza wydajność dla dużych plików)**  
✅ **Działa dobrze w środowiskach Jakarta EE (np. aplikacje serwerowe, Java EE)**

**📌 Przykład:**
```java
JsonObject json = Json.createObjectBuilder()
    .add("imie", "Jan")
    .add("wiek", 30)
    .build();
```
👉 **Lepsza, jeśli używasz środowiska Jakarta EE i potrzebujesz lekkiego API do JSON.**

### **🔎 Które rozwiązanie wybrać?**

| Cecha                            | **Jackson Databind** 🏆                               | **Jakarta JSON-P**                              |
|----------------------------------|-------------------------------------------------------|-------------------------------------------------|
| **Popularność**                  | 🔥🔥🔥 **Najpopularniejsze** (Spring Boot, REST APIs) | 🔥 Używane w Jakarta EE                         |
| **Łatwość użycia**               | ✅ **Łatwe mapowanie JSON ↔ POJO**                     | ⚠️ Wymaga manualnej obsługi JSON                |
| **Wydajność**                    | 🚀 Bardzo szybkie                                     | ⚡ Lekkie, ale mniej optymalne dla dużych danych |
| **Obsługa POJO**                 | ✅ Automatyczna                                        | ❌ Brak natywnego wsparcia                       |
| **API Strumieniowe**             | 🔄 Obsługiwane, ale mniej elastyczne                  | ✅ Bardzo wydajne dla dużych plików              |
| **Wsparcie dla innych formatów** | ✅ JSON, XML, YAML, CBOR                               | ❌ Tylko JSON                                    |
| **Wbudowane w Jakarta EE?**      | ❌ Nie                                                 | ✅ Tak                                           |

**📌 Werdykt:**  
✔ **Użyj Jackson Databind**, jeśli pracujesz ze **Spring Boot, REST API lub potrzebujesz mapowania JSON ↔️ Java POJO**.  
✔ **Użyj Jakarta JSON-P**, jeśli pracujesz w środowisku **Jakarta EE i potrzebujesz lekkiej biblioteki JSON**.

🚀 **W większości przypadków Jackson Databind będzie lepszym wyborem!**

---

# 🔌Pluginy do IDE — opis

## .ignore

### **.ignore – Wtyczka do zarządzania plikami ignorowanymi w projektach**

🔹 **`.ignore`** to wtyczka do popularnych środowisk IDE, takich jak **IntelliJ IDEA**, **PyCharm**, **WebStorm**,
czy **Android Studio**, ułatwiająca zarządzanie plikami ignorowanymi przez systemy kontroli wersji, np. **Git**.

### **📌 Kluczowe funkcje wtyczki `.ignore`**
✅ **Automatyczne generowanie plików `.gitignore`** – wspiera różne technologie i języki, oferując gotowe szablony.  
✅ **Podpowiedzi składni** – IntelliSense dla reguł ignorowania plików.  
✅ **Podświetlanie składni** – ułatwia edycję plików `.gitignore`, `.dockerignore`, `.npmignore` itd.  
✅ **Sugerowanie plików do ignorowania** – na podstawie struktury projektu.  
✅ **Szybkie dodawanie plików do `.gitignore`** – kliknij prawym przyciskiem na plik → "Add to .gitignore".  
✅ **Obsługa wielu systemów kontroli wersji** – Git, Mercurial, Bazaar, Darcs itp.

### **📦 Instalacja w IntelliJ IDEA**
1️⃣ Otwórz **File → Settings → Plugins** (lub `Ctrl + Alt + S`).  
2️⃣ Wyszukaj **“.ignore”** w zakładce Marketplace.  
3️⃣ Kliknij **Install** i zrestartuj IDE.

### **📁 Obsługiwane formaty plików**
- `.gitignore`
- `.dockerignore`
- `.npmignore`
- `.cvsignore`
- `.bzrignore`
- `.hgignore`
- `.tfignore`
- `.boringignore`

### **🎯 Dlaczego warto używać `.ignore`?**
🔹 Ułatwia zarządzanie ignorowanymi plikami.  
🔹 Redukuje błędy w konfiguracji `.gitignore`.  
🔹 Automatyzuje tworzenie reguł dla różnych technologii.  
🔹 Przyspiesza workflow w repozytorium Git.

## Rainbow Brackets

### **🌈 Rainbow Brackets – Wtyczka do podświetlania nawiasów w IDE**

**Rainbow Brackets** to wtyczka do **IntelliJ IDEA**, **PyCharm**, **WebStorm**, **Android Studio** i innych IDE
z rodziny **JetBrains**, która podświetla nawiasy w różnych kolorach, ułatwiając analizę kodu.

### **📌 Kluczowe funkcje wtyczki Rainbow Brackets**
✅ **Kolorowe podświetlanie nawiasów** – różne poziomy zagnieżdżenia otrzymują różne kolory.  
✅ **Obsługa wielu języków programowania** – działa m.in. w **Java, Python, JavaScript, Kotlin, HTML, XML, JSON** i wielu innych.  
✅ **Łatwa identyfikacja błędów** – pomaga znaleźć brakujące lub źle zamknięte nawiasy.  
✅ **Dostosowywanie kolorów** – użytkownik może zmieniać schemat kolorów według własnych preferencji.  
✅ **Wsparcie dla ciemnych i jasnych motywów**.  
✅ **Współpraca z innymi wtyczkami** – działa z **Material Theme UI, Atom Material Icons**, itp.

### **📦 Instalacja w IntelliJ IDEA**
1️⃣ **Otwórz:** `File → Settings → Plugins` (lub `Ctrl + Alt + S`).  
2️⃣ **Wyszukaj:** "Rainbow Brackets" w zakładce **Marketplace**.  
3️⃣ **Kliknij:** **Install**, a następnie **Restart IDE**.

### **🎨 Przykład działania i dostosowanie kolorów**

Przed instalacją:
```java
public void exampleMethod() {
    if (condition) {
        while (true) {
            doSomething();
        }
    }
}
```

Po instalacji **Rainbow Brackets**:
- `{ }`, `[ ]`, `( )` będą miały różne kolory, zależnie od poziomu zagnieżdżenia.

Możesz edytować kolory w **File → Settings → Editor → Color Scheme → Rainbow Brackets**.

### **🎯 Dlaczego warto używać Rainbow Brackets?**
🔹 Zwiększa **czytelność kodu** w dużych projektach.  
🔹 Pomaga znaleźć **brakujące lub nadmiarowe nawiasy**.  
🔹 Przyspiesza **debugowanie** i **analizę kodu**.  
🔹 Jest **prosta w użyciu** i nie wpływa na wydajność IDE.

## Key Promoter X

### **⌨️ Key Promoter X – Wtyczka do nauki skrótów klawiszowych w IDE**

**Key Promoter X** to popularna wtyczka do **IntelliJ IDEA**, **PyCharm**, **WebStorm**, **Android Studio** i innych
IDE z rodziny **JetBrains**, która pomaga użytkownikom nauczyć się skrótów klawiszowych poprzez automatyczne podpowiedzi.

### **📌 Kluczowe funkcje wtyczki Key Promoter X**
✅ **Podpowiada skróty klawiszowe** – wyświetla powiadomienia, gdy użytkownik użyje myszy zamiast klawiatury.  
✅ **Pokazuje liczbę użyć myszy** – informuje, ile razy kliknąłeś daną opcję zamiast użyć skrótu.  
✅ **Lista najczęściej używanych operacji** – pomaga określić, które skróty warto zapamiętać w pierwszej kolejności.  
✅ **Automatycznie proponuje nowe skróty** – uczy bardziej efektywnego korzystania z IDE.  
✅ **Obsługa własnych skrótów** – integruje się z niestandardowymi skrótami zdefiniowanymi w IDE.

### **📦 Instalacja w IntelliJ IDEA**
1️⃣ **Otwórz:** `File → Settings → Plugins` (lub `Ctrl + Alt + S`).  
2️⃣ **Wyszukaj:** "Key Promoter X" w zakładce **Marketplace**.  
3️⃣ **Kliknij:** **Install**, a następnie **Restart IDE**.

### **🎯 Jak działa Key Promoter X?**
Jeśli klikniesz np. **"Refactor"** w menu zamiast użyć skrótu klawiszowego, pojawi się powiadomienie:

🔹 **"Refactor (Ctrl + Alt + Shift + T) – użyj skrótu zamiast myszy!"**

Im częściej ignorujesz skróty, tym bardziej wtyczka przypomina, aby z nich korzystać. 😃

### **🎓 Dlaczego warto używać Key Promoter X?**
🚀 **Przyspiesza pracę w IDE** – dzięki skrótom działasz znacznie szybciej.  
📈 **Zwiększa produktywność** – mniej klikania, więcej kodowania.  
🧠 **Uczy efektywnej pracy** – idealne narzędzie dla początkujących i zaawansowanych programistów.

Chcesz szybciej nauczyć się skrótów klawiszowych i pracować wydajniej? **Key Promoter X to must-have!** 🔥

## Lombok

### **🍃 Lombok – Wtyczka do IntelliJ IDEA i JetBrains IDEs**

**Lombok** to **biblioteka** dla Javy, która **automatycznie generuje kod**, eliminując konieczność ręcznego pisania
**getterów, setterów, konstruktorów** i innych standardowych metod. Wtyczka **Lombok Plugin** w IntelliJ IDEA zapewnia
pełne wsparcie dla tej biblioteki, umożliwiając poprawne działanie adnotacji Lomboka w IDE.

### **📌 Kluczowe funkcje wtyczki Lombok**
✅ **Obsługa adnotacji Lomboka** w IntelliJ IDEA i innych JetBrains IDEs.  
✅ **Automatyczne generowanie kodu** w tle bez konieczności ręcznego pisania metod.  
✅ **Poprawne działanie funkcji "Go to Definition"** dla metod generowanych przez Lomboka.  
✅ **Rozwiązywanie błędów kompilacji związanych z Lombokiem**.

### **📦 Instalacja w IntelliJ IDEA**
1️⃣ **Otwórz:** `File → Settings → Plugins` (lub `Ctrl + Alt + S`).  
2️⃣ **Wyszukaj:** "Lombok Plugin" w zakładce **Marketplace**.  
3️⃣ **Kliknij:** **Install**, a następnie **Restart IDE**.  
4️⃣ **Upewnij się, że Lombok jest włączony:**
- Przejdź do `File → Settings → Build, Execution, Deployment → Compiler → Annotation Processors`
- Zaznacz **"Enable annotation processing"**

### **🎯 Jak działa Lombok w IntelliJ IDEA?**
Po zainstalowaniu wtyczki możesz używać adnotacji **Lombok** w swoim kodzie, np.:

```java
import lombok.Data;

@Data // Automatycznie generuje gettery, settery, toString, equals i hashCode
public class User {
    private String name;
    private int age;
}
```

➡️ Bez Lomboka musiałbyś ręcznie pisać **gettery, settery i inne metody**!

### **💡 Najpopularniejsze adnotacje Lomboka**
🔹 `@Getter` i `@Setter` → Generują gettery i settery dla pól klasy.  
🔹 `@Data` → Generuje **toString()**, **equals()**, **hashCode()**, gettery i settery.  
🔹 `@AllArgsConstructor` i `@NoArgsConstructor` → Tworzą konstruktory z wszystkimi lub żadnym parametrem.  
🔹 `@Builder` → Tworzy wzorzec **Builder** dla klasy.  
🔹 `@Slf4j` → Automatycznie dodaje logger **SLF4J**.

### **🎓 Dlaczego warto używać Lomboka?**
🚀 **Mniej boilerplate code** – nie musisz pisać setek linii zbędnego kodu.  
📈 **Lepsza czytelność** – kod staje się bardziej przejrzysty.  
⚡ **Szybsza praca** – nie musisz generować metod ręcznie.

Jeśli chcesz **przyspieszyć pracę i uprościć kod w Javie**, **Lombok Plugin** to **must-have**! 🔥

## Allure Report

### **📊 Allure Report – Wtyczka do IntelliJ IDEA**

**Allure Report** to **zaawansowane narzędzie do generowania raportów testowych**. Wtyczka **Allure Plugin** dla
IntelliJ IDEA integruje Allure z IDE, umożliwiając szybkie generowanie, przeglądanie i analizowanie raportów
bez wychodzenia z IntelliJ.

### **📌 Co robi wtyczka Allure Report?**
✅ **Integruje raporty Allure z IntelliJ IDEA** – pozwala otwierać i analizować wyniki testów bez wychodzenia z IDE.  
✅ **Dodaje nową zakładkę "Allure"**, w której można wizualizować raporty w graficznej formie.  
✅ **Automatycznie wykrywa katalog `allure-results`** i generuje raport jednym kliknięciem.  
✅ **Obsługuje TestNG, JUnit 4/5, Cucumber i inne frameworki** testowe.  
✅ **Pozwala przeglądać szczegóły testów** – błędy, logi, załączniki (np. screenshoty).

### **🔧 Jak zainstalować wtyczkę?**
1️⃣ Otwórz **IntelliJ IDEA** i przejdź do:
- `File → Settings → Plugins` (Windows/Linux)
- `IntelliJ IDEA → Preferences → Plugins` (Mac)  
  2️⃣ Wyszukaj: **"Allure Report"** w zakładce **Marketplace**.  
  3️⃣ Kliknij **Install**, a potem **Restart IDE**.

### **📂 Jak używać wtyczki?**
1️⃣ **Uruchom testy**, które zapisują wyniki do `allure-results`.  
2️⃣ W **dolnym panelu IntelliJ** przejdź do zakładki **"Allure"**.  
3️⃣ Kliknij **"Generate Report"**, aby zobaczyć wyniki w IDE.  
4️⃣ Możesz nawigować po testach, sprawdzać błędy i załączniki.

### **📢 Zalety wtyczki Allure Report w IntelliJ IDEA**
🚀 **Nie trzeba otwierać raportów w przeglądarce** – wszystko działa w IDE.  
🔍 **Szybki podgląd wyników testów** bez dodatkowych poleceń w terminalu.  
📊 **Wizualizacja błędów, logów i statystyk** testów.  
🛠️ **Łatwa integracja z popularnymi frameworkami** testowymi.

Jeśli pracujesz z Allure, ta wtyczka **znacznie ułatwia życie**! 🔥

---

# 📚Dependencies — opis

## ⭐Uniwersalne

### 📕JUnit Jupiter (Aggregator)

`JUnit Jupiter (Aggregator)` to zależność dla **JUnit 5**, która zapewnia pełną funkcjonalność silnika testowego
**JUnit Jupiter**. Jest to **główna implementacja testów** w JUnit 5, zawierająca **adnotacje, asercje i mechanizmy
testowania**.

#### **📌 Co to jest JUnit Jupiter?**
JUnit 5 składa się z trzech głównych modułów:
1. **JUnit Platform** – uruchamia testy i integruje różne silniki testowe.
2. **JUnit Jupiter** – nowoczesna implementacja testów dla JUnit 5.
3. **JUnit Vintage** – wsparcie dla testów JUnit 3 i 4.

**JUnit Jupiter** jest **domyślnym silnikiem testowym** w JUnit 5 i dostarcza **adnotacje** oraz **API do pisania testów**.

#### **📦 Jak dodać zależność?**

**Dla Maven (pom.xml)**
```xml
<dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter</artifactId>
    <version>5.10.0</version>
    <scope>test</scope>
</dependency>
```

**Dla Gradle (build.gradle.kts)**
```kotlin
dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.0")
}
```

#### **🛠️ Jak używać?**

**✅ Przykładowy test w JUnit 5 (Jupiter)**
```java
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class ExampleTest {

    @BeforeAll
    static void beforeAll() {
        System.out.println("Uruchamiane przed wszystkimi testami");
    }

    @BeforeEach
    void beforeEach() {
        System.out.println("Uruchamiane przed każdym testem");
    }

    @Test
    void testAddition() {
        int result = 2 + 3;
        assertEquals(5, result, "Dodawanie nie działa poprawnie!");
    }

    @Test
    void testBoolean() {
        assertTrue(10 > 5, "10 powinno być większe od 5");
    }

    @AfterEach
    void afterEach() {
        System.out.println("Uruchamiane po każdym teście");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("Uruchamiane po wszystkich testach");
    }
}
```

#### **📢 Kluczowe funkcje JUnit Jupiter**
✅ **Nowoczesna składnia i API** – pełne wsparcie dla **Lambda Expressions** i **Java 8+**  
✅ **Więcej adnotacji** – np. `@BeforeAll`, `@BeforeEach`, `@AfterEach`, `@AfterAll`  
✅ **Bogate API asercji** – `assertEquals()`, `assertTrue()`, `assertThrows()` itd.  
✅ **Dynamiczne testy** – `@TestFactory` do tworzenia testów w locie  
✅ **Tagowanie testów** – `@Tag("slow")`, `@Tag("fast")`

#### **🎯 Dlaczego warto używać JUnit Jupiter?**
`JUnit Jupiter` to **główna implementacja testów w JUnit 5**, która:  
🔹 Usprawnia pisanie testów dzięki nowoczesnemu API.  
🔹 Wspiera **dynamiczne testy** i **parametryzację**.  
🔹 Pozwala na **lepsze organizowanie testów** w projektach Java.

Jeśli chcesz korzystać z **JUnit 5**, to `JUnit Jupiter` jest absolutnie kluczowym dependency! 🚀

---

### 📕JUnit Platform Suite (Aggregator)

`JUnit Platform Suite (Aggregator)` to zależność używana do uruchamiania testów w **JUnit 5**, pozwalająca na
konfigurację i uruchamianie testów z różnych pakietów i klas w ramach jednej **suity testowej**.

#### **📌 Co to jest JUnit Platform Suite?**
JUnit 5 składa się z trzech głównych modułów:
1. **JUnit Platform** – uruchamia testy i integruje różne silniki testowe.
2. **JUnit Jupiter** – implementacja dla testów JUnit 5.
3. **JUnit Vintage** – wsparcie dla testów JUnit 3 i 4.

**JUnit Platform Suite** to mechanizm pozwalający na **grupowanie i uruchamianie testów** z różnych klas, pakietów
i frameworków w ramach jednej konfiguracji.

#### **📦 Jak dodać zależność?**

**Dla Maven (pom.xml)**
```xml
<dependency>
    <groupId>org.junit.platform</groupId>
    <artifactId>junit-platform-suite</artifactId>
    <version>1.10.0</version>
</dependency>
```

**Dla Gradle (build.gradle.kts)**
```kotlin
dependencies {
    testImplementation("org.junit.platform:junit-platform-suite:1.10.0")
}
```

#### **🛠️ Jak używać?**

Tworząc klasę testową, można użyć **JUnit 5 Platform Suite** do uruchomienia wielu testów jednocześnie.

**✅ Przykład użycia**
```java
import org.junit.platform.suite.api.*;

@Suite
@SelectPackages("com.example.tests") // Wybiera wszystkie testy z pakietu
@IncludeTags("smoke") // Uruchamia tylko testy oznaczone jako "smoke"
public class TestSuite {
}
```
📌 **Co robi ten kod?**  
✔ **@Suite** – oznacza klasę jako zestaw testów.  
✔ **@SelectPackages("com.example.tests")** – uruchamia testy z określonego pakietu.  
✔ **@IncludeTags("smoke")** – filtruje testy po tagach.

#### **📢 Główne zalety JUnit Platform Suite**
✅ **Grupowanie testów** – można uruchamiać wiele testów na raz.  
✅ **Filtracja testów** – można wybierać testy po pakietach, klasach, tagach.  
✅ **Integracja z JUnit 5, TestNG i innymi frameworkami**.  
✅ **Łatwa konfiguracja w Maven/Gradle**.

Jeśli potrzebujesz **zbiorczego uruchamiania testów w JUnit 5**, to `JUnit Platform Suite` jest idealnym rozwiązaniem! 🚀

---

### 📕AssertJ Core

🔹 **AssertJ Core** to biblioteka do **asercji w testach jednostkowych**, która **rozszerza możliwości JUnit**.
Jest nowoczesną alternatywą dla wbudowanych asercji w **JUnit** i **Hamcrest**, oferując **bardziej czytelną, płynną
składnię (Fluent API)**.

#### **📦 Dodanie do projektu**

**Maven (pom.xml)**
```xml
<dependency>
    <groupId>org.assertj</groupId>
    <artifactId>assertj-core</artifactId>
    <version>3.24.2</version>
    <scope>test</scope>
</dependency>
```

**Gradle (build.gradle.kts)**
```kotlin
dependencies {
    testImplementation("org.assertj:assertj-core:3.24.2")
}
```

#### **🛠️ Jak używać AssertJ?**

**✅ Przykładowe asercje**

```java
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class AssertJTest {

    @Test
    void testBasicAssertions() {
        int result = 5;

        // Klasyczne JUnit
        // assertEquals(5, result);

        // Lepsza wersja w AssertJ
        assertThat(result).isEqualTo(5);
    }

    @Test
    void testStringAssertions() {
        String text = "Hello AssertJ";

        assertThat(text)
                .isNotEmpty()
                .startsWith("Hello")
                .endsWith("AssertJ")
                .contains("lo As");
    }

    @Test
    void testListAssertions() {
        var numbers = java.util.List.of(1, 2, 3, 4, 5);

        assertThat(numbers)
                .hasSize(5)
                .contains(3)
                .doesNotContain(10)
                .startsWith(1, 2)
                .endsWith(4, 5);
    }

    @Test
    void testExceptionAssertions() {
        assertThatThrownBy(() -> { throw new IllegalArgumentException("Błąd!"); })
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Błąd!");
    }
}
```

#### **📌 Kluczowe zalety AssertJ**
✅ **Czytelniejsza składnia** – `assertThat(value).isEqualTo(expected)` zamiast `assertEquals(expected, value)`.  
✅ **Fluent API** – możliwość łączenia wielu asercji w jednej linii.  
✅ **Zaawansowane asercje** – np. **sprawdzanie wyjątków, kolekcji, dat** itp.  
✅ **Wsparcie dla Java 8+** – m.in. **Stream API, Optional, Lambda Expressions**.

#### **🎯 Dlaczego warto używać AssertJ?**
🔹 **Lepsza czytelność kodu** – testy są bardziej zrozumiałe.  
🔹 **Większa elastyczność** – zaawansowane operacje na **Stringach, kolekcjach, wyjątkach**.  
🔹 **Lepsza obsługa błędów** – komunikaty błędów są bardziej szczegółowe niż w JUnit/Hamcrest.

👉 **AssertJ Core** to świetne rozszerzenie do **JUnit 5**! 🚀

---

### 📕Java Faker

🔹 **Java Faker** to biblioteka do generowania **losowych danych testowych** w języku Java. Umożliwia tworzenie
**fikcyjnych nazw, adresów, numerów telefonów, dat, tekstów i wielu innych typów danych**. Jest często używana
w **testach jednostkowych, integracyjnych oraz do populacji baz danych**.

#### **📦 Dodanie do projektu**

**Maven (pom.xml)**
```xml
<dependency>
    <groupId>com.github.javafaker</groupId>
    <artifactId>javafaker</artifactId>
    <version>1.0.2</version>
</dependency>
```

**Gradle (build.gradle.kts)**
```kotlin
dependencies {
    implementation("com.github.javafaker:javafaker:1.0.2")
}
```

#### **🛠️ Jak używać Java Faker?**

```java
import com.github.javafaker.Faker;

public class FakerExample {
    public static void main(String[] args) {
        Faker faker = new Faker();

        // Generowanie danych osobowych
        System.out.println("Imię: " + faker.name().firstName());
        System.out.println("Nazwisko: " + faker.name().lastName());
        System.out.println("Adres e-mail: " + faker.internet().emailAddress());

        // Generowanie adresu
        System.out.println("Ulica: " + faker.address().streetAddress());
        System.out.println("Miasto: " + faker.address().city());
        System.out.println("Kod pocztowy: " + faker.address().zipCode());

        // Generowanie losowych danych finansowych
        System.out.println("Numer karty kredytowej: " + faker.finance().creditCard());

        // Generowanie daty urodzenia
        System.out.println("Data urodzenia: " + faker.date().birthday());

        // Generowanie numeru telefonu
        System.out.println("Numer telefonu: " + faker.phoneNumber().cellPhone());

        // Generowanie fikcyjnej firmy
        System.out.println("Firma: " + faker.company().name());

        // Generowanie losowego tekstu
        System.out.println("Randomowy tekst: " + faker.lorem().sentence());
    }
}
```

#### **📌 Kluczowe funkcjonalności Java Faker**

✅ **Generowanie danych osobowych** (`name()`, `internet().emailAddress()`)  
✅ **Adresy i lokalizacje** (`address().city()`, `address().streetName()`)  
✅ **Numery telefonów i karty kredytowe** (`phoneNumber().cellPhone()`, `finance().creditCard()`)  
✅ **Losowe daty** (`date().birthday()`, `date().past()`)  
✅ **Firmy i stanowiska pracy** (`company().name()`, `job().position()`)  
✅ **Generowanie losowych słów i zdań** (`lorem().sentence()`, `lorem().paragraph()`)

#### **🎯 Dlaczego warto używać Java Faker?**
🔹 **Automatyczne generowanie realistycznych danych** – przydatne w testach i mockowaniu danych.  
🔹 **Łatwa integracja** – szybkie wdrożenie w **JUnit, TestNG, Selenium i Spring Boot**.  
🔹 **Wsparcie dla wielu języków** – można generować dane w różnych lokalizacjach (`Faker faker = new Faker(new Locale("pl"))`).

👉 **Java Faker** to świetne narzędzie do **testowania i mockowania danych** w aplikacjach Java! 🚀

---

### 📕Dotenv Java

🔹 **Dotenv Java** to biblioteka umożliwiająca **wczytywanie zmiennych środowiskowych z pliku `.env`** do aplikacji
Java. Jest szczególnie przydatna w celu **przechowywania konfiguracji aplikacji** (np. kluczy API, adresów baz danych)
w sposób bezpieczny i łatwy do zarządzania.

#### **📦 Dodanie do projektu**

**Maven (pom.xml)**
```xml
<dependency>
    <groupId>io.github.cdimascio</groupId>
    <artifactId>dotenv-java</artifactId>
    <version>3.0.0</version>
</dependency>
```

**Gradle (build.gradle.kts)**
```kotlin
dependencies {
    implementation("io.github.cdimascio:dotenv-java:3.0.0")
}
```

#### **🛠️ Jak używać Dotenv Java?**

1️⃣ **Tworzymy plik `.env` w katalogu głównym projektu:**
```
DATABASE_URL=jdbc:mysql://localhost:3306/mydb
DATABASE_USER=root
DATABASE_PASSWORD=secret
API_KEY=12345-abcdef
```

2️⃣ **Wczytujemy plik `.env` w kodzie Java:**
```java
import io.github.cdimascio.dotenv.Dotenv;

public class DotenvExample {
    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.load(); // Automatycznie wczytuje plik .env

        String dbUrl = dotenv.get("DATABASE_URL");
        String dbUser = dotenv.get("DATABASE_USER");
        String dbPassword = dotenv.get("DATABASE_PASSWORD");
        String apiKey = dotenv.get("API_KEY");

        System.out.println("Database URL: " + dbUrl);
        System.out.println("Database User: " + dbUser);
        System.out.println("API Key: " + apiKey);
    }
}
```

#### **📌 Kluczowe funkcjonalności Dotenv Java**

✅ **Automatyczne wczytywanie pliku `.env`** – brak konieczności ustawiania zmiennych środowiskowych w systemie  
✅ **Obsługa wartości domyślnych** (`dotenv.get("VARIABLE", "default_value")`)  
✅ **Wsparcie dla różnych lokalizacji pliku** (`Dotenv.configure().directory("/custom/path").load()`)  
✅ **Łatwa integracja z aplikacjami Spring Boot, JUnit, Selenium i innymi**

#### **🎯 Dlaczego warto używać Dotenv Java?**
🔹 **Bezpieczeństwo** – zamiast przechowywać dane w kodzie źródłowym, używasz `.env`, który nie jest commitowany do repozytorium (`.gitignore`).  
🔹 **Łatwość konfiguracji** – konfiguracja aplikacji jest przechowywana w jednym miejscu i może być łatwo zmieniana.  
🔹 **Przenośność** – działa zarówno w **środowisku lokalnym**, jak i na **serwerach CI/CD**.

👉 **Dotenv Java** to świetne narzędzie do **zarządzania konfiguracją aplikacji w sposób bezpieczny i elastyczny**! 🚀

---

### 📕Allure Report

🔹 **Allure Report** to biblioteka służąca do **generowania atrakcyjnych i szczegółowych raportów z testów** w Java.
Współpracuje z popularnymi frameworkami testowymi, takimi jak **JUnit 5, TestNG, Cucumber, Serenity BDD**, a także
z narzędziami CI/CD (Jenkins, GitHub Actions).

Allure pozwala na **lepszą wizualizację wyników testów**, śledzenie historii ich wykonania oraz analizowanie błędów.

#### **📦 Dodanie Allure Report do projektu**

**Maven (pom.xml)**
```xml
<dependencies>
    <!-- Główna biblioteka Allure -->
    <dependency>
        <groupId>io.qameta.allure</groupId>
        <artifactId>allure-junit5</artifactId>
        <version>2.24.0</version>
    </dependency>

    <!-- Jeśli używasz TestNG zamiast JUnit 5 -->
    <dependency>
        <groupId>io.qameta.allure</groupId>
        <artifactId>allure-testng</artifactId>
        <version>2.24.0</version>
    </dependency>
</dependencies>
```

**Gradle (build.gradle.kts)**
```kotlin
dependencies {
    testImplementation("io.qameta.allure:allure-junit5:2.24.0")
}
```

#### **🛠️ Jak używać Allure Report?**

1️⃣ **Dodanie adnotacji do testów**
```java
import io.qameta.allure.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AllureExampleTest {

    @Test
    @Description("Sprawdza, czy test przechodzi poprawnie")
    @Severity(SeverityLevel.CRITICAL)
    @Feature("Funkcja logowania")
    @Story("Logowanie użytkownika")
    public void sampleTest() {
        assertTrue(true, "Test powinien przejść");
    }
}
```

2️⃣ **Uruchomienie testów i wygenerowanie raportu**
- **Maven:**
    ```sh
    mvn clean test
    mvn allure:serve
    ```
- **Gradle:**
  ```sh
  ./gradlew clean test allureReport
  ./gradlew allureServe
  ```

📌 **`allure:serve`** otwiera interaktywny raport w przeglądarce.

#### **📌 Kluczowe funkcjonalności Allure Report**

✅ **Graficzna wizualizacja wyników testów** 🖥️  
✅ **Adnotacje do oznaczania testów (`@Feature`, `@Story`, `@Step`)**  
✅ **Śledzenie historii testów**  
✅ **Zrzuty ekranu i logi do analizy błędów**  
✅ **Integracja z narzędziami CI/CD (Jenkins, GitHub Actions)**

#### **🎯 Dlaczego warto używać Allure Report?**
🔹 **Przejrzyste raporty** – lepsza analiza wyników testów.  
🔹 **Świetna integracja z JUnit 5, TestNG, Cucumber itp.**  
🔹 **Łatwe debugowanie testów i śledzenie historii ich wykonania.**

👉 **Allure Report to jedno z najlepszych narzędzi do generowania raportów z testów automatycznych!** 🚀

---

### 📕Project Lombok

#### 🚨 UWAGA!

Po sprawdzeniu opinii na Internecie wyszło na to, że lepiej tego nie używać!  
Nawet jeśli kod dzięki temu fajniej wygląda, to ukryte elementy są generowane w trakcie kompilacji.  
Stwarza to zawsze ryzyko, że w którymś momencie może to przestać działać i nie będziemy wiedzieli dlaczego.  
Lepiej już mieć rozpisany kod, stały dostęp do niego i większą pewność, że zadziała.

#### 📄 Opis

**Project Lombok** to popularna biblioteka Java, która eliminuje *boilerplate code* (czyli powtarzalny, techniczny kod)
poprzez automatyczne generowanie metod i konstruktorów za pomocą adnotacji.

#### 📦 Maven dependency:

```xml
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <version>1.18.30</version> <!-- sprawdź najnowszą wersję -->
    <scope>provided</scope>
</dependency>
```

#### ✅ Główne funkcjonalności:

| Adnotacja                                                               | Co robi?                                                                                           |
|-------------------------------------------------------------------------|----------------------------------------------------------------------------------------------------|
| `@Getter` / `@Setter`                                                   | Generuje gettery i settery dla pól                                                                 |
| `@ToString`                                                             | Tworzy metodę `toString()`                                                                         |
| `@EqualsAndHashCode`                                                    | Tworzy `equals()` i `hashCode()`                                                                   |
| `@NoArgsConstructor`, `@AllArgsConstructor`, `@RequiredArgsConstructor` | Generuje konstruktory                                                                              |
| `@Data`                                                                 | Skrót łączący: `@Getter`, `@Setter`, `@ToString`, `@EqualsAndHashCode`, `@RequiredArgsConstructor` |
| `@Builder`                                                              | Tworzy wzorzec buildera                                                                            |
| `@Slf4j`                                                                | Tworzy logger (np. `private static final Logger log = LoggerFactory.getLogger(...);`)              |
| `@Value`                                                                | Tworzy immutable klasę (jak `@Data`, ale z finalnymi polami)                                       |

#### 🧪 Przykład użycia:

```java
import lombok.Data;
import lombok.Builder;

@Data
@Builder
public class User {
    private String name;
    private int age;
}
```

Powyższy kod automatycznie generuje:

* gettery i settery,
* `toString()`, `equals()`, `hashCode()`,
* konstruktor,
* builder.

#### ⚙️ Uwaga dla IDE

Aby IDE (np. IntelliJ IDEA, Eclipse) prawidłowo obsługiwało Lomboka:

* Zainstaluj **plugin "Lombok"**.
* Włącz opcję **annotation processing** w ustawieniach kompilatora.

#### 📌 Zalety

* Mniej kodu, większa czytelność.
* Ułatwia pracę przy DTO, encjach, modelach domenowych.
* Łatwiejsze stosowanie wzorców projektowych (np. builder).

#### ⚠️ Wady i uwagi

* Magiczne generowanie kodu może być nieczytelne dla początkujących.
* Może wymagać wsparcia IDE / build toola (np. Maven, Gradle).
* Potencjalne trudności przy debugowaniu i refleksji.

## 🌐Backend

### 📘REST Assured

🔹 **REST Assured** to biblioteka Java ułatwiająca **testowanie API REST**. Pozwala na **wysyłanie żądań HTTP**
i **walidację odpowiedzi** w sposób prosty i czytelny, przypominający składnię BDD (Behavior-Driven Development).

#### **📦 Dodanie REST Assured do projektu**

**Maven (pom.xml)**
```xml
<dependency>
    <groupId>io.rest-assured</groupId>
    <artifactId>rest-assured</artifactId>
    <version>5.4.0</version>
    <scope>test</scope>
</dependency>
```

**Gradle (build.gradle.kts)**
```kotlin
dependencies {
    testImplementation("io.rest-assured:rest-assured:5.4.0")
}
```

📌 **Biblioteka najlepiej działa w połączeniu z JUnit lub TestNG**.

#### **🚀 Podstawowe użycie REST Assured**

**1️⃣ Wysyłanie żądania GET i sprawdzenie odpowiedzi**
```java
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class RestAssuredExample {
    public static void main(String[] args) {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

        given()
            .when()
                .get("/posts/1")
            .then()
                .statusCode(200) // Sprawdzenie statusu HTTP
                .body("userId", equalTo(1)) // Walidacja pola userId
                .body("title", notNullValue()); // Sprawdzenie, czy tytuł nie jest pusty
    }
}
```
✅ **REST Assured pozwala w prosty sposób testować API i walidować odpowiedzi!**

**2️⃣ Wysyłanie żądania POST z JSON-em w ciele**
```java
import org.json.JSONObject;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class PostRequestExample {
    public static void main(String[] args) {
        JSONObject requestParams = new JSONObject();
        requestParams.put("title", "Nowy post");
        requestParams.put("body", "To jest treść posta");
        requestParams.put("userId", 1);

        given()
            .header("Content-Type", "application/json")
            .body(requestParams.toString())
        .when()
            .post("https://jsonplaceholder.typicode.com/posts")
        .then()
            .statusCode(201) // Sprawdzenie, czy żądanie zakończyło się sukcesem (201 Created)
            .body("title", equalTo("Nowy post"));
    }
}
```

**3️⃣ Testowanie API z autoryzacją (Bearer Token)**  
Jeśli API wymaga **uwierzytelnienia**, REST Assured obsługuje to w prosty sposób:
```java
given()
    .header("Authorization", "Bearer " + "TOKEN_JWT")
    .when()
    .get("https://api.example.com/protected-data")
    .then()
    .statusCode(200);
```

#### **🛠️ Dodatkowe funkcje REST Assured**

🔹 **Obsługa różnych metod HTTP:** `GET`, `POST`, `PUT`, `DELETE`, `PATCH`  
🔹 **Obsługa autoryzacji:** Basic Auth, OAuth 2.0, Bearer Token  
🔹 **Wsparcie dla JSON i XML**  
🔹 **Łatwa integracja z JUnit i TestNG**  
🔹 **Walidacja odpowiedzi za pomocą `Matchers` (Hamcrest)**

#### **🎯 Dlaczego warto używać REST Assured?**
✅ **Prosta składnia BDD – łatwa do czytania i pisania**  
✅ **Obsługa JSON i XML out-of-the-box**  
✅ **Integracja z JUnit, TestNG i Allure Report**  
✅ **Obsługa autoryzacji i nagłówków**  
✅ **Eliminacja potrzeby używania dodatkowych klientów HTTP (np. HttpClient, OkHttp)**

👉 **REST Assured to najlepsze narzędzie do testowania API w Java!** 🚀

---

### 📘Hibernate Validator Engine

**Hibernate Validator Engine** to biblioteka do **walidacji danych** w Javie, stanowiąca **referencyjną implementację
specyfikacji Bean Validation** (JSR 380 – Bean Validation 2.0).

#### 🔧 Co to oznacza w praktyce?

Umożliwia łatwe **dodawanie reguł walidacji** do pól klas (np. DTO, encji) za pomocą adnotacji, np.:

```java
public class User {

    @NotNull
    @Size(min = 2, max = 30)
    private String name;

    @Email
    private String email;
}
```

#### 💡 Co robi Hibernate Validator?

✅ Sprawdza dane w czasie działania (runtime)  
✅ Może walidować dane w kontrolerach, formularzach, DTO  
✅ Obsługuje standardowe adnotacje: `@NotNull`, `@Email`, `@Min`, `@Pattern`, itd.  
✅ Pozwala tworzyć **własne adnotacje walidacyjne**  
✅ Integruje się z frameworkami (Spring, JAX-RS, Jakarta EE itd.)

#### 🚀 Jak użyć?

Dodajesz do `pom.xml`:

```xml
<dependency>
    <groupId>org.hibernate.validator</groupId>
    <artifactId>hibernate-validator</artifactId>
</dependency>
```

Opcjonalnie:

```xml
<dependency>
    <groupId>org.glassfish</groupId>
    <artifactId>jakarta.el</artifactId>
</dependency>
```

#### ✅ Przykład walidacji:

```java
ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
Validator validator = factory.getValidator();

Set<ConstraintViolation<User>> violations = validator.validate(user);

for (ConstraintViolation<User> v : violations) {
    System.out.println(v.getPropertyPath() + ": " + v.getMessage());
}
```

#### 🧰 Przykładowe adnotacje:

* `@NotNull`, `@NotBlank`
* `@Size(min=, max=)`
* `@Email`
* `@Min`, `@Max`
* `@Pattern(regex=...)`

#### 📌 Podsumowanie

| Cecha        | Opis                                  |
|--------------|---------------------------------------|
| Framework    | Hibernate Validator                   |
| Specyfikacja | Bean Validation (JSR 380)             |
| Zastosowanie | Walidacja danych w obiektach Java     |
| Obsługa      | Adnotacje + programowe API            |
| Integracje   | Spring, Jakarta EE, JAX-RS, JSF, itp. |

---

### 📘Jakarta Validation API

**Jakarta Validation API** to oficjalna specyfikacja (API) dla walidacji danych w Javie – wcześniej znana jako
**Bean Validation API** (JSR 303/349/380), a obecnie pod marką **Jakarta EE**.

#### 🧩 Co to jest?

`jakarta.validation:jakarta.validation-api` to **interfejsy i adnotacje**, które definiują sposób opisywania
i wykonywania walidacji w Javie. **Nie zawiera implementacji** — do działania potrzebujesz np. Hibernate Validator
(który implementuje to API).

#### 📦 Przykładowa zależność Maven:

```xml
<dependency>
    <groupId>jakarta.validation</groupId>
    <artifactId>jakarta.validation-api</artifactId>
    <version>3.0.2</version> <!-- lub nowsza -->
</dependency>
```

#### 🏷️ Przykładowe adnotacje w API:

* `@NotNull`
* `@Size(min, max)`
* `@Email`
* `@Pattern`
* `@Min`, `@Max`
* `@Positive`, `@Negative`

#### ✅ Przykład użycia:

```java
import jakarta.validation.constraints.*;

public class User {

    @NotNull
    private String username;

    @Email
    private String email;

    @Size(min = 8)
    private String password;
}
```

Aby walidacja działała, musisz użyć implementacji, np. Hibernate Validator:

```java
Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
Set<ConstraintViolation<User>> violations = validator.validate(user);
```

#### 🔄 Różnica: API vs. Implementacja

| Element           | Przykład                                 |
|-------------------|------------------------------------------|
| API               | `jakarta.validation-api`                 |
| Implementacja     | `hibernate-validator`                    |
| Porównanie        | API to kontrakt, implementacja to logika |
| Typowe połączenie | API + Hibernate Validator                |

#### 📌 Podsumowanie

| Cecha                   | Opis                                                     |
|-------------------------|----------------------------------------------------------|
| Nazwa                   | Jakarta Validation API                                   |
| Zastosowanie            | Walidacja danych przez adnotacje                         |
| Część                   | Specyfikacji Jakarta EE                                  |
| Potrzeba implementacji? | Tak (np. Hibernate Validator)                            |
| Popularność             | Bardzo szeroko stosowane w Spring Boot, Jakarta EE, itp. |

#### ❓ Czy hibernate-validator oraz jakarta.validation-api muszą być razem?

Świetne pytanie — **tak**, w praktyce **`hibernate-validator` i `jakarta.validation-api` muszą iść razem**,
ponieważ pełnią **różne, uzupełniające się role**:

##### 🧩 Różnice i zależność:

| Biblioteka               | Rola                                                                         | Obowiązkowa?                     |
|--------------------------|------------------------------------------------------------------------------|----------------------------------|
| `jakarta.validation-api` | **Specyfikacja interfejsów i adnotacji** (np. `@NotNull`, `@Size`, `@Valid`) | ✅ TAK — to "API", czyli kontrakt |
| `hibernate-validator`    | **Implementacja specyfikacji** — faktyczne wykonywanie walidacji             | ✅ TAK — to konkretne działanie   |

##### 📌 Co się stanie, jeśli dodasz tylko jedną?

* **Tylko `jakarta.validation-api`**:
  → Masz adnotacje, ale **nie zadziała żadna walidacja** — brak silnika, który by je wykonał.

* **Tylko `hibernate-validator`**:
  → Nie zadziała kompilacja, bo brakuje interfejsów i adnotacji z API.

##### ✅ Dlatego zawsze używaj obu:

W `pom.xml` (lub odpowiednik dla Gradle):

```xml
<dependency>
    <groupId>org.hibernate.validator</groupId>
    <artifactId>hibernate-validator</artifactId>
    <version>8.0.1.Final</version>
</dependency>
<dependency>
    <groupId>jakarta.validation</groupId>
    <artifactId>jakarta.validation-api</artifactId>
    <version>3.0.2</version>
</dependency>
```

Hibernate Validator **implementuje** `jakarta.validation-api`.

---

### 📘Jakarta Expression Language Implementation

Świetny temat — **Jakarta Expression Language Implementation** to coś, co często pojawia się „w tle” i wiele osób myli
go z API. Wyjaśnię to jasno, praktycznie i bez zbędnej teorii.

#### 🧠 **Czym jest Jakarta Expression Language Implementation?**

To **konkretna implementacja (czyli działający kod)** dla **Jakarta Expression Language (EL)**.

👉 Czyli:

* **API (`jakarta.el-api`)** → tylko interfejsy (co można robić)
* **Implementation (`jakarta.el`)** → realna logika (jak to działa)

#### 📦 Podział (bardzo ważny)

##### 1️⃣ **API**

```xml
jakarta.el:jakarta.el-api
```

* definiuje:
    * `ExpressionFactory`
    * `ValueExpression`
    * `ELContext`
* NIE działa samodzielnie

##### 2️⃣ **Implementation (to, o co pytasz)**

Najczęściej:

```xml
org.glassfish:jakarta.el
```

👉 To jest:
* rzeczywisty silnik EL
* parser i evaluator wyrażeń

#### 🔥 Co robi implementation?

Pozwala na wykonanie takich rzeczy jak:

```java
ExpressionFactory factory = ExpressionFactory.newInstance();
ELContext context = new StandardELContext(factory);

ValueExpression expr = factory.createValueExpression(context, "${1 + 2}", int.class);
Object result = expr.getValue(context);

System.out.println(result); // 3
```

#### ⚙️ Co jest „pod maską”?

Implementation zajmuje się:

##### ✔ Parsowaniem wyrażeń

```
${user.name}
${order.total > 100}
${list[0]}
```

##### ✔ Dostępem do obiektów

* refleksja
* gettery/settery

##### ✔ Operacjami:

* matematyka (`+ - * /`)
* logika (`&& || !`)
* porównania (`==`, `>`, `<`)

##### ✔ Wywoływaniem metod:

```java
${user.getName()}
```

#### 🧩 Gdzie się tego używa?

##### 🔹 1. **JSF (JavaServer Faces)**

```xml
<h:outputText value="#{user.name}" />
```

##### 🔹 2. **JSP**

```jsp
${user.name}
```

##### 🔹 3. **CDI / Dependency Injection**

##### 🔹 4. **Hibernate Validator**

```java
@ELAssert(expression = "this.age > 18")
```

##### 🔹 5. Frameworki backendowe / custom DSL

---

### 📘Jakarta Expression Language API

**`jakarta.el:jakarta.el-api`** to biblioteka definiująca **Expression Language (EL)**, czyli **język wyrażeń**
używany w aplikacjach Java do dynamicznego wiązania danych (np. w JSP, JSF, CDI, beanach).

#### 🔍 Do czego służy EL?

Expression Language umożliwia:

* **odczyt/zapis właściwości obiektów** (np. `${user.name}`),
* **wywoływanie metod**, operatorów logicznych, porównań (`${user.age > 18}`),
* **integrację z beanami**, kontekstem aplikacji, zmiennymi środowiskowymi.

#### 🧩 Główne zastosowania:

* **JavaServer Faces (JSF)**
* **JavaServer Pages (JSP)**
* **Jakarta CDI (Contexts and Dependency Injection)**
* **Jakarta Bean Validation** (np. w `@AssertTrue(expression = ...)`)

#### 🏷️ Przykład dependency w `pom.xml`:

```xml
<dependency>
    <groupId>jakarta.el</groupId>
    <artifactId>jakarta.el-api</artifactId>
    <version>5.0.1</version>
</dependency>
```

> 💡 Uwaga: Samo API nie zawiera implementacji — to tylko interfejsy. Do działania potrzebna jest **implementacja**, np.:
>
> ```xml
> <dependency>
>     <groupId>org.glassfish</groupId>
>     <artifactId>jakarta.el</artifactId>
>     <version>5.0.1</version>
> </dependency>
> ```

#### 🧾 Przykład EL w praktyce (np. w JSF):

```xml
<h:outputText value="#{user.name}" />
```

Lub w Bean Validation (Hibernate Validator):

```java
@AssertTrue(message = "User must be an adult")
@ELAssert(expression = "this.age >= 18")
private boolean isAdult;
```

Kod z moich testów:

```java
@NotNull
@Size(min = 1, max = 16384, message = "'name' must be between {min} and {max} characters long")
public String name;
```

#### 📌 Podsumowanie

| Właściwość            | Opis                                   |
|-----------------------|----------------------------------------|
| **Nazwa**             | `jakarta.el-api`                       |
| **Typ**               | API (interfejsy)                       |
| **Zastosowanie**      | EL w JSF, JSP, CDI, bean validation    |
| **Wersja Java EE**    | Następca `javax.el` w `Jakarta EE`     |
| **Potrzebuje impl.?** | ✅ Tak – np. `org.glassfish:jakarta.el` |

---

### 📘JSONassert

**JSONassert** to lekkie **Java dependency** (biblioteka), która umożliwia łatwe i **precyzyjne porównywanie dwóch
dokumentów JSON** podczas pisania testów jednostkowych lub integracyjnych.

#### ✨ Co umożliwia JSONassert?

- **Porównywanie dwóch JSON-ów** — sprawdza, czy struktury i wartości JSON są zgodne.
- **Porównywanie z dokładnością** — możesz zdecydować, czy JSON-y muszą być dokładnie takie same (strict) lub czy
  wystarczy zgodność kluczowych pól (non-strict).
- **Łatwa obsługa** — nie musisz parsować JSON-ów ręcznie, tylko przekazujesz je jako `String`.
- **Świetne do testów REST API** — sprawdzasz odpowiedzi serwera (`response.body()`) bez budowania obiektów Java.

#### 📦 Popularne metody JSONassert:

```java
JSONAssert.assertEquals(expectedJson, actualJson, strict);
```

- `expectedJson` – oczekiwany JSON jako `String`
- `actualJson` – otrzymany JSON jako `String`
- `strict` – `true` = dokładne dopasowanie (`order`, `klucze`, `wartości`)  
  `false` = luźniejsze dopasowanie (ignoruje kolejność, dodatkowe pola)

#### ✅ Przykład użycia:

```java
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;

public class SampleJsonTest {

    @Test
    void shouldCompareJsonStrings() throws Exception {
        String expected = "{\"id\":1, \"name\":\"Board A\"}";
        String actual = "{\"name\":\"Board A\", \"id\":1}";

        JSONAssert.assertEquals(expected, actual, false); // kolejność nieistotna
    }
}
```

#### 🛠 Kiedy warto używać JSONassert?

- Testy odpowiedzi API (`REST Assured`, `HttpClient`, itp.)
- Testy transformacji danych JSON → JSON
- Testy walidacji odpowiedzi backendu
- Gdy nie chcesz pisać własnych parserów/mapperów

#### 📥 Maven dependency:

```xml
<dependency>
    <groupId>org.skyscreamer</groupId>
    <artifactId>jsonassert</artifactId>
    <version>1.5.1</version> <!-- lub najnowsza -->
    <scope>test</scope>
</dependency>
```

Chcesz, że podam Ci też przykład **porównania z błędem** i jak wygląda wtedy komunikat w teście? 📜  
(bo JSONassert ma naprawdę fajne, czytelne komunikaty o błędach!) 🚀  
Super! Oto przykład, jak wygląda **porównanie, które się nie powiedzie** z użyciem JSONassert — razem z komunikatem
błędu, jaki zobaczysz w teście:

#### 🔥 Przykład testu, który się nie powiedzie:

```java
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;

public class JsonFailureTest {

    @Test
    void shouldFailWhenJsonDoesNotMatch() throws Exception {
        String expected = "{\"id\":1, \"name\":\"Board A\"}";
        String actual = "{\"id\":2, \"name\":\"Board B\"}";

        JSONAssert.assertEquals(expected, actual, true); // strict = true
    }
}
```

#### ❗ Wynik testu (przykładowy komunikat błędu):

```css
org.junit.ComparisonFailure:
Expected: {
  "id": 1,
  "name": "Board A"
}
Actual: {
  "id": 2,
  "name": "Board B"
}
Difference is: Different value found in node "id". Expected 1 but got 2.
Different value found in node "name". Expected "Board A" but got "Board B".

	at org.skyscreamer.jsonassert.JSONAssert.assertEquals(JSONAssert.java:xxx)
	...
```

#### ✍ Co tu się stało?

- JSONassert najpierw przeparsował oba JSON-y do struktury drzewa.
- Potem przeszedł po każdym kluczu i **wskazał dokładnie**, gdzie są różnice (`"id"` i `"name"`).
- Dostałeś **czytelny, szczegółowy raport**, a nie ogólny "Assertion failed" jak w zwykłych `assertEquals()`.

#### 🧠 Ciekawostka:

Jeśli byś ustawił `strict = false`, to JSONassert **zignorowałby** kolejność pól i ewentualne dodatkowe pola,
ale **wartości kluczy muszą się zgadzać**.

Chcesz, że jeszcze pokażę Ci przykład jak porównywać **część JSON-a** (np. tylko pewne klucze)? 🌟  
(bo JSONassert też to wspiera!) 🚀
Super! 🚀  
To pokażę Ci teraz, jak za pomocą **JSONassert** porównywać **tylko fragment JSON-a**, czyli nie wszystko na raz.

#### 🔥 Przykład: porównanie tylko wybranych kluczy (partial match)

```java
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;

public class JsonPartialTest {

    @Test
    void shouldPartiallyMatchJson() throws Exception {
        String expectedPartialJson = "{\"name\":\"Board A\"}";
        String actualFullJson = "{\"id\":2, \"name\":\"Board A\", \"createdAt\":\"2024-04-26T18:30:00\"}";

        JSONAssert.assertEquals(expectedPartialJson, actualFullJson, false); // strict = false
    }
}
```

#### ✅ Wynik testu:

✅ Test przejdzie pomyślnie, nawet jeśli pełny JSON zawiera więcej danych (`id`, `createdAt`).

**Dlaczego?**  
Bo `strict = false` mówi JSONassertowi:
> "Sprawdź tylko, czy w tym dużym JSON-ie jest `name = Board A`. Reszta mnie nie interesuje."

#### 🧠 Kiedy się tego używa?

- Gdy **nie interesują Cię wszystkie pola** (np. data utworzenia, ID generowane losowo).
- Gdy API **dodaje nowe pola** i nie chcesz, by Twój test od razu wybuchał.
- Gdy testujesz **częściowe odpowiedzi**, np. weryfikujesz tylko status lub tytuł.

#### ⚡ Jeszcze bardziej sprytnie: JSON z "wildcards"

JSONassert obsługuje też **specjalne znaki** (`*`) do bardziej zaawansowanego porównywania!  
Np. możesz napisać:

```java
String expected = "{\"id\":\"*\", \"name\":\"Board A\"}";
JSONAssert.assertEquals(expected, actual, false);
```

I wtedy `id` może mieć **cokolwiek** — byle był obecny!

Chcesz, że jeszcze pokażę Ci przykład jak użyć JSONassert razem z **dynamicznymi danymi** (np. ID, które zmieniają się
za każdym razem)? 🔥  
To jest mega przydatne w testach REST API!
Super! 😎 To teraz pokażę Ci **praktyczny przykład** użycia **JSONassert** w sytuacji, gdy masz **dynamiczne dane**
(np. ID, które za każdym razem jest inne).

#### 🔥 Przykład: Ignorowanie zmieniających się danych (np. `id`, `createdAt`)

Wyobraź sobie, że dostajesz z API taki **dynamiczny JSON**:

```json
{
  "id": 12345,
  "name": "Board A",
  "createdAt": "2025-04-26T18:45:00"
}
```

➡️ `id` oraz `createdAt` **za każdym razem są inne**.  
➡️ Ale **`name`** musi być zawsze `"Board A"`.

#### ✍ Kod testu:

```java
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;

public class JsonDynamicFieldsTest {

    @Test
    void shouldIgnoreDynamicFields() throws Exception {
        String expectedJson = "{"
                + "\"name\":\"Board A\""
                + "}";

        String actualJson = "{"
                + "\"id\":99999,"
                + "\"name\":\"Board A\","
                + "\"createdAt\":\"2025-04-26T18:50:00\""
                + "}";

        JSONAssert.assertEquals(expectedJson, actualJson, false); // strict = false
    }
}
```

✅ Test przejdzie, bo porównuje **tylko `name`**.  
❌ Nie wymaga zgodności `id` i `createdAt`.

#### 🧠 Co robi parametr `false`?

W tym miejscu:

```java
JSONAssert.assertEquals(expectedJson, actualJson, false);
```

- `false` oznacza **"nie sprawdzaj dokładnego dopasowania wszystkich pól"**.
- JSON może mieć więcej danych — liczy się tylko to, co Ty podałeś.

#### 🔥 Opcjonalnie: pełna walidacja (strict = true)

Jeśli byś ustawił:

```java
JSONAssert.assertEquals(expectedJson, actualJson, true);
```

to test by się **wywalił**, bo JSONy nie są **identyczne** (brakuje pól `id` i `createdAt` w expected).

#### ✨ Podsumowanie

- **strict = false** — sprawdzamy tylko to, co nas interesuje.
- Idealne do testów **REST API**, gdy np. ID, timestampy albo inne dane są dynamiczne.
- JSONassert = szybkie, lekkie i bardzo czytelne asercje JSON.

Chcesz, że jeszcze pokażę Ci jak **porównywać tablice JSON** (np. lista obiektów)? 🔥  
W API to bardzo często się przydaje! 🚀  
Świetnie! 🔥 Lecimy dalej — teraz pokażę Ci **jak JSONassert radzi sobie z tablicami JSON** (czyli np. gdy API
zwraca **listę obiektów**).

#### 🧠 Załóżmy, że dostajesz taki JSON od API:

```json
[
  {
    "id": 1,
    "name": "Board A"
  },
  {
    "id": 2,
    "name": "Board B"
  }
]
```

#### ✍ Kod testu w Java z JSONassert:

```java
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;

public class JsonArrayTest {

    @Test
    void shouldCompareJsonArray() throws Exception {
        String expectedJson = "["
                + "{ \"name\": \"Board A\" },"
                + "{ \"name\": \"Board B\" }"
                + "]";

        String actualJson = "["
                + "{ \"id\": 1, \"name\": \"Board A\" },"
                + "{ \"id\": 2, \"name\": \"Board B\" }"
                + "]";

        JSONAssert.assertEquals(expectedJson, actualJson, false); // strict = false
    }
}
```

#### 📋 Co się tutaj dzieje?

- **`expectedJson`** — podajemy tylko pola, które **nas interesują** (`name`).
- **`actualJson`** — może mieć dodatkowe dane (`id`).
- **`strict = false`** — oznacza, że **extra pola są ignorowane**.

✅ Test przejdzie!  
Nie musisz podawać ID ani innych zmieniających się danych.

#### 💥 A co jakby `strict = true`?

Jeśli napiszemy:

```java
JSONAssert.assertEquals(expectedJson, actualJson, true);
```

➡️ test się **wywali**, bo brakuje w expected takich samych `id` jak w actual.  
Przy `strict = true` **JSONy muszą być identyczne**.

#### 🧩 Co jeszcze możesz robić z tablicami?

- Sprawdzać kolejność elementów (jeśli strict = true).
- Weryfikować konkretne elementy (np. pierwszy element tablicy).
- Testować fragmenty dużych odpowiedzi JSON.

#### ✨ Podsumowując

| Cecha                                         | strict = false | strict = true          |
|:----------------------------------------------|:---------------|:-----------------------|
| Czy porównuje wszystkie pola?                 | ❌ Nie          | ✅ Tak                  |
| Czy ważna jest kolejność elementów w tablicy? | ❌ Nie          | ✅ Tak                  |
| Czy sprawdza dodatkowe pola?                  | ❌ Ignoruje     | ✅ Wymaga identyczności |

---

### 📘JSON Schema Validator

🔹 **JSON Schema Validator** to biblioteka do **walidacji odpowiedzi JSON** na podstawie schematu **JSON Schema**.
Jest często używana w połączeniu z **REST Assured**, aby sprawdzać, czy odpowiedzi API są zgodne z oczekiwanym formatem.

#### **📥 Dodanie JSON Schema Validator do projektu**

**Maven (pom.xml)**
```xml
<dependency>
    <groupId>io.rest-assured</groupId>
    <artifactId>json-schema-validator</artifactId>
    <version>5.4.0</version>
    <scope>test</scope>
</dependency>
```

**Gradle (build.gradle.kts)**
```kotlin
dependencies {
    testImplementation("io.rest-assured:json-schema-validator:5.4.0")
}
```

📌 **Biblioteka jest kompatybilna z REST Assured, ale może być używana samodzielnie.**

#### **🔍 Jak działa JSON Schema Validator?**

Schemat **JSON Schema** definiuje strukturę i typy pól w odpowiedzi JSON. Możemy go używać do walidacji poprawności API.

#### **📌 Przykładowy JSON Schema (`user-schema.json`):**
```json
{
  "$schema": "http://json-schema.org/draft-07/schema#",
  "type": "object",
  "properties": {
    "id": { "type": "integer" },
    "name": { "type": "string" },
    "email": { "type": "string", "format": "email" }
  },
  "required": ["id", "name", "email"]
}
```
Ten schemat określa, że **`id`** musi być liczbą, **`name`** tekstem, a **`email`** poprawnym adresem e-mail.

#### **🚀 Użycie JSON Schema Validator z REST Assured**

**✅ Walidacja odpowiedzi API z JSON Schema**
```java
import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.*;

public class JsonSchemaTest {
    public static void main(String[] args) {
        given()
            .when()
                .get("https://jsonplaceholder.typicode.com/users/1")
            .then()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemas/user-schema.json"));
    }
}
```
📌 **Co tu się dzieje?**  
✅ Pobieramy użytkownika `GET /users/1`  
✅ Sprawdzamy, czy **status HTTP to 200**  
✅ Walidujemy, czy odpowiedź pasuje do **`user-schema.json`**

#### **🛠️ Zaawansowana walidacja schematu JSON**

**1️⃣ Walidacja z dynamicznymi danymi (ignoring fields)**  
Czasem nie chcemy walidować wszystkich pól, np. **dynamicznych ID czy dat**. Możemy użyć **`with().ignoring()`**:
```java
body(matchesJsonSchemaInClasspath("schemas/user-schema.json")
     .using(jsonSchemaFactory().with().ignoring("id")));
```

**2️⃣ Walidacja wielu obiektów w tablicy JSON**  
Jeśli API zwraca listę użytkowników:
```json
[
  { "id": 1, "name": "Jan", "email": "jan@demo.com" },
  { "id": 2, "name": "Anna", "email": "anna@demo.com" }
]
```
Możemy sprawdzić, czy każdy obiekt w tablicy pasuje do schematu:
```java
body("$", everyItem(matchesJsonSchemaInClasspath("schemas/user-schema.json")));
```

#### **🎯 Dlaczego warto używać JSON Schema Validator?**
✅ **Gwarantuje poprawność odpowiedzi API**  
✅ **Integruje się z REST Assured, JUnit i TestNG**  
✅ **Pozwala na ignorowanie dynamicznych pól**  
✅ **Obsługuje typy danych, formaty i wymagalność pól**  
✅ **Łatwo sprawdza struktury obiektów i tablic**

🔹 **To idealne narzędzie do testowania API!** 🚀

---

### 📘Project Lombok

🔹 **Project Lombok** to biblioteka do **automatycznego generowania kodu** w Javie. Pozwala **usunąć boilerplate code**,
taki jak **gettery, settery, konstruktory, `toString()`, `equals()` itp.**, dzięki wykorzystaniu **adnotacji**.

**✅ Zalety Lomboka:**  
✔ Skraca kod i poprawia jego czytelność  
✔ Automatycznie generuje kod w czasie kompilacji  
✔ Eliminuje konieczność pisania powtarzalnych metod

#### **📥 Dodanie Lomboka do projektu**

**📌 Maven (pom.xml)**
```xml
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <version>1.18.30</version>
    <scope>provided</scope>
</dependency>
```
📌 **`scope: provided`** oznacza, że Lombok jest używany tylko w czasie kompilacji i nie jest wymagany w środowisku wykonawczym.

#### **🛠️ Instalacja w IDE**

**📌 IntelliJ IDEA**
1. Otwórz **File > Settings > Plugins**
2. Wyszukaj **Lombok** i zainstaluj
3. Przejdź do **Settings > Build, Execution, Deployment > Compiler > Annotation Processors**
4. Zaznacz **Enable annotation processing**

**📌 Eclipse**
1. Pobierz plugin: [Lombok dla Eclipse](https://projectlombok.org/download)
2. Uruchom plik JAR (`java -jar lombok.jar`) i wskaż katalog Eclipse
3. Restartuj IDE

#### **🚀 Przykłady użycia Lomboka**

**1️⃣ Gettery i settery (`@Getter`, `@Setter`)**  
Zamiast pisać ręcznie:
```java
public class User {
    private String name;
    private int age;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
}
```
Użyj **Lomboka**:
```java
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    private String name;
    private int age;
}
```
📌 **Lombok automatycznie wygeneruje gettery i settery w czasie kompilacji!**

**2️⃣ Konstruktor (`@AllArgsConstructor`, `@NoArgsConstructor`)**  
Zamiast:
```java
public class User {
    private String name;
    private int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
```
Użyj:
```java
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor  // Konstruktor dla wszystkich pól
@NoArgsConstructor   // Konstruktor bezargumentowy
public class User {
    private String name;
    private int age;
}
```

**3️⃣ ToString, Equals i HashCode (`@ToString`, `@EqualsAndHashCode`)**
```java
import lombok.ToString;
import lombok.EqualsAndHashCode;

@ToString
@EqualsAndHashCode
public class User {
    private String name;
    private int age;
}
```
📌 Teraz **`toString()`**, **`equals()`** i **`hashCode()`** są generowane automatycznie!

**4️⃣ Builder (`@Builder`) – Tworzenie obiektów w stylu Fluent API**  
Zamiast pisać własną klasę Builder:
```java
import lombok.Builder;

@Builder
public class User {
    private String name;
    private int age;
}
```
Możesz teraz używać:
```java
User user = User.builder()
    .name("Jan Kowalski")
    .age(30)
    .build();
```
📌 **Czytelniejszy i bardziej elastyczny sposób tworzenia obiektów!**

**5️⃣ Wartości niemutowalne (`@Value`)**  
Działa jak **`record`** w Javie 17:
```java
import lombok.Value;

@Value
public class User {
    String name;
    int age;
}
```
📌 **Klasa staje się niemutowalna (immutable)** – brak setterów, tylko **final** pola.

**6️⃣ Logowanie (`@Slf4j`)**  
Nie musisz ręcznie tworzyć loggera:
```java
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoggerExample {
    public static void main(String[] args) {
        log.info("To jest logowanie z Lombok");
    }
}
```
📌 **Automatycznie generuje Loggera dla klasy!**

#### **🎯 Podsumowanie**
✅ **Mniej kodu – więcej funkcjonalności**  
✅ **Łatwa integracja z IntelliJ i Eclipse**  
✅ **Obsługa wzorców projektowych (Builder, Singleton, DTO)**  
✅ **Szybszy rozwój aplikacji**

**💡 MUST-HAVE w nowoczesnych projektach Java! 🚀**

---

### 📘Jackson Databind

🔹 **Jackson Databind** to biblioteka do **mapowania obiektów Java na JSON i odwrotnie**. Jest częścią ekosystemu
**Jackson** i zapewnia prostą obsługę konwersji danych.

**✅ Zalety Jackson Databind:**  
✔ **Szybka i wydajna** konwersja JSON ↔ Java  
✔ Obsługa **klas POJO** i kolekcji  
✔ Możliwość **customizacji** za pomocą adnotacji  
✔ Współpraca z frameworkami jak **Spring, Hibernate, JAX-RS**

#### **📥 Dodanie do projektu**

**📌 Maven (pom.xml)**
```xml
<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-databind</artifactId>
    <version>2.16.0</version>
</dependency>
```

**📌 Gradle (build.gradle)**
```gradle
implementation 'com.fasterxml.jackson.core:jackson-databind:2.16.0'
```

#### **🚀 Przykłady użycia**

**1️⃣ Konwersja Java → JSON**  
Zamiast ręcznie tworzyć JSON:
```java
import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonExample {
    public static void main(String[] args) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        User user = new User("Jan", "Kowalski", 30);
        String json = objectMapper.writeValueAsString(user);

        System.out.println(json);
    }
}
```
✅ **Wyjście:**
```json
{"firstName":"Jan","lastName":"Kowalski","age":30}
```

**2️⃣ Konwersja JSON → Java**
```java
String json = "{\"firstName\":\"Jan\",\"lastName\":\"Kowalski\",\"age\":30}";

User user = objectMapper.readValue(json, User.class);
System.out.println(user.getFirstName());  // Jan
```

**3️⃣ Ignorowanie pól (`@JsonIgnore`)**  
Jeśli pole nie powinno być zapisane w JSON:
```java
import com.fasterxml.jackson.annotation.JsonIgnore;

public class User {
    private String firstName;
    private String lastName;

    @JsonIgnore
    private int age;
}
```
✅ Teraz **`age`** nie będzie w JSON!

**4️⃣ Zmiana nazwy pola (`@JsonProperty`)**
```java
import com.fasterxml.jackson.annotation.JsonProperty;

public class User {
    @JsonProperty("imie")
    private String firstName;

    @JsonProperty("nazwisko")
    private String lastName;
}
```
✅ **JSON wynikowy:**
```json
{"imie":"Jan","nazwisko":"Kowalski"}
```

**5️⃣ Obsługa list (`List<T>`)**
```java
List<User> users = Arrays.asList(new User("Jan", "Kowalski", 30), new User("Anna", "Nowak", 25));

String json = objectMapper.writeValueAsString(users);
System.out.println(json);
```
✅ **Lista w JSON:**
```json
[{"firstName":"Jan","lastName":"Kowalski","age":30}, {"firstName":"Anna","lastName":"Nowak","age":25}]
```

#### **🎯 Podsumowanie**
✅ **Najpopularniejsza biblioteka JSON ↔ Java**  
✅ **Łatwa w użyciu, szybka i elastyczna**  
✅ **Obsługuje mapowanie POJO, kolekcje, adnotacje**  
✅ **Niezastąpiona w Spring Boot i API REST**

💡 **Idealne narzędzie do pracy z JSON w Javie! 🚀**

---

### 📘Jakarta JSON Processing API (JSON-P)

🔹 **Jakarta JSON Processing API (JSON-P)** to **standardowa biblioteka do przetwarzania JSON** w języku Java. Pozwala
na **parsowanie, generowanie i manipulację JSON-em** zarówno w sposób strumieniowy (streaming API), jak i w modelu
obiektowym (object model API).

🛠 **Przydatne w:**  
✔ Przetwarzaniu danych JSON w aplikacjach Java  
✔ Tworzeniu i edytowaniu struktur JSON  
✔ Obsłudze API REST w Java EE / Jakarta EE

#### **📥 Dodanie do projektu**

**📌 Maven (pom.xml)**
```xml
<dependency>
    <groupId>jakarta.json</groupId>
    <artifactId>jakarta.json-api</artifactId>
    <version>2.1.2</version>
</dependency>
```

**📌 Gradle (build.gradle)**
```gradle
implementation 'jakarta.json:jakarta.json-api:2.1.2'
```

#### **🚀 Przykłady użycia**

**1️⃣ Tworzenie JSON w sposób programowy**
```java
import jakarta.json.Json;
import jakarta.json.JsonObject;

public class JsonExample {
    public static void main(String[] args) {
        JsonObject json = Json.createObjectBuilder()
                .add("imie", "Jan")
                .add("nazwisko", "Kowalski")
                .add("wiek", 30)
                .build();

        System.out.println(json);
    }
}
```
✅ **Wyjście:**
```json
{"imie":"Jan","nazwisko":"Kowalski","wiek":30}
```

**2️⃣ Parsowanie JSON (czytanie JSON do obiektu Java)**
```java
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import java.io.StringReader;

public class JsonParsingExample {
    public static void main(String[] args) {
        String jsonString = "{\"imie\":\"Jan\",\"nazwisko\":\"Kowalski\",\"wiek\":30}";
        
        JsonReader reader = Json.createReader(new StringReader(jsonString));
        JsonObject jsonObject = reader.readObject();
        
        System.out.println("Imię: " + jsonObject.getString("imie"));
        System.out.println("Nazwisko: " + jsonObject.getString("nazwisko"));
        System.out.println("Wiek: " + jsonObject.getInt("wiek"));
    }
}
```
✅ **Wyjście:**
```
Imię: Jan  
Nazwisko: Kowalski  
Wiek: 30  
```

**3️⃣ Obsługa tablic JSON (`JsonArray`)**
```java
import jakarta.json.Json;
import jakarta.json.JsonArray;

public class JsonArrayExample {
    public static void main(String[] args) {
        JsonArray jsonArray = Json.createArrayBuilder()
                .add(Json.createObjectBuilder().add("imie", "Jan").add("wiek", 30))
                .add(Json.createObjectBuilder().add("imie", "Anna").add("wiek", 25))
                .build();

        System.out.println(jsonArray);
    }
}
```
✅ **Wyjście:**
```json
[
    {"imie":"Jan","wiek":30},
    {"imie":"Anna","wiek":25}
]
```

**4️⃣ Streaming API – przetwarzanie JSON kawałek po kawałku**  
Streaming API jest bardziej wydajne, ponieważ nie wymaga przechowywania całego JSON-a w pamięci.
```java
import jakarta.json.stream.JsonGenerator;
import java.io.StringWriter;

public class StreamingExample {
    public static void main(String[] args) {
        StringWriter writer = new StringWriter();
        JsonGenerator generator = Json.createGenerator(writer);
        
        generator.writeStartObject()
                .write("imie", "Jan")
                .write("nazwisko", "Kowalski")
                .write("wiek", 30)
                .writeEnd();
        generator.close();
        
        System.out.println(writer);
    }
}
```
✅ **Wyjście:**
```json
{"imie":"Jan","nazwisko":"Kowalski","wiek":30}
```

#### **🎯 Podsumowanie**
✅ **Standardowa biblioteka JSON dla Jakarta EE**  
✅ **Obsługuje model obiektowy i API strumieniowe**  
✅ **Lekka, szybka i kompatybilna z Java SE i Java EE**  
✅ **Alternatywa dla Jackson i Gson**

💡 **Idealne do przetwarzania JSON w aplikacjach enterprise! 🚀**
