package utils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public final class UtilsFile {

    // ==========================================================================================================
    // METHODS – MAIN
    // ==========================================================================================================

    public static String readResourceFileAsString(String resourcePath) {
        try (InputStream is = UtilsFile.class
                .getClassLoader()
                .getResourceAsStream(resourcePath)) {

            if (is == null) {
                throw new IllegalArgumentException("Resource not found: " + resourcePath);
            }

            return new String(is.readAllBytes(), StandardCharsets.UTF_8);

        } catch (IOException e) {
            throw new RuntimeException("Failed to read resource: " + resourcePath, e);
        }
    }
}

/*
########################################################################################################################
MY ADDITIONAL NOTES
########################################################################################################################

--------
NEW PATH
--------

BEFORE REFACTOR:

String expectedResponseJsonFile = readResourceFileAsString(
        "src/test/resources/tests/expected_responses/emoji/GET_ListAvailableEmojiExpected/P4_ExpectedGetListAvailableEmojiResponse.json"
);

AFTER REFACTOR:

String expectedResponseJsonFile = readResourceFileAsString(
        "tests/expected_responses/emoji/GET_ListAvailableEmojiExpected/P4_ExpectedGetListAvailableEmojiResponse.json"
);

-----------------------------
BONUS – REST ASSURED USE-CASE
-----------------------------

String body = readResourceFileAsString("payloads/create-user.json")
        .replace("${username}", faker.name().username());

given()
    .body(body)
.when()
    .post("/users")
.then()
    .statusCode(201);

*/
