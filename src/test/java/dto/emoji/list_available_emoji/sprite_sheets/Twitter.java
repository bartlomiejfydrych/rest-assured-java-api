package dto.emoji.list_available_emoji.sprite_sheets;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import dto.emoji.list_available_emoji.sprite_sheets.twitter.SpriteSheetEntry;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@JsonIgnoreProperties(ignoreUnknown = false)
public class Twitter {

    @NotNull
    @Valid
    @JsonProperty("16")
    public SpriteSheetEntry size16;

    @NotNull
    @Valid
    @JsonProperty("20")
    public SpriteSheetEntry size20;

    @NotNull
    @Valid
    @JsonProperty("32")
    public SpriteSheetEntry size32;

    @NotNull
    @Valid
    @JsonProperty("64")
    public SpriteSheetEntry size64;

    public Twitter() {
    }
}
