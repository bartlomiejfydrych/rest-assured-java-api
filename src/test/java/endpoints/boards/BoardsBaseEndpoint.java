package endpoints.boards;

import endpoints.BaseEndpoint;

public class BoardsBaseEndpoint extends BaseEndpoint {

    // ==========================================================================================================
    // ENDPOINTS (URL)
    // ==========================================================================================================

    protected static final String ENDPOINT_BOARDS = "/boards";

    // ==========================================================================================================
    // METHODS – MAIN
    // ==========================================================================================================

    protected static String boardById(String boardId) {
        return ENDPOINT_BOARDS + "/" + boardId;
    }
}
