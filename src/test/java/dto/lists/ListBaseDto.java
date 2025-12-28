package dto.lists;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

@JsonIgnoreProperties(ignoreUnknown = false)
public class ListBaseDto {

    // ==========================================================================================================
    // FIELDS
    // ==========================================================================================================

    // NOTE:
    // These variables are also used to call the name of an ignored, redundant field when comparing objects using AssertJ.

    public static final String FIELD_ID = "id";
    public static final String FIELD_NAME = "name";
    public static final String FIELD_CLOSED = "closed";
    public static final String FIELD_COLOR = "color";
    public static final String FIELD_ID_BOARD = "idBoard";
    public static final String FIELD_POS = "pos";

    // ==========================================================================================================
    // FIELDS – VALIDATION CONSTRAINTS
    // ==========================================================================================================

    @NotNull
    public String id;

    @NotNull
    public String name;

    @NotNull
    public Boolean closed;

    public String color;

    @NotNull
    public String idBoard;

    @NotNull
    public Long pos;

    // ==========================================================================================================
    // CONSTRUCTORS
    // ==========================================================================================================

    @JsonCreator
    public ListBaseDto(
            @JsonProperty(value = FIELD_ID, required = true) String id,
            @JsonProperty(value = FIELD_NAME, required = true) String name,
            @JsonProperty(value = FIELD_CLOSED, required = true) Boolean closed,
            @JsonProperty(value = FIELD_COLOR, required = true) String color,
            @JsonProperty(value = FIELD_ID_BOARD, required = true) String idBoard,
            @JsonProperty(value = FIELD_POS, required = true) Long pos
    ) {
        this.id = id;
        this.name = name;
        this.closed = closed;
        this.color = color;
        this.idBoard = idBoard;
        this.pos = pos;
    }

    // Empty constructor - needed to be able to assign values manually
    public ListBaseDto() {
    }
}
