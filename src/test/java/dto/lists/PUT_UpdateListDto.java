package dto.lists;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

@JsonIgnoreProperties(ignoreUnknown = false)
public class PUT_UpdateListDto extends ListBaseDto {

    public static final String FIELD_SUBSCRIBED = "subscribed";

    @NotNull
    public Boolean subscribed;

    @JsonCreator
    public PUT_UpdateListDto(
            @JsonProperty(value = "id", required = true) String id,
            @JsonProperty(value = "name", required = true) String name,
            @JsonProperty(value = "closed", required = true) Boolean closed,
            @JsonProperty(value = "color", required = true) String color,
            @JsonProperty(value = "idBoard", required = true) String idBoard,
            @JsonProperty(value = "pos", required = true) Long pos,
            @JsonProperty(value = FIELD_SUBSCRIBED, required = true) Boolean subscribed
    ) {
        super(id, name, closed, color, idBoard, pos);
        this.subscribed = subscribed;
    }

    public PUT_UpdateListDto() {
        super();
    }
}
