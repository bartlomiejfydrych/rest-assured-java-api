# 🟣POST – Create a Label

# 📑Contents

- [🌐Endpoint](#endpoint)
- [📄Description](#description)
- [📌Important notes](#important_notes)
- [☑Test coverage](#test_coverage)
- [📦Query parameters / Payload](#query_parameters_payload)
- [📩Response](#response)

---

## 🌐Endpoint <a name="endpoint"></a>

/labels

---

## 📄Description <a name="description"></a>

Create a new Label on a Board.  
Forge and OAuth2 apps cannot access this REST resource.

---

## 📌Important notes <a name="important_notes"></a>

To create a label, we first need to have a **board** created and have its `ID`.

---

## ☑Test coverage <a name="test_coverage"></a>

- 💠idBoard `string` 🔴REQUIRED🔴
  - ✅Positive:
    - **[]** Correct
  - ❌Negative:
    - **[]** Missing
    - **[]** Null
    - **[]** Empty string ("")
    - **[]** Non-existent
    - **[]** Incorrect
- 💠name `string` 🔴REQUIRED🔴
  - ✅Positive:
    - **[]** Special characters and numbers
    - **[]** 1 character
    - **[💥]** 16384 characters -> Can't test it because max URI size is ~2000 characters
  - ❌Negative:
    - **[]** Missing (0 characters)
    - **[]** null
    - **[]** Empty string ("")
    - **[💥]** 16385 characters -> Can't test it because max URI size is ~2000 characters
- 💠color `Color` 🔴REQUIRED🔴
  - ✅Positive:
    - **[]** Null
    - **[]** yellow
    - **[]** purple
    - **[]** blue
    - **[]** red
    - **[]** green
    - **[]** orange
    - **[]** black
    - **[]** sky
    - **[]** pink
    - **[]** lime
  - ❌Negative:
    - **[]** Missing
    - **[]** Empty string
    - **[]** Incorrect (other value)

---

## 📦Query parameters / Payload <a name="query_parameters_payload"></a>

### 💠idBoard `string` 🔴REQUIRED🔴

The `ID` of the Board to create the Label on.

### 💠name `string` 🔴REQUIRED🔴

Name for the label.

### 💠color `Color` 🔴REQUIRED🔴

The color for the label.  
Nullable: `true`  
Valid values: `yellow`, `purple`, `blue`, `red`, `green`, `orange`, `black`, `sky`, `pink`, `lime`

---

## 📩Response <a name="response"></a>
