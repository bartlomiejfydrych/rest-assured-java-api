# ☕Java — notatki

# 📑Spis treści

- [ENV — Zmienne środowiskowe](#env)

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

