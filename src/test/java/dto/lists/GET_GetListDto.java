package dto.lists;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import dto.lists.list.DataSource;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@JsonIgnoreProperties(ignoreUnknown = false)
public class GET_GetListDto extends ListBaseDto {

    public static final String FIELD_TYPE = "type";
    public static final String FIELD_DATASOURCE = "datasource";

    public String type;

    @Valid
    @NotNull
    public DataSource datasource;

    @JsonCreator
    public GET_GetListDto(
            @JsonProperty(value = "id", required = true) String id,
            @JsonProperty(value = "name", required = true) String name,
            @JsonProperty(value = "closed", required = true) Boolean closed,
            @JsonProperty(value = "color", required = true) String color,
            @JsonProperty(value = "idBoard", required = true) String idBoard,
            @JsonProperty(value = "pos", required = true) Long pos,
            @JsonProperty(value = FIELD_TYPE, required = true) String type,
            @JsonProperty(value = FIELD_DATASOURCE, required = true) DataSource datasource
    ) {
        super(id, name, closed, color, idBoard, pos);
        this.type = type;
        this.datasource = datasource;
    }

    public GET_GetListDto() {
        super();
    }
}
