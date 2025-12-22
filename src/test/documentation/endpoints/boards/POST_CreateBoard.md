# 🟣POST – Create a Board

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

/boards

## 📗Description <a name="description"></a>

Creates a new board.

## 📌Important notes <a name="important_notes"></a>

- Changing the `"name"` updates the resulting `"url"`
- `"background"` automatically modifies the HEX color value for:
  - `backgroundColor`
  - `backgroundBottomColor`
  - `backgroundTopColor`

---

# ☑Test coverage <a name="test_coverage"></a>

## 🧵Query parameters <a name="query_parameters"></a>

### 💠name `string` 🔴REQUIRED🔴

#### 📄Description

The new name for the board. 1 to 16384 characters long.

#### 📋Summary

| Property   | Value                   |
|------------|-------------------------|
| Required   | ✔                       |
| Min length | 1                       |
| Max length | 16384 (practical ~2000) |

#### ✅Positive

- BASIC
    - **[ P1 ]** Special characters and numbers (`"Board_123-!"`)
    - **[ P2 ]** Exactly 1 character
    - **[ P ]** Leading/Trailing spaces (`" text "`)
    - **[ P ]** Unicode characters (PL diacritics, emoji, CJK)
- MUST HAVE
    - **[ P ]** URL-unsafe characters (encoded: `%2F%3F%23`)
    - **[ P ]** Mixed plain text + encoded characters (`test%2Fname`)
    - **[ P ]** Percent sign as literal (`100%`)
    - **[ P ]** Newline characters (`\n`, `\r\n`)
    - **[ P ]** Tab characters (`\ttext\t`)
    - **[ P ]** HTML-looking text (escaped, not executed: `<button>Test</button>`)
    - **[ 💥 ]** 16384 characters → Can't test it because max URI size is ~2000 characters

#### ❌Negative

- BASIC
    - **[ N1 ]** Missing
    - **[ N2 ]** null
    - **[ N3 ]** Empty string (`""`)
    - **[ N ]** Only control characters (`\n\t\r`)
    - **[ N ]** Only whitespace characters (` `)
- MUST HAVE
    - **[ N ]** Invalid UTF-8 (`\x80`, `\xED\xA0\x80`)
    - **[ N ]** Mixed valid + invalid UTF-8
    - **[ N ]** Zero-width characters only (`\u200B`)
    - **[ N ]** Broken URL encoding (`%2`, `%GG`, `%`)
    - **[ N ]** Double-encoded input (`%252F`, `%253C`)
    - **[ N ]** HTML / JS injection payload (`<script>alert(1)</script>`)
    - **[ N ]** SQL-like payload (`' OR 1=1 --`)
    - **[ N ]** Non-normalized Unicode (`é` vs `e + ́`)
    - **[ N ]** Wrong type: number
    - **[ N ]** Wrong type: boolean
- NICE TO HAVE
    - **[ N ]** Wrong type: JSON object (`{"name":"Board"}`)
    - **[ N ]** Wrong type: Array (`["name"]`)
    - **[ 💥 ]** 16385 characters → Can't test it because max URI size is ~2000 characters

### 💠defaultLabels `boolean`

#### 📄Description

Determines whether to use the default set of labels.

#### 📋Summary

| Property | Value |
|----------|-------|
| Required | ❌     |
| Default  | true  |

#### ✅Positive

- BASIC
    - **[ P1 ]** Missing (will there be a default value of `true`) → Not in response at all
    - **[ P2 ]** true
    - **[ P3 ]** false
    - **[ P4 ]** null
- MUST HAVE
    - **[ P ]** TRUE
    - **[ P ]** FALSE
    - **[ P ]** Value with surrounding whitespace (`" true "`) if trimmed

#### ❌Negative

- BASIC
    - **[ N ]** "true"
    - **[ N ]** "false"
    - **[ N ]** Empty string (`""`)
    - **[ N ]** Object
    - **[ N ]** Array
- MUST HAVE
    - **[ N ]** 0
    - **[ N ]** 1
    - **[ N ]** -1
    - **[ N ]** Floating point (`0.0`)
    - **[ N ]** Floating point (`1.0`)
    - **[ N ]** Mixed casing (`"True"`) if not normalized
    - **[ N ]** Mixed casing (`"False"`) if not normalized
    - **[ N ]** Boolean embedded in string (`"value=true"`)
    - **[ N ]** Multiple values (`param=true&param=false`)
- NICE TO HAVE
    - **[ N ]** "yes"
    - **[ N ]** "no"
    - **[ N ]** NaN
    - **[ N ]** Infinity
    - **[ N ]** Broken URL encoding (`%`, `%GG`)
    - **[ N ]** Double URL-encoded value (`%2574%2572%2575%2565`)

### 💠defaultLists `boolean`

#### 📄Description

Determines whether to add the default set of lists to a board (To Do, Doing, Done).  
It is ignored if idBoardSource is provided.

#### 📋Summary

| Property | Value |
|----------|-------|
| Required | ❌     |
| Default  | true  |

#### ✅Positive

- BASIC
    - **[ P1 ]** Missing (will there be a default value of `true`) -> Not in response at all
    - **[ P2 ]** true
    - **[ P3 ]** false
    - **[ P4 ]** null
- MUST HAVE
    - **[ P ]** TRUE (uppercase, if normalized)
    - **[ P ]** FALSE (uppercase, if normalized)
    - **[ P ]** Value with surrounding whitespace (" true ") if trimmed
    - **[ ⏭ ]** Parameter is ignored when `idBoardSource` is provided

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
    - **[ N ]** Multiple values (`defaultLists=true&defaultLists=false`)
- NICE TO HAVE
    - **[ N ]** `"yes"`
    - **[ N ]** `"no"`
    - **[ N ]** `NaN`
    - **[ N ]** `Infinity`
    - **[ N ]** Broken URL encoding (`%`, `%GG`)
    - **[ N ]** Double URL-encoded value (`%2574%2572%2575%2565`)

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
    - **[ P1 ]** Missing → default value `""` (or not present in response)
    - **[ P ]** Empty string (`""`)
    - **[ P2 ]** Special characters and numbers (`"Desc_123-!"`)
    - **[ P ]** Leading / trailing spaces (`" text "`)
- MUST HAVE
    - **[ P4 ]** `null` (treated as empty / not set)
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

### 💠idOrganization `TrelloID`

*(Example: `"idOrganization": "67d9d5e34d7b900257deed0e"`)*

#### 📄Description

The id or name of the Workspace the board should belong to.

#### 📋Summary

| Property | Value               |
|----------|---------------------|
| Required | ❌                   |
| Pattern  | `^[0-9a-fA-F]{24}$` |

#### ✅Positive

- BASIC
    - **[ P1 ]** Missing → default Workspace ID
    - **[ P2 ]** Valid ID (lowercase hex, 24 chars)
    - **[ P3 ]** `null` (treated as missing / default)
- MUST HAVE
    - **[ P ]** Valid ID (uppercase hex, 24 chars)
    - **[ P ]** Valid ID with mixed casing
    - **[ P ]** ID pointing to Workspace owned by the user
    - **[ P ]** ID pointing to Workspace where user is a member
- NICE TO HAVE
    - **[ P ]** Valid ID with leading/trailing whitespace (`" 67d9d5e34d7b900257deed0e "`) if trimmed

#### ❌Negative

- BASIC
    - **[ N ]** Empty string (`""`)
    - **[ N5 (poprawić) ]** Too short (less than 24 chars)
    - **[ N ]** Too long (more than 24 chars)
    - **[ N ]** Contains non-hex characters (`g`, `z`, `!`)
- MUST HAVE
    - **[ N4 ]** Non-existent but valid-format ID
    - **[ N ]** Valid-format ID without access rights → `403 Forbidden`
    - **[ N ]** Numeric-only value (`123456789012345678901234`)
    - **[ N ]** Broken hex pattern (`67d9d5e34d7b900257deed0x`)
    - **[ N ]** Uppercase non-hex characters (`G–Z`)
- NICE TO HAVE
    - **[ N ]** ID with mixed control characters (`"67d9d5e34d7b900257deed0e\r\n"`)
    - **[ N ]** URL-unsafe characters in the middle (`"67d9d5e34d7b90/0257deed0e"`)
    - **[ N ]** Double-encoded value (`%2536%2537...`)
    - **[ N ]** Wrong type: number (`123`)
    - **[ N ]** Wrong type: boolean (`true`)
    - **[ N ]** Wrong type: JSON object (`{"id":"67d9d5e34d7b900257deed0e"}`)
    - **[ N ]** Wrong type: Array (`["67d9d5e34d7b900257deed0e"]`)

### 💠idBoardSource `TrelloID`

*(Example: `"id": "68063bdc4bdbd152d658851a"`)*

#### 📄Description

The id of a board to copy into the new board.

#### 📋Summary

| Property | Value               |
|----------|---------------------|
| Pattern  | `^[0-9a-fA-F]{24}$` |
| Required | ❌                   |

#### ✅Positive

- BASIC
    - **[ P1 ]** Missing → board created without copying
    - **[ P2 ]** `null` → treated as missing
    - **[ ⏭ (SPRAWDZIĆ) ]** Valid ID (lowercase hex, 24 chars)
- MUST HAVE
    - **[ P ]** Valid ID (uppercase hex, 24 chars)
    - **[ P ]** Parameters ignored/overridden when copying (`defaultLists`, `defaultLabels` ignored)
- NICE TO HAVE
    - **[ P ]** Valid ID with leading/trailing whitespace if trimmed (`" 68063bdc4bdbd152d658851a "`)

#### ❌Negative

- BASIC
    - **[ N ]** Empty string (`""`)
    - **[ N7 (poprawić) ]** Too short (less than 24 chars)
    - **[ N ]** Too long (more than 24 chars)
    - **[ N ]** Contains non-hex characters (`g`, `z`, `!`)
- MUST HAVE
    - **[ N6 ]** Non-existent but valid-format ID
    - **[ N ]** Valid-format ID without access rights → `403 Forbidden`
    - **[ N ]** Numeric-only value (`123456789012345678901234`)
    - **[ N ]** Broken hex pattern (`68063bdc4bdbd152d658851x`)
    - **[ N ]** ID with newline characters (`"68063bdc4bdbd152d658851a\n"`)
    - **[ N ]** ID with control characters (`"68063bdc4bdbd152d658851a\t"`)
    - **[ N ]** URL-unsafe characters encoded (`"68063bdc4bdbd152d658851a/"`)
    - **[ N ]** URL-unsafe characters raw (`"68063bdc4bdbd152d658851a%2F"`)
    - **[ N ]** Multiple values (`idBoardSource=68063bdc4bdbd152d658851a&idBoardSource=abcdefabcdefabcdefabcdef`)
- NICE TO HAVE
    - **[ N ]** Double-encoded value (`%2536%2538%2530%2536%2533%2562%2564%2563%2534%2562%2564%2562%2564%2531%2535%2532%2564%2536%2535%2538%2538%2535%2531%2561`)
    - **[ N ]** Unicode characters inside ID (`"68063bdc4bdbd152d65885🅰️1a"`)
    - **[ N ]** Wrong type: number (`123`)
    - **[ N ]** Wrong type: boolean (`true`)
    - **[ N ]** Wrong type: JSON object (`{"id":"68063bdc4bdbd152d658851a"}`)
    - **[ N ]** Wrong type: Array (`["68063bdc4bdbd152d658851a"]`)

### 💠keepFromSource `string`

#### 📄Description

To keep cards from the original board pass in the value cards.

#### 📋Summary

| Property     | Value       |
|--------------|-------------|
| Valid values | none, cards |
| Default      | none        |

#### ✅Positive

- BASIC
    - **[ P1 ]** Missing (default value `none`) → Param not present in response
    - **[ P2 ]** `none`
    - **[ P3 ]** `cards`
    - **[ P4 ]** `null` → treated as missing / default
- MUST HAVE
    - **[ P ]** Value with leading/trailing whitespace (`" cards "`) if trimmed
    - **[ P ]** `cards` + valid `idBoardSource` → cards are copied
    - **[ P ]** `none` + valid `idBoardSource` → cards are NOT copied
- NICE TO HAVE
    - **[ P ]** URL-encoded enum value (`cards` encoded as `cards`)
    - **[ P ]** Repeated valid value (`cards,cards`) if ignored safely

#### ❌Negative

- BASIC
    - **[ N ]** Empty string (`""`)
    - **[ N ]** Unknown string (`"labels"`) → It was ignored and board was created
- MUST HAVE
    - **[ N ]** Uppercase value (`"NONE"`) if not normalized
    - **[ N ]** Uppercase value (`"CARDS"`) if not normalized
    - **[ N ]** Value with internal whitespace (`"car ds"`)
    - **[ N ]** Multiple values (`keepFromSource=cards&keepFromSource=none`)
    - **[ N ]** Enum value without `idBoardSource` (ignored or error — must be defined)
- NICE TO HAVE
    - **[ N ]** Numeric value (`1`)
    - **[ N ]** Boolean value (`true`)
    - **[ N ]** JSON object (`{"keep":"cards"}`)
    - **[ N ]** Array (`["cards"]`)
    - **[ N ]** Broken URL encoding (`%`, `%GG`)
    - **[ N ]** Double-encoded value (`%2563%2561%2572%2564%2573`)

### 💠powerUps `string`

#### 📄Description

The Power-Ups that should be enabled on the new board. One of: all, calendar, cardAging, recap, voting.

#### 📋Summary

| Property     | Value                                   |
|--------------|-----------------------------------------|
| Valid values | all, calendar, cardAging, recap, voting |

#### ✅Positive

- BASIC
    - **[ P1 ]** Missing (no Power-Ups enabled) -> Param not present in response
    - **[ P2 ]** `all`
    - **[ P3 ]** `calendar`
    - **[ P5r ]** `cardAging`
    - **[ P5r ]** `recap`
    - **[ P5r ]** `voting`
    - **[ P4 ]** `null` → treated as missing
- MUST HAVE
    - **[ P ]** Value with leading/trailing whitespace (`" calendar "`) if trimmed
- NICE TO HAVE
    - **[ P ]** URL-encoded enum value (`calendar` encoded)
    - **[ P ]** Repeated same value (`powerUps=calendar&powerUps=calendar`) if ignored safely

#### ❌Negative

- BASIC
    - **[ N ]** Empty string (`""`)
    - **[ N ]** Combined list (`"calendar,recap"`)
    - **[ N ]** Unknown string (`"labels"`)
- MUST HAVE
    - **[ N ]** Uppercase value (`"ALL"`) if not normalized
    - **[ N ]** Uppercase value (`"CALENDAR"`) if not normalized
    - **[ N ]** Uppercase value (`"CARDAGING"`) if not normalized
    - **[ N ]** Uppercase value (`"RECAP"`) if not normalized
    - **[ N ]** Uppercase value (`"VOTING"`) if not normalized
    - **[ N ]** Internal whitespace (`"card Aging"`)
    - **[ N ]** Multiple values (`powerUps=calendar&powerUps=recap`)
- NICE TO HAVE
    - **[ N ]** Numeric value (`1`)
    - **[ N ]** Boolean value (`true`)
    - **[ N ]** JSON array (`["calendar"]`)
    - **[ N ]** JSON object (`{"powerUps":"calendar"}`)
    - **[ N ]** Broken URL encoding (`%`, `%GG`)
    - **[ N ]** Double-encoded value (`%2563%2561%256C%2565%256E%2564%2561%2572`)

### 💠prefs_permissionLevel `string`

#### 📄Description

The permissions level of the board. One of: org, private, public.

#### 📋Summary

| Property     | Value                |
|--------------|----------------------|
| Valid values | org, private, public |
| Default      | private              |

#### ✅Positive

- BASIC
    - **[ P1 ]** Missing (default value `private`) → Param not present in response
    - **[ P2 ]** `private`
    - **[ P3 ]** `org`
    - **[ P5 ]** `public`
    - **[ P4 ]** `null` → treated as missing / default
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
    - **[ N8 ]** Invalid value (`"team"`)
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

### 💠prefs_voting `string`

#### 📄Description

Who can vote on this board. One of disabled, members, observers, org, public.

#### 📋Summary

| Property     | Value                                     |
|--------------|-------------------------------------------|
| Valid values | disabled, members, observers, org, public |
| Default      | disabled                                  |

#### ✅Positive

- BASIC
    - **[ P1 ]** Missing (default value `disabled`) → Param not present in response
    - **[ P2 ]** `disabled`
    - **[ P3 ]** `members`
    - **[ P5r ]** `observers`
    - **[ P5r ]** `org`
    - **[ P5r ]** `public`
    - **[ P4 ]** `null` → treated as missing / default
- MUST HAVE
    - **[ P ]** `disabled` → voting disabled for everyone
    - **[ P ]** `members` → only board members can vote
    - **[ P ]** `observers` → members + observers can vote
    - **[ P ]** `org` → Workspace members can vote
    - **[ P ]** `public` → anyone can vote
    - **[ P ]** Value with leading/trailing whitespace (`" members "`) if trimmed
- NICE TO HAVE
    - **[ P ]** URL-encoded enum value (`members` encoded)
    - **[ P ]** Repeated same value (`prefs_voting=members&prefs_voting=members`) if ignored safely

#### ❌Negative

- BASIC
    - **[ N ]** Empty string (`""`)
    - **[ N9 ]** Invalid value (`"admins"`)
- MUST HAVE
    - **[ N ]** Uppercase value (`"DISABLED"`) if not normalized
    - **[ N ]** Uppercase value (`"MEMBERS"`) if not normalized
    - **[ N ]** Uppercase value (`"OBSERVERS"`) if not normalized
    - **[ N ]** Uppercase value (`"ORG"`) if not normalized
    - **[ N ]** Uppercase value (`"PUBLIC"`) if not normalized
    - **[ N ]** Internal whitespace (`"mem bers"`)
    - **[ N ]** Multiple values (`prefs_voting=members&prefs_voting=public`)
- NICE TO HAVE
    - **[ N ]** Numeric value (`1`)
    - **[ N ]** Boolean value (`true`)
    - **[ N ]** JSON object (`{"voting":"members"}`)
    - **[ N ]** Array (`["members"]`)
    - **[ N ]** Broken URL encoding (`%`, `%GG`)
    - **[ N ]** Double-encoded value (`%256D%2565%256D%2562%2565%2572%2573`)

### 💠prefs_comments `string`

#### 📄Description

Who can comment on cards on this board. One of: disabled, members, observers, org, public.

#### 📋Summary

| Property     | Value                                     |
|--------------|-------------------------------------------|
| Valid values | disabled, members, observers, org, public |
| Default      | members                                   |

#### ✅Positive

- BASIC
    - **[ P1 ]** Missing (default value `members`) → Param not present in response
    - **[ P2 ]** `members`
    - **[ P3 ]** `observers`
    - **[ P5r ]** `disabled`
    - **[ P5r ]** `org`
    - **[ P5r ]** `public`
    - **[ P4 ]** `null` → treated as missing / default
- MUST HAVE
    - **[ P ]** `members` → only board members can comment
    - **[ P ]** `observers` → members + observers can comment
    - **[ P ]** `org` → Workspace members can comment
    - **[ P ]** `public` → anyone can comment
    - **[ P ]** `disabled` → comments disabled for everyone
    - **[ P ]** Value with leading/trailing whitespace (`" observers "`) if trimmed
- NICE TO HAVE
    - **[ P ]** URL-encoded enum value (`members` encoded)
    - **[ P ]** Repeated same value (`prefs_comments=members&prefs_comments=members`) if ignored safely

#### ❌Negative

- BASIC
    - **[ N ]** Empty string (`""`)
    - **[ N10 ]** Invalid value (`"admins"`)
    - **[ N ]** Combined values (`"members,observers"`)
- MUST HAVE
    - **[ N ]** Uppercase value (`"DISABLED"`) if not normalized
    - **[ N ]** Uppercase value (`"MEMBERS"`) if not normalized
    - **[ N ]** Uppercase value (`"OBSERVERS"`) if not normalized
    - **[ N ]** Uppercase value (`"ORG"`) if not normalized
    - **[ N ]** Uppercase value (`"PUBLIC"`) if not normalized
    - **[ N ]** Mixed casing (`"Members"`) if not normalized
    - **[ N ]** Internal whitespace (`"mem bers"`)
    - **[ N ]** Multiple values (`prefs_comments=members&prefs_comments=public`)
- NICE TO HAVE
    - **[ N ]** Numeric value (`1`)
    - **[ N ]** Boolean value (`true`)
    - **[ N ]** JSON object (`{"comments":"members"}`)
    - **[ N ]** Array (`["members"]`)
    - **[ N ]** Broken URL encoding (`%`, `%GG`)
    - **[ N ]** Double-encoded value (`%256D%2565%256D%2562%2565%2572%2573`)

### 💠prefs_invitations `string`

#### 📄Description

Determines what types of members can invite users to join. One of: admins, members.

#### 📋Summary

| Property     | Value           |
|--------------|-----------------|
| Valid values | members, admins |
| Default      | members         |

#### ✅Positive

- BASIC
    - **[ P1 ]** Missing (default value `members`) → Param not present in response
    - **[ P2 ]** `members`
    - **[ P3 ]** `admins`
    - **[ P4 ]** `null` → treated as missing / default
- MUST HAVE
    - **[ P ]** `members` → all board members can invite users
    - **[ P ]** `admins` → only board admins can invite users
    - **[ P ]** Value with leading/trailing whitespace (`" admins "`) if trimmed
- NICE TO HAVE
    - **[ P ]** URL-encoded enum value (`members` encoded)
    - **[ P ]** Repeated same value (`prefs_invitations=members&prefs_invitations=members`) if ignored safely

#### ❌Negative

- BASIC
    - **[ N ]** Empty string (`""`)
    - **[ N ]** Invalid value (`"owners"`)
- MUST HAVE
    - **[ N ]** Uppercase value (`"MEMBERS"`) if not normalized
    - **[ N ]** Uppercase value (`"ADMINS"`) if not normalized
    - **[ N ]** Internal whitespace (`"ad mins"`)
    - **[ N ]** Multiple values (`prefs_invitations=members&prefs_invitations=admins`)
- NICE TO HAVE
    - **[ N ]** Numeric value (`1`)
    - **[ N ]** Boolean value (`true`)
    - **[ N ]** JSON object (`{"invite":"admins"}`)
    - **[ N ]** Array (`["members"]`)
    - **[ N ]** Broken URL encoding (`%`, `%GG`)
    - **[ N ]** Double-encoded value (`%256D%2565%256D%2562%2565%2572%2573`)

### 💠prefs_selfJoin `boolean`

#### 📄Description

Determines whether users can join the boards themselves or whether they have to be invited.

#### 📋Summary

| Property | Value |
|----------|-------|
| Default  | true  |

#### ✅Positive

- BASIC
    - **[ P1 ]** Missing (default value `true`) → Param not present in response
    - **[ P2 ]** `true`
    - **[ P3 ]** `false`
    - **[ P4 ]** `null` → treated as missing / default
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

### 💠prefs_cardCovers `boolean`

#### 📄Description

Determines whether card covers are enabled.

#### 📋Summary

| Property | Value |
|----------|-------|
| Default  | true  |

#### ✅Positive

- BASIC
    - **[ P1 ]** Missing (default value `true`) → Param not present in response
    - **[ P2 ]** `true`
    - **[ P3 ]** `false`
    - **[ P4 ]** `null` → treated as missing / default
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

### 💠prefs_background `string`

#### 📄Description

The id of a custom background or one of: blue, orange, green, red, purple, pink, lime, sky, grey.

#### 📋Summary

| Property     | Value                                                   |
|--------------|---------------------------------------------------------|
| Valid values | blue, orange, green, red, purple, pink, lime, sky, grey |
| Default      | blue                                                    |
| Also accepts | custom background ID                                    |

#### ✅Positive

- BASIC
    - **[ P1 ]** Missing (default value `blue`) → Not present in `prefs`
    - **[ P2 ]** `blue`
    - **[ P3 ]** `orange`
    - **[ P5r ]** `green`
    - **[ P5r ]** `red`
    - **[ P5r ]** `purple`
    - **[ P5r ]** `pink`
    - **[ P5r ]** `lime`
    - **[ P5r ]** `sky`
    - **[ P5r ]** `grey`
    - **[ P4 ]** `null` → treated as missing / default
- MUST HAVE
    - **[ P ]** Valid custom background ID (owned by user) (`"5abbe4b7ddc1b351ef961414"`)
    - **[ P ]** Valid custom background ID (user has access)
    - **[ P ]** Value with leading/trailing whitespace (`" grey "`) if trimmed
- NICE TO HAVE
    - **[ P ]** URL-encoded enum value (`sky` encoded)
    - **[ P ]** URL-encoded custom background ID
    - **[ P ]** Repeated same value (`prefs_background=blue&prefs_background=blue`) if ignored safely

#### ❌Negative

- BASIC
    - **[ N ]** Empty string (`""`)
    - **[ N ]** Invalid value (`"black"`)
- MUST HAVE
    - **[ N ]** Uppercase value (`"BLUE"`) if not normalized
    - **[ N ]** Uppercase value (`"ORANGE"`) if not normalized
    - **[ N ]** Uppercase value (`"GREEN"`) if not normalized
    - **[ N ]** Uppercase value (`"RED"`) if not normalized
    - **[ N ]** Uppercase value (`"PURPLE"`) if not normalized
    - **[ N ]** Uppercase value (`"PINK"`) if not normalized
    - **[ N ]** Uppercase value (`"LIME"`) if not normalized
    - **[ N ]** Uppercase value (`"SKY"`) if not normalized
    - **[ N ]** Uppercase value (`"GREY"`) if not normalized
    - **[ N ]** Internal whitespace (`"gr ey"`)
    - **[ N ]** Multiple values (`prefs_background=blue&prefs_background=red`)
- NICE TO HAVE
    - **[ N ]** Numeric value (`1`)
    - **[ N ]** Boolean value (`true`)
    - **[ N ]** JSON object (`{"background":"blue"}`)
    - **[ N ]** Array (`["blue"]`)
    - **[ N ]** Broken URL encoding (`%`, `%GG`)
    - **[ N ]** Double-encoded value (`%2562%256C%2575%2565`)
    - **[ N ]** Valid-format background ID without access rights
    - **[ N ]** Non-existent custom background ID

### 💠prefs_cardAging `string`

#### 📄Description

Determines the type of card aging that should take place on the board if card aging is enabled. One of: pirate, regular.

#### 📋Summary

| Property     | Value           |
|--------------|-----------------|
| Valid values | regular, pirate |
| Default      | regular         |

#### ✅Positive

- BASIC
    - **[ P1 ]** Missing (default value `regular`) → Param not present in response
    - **[ P2 ]** `regular`
    - **[ P3 ]** `pirate`
    - **[ P4 ]** `null` → treated as missing / default
- MUST HAVE
    - **[ P ]** Value with leading/trailing whitespace (`" pirate "`) if trimmed
    - **[ P ]** Parameter is ignored if `powerUps=cardAging` is not enabled
- NICE TO HAVE
    - **[ P ]** URL-encoded enum value (`pirate` encoded)
    - **[ P ]** Repeated same value (`prefs_cardAging=regular&prefs_cardAging=regular`) if ignored safely

#### ❌Negative

- BASIC
    - **[ N ]** Empty string (`""`)
    - **[ N12 ]** Invalid value (`"modern"`)
- MUST HAVE
    - **[ N ]** Uppercase value (`"REGULAR"`) if not normalized
    - **[ N ]** Uppercase value (`"PIRATE"`) if not normalized
    - **[ N ]** Internal whitespace (`"pi rate"`)
    - **[ N ]** Multiple values (`prefs_cardAging=regular&prefs_cardAging=pirate`)
- NICE TO HAVE
    - **[ N ]** Numeric value (`1`)
    - **[ N ]** Boolean value (`true`)
    - **[ N ]** JSON object (`{"aging":"pirate"}`)
    - **[ N ]** Array (`["regular"]`)
    - **[ N ]** Broken URL encoding (`%`, `%GG`)
    - **[ N ]** Double-encoded value (`%2570%2569%2572%2561%2574%2565`)

### 💠Other tests

#### ✅Positive

- **[ P1 ]** Providing only required parameters (`name`)
- **[ P2 ]** Providing all or most parameters at once

# 📜Response <a name="response"></a>

```json
{
  "id": "68063bdc4bdbd152d658851a",
  "name": "Hegmann, West and Rice board com.github.javafaker.Number@1320e68a",
  "desc": "",
  "descData": null,
  "closed": false,
  "idOrganization": "67d9d5e34d7b900257deed0e",
  "idEnterprise": null,
  "pinned": false,
  "url": "https://trello.com/b/kubTebpv/hegmann-west-and-rice-board-comgithubjavafakernumber1320e68a",
  "shortUrl": "https://trello.com/b/kubTebpv",
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
  },
  "limits": {

  }
}
```
