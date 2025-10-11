# 🔵PUT – Update a Label

# 📑Contents

- [🌐Endpoint](#endpoint)
- [📄Description](#description)
- [📌Important notes](#important_notes)
- [☑Test coverage](#test_coverage)
- [📦Query parameters / Payload](#query_parameters_payload)
- [📩Response](#response)

---

## 🌐Endpoint <a name="endpoint"></a>

/labels/{id}

---

## 📄Description <a name="description"></a>

Update a label by ID.  
Forge and OAuth2 apps cannot access this REST resource.

---

## 📌Important notes <a name="important_notes"></a>

To update a label, we first need to have a **board** created and have its `ID`.  
Next, we need to create for that board a label that we will edit.

---

## ☑Test coverage <a name="test_coverage"></a>

- 💠name `string`
  - ✅Positive:
    - **[P1]** Special characters and numbers
    - **[P2]** 1 character
    - **[]** Missing (0 characters)
    - **[]** null
    - **[]** Empty string ("")
    - **[💥]** 16384 characters -> Can't test it because max URI size is ~2000 characters
  - ❌Negative:
    - **[💥]** 16385 characters -> Can't test it because max URI size is ~2000 characters
- 💠color `Color`
  - ✅Positive:
    - **[rP1]** yellow
    - **[rP1]** purple
    - **[rP1]** blue
    - **[rP1]** red
    - **[rP1]** green
    - **[rP1]** orange
    - **[rP1]** black
    - **[rP1]** sky
    - **[rP1]** pink
    - **[rP1]** lime
    - **[P2]** Null
    - **[]** Missing (0 characters)
    - **[]** Empty string ("")
  - ❌Negative:
    - **[]** Incorrect (other value)

---

## 📦Query parameters / Payload <a name="query_parameters_payload"></a>

### 💠name `string`

The new name for the label.

### 💠color `Color`

The new color for the label.  
Nullable: `true`  
Valid values: `yellow`, `purple`, `blue`, `red`, `green`, `orange`, `black`, `sky`, `pink`, `lime`

---

## 📩Response <a name="response"></a>

```json

```
