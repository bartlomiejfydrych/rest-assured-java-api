package endpoints.emoji;

import endpoints.BaseEndpoint;

public class EmojiBaseEndpoint extends BaseEndpoint {

    // ==========================================================================================================
    // ENDPOINTS (URL)
    // ==========================================================================================================

    public static final String ENDPOINT_EMOJI = "/emoji";

    // ==========================================================================================================
    // METHODS – MAIN
    // ==========================================================================================================

    protected static String emojiById(String emojiId) {
        return ENDPOINT_EMOJI + "/" + emojiId;
    }
}
