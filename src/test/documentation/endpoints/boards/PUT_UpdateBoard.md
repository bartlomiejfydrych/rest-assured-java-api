# 🔵PUT – Update a Board

# 📑Contents

- [📔Basic information](#basic_information)
    - [🌐Endpoint](#endpoint)
    - [📗Description](#description)
    - [📌Important notes](#important_notes)
- [☑Test coverage](#test_coverage)
    - [🔗Path parameters](#path_parameters)
    - [🧵Query parameters](#query_parameters)
    - [🎫Payload JSON](#payload_json)
- [📜Response](#response)

---

# 📔Basic information <a name="basic_information"></a>

## 🌐Endpoint <a name="endpoint"></a>

/boards/{id}

## 📗Description <a name="description"></a>

Update an existing board by id.  
Forge and OAuth2 apps cannot access this REST resource.

## 📌Important notes <a name="important_notes"></a>

- Changing the `"name"` updates the resulting `"url"`
- `"background"` automatically modifies the HEX color value for:
    - `backgroundColor`
    - `backgroundBottomColor`
    - `backgroundTopColor`

---

# ☑Test coverage <a name="test_coverage"></a>

## 🔗Path parameters <a name="path_parameters"></a>

### 💠Path parameter: `id` (`TrelloID`) 🔴REQUIRED🔴

*(Example: `/boards/68063bdc4bdbd152d658851a`)*

#### 📄Description

The ID of the board to be updated.

#### 📋Summary

| Property | Value               |
|----------|---------------------|
| Required | ✔                   |
| Pattern  | `^[0-9a-fA-F]{24}$` |
| Type     | path parameter      |

#### ✅Positive

- BASIC
    - **[ P ]** Valid board ID (`/boards/68063bdc4bdbd152d658851a`)
    - **[ P ]** Uppercase hex characters (`/boards/68063BDC4BDBD152D658851A`)
- NICE TO HAVE
    - **[ P ]** Repeated request with same ID (idempotency check)

#### ❌Negative

- BASIC
    - **[ N ]** Missing ID in path (`PUT /boards/`)
    - **[ N ]** Empty ID segment (`PUT /boards//`)
    - **[ N ]** Empty string (`PUT /boards/""`)
- MUST HAVE
    - **[ N ]** Too short ID (`/boards/68063bdc4bdbd152d65885`)
    - **[ N ]** Too long ID (`/boards/68063bdc4bdbd152d658851aff`)
    - **[ N ]** Non-hex characters (`/boards/zz063bdc4bdbd152d658851a`)
    - **[ N ]** Numeric-only (`/boards/123456789012345678901234`)
    - **[ N ]** Non-existent but valid-format ID (`/boards/ffffffffffffffffffffffff`)
    - **[ N ]** Valid ID but user has no access → 403 Forbidden
    - **[ N ]** Valid ID but board belongs to another workspace without permissions
- NICE TO HAVE
    - **[ N ]** ID with newline characters (`/boards/68063bdc4bdbd152d658851a\n`)
    - **[ N ]** ID with control characters (`/boards/68063bdc4bdbd152d658851a\t`)
    - **[ N ]** URL-unsafe character raw (`/boards/68063bdc4bdbd152d658851a/`)
    - **[ N ]** URL-unsafe character encoded (`/boards/68063bdc4bdbd152d658851a%2F`)
    - **[ N ]** Double-encoded ID (`/boards/%2536%2538%2530%2536%2533%2562%2564%2563...`)
    - **[ N ]** Unicode characters inside ID (`/boards/68063bdc4bdbd152d65885ą`)
    - **[ N ]** SQL-like payload (`/boards/68063bdc4bdbd152d658851a' OR 1=1`)
    - **[ N ]** Path traversal attempt (`/boards/../68063bdc4bdbd152d658851a`)

## 🧵Query parameters <a name="query_parameters"></a>

### 💠name `string`

#### 📄Description

The new name for the board. 1 to 16384 characters long.  
Updating name changes `url` and `shortUrl`.

#### 📋Summary

| Property    | Value                     |
|-------------|---------------------------|
| Required    | ❌                         |
| Min length  | 1 (if provided)           |
| Max length  | 16384 (practical ~2000)   |
| Side effect | Updates `url`, `shortUrl` |

#### ✅Positive

- BASIC
    - **[ P3 ]** Missing → name remains unchanged
    - **[ P2 ]** `null` → treated as missing (no update)
    - **[ P4 ]** Exactly 1 character
    - **[ P1 ]** Special characters and numbers (`"Board_123-!"`)
    - **[ P ]** Unicode characters (PL diacritics, emoji, CJK)
    - **[ P ]** Leading / trailing spaces (`" text "`)
- MUST HAVE
    - **[ P ]** Update name to a new valid value → `name`, `url`, `shortUrl` are updated
    - **[ P ]** Update name to the same value → no unintended side effects
    - **[ P ]** Two boards can have the same name
    - **[ P ]** URL-unsafe characters (encoded: `%2F%3F%23`)
    - **[ P ]** Percent sign as literal (`100%`)
    - **[ P ]** Newline characters (`\n`, `\r\n`)
    - **[ P ]** Tab characters (`\ttext\t`)
    - **[ P ]** HTML-looking text (escaped, not executed: `<button>Test</button>`)
    - **[ 💥 ]** 16384 characters → Can't test due to max URI size (~2000 chars)
- NICE TO HAVE
    - **[ P ]** Partial update request with only `name` field
    - **[ P ]** Multiple updates in sequence → `url` and `shortUrl` update consistently

#### ❌Negative

- BASIC
    - **[ N1 ]** Empty string (`""`)
    - **[ N ]** Only whitespace characters (`"   "`)
    - **[ N ]** Only control characters (`\n\t\r`)
- MUST HAVE
    - **[ N ]** Invalid UTF-8 (`\x80`, `\xED\xA0\x80`)
    - **[ N ]** Mixed valid + invalid UTF-8
    - **[ N ]** Zero-width characters only (`\u200B`)
    - **[ N ]** Broken URL encoding (`%2`, `%GG`, `%`)
    - **[ N ]** Double-encoded input (`%252F`, `%253C`)
    - **[ N ]** HTML / JS injection payload (`<script>alert(1)</script>`)
    - **[ N ]** SQL-like payload (`' OR 1=1 --`)
    - **[ N ]** Non-normalized Unicode `é` vs `e + ́`)
    - **[ N ]** Wrong type: number (`123`)
    - **[ N ]** Wrong type: boolean (`true`)
- NICE TO HAVE
    - **[ N ]** Wrong type: JSON object (`{"name":"Board"}`)
    - **[ N ]** Wrong type: Array (`["name"]`)
    - **[ N ]** Multiple `name` params (`?name=Board1&name=Board2`)
    - **[ 💥 ]** 16385 characters → Can't test due to max URI size (~2000 chars)

### 💠desc `string`

#### 📄Description

A new description for the board, 0 to 16384 characters long.

#### 📋Summary

| Property | Value                   |
|----------|-------------------------|
| Min      | 0                       |
| Max      | 16384 (practical ~2000) |
| Required | ❌                       |

#### ✅Positive

- BASIC
    - **[ P3 ]** Missing → default value `""` (or not present in response)
    - **[ P ]** Empty string (`""`)
    - **[ P1 ]** Special characters and numbers (`"Desc_123-!"`)
    - **[ P ]** Leading / trailing spaces (`" text "`)
- MUST HAVE
    - **[ P2 ]** `null` (treated as empty / not set)
    - **[ P ]** Unicode characters (PL diacritics, emoji, CJK)
    - **[ P ]** URL-unsafe characters (encoded: `%2F%3F%23`)
    - **[ P ]** Percent sign as literal (`100%`)
    - **[ P ]** Newline characters (`\n`, `\r\n`)
    - **[ P ]** Tab characters (`\tdescription\t`)
    - **[ P ]** HTML-looking text (escaped, not executed: `<b>bold</b>`)
    - **[ 💥 ]** 16384 characters → Can't test it because max URI size is ~2000 characters

#### ❌Negative

- BASIC
    - **[ N ]** Only whitespace characters (`"   "`)
    - **[ N ]** Only control characters (`\n\t\r`)
- MUST HAVE
    - **[ N ]** Invalid UTF-8 (`\x80`, `\xED\xA0\x80`)
    - **[ N ]** Zero-width characters only (`\u200B`)
    - **[ N ]** Broken URL encoding (`%2`, `%GG`, `%`)
    - **[ N ]** HTML / JS injection payload (`<script>alert(1)</script>`)
    - **[ N ]** SQL-like payload (`' OR 1=1 --`)
    - **[ N ]** Non-normalized Unicode (`é` vs `e + ́`)
    - **[ N ]** Wrong type: number (`123`)
    - **[ N ]** Wrong type: boolean (`true`)
    - **[ 💥 ]** 16385 characters → Can't test it because max URI size is ~2000 characters
- NICE TO HAVE
    - **[ N ]** Wrong type: JSON object (`{"desc":"text"}`)
    - **[ N ]** Wrong type: Array (`["desc"]`)

DOKOŃCZYĆ

---

# 📜Response <a name="response"></a>

```json
{
  "id": "68740e988eabe783637e2545",
  "name": "Jenkins Inc borad 14019989791900",
  "desc": "",
  "descData": null,
  "closed": false,
  "idOrganization": "67d9d5e34d7b900257deed0e",
  "idEnterprise": null,
  "pinned": false,
  "url": "https://trello.com/b/bNscpbn9/jenkins-inc-borad-14019989791900",
  "shortUrl": "https://trello.com/b/bNscpbn9",
  "prefs": {
    "permissionLevel": "private",
    "hideVotes": false,
    "voting": "disabled",
    "comments": "members",
    "invitations": "members",
    "selfJoin": true,
    "cardCovers": true,
    "showCompleteStatus": true,
    "cardCounts": false,
    "isTemplate": false,
    "cardAging": "regular",
    "calendarFeedEnabled": false,
    "hiddenPluginBoardButtons": [

    ],
    "switcherViews": [
      {
        "viewType": "Board",
        "enabled": true
      },
      {
        "viewType": "Table",
        "enabled": true
      },
      {
        "viewType": "Calendar",
        "enabled": false
      },
      {
        "viewType": "Dashboard",
        "enabled": false
      },
      {
        "viewType": "Timeline",
        "enabled": false
      },
      {
        "viewType": "Map",
        "enabled": false
      }
    ],
    "autoArchive": null,
    "background": "blue",
    "backgroundColor": "#0079BF",
    "backgroundDarkColor": null,
    "backgroundImage": null,
    "backgroundDarkImage": null,
    "backgroundImageScaled": null,
    "backgroundTile": false,
    "backgroundBrightness": "dark",
    "sharedSourceUrl": null,
    "backgroundBottomColor": "#0079BF",
    "backgroundTopColor": "#0079BF",
    "canBePublic": true,
    "canBeEnterprise": true,
    "canBeOrg": true,
    "canBePrivate": true,
    "canInvite": true
  },
  "labelNames": {
    "green": "",
    "yellow": "",
    "orange": "",
    "red": "",
    "purple": "",
    "blue": "",
    "sky": "",
    "lime": "",
    "pink": "",
    "black": "",
    "green_dark": "",
    "yellow_dark": "",
    "orange_dark": "",
    "red_dark": "",
    "purple_dark": "",
    "blue_dark": "",
    "sky_dark": "",
    "lime_dark": "",
    "pink_dark": "",
    "black_dark": "",
    "green_light": "",
    "yellow_light": "",
    "orange_light": "",
    "red_light": "",
    "purple_light": "",
    "blue_light": "",
    "sky_light": "",
    "lime_light": "",
    "pink_light": "",
    "black_light": ""
  }
}
```
