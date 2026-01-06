package enums.query_parameters.lists.lists;

import enums.query_parameters.interfaces.lists.POST_CreateNewListQueryParam;
import enums.query_parameters.interfaces.lists.PUT_UpdateListQueryParam;

public enum ListBaseQueryParameters implements POST_CreateNewListQueryParam, PUT_UpdateListQueryParam {

    // ==========================================================================================================
    // ENUMS
    // ==========================================================================================================

    // -----------------------
    // COMMON QUERY PARAMETERS
    // -----------------------

    ID_BOARD("idBoard"),
    NAME("name"),
    POS("pos");

    // ==========================================================================================================
    // INTERNAL ENUM VALUE
    // ==========================================================================================================

    private final String key;

    // ==========================================================================================================
    // CONSTRUCTOR
    // ==========================================================================================================

    ListBaseQueryParameters(String key) {
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
