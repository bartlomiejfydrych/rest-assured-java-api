# 🔵PUT – Update a Label

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

/labels/{id}

## 📗Description <a name="description"></a>

Update a label by ID.  
Forge and OAuth2 apps cannot access this REST resource.

## 📌Important notes <a name="important_notes"></a>

If any tests seem incomplete, outdated, or different from what is in the Trello documentation,
it may mean that the developers may have changed something over time and it's different from what I wrote the tests for.

To update a label, we first need to have a **board** created and have its `ID`.  
Next, we need to create for that board a label that we will edit.

---

# ☑Test coverage <a name="test_coverage"></a>

## 🔗Path parameters <a name="path_parameters"></a>

### 💠id `TrelloID` 🔴REQUIRED🔴

#### 📄Description

The ID of the Label.  
Pattern: `^[0-9a-fA-F]{24}$`

#### 📋Summary

| Property | Value               |
|----------|---------------------|
| Required | ✔                   |
| Type     | 24-hex string       |
| Pattern  | `^[0-9a-fA-F]{24}$` |

#### ✅Positive

- **[ PX ]** Correct

#### ❌Negative

🔴TODO🔴
- **[ N ]** Non-existent
- **[ N ]** Incorrect

## 🧵Query parameters <a name="query_parameters"></a>

### 💠name `string`

#### 📄Description

The new name for the label.

#### 📋Summary

| Property   | Value                             |
|------------|-----------------------------------|
| Required   | ❌                                 |
| Type       | string                            |
| Max length | 16384 (practical ~2000 via query) |

#### ✅Positive

- **[ P1 ]** Special characters and numbers
- **[ P2 ]** 1 character
- **[ P3 ]** Missing (0 characters)
- **[ P4 ]** null
- **[ P5💥 ]** Empty string ("") → Flaky test. Sometimes the fields become empty/null, sometimes they are not changed at all.
- **[ 💥 ]** 16384 characters → Can't test it because max URI size is ~2000 characters

#### ❌Negative

- **[ 💥 ]** 16385 characters → Can't test it because max URI size is ~2000 characters

### 💠color `Color`

#### 📄Description

The new color for the label.  
Nullable: `true`  
Valid values: `yellow`, `purple`, `blue`, `red`, `green`, `orange`, `black`, `sky`, `pink`, `lime`

#### 📋Summary

| Property | Value |
|----------|-------|
| Required | ❌     |
| Type     | enum  |
| Nullable | ✔     |

#### ✅Positive

- **[ rP1 | rP2 ]** yellow
- **[ rP1 | rP2 ]** purple
- **[ rP1 | rP2 ]** blue
- **[ rP1 | rP2 ]** red
- **[ rP1 | rP2 ]** green
- **[ rP1 | rP2 ]** orange
- **[ rP1 | rP2 ]** black
- **[ rP1 | rP2 ]** sky
- **[ rP1 | rP2 ]** pink
- **[ rP1 | rP2 ]** lime
- **[ P3 ]** Missing (0 characters)
- **[ P4 ]** Null
- **[ 💥 ]** Empty string ("") → Flaky test. Sometimes the fields become empty/null, sometimes they are not changed at all.

#### ❌Negative

- **[ N1 ]** Incorrect (other value)

---

# 📜Response <a name="response"></a>

```json
{
    "id": "68f3727ceca87e58e1db7f37",
    "idBoard": "68f3727b519a2a57cdf7fec1",
    "name": "QćJsŃY4źwEv<Wf;DĄ:VL )TF?!0OoŁaNĘ68Ic(iC\"hx\\śn+*u5K7ZŻUH1ÓŚ]Ćq^AlbSr{9\\|-',jŹ3ąg$%_>2}dmz@&X~łń#peę=P\\RóB[`Gkżty./M",
    "color": "black",
    "uses": 0
}
```
