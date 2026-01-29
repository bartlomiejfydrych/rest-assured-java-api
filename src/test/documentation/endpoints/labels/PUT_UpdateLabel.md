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

If any tests seem incomplete, outdated, or different from what is in the Trello documentation,
it may mean that the developers may have changed something over time and it's different from what I wrote the tests for.

To update a label, we first need to have a **board** created and have its `ID`.  
Next, we need to create for that board a label that we will edit.

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
{
    "id": "68f3727ceca87e58e1db7f37",
    "idBoard": "68f3727b519a2a57cdf7fec1",
    "name": "QćJsŃY4źwEv<Wf;DĄ:VL )TF?!0OoŁaNĘ68Ic(iC\"hx\\śn+*u5K7ZŻUH1ÓŚ]Ćq^AlbSr{9\\|-',jŹ3ąg$%_>2}dmz@&X~łń#peę=P\\RóB[`Gkżty./M",
    "color": "black",
    "uses": 0
}
```
