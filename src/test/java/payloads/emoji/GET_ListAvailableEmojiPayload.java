package payloads.emoji;

import java.util.HashMap;
import java.util.Map;

public class GET_ListAvailableEmojiPayload {

    // ----------------
    // Query parameters
    // ----------------

    private final String locale;
    private final Boolean spritesheets;

    // -----------------------------------------
    // Helper method – conversion to queryParams
    // -----------------------------------------

    public Map<String, Object> toQueryParams() {
        Map<String, Object> params = new HashMap<>();

        if (locale != null) params.put("locale", locale);
        if (spritesheets != null) params.put("spritesheets", spritesheets);

        return params;
    }

    // --------------
    // Example of use
    // --------------

    /*

    GET_ListAvailableEmojiPayload payload = new GET_ListAvailableEmojiPayload.Builder()
        .setLocale("Text")
        .setSpritesheets(true)
        .build();

    Map<String, Object> queryParams = payload.toQueryParams();

    */

    // -----------------------------------------------------
    // Private constructor (accessible only through builder)
    // -----------------------------------------------------

    private GET_ListAvailableEmojiPayload(Builder builder) {
        this.locale = builder.locale;
        this.spritesheets = builder.spritesheets;
    }

    // -------
    // Builder
    // -------

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
