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
6. 