package dto.lists;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import dto.lists.list.DataSource;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@JsonIgnoreProperties(ignoreUnknown = false)
public class POST_CreateNewListDto extends ListBaseDto {

    // Note:
    // This variable is used to call it as the name of an ignored, redundant field when comparing objects using AssertJ.
    public static final String FIELD_LIMITS = "limits";

    public String type;

    @Valid
    @NotNull
    public DataSource datasource;

    @Valid
    public Object limits;

    @JsonCreator
    public POST_CreateNewListDto(
            @JsonProperty(value = "id", required = true) String id,
            @JsonProperty(value = "name", required = true) String name,
            @JsonProperty(value = "closed", required = true) Boolean closed,
            @JsonProperty(value = "color", required = true) String color,
            @JsonProperty(value = "idBoard", required = true) String idBoard,
            @JsonProperty(value = "pos", required = true) Long pos,
            @JsonProperty(value = "type", required = true) String type,
            @JsonProperty(value = "datasource", required = true) DataSource datasource,
            @JsonProperty(value = FIELD_LIMITS, required = true) Object limits
    ) {
        super(id, name, closed, color, idBoard, pos);
        this.type = type;
        this.datasource = datasource;
        this.limits = limits;
    }

    public POST_CreateNewListDto() {
        super();
    }
}
