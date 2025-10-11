package payloads.labels;

import java.util.HashMap;
import java.util.Map;

public class PUT_UpdateLabelPayload {

    // ----------------
    // Query parameters
    // ----------------

    private final String name;
    private final String color;

    // -----------------------------------------
    // Helper method – conversion to queryParams
    // -----------------------------------------

    public Map<String, Object> toQueryParams() {
        Map<String, Object> params = new HashMap<>();

        if (name != null) params.put("name", name);
        if (color != null) params.put("color", color);

        return params;
    }

    // --------------
    // Example of use
    // --------------

    /*

    PUT_UpdateLabelPayload payload = new PUT_UpdateLabelPayload.Builder()
        .setName("Name")
        .setColor("Color")
        .build();

    Map<String, Object> queryParams = payload.toQueryParams();

    */

    // -----------------------------------------------------
    // Private constructor (accessible only through builder)
    // -----------------------------------------------------

    private PUT_UpdateLabelPayload(Builder builder) {
        this.name = builder.name;
        this.color = builder.color;
    }

    // -------
    // Builder
    // -------

    public static class Builder {
        private String name;
        private String color;

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setColor(String color) {
            this.color = color;
            return this;
        }

        public PUT_UpdateLabelPayload build() {
            return new PUT_UpdateLabelPayload(this);
        }
    }
}
