package payloads.labels;

import java.util.HashMap;
import java.util.Map;

public class POST_CreateLabelPayload {

    // ----------------
    // Query parameters
    // ----------------

    private final String idBoard;
    private final String name;
    private final String color;

    // -----------------------------------------
    // Helper method – conversion to queryParams
    // -----------------------------------------

    public Map<String, Object> toQueryParams() {
        Map<String, Object> params = new HashMap<>();

        if (idBoard != null) params.put("idBoard", idBoard);
        if (name != null) params.put("name", name);
        if (color != null) params.put("color", color);

        return params;
    }

    // --------------
    // Example of use
    // --------------

    /*

    POST_CreateLabelPayload payload = new POST_CreateLabelPayload.Builder()
        .setIdBoard("1")
        .setName("Name")
        .setColor("Color")
        .build();

    Map<String, Object> queryParams = payload.toQueryParams();

    */

    // -----------------------------------------------------
    // Private constructor (accessible only through builder)
    // -----------------------------------------------------

    private POST_CreateLabelPayload(Builder builder) {
        this.idBoard = builder.idBoard;
        this.name = builder.name;
        this.color = builder.color;
    }

    // -------
    // Builder
    // -------

    public static class Builder {
        private String idBoard;
        private String name;
        private String color;

        public Builder setIdBoard(String idBoard) {
            this.idBoard = idBoard;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setColor(String color) {
            this.color = color;
            return this;
        }

        public POST_CreateLabelPayload build() {
            return new POST_CreateLabelPayload(this);
        }
    }
}
