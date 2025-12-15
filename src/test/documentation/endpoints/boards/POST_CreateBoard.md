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
- **[ 💥 ]** 16384 characters → Can't test it because max URI size is ~2000 characters

#### ❌Negative

- **[ N1 ]** Missing
- **[ N2 ]** null
- **[ N3 ]** Empty string (`""`)
- **[ N ]** Only spaces
- **[ N ]** Invalid UTF-8 (`\x80` | `\xED\xA0\x80`)
- Wrong type:
    - **[ N ]** number
    - **[ N ]** boolean
    - **[ N ]** JSON object

### 💠defaultLabels `boolean`

#### 📄Description

Determines whether to use the default set of labels.

#### 📋Summary

| Property | Value |
|----------|-------|
| Required | ❌     |
| Default  | true  |

#### ✅Positive

- **[ P1 ]** Missing (will there be a default value of `true`) → Not in response at all
- **[ P2 ]** true
- **[ P3 ]** false
- **[ P4 ]** null

#### ❌Negative

- **[ N ]** "true"
- **[ N ]** "false"
- **[ N ]** 0
- **[ N ]** 1
- **[ N ]** "yes"
- **[ N ]** "no"
- **[ N ]** -1
- **[ N ]** Empty string (`""`)
- **[ N ]** Object
- **[ N ]** Array

### 💠defaultLists `boolean`

#### 📄Description

Determines whether to add the default set of lists to a board (To Do, Doing, Done). It is ignored if idBoardSource is provided.

#### 📋Summary

| Property | Value |
|----------|-------|
| Required | ❌     |
| Default  | true  |

#### ✅Positive

- **[ P1 ]** Missing (will there be a default value of `true`) -> Not in response at all
- **[ P2 ]** true
- **[ P3 ]** false
- **[ P4 ]** null
- **[ ⏭ ]** Is it ignored when `idBoardSource` is given?

#### ❌Negative

- **[ N ]** "true"
- **[ N ]** "false"
- **[ N ]** 0
- **[ N ]** 1
- **[ N ]** "yes"
- **[ N ]** "no"
- **[ N ]** -1
- **[ N ]** Empty string (`""`)
- **[ N ]** Object
- **[ N ]** Array

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

- **[ P2 ]** Special characters and numbers
- **[ P1 ]** Missing (will there be a default value of `""`)
- **[ P4 ]** null
- **[ P ]** Leading/Trailing spaces (" text ")
- **[ P  ]** URL-unsafe characters (`%2F` | encoded)
- **[ 💥 ]** 16384 characters → Can't test it because max URI size is ~2000 characters

#### ❌Negative

- **[ N ]** Only spaces
- **[ N ]** Invalid UTF-8 (`\x80` | `\xED\xA0\x80`)
- Wrong type:
  - **[ N ]** number
  - **[ N ]** boolean
  - **[ N ]** JSON object
- **[ 💥 ]** 16385 characters → Can't test it because max URI size is ~2000 characters

### 💠idOrganization `TrelloID`

#### 📄Description

The id or name of the Workspace the board should belong to.

#### 📋Summary

| Property | Value               |
|----------|---------------------|
| Required | ❌                   |
| Pattern  | `^[0-9a-fA-F]{24}$` |

#### ✅Positive

- **[ P1 ]** Missing → Default ID
- **[ P3 ]** null
- **[ P2 ]** Valid

#### ❌Negative

- **[ N4 ]** Non-existent
- **[ N5 ]** Too short
- **[ N5 ]** Too long
- **[ N5 ]** Empty string (`""`)
- **[ N5 ]** Numeric-only
- **[ N ]** Fits, but we shouldn't have access to it

### 💠idBoardSource `TrelloID`

#### 📄Description

The id of a board to copy into the new board.

#### 📋Summary

| Property | Value               |
|----------|---------------------|
| Pattern  | `^[0-9a-fA-F]{24}$` |
| Required | ❌                   |

#### ✅Positive

- **[ P1 ]** Missing → Not in response at all
- **[ P3 ]** null
- **[ ⏭ ]** Valid ID

#### ❌Negative

- **[ N6 ]** Non-existent
- **[ N7 ]** Too short
- **[ N7 ]** Too long
- **[ N7 ]** Empty string (`""`)
- **[ N7 ]** Numeric-only
- **[ N ]** Fits, but we shouldn't have access to it

### 💠keepFromSource `string`

#### 📄Description

To keep cards from the original board pass in the value cards.

#### 📋Summary

| Property     | Value       |
|--------------|-------------|
| Valid values | none, cards |
| Default      | none        |

#### ✅Positive

- **[ P1 ]** Missing (will there be a default value of `none`) → Not in response at all
- **[ P4 ]** null
- **[ P2 ]** none
- **[ P3 ]** cards
- **[ P ]** Leading/Trailing spaces (" text ")

#### ❌Negative

- **[ N ]** NONE
- **[ N ]** CARDS
- **[ N ]** Empty string (`""`)
- **[ N ]** number
- **[ 💥 ]** Unknown string → It was ignored and board was created

### 💠powerUps `string`

#### 📄Description

The Power-Ups that should be enabled on the new board. One of: all, calendar, cardAging, recap, voting.

#### 📋Summary

| Property     | Value                                   |
|--------------|-----------------------------------------|
| Valid values | all, calendar, cardAging, recap, voting |

#### ✅Positive

- **[ P1 ]** Missing → Not in response at all
- **[ P4 ]** null
- **[ P2 ]** all
- **[ P3 ]** calendar
- **[ P5r ]** cardAging
- **[ P5r ]** recap
- **[ P5r ]** voting

#### ❌Negative

- **[ N ]** Combined list
- **[ N ]** Empty string (`""`)
- **[ N ]** ALL
- **[ N ]** CALENDAR
- **[ N ]** CARDAGING
- **[ N ]** RECAP
- **[ N ]** VOTING
- **[ N ]** Numeric
- **[ N ]** JSON array
- **[ 💥 ]** Unknown string → It was ignored and board was created

### 💠prefs_permissionLevel `string`

#### 📄Description

The permissions level of the board. One of: org, private, public.

#### 📋Summary

| Property     | Value                |
|--------------|----------------------|
| Valid values | org, private, public |
| Default      | private              |

#### ✅Positive

- **[ P1 ]** Missing (will there be a default value of `private`)
- **[ P4 ]** null
- **[ P2 ]** private
- **[ P3 ]** org
- **[ P5 ]** public

#### ❌Negative

- **[ N8 ]** Invalid value
- **[ N ]** Empty string (`""`)
- **[ N ]** PRIVATE
- **[ N ]** ORG
- **[ N ]** PUBLIC
- **[ N ]** Number
- **[ N ]** Boolean

### 💠prefs_voting `string`

#### 📄Description

Who can vote on this board. One of disabled, members, observers, org, public.

#### 📋Summary

| Property     | Value                                     |
|--------------|-------------------------------------------|
| Valid values | disabled, members, observers, org, public |
| Default      | disabled                                  |

#### ✅Positive

- **[ P1 ]** Missing
- **[ P4 ]** null
- **[ P2 ]** disabled
- **[ P3 ]** members
- **[ P5r ]** observers
- **[ P5r ]** org
- **[ P5r ]** public

#### ❌Negative

- **[ N9 ]** Other value
- **[ N ]** DISABLED
- **[ N ]** MEMBERS
- **[ N ]** OBSERVERS
- **[ N ]** ORG
- **[ N ]** PUBLIC
- **[ N ]** Numeric
- **[ N ]** Array

### 💠prefs_comments `string`

#### 📄Description

Who can comment on cards on this board. One of: disabled, members, observers, org, public.

#### 📋Summary

| Property     | Value                                     |
|--------------|-------------------------------------------|
| Valid values | disabled, members, observers, org, public |
| Default      | members                                   |

#### ✅Positive

- **[ P1 ]** Missing
- **[ P4 ]** null
- **[ P5r ]** disabled
- **[ P2 ]** members
- **[ P3 ]** observers
- **[ P5r ]** org
- **[ P5r ]** public

#### ❌Negative

- **[ N10 ]** Other, invalid value
- **[ N ]** DISABLED
- **[ N ]** MEMBERS
- **[ N ]** OBSERVERS
- **[ N ]** ORG
- **[ N ]** PUBLIC
- **[ N ]** Combined values
- **[ N ]** Numeric
- **[ N ]** Array

### 💠prefs_invitations `string`

#### 📄Description

Determines what types of members can invite users to join. One of: admins, members.

#### 📋Summary

| Property     | Value           |
|--------------|-----------------|
| Valid values | members, admins |
| Default      | members         |

#### ✅Positive

- **[ P1 ]** Missing (will there be a default value `members`)
- **[ P4 ]** null
- **[ P2 ]** members
- **[ P3 ]** admins

#### ❌Negative

- **[ N ]** Other, invalid value
- **[ N ]** Empty string (`""`)
- **[ N ]** MEMBERS
- **[ N ]** ADMINS

### 💠prefs_selfJoin `boolean`

#### 📄Description

Determines whether users can join the boards themselves or whether they have to be invited.

#### 📋Summary

| Property | Value |
|----------|-------|
| Default  | true  |

#### ✅Positive

- **[ P1 ]** Missing (will there be a default value `true`)
- **[ P2 ]** true
- **[ P3 ]** false
- **[ P4 ]** null

#### ❌Negative

- **[ N ]** "true"
- **[ N ]** "false"
- **[ N ]** 0
- **[ N ]** 1
- **[ N ]** "yes"
- **[ N ]** "no"
- **[ N ]** -1
- **[ N ]** Empty string (`""`)
- **[ N ]** Object
- **[ N ]** Array

### 💠prefs_cardCovers `boolean`

#### 📄Description

Determines whether card covers are enabled.

#### 📋Summary

| Property | Value |
|----------|-------|
| Default  | true  |

#### ✅Positive

- **[ P1 ]** Missing (will there be a default value `true`)
- **[ P2 ]** true
- **[ P3 ]** false
- **[ P4 ]** null

#### ❌Negative

- **[ N ]** "true"
- **[ N ]** "false"
- **[ N ]** 0
- **[ N ]** 1
- **[ N ]** "yes"
- **[ N ]** "no"
- **[ N ]** -1
- **[ N ]** Empty string (`""`)
- **[ N ]** Object
- **[ N ]** Array

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

- **[ P1 ]** Missing (will there be a default value of `blue`) -> Not in "prefs"
- **[ P4 ]** null
- **[ P2 ]** blue
- **[ P3 ]** orange
- **[ P5r ]** green
- **[ P5r ]** red
- **[ P5r ]** purple
- **[ P5r ]** pink
- **[ P5r ]** lime
- **[ P5r ]** sky
- **[ P5r ]** grey
- **[ P ]** custom ID

#### ❌Negative

- **[ N ]** Empty string (`""`)
- **[ N ]** BLUE
- **[ N ]** ORANGE
- **[ N ]** GREEN
- **[ N ]** RED
- **[ N ]** PURPLE
- **[ N ]** PINK
- **[ N ]** LIME
- **[ N ]** SKY
- **[ N ]** GREY
- **[ N ]** number
- **[ N ]** JSON

### 💠prefs_cardAging `string`

#### 📄Description

Determines the type of card aging that should take place on the board if card aging is enabled. One of: pirate, regular.

#### 📋Summary

| Property     | Value           |
|--------------|-----------------|
| Valid values | regular, pirate |
| Default      | regular         |

#### ✅Positive

- **[ P1 ]** Missing (will there be a default value of `regular`)
- **[ P4 ]** null
- **[ P2 ]** regular
- **[ P3 ]** pirate

#### ❌Negative

- **[ N12 ]** Other, invalid value
- **[ N ]** Empty string (`""`)
- **[ N ]** REGULAR
- **[ N ]** PIRATE
- **[ N ]** wrong type

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
