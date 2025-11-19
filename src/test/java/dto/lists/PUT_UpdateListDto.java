package dto.lists;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;

@JsonIgnoreProperties(ignoreUnknown = false)
public class PUT_UpdateListDto extends ListBaseDto {

    public static final String FIELD_SUBSCRIBED = "subscribed";

    @Valid
    @JsonProperty(FIELD_SUBSCRIBED)
    public Boolean subscribed;

    @JsonCreator
    public PUT_UpdateListDto(
            @JsonProperty(value = "id", required = true) String id,
            @JsonProperty(value = "name", required = true) String name,
            @JsonProperty(value = "closed", required = true) Boolean closed,
            @JsonProperty(value = "color", required = true) String color,
            @JsonProperty(value = "idBoard", required = true) String idBoard,
            @JsonProperty(value = "pos", required = true) Long pos
    ) {
        super(id, name, closed, color, idBoard, pos);
    }

    public PUT_UpdateListDto() {
        super();
    }
}
