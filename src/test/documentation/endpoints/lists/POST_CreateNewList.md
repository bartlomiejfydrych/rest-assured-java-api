# 🟣POST – {endpoint name}

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

None.

---

## ☑Test coverage <a name="test_coverage"></a>

- 💠name `string` 🔴REQUIRED🔴
  - ✅Positive:
    - **[P1]** Special characters and numbers
    - **[]** 1 character
    - **[💥]** 16384 characters -> Can't test it because max URI size is ~2000 characters
  - ❌Negative:
    - **[]** Missing (0 characters)
    - **[]** Null
    - **[]** Empty string ("")
    - **[💥]** 16385 characters -> Can't test it because max URI size is ~2000 characters
- 💠idBoard `TrelloID (string)` 🔴REQUIRED🔴
  - ✅Positive:
    - **[P1]** Correct
  - ❌Negative:
    - **[]** Missing
    - **[]** Null
    - **[]** Empty string ("")
    - **[]** Non-existent
    - **[]** Incorrect
- 💠idListSource `TrelloID`
  - ✅Positive:
    - **[]** Correct
    - **[P1]** Missing
    - **[]** Null
    - **[]** Empty string ("")
  - ❌Negative:
    - **[]** Non-existent
    - **[]** Incorrect
- 💠pos `oneOf [number, string]`
  - ✅Positive:
    - **[]** top
    - **[]** bottom
    - **[]** number
    - **[P1]** Missing
    - **[]** Null
    - **[]** Empty string ("")
  - ❌Negative:
    - **[]** Incorrect

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
`top`, `bottom`, or a positive floating point number

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
