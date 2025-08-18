# 🟢GET – List available Emoji

# 📑Contents

- [🌐Endpoint](#endpoint)
- [📄Description](#description)
- [📌Important notes](#important_notes)
- [☑Test coverage](#test_coverage)
- [📦Query parameters / Payload](#query_parameters_payload)
- [📩Response](#response)

---

## 🌐Endpoint <a name="endpoint"></a>

/emoji

---

## 📄Description <a name="description"></a>

List available Emoji.  
Forge and OAuth2 apps cannot access this REST resource.

---

## 📌Important notes <a name="important_notes"></a>

### 💠locale `string`

There is no information in the documentation about what else could be correct.

---

## ☑Test coverage <a name="test_coverage"></a>

- 💠locale `string`
  - ✅Positive:
    - **[P1]** Missing
    - **[]** Other than default
    - **[]** null
- 💠spritesheets `boolean`
  - ✅Positive:
    - **[P1]** Missing
    - **[]** true
    - **[]** false
    - **[]** null

---

## 📦Query parameters / Payload <a name="query_parameters_payload"></a>

### 💠locale `string`

The locale to return emoji descriptions and names in. Defaults to the logged in member's locale.

### 💠spritesheets `boolean`

`true` to return spritesheet URLs in the response  
Default: `false`

---

## 📩Response <a name="response"></a>

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
