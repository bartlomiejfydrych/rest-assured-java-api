package dto.lists;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import dto.lists.list.DataSource;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@JsonIgnoreProperties(ignoreUnknown = false)
public class POST_CreateNewListDto extends ListBaseDto {

    // ==========================================================================================================
    // FIELDS
    // ==========================================================================================================

    // NOTE:
    // These variables are also used to call the name of an ignored, redundant field when comparing objects using AssertJ.

    public static final String FIELD_TYPE = "type";
    public static final String FIELD_DATASOURCE = "datasource";
    public static final String FIELD_LIMITS = "limits";

    // ==========================================================================================================
    // FIELDS – VALIDATION CONSTRAINTS
    // ==========================================================================================================

    public String type;

    @Valid // <-- validates nested fields if object exists
    @NotNull
    public DataSource datasource;

    @Valid // <-- validates nested fields if object exists
    public Object limits;

    // ==========================================================================================================
    // CONSTRUCTORS
    // ==========================================================================================================

    @JsonCreator
    public POST_CreateNewListDto(
            @JsonProperty(value = FIELD_ID, required = true) String id,
            @JsonProperty(value = FIELD_NAME, required = true) String name,
            @JsonProperty(value = FIELD_CLOSED, required = true) Boolean closed,
            @JsonProperty(value = FIELD_COLOR, required = true) String color,
            @JsonProperty(value = FIELD_ID_BOARD, required = true) String idBoard,
            @JsonProperty(value = FIELD_POS, required = true) Long pos,
            @JsonProperty(value = FIELD_TYPE, required = true) String type,
            @JsonProperty(value = FIELD_DATASOURCE, required = true) DataSource datasource,
            @JsonProperty(value = FIELD_LIMITS, required = true) Object limits
    ) {
        super(id, name, closed, color, idBoard, pos);
        this.type = type;
        this.datasource = datasource;
        this.limits = limits;
    }

    // Empty constructor - needed to be able to assign values manually
    public POST_CreateNewListDto() {
        super();
    }
}
