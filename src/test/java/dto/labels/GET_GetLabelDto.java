package dto.labels;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = false)
public class GET_GetLabelDto extends LabelBaseDto {

    @JsonCreator
    public GET_GetLabelDto(
            @JsonProperty(value = "id", required = true) String id,
            @JsonProperty(value = "idBoard", required = true) String idBoard,
            @JsonProperty(value = "name", required = true) String name,
            @JsonProperty(value = "color") String color,
            @JsonProperty(value = "uses", required = true) Integer uses
    ) {
        super(id, idBoard, name, color, uses);
    }

    public GET_GetLabelDto() {
        super();
    }
}
