package payloads.emoji;

import payloads.BasePayload;

import java.util.HashMap;
import java.util.Map;

import static enums.query_parameters.emoji.GET_ListAvailableEmojiQueryParameters.*;

public class GET_ListAvailableEmojiPayload extends BasePayload {

    // ==========================================================================================================
    // FIELDS – QUERY PARAMETERS
    // ==========================================================================================================

    private final String locale;
    private final Boolean spritesheets;

    // ==========================================================================================================
    // HELPER METHOD – CONVERTS A CLASS OBJECT TO QUERY PARAMETER MAP
    // ==========================================================================================================

    public Map<String, Object> toQueryParams() {

        Map<String, Object> params = new HashMap<>();

        putIfNotNull(params, LOCALE, locale);
        putIfNotNull(params, SPRITESHEETS, spritesheets);

        return params;
    }

    // ==========================================================================================================
    // EXAMPLE OF USE
    // ==========================================================================================================

    /*

    GET_ListAvailableEmojiPayload payload = new GET_ListAvailableEmojiPayload.Builder()
        .setLocale("Text")
        .setSpritesheets(true)
        .build();

    Map<String, Object> queryParams = payload.toQueryParams();

    */

    // ==========================================================================================================
    // PRIVATE CONSTRUCTOR (ACCESSIBLE ONLY THROUGH BUILDER)
    // ==========================================================================================================

    private GET_ListAvailableEmojiPayload(Builder builder) {
        this.locale = builder.locale;
        this.spritesheets = builder.spritesheets;
    }

    // ==========================================================================================================
    // BUILDER
    // ==========================================================================================================

    public static class Builder {
        private String locale;
        private Boolean spritesheets;

        public Builder setLocale(String locale) {
            this.locale = locale;
            return this;
        }

        public Builder setSpritesheets(Boolean spritesheets) {
            this.spritesheets = spritesheets;
            return this;
        }

        public GET_ListAvailableEmojiPayload build() {
            return new GET_ListAvailableEmojiPayload(this);
        }
    }
}
