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

Create a new board.

## 📌Important notes <a name="important_notes"></a>

Each time the `"name"` changes, the `"url"` also changes.

Depending on the `"background"` color, the HEX color value for `"backgroundColor"`, `"backgroundBottomColor"`
and `"backgroundTopColor"` changes.

---

# ☑Test coverage <a name="test_coverage"></a>

## 🧵Query parameters <a name="query_parameters"></a>

### 💠name `string` 🔴REQUIRED🔴

#### 📄Description

The new name for the board. 1 to 16384 characters long.

Min length: `1`  
Max length: `16384`

#### 📋Summary

| Property   | Value                   |
|------------|-------------------------|
| Required   | ✔                       |
| Min length | 1                       |
| Max length | 16384 (practical ~2000) |

#### ✅Positive

- **[ P1 ]** Special characters and numbers
- **[ P2 ]** 1 character
- **[ 💥 ]** 16384 characters → Can't test it because max URI size is ~2000 characters

#### ❌Negative

- **[ N1 ]** Missing (0 characters)
- **[ N2 ]** null
- **[ N3 ]** Empty string ("")
- **[ 💥 ]** 16385 characters → Can't test it because max URI size is ~2000 characters

### 💠defaultLabels `boolean`

#### 📄Description

Determines whether to use the default set of labels.

Default: `true`

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

None.

### 💠defaultLists `boolean`

#### 📄Description

Determines whether to add the default set of lists to a board (To Do, Doing, Done). It is ignored if idBoardSource is provided.

Default: `true`

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
- **[ ⏭ ]** Is it ignored when `idBoardSource` is given?

#### ❌Negative

None.

### 💠desc `string`

#### 📄Description

A new description for the board, 0 to 16384 characters long

Min length: `0`  
Max length: `16384`

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
- **[ 💥 ]** 16384 characters → Can't test it because max URI size is ~2000 characters

#### ❌Negative

- **[ 💥 ]** 16385 characters → Can't test it because max URI size is ~2000 characters

### 💠idOrganization `TrelloID`

#### 📄Description

The id or name of the Workspace the board should belong to.

Pattern: `^[0-9a-fA-F]{24}$`

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
- **[ N5 ]** Incompatible with `^[0-9a-fA-F]{24}$`

### 💠idBoardSource `TrelloID`

#### 📄Description

The id of a board to copy into the new board.

Pattern: `^[0-9a-fA-F]{24}$

#### 📋Summary

| Property | Value               |
|----------|---------------------|
| Pattern  | `^[0-9a-fA-F]{24}$` |
| Required | ❌                   |

#### ✅Positive

- **[ P1 ]** Missing → Not in response at all
- **[ P3 ]** null
- **[ ⏭ ]** Correct

#### ❌Negative

- **[ N6 ]** Non-existent
- **[ N7 ]** Incompatible with `^[0-9a-fA-F]{24}$`

### 💠keepFromSource `string`

#### 📄Description

To keep cards from the original board pass in the value cards

Default: `none`  
Valid values: `cards`, `none`

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

#### ❌Negative

- **[ 💥 ]** Other string → It was ignored and board was created

### 💠powerUps `string`

#### 📄Description

The Power-Ups that should be enabled on the new board. One of: all, calendar, cardAging, recap, voting.

Valid values: `all`, `calendar`, `cardAging`, `recap`, `voting`

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

- **[ 💥 ]** Other string → It was ignored and board was created

### 💠prefs_permissionLevel `string`

#### 📄Description

The permissions level of the board. One of: org, private, public.

Default: `private`  
Valid values: `org`, `private`, `public`

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

- **[ N8 ]** Other string

### 💠prefs_voting `string`

#### 📄Description

Who can vote on this board. One of disabled, members, observers, org, public.

Default: `disabled`  
Valid values: `disabled`, `members`, `observers`, `org`, `public`

#### 📋Summary

| Property     | Value                                     |
|--------------|-------------------------------------------|
| Valid values | disabled, members, observers, org, public |
| Default      | disabled                                  |

#### ✅Positive

- **[ P1 ]** Missing (will there be a default value of `disabled`)
- **[ P4 ]** null
- **[ P2 ]** disabled
- **[ P3 ]** members
- **[ P5r ]** observers
- **[ P5r ]** org
- **[ P5r ]** public

#### ❌Negative

- **[ N9 ]** Other string

### 💠prefs_comments `string`

#### 📄Description

Who can comment on cards on this board. One of: disabled, members, observers, org, public.

Default: `members`  
Valid values: `disabled`, `members`, `observers`, `org`, `public`

#### 📋Summary

| Property     | Value                                     |
|--------------|-------------------------------------------|
| Valid values | disabled, members, observers, org, public |
| Default      | members                                   |

#### ✅Positive

- **[ P1 ]** Missing (will there be a default value of `members`)
- **[ P4 ]** null
- **[ P5r ]** disabled
- **[ P2 ]** members
- **[ P3 ]** observers
- **[ P5r ]** org
- **[ P5r ]** public

#### ❌Negative

- **[ N10 ]** Other string

### 💠prefs_invitations `string`

#### 📄Description

Determines what types of members can invite users to join. One of: admins, members.

Default: `members`  
Valid values: `members`, `admins`

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

- **[ N11 ]** Other string

### 💠prefs_selfJoin `boolean`

#### 📄Description

Determines whether users can join the boards themselves or whether they have to be invited.

Default: `true`

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

None.

### 💠prefs_cardCovers `boolean`

#### 📄Description

Determines whether card covers are enabled.

Default: `true`

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

None.

### 💠prefs_background `string`

#### 📄Description

The id of a custom background or one of: blue, orange, green, red, purple, pink, lime, sky, grey.

Default: `blue`  
Valid values: `blue`, `orange`, `green`, `red`, `purple`, `pink`, `lime`, `sky`, `grey`

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

#### ❌Negative

- **[ 💥 ]** Other string -> It was ignored and board was created

### 💠prefs_cardAging `string`

#### 📄Description

Determines the type of card aging that should take place on the board if card aging is enabled. One of: pirate, regular.

Default: `regular`  
Valid values: `pirate`, `regular`

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

- **[ N12 ]** Other string

### 💠Other tests

#### ✅Positive

- **[ P1 ]** Providing only required parameters (`name`)
- **[ P2 ]** Providing all or most parameters at once

#### ❌Negative

None.

---

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
