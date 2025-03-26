# 🌐REST Assured i testy – notatki

# 📑Spis treści

- [START – rozpoczęcie pisania testów](#start_writing_tests)

---

# 📝Opis

## 📄START – rozpoczęcie pisania testów <a name="start_writing_tests"></a>

1. Zakładamy `konta` i inne `dostępy`
    - W przypadku tego projektu zakładamy `konto` oraz zdobywamy `API key` oraz `token` na stronie **Trello**
    - Szczegóły w `README` katalogu `📂trello-configuration`
2. W katalogu `src/main/resources` tworzymy katalog `configs`, a w nim plik o nazwie `config.properties`  
   Wszelkie ustawienia projektu warto trzymać i odczytywać z osobnego pliku, aby nie musieć nic zmieniać w samym kodzie.  
   Zapisujemy w nim takie rzeczy jak:
   - `baseUrl`
3. W katalogu `src/main/java` tworzymy katalog o nazwie `configuration`
4. W katalogu `configuration` tworzymy plik java class o nazwie `Config.java`
5. W klasie tej tworzymy:
   - mechanizm ładujący/czytający i re-używający plik konfiguracyjny
   - metody pomocnicze np. wymagające niektórych parametrów lub dające możliwość ustawiania wartości domyślnej
   - metodę pobierającą bazowy URL
6. Sprawdzamy, czy mamy w `pom.xml` dodane dependecy o nazwie `Dotenv Java`
7. Otwieramy plik `.gitignore` i dopisujemy w nim:  
   ```ignore
   ### MY FILES

   # environment
   environment/.env
   ```
8. W głównym katalogu projektu tworzymy katalog o nazwie `environment`
9. W nim tworzymy dwa pliki:  
   - `.env` (tutaj będziemy przechowywać nasze prawdziwe zmienne)
   - `.env.example` (tutaj będzie pusty wzór dostępny na repozytorium)
10. W plikach `.env` definiujemy zmienne z naszym `API key` oraz `token`:  
   ```ini
   # File .env – environment variables
   
   # Trello API key & token
   TRELLO_API_KEY=yourTrelloApiKey
   TRELLO_TOKEN=yourTrelloToken
   ```
11. CDN