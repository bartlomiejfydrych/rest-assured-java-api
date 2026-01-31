# 🟣POST – Create a new List

# 📑Contents

- [🌐Endpoint](#endpoint)
- [📄Description](#description)
- [📌Important notes](#important_notes)
- [☑Test coverage](#test_coverage)
- [🔗Path parameters](#path_parameters)
- [📦Query parameters / Payload](#query_parameters_payload)
- [📩Response](#response)

---

## 🌐Endpoint <a name="endpoint"></a>

/lists

---

## 📄Description <a name="description"></a>

Create a new List on a Board.  
Forge and OAuth2 apps cannot access this REST resource.

---

## 📌Important notes <a name="important_notes"></a>

If any tests seem incomplete, outdated, or different from what is in the Trello documentation,
it may mean that the developers may have changed something over time and it's different from what I wrote the tests for.

---

## ☑Test coverage <a name="test_coverage"></a>

- 💠name `string` 🔴REQUIRED🔴
  - ✅Positive:
    - **[P1]** Special characters and numbers
    - **[P2]** 1 character
    - **[💥]** 16384 characters -> Can't test it because max URI size is ~2000 characters
  - ❌Negative:
    - **[N1]** Missing (0 characters)
    - **[N2]** null
    - **[N3]** Empty string ("")
    - **[💥]** 16385 characters -> Can't test it because max URI size is ~2000 characters
- 💠idBoard `TrelloID (string)` 🔴REQUIRED🔴
  - ✅Positive:
    - **[P1]** Correct
  - ❌Negative:
    - **[N4]** Missing
    - **[N5]** null
    - **[N6]** Empty string ("")
    - **[N7]** Non-existent
    - **[N8]** Incorrect
- 💠idListSource `TrelloID`
  - ✅Positive:
    - **[P3]** Correct
    - **[P1]** Missing
    - **[P2]** null
    - **[P5]** Empty string ("")
  - ❌Negative:
    - **[N9]** Non-existent
    - **[N10]** Incorrect
- 💠pos `oneOf [number, string]`
  - ✅Positive:
    - **[P4]** top
    - **[P4]** bottom
    - **[P4]** number
    - **[P1|P3]** Missing
    - **[P2]** null
    - **[P5]** Empty string ("")
    - **[P6->🟢]** Number as string -> According to the documentation, the specific position of list should be of type Number. A String value will also work.
  - ❌Negative:
    - **[N11]** Incorrect

---

## 🔗Path parameters <a name="path_parameters"></a>

None.

---

## 📦Query parameters / Payload <a name="query_parameters_payload"></a>

### 💠name `string` 🔴REQUIRED🔴

Name for the list.

### 💠idBoard `TrelloID (string)` 🔴REQUIRED🔴

The long ID of the board the list should be created on.  
Pattern: `^[0-9a-fA-F]{24}$`

### 💠idListSource `TrelloID`

ID of the List to copy into the new List.  
Pattern: `^[0-9a-fA-F]{24}$`

### 💠pos `oneOf [number, string]`

Position of the list.  
`top`, `bottom`, or a positive floating point `number`.

---

## 📩Response <a name="response"></a>

```json
{
    "id": "690f8836a26231502a0a1bed",
    "name": "Vłh-N <tXR'7dGĘŚP,ŹFŻYńz&*52+ŃZ0W}/CO?(vUATkĆs9E3]\\)[fói;ębqeJ\\j$#up1@x:no8ÓśżĄS\\IćgM_Krą^`~mHcQ=\"|BDź6Ll!Ł4a%.{>wy",
    "closed": false,
    "color": null,
    "idBoard": "690f88356a13c24b8dafaeb1",
    "pos": 140737488322560,
    "type": null,
    "datasource": {
        "filter": false
    },
    "limits": {
        
    }
}
```
