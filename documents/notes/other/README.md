# 📒Pozostałe notatki

# 📑Spis treści

- [🗃Repozytorium z kursu](#repository_from_the_course)
- [🐱GIT – treść commitów (dywagacje)](#git_commit_messages)

---

## 🗃Repozytorium z kursu <a name="repository_from_the_course"></a>

**Link:**  
https://github.com/simplelogic2023szkolenie

## 🐱GIT – treść commitów (dywagacje) <a name="git_commit_messages"></a>

> Powiedz mi, jak powinny wyglądać treści commitów w Git po polsku.
> 
> Zazwyczaj piszę coś takiego:
> 
>> Przygotowania testów dla {POST_CreateNewCard}.
> 
> lub
> 
>> 3271-[T]-test_create – Dodanie do testów brakującego uprawnienia {RATE}
> 
> Czy jest to prawidłowe?

Twoje przykłady **nie są błędne**, ale można je ulepszyć, aby były bardziej **spójne**, **standardowe** i **łatwiejsze
do przeszukiwania**. Poniżej daję Ci dokładne wytyczne + pokazuję, jak poprawnie pisać commit messages **po polsku**,
zgodnie z dobrymi praktykami Git.

### ✅ Jak powinien wyglądać dobry commit po polsku?

#### **1. Krótka, konkretna linia (max 72 znaki)**

* w trybie **rozkazującym** (np. "Dodaj", "Usuń", "Popraw"), nie w czasie przeszłym
* opisuje *co commit robi*, a nie *co autor zrobił*

Przykłady:

* `Dodaj obsługę tworzenia nowych kart`
* `Popraw test tworzenia karty`
* `Usuń zbędne logowanie w module płatności`
* `Dodaj brakujące uprawnienie RATE do testów`

Dlaczego tryb rozkazujący?  
→ Bo odpowiada na pytanie: **"Co zrobi ten commit?"**, a nie "co ja zrobiłem". Dokładnie tak robi Git w commitach typu
*Merge*, *Revert* itp.

#### **2. Opcjonalny opis (kilka linijek)**

Możesz dodać szczegóły poniżej (po pustej linii):

```
Dodaj brakujące uprawnienie RATE do testu POST_CreateNewCard

Uprawnienie RATE było wymagane po ostatniej zmianie w API.
Bez niego test kończył się błędem 403.
```

#### **3. Jeśli używasz ticketów (Jira itp.) – stosuj jednolity format**

Najczęstsze schematy:

##### ✔ **[ID-zadania] Opis**

```
3271-T Dodaj brakujące uprawnienie RATE do testu create card
```

##### ✔ **ID: Opis**

```
3271: Dodaj test dla POST_CreateNewCard
```

##### ✔ **ID – krótki opis**

```
3271 – Test: Dodaj obsługę RATE
```

Zasada: *najpierw identyfikator zadania, potem opis commitowanych zmian*.

### 🔍 A co z Twoimi przykładami?

Twoje:

> Przygotowania testów dla {POST_CreateNewCard}.

🔸 Problemy:

* to forma rzeczownikowa ("przygotowania"), nie mówi co commit robi
* brak standardowego stylu
* opis jest niejednoznaczny (czy coś dodajesz? poprawiasz? refaktorujesz?)

Lepsza wersja:

`Dodaj przygotowanie testów dla POST_CreateNewCard`  
lub  
`Wprowadź strukturę testów dla POST_CreateNewCard`

Twoje:

> 3271-[T]-test_create – Dodanie do testów brakującego uprawnienia {RATE}

✔ To już BARDZO dobre — jest ID, jest opis.  
Można tylko poprawić styl:

`3271-T Dodaj brakujące uprawnienie RATE do testu create`

Ja bym pisał:

`3271-T Dodaj brakujące uprawnienie RATE do testu create`

lub bardziej neutralnie:

`3271-T Uzupełnij test create o uprawnienie RATE`

### ⭐ Podsumowanie – złoty standard po polsku

#### ✔ Tryb rozkazujący

* Dodaj
* Popraw
* Usuń
* Uzupełnij
* Refaktoruj
* Zaktualizuj

#### ✔ Krótka pierwsza linia (jedno zdanie)

#### ✔ Opcjonalny opis poniżej

#### ✔ Jeśli są tickety – jednolity format

> Mój format:
>> 3271-[T]-test_create – Dodanie do testów brakującego uprawnienia {RATE}
> 
> Jest taki, dlatego że:
>> 3271 - numer story w jira  
>> [T] - literka czy robił to Frontend, Backend czy Tester itd.  
>> test_create - kolekcja testów w której dokonano zmian (dawanie jej na początku ma ułatwić testerom szukanie, gdy
>> przeglądają po commitach, które kolekcje były zmieniane w danym momencie).
> 
> Czy w związku z tym dalej uważasz go nie do końca za prawidłowy?
> Jeśli tak i wiedząc to co napisałem, zaproponuj jakiś lepszy.
> 
> Zauważyłem, że w treści pomijasz znaki [], {}, które moim zdaniem zwiększają czytelność. Dlaczego?
> 
> Powiedz mi jeszcze, skoro w treści commita piszemy co robi dany commit, a nie autor, a przekład z angielskiego ma
> trochę więcej możliwości tłumaczenia to nie powinno być:
> - Dodaję
> - Poprawiam
> - Usuwam
> - Uzupełniam
> - Refaktoryzuję
> - Aktualizuję
> - itp.?

Świetne pytania — bardzo dojrzałe podejście do tworzenia commitów.
Odpowiem **konkretnie, technicznie i bez lania wody**, uwzględniając Twoją obecną strukturę i potrzeby zespołu.

### ✅ 1. Czy Twój format commitów jest „nieprawidłowy”?

**Nie, nie jest nieprawidłowy.**  
Jest logiczny, ma swoją strukturę, odpowiada potrzebom zespołu i jest spójny.  
To **znacznie ważniejsze niż trzymanie się jednego „idealnego” standardu**.

Natomiast można go:

* uprościć,
* ujednolicić,
* uczynić bardziej „git-standardowym” i przeszukiwalnym.

Twój format:

```
3271-[T]-test_create – Dodanie do testów brakującego uprawnienia {RATE}
```

Nie jest błędny — można go tylko dopracować.

### ✨ 2. Co można poprawić?

#### ✔ **1. Uniknięcie nadmiaru znaków specjalnych**

Znaków `{}`, `[]`, `–`, `-` jest sporo. Git nie ma nic przeciwko, ale:

* zwiększają „szum wizualny”,
* pogarszają przeszukiwanie historii,
* mogą się mylić w skrypcie pipeline, grepach itd.

Natomiast jeśli Twój zespół **lubi** takie formatowanie — można je zachować, tylko uprościć.

#### ✔ **2. Kolejność elementów**

Git standardowo (i narzędzia typu GitLog/Git Graph/GitLens) wyróżnia **lewy początek commit message jako najważniejszy klucz**.

W Twoim przypadku:

* Jira ID → OK na początku
* `[T]` → OK
* `test_create` → OK
* Ale separator `-` + `-` + `–` może się zlewać

### 💡 3. Lepsza propozycja formatowania (zachowująca Twoją logikę)

#### **Wersja A — minimalistyczna, najbardziej czytelna**

```
3271 [T] test_create: Dodaj brakujące uprawnienie RATE
```

Dlaczego dobrze działa?

* `:` jest standardowym separatorem w commitach
* Bez nawiasów `{}`, bo nie wnoszą informacji
* Bez myślników, które utrudniają szybkie skanowanie
* Komenda zaczyna się od czasownika (Git way)

#### **Wersja B — z zachowaniem Twoich nawiasów**

Jeśli naprawdę chcesz zachować `{}` i `[]`:

```
3271 [T] {test_create}: Dodanie brakującego uprawnienia {RATE}
```

↑ Bardziej estetyczne, mniej separatorów.

#### **Wersja C — jeśli bardzo zależy Ci na trójczłonowej strukturze**

```
3271 [T] - test_create - Dodaj uprawnienie RATE do testów
```

### 🔍 4. Dlaczego ja nie używam `[]` i `{}`?

Nie dlatego, że są „złe”, tylko dlatego że:

1. **Git log / grep / narzędzia CI/CD łatwiej filtrują słowa bez nawiasów.**

   ```
   git log --grep="test_create"
   ```

   Zadziała w każdym formacie.
   Ale:

   ```
   git log --grep="{test_create}"
   ```

   → Zależy od dokładnego użycia nawiasów.

2. **Wszystkie popularne standardy commitów nie używają nawiasów**:

    * Conventional Commits
    * Angular Commit Style
    * GitHub Commits Guidelines
    * GitMoji

Nawiasy nie są złe — tylko nie są standardem.

### 🧠 5. Czy commit po polsku powinien zaczynać się od:

* **Dodaję**
* **Usuwam**
* **Refaktoryzuję**
* itd.?

#### ❌ *Nie.*

I to nie wynika z tłumaczenia — tylko z konwencji Git.

#### ✔ Git używa TRYBU ROZKAZUJĄCEGO

Git automatycznie tworzy wiadomości np.:

* `Merge branch 'x'`
* `Revert commit abc`
* `Fix typo`

Nigdy:

* *Merguję*
* *Revertuję*
* *Poprawiłem*

#### Dlaczego tryb rozkazujący?

Bo commit opisuje **co commit robi**, a nie „ja coś robię”.

**Commit message to „polecenie dla repozytorium”, nie notatka autora.**

Dlatego poprawny format to:

* `Dodaj`
* `Popraw`
* `Usuń`
* `Refaktoruj`
* `Uzupełnij`
* `Aktualizuj`

A nie:

* ~Dodaję~
* ~Poprawiam~
* ~Usuwam~

### 🎯 PODSUMOWANIE – rekomendowany format dla Twojego zespołu

Najbardziej profesjonalna, czytelna i „industry standard” wersja:

```
3271 [T] test_create: Dodaj brakujące uprawnienie RATE
```

✔ czytelne  
✔ łatwe do filtrowania  
✔ estetyczne  
✔ zgodne z konwencjami Git  
✔ zachowuje ID Jira, typ autora i nazwę kolekcji

> A czy zamiast znaków: [], {}, -
> 
> Można lub lepiej używać znaków: '', "", ``
> 
> Czy na to samo wychodzi albo nawet jeszcze gorzej?

Krótko i konkretnie:

### ❌ Nie, `' " `` ` nie są lepsze od `{ } [ ]` — są **gorsze** w commitach.

I to z kilku konkretnych powodów:

### 🧨 1. Cudzysłowy `' "` robią problemy w narzędziach Git/CI/CD

Znaki:

* `'` (apostrof)
* `"` (cudzysłów)

mogą:

* rozwalać parsowanie w terminalach,
* wymuszać escape’owanie,
* zakłócać grep,
* powodować problemy w skryptach, regexach i API.

Przykład problematyczny:

```
git log --grep="test_create"
```

vs

```
git log --grep="'test_create'"
```

A już w ogóle kłopotliwe jest:

```
3271 "[T]" "test_create": "Dodaj RATE"
```

Żadne narzędzie nie zakłada, że commit będzie zawierał cudzysłowy jako element struktury.

### 🧨 2. Backticks `` ` `` wyglądają technicznie, ale też przeszkadzają

Markdown używa `` ` `` do oznaczania kodu.
GitHub wyświetlając commity w interfejsie **potraktuje to jak kod**, np.:

```
Dodaj funkcję `RATE_HANDLER`
```

To jest OK, bo to *fragment kodu*.

Ale jeśli użyjesz backticków jako "separatora", to będzie wyglądać, jakby commit był błędnie sformatowany:

```
3271 `T` `test_create`: Dodaj RATE
```

Wygląda to:

* dziwnie,
* nienaturalnie wobec standardów gitowych,
* może sugerować, że to nazwy zmiennych.

### 🧨 3. W żaden sposób nie ułatwiają filtrowania

Grep nie przestaje działać, ale commit robi się mniej naturalny:

* `test_create` → czytelne
* `'test_create'` → wizualny szum
* `"test_create"` → jeszcze większy szum
* `` `test_create` `` → markdownowo wygląda jak kod, ale nie jest

Zawsze lepiej pozostawić słowa jako zwykłe tokeny.

### 🧨 4. Jeśli już używać „obramowań”, to **nawiasy** są najlepszym wyborem

Ważne:

**W świecie commitów nawiasy są standardem.
Cudzysłowy — nie.
Backticki — nie.**

Najbardziej akceptowalne są:

* `[]` — do typowania (np. `[T]`, `[Fix]`, `[Docs]`)
* `{}` — do oznaczania parametrów (jeśli musisz)
* `()` — do wskazywania zakresu lub nazwy modułu (najbardziej popularne!)

### ❤️ 5. Najlepszy, najbardziej profesjonalny format (branżowo):

Najpopularniejszy wzorzec ze świata IT (inspirowany Angular / Conventional Commits):

```
<task-id> <tag> (<module>): <akcja>
```

W Twoim przypadku:

```
3271 [T] (test_create): Dodaj brakujące uprawnienie RATE
```

Dlaczego **()**?

* bo wyglądają najczściej,
* bo są standardem w stylach commitów,
* nie mylą się z JSON-em `{}`,
* nie mylą się z listami `[]`,
* nie kolidują z markdownem.

### 📌 Podsumowanie

#### Czy używać `' " `` ` dla formatowania sekcji commitów?

##### **❌ NIE.**

Są mniej czytelne, bardziej problematyczne i mniej naturalne dla Git/GitHub.

##### **✔ Jeśli chcesz coś wyróżniać — najlepszy wybór to:**

1. `[]` — tagi (T, FE, BE, FIX itp.)
2. `()` — nazwy modułów / kolekcji
3. `{}` — parametry (opcjonalnie; można zostawić, nie przeszkadzają)

### 🎯 Najlepsza rekomendowana wersja Twojego stylu:

```
3271 [T] (test_create): Dodaj brakujące uprawnienie RATE
```
