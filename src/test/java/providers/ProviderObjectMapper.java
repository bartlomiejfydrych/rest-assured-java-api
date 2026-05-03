package providers;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.cfg.CoercionAction;
import com.fasterxml.jackson.databind.cfg.CoercionInputShape;
import com.fasterxml.jackson.databind.type.LogicalType;

public final class ProviderObjectMapper {

    // ==========================================================================================================
    // FIELDS
    // ==========================================================================================================

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true)
            .configure(DeserializationFeature.FAIL_ON_MISSING_CREATOR_PROPERTIES, true)
            .configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, true);

    static {
        OBJECT_MAPPER.coercionConfigFor(LogicalType.Textual)
                .setCoercion(CoercionInputShape.Integer, CoercionAction.Fail)
                .setCoercion(CoercionInputShape.Float, CoercionAction.Fail)
                .setCoercion(CoercionInputShape.Boolean, CoercionAction.Fail);
    }

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
