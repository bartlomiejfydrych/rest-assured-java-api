package dto.lists;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import dto.lists.list.DataSource;

@JsonIgnoreProperties(ignoreUnknown = false)
public class GET_GetListDto extends ListBaseDto {

    @JsonCreator
    public GET_GetListDto(
            @JsonProperty(value = "id", required = true) String id,
            @JsonProperty(value = "name", required = true) String name,
            @JsonProperty(value = "closed", required = true) Boolean closed,
            @JsonProperty(value = "color") String color,
            @JsonProperty(value = "idBoard", required = true) String idBoard,
            @JsonProperty(value = "pos", required = true) Long pos,
            @JsonProperty(value = "type") String type,
            @JsonProperty(value = "datasource", required = true) DataSource datasource
    ) {
        super(id, name, closed, color, idBoard, pos, type, datasource);
    }

    public GET_GetListDto() {
        super();
    }
}
