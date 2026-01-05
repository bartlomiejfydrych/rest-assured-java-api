package payloads;

import enums.query_parameters.interfaces.QueryParam;

import java.util.Map;

public abstract class BasePayload {

    // ==========================================================================================================
    // METHODS – MAIN
    // ==========================================================================================================

    protected void putIfNotNull(
            Map<String, Object> params,
            QueryParam param,
            Object value
    ) {
        if (value != null) {
            params.put(param.key(), value);
        }
    }
}
