# 🔴DELETE – Delete a Board

# 📑Contents

- [📔Basic information](#basic_information)
    - [🌐Endpoint](#endpoint)
    - [📗Description](#description)
    - [📌Important notes](#important_notes)
- [☑Test coverage](#test_coverage)
- [📜Response](#response)

---

# 📔Basic information <a name="basic_information"></a>

## 🌐Endpoint <a name="endpoint"></a>

/boards/{id}

## 📗Description <a name="description"></a>

Delete a board.  
Forge and OAuth2 apps cannot access this REST resource.

## 📌Important notes <a name="important_notes"></a>

Notes.

---

# ☑Test coverage <a name="test_coverage"></a>

#### ✅Positive

- **[ P1 ]** Correct delete a board

#### ❌Negative

- **[ N1 ]** Board without access
- **[ N2 ]** Non-existent board

---

# 📜Response <a name="response"></a>

```json
{
  "_value": null
}
```
