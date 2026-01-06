package enums.query_parameters.labels;

import enums.query_parameters.interfaces.labels.POST_CreateLabelQueryParam;
import enums.query_parameters.interfaces.labels.PUT_UpdateLabelQueryParam;

public enum LabelBaseQueryParameters implements POST_CreateLabelQueryParam, PUT_UpdateLabelQueryParam {

    // ==========================================================================================================
    // ENUMS
    // ==========================================================================================================

    // -----------------------
    // COMMON QUERY PARAMETERS
    // -----------------------

    NAME("name"),
    COLOR("color");

    // ==========================================================================================================
    // INTERNAL ENUM VALUE
    // ==========================================================================================================

    private final String key;

    // ==========================================================================================================
    // CONSTRUCTOR
    // ==========================================================================================================

    LabelBaseQueryParameters(String key) {
        this.key = key;
    }

    // ==========================================================================================================
    // QUERY PARAMETER ACCESSOR
    // ==========================================================================================================

    @Override
    public String key() {
        return key;
    }
}
