package dto.emoji.list_available_emoji.trello;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import dto.emoji.list_available_emoji.trello.skin_variations.SkinVariationEntry;
import jakarta.validation.Valid;

@JsonIgnoreProperties(ignoreUnknown = false)
public class SkinVariations {

    // ==========================================================================================================
    // FIELDS
    // ==========================================================================================================

    // NOTE:
    // These variables are also used to call the name of an ignored, redundant field when comparing objects using AssertJ.

    public static final String FIELD_1F3FB = "1F3FB";
    public static final String FIELD_1F3FC = "1F3FC";
    public static final String FIELD_1F3FD = "1F3FD";
    public static final String FIELD_1F3FE = "1F3FE";
    public static final String FIELD_1F3FF = "1F3FF";

    // ==========================================================================================================
    // FIELDS – VALIDATION CONSTRAINTS
    // ==========================================================================================================

    @Valid // <-- validates nested fields if object exists
    @JsonProperty(FIELD_1F3FB)
    public SkinVariationEntry oneF3FB;

    @Valid // <-- validates nested fields if object exists
    @JsonProperty(FIELD_1F3FC)
    public SkinVariationEntry oneF3FC;

    @Valid // <-- validates nested fields if object exists
    @JsonProperty(FIELD_1F3FD)
    public SkinVariationEntry oneF3FD;

    @Valid // <-- validates nested fields if object exists
    @JsonProperty(FIELD_1F3FE)
    public SkinVariationEntry oneF3FE;

    @Valid // <-- validates nested fields if object exists
    @JsonProperty(FIELD_1F3FF)
    public SkinVariationEntry oneF3FF;

    // ==========================================================================================================
    // CONSTRUCTORS
    // ==========================================================================================================

    // Empty constructor - needed to be able to assign values manually
    public SkinVariations() {
    }
}
