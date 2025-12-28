package dto.emoji.list_available_emoji;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import dto.emoji.list_available_emoji.sprite_sheets.Twitter;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@JsonIgnoreProperties(ignoreUnknown = false)
public class SpriteSheets {

    // ==========================================================================================================
    // FIELDS
    // ==========================================================================================================

    // NOTE:
    // These variables are also used to call the name of an ignored, redundant field when comparing objects using AssertJ.

    public static final String FIELD_TWITTER = "twitter";

    // ==========================================================================================================
    // FIELDS – VALIDATION CONSTRAINTS
    // ==========================================================================================================

    @NotNull
    @Valid // <-- validates nested fields if object exists
    @JsonProperty(FIELD_TWITTER)
    public Twitter twitter;

    // ==========================================================================================================
    // CONSTRUCTORS
    // ==========================================================================================================

    // Empty constructor - needed to be able to assign values manually
    public SpriteSheets() {
    }
}
