package endpoints.emoji;

import endpoints.BaseEndpoint;

public class EmojiBaseEndpoint extends BaseEndpoint {

    // ==========================================================================================================
    // ENDPOINTS (URL)
    // ==========================================================================================================

    protected static final String ENDPOINT_EMOJI = "/emoji";

    // ==========================================================================================================
    // METHODS – MAIN
    // ==========================================================================================================

    protected static String emojiById(String emojiId) {
        return ENDPOINT_EMOJI + "/" + emojiId;
    }
}
