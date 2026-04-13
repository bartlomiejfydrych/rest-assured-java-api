package endpoints.labels;

import endpoints.BaseEndpoint;

public class LabelsBaseEndpoint extends BaseEndpoint {

    // ==========================================================================================================
    // ENDPOINTS (URL)
    // ==========================================================================================================

    public static final String ENDPOINT_LABELS = "/labels";

    // ==========================================================================================================
    // METHODS – MAIN
    // ==========================================================================================================

    protected static String labelById(String labelId) {
        return ENDPOINT_LABELS + "/" + labelId;
    }
}
