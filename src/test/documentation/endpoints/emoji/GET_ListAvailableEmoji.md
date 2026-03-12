# 🟢GET – List available Emoji

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

/emoji

## 📗Description <a name="description"></a>

List available Emoji.  
Forge and OAuth2 apps cannot access this REST resource.

## 📌Important notes <a name="important_notes"></a>

If any tests seem incomplete, outdated, or different from what is in the Trello documentation,
it may mean that the developers may have changed something over time and it's different from what I wrote the tests for.

💠locale `string`  
There is no information in the documentation about what else could be correct.  
I found out on my own that this parameter can take values such as `"en-US"`.

---

# ☑Test coverage <a name="test_coverage"></a>

## 🧵Query parameters <a name="query_parameters"></a>

### 💠locale `string`

#### 📄Description

The locale to return emoji descriptions and names in. Defaults to the logged in member's locale.

#### 📋Summary

| Property | Value                      |
|----------|----------------------------|
| Required | ❌                          |
| Default  | User locale like `"en-US"` |

#### ✅Positive

- **[ P1 ]** Missing
- **[ P2 ]** null
- **[ P3 ]** Other (en-US) than default (PL)

#### ❌Negative

- **[ N1 ]** Incorrect

### 💠spritesheets `boolean`

#### 📄Description

`true` to return spritesheet URLs in the response  
Default: `false`

#### 📋Summary

| Property | Value |
|----------|-------|
| Required | ❌     |
| Default  | false |

#### ✅Positive

- **[ P1 ]** Missing
- **[ P2 ]** null
- **[ P3 ]** false
- **[ P4 ]** true

#### ❌Negative

None.

---

# 📜Response <a name="response"></a>

```json
{
  "trello": [
    {
      "unified": "1F600",
      "name": "GRINNING FACE",
      "native": "😀",
      "shortName": "grinning",
      "shortNames": [
        "grinning"
      ],
      "text": ":D",
      "texts": null,
      "category": "Smileys & People",
      "sheetX": 30,
      "sheetY": 24,
      "skinVariation": null,
      "tts": "szeroko uśmiechnięta twarz",
      "keywords": [
        "roześmiana buźka",
        "szeroko uśmiechnięta twarz"
      ]
    },
    {
      "unified": "1F601",
      "name": "GRINNING FACE WITH SMILING EYES",
      "native": "😁",
      "shortName": "grin",
      "shortNames": [
        "grin"
      ],
      "text": null,
      "texts": null,
      "category": "Smileys & People",
      "sheetX": 30,
      "sheetY": 25,
      "skinVariation": null,
      "tts": "szeroko uśmiechnięta twarz o roześmianych oczach",
      "keywords": [
        "śmiech",
        "szeroko uśmiechnięta twarz o roześmianych oczach",
        "uśmiech",
        "wyszczerzone",
        "zęby"
      ]
    },
    AND MORE...
  ]
}
```
