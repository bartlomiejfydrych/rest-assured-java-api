package payloads.labels;

import enums.query_parameters_values.labels.common.Color;
import payloads.BasePayload;

import java.util.HashMap;
import java.util.Map;

import static enums.query_parameters.labels.LabelBaseQueryParameters.*;

public class PUT_UpdateLabelPayload extends BasePayload {

    // ==========================================================================================================
    // FIELDS – QUERY PARAMETERS
    // ==========================================================================================================

    private final String name;
    private final String color;

    // ==========================================================================================================
    // HELPER METHOD – CONVERTS A CLASS OBJECT TO QUERY PARAMETER MAP
    // ==========================================================================================================

    public Map<String, Object> toQueryParams() {

        Map<String, Object> params = new HashMap<>();

        putIfNotNull(params, NAME, name);
        putIfNotNull(params, COLOR, color);

        return params;
    }

    // ==========================================================================================================
    // EXAMPLE OF USE
    // ==========================================================================================================

    /*

    PUT_UpdateLabelPayload payload = new PUT_UpdateLabelPayload.Builder()
        .setName("Name")
        .setColor("Color")
        .build();

    Map<String, Object> queryParams = payload.toQueryParams();

    */

    // ==========================================================================================================
    // PRIVATE CONSTRUCTOR (ACCESSIBLE ONLY THROUGH BUILDER)
    // ==========================================================================================================

    private PUT_UpdateLabelPayload(Builder builder) {
        this.name = builder.name;
        this.color = builder.color;
    }

    // ==========================================================================================================
    // GETTERS
    // ==========================================================================================================

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    // ==========================================================================================================
    // BUILDER
    // ==========================================================================================================

    public static class Builder {
        private String name;
        private String color;

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        // COLOR – START

        public Builder setColor(String color) {
            this.color = color;
            return this;
        }

        public Builder setColor(Color color) {
            this.color = color != null ? color.value() : null;
            return this;
        }

        // COLOR – END

        public PUT_UpdateLabelPayload build() {
            return new PUT_UpdateLabelPayload(this);
        }
    }
}
