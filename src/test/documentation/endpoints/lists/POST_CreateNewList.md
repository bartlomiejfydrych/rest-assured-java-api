# 🟣POST – Create a new List

# 📑Contents

- [📔Basic information](#basic_information)
  - [🌐Endpoint](#endpoint)
  - [📗Description](#description)
  - [📌Important notes](#important_notes)
- [☑Test coverage](#test_coverage)
  - [🧵Query parameters](#query_parameters)
- [📜Response](#response)

---

# 📔Basic information <a name="basic_information"></a>

## 🌐Endpoint <a name="endpoint"></a>

/lists

## 📗Description <a name="description"></a>

Create a new List on a Board.  
Forge and OAuth2 apps cannot access this REST resource.

## 📌Important notes <a name="important_notes"></a>

If any tests seem incomplete, outdated, or different from what is in the Trello documentation,
it may mean that the developers may have changed something over time and it's different from what I wrote the tests for.

---

# ☑Test coverage <a name="test_coverage"></a>

## 🧵Query parameters <a name="query_parameters"></a>

### 💠name `string` 🔴REQUIRED🔴

#### 📄Description

Name for the list.

#### 📋Summary

| Property   | Value                           |
|------------|---------------------------------|
| Required   | ✔                               |
| Type       | string                          |
| Max length | 16384 (practical ~2000 via URI) |

#### ✅Positive

- **[ P1 ]** Special characters and numbers
- **[ P2 ]** 1 character
- **[ 💥 ]** 16384 characters → Can't test it because max URI size is ~2000 characters

#### ❌Negative

- **[ N1 ]** Missing (0 characters)
- **[ N3 ]** Empty string ("")
- **[ 💥 ]** 16385 characters → Can't test it because max URI size is ~2000 characters

### 💠idBoard `TrelloID (string)` 🔴REQUIRED🔴

#### 📄Description

The long ID of the board the list should be created on.  
Pattern: `^[0-9a-fA-F]{24}$`

#### 📋Summary

| Property | Value    |
|----------|----------|
| Required | ✔        |
| Type     | TrelloID |
| Pattern  | 24 hex   |

#### ✅Positive

- **[ P1 ]** Correct

#### ❌Negative

- **[ N4 ]** Missing
- **[ N6 ]** Empty string ("")
- **[ N7 ]** Non-existent
- **[ N8 ]** Incorrect

### 💠idListSource `TrelloID`

#### 📄Description

ID of the List to copy into the new List.  
Pattern: `^[0-9a-fA-F]{24}$`

#### 📋Summary

| Property | Value    |
|----------|----------|
| Required | ❌        |
| Type     | TrelloID |

#### ✅Positive

- **[ P3 ]** Correct
- **[ P1 ]** Missing
- **[ P2 ]** null
- **[ P5 ]** Empty string ("")

#### ❌Negative

- **[ N9 ]** Non-existent
- **[ N10 ]** Incorrect

### 💠pos `oneOf [number, string]`

#### 📄Description

Position of the list.  
`top`, `bottom`, or a positive floating point `number`.

#### 📋Summary

| Property | Value           |
|----------|-----------------|
| Required | ❌               |
| Type     | number / string |

#### ✅Positive

- **[ P4 ]** top
- **[ P4 ]** bottom
- **[ P4 ]** number
- **[ P1 | P3 ]** Missing
- **[ P2 ]** null
- **[ P5 ]** Empty string ("")
- **[ P6->🟢 ]** Number as string → According to the documentation, the specific position of list should be of type Number. A String value will also work.

#### ❌Negative

- **[ N11 ]** Incorrect

---

# 📜Response <a name="response"></a>

```json
{
  "limits" : { },
  "id" : "69ff0744a9612e90181aa5ba",
  "name" : "aÓxA@I!fD}W4ł(cXŁźb3^=QęUo:_'`zJE&mhG~9Z]\\/Pw2C#[VBt)HŻ5ĘjŃKĆ-{lśńOrYSv0T\"kN>LŚ18iĄ|q;6ć$yóą,pF*egd. nMużŹ+R?<7s",
  "closed" : false,
  "color" : null,
  "idBoard" : "69ff0740485b3b3223b5f922",
  "pos" : 140737488289792,
  "subscribed" : false,
  "softLimit" : null,
  "type" : null,
  "datasource" : {
    "filter" : false
  }
}
```
