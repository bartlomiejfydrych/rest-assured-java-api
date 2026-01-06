package payloads.lists;

import payloads.BasePayload;

import java.util.HashMap;
import java.util.Map;

import static enums.query_parameters.lists.lists.ListBaseQueryParameters.*;
import static enums.query_parameters.lists.lists.PUT_UpdateListQueryParameters.*;

public class PUT_UpdateListPayload extends BasePayload {

    // ==========================================================================================================
    // FIELDS – QUERY PARAMETERS
    // ==========================================================================================================

    private final String name;
    private final Boolean closed;
    private final String idBoard;
    private final Object pos;   // Can be String or Long (Number)
    private final Boolean subscribed;

    // ==========================================================================================================
    // HELPER METHOD – CONVERTS A CLASS OBJECT TO QUERY PARAMETER MAP
    // ==========================================================================================================

    public Map<String, Object> toQueryParams() {

        Map<String, Object> params = new HashMap<>();

        putIfNotNull(params, NAME, name);
        putIfNotNull(params, CLOSED, closed);
        putIfNotNull(params, ID_BOARD, idBoard);
        putIfNotNull(params, POS, pos);
        putIfNotNull(params, SUBSCRIBED, subscribed);

        return params;
    }

    // ==========================================================================================================
    // EXAMPLE OF USE
    // ==========================================================================================================

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

    // ==========================================================================================================
    // PRIVATE CONSTRUCTOR (ACCESSIBLE ONLY THROUGH BUILDER)
    // ==========================================================================================================

    private PUT_UpdateListPayload(Builder builder) {
        this.idBoard = builder.idBoard;
        this.name = builder.name;
        this.closed = builder.closed;
        this.pos = builder.pos;
        this.subscribed = builder.subscribed;
    }

    // ==========================================================================================================
    // BUILDER
    // ==========================================================================================================

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
