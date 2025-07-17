# 🟣POST – Update a Board

# 📑Contents

- [🌐Endpoint](#endpoint)
- [📄Description](#description)
- [📌Important notes](#important_notes)
- [☑Test coverage](#test_coverage)
- [📦Query parameters / Payload](#query_parameters_payload)
- [📩Response](#response)

---

## 🌐Endpoint <a name="endpoint"></a>

/boards/{id}

---

## 📄Description <a name="description"></a>

Update an existing board by id.  
Forge and OAuth2 apps cannot access this REST resource.

---

## 📌Important notes <a name="important_notes"></a>

None.

---

## ☑Test coverage <a name="test_coverage"></a>

- 💠name `string`
  - ✅Positive:
    - [] Special characters and numbers
    - [] 1 character
    - [] Missing (0 characters)
    - [] null
    - [💥] 16384 characters -> Can't test it because max URI size is ~2000 characters
  - ❌Negative:
    - [] Empty string ("")
    - [💥] 16385 characters -> Can't test it because max URI size is ~2000 characters
- 💠desc `string`
  - ✅Positive:
    - [] Special characters and numbers
    - [] Missing (0 characters)
    - [] null
    - [💥] 16384 characters -> Can't test it because max URI size is ~2000 characters
  - ❌Negative:
    - [💥] 16385 characters -> Can't test it because max URI size is ~2000 characters
- 💠closed `boolean`
  - ✅Positive:
    - [] Missing
    - [] true
    - [] false
    - [] null
- 💠subscribed `TrelloID` | Style: `form` | `^[0-9a-fA-F]{24}$`
  - ✅Positive:
    - [] Missing
    - [] null
    - [] Correct
  - ❌Negative:
    - [] Non-existent
    - [] Incompatible with `^[0-9a-fA-F]{24}$`
- 💠idOrganization `string` (📌It is possible that instead of String there is `TrelloID` | `^[0-9a-fA-F]{24}$`)
  - ✅Positive:
    - [] Missing
    - [] null
    - [] Valid
  - ❌Negative:
    - [] Non-existent
    - [] Incompatible with `^[0-9a-fA-F]{24}$`
- 💠prefs/permissionLevel `string`
  - ✅Positive:
    - [] Missing
    - [] null
    - [] org
    - [] private
    - [] public
  - ❌Negative:
    - [] Other string
- 💠prefs/selfJoin `boolean`
  - ✅Positive:
    - [] Missing
    - [] true
    - [] false
    - [] null
- 💠prefs/cardCovers `boolean`
  - ✅Positive:
    - [] Missing
    - [] true
    - [] false
    - [] null
- 💠prefs/hideVotes `boolean`
  - ✅Positive:
    - [] Missing
    - [] true
    - [] false
    - [] null
- 💠prefs/invitations `string`
  - ✅Positive:
    - [] Missing
    - [] null
    - [] admins
    - [] members
  - ❌Negative:
    - [] Other string
- 💠prefs/voting `string`
  - ✅Positive:
    - [] Missing
    - [] null
    - [] disabled
    - [] members
    - [] observers
    - [] org
    - [] public
  - ❌Negative:
    - [] Other string
- 💠prefs/comments `string`
  - ✅Positive:
    - [] Missing
    - [] null
    - [] disabled
    - [] members
    - [] observers
    - [] org
    - [] public
  - ❌Negative:
    - [] Other string
- 💠prefs/background `string`
  - ✅Positive:
    - [] Missing
    - [] null
    - [] blue
    - [] orange
    - [] green
    - [] red
    - [] purple
    - [] pink
    - [] lime
    - [] sky
    - [] grey
  - ❌Negative:
    - [SPRAWDZIĆ💥] Other string -> It was ignored and board was created
- 💠prefs/cardAging `string`
  - ✅Positive:
    - [] Missing (will there be a default value of `regular`)
    - [] null
    - [] regular
    - [] pirate
  - ❌Negative:
    - [] Other string
- 💠prefs/calendarFeedEnabled `boolean`
  - ✅Positive:
    - [] Missing
    - [] true
    - [] false
    - [] null
- 💠labelNames/green `string`
  - ✅Positive:
    - [] Special characters and numbers
    - [] 1 character
    - [] Missing (0 characters)
    - [] null
    - [💥] 16384 characters -> Can't test it because max URI size is ~2000 characters
  - ❌Negative:
    - [] Empty string ("")
    - [💥] 16385 characters -> Can't test it because max URI size is ~2000 characters
- 💠labelNames/yellow `string`
  - ✅Positive:
    - [] Special characters and numbers
    - [] 1 character
    - [] Missing (0 characters)
    - [] null
    - [💥] 16384 characters -> Can't test it because max URI size is ~2000 characters
  - ❌Negative:
    - [] Empty string ("")
    - [💥] 16385 characters -> Can't test it because max URI size is ~2000 characters
- 💠labelNames/orange `string`
  - ✅Positive:
    - [] Special characters and numbers
    - [] 1 character
    - [] Missing (0 characters)
    - [] null
    - [💥] 16384 characters -> Can't test it because max URI size is ~2000 characters
  - ❌Negative:
    - [] Empty string ("")
    - [💥] 16385 characters -> Can't test it because max URI size is ~2000 characters
- 💠labelNames/red `string`
  - ✅Positive:
    - [] Special characters and numbers
    - [] 1 character
    - [] Missing (0 characters)
    - [] null
    - [💥] 16384 characters -> Can't test it because max URI size is ~2000 characters
  - ❌Negative:
    - [] Empty string ("")
    - [💥] 16385 characters -> Can't test it because max URI size is ~2000 characters
- 💠labelNames/purple `string`
  - ✅Positive:
    - [] Special characters and numbers
    - [] 1 character
    - [] Missing (0 characters)
    - [] null
    - [💥] 16384 characters -> Can't test it because max URI size is ~2000 characters
  - ❌Negative:
    - [] Empty string ("")
    - [💥] 16385 characters -> Can't test it because max URI size is ~2000 characters
- 💠labelNames/blue `string`
  - ✅Positive:
    - [] Special characters and numbers
    - [] 1 character
    - [] Missing (0 characters)
    - [] null
    - [💥] 16384 characters -> Can't test it because max URI size is ~2000 characters
  - ❌Negative:
    - [] Empty string ("")
    - [💥] 16385 characters -> Can't test it because max URI size is ~2000 characters

---

## 📦Query parameters / Payload <a name="query_parameters_payload"></a>

### 💠name `string`

The new name for the board. `1` to `16384` characters long.

### 💠desc `string`

A new description for the board, `0` to `16384` characters long

### 💠closed `boolean`

Whether the board is closed

### 💠subscribed `TrelloID`

Whether the acting user is subscribed to the board  
Style: `form`  
Pattern: `^[0-9a-fA-F]{24}$`

### 💠idOrganization `string` (📌It is possible that instead of String there is `TrelloID` | `^[0-9a-fA-F]{24}$`)

The id of the Workspace the board should be moved to

### 💠prefs/permissionLevel `string`

One of: `org`, `private`, `public`

### 💠prefs/selfJoin `boolean`

Whether Workspace members can join the board themselves

### 💠prefs/cardCovers `boolean`

Whether card covers should be displayed on this board

### 💠prefs/hideVotes `boolean`

Determines whether the Voting Power-Up should hide who voted on cards or not.

### 💠prefs/invitations `string`

Who can invite people to this board. One of: `admins`, `members`

### 💠prefs/voting `string`

Who can vote on this board. One of `disabled`, `members`, `observers`, `org`, `public`

### 💠prefs/comments `string`

Who can comment on cards on this board. One of: `disabled`, `members`, `observers`, `org`, `public`

### 💠prefs/background `string`

The id of a custom background or one of: `blue`, `orange`, `green`, `red`, `purple`, `pink`, `lime`, `sky`, `grey`

### 💠prefs/cardAging `string`

One of: `pirate`, `regular`

### 💠prefs/calendarFeedEnabled `boolean`

Determines whether the calendar feed is enabled or not.

### 💠labelNames/green `string`

Name for the green label. `1` to `16384` characters long

### 💠labelNames/yellow `string`

Name for the yellow label. `1` to `16384` characters long

### 💠labelNames/orange `string`

Name for the orange label. `1` to `16384` characters long

### 💠labelNames/red `string`

Name for the red label. `1` to `16384` characters long

### 💠labelNames/purple `string`

Name for the purple label. `1` to `16384` characters long

### 💠labelNames/blue `string`

Name for the blue label. `1` to `16384` characters long

---

## 📩Response <a name="response"></a>

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
