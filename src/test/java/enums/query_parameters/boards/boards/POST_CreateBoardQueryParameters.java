package enums.query_parameters.boards.boards;

import enums.query_parameters.interfaces.boards.POST_CreateBoardQueryParam;

public enum POST_CreateBoardQueryParameters implements POST_CreateBoardQueryParam {

    // ==========================================================================================================
    // ENUMS
    // ==========================================================================================================

    DEFAULT_LABELS("defaultLabels"),
    DEFAULT_LISTS("defaultLists"),
    ID_BOARD_SOURCE("idBoardSource"),
    KEEP_FROM_SOURCE("keepFromSource"),
    POWER_UPS("powerUps"),

    PREFS_PERMISSION_LEVEL("prefs_permissionLevel"),
    PREFS_VOTING("prefs_voting"),
    PREFS_COMMENTS("prefs_comments"),
    PREFS_INVITATIONS("prefs_invitations"),
    PREFS_SELF_JOIN("prefs_selfJoin"),
    PREFS_CARD_COVERS("prefs_cardCovers"),
    PREFS_BACKGROUND("prefs_background"),
    PREFS_CARD_AGING("prefs_cardAging");

    // ==========================================================================================================
    // INTERNAL ENUM VALUE
    // ==========================================================================================================

    private final String key;

    // ==========================================================================================================
    // CONSTRUCTOR
    // ==========================================================================================================

    POST_CreateBoardQueryParameters(String key) {
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
