package dto.labels;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

@JsonIgnoreProperties(ignoreUnknown = false)
public class LabelBaseDto {

    // ==========================================================================================================
    // FIELDS
    // ==========================================================================================================

    // NOTE:
    // These variables are also used to call the name of an ignored, redundant field when comparing objects using AssertJ.

    public static final String FIELD_ID = "id";
    public static final String FIELD_ID_BOARD = "idBoard";
    public static final String FIELD_NAME = "name";
    public static final String FIELD_COLOR = "color";
    public static final String FIELD_USES = "uses";

    // ==========================================================================================================
    // FIELDS – VALIDATION CONSTRAINTS
    // ==========================================================================================================

    @NotNull
    public String id;

    @NotNull
    public String idBoard;

    @NotNull
    public String name;

    // color is "nullable: true" in the API documentation
    // so we don't use @NotNull here
    public String color;

    @NotNull
    public Integer uses;

    // ==========================================================================================================
    // CONSTRUCTORS
    // ==========================================================================================================

    @JsonCreator
    public LabelBaseDto(
            @JsonProperty(value = FIELD_ID, required = true) String id,
            @JsonProperty(value = FIELD_ID_BOARD, required = true) String idBoard,
            @JsonProperty(value = FIELD_NAME, required = true) String name,
            @JsonProperty(value = FIELD_COLOR) String color,
            @JsonProperty(value = FIELD_USES, required = true) Integer uses
    ) {
        this.id = id;
        this.idBoard = idBoard;
        this.name = name;
        this.color = color;
        this.uses = uses;
    }

    // Empty constructor - needed to be able to assign values manually
    public LabelBaseDto() {
    }
}
