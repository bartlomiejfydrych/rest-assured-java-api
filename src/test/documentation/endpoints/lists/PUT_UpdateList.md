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
    - **[]** Correct
  - ❌Negative:
    - **[]** Non-existent
    - **[]** Incorrect
- 💠name `string`
  - ✅Positive:
    - **[]** Special characters and numbers
    - **[]** 1 character
    - **[]** Missing (0 characters)
    - **[]** null
    - **[]** Empty string ("")
    - **[💥]** 16384 characters -> Can't test it because max URI size is ~2000 characters
  - ❌Negative:
    - **[💥]** 16385 characters -> Can't test it because max URI size is ~2000 characters
- 💠closed `boolean`
  - ✅Positive:
    - **[]** true
    - **[]** false
    - **[]** Missing
    - **[]** null
- 💠idBoard `TrelloID`
  - ✅Positive:
    - **[]** Correct
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
    - **[]** Missing
    - **[]** null
    - **[]** Empty string ("")
  - ❌Negative:
    - **[]** Incorrect
    - **[SPRAWDZIĆ!->💥]** Number as string -> According to the documentation, the specific position of list should be of type Number. A String value will also work.
- 💠subscribed `boolean`
  - ✅Positive:
    - **[]** true
    - **[]** false
    - **[]** Missing
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
