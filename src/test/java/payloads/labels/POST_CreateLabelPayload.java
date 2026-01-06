package payloads.labels;

import payloads.BasePayload;

import java.util.HashMap;
import java.util.Map;

import static enums.query_parameters.labels.LabelBaseQueryParameters.*;
import static enums.query_parameters.labels.POST_CreateLabelQueryParameters.*;

public class POST_CreateLabelPayload extends BasePayload {

    // ==========================================================================================================
    // FIELDS – QUERY PARAMETERS
    // ==========================================================================================================

    private final String idBoard;
    private final String name;
    private final String color;

    // ==========================================================================================================
    // HELPER METHOD – CONVERTS A CLASS OBJECT TO QUERY PARAMETER MAP
    // ==========================================================================================================

    public Map<String, Object> toQueryParams() {

        Map<String, Object> params = new HashMap<>();

        putIfNotNull(params, ID_BOARD, idBoard);
        putIfNotNull(params, NAME, name);
        putIfNotNull(params, COLOR, color);

        return params;
    }

    // ==========================================================================================================
    // EXAMPLE OF USE
    // ==========================================================================================================

    /*

    POST_CreateLabelPayload payload = new POST_CreateLabelPayload.Builder()
        .setIdBoard("1")
        .setName("Name")
        .setColor("Color")
        .build();

    Map<String, Object> queryParams = payload.toQueryParams();

    */

    // ==========================================================================================================
    // PRIVATE CONSTRUCTOR (ACCESSIBLE ONLY THROUGH BUILDER)
    // ==========================================================================================================

    private POST_CreateLabelPayload(Builder builder) {
        this.idBoard = builder.idBoard;
        this.name = builder.name;
        this.color = builder.color;
    }

    // ==========================================================================================================
    // BUILDER
    // ==========================================================================================================

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
