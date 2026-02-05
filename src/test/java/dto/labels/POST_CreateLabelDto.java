package dto.labels;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;

@JsonIgnoreProperties(ignoreUnknown = false)
public class POST_CreateLabelDto extends LabelBaseDto {

    // ==========================================================================================================
    // FIELDS
    // ==========================================================================================================

    // NOTE:
    // These variables are also used to call the name of an ignored, redundant field when comparing objects using AssertJ.

    public static final String FIELD_LIMITS = "limits";

    // ==========================================================================================================
    // FIELDS – VALIDATION CONSTRAINTS
    // ==========================================================================================================

    // The @Valid annotation returns warnings for unspecified objects. It should only be uncommented once we have a specified object type.
    // @Valid // <-- validates nested fields if object exists
    public Object limits;

    // ==========================================================================================================
    // CONSTRUCTORS
    // ==========================================================================================================

    @JsonCreator
    public POST_CreateLabelDto(
            @JsonProperty(value = FIELD_ID, required = true) String id,
            @JsonProperty(value = FIELD_ID_BOARD, required = true) String idBoard,
            @JsonProperty(value = FIELD_NAME, required = true) String name,
            @JsonProperty(value = FIELD_COLOR) String color,
            @JsonProperty(value = FIELD_USES, required = true) Integer uses,
            @JsonProperty(value = FIELD_LIMITS, required = true) Object limits
    ) {
        super(id, idBoard, name, color, uses);
        this.limits = limits;
    }

    // Empty constructor - needed to be able to assign values manually
    public POST_CreateLabelDto() {
        super();
    }
}
