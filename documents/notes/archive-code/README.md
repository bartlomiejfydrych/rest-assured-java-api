# 🚮Archiwalny kod – notatki

# 📑Spis treści

- [JsonSchema Validation](#json_schema_validation)

# 📝Opis

## 📄JsonSchema Validation <a name="json_schema_validation"></a>

1. Pobieramy dependency **Json Schema Validator** od **Rest Assured**:
    ```xml
    <!-- https://mvnrepository.com/artifact/io.rest-assured/json-schema-validator -->
    <dependency>
        <groupId>io.rest-assured</groupId>
        <artifactId>json-schema-validator</artifactId>
        <version>${jsonSchemaValidatorRestAssured.version}</version>
    </dependency>
    ```
2. W katalogu `src/test` tworzymy katalog o nazwie `resources`
3. W katalogu `scr/test/resources` tworzymy katalog o nazwie `schema`
4. W nim tworzymy plik, któr będzie przechowywał nasz schema np. `POST_create_board_schema.json`
5. Oto jego przykładowa zawartość:
    ```json
    {
      "$schema": "https://json-schema.org/draft/2020-12/schema",
      "type": "object",
      "additionalProperties": false,
      "required": [
        "id", "name", "desc", "descData", "closed", "idOrganization", "idEnterprise",
        "pinned", "url", "shortUrl", "prefs", "labelNames", "limits"
      ],
      "properties": {
        "id": { "type": "string" },
        "name": {
          "type": "string",
          "minLength": 1,
          "maxLength": 16384
        },
        "desc": {
          "type": "string",
          "minLength": 0,
          "maxLength": 16384
        },
        "descData": { "type": ["object", "null"] },
        "closed": { "type": "boolean" },
        "idOrganization": {
          "type": "string",
          "pattern": "^[0-9a-fA-F]{24}$"
        },
        "idEnterprise": { "type": ["string", "null"] },
        "pinned": { "type": "boolean" },
        "url": { "type": "string", "format": "uri" },
        "shortUrl": { "type": "string", "format": "uri" },
        "prefs": {
          "type": "object",
          "additionalProperties": false,
          "required": [
            "permissionLevel", "hideVotes", "voting", "comments", "invitations", "selfJoin",
            "cardCovers", "showCompleteStatus", "cardCounts", "isTemplate", "cardAging",
            "calendarFeedEnabled", "hiddenPluginBoardButtons", "switcherViews", "autoArchive",
            "background", "backgroundColor", "backgroundDarkColor", "backgroundImage",
            "backgroundDarkImage", "backgroundImageScaled", "backgroundTile", "backgroundBrightness",
            "sharedSourceUrl", "backgroundBottomColor", "backgroundTopColor", "canBePublic",
            "canBeEnterprise", "canBeOrg", "canBePrivate", "canInvite"
          ],
          "properties": {
            "permissionLevel": {
              "type": "string",
              "enum": ["org", "private", "public"]
            },
            "hideVotes": { "type": "boolean" },
            "voting": {
              "type": "string",
              "enum": ["org", "private", "public", "disabled"]
            },
            "comments": {
              "type": "string",
              "enum": ["disabled", "members", "observers", "org", "public"]
            },
            "invitations": {
              "type": "string",
              "enum": ["members", "admins"]
            },
            "selfJoin": { "type": "boolean" },
            "cardCovers": { "type": "boolean" },
            "showCompleteStatus": { "type": "boolean" },
            "cardCounts": { "type": "boolean" },
            "isTemplate": { "type": "boolean" },
            "cardAging": {
              "type": "string",
              "enum": ["pirate", "regular"]
            },
            "calendarFeedEnabled": { "type": "boolean" },
            "hiddenPluginBoardButtons": {
              "type": "array",
              "items": { "type": "string" }
            },
            "switcherViews": {
              "type": "array",
              "items": {
                "type": "object",
                "required": ["viewType", "enabled"],
                "properties": {
                  "viewType": { "type": "string" },
                  "enabled": { "type": "boolean" }
                },
                "additionalProperties": false
              }
            },
            "autoArchive": { "type": ["boolean", "null"] },
            "background": {
              "type": "string",
              "enum": ["blue", "orange", "green", "red", "purple", "pink", "lime", "sky", "grey"]
            },
            "backgroundColor": { "type": "string" },
            "backgroundDarkColor": { "type": ["string", "null"] },
            "backgroundImage": { "type": ["string", "null"] },
            "backgroundDarkImage": { "type": ["string", "null"] },
            "backgroundImageScaled": { "type": ["array", "null"] },
            "backgroundTile": { "type": "boolean" },
            "backgroundBrightness": { "type": "string" },
            "sharedSourceUrl": { "type": ["string", "null"] },
            "backgroundBottomColor": { "type": "string" },
            "backgroundTopColor": { "type": "string" },
            "canBePublic": { "type": "boolean" },
            "canBeEnterprise": { "type": "boolean" },
            "canBeOrg": { "type": "boolean" },
            "canBePrivate": { "type": "boolean" },
            "canInvite": { "type": "boolean" }
          }
        },
        "labelNames": {
          "type": "object",
          "additionalProperties": { "type": "string" }
        },
        "limits": {
          "type": "object",
          "additionalProperties": false
        }
      }
    }
    ```
6. W klasie `TestBase` definiujemy zmienną z bazową ścieżką do naszych schematów:
    ```java
    public class TestBase {
        // Base path to all schemas
        protected static final String baseSchemaPath = "src/test/resources/schema";
    }
    ```
7. W klasie z naszymi testami np. `POST_CreateBoardTest` definiujemy zmienne ze ścieżkami do naszych schematów np.:
    ```java
    public class POST_CreateBoardTest extends TestBase {
    
        private final File postCreateBoardSchema = Paths.get(baseSchemaPath, "boards", "POST_create_board_schema.json").toFile();
        private final File getGetBoardSchema = Paths.get(baseSchemaPath, "boards", "GET_get_board.json").toFile();
    }
    ```
8. W samym teście wywołujemy metodę o nazwie `matchesJsonSchema()`, która odpowiada za sprawdzanie, czy nasz response
   jest zgodny z przekazanym Json Schema:
    ```java
    responsePost.then().assertThat().body(matchesJsonSchema(postCreateBoardSchema));
    ```

---
