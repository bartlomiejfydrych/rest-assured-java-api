package payloads.lists;

import java.util.HashMap;
import java.util.Map;

public class POST_CreateNewListPayload {

    // ----------------
    // Query parameters
    // ----------------

    private final String idBoard;
    private final String name;
    private final String idListSource;
    private final String pos;

    // -----------------------------------------
    // Helper method – conversion to queryParams
    // -----------------------------------------

    public Map<String, Object> toQueryParams() {
        Map<String, Object> params = new HashMap<>();

        if (idBoard != null) params.put("idBoard", idBoard);
        if (name != null) params.put("name", name);
        if (idListSource != null) params.put("idListSource", idListSource);
        if (pos != null) params.put("pos", pos);

        return params;
    }

    // --------------
    // Example of use
    // --------------

    /*

    POST_CreateNewListPayload payload = new POST_CreateNewListPayload.Builder()
        .setIdBoard("1")
        .setName("Name")
        .setIdListSource("2")
        .setPos("top")
        .build();

    Map<String, Object> queryParams = payload.toQueryParams();

    */

    // -----------------------------------------------------
    // Private constructor (accessible only through builder)
    // -----------------------------------------------------

    private POST_CreateNewListPayload(Builder builder) {
        this.idBoard = builder.idBoard;
        this.name = builder.name;
        this.idListSource = builder.idListSource;
        this.pos = builder.pos;
    }

    // -------
    // Builder
    // -------

    public static class Builder {
        private String idBoard;
        private String name;
        private String idListSource;
        private String pos;

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

        public Builder setPos(String pos) {
            this.pos = pos;
            return this;
        }

        public POST_CreateNewListPayload build() {
            return new POST_CreateNewListPayload(this);
        }
    }
}
