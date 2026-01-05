package enums.query_parameters.boards;

import enums.query_parameters.interfaces.boards.PUT_UpdateBoardQueryParam;

public enum PUT_UpdateBoardQueryParameters implements PUT_UpdateBoardQueryParam {

    // ==========================================================================================================
    // ENUMS
    // ==========================================================================================================

    CLOSED("closed"),
    SUBSCRIBED("subscribed"),

    PREFS_PERMISSION_LEVEL("prefs/permissionLevel"),
    PREFS_SELF_JOIN("prefs/selfJoin"),
    PREFS_CARD_COVERS("prefs/cardCovers"),
    PREFS_HIDE_VOTES("prefs/hideVotes"),
    PREFS_INVITATIONS("prefs/invitations"),
    PREFS_VOTING("prefs/voting"),
    PREFS_COMMENTS("prefs/comments"),
    PREFS_BACKGROUND("prefs/background"),
    PREFS_CARD_AGING("prefs/cardAging"),
    PREFS_CALENDAR_FEED_ENABLED("prefs/calendarFeedEnabled");

    // ==========================================================================================================
    // INTERNAL ENUM VALUE
    // ==========================================================================================================

    private final String key;

    // ==========================================================================================================
    // CONSTRUCTOR
    // ==========================================================================================================

    PUT_UpdateBoardQueryParameters(String key) {
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
