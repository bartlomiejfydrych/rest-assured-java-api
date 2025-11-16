package dto.lists;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

@JsonIgnoreProperties(ignoreUnknown = false)
public class ListBaseDto {

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

    @JsonCreator
    public ListBaseDto(
            @JsonProperty(value = "id", required = true) String id,
            @JsonProperty(value = "name", required = true) String name,
            @JsonProperty(value = "closed", required = true) Boolean closed,
            @JsonProperty(value = "color", required = true) String color,
            @JsonProperty(value = "idBoard", required = true) String idBoard,
            @JsonProperty(value = "pos", required = true) Long pos
    ) {
        this.id = id;
        this.name = name;
        this.closed = closed;
        this.color = color;
        this.idBoard = idBoard;
        this.pos = pos;
    }

    public ListBaseDto() {
    }
}
