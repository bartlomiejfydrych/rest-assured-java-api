package dto.boards.board;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import dto.boards.board.desc_data.Emoji;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@JsonIgnoreProperties(ignoreUnknown = false)
public class DescData {

    // ==========================================================================================================
    // FIELDS
    // ==========================================================================================================

    // NOTE:
    // These variables are also used to call the name of an ignored, redundant field when comparing objects using AssertJ.

    public static final String FIELD_EMOJI = "emoji";

    // ==========================================================================================================
    // FIELDS – VALIDATION CONSTRAINTS
    // ==========================================================================================================

    @Valid // <-- validates nested fields if object exists
    @NotNull
    public Emoji emoji;

    // ==========================================================================================================
    // CONSTRUCTORS
    // ==========================================================================================================

    @JsonCreator
    public DescData(@JsonProperty(value = FIELD_EMOJI, required = true) Emoji emoji) {
        this.emoji = emoji;
    }

    // Empty constructor - needed to be able to assign values manually
    public DescData() {
    }
}
