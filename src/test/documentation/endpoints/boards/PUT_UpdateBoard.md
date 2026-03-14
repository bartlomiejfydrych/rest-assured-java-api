# 🔵PUT – Update a Board

# 📑Contents

- [📔Basic information](#basic_information)
  - [🌐Endpoint](#endpoint)
  - [📗Description](#description)
  - [📌Important notes](#important_notes)
- [☑Test coverage](#test_coverage)
  - [🔗Path parameters](#path_parameters)
  - [🧵Query parameters](#query_parameters)
- [📜Response](#response)

---

# 📔Basic information <a name="basic_information"></a>

## 🌐Endpoint <a name="endpoint"></a>

/boards/{id}

## 📗Description <a name="description"></a>

Update an existing board by id.  
Forge and OAuth2 apps cannot access this REST resource.

## 📌Important notes <a name="important_notes"></a>

Each time the `"name"` changes, the `"url"` also changes.

Depending on the `"background"` color, the HEX color value for `"backgroundColor"`, `"backgroundBottomColor"`
and `"backgroundTopColor"` changes.

---

# ☑Test coverage <a name="test_coverage"></a>

## 🔗Path parameters <a name="path_parameters"></a>

### 💠id `TrelloID` 🔴REQUIRED🔴

#### 📄Description

The `ID` of the Board to edit.

#### 📋Summary

| Property | Value               |
|----------|---------------------|
| Required | ✔                   |
| Type     | 24-hex string       |
| Pattern  | `^[0-9a-fA-F]{24}$` |

#### ✅Positive

- **[ PX ]** Correct

#### ❌Negative

🔴TODO🔴
- **[ N ]** Non-existent
- **[ N ]** Incorrect

## 🧵Query parameters <a name="query_parameters"></a>

### 💠name `string`

#### 📄Description

The new name for the board. `1` to `16384` characters long.

#### 📋Summary

| Property    | Value                     |
|-------------|---------------------------|
| Required    | ❌                         |
| Min length  | 1 (if provided)           |
| Max length  | 16384 (practical ~2000)   |
| Side effect | Updates `url`, `shortUrl` |

#### ✅Positive

- **[ P1 ]** Special characters and numbers
- **[ P4 ]** 1 character
- **[ P3 ]** Missing (0 characters)
- **[ P2 ]** null
- **[ 💥 ]** 16384 characters → Can't test it because max URI size is ~2000 characters

#### ❌Negative

- **[ N1 ]** Empty string ("")
- **[ 💥 ]** 16385 characters → Can't test it because max URI size is ~2000 characters

### 💠desc `string`

#### 📄Description

A new description for the board, `0` to `16384` characters long

#### 📋Summary

| Property | Value                   |
|----------|-------------------------|
| Min      | 0                       |
| Max      | 16384 (practical ~2000) |
| Required | ❌                       |

#### ✅Positive

- **[ P1 ]** Special characters and numbers
- **[ P3 ]** Missing (0 characters)
- **[ P2 ]** null
- **[ 💥 ]** 16384 characters → Can't test it because max URI size is ~2000 characters

#### ❌Negative

- **[ 💥 ]** 16385 characters → Can't test it because max URI size is ~2000 characters

### 💠closed `boolean`

#### 📄Description

Whether the board is closed

#### 📋Summary

| Property | Value                                             |
|----------|---------------------------------------------------|
| Required | ❌                                                 |
| Default  | false                                             |
| Effect   | `true` → archive board <br>`false` → reopen board |

#### ✅Positive

- **[ P3 ]** Missing
- **[ P1 ]** true
- **[ P4 ]** false
- **[ P2 ]** null

#### ❌Negative

None.

### 💠subscribed `TrelloID`

#### 📄Description

Whether the acting user is subscribed to the board  
Style: `form`  
Pattern: `^[0-9a-fA-F]{24}$`

#### 📋Summary

| Property | Value                               |
|----------|-------------------------------------|
| Required | ❌                                   |
| Pattern  | `^[0-9a-fA-F]{24}$`                 |
| Effect   | Subscribe / unsubscribe acting user |

#### ✅Positive

- **[ P3 ]** Missing
- **[ P2 ]** null
- **[ 💥 ]** Correct → I was unable to determine the correct TrelloID

#### ❌Negative

- **[ N2 ]** Non-existent
- **[ N3 ]** Incompatible with `^[0-9a-fA-F]{24}$`

### 💠idOrganization `string` (📌It is possible that instead of String there is `TrelloID` | `^[0-9a-fA-F]{24}$`)

#### 📄Description

The id of the Workspace the board should be moved to

#### 📋Summary

| Property | Value                            |
|----------|----------------------------------|
| Required | ❌                                |
| Pattern  | `^[0-9a-fA-F]{24}$`              |
| Effect   | Moves board to another Workspace |

#### ✅Positive

- **[ P3 ]** Missing
- **[ P2 ]** null
- **[ P1 ]** Valid

#### ❌Negative

- **[ N4 ]** Non-existent
- **[ N5 ]** Incompatible with `^[0-9a-fA-F]{24}$`

### 💠prefs/permissionLevel `string`

#### 📄Description

One of: `org`, `private`, `public`

#### 📋Summary

| Property     | Value                |
|--------------|----------------------|
| Valid values | org, private, public |
| Default      | private              |

#### ✅Positive

- **[ P3 ]** Missing
- **[ P2 ]** null
- **[ P1 ]** org
- **[ P4 ]** private
- **[ P5 ]** public

#### ❌Negative

- **[ N6 ]** Other string

### 💠prefs/selfJoin `boolean`

#### 📄Description

Whether Workspace members can join the board themselves

#### 📋Summary

| Property | Value |
|----------|-------|
| Default  | true  |

#### ✅Positive

- **[ P3 ]** Missing
- **[ P1 ]** true
- **[ P4 ]** false
- **[ P2 ]** null

#### ❌Negative

None.

### 💠prefs/cardCovers `boolean`

#### 📄Description

Whether card covers should be displayed on this board

#### 📋Summary

| Property | Value |
|----------|-------|
| Default  | true  |

#### ✅Positive

- **[ P3 ]** Missing
- **[ P1 ]** true
- **[ P4 ]** false
- **[ P2 ]** null

#### ❌Negative

None.

### 💠prefs/hideVotes `boolean`

#### 📄Description

Determines whether the Voting Power-Up should hide who voted on cards or not.

#### 📋Summary

| Property   | Value                                                         |
|------------|---------------------------------------------------------------|
| Required   | ❌                                                             |
| Default    | false                                                         |
| Effect     | `true` → votes are anonymous <br>`false` → voters are visible |
| Depends on | `powerUps=voting`                                             |

#### ✅Positive

- **[ P3 ]** Missing
- **[ P1 ]** true
- **[ P4 ]** false
- **[ P2 ]** null

#### ❌Negative

None.

### 💠prefs/invitations `string`

#### 📄Description

Who can invite people to this board. One of: `admins`, `members`

#### 📋Summary

| Property     | Value           |
|--------------|-----------------|
| Valid values | members, admins |
| Default      | members         |

#### ✅Positive

- **[ P3 ]** Missing
- **[ P2 ]** null
- **[ P1 ]** admins
- **[ P4 ]** members

#### ❌Negative

- **[ N7 ]** Other string

### 💠prefs/voting `string`

#### 📄Description

Who can vote on this board. One of `disabled`, `members`, `observers`, `org`, `public`

#### 📋Summary

| Property     | Value                                     |
|--------------|-------------------------------------------|
| Valid values | disabled, members, observers, org, public |
| Default      | disabled                                  |

#### ✅Positive

- **[ P3 ]** Missing
- **[ P2 ]** null
- **[ P1 ]** disabled
- **[ P4 ]** members
- **[ 💥 ]** observers → Can't test it because "board is not in an organization with observers enabled" and I don't want to change it
- **[ P5r ]** org
- **[ P5r ]** public

#### ❌Negative

- **[ N8 ]** Other string

### 💠prefs/comments `string`

#### 📄Description

Who can comment on cards on this board. One of: `disabled`, `members`, `observers`, `org`, `public`

#### 📋Summary

| Property     | Value                                     |
|--------------|-------------------------------------------|
| Valid values | disabled, members, observers, org, public |
| Default      | members                                   |

#### ✅Positive

- **[ P3 ]** Missing
- **[ P2 ]** null
- **[ P1 ]** disabled
- **[ P4 ]** members
- **[ 💥 ]** observers → Can't test it because "board is not in an organization with observers enabled" and I don't want to change it
- **[ P5r ]** org
- **[ P5r ]** public

#### ❌Negative

- **[ N9 ]** Other string

### 💠prefs/background `string`

#### 📄Description

The id of a custom background or one of: `blue`, `orange`, `green`, `red`, `purple`, `pink`, `lime`, `sky`, `grey`

#### 📋Summary

| Property     | Value                                                   |
|--------------|---------------------------------------------------------|
| Valid values | blue, orange, green, red, purple, pink, lime, sky, grey |
| Default      | blue                                                    |
| Also accepts | custom background ID                                    |

#### ✅Positive

- **[ P3 ]** Missing
- **[ P2 ]** null
- **[ P1 ]** blue
- **[ P4 ]** orange
- **[ P5r ]** green
- **[ P5r ]** red
- **[ P5r ]** purple
- **[ P5r ]** pink
- **[ P5r ]** lime
- **[ P5r ]** sky
- **[ P5r ]** grey

#### ❌Negative

- **[ N10 ]** Other string

### 💠prefs/cardAging `string`

#### 📄Description

One of: `pirate`, `regular`

#### 📋Summary

| Property     | Value           |
|--------------|-----------------|
| Valid values | regular, pirate |
| Default      | regular         |

#### ✅Positive

- **[ P3 ]** Missing (will there be a default value of `regular`)
- **[ P2 ]** null
- **[ P1 ]** regular
- **[ P4 ]** pirate

#### ❌Negative

- **[ N11 ]** Other string

### 💠prefs/calendarFeedEnabled `boolean`

#### 📄Description

Determines whether the calendar feed is enabled or not.

#### 📋Summary

| Property | Value                                                                |
|----------|----------------------------------------------------------------------|
| Required | ❌                                                                    |
| Default  | false                                                                |
| Effect   | `true` → calendar feed enabled <br> `false` → calendar feed disabled |

#### ✅Positive

- **[ P3 ]** Missing
- **[ P1 ]** true
- **[ P4 ]** false
- **[ P2 ]** null

#### ❌Negative

None.

### 💠Other tests

#### ✅Positive

- **[ P1 ]** Providing all or most parameters at once
- **[ P1 | P4 ]** Checking if (POST_url != PUT_url) when table name is changed
- **[ P1 | P4 ]** Checking if the beginning of the URL response PUT and POST remains the same

#### ❌Negative

None.

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
