# 🔵PUT – Update a field on a label

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

/labels/{id}/{field}

---

## 📄Description <a name="description"></a>

Update a field on a label.  
Forge and OAuth2 apps cannot access this REST resource.

---

## 📌Important notes <a name="important_notes"></a>

If any tests seem incomplete, outdated, or different from what is in the Trello documentation,
it may mean that the developers may have changed something over time and it's different from what I wrote the tests for.

To update field on a label, we first need to have a **board** created and have its `ID`.  
Next, we need to create for that board a label that fields we will edit.

---

## ☑Test coverage <a name="test_coverage"></a>

- 💠name `string`
  - ✅Positive:
    - **[P1]** Special characters and numbers
    - **[P2]** 1 character
    - **[P3]** Empty string ("")
    - **[💥]** null -> Flaky test. Sometimes the fields become empty/null, sometimes they are not changed at all.
    - **[💥]** 16384 characters -> Can't test it because max URI size is ~2000 characters
  - ❌Negative:
    - **[N1]** Missing (0 characters)
    - **[💥]** 16385 characters -> Can't test it because max URI size is ~2000 characters
- 💠color `Color`
  - ✅Positive:
    - **[rP4]** yellow
    - **[rP4]** purple
    - **[rP4]** blue
    - **[rP4]** red
    - **[rP4]** green
    - **[rP4]** orange
    - **[rP4]** black
    - **[rP4]** sky
    - **[rP4]** pink
    - **[rP4]** lime
    - **[P5]** null
    - **[P6]** Empty string ("")
    - **[P7->🐞]** Missing (0 characters) -> If we don't provide a value, it changes to 'null', and it probably shouldn't be changed.
  - ❌Negative:
    - **[N2]** Incorrect (other value)

---

## 🔗Path parameters <a name="path_parameters"></a>

### 💠id `string` 🔴REQUIRED🔴

The id of the label.

### 💠field `string` 🔴REQUIRED🔴

The field on the Label to update.  
Valid values: `color`, `name`

---

## 📦Query parameters / Payload <a name="query_parameters_payload"></a>

### 💠value `TrelloID (string)` 🔴REQUIRED🔴

The new value for the field.  
Pattern: `^[0-9a-fA-F]{24}$`

---

## 📩Response <a name="response"></a>

```json
{
    "id": "6903d407eb7881f9f767c36e",
    "idBoard": "6903d4065c794519e0f67cc7",
    "name": "Mayer, MacGyver and Muller label 21982431641000",
    "color": null,
    "uses": 0
}
```
