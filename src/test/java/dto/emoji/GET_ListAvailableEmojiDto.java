package dto.emoji;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import dto.emoji.list_available_emoji.SpriteSheets;
import dto.emoji.list_available_emoji.Trello;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.Valid;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = false)
public class GET_ListAvailableEmojiDto {

    // ==========================================================================================================
    // FIELDS
    // ==========================================================================================================

    // NOTE:
    // These variables are also used to call the name of an ignored, redundant field when comparing objects using AssertJ.

    public static final String FIELD_SPRITE_SHEETS = "spriteSheets";

    public static final String FIELD_TRELLO = "trello";

    // ==========================================================================================================
    // FIELDS – VALIDATION CONSTRAINTS
    // ==========================================================================================================

    @Valid // <-- validates nested fields if object exists
    @JsonProperty(FIELD_SPRITE_SHEETS)
    public SpriteSheets spriteSheets;

    @NotNull
    @Valid // <-- validates nested fields if object exists
    public List<Trello> trello;

    // ==========================================================================================================
    // CONSTRUCTORS
    // ==========================================================================================================

    @JsonCreator
    public GET_ListAvailableEmojiDto(
            @JsonProperty(value = FIELD_TRELLO, required = true) List<Trello> trello
    ) {
        this.trello = trello;
    }

    // Empty constructor - needed to be able to assign values manually
    public GET_ListAvailableEmojiDto() {
    }
}
