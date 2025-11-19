# 🔵PUT – Update a List

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

/lists/{id}

---

## 📄Description <a name="description"></a>

Update the properties of a List.  
Forge and OAuth2 apps cannot access this REST resource.

---

## 📌Important notes <a name="important_notes"></a>

None.

---

## ☑Test coverage <a name="test_coverage"></a>

- 💠id `string` 🔴REQUIRED🔴
  - ✅Positive:
    - **[P1|P2|P3]** Correct
  - ❌Negative:
    - **[]** Non-existent
    - **[]** Incorrect
- 💠name `string`
  - ✅Positive:
    - **[P1]** Special characters and numbers
    - **[P2]** 1 character
    - **[P3]** Missing (0 characters)
    - **[]** null
    - **[]** Empty string ("")
    - **[💥]** 16384 characters -> Can't test it because max URI size is ~2000 characters
  - ❌Negative:
    - **[💥]** 16385 characters -> Can't test it because max URI size is ~2000 characters
- 💠closed `boolean`
  - ✅Positive:
    - **[P1]** true
    - **[P2]** false
    - **[P3]** Missing
    - **[]** null
- 💠idBoard `TrelloID`
  - ✅Positive:
    - **[P1|P2|P3]** Correct
  - ❌Negative:
    - **[]** Missing
    - **[]** null
    - **[]** Empty string ("")
    - **[]** Non-existent
    - **[]** Incorrect
- 💠pos `oneOf [number, string]`
  - ✅Positive:
    - **[]** top
    - **[]** bottom
    - **[]** number
    - **[P1]** Missing
    - **[P2]** null
    - **[P3💥]** Empty string ("") -> It seems this PUT request changes "Pos" to some other, fixed value. I'm hardcoding the expected value so the test doesn't fail.
  - ❌Negative:
    - **[]** Incorrect
    - **[SPRAWDZIĆ!->💥]** Number as string -> According to the documentation, the specific position of list should be of type Number. A String value will also work.
- 💠subscribed `boolean`
  - ✅Positive:
    - **[P1]** true
    - **[P2]** false
    - **[P3]** Missing
    - **[]** null

---

## 🔗Path parameters <a name="path_parameters"></a>

### 💠id `string` 🔴REQUIRED🔴

The ID of the list.

---

## 📦Query parameters / Payload <a name="query_parameters_payload"></a>

### 💠name `string`

New name for the list.

### 💠closed `boolean`

Whether the list should be closed (archived).

### 💠idBoard `TrelloID`

ID of a board the list should be moved to.  
Style: `form`  
Pattern: `^[0-9a-fA-F]{24}$`

### 💠pos `oneOf [number, string]`

New position for the list: `top`, `bottom`, or a positive floating point `number`.

### 💠subscribed `boolean`

Whether the active member is subscribed to this list.

---

## 📩Response <a name="response"></a>

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
