package payloads.lists;

import java.util.HashMap;
import java.util.Map;

public class PUT_UpdateListPayload {

    // ----------------
    // Query parameters
    // ----------------

    private final String idBoard;
    private final String name;
    private final Boolean closed;
    private final Object pos;   // Can be String or Long (Number)
    private final Boolean subscribed;

    // -----------------------------------------
    // Helper method – conversion to queryParams
    // -----------------------------------------

    public Map<String, Object> toQueryParams() {
        Map<String, Object> params = new HashMap<>();

        if (idBoard != null) params.put("idBoard", idBoard);
        if (name != null) params.put("name", name);
        if (closed != null) params.put("closed", closed);
        if (pos != null) params.put("pos", pos);
        if (subscribed != null) params.put("subscribed", subscribed);

        return params;
    }

    // --------------
    // Example of use
    // --------------

    /*

    PUT_UpdateListPayload payload = new PUT_UpdateListPayload.Builder()
        .setIdBoard("1")
        .setName("Name")
        .setClosed(true)
        .setPos("top")
        .setSubscribed(false)
        .build();

    Map<String, Object> queryParams = payload.toQueryParams();

    */

    // -----------------------------------------------------
    // Private constructor (accessible only through builder)
    // -----------------------------------------------------

    private PUT_UpdateListPayload(Builder builder) {
        this.idBoard = builder.idBoard;
        this.name = builder.name;
        this.closed = builder.closed;
        this.pos = builder.pos;
        this.subscribed = builder.subscribed;
    }

    // -------
    // Builder
    // -------

    public static class Builder {
        private String idBoard;
        private String name;
        private Boolean closed;
        private Object pos;
        private Boolean subscribed;

        public Builder setIdBoard(String idBoard) {
            this.idBoard = idBoard;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setClosed(Boolean closed) {
            this.closed = closed;
            return this;
        }

        // Pos – START

        public Builder setPos(String pos) {
            this.pos = pos;
            return this;
        }

        public Builder setPos(Long pos) {
            this.pos = pos;
            return this;
        }

        // Pos – END

        public Builder setSubscribed(Boolean subscribed) {
            this.subscribed = subscribed;
            return this;
        }

        public PUT_UpdateListPayload build() {
            return new PUT_UpdateListPayload(this);
        }
    }
}
