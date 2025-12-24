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

### 💠closed `boolean`

#### 📄Description

Whether the board is closed.

#### 📋Summary

| Property | Value                                             |
|----------|---------------------------------------------------|
| Required | ❌                                                 |
| Default  | false                                             |
| Effect   | `true` → archive board <br>`false` → reopen board |

#### ✅Positive

- BASIC
    - **[ P3 ]** Missing → no change to board state
    - **[ P1 ]** `true`
    - **[ P4 ]** `false`
    - **[ P5 ]** `null` → treated as missing (no change)
- MUST HAVE
    - **[ P ]** Idempotent update (`closed=true` on already closed board)
    - **[ P ]** TRUE (uppercase, if normalized)
    - **[ P ]** FALSE (uppercase, if normalized)
    - **[ P ]** URL-encoded boolean value (`true` → `%74%72%75%65`)
    - **[ P ]** URL-encoded boolean value (`false` → `%66%61%6C%73%65`)
- NICE TO HAVE
    - **[ P ]** Toggle closed state multiple times (`true → false → true`)
    - **[ P ]** Partial update request with only `closed` field

#### ❌Negative

- BASIC
    - **[ N ]** `"true"`
    - **[ N ]** `"false"`
    - **[ N ]** Empty string (`""`)
    - **[ N ]** Object (`{"closed":true}`)
    - **[ N ]** Array (`[true]`)
- MUST HAVE
    - **[ N ]** Numeric (`0`)
    - **[ N ]** Numeric (`1`)
    - **[ N ]** Negative number (`-1`)
    - **[ N ]** Floating point (`0.0`)
    - **[ N ]** Floating point (`1.0`)
    - **[ N ]** Boolean embedded in string (`"value=true"`)
    - **[ N ]** Multiple values (`closed=true&closed=false`)
- NICE TO HAVE
    - **[ N ]** `"yes"`
    - **[ N ]** `"no"`
    - **[ N ]** `NaN`
    - **[ N ]** `Infinity`
    - **[ N ]** Broken URL encoding (`%`, `%GG`)
    - **[ N ]** Double URL-encoded value (`%2574%2572%2575%2565`)

### 💠subscribed `TrelloID`

#### 📄Description

Whether the acting user is subscribed to the board.  
Style: `form`  
Pattern: `^[0-9a-fA-F]{24}$`

#### 📋Summary

| Property | Value                               |
|----------|-------------------------------------|
| Required | ❌                                   |
| Pattern  | `^[0-9a-fA-F]{24}$`                 |
| Effect   | Subscribe / unsubscribe acting user |

#### ✅Positive

- BASIC
    - **[ P3 ]** Missing → subscription state unchanged
    - **[ P2 ]** `null` → treated as missing (no change)
    - **[ 💥 ]** Valid TrelloID (`"68063bdc4bdbd152d658851a"`) → I was unable to determine the correct TrelloID
- MUST HAVE
    - **[ P ]** Valid ID of acting user → user becomes subscribed to the board
    - **[ P ]** Valid ID already subscribed → idempotent (no duplicate subscription)
    - **[ P ]** Mixed-case hex ID (`"68063BdC4BdBd152D658851A"`)
    - **[ P ]** URL-encoded valid ID (`"68063bdc4bdbd152d658851a"` → encoded in URL)
- NICE TO HAVE
    - **[ P ]** Partial update request with only `subscribed` field
    - **[ P ]** Subscribe → unsubscribe → subscribe flow (state changes are consistent)

#### ❌Negative

- BASIC
    - **[ N ]** Empty string (`""`)
    - **[ N ]** Too short ID (`"68063bdc4bdbd152d65885"`)
    - **[ N ]** Too long ID (`"68063bdc4bdbd152d658851aff"`)
- MUST HAVE
    - **[ N3 ]** Non-hex characters (`"zz063bdc4bdbd152d658851a"`)
    - **[ N ]** Numeric-only value (`"123456789012345678901234"`)
    - **[ N2 ]** Valid format but non-existent ID (`"ffffffffffffffffffffffff"`)
    - **[ N ]** Valid ID but user has no access to board → 403 Forbidden
    - **[ N ]** Multiple values (`subscribed=id1&subscribed=id2`)
- NICE TO HAVE
    - **[ N ]** ID with newline character (`"68063bdc4bdbd152d658851a\n"`)
    - **[ N ]** ID with control character (`"68063bdc4bdbd152d658851a\t"`)
    - **[ N ]** URL-unsafe character raw (`"68063bdc4bdbd152d658851a/"`)
    - **[ N ]** URL-unsafe character encoded (`"68063bdc4bdbd152d658851a%2F"`)
    - **[ N ]** Double URL-encoded value (`"%2536%2538%2530%2536%2533%2562%2564%2563..."`)
    - **[ N ]** Unicode characters inside ID (`"68063bdc4bdbd152d65885ą"`)

### 💠idOrganization `string`

#### 📄Description

The id of the Workspace the board should be moved to.

#### 📋Summary

| Property | Value                            |
|----------|----------------------------------|
| Required | ❌                                |
| Pattern  | `^[0-9a-fA-F]{24}$`              |
| Effect   | Moves board to another Workspace |

#### ✅Positive

- BASIC
    - **[ P3 ]** Missing → board remains in current Workspace
    - **[ P2 ]** `null` → treated as missing (no change)
    - **[ P1 ]** Valid Workspace ID (`"67d9d5e34d7b900257deed0e"`)
- MUST HAVE
    - **[ P ]** Move board to another Workspace user belongs to → `idOrganization` updated
    - **[ P ]** Move board to the same Workspace → idempotent (no change)
    - **[ P ]** Mixed-case hex ID (`"67D9d5E34D7B900257DeEd0E"`)
    - **[ P ]** URL-encoded valid ID (`"67d9d5e34d7b900257deed0e"` → encoded in URL)
    - **[ P ]** Move closed (archived) board to another Workspace
- NICE TO HAVE
    - **[ P ]** Multiple moves in sequence (`orgA → orgB → orgA`)
    - **[ P ]** Partial update request with only `idOrganization` field

#### ❌Negative

- BASIC
    - **[ N ]** Empty string (`""`)
    - **[ N3 ]** Too short ID (`"67d9d5e34d7b900257deed"`)
    - **[ N ]** Too long ID (`"67d9d5e34d7b900257deed0eff"`)
- MUST HAVE
    - **[ N ]** Workspace name instead of ID (`"my-workspace-name"`)
    - **[ N ]** Non-hex characters (`"zzd9d5e34d7b900257deed0e"`)
    - **[ N ]** Numeric-only value (`"123456789012345678901234"`)
    - **[ N4 ]** Valid format but non-existent Workspace ID (`"ffffffffffffffffffffffff"`)
    - **[ N ]** Valid ID but user has no permission to move board there → 403 Forbidden
    - **[ N ]** Workspace does not allow board creation / move → 400 / 403 depending on API behavior
    - **[ N ]** Multiple values (`idOrganization=org1&idOrganization=org2`)
    - **[ N ]** Attempt to move closed board → expected error
- NICE TO HAVE
    - **[ N ]** ID with newline character (`"67d9d5e34d7b900257deed0e\n"`)
    - **[ N ]** ID with control character (`"67d9d5e34d7b900257deed0e\t"`)
    - **[ N ]** URL-unsafe character raw (`"67d9d5e34d7b900257deed0e/"`)
    - **[ N ]** URL-unsafe character encoded (`"67d9d5e34d7b900257deed0e%2F"`)
    - **[ N ]** Double URL-encoded value (`"%2536%2537%2564%2539%2564%2535..."`)
    - **[ N ]** Unicode characters inside ID (`"67d9d5e34d7b900257deedą"`)

### 💠prefs/permissionLevel `string`

#### 📄Description

The permissions level of the board. One of: org, private, public.

#### 📋Summary

| Property     | Value                |
|--------------|----------------------|
| Valid values | org, private, public |
| Default      | private              |

#### ✅Positive

- BASIC
  - **[ P3 ]** Missing (default value `private`) → Param not present in response
  - **[ P4 ]** `private`
  - **[ P1 ]** `org`
  - **[ P5 ]** `public`
  - **[ P2 ]** `null` → treated as missing / default
- MUST HAVE
  - **[ P ]** `private` → board visible only to members
  - **[ P ]** `org` → board visible to Workspace members only
  - **[ P ]** `public` → board publicly accessible
  - **[ P ]** Value with leading/trailing whitespace (`" public "`) if trimmed
- NICE TO HAVE
  - **[ P ]** URL-encoded enum value (`public` encoded)
  - **[ P ]** Repeated same value (`prefs_permissionLevel=private&prefs_permissionLevel=private`) if ignored safely

#### ❌Negative

- BASIC
  - **[ N ]** Empty string (`""`)
  - **[ N6 ]** Invalid value (`"team"`)
- MUST HAVE
  - **[ N ]** Uppercase value (`"PRIVATE"`) if not normalized
  - **[ N ]** Uppercase value (`"ORG"`) if not normalized
  - **[ N ]** Uppercase value (`"PUBLIC"`) if not normalized
  - **[ N ]** Internal whitespace (`"pub lic"`)
  - **[ N ]** Multiple values (`prefs_permissionLevel=private&prefs_permissionLevel=public`)
- NICE TO HAVE
  - **[ N ]** Numeric value (`1`)
  - **[ N ]** Boolean value (`true`)
  - **[ N ]** JSON object (`{"permission":"public"}`)
  - **[ N ]** Array (`["public"]`)
  - **[ N ]** Broken URL encoding (`%`, `%GG`)
  - **[ N ]** Double-encoded value (`%2570%2575%2562%256C%2569%2563`)

### 💠prefs/selfJoin `boolean`

#### 📄Description

Determines whether users can join the boards themselves or whether they have to be invited.

#### 📋Summary

| Property | Value |
|----------|-------|
| Default  | true  |

#### ✅Positive

- BASIC
  - **[ P3 ]** Missing (default value `true`) → Param not present in response
  - **[ P1 ]** `true`
  - **[ P4 ]** `false`
  - **[ P2 ]** `null` → treated as missing / default
- MUST HAVE
  - **[ P ]** `true` → users can self-join the board
  - **[ P ]** `false` → users must be invited
  - **[ P ]** Value with surrounding whitespace (`" true "`) if trimmed
  - **[ P ]** Uppercase boolean (`TRUE`) if normalized
  - **[ P ]** Uppercase boolean (`FALSE`) if normalized
- NICE TO HAVE
  - **[ P ]** URL-encoded boolean value (`prefs_selfJoin=true`)
  - **[ P ]** URL-encoded boolean value (`prefs_selfJoin=false`)
  - **[ P ]** Repeated same value (`prefs_selfJoin=true&prefs_selfJoin=true`) if ignored safely

#### ❌Negative

- BASIC
  - **[ N ]** `"true"`
  - **[ N ]** `"false"`
  - **[ N ]** Empty string (`""`)
  - **[ N ]** Wrong type: Object (`{}`)
  - **[ N ]** Wrong type: Array (`[]`)
- MUST HAVE
  - **[ N ]** `0`
  - **[ N ]** `1`
  - **[ N ]** `-1`
  - **[ N ]** Floating point (`0.0`)
  - **[ N ]** Floating point (`1.0`)
  - **[ N ]** Boolean embedded in string (`"value=true"`)
  - **[ N ]** Multiple values (`prefs_selfJoin=true&prefs_selfJoin=false`)
- NICE TO HAVE
  - **[ N ]** `"yes"`
  - **[ N ]** `"no"`
  - **[ N ]** `NaN`
  - **[ N ]** `Infinity`
  - **[ N ]** Broken URL encoding (`%`, `%GG`)
  - **[ N ]** Double URL-encoded value (`%2574%2572%2575%2565`)

### 💠prefs/cardCovers `boolean`

#### 📄Description

Whether card covers should be displayed on this board.

#### 📋Summary

| Property | Value |
|----------|-------|
| Default  | true  |

#### ✅Positive

- BASIC
  - **[ P3 ]** Missing (default value `true`) → Param not present in response
  - **[ P1 ]** `true`
  - **[ P4 ]** `false`
  - **[ P2 ]** `null` → treated as missing / default
- MUST HAVE
  - **[ P ]** `true` → card covers enabled
  - **[ P ]** `false` → card covers disabled
  - **[ P ]** Uppercase boolean (`TRUE`) if normalized
  - **[ P ]** Uppercase boolean (`FALSE`) if normalized
  - **[ P ]** Value with surrounding whitespace (`" true "`) if trimmed
- NICE TO HAVE
  - **[ P ]** URL-encoded boolean value (`prefs_cardCovers=true`)
  - **[ P ]** URL-encoded boolean value (`prefs_cardCovers=false`)
  - **[ P ]** Repeated same value (`prefs_cardCovers=true&prefs_cardCovers=true`) if ignored safely

#### ❌Negative

- BASIC
  - **[ N ]** `"true"`
  - **[ N ]** `"false"`
  - **[ N ]** Empty string (`""`)
  - **[ N ]** Wrong type: Object (`{}`)
  - **[ N ]** Wrong type: Array (`[]`)
- MUST HAVE
  - **[ N ]** `0`
  - **[ N ]** `1`
  - **[ N ]** `-1`
  - **[ N ]** Floating point (`0.0`)
  - **[ N ]** Floating point (`1.0`)
  - **[ N ]** Mixed casing (`"True"`) if not normalized
  - **[ N ]** Mixed casing (`"False"`) if not normalized
  - **[ N ]** Boolean embedded in string (`"value=true"`)
  - **[ N ]** Multiple values (`prefs_cardCovers=true&prefs_cardCovers=false`)
- NICE TO HAVE
  - **[ N ]** `"yes"`
  - **[ N ]** `"no"`
  - **[ N ]** `NaN`
  - **[ N ]** `Infinity`
  - **[ N ]** Broken URL encoding (`%`, `%GG`)
  - **[ N ]** Double URL-encoded value (`%2574%2572%2575%2565`)

### 💠prefs/hideVotes `boolean`

#### 📄Description

Determines whether the Voting Power-Up should hide who voted on cards.

#### 📋Summary

| Property   | Value                                                         |
|------------|---------------------------------------------------------------|
| Required   | ❌                                                             |
| Default    | false                                                         |
| Effect     | `true` → votes are anonymous <br>`false` → voters are visible |
| Depends on | `powerUps=voting`                                             |

#### ✅Positive

- BASIC
    - **[ P3 ]** Missing → no change to current setting
    - **[ P1 ]** `true`
    - **[ P4 ]** `false`
    - **[ P2 ]** `null` → treated as missing (no change)
- MUST HAVE
    - **[ P ]** Enable vote hiding (`true`) when Voting Power-Up is enabled
    - **[ P ]** Disable vote hiding (`false`) when Voting Power-Up is enabled
    - **[ P ]** Idempotent update (`true` → `true`, `false` → `false`)
    - **[ P ]** TRUE (uppercase, if normalized)
    - **[ P ]** FALSE (uppercase, if normalized)
    - **[ P ]** URL-encoded boolean value (`true` → `%74%72%75%65`)
    - **[ P ]** URL-encoded boolean value (`false` → `%66%61%6C%73%65`)
- NICE TO HAVE
    - **[ P ]** Toggle value multiple times (`true → false → true`)
    - **[ P ]** Partial update request with only `prefs/hideVotes` field
    - **[ P ]** Parameter ignored when Voting Power-Up is not enabled

#### ❌Negative

- BASIC
    - **[ N ]** `"true"`
    - **[ N ]** `"false"`
    - **[ N ]** Empty string (`""`)
    - **[ N ]** Object (`{"hideVotes":true}`)
    - **[ N ]** Array (`[true]`)
- MUST HAVE
    - **[ N ]** Numeric (`0`)
    - **[ N ]** Numeric (`1`)
    - **[ N ]** Negative number (`-1`)
    - **[ N ]** Floating point (`0.0`)
    - **[ N ]** Floating point (`1.0`)
    - **[ N ]** Boolean embedded in string (`"value=true"`)
    - **[ N ]** Multiple values (`prefs/hideVotes=true&prefs/hideVotes=false`)
- NICE TO HAVE
    - **[ N ]** `"yes"`
    - **[ N ]** `"no"`
    - **[ N ]** `NaN`
    - **[ N ]** `Infinity`
    - **[ N ]** Broken URL encoding (`%`, `%GG`)
    - **[ N ]** Double URL-encoded value (`%2574%2572%2575%2565`)

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
