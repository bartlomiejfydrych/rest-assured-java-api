package dto.emoji;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import dto.emoji.list_available_emoji.Trello;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.Valid;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = false)
public class GET_ListAvailableEmojiDto {

    @NotNull
    @Valid
    public List<Trello> trello;

    @JsonCreator
    public GET_ListAvailableEmojiDto(
            @JsonProperty(value = "trello", required = true) List<Trello> trello
    ) {
        this.trello = trello;
    }

    public GET_ListAvailableEmojiDto() {
    }
}
