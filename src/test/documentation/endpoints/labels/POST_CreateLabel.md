# 🟣POST – Create a Label

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

/labels

## 📗Description <a name="description"></a>

Create a new Label on a Board.  
Forge and OAuth2 apps cannot access this REST resource.

## 📌Important notes <a name="important_notes"></a>

If any tests seem incomplete, outdated, or different from what is in the Trello documentation,
it may mean that the developers may have changed something over time and it's different from what I wrote the tests for.

To create a label, we first need to have a **board** created and have its `ID`.

---

# ☑Test coverage <a name="test_coverage"></a>

## 🧵Query parameters <a name="query_parameters"></a>

### 💠idBoard `string` 🔴REQUIRED🔴

#### 📄Description

The `ID` of the Board to create the Label on.

#### 📋Summary

| Property | Value  |
|----------|--------|
| Required | ✔      |
| Type     | string |

#### ✅Positive

- **[ P1 | P2 ]** Correct

#### ❌Negative

- **[ N1 ]** Missing
- **[ N2 ]** Null
- **[ N3 ]** Empty string ("")
- **[ N4 ]** Non-existent
- **[ N5 ]** Incorrect

### 💠name `string` 🔴REQUIRED🔴

#### 📄Description

Name for the label.

#### 📋Summary

| Property   | Value                             |
|------------|-----------------------------------|
| Required   | ✔                                 |
| Type       | string                            |
| Max length | 16384 (practical ~2000 via query) |

#### ✅Positive

- **[ P1 ]** Special characters and numbers
- **[ P2 ]** 1 character
- **[ 💥 ]** 16384 characters → Can't test it because max URI size is ~2000 characters
- **[ P3🐞 ]** null → A label without a name is created, but it probably shouldn't be
- **[ P4🐞 ]** Empty string ("") → A label without a name is created, but it probably shouldn't be

#### ❌Negative

- **[ N6 ]** Missing (0 characters)
- **[ 💥 ]** 16385 characters → Can't test it because max URI size is ~2000 characters

### 💠color `Color` 🔴REQUIRED🔴

#### 📄Description

The color for the label.  
Nullable: `true`  
Valid values: `yellow`, `purple`, `blue`, `red`, `green`, `orange`, `black`, `sky`, `pink`, `lime`

#### 📋Summary

| Property | Value |
|----------|-------|
| Required | ✔     |
| Type     | enum  |
| Nullable | ✔     |

#### ✅Positive

- **[ P2 ]** Null
- **[ rP1 ]** yellow
- **[ rP1 ]** purple
- **[ rP1 ]** blue
- **[ rP1 ]** red
- **[ rP1 ]** green
- **[ rP1 ]** orange
- **[ rP1 ]** black
- **[ rP1 ]** sky
- **[ rP1 ]** pink
- **[ rP1 ]** lime
- **[ P5🐞 ]** Missing → A label without a color is created, but it probably shouldn't be
- **[ P6🐞 ]** Empty string → A label without a color is created, but it probably shouldn't be

#### ❌Negative

- **[ N7 ]** Incorrect (other value)

---

# 📜Response <a name="response"></a>

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
