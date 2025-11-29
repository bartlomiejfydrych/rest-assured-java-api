# 🟣POST – Create a new Card

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

/cards

---

## 📄Description <a name="description"></a>

Create a new card. Query parameters may also be replaced with a JSON request body instead.  
Forge and OAuth2 apps cannot access this REST resource.

---

## 📌Important notes <a name="important_notes"></a>

None.

---

## ☑Test coverage <a name="test_coverage"></a>

- 💠idList `TrelloID` 🔴REQUIRED🔴
  - ✅Positive:
    - **[]** text
  - ❌Negative:
    - **[]** text
- 💠name `string`
  - ✅Positive:
    - **[]** text
  - ❌Negative:
    - **[]** text
- 💠desc `string`
  - ✅Positive:
    - **[]** text
  - ❌Negative:
    - **[]** text
- 💠pos `oneOf [string, number]`
  - ✅Positive:
    - **[]** text
  - ❌Negative:
    - **[]** text
- 💠due `string`
  - ✅Positive:
    - **[]** text
  - ❌Negative:
    - **[]** text
- 💠start `string`
  - ✅Positive:
    - **[]** text
  - ❌Negative:
    - **[]** text
- 💠dueComplete `boolean`
  - ✅Positive:
    - **[]** text
  - ❌Negative:
    - **[]** text
- 💠idMembers `array<TrelloID>`
  - ✅Positive:
    - **[]** text
  - ❌Negative:
    - **[]** text
- 💠idLabels `array<TrelloID>`
  - ✅Positive:
    - **[]** text
  - ❌Negative:
    - **[]** text
- 💠urlSource `string`
  - ✅Positive:
    - **[]** text
  - ❌Negative:
    - **[]** text
- 💠fileSource `string`
  - ✅Positive:
    - **[]** text
  - ❌Negative:
    - **[]** text
- 💠mimeType `string`
  - ✅Positive:
    - **[]** text
  - ❌Negative:
    - **[]** text
- 💠idCardSource `TrelloID`
  - ✅Positive:
    - **[]** text
  - ❌Negative:
    - **[]** text
- 💠keepFromSource `string`
  - ✅Positive:
    - **[]** text
  - ❌Negative:
    - **[]** text
- 💠address `string`
  - ✅Positive:
    - **[]** text
  - ❌Negative:
    - **[]** text
- 💠locationName `string`
  - ✅Positive:
    - **[]** text
  - ❌Negative:
    - **[]** text
- 💠coordinates `string`
  - ✅Positive:
    - **[]** text
  - ❌Negative:
    - **[]** text
- 💠cardRole `string`
  - ✅Positive:
    - **[]** text
  - ❌Negative:
    - **[]** text

---

## 🔗Path parameters <a name="path_parameters"></a>

None.

---

## 📦Query parameters / Payload <a name="query_parameters_payload"></a>

### 💠idList `TrelloID` 🔴REQUIRED🔴

The ID of the list the card should be created in.  
Pattern: `^[0-9a-fA-F]{24}$`

### 💠name `string`

The name for the card.

### 💠desc `string`

The description for the card.

### 💠pos `oneOf [string, number]`

The position of the new card. `top`, `bottom`, or a positive float.

### 💠due `string`

A due date for the card.  
Format: `date`

### 💠start `string`

The start date of a card, or `null`.  
Nullable: `true`  
Format: `date`

### 💠dueComplete `boolean`

Whether the status of the card is complete.

### 💠idMembers `array<TrelloID>`

Comma-separated list of member IDs to add to the card.

### 💠idLabels `array<TrelloID>`

Comma-separated list of label IDs to add to the card.

### 💠urlSource `string`

A URL starting with `http://` or `https://`. The URL will be attached to the card upon creation.  
Format: `url`

### 💠fileSource `string`

Format: `binary`

### 💠mimeType `string`

The mimeType of the attachment. Max length 256.

### 💠idCardSource `TrelloID`

The ID of a card to copy into the new card.  
Pattern: `^[0-9a-fA-F]{24}$`

### 💠keepFromSource `string`

If using `idCardSource` you can specify which properties to copy over. `all` or comma-separated list of:  
`attachments,checklists,customFields,comments,due,start,labels,members,start,stickers`

Style: `form`  
Default: `all`  
Valid values: `all`, `attachments`, `checklists`, `comments`, `customFields`, `due`, `start`, `labels`, `members`, `start`, `stickers`

### 💠address `string`

For use with/by the Map View.

### 💠locationName `string`

For use with/by the Map View.

### 💠coordinates `string`

For use with/by the Map View. Should take the form latitude, longitude.

### 💠cardRole `string`

For displaying cards in different ways based on the card name. Board cards must have a name that is a link to a Trello
board. Mirror cards must have a name that is a link to a Trello card.

Nullable: `true`  
Valid values: `separator`, `board`, `mirror`, `link`

---

## 📩Response <a name="response"></a>
