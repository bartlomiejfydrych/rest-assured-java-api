# 🔵PUT – Update a List

# 📑Contents

- [📔Basic information](#basic_information)
  - [🌐Endpoint](#endpoint)
  - [📗Description](#description)
  - [📌Important notes](#important_notes)
- [☑Test coverage](#test_coverage)
  - [🔗Path parameters](#path_parameters)
  - [🧵Query parameters](#query_parameters)
- [📜Response](#response)

---

# 📔Basic information <a name="basic_information"></a>

## 🌐Endpoint <a name="endpoint"></a>

/lists/{id}

## 📗Description <a name="description"></a>

Update the properties of a List.  
Forge and OAuth2 apps cannot access this REST resource.

## 📌Important notes <a name="important_notes"></a>

If any tests seem incomplete, outdated, or different from what is in the Trello documentation,
it may mean that the developers may have changed something over time and it's different from what I wrote the tests for.

---

# ☑Test coverage <a name="test_coverage"></a>

## 🔗Path parameters <a name="path_parameters"></a>

### 💠id `string` 🔴REQUIRED🔴

#### 📄Description

The ID of the list.

#### 📋Summary

| Property | Value                         |
|----------|-------------------------------|
| Required | ✔                             |
| Type     | string (TrelloID in practice) |

#### ✅Positive

- **[ PX ]** Correct

#### ❌Negative

- **[ N1 ]** Non-existent
- **[ N2 ]** Incorrect

## 🧵Query parameters <a name="query_parameters"></a>

### 💠name `string`

#### 📄Description

New name for the list.

#### 📋Summary

| Property   | Value                           |
|------------|---------------------------------|
| Required   | ❌                               |
| Type       | string                          |
| Max length | 16384 (practical ~2000 via URI) |

#### ✅Positive

- **[ P1 ]** Special characters and numbers
- **[ P2 ]** 1 character
- **[ P3 ]** Missing (0 characters)
- **[ P4 ]** null
- **[ 💥 ]** 16384 characters → Can't test it because max URI size is ~2000 characters

#### ❌Negative

- **[ N3 ]** Empty string ("")
- **[ 💥 ]** 16385 characters → Can't test it because max URI size is ~2000 characters

### 💠closed `boolean`

#### 📄Description

Whether the list should be closed (archived).

#### 📋Summary

| Property | Value   |
|----------|---------|
| Required | ❌       |
| Type     | boolean |

#### ✅Positive

- **[ P1 ]** true
- **[ P2 ]** false
- **[ P3 ]** Missing
- **[ P4 ]** null

#### ❌Negative

None.

### 💠idBoard `TrelloID`

#### 📄Description

ID of a board the list should be moved to.  
Style: `form`  
Pattern: `^[0-9a-fA-F]{24}$`

#### 📋Summary

| Property | Value    |
|----------|----------|
| Required | ❌        |
| Type     | TrelloID |

#### ✅Positive

- **[ PX ]** Missing
- **[ P6 ]** Correct
- **[ P7 ]** null

#### ❌Negative

- **[ N4 ]** Empty string ("")
- **[ N5 ]** Non-existent
- **[ N6 ]** Incorrect

### 💠pos `oneOf [number, string]`

#### 📄Description

New position for the list: `top`, `bottom`, or a positive floating point `number`.

#### 📋Summary

| Property | Value           |
|----------|-----------------|
| Required | ❌               |
| Type     | number / string |

#### ✅Positive

- **[ P5 ]** top
- **[ P5 ]** bottom
- **[ P5 ]** number
- **[ P1 ]** Missing
- **[ P2 ]** null
- **[ P3💥 ]** Empty string (""):
  - This test detected strange behavior
  - If the first PUT request changes something in the list, but not its "Pos," or if we try to change "Pos" to
    something that shouldn't change it, such as null or an empty String, the initial value of "Pos" still changes
  - Because of this strange behavior:
    - If this test is run individually, it will fail because the value has changed
    - If it is run with all tests, it will pass because the value has already been changed in another test
- **[ P8 ]** Number as string → According to the documentation, the specific position of list should be of type Number. A String value will also work.

#### ❌Negative

- **[ N7 ]** Incorrect

### 💠subscribed `boolean`

#### 📄Description

Whether the active member is subscribed to this list.

#### 📋Summary

| Property | Value   |
|----------|---------|
| Required | ❌       |
| Type     | boolean |

#### ✅Positive

- **[ P1 ]** true
- **[ P2 ]** false
- **[ P3 ]** Missing
- **[ P4 ]** null

#### ❌Negative

None.

---

# 📜Response <a name="response"></a>

```json
{
    "id": "6918c0e928328bf5ad423af0",
    "name": "ZECŚ]bQźŻ.ĘV-żX8N$rPę7ś^R}v3OĄJ&ą#ił\\GYB|()xŁ_%>Ń{dFAyuIagŹńom5=S01U,TzK+k46/sLl<wć?W@~j;óMthDf9eq n'\\:H\"Ć`\\[cpÓ2!*",
    "closed": true,
    "color": null,
    "idBoard": "6918c0e725eebf9505f994b3",
    "pos": 140737488338944,
    "subscribed": true
}
```
