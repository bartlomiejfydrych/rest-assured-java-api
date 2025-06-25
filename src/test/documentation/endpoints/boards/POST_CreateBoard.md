# 🟣POST – Create a Board

## 🌐Endpoint

/boards

## 📄Description

Create a new board.

## 📌Important notes

None.

## ✅Test coverage

SOON

## 📦Query parameters / Payload

### 💠name `string` 🔴REQUIRED🔴

The new name for the board. 1 to 16384 characters long.

Min length: `1`  
Max length: `16384`

### 💠defaultLabels `boolean`

Determines whether to use the default set of labels.

Default: `true`

### 💠defaultLists `boolean`

Determines whether to add the default set of lists to a board (To Do, Doing, Done). It is ignored if idBoardSource is provided.

Default: `true`

### 💠desc `string`

A new description for the board, 0 to 16384 characters long

Min length: `0`  
Max length: `16384`

### 💠idOrganization `TrelloID`

The id or name of the Workspace the board should belong to.

Pattern: `^[0-9a-fA-F]{24}$`

### 💠idBoardSource `TrelloID`

The id of a board to copy into the new board.

Pattern: `^[0-9a-fA-F]{24}$`

### 💠keepFromSource `string`

To keep cards from the original board pass in the value cards

Default: `none`  
Valid values: `cards`, `none`

### 💠powerUps `string`

The Power-Ups that should be enabled on the new board. One of: all, calendar, cardAging, recap, voting.

Valid values: `all`, `calendar`, `cardAging`, `recap`, `voting`

### 💠prefs_permissionLevel `string`

The permissions level of the board. One of: org, private, public.

Default: `private`  
Valid values: `org`, `private`, `public`

### 💠prefs_voting `string`

Who can vote on this board. One of disabled, members, observers, org, public.

Default: `disabled`  
Valid values: `disabled`, `members`, `observers`, `org`, `public`

### 💠prefs_comments `string`

Who can comment on cards on this board. One of: disabled, members, observers, org, public.

Default: `members`  
Valid values: `disabled`, `members`, `observers`, `org`, `public`

### 💠prefs_invitations `string`

Determines what types of members can invite users to join. One of: admins, members.

Default: `members`  
Valid values: `members`, `admins`

### 💠prefs_selfJoin `boolean`

Determines whether users can join the boards themselves or whether they have to be invited.

Default: `true`

### 💠prefs_cardCovers `boolean`

Determines whether card covers are enabled.

Default: `true`

### 💠prefs_background `string`

The id of a custom background or one of: blue, orange, green, red, purple, pink, lime, sky, grey.

Default: `blue`  
Valid values: `blue`, `orange`, `green`, `red`, `purple`, `pink`, `lime`, `sky`, `grey`

### 💠prefs_cardAging `string`

Determines the type of card aging that should take place on the board if card aging is enabled. One of: pirate, regular.

Default: `regular`  
Valid values: `pirate`, `regular`

## 📩Response

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
