package endpoints.lists;

import endpoints.BaseEndpoint;

public class ListsBaseEndpoint extends BaseEndpoint {

    // ==========================================================================================================
    // ENDPOINTS (URL)
    // ==========================================================================================================

    public static final String ENDPOINT_LISTS = "/lists";

    // ==========================================================================================================
    // METHODS – MAIN
    // ==========================================================================================================

    protected static String listById(String listId) {
        return ENDPOINT_LISTS + "/" + listId;
    }
}
