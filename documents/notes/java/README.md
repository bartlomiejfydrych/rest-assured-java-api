# ☕Java — notatki

# 📑Spis treści

- [ENV — Zmienne środowiskowe](#env)
- [Enum](#enum)
- [Typ zmiennej – Long](#long)

---

# 📝Opis

## 📄ENV — Zmienne środowiskowe <a name="env"></a>

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

## 📄Enum <a name="enum"></a>

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

## 📄Typ zmiennej – Long <a name="long"></a>

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
