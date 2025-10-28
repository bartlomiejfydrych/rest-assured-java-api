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

To update field on a label, we first need to have a **board** created and have its `ID`.  
Next, we need to create for that board a label that fields we will edit.

---

## ☑Test coverage <a name="test_coverage"></a>

- 💠name `string`
  - ✅Positive:
    - **[P1]** Special characters and numbers
    - **[P2]** 1 character
    - **[P3]** Missing (0 characters)
    - **[P4]** null
    - **[💥]** Empty string ("") -> Flaky test. Sometimes the fields become empty/null, sometimes they are not changed at all.
    - **[💥]** 16384 characters -> Can't test it because max URI size is ~2000 characters
  - ❌Negative:
    - **[💥]** 16385 characters -> Can't test it because max URI size is ~2000 characters
- 💠color `Color`
  - ✅Positive:
    - **[rP1|rP2]** yellow
    - **[rP1|rP2]** purple
    - **[rP1|rP2]** blue
    - **[rP1|rP2]** red
    - **[rP1|rP2]** green
    - **[rP1|rP2]** orange
    - **[rP1|rP2]** black
    - **[rP1|rP2]** sky
    - **[rP1|rP2]** pink
    - **[rP1|rP2]** lime
    - **[P3]** Missing (0 characters)
    - **[P4]** Null
    - **[💥]** Empty string ("") -> Flaky test. Sometimes the fields become empty/null, sometimes they are not changed at all.
  - ❌Negative:
    - **[N1]** Incorrect (other value)

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
