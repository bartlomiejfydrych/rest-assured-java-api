package dto.emoji.list_available_emoji.sprite_sheets.twitter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.net.URL;

@JsonIgnoreProperties(ignoreUnknown = false)
public class SpriteSheetEntry {

    @NotNull
    @Valid
    @JsonProperty("url")
    public URL url;

    public SpriteSheetEntry() {
    }
}
