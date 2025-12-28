package dto.labels;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = false)
public class PUT_UpdateFieldOnLabelDto extends LabelBaseDto {

    // ==========================================================================================================
    // CONSTRUCTORS
    // ==========================================================================================================

    @JsonCreator
    public PUT_UpdateFieldOnLabelDto(
            @JsonProperty(value = FIELD_ID, required = true) String id,
            @JsonProperty(value = FIELD_ID_BOARD, required = true) String idBoard,
            @JsonProperty(value = FIELD_NAME, required = true) String name,
            @JsonProperty(value = FIELD_COLOR) String color,
            @JsonProperty(value = FIELD_USES, required = true) Integer uses
    ) {
        super(id, idBoard, name, color, uses);
    }

    // Empty constructor - needed to be able to assign values manually
    public PUT_UpdateFieldOnLabelDto() {
        super();
    }
}
