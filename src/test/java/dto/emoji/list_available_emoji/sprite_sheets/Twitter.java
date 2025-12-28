package dto.emoji.list_available_emoji.sprite_sheets;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import dto.emoji.list_available_emoji.sprite_sheets.twitter.SpriteSheetEntry;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@JsonIgnoreProperties(ignoreUnknown = false)
public class Twitter {

    // ==========================================================================================================
    // FIELDS
    // ==========================================================================================================

    // NOTE:
    // These variables are also used to call the name of an ignored, redundant field when comparing objects using AssertJ.

    public static final String FIELD_16 = "16";
    public static final String FIELD_20 = "20";
    public static final String FIELD_32 = "32";
    public static final String FIELD_64 = "64";

    // ==========================================================================================================
    // FIELDS – VALIDATION CONSTRAINTS
    // ==========================================================================================================

    @NotNull
    @Valid // <-- validates nested fields if object exists
    @JsonProperty(FIELD_16)
    public SpriteSheetEntry size16;

    @NotNull
    @Valid // <-- validates nested fields if object exists
    @JsonProperty(FIELD_20)
    public SpriteSheetEntry size20;

    @NotNull
    @Valid // <-- validates nested fields if object exists
    @JsonProperty(FIELD_32)
    public SpriteSheetEntry size32;

    @NotNull
    @Valid // <-- validates nested fields if object exists
    @JsonProperty(FIELD_64)
    public SpriteSheetEntry size64;

    // ==========================================================================================================
    // CONSTRUCTORS
    // ==========================================================================================================

    // Empty constructor - needed to be able to assign values manually
    public Twitter() {
    }
}
