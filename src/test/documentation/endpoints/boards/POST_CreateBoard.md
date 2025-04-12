# 🌐POST Create a Board

## Documentation link

https://developer.atlassian.com/cloud/trello/rest/api-group-boards/#api-boards-post

## Endpoint

POST  
https://api.trello.com/1/boards/?name={name}&key=APIKey&token=APIToken

## Query parameters

- `name` string Required🔴
- `defaultLabels` boolean
- `defaultLists` boolean
- `desc` string
- `idOrganization` TrelloID
- `idBoardSource` TrelloID
- `keepFromSource` string
- `powerUps` string
- `prefs_permissionLevel` string
- `prefs_voting` string
- `prefs_comments` string
- `prefs_invitations` string
- `prefs_selfJoin` boolean
- `prefs_cardCovers` boolean
- `prefs_background` string
- `prefs_cardAging` string

## Query parameters – description

`name` string Required🔴  
The new name for the board. 1 to 16384 characters long.  
Min length: 1  
Max length: 16384

`defaultLabels` boolean  
Determines whether to use the default set of labels.  
Default: true

`defaultLists` boolean  
Determines whether to add the default set of lists to a board (To Do, Doing, Done). It is ignored if idBoardSource is provided.  
Default: true

`desc` string  
A new description for the board, 0 to 16384 characters long  
Min length: 0  
Max length: 16384

`idOrganization` TrelloID  
The id or name of the Workspace the board should belong to.  
Pattern: ^[0-9a-fA-F]{24}$

`idBoardSource` TrelloID  
The id of a board to copy into the new board.  
Pattern: ^[0-9a-fA-F]{24}$

`keepFromSource` string  
To keep cards from the original board pass in the value cards  
Default: none  
Valid values: cards, none

`powerUps` string  
The Power-Ups that should be enabled on the new board. One of: all, calendar, cardAging, recap, voting.  
Valid values: all, calendar, cardAging, recap, voting

`prefs_permissionLevel` string  
The permissions level of the board. One of: org, private, public.  
Default: private  
Valid values: org, private, public

`prefs_voting` string  
Who can vote on this board. One of disabled, members, observers, org, public.  
Default: disabled  
Valid values: disabled, members, observers, org, public

`prefs_comments` string  
Who can comment on cards on this board. One of: disabled, members, observers, org, public.  
Default: members  
Valid values: disabled, members, observers, org, public

`prefs_invitations` string  
Determines what types of members can invite users to join. One of: admins, members.  
Default: members  
Valid values: members, admins

`prefs_selfJoin` boolean  
Determines whether users can join the boards themselves or whether they have to be invited.  
Default: true

`prefs_cardCovers` boolean  
Determines whether card covers are enabled.  
Default: true

`prefs_background` string  
The id of a custom background or one of: blue, orange, green, red, purple, pink, lime, sky, grey.  
Default: blue  
Valid values: blue, orange, green, red, purple, pink, lime, sky, grey

`prefs_cardAging` string  
Determines the type of card aging that should take place on the board if card aging is enabled. One of: pirate, regular.  
Default: regular  
Valid values: pirate, regular
