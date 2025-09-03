package dto.labels;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import dto.labels.label.Limits;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@JsonIgnoreProperties(ignoreUnknown = false)
public class POST_CreateLabelDto {

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

    @Valid
    public Limits limits;

    @JsonCreator
    public POST_CreateLabelDto(
            @JsonProperty(value = "id", required = true) String id,
            @JsonProperty(value = "idBoard", required = true) String idBoard,
            @JsonProperty(value = "name", required = true) String name,
            @JsonProperty(value = "color") String color,
            @JsonProperty(value = "uses", required = true) Integer uses,
            @JsonProperty(value = "limits", required = true) Limits limits
    ) {
        this.id = id;
        this.idBoard = idBoard;
        this.name = name;
        this.color = color;
        this.uses = uses;
        this.limits = limits;
    }

    public POST_CreateLabelDto() {
    }
}
