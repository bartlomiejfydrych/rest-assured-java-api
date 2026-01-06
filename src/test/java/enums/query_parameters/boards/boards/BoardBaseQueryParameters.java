package enums.query_parameters.boards.boards;

import enums.query_parameters.interfaces.boards.POST_CreateBoardQueryParam;
import enums.query_parameters.interfaces.boards.PUT_UpdateBoardQueryParam;

public enum BoardBaseQueryParameters implements POST_CreateBoardQueryParam, PUT_UpdateBoardQueryParam {

    // ==========================================================================================================
    // ENUMS
    // ==========================================================================================================

    // -----------------------
    // COMMON QUERY PARAMETERS
    // -----------------------

    NAME("name"),
    DESC("desc"),
    ID_ORGANIZATION("idOrganization");

    // ==========================================================================================================
    // INTERNAL ENUM VALUE
    // ==========================================================================================================

    private final String key;

    // ==========================================================================================================
    // CONSTRUCTOR
    // ==========================================================================================================

    BoardBaseQueryParameters(String key) {
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
