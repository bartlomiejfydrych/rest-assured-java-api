package providers;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public final class ProviderObjectMapper {

    // ==========================================================================================================
    // FIELDS
    // ==========================================================================================================

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true)
            .configure(DeserializationFeature.FAIL_ON_MISSING_CREATOR_PROPERTIES, true)
            .configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, true);

    // ==========================================================================================================
    // CONSTRUCTOR
    // ==========================================================================================================

    private ProviderObjectMapper() {
    }

    // ==========================================================================================================
    // METHODS – MAIN
    // ==========================================================================================================

    public static ObjectMapper getObjectMapper() {
        return OBJECT_MAPPER;
    }
}
