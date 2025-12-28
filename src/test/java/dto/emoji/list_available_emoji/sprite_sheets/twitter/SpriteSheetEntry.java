package dto.emoji.list_available_emoji.sprite_sheets.twitter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.net.URL;

@JsonIgnoreProperties(ignoreUnknown = false)
public class SpriteSheetEntry {

    // ==========================================================================================================
    // FIELDS
    // ==========================================================================================================

    // NOTE:
    // These variables are also used to call the name of an ignored, redundant field when comparing objects using AssertJ.

    public static final String FIELD_URL = "url";

    // ==========================================================================================================
    // FIELDS – VALIDATION CONSTRAINTS
    // ==========================================================================================================

    @NotNull
    @Valid // <-- validates nested fields if object exists
    @JsonProperty(FIELD_URL)
    public URL url;

    // ==========================================================================================================
    // CONSTRUCTORS
    // ==========================================================================================================

    // Empty constructor - needed to be able to assign values manually
    public SpriteSheetEntry() {
    }
}
