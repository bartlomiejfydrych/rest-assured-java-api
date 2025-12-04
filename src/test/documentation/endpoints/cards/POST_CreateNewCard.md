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
    - **[]** Correct (string)
    - **[]** Correct (integer/number)
  - ❌Negative:
    - **[]** Non-existent
    - **[]** Incorrect
    - **[]** Missing
    - **[]** Null
    - **[]** Empty string ("")
- 💠name `string`
  - ✅Positive:
    - **[]** Special characters and numbers
    - **[]** 1 character
    - **[]** Missing
    - **[]** Null
    - **[💥]** 16384 characters -> Can't test it because max URI size is ~2000 characters
  - ❌Negative:
    - **[]** Empty string ("")
    - **[💥]** 16385 characters -> Can't test it because max URI size is ~2000 characters
- 💠desc `string`
  - ✅Positive:
    - **[]** Special characters and numbers
    - **[]** 1 character
    - **[]** Missing
    - **[]** Null
    - **[💥]** 16384 characters -> Can't test it because max URI size is ~2000 characters
  - ❌Negative:
    - **[]** Empty string ("")
    - **[💥]** 16385 characters -> Can't test it because max URI size is ~2000 characters
- 💠pos `oneOf [string, number]`
  - ✅Positive:
    - **[]** top
    - **[]** bottom
    - **[]** number
    - **[]** Missing
    - **[]** null
    - **[sprawdzić]** Empty string ("")
    - **[sprawdzić]** Number as string -> According to the documentation, the specific position of list should be of type Number. A String value will also work.
  - ❌Negative:
    - **[]** Incorrect
- 💠due `string`
  - ✅Positive:
    - **[]** Correct date in YYYY-MM-DD format (e.g., 2025-12-31)
    - **[]** Current date
    - **[]** Missing
    - **[]** Null
    - **[]** Earliest acceptable date (e.g., 0001-01-01, if the system allows)
    - **[]** Latest acceptable date (e.g., 9999-12-31, if the system allows)
    - **[]** Date with local time ignored by the backend (e.g., 2025-11-30T00:00:00 → the backend should parse only the date if allowed)
    - **[]** Date with valid alternative separators (e.g., 2025/11/30) — if the backend accepts different separators
    - **[]** Date in time zone (2025-11-30Z or 2025-11-30+02:00) — if the backend can dump to the date itself
  - ❌Negative:
    - **[]** Empty string ("")
    - **[]** Incorrect date format (30-11-2025)
    - **[]** Non-existent date (e.g., 2025-02-30)
    - **[]** Number instead of date (e.g., 12345)
    - **[]** Text instead of date ("tomorrow", "abc")
    - **[]** Unsupported datetime format (2025-11-30 10:20:30)
    - **[]** Date with month 0 or >12 (2025-00-10, 2025-13-10)
    - **[]** Date with day 0 or >31 (2025-10-00, 2025-10-32)
    - **[]** Short format (2025-1-1)
    - **[]** Special characters that don't form a date (****, !!!, %20%20%20)
    - **[]** Binary / base64 value
    - **[]** JSON ({"date":"2025-10-10"} as the query parameter value)
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
