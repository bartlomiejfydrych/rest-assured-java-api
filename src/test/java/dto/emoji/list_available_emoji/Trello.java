package dto.emoji.list_available_emoji;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import dto.emoji.list_available_emoji.trello.SkinVariations;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = false)
public class Trello {

    // ==========================================================================================================
    // FIELDS
    // ==========================================================================================================

    // NOTE:
    // These variables are also used to call the name of an ignored, redundant field when comparing objects using AssertJ.

    public static final String FIELD_SKIN_VARIATIONS = "skinVariations";
    public static final String FIELD_TTS = "tts";
    public static final String FIELD_KEYWORDS = "keywords";

    public static final String FIELD_UNIFIED = "unified";
    public static final String FIELD_NAME = "name";
    public static final String FIELD_NATIVE = "native";
    public static final String FIELD_SHORT_NAME = "shortName";
    public static final String FIELD_SHORT_NAMES = "shortNames";
    public static final String FIELD_TEXT = "text";
    public static final String FIELD_TEXTS = "texts";
    public static final String FIELD_CATEGORY = "category";
    public static final String FIELD_SHEET_X = "sheetX";
    public static final String FIELD_SHEET_Y = "sheetY";
    public static final String FIELD_SKIN_VARIATION = "skinVariation";

    // ==========================================================================================================
    // FIELDS – VALIDATION CONSTRAINTS
    // ==========================================================================================================

    @NotNull
    @Pattern(regexp = "^[0-9A-Fa-f]{4,6}(?:-[0-9A-Fa-f]{4,6})*$",
            message = "Unified must be a valid emoji Unicode code point sequence in hex format")
    public String unified;

    public String name;

    @NotNull
    public String nativeChar; // "native" is a keyword in Java - need to be renamed

    @NotNull
    public String shortName;

    @NotNull
    public List<String> shortNames;

    public String text;

    public List<String> texts;

    @NotNull
    public String category;

    @NotNull
    public Integer sheetX;

    @NotNull
    public Integer sheetY;

    public String skinVariation;

    @Valid // <-- validates nested fields if object exists
    @JsonProperty(FIELD_SKIN_VARIATIONS)
    public SkinVariations skinVariations;

    @JsonProperty(FIELD_TTS)
    public String tts;

    @JsonProperty(FIELD_KEYWORDS)
    public List<String> keywords;

    // ==========================================================================================================
    // CONSTRUCTORS
    // ==========================================================================================================

    @JsonCreator
    public Trello(
            @JsonProperty(value = FIELD_UNIFIED, required = true) String unified,
            @JsonProperty(value = FIELD_NAME, required = true) String name,
            @JsonProperty(value = FIELD_NATIVE, required = true) String nativeChar,
            @JsonProperty(value = FIELD_SHORT_NAME, required = true) String shortName,
            @JsonProperty(value = FIELD_SHORT_NAMES, required = true) List<String> shortNames,
            @JsonProperty(value = FIELD_TEXT, required = true) String text,
            @JsonProperty(value = FIELD_TEXTS) List<String> texts,
            @JsonProperty(value = FIELD_CATEGORY, required = true) String category,
            @JsonProperty(value = FIELD_SHEET_X, required = true) Integer sheetX,
            @JsonProperty(value = FIELD_SHEET_Y, required = true) Integer sheetY,
            @JsonProperty(value = FIELD_SKIN_VARIATION, required = true) String skinVariation
    ) {
        this.unified = unified;
        this.name = name;
        this.nativeChar = nativeChar;
        this.shortName = shortName;
        this.shortNames = shortNames;
        this.text = text;
        this.texts = texts;
        this.category = category;
        this.sheetX = sheetX;
        this.sheetY = sheetY;
        this.skinVariation = skinVariation;
    }

    // Empty constructor - needed to be able to assign values manually
    public Trello() {
    }
}
