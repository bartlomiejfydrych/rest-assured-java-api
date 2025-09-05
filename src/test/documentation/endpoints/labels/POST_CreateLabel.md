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
    - **[P1|P2]** Correct
  - ❌Negative:
    - **[]** Missing
    - **[]** Null
    - **[]** Empty string ("")
    - **[]** Non-existent
    - **[]** Incorrect
- 💠name `string` 🔴REQUIRED🔴
  - ✅Positive:
    - **[P1]** Special characters and numbers
    - **[P2]** 1 character
    - **[💥]** 16384 characters -> Can't test it because max URI size is ~2000 characters
  - ❌Negative:
    - **[]** Missing (0 characters)
    - **[]** null
    - **[]** Empty string ("")
    - **[💥]** 16385 characters -> Can't test it because max URI size is ~2000 characters
- 💠color `Color` 🔴REQUIRED🔴
  - ✅Positive:
    - **[P2]** Null
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

```json
{
    "id": "68b8a290c4f66f01d06926d0",
    "idBoard": "68b8a28f40001d27d813d29e",
    "name": "}NmX\\W=Ma^ŃZżśS#źąGó I/$BjR\\ĆDŚbłV02ńÓ\\9PTAg'i?4sy.Ł*Kr>(Ż%&<QCp6q8oO@U[cFn{t]~YH:LvzĄ`;dJE!wkufćl31Ę,Ź-5)_h+eęx\"7|",
    "color": "sky",
    "uses": 0,
    "limits": {
        
    }
}
```
