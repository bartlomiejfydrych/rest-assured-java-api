package endpoints.boards;

import endpoints.BaseEndpoint;

public class BoardsBaseEndpoint extends BaseEndpoint {

    // ==========================================================================================================
    // ENDPOINTS (URL)
    // ==========================================================================================================

    public static final String ENDPOINT_BOARDS = "/boards";

    // ==========================================================================================================
    // METHODS – MAIN
    // ==========================================================================================================

    protected static String boardById(String boardId) {
        return ENDPOINT_BOARDS + "/" + boardId;
    }
}
