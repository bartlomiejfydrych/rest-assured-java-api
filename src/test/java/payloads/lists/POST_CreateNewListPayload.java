package payloads.lists;

import payloads.BasePayload;

import java.util.HashMap;
import java.util.Map;

import static enums.query_parameters.lists.lists.ListBaseQueryParameters.*;
import static enums.query_parameters.lists.lists.POST_CreateNewListQueryParameters.*;

public class POST_CreateNewListPayload extends BasePayload {

    // ==========================================================================================================
    // FIELDS – QUERY PARAMETERS
    // ==========================================================================================================

    private final String name;
    private final String idBoard;
    private final String idListSource;
    private final Object pos;   // Can be String or Long (Number)

    // ==========================================================================================================
    // HELPER METHOD – CONVERTS A CLASS OBJECT TO QUERY PARAMETER MAP
    // ==========================================================================================================

    public Map<String, Object> toQueryParams() {

        Map<String, Object> params = new HashMap<>();

        putIfNotNull(params, NAME, name);
        putIfNotNull(params, ID_BOARD, idBoard);
        putIfNotNull(params, ID_LIST_SOURCE, idListSource);
        putIfNotNull(params, POS, pos);

        return params;
    }

    // ==========================================================================================================
    // EXAMPLE OF USE
    // ==========================================================================================================

    /*

    POST_CreateNewListPayload payload = new POST_CreateNewListPayload.Builder()
        .setIdBoard("1")
        .setName("Name")
        .setIdListSource("2")
        .setPos("top")
        .build();

    Map<String, Object> queryParams = payload.toQueryParams();

    */

    // ==========================================================================================================
    // PRIVATE CONSTRUCTOR (ACCESSIBLE ONLY THROUGH BUILDER)
    // ==========================================================================================================

    private POST_CreateNewListPayload(Builder builder) {
        this.idBoard = builder.idBoard;
        this.name = builder.name;
        this.idListSource = builder.idListSource;
        this.pos = builder.pos;
    }

    // ==========================================================================================================
    // BUILDER
    // ==========================================================================================================

    public static class Builder {
        private String idBoard;
        private String name;
        private String idListSource;
        private Object pos;

        public Builder setIdBoard(String idBoard) {
            this.idBoard = idBoard;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setIdListSource(String idListSource) {
            this.idListSource = idListSource;
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

        public POST_CreateNewListPayload build() {
            return new POST_CreateNewListPayload(this);
        }
    }
}
