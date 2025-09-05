package dto.labels;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

@JsonIgnoreProperties(ignoreUnknown = false)
public class LabelBaseDto {

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

    @JsonCreator
    public LabelBaseDto(
            @JsonProperty(value = "id", required = true) String id,
            @JsonProperty(value = "idBoard", required = true) String idBoard,
            @JsonProperty(value = "name", required = true) String name,
            @JsonProperty(value = "color") String color,
            @JsonProperty(value = "uses", required = true) Integer uses
    ) {
        this.id = id;
        this.idBoard = idBoard;
        this.name = name;
        this.color = color;
        this.uses = uses;
    }

    public LabelBaseDto() {
    }
}
