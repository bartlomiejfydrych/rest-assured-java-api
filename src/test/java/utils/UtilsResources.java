package utils;

import static utils.UtilsFile.readResourceFileAsString;
import static utils.response.UtilsResponseDeserializer.deserializeJson;

public final class UtilsResources {

    // ==========================================================================================================
    // CONSTRUCTOR
    // ==========================================================================================================

    private UtilsResources() {
    }

    // ==========================================================================================================
    // METHODS – MAIN
    // ==========================================================================================================

    public static <T> T readJsonFileAsObject(String resourcePath, Class<T> clazz) {
        String json = readResourceFileAsString(resourcePath);
        return deserializeJson(json, clazz);
    }
}
