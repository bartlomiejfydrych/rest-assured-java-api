package dto.emoji.list_available_emoji;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import dto.emoji.list_available_emoji.sprite_sheets.Twitter;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@JsonIgnoreProperties(ignoreUnknown = false)
public class SpriteSheets {

    @NotNull
    @Valid
    @JsonProperty("twitter")
    public Twitter twitter;

    public SpriteSheets() {
    }
}
