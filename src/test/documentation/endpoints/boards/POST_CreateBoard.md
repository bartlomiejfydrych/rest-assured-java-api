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

- **[ P1 ]** Special characters and numbers
- **[ P2 ]** 1 character
- **[ P ]** Leading/Trailing spaces (" text ")
- **[ P  ]** URL-unsafe characters (`%2F` | encoded)
- **[ 💥 ]** 16384 characters -> Can't test it because max URI size is ~2000 characters

#### ❌Negative

* **[N1]** Missing
* **[N2]** null
* **[N3]** Empty string `""`
* **[N4]** Only spaces
* **[N5]** Invalid UTF-8
* **[N6]** Wrong type (number, boolean, JSON object)

### 💠defaultLabels `boolean`

#### 📄Description

Determines whether to use the default set of labels.

#### 📋Summary

| Property | Value |
|----------|-------|
| Required | ❌     |
| Default  | true  |

#### ✅Positive

* **[P1]** Missing
* **[P2]** true
* **[P3]** false
* **[P4]** null

#### ❌Negative

* **[N1]** `"true"` / `"false"` strings
* **[N2]** 0 / 1
* **[N3]** `"yes"`, `"no"`
* **[N4]** -1
* **[N5]** Empty string
* **[N6]** Object/array

### 💠defaultLists `boolean`

#### 📄Description

Determines whether to add the default set of lists to a board (To Do, Doing, Done). It is ignored if idBoardSource is provided.

#### 📋Summary

| Property | Value |
|----------|-------|
| Required | ❌     |
| Default  | true  |

#### ✅Positive

* **[P1]** Missing
* **[P2]** true
* **[P3]** false
* **[P4]** null
* **[P5]** Validation when `idBoardSource` is present (ignored)

#### ❌Negative

* **[N1]** `"true"` / `"false"`
* **[N2]** numbers
* **[N3]** text
* **[N4]** empty string
* **[N5]** JSON

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

* **[P1]** Missing → `""`
* **[P2]** Special characters
* **[P3]** Unicode
* **[P4]** null
* **[P5]** Only spaces
* **[P6]** Max-length (~2000 chars)

#### ❌Negative

* **[N1]** Invalid UTF-8
* **[N2]** Raw JSON without encoding
* **[X1]** >2000 chars

### 💠idOrganization `TrelloID`

#### 📄Description

The id or name of the Workspace the board should belong to.

#### 📋Summary

| Property | Value               |
|----------|---------------------|
| Required | ❌                   |
| Pattern  | `^[0-9a-fA-F]{24}$` |

#### ✅Positive

* **[P1]** Missing
* **[P2]** Valid lowercase ID
* **[P3]** null
* **[P4]** Valid uppercase ID

#### ❌Negative

* **[N1]** Non-existent ID
* **[N2]** Non-hex characters
* **[N3]** Too short
* **[N4]** Too long
* **[N5]** Empty string
* **[N6]** Numeric-only but invalid
* **[N7]** No access → 403

### 💠idBoardSource `TrelloID`

#### 📄Description

The id of a board to copy into the new board.

#### 📋Summary

| Property | Value               |
|----------|---------------------|
| Pattern  | `^[0-9a-fA-F]{24}$` |
| Required | ❌                   |

#### ✅Positive

* **[P1]** Missing
* **[P2]** Valid ID
* **[P3]** null
* **[P4]** Uppercase valid

#### ❌Negative

* **[N1]** Non-existent board
* **[N2]** Wrong hex structure
* **[N3]** Too short
* **[N4]** Too long
* **[N5]** Empty string
* **[N6]** No access (403)

### 💠keepFromSource `string`

#### 📄Description

To keep cards from the original board pass in the value cards.

#### 📋Summary

| Property     | Value       |
|--------------|-------------|
| Valid values | none, cards |
| Default      | none        |

#### ✅Positive

* **[P1]** Missing
* **[P2]** none
* **[P3]** cards
* **[P4]** null
* **[P5]** Leading/trailing spaces

#### ❌Negative

* **[N1]** Wrong casing
* **[N2]** Empty string
* **[N3]** Non-string type
* **[N4]** Very long value
* **[N5]** Unknown string (ignored)

### 💠powerUps `string`

#### 📄Description

The Power-Ups that should be enabled on the new board. One of: all, calendar, cardAging, recap, voting.

#### 📋Summary

| Property     | Value                                   |
|--------------|-----------------------------------------|
| Valid values | all, calendar, cardAging, recap, voting |

#### ✅Positive

* **[P1]** Missing
* **[P2]** all
* **[P3]** calendar
* **[P4]** cardAging
* **[P5]** recap
* **[P6]** voting
* **[P7]** Mixed-case valid
* **[P8]** null

#### ❌Negative

* **[N1]** Combined list
* **[N2]** Empty string
* **[N3]** Wrong casing
* **[N4]** Numeric
* **[N5]** JSON array
* **[N6]** Unknown value (ignored)

### 💠prefs_permissionLevel `string`

#### 📄Description

The permissions level of the board. One of: org, private, public.

#### 📋Summary

| Property     | Value                |
|--------------|----------------------|
| Valid values | org, private, public |
| Default      | private              |

#### ✅Positive

* **[P1]** Missing
* **[P2]** private
* **[P3]** org
* **[P4]** public
* **[P5]** null
* **[P6]** Uppercase valid (if normalized)

#### ❌Negative

* **[N1]** Empty
* **[N2]** Wrong casing
* **[N3]** Invalid value
* **[N4]** Number
* **[N5]** Boolean
* **[N6]** Too long

### 💠prefs_voting `string`

#### 📄Description

Who can vote on this board. One of disabled, members, observers, org, public.

#### 📋Summary

| Property     | Value                                     |
|--------------|-------------------------------------------|
| Valid values | disabled, members, observers, org, public |
| Default      | disabled                                  |

#### ✅Positive

* **[P1]** Missing
* **[P2]** disabled
* **[P3]** members
* **[P4]** observers
* **[P5]** org
* **[P6]** public
* **[P7]** null

#### ❌Negative

* **[N1]** Empty
* **[N2]** Wrong casing
* **[N3]** Combined values
* **[N4]** Numeric
* **[N5]** Array

### 💠prefs_comments `string`

#### 📄Description

Who can comment on cards on this board. One of: disabled, members, observers, org, public.

#### 📋Summary

| Property     | Value                                     |
|--------------|-------------------------------------------|
| Valid values | disabled, members, observers, org, public |
| Default      | members                                   |

#### ✅Positive

* **[P1]** Missing
* **[P2]** disabled
* **[P3]** members
* **[P4]** observers
* **[P5]** org
* **[P6]** public
* **[P7]** null

#### ❌Negative

* **[N1]** Empty
* **[N2]** Wrong casing
* **[N3]** Combined values
* **[N4]** Numeric
* **[N5]** Array

### 💠prefs_invitations `string`

#### 📄Description

Determines what types of members can invite users to join. One of: admins, members.

#### 📋Summary

| Property     | Value           |
|--------------|-----------------|
| Valid values | members, admins |
| Default      | members         |

#### ✅Positive

- **[  ]** members
- **[  ]** admins
- **[  ]** missing
- **[  ]** null

#### ❌Negative

- **[   ]** empty
- **[   ]** wrong casing
- **[   ]** invalid type
- **[   ]** unsupported role

### 💠prefs_selfJoin `boolean`

#### 📄Description

Determines whether users can join the boards themselves or whether they have to be invited.

#### 📋Summary

| Property | Value |
|----------|-------|
| Default  | true  |

#### ✅Positive

- **[   ]** missing
- **[   ]** true
- **[   ]** false
- **[   ]** null

#### ❌Negative

- **[   ]** strings
- **[   ]** numbers
- **[   ]** empty
- **[   ]** JSON

### 💠prefs_cardCovers `boolean`

#### 📄Description

Determines whether card covers are enabled.

#### 📋Summary

| Property | Value |
|----------|-------|
| Default  | true  |

#### ✅Positive

- **[   ]** missing
- **[   ]** true
- **[   ]** false
- **[   ]** null

#### ❌Negative

- **[   ]** strings
- **[   ]** numbers
- **[   ]** empty
- **[   ]** JSON

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

- **[   ]** valid colors
- **[   ]** custom ID
- **[   ]** missing
- **[   ]** null

#### ❌Negative

- **[   ]** empty
- **[   ]** wrong casing
- **[   ]** number
- **[   ]** JSON
- **[   ]** too long

### 💠prefs_cardAging `string`

#### 📄Description

Determines the type of card aging that should take place on the board if card aging is enabled. One of: pirate, regular.

#### 📋Summary

| Property     | Value           |
|--------------|-----------------|
| Valid values | regular, pirate |
| Default      | regular         |

#### ✅Positive

- **[   ]** missing
- **[   ]** regular
- **[   ]** pirate
- **[   ]** null

#### ❌Negative

- **[   ]** empty
- **[   ]** unsupported
- **[   ]** wrong casing
- **[   ]** wrong type

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
