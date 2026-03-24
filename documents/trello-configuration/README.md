# 🔼Trello – konfiguracja

1. Zakładamy konto na stronie `Trello`:  
   https://trello.com/
2. Zakładamy konto na `Trello Developers`:  
   (UWAGA: Teraz chyba powinno działać na tym nasze pierwsze konto)  
   https://developer.atlassian.com/cloud/trello/
3. Na naszym koncie tworzymy `Przestrzeń roboczą (Workspace)`:
   - W prawym, górnym rogu klikamy na `ikonkę naszego profilu`
   - Klikamy `Utwórz przestrzeń roboczą`  
     ![Przestrzeń robocza – przycisk dodania nowej](images/trello_workspace_1.png)
   - Wypełniamy pola poniższego formularza:  
     ![Przestrzeń robocza – formularz dodawania](images/trello_workspace_2.PNG)
     - Podajemy jej `nazwę`
     - Wybieramy jej `typ`
     - Podajemy jej `opis`
     - Klikamy przycisk `Kontynuuj`
4. Na poniższej stronie zapoznajemy się z informacjami z sekcji **Managing your API Key**:  
   https://developer.atlassian.com/cloud/trello/guides/rest-api/api-introduction/  
   Po co? Żeby lepiej wiedzieć, jak to wszystko działa oraz jak "na szybko" można puszczać pierwsze requesty do API Trello.
5. Tworzymy `Trello Power-Up`:
   - Wchodzimy na poniższą stronę i zaczynamy zapoznawać się z informacjami:  
     https://developer.atlassian.com/cloud/trello/guides/power-ups/managing-apps/#adding-a-new-custom-power-up  
     Po co? Jest tu ogólna instrukcja i informacje o tym, jak dodawać **Power Up'y**
   - Zarządzanie `Power-Up'ami` odbywa się za pośrednictwem strony:  
     https://trello.com/power-ups/admin  
     ![Power Up – dodawanie nowego](images/trello_powerup_1.PNG)
     - Klikamy przycisk `Nowe`
   - Wypełniamy pola poniższego formularza:  
     ![Power Up – formularz dodawania nowego](images/trello_powerup_2.PNG)
     - Podajemy `nazwę dodatku`
     - Wybieramy `przestrzeń roboczą`
     - Podajemy `adres URL` (może być byle jaki)
     - Podajemy `email` (może być nasz)
     - Podajemy `email pomocy technicznej` (może być nasz)
     - Podajemy `autora` (możemy być my)
     - Klikamy przycisk `Utwórz`
6. Po udanym stworzeniu `Power UP'a` zostaniemy przeniesieni na stronę z `Podstawowymi informacjami`.  
   ![Power Up – podstawowe informacje](images/trello_powerup_3.png)
   Opcjonalnie możemy wybrać kategorie:
    - Główną: `Automatyzacja`
    - Dodatkową: `IT i zarządzanie projektami`
    - Klikamy przycisk `Zapisz`
7. W menu po lewej klikamy `Możliwości` i **włączamy** wszystkie `opcje`  
   (Wydaje mi się, że umożliwi to pisanie testów pod większą ilość endpoint'ów)  
   ![Power Up – możliwości](images/trello_powerup_4.PNG)
8. W menu po lewej klikamy `Klucz API` i **generujemy nowy klucz API** lub **używamy istniejącego klucza API**  
   ![Power Up – klucz API tworzenie](images/trello_powerup_5.PNG)
9. Gdy to się uda zostanie otwarta strona z `kluczem API` oraz `Sekretem`  
   ![Power Up – klucz API utworzony](images/trello_powerup_6.png)
   - Kopiujemy/Zapisujemy gdzieś `Klucz API`
   - Kopiujemy/Zapisujemy gdzieś `Sekret`
10. Po prawej od `Klucz API` klikamy hiperłącze w słowie `token`
11. Zostaniemy przeniesieni do strony z podsumowaniem i klikamy przycisk `Pozwalaj`  
    ![Power Up – token formularz do zatwierdzenia](images/trello_powerup_7.png)
12. Zostaniemy przeniesieni na stronę, na której będzie wyświetlony nasz `token`.  
    Kopiujemy/Zapisujemy go.  
    ![Power Up – token](images/trello_powerup_8.PNG)
13. Możemy rozpocząć pisanie `pierwszych requestów do API`

# 🧭Dalsze kroki — rozpoczęcie pisania testów <a name="next_steps_writing_tests"></a>

Dalsze kroki opisujące jak rozpocząć pisanie testów znajdują się w:  
📁rest-assured-java-api (projekt)  
&emsp;📁documents  
&emsp;&emsp;📁notes  
&emsp;&emsp;&emsp;📂rest-assured-and-tests  
&emsp;&emsp;&emsp;&emsp;[README.md](../notes/rest-assured-and-tests/README.md)
