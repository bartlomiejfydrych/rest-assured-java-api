package dto.emoji.list_available_emoji.trello.skin_variations;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = false)
public class SkinVariationEntry {

    // ==========================================================================================================
    // FIELDS
    // ==========================================================================================================

    // NOTE:
    // These variables are also used to call the name of an ignored, redundant field when comparing objects using AssertJ.

    public static final String FIELD_UNIFIED = "unified";
    public static final String FIELD_NATIVE = "native";
    public static final String FIELD_SHEET_X = "sheetX";
    public static final String FIELD_SHEET_Y = "sheetY";

    // ==========================================================================================================
    // FIELDS – VALIDATION CONSTRAINTS
    // ==========================================================================================================

    @JsonProperty(FIELD_UNIFIED)
    public String unified;

    @JsonProperty(FIELD_NATIVE)
    public String nativeChar;

    @JsonProperty(FIELD_SHEET_X)
    public Integer sheetX;

    @JsonProperty(FIELD_SHEET_Y)
    public Integer sheetY;

    // ==========================================================================================================
    // CONSTRUCTORS
    // ==========================================================================================================

    // Empty constructor - needed to be able to assign values manually
    public SkinVariationEntry() {
    }
}
