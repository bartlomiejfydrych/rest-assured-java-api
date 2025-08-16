package dto.emoji.list_available_emoji;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import dto.emoji.list_available_emoji.trello.SkinVariations;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@JsonIgnoreProperties(ignoreUnknown = false)
public class Trello {

    @NotNull
    @Pattern(regexp = "^[0-9A-Fa-f]{4,6}(?:-[0-9A-Fa-f]{4,6})*$",
            message = "Unified must be a valid emoji Unicode code point sequence in hex format")
    public String unified;

    @NotNull
    public String name;

    @NotNull
    @JsonProperty("native")
    public String nativeChar; // "native" is a keyword in Java - need to be renamed

    @NotNull
    public String shortName;

    @NotNull
    public List<String> shortNames;

    @NotNull
    public String text;

    public List<String> texts; // Can be null

    @NotNull
    public String category;

    @NotNull
    public Integer sheetX;

    @NotNull
    public Integer sheetY;

    public String skinVariation;

    @Valid
    public Optional<Map<String, SkinVariations>> skinVariations = Optional.empty();

    @NotNull
    public String tts;

    @NotNull
    public List<String> keywords;

    @JsonCreator
    public Trello(
            @JsonProperty(value = "unified", required = true) String unified,
            @JsonProperty(value = "name", required = true) String name,
            @JsonProperty(value = "native", required = true) String nativeChar,
            @JsonProperty(value = "shortName", required = true) String shortName,
            @JsonProperty(value = "shortNames", required = true) List<String> shortNames,
            @JsonProperty(value = "text", required = true) String text,
            @JsonProperty(value = "texts") List<String> texts,
            @JsonProperty(value = "category", required = true) String category,
            @JsonProperty(value = "sheetX", required = true) Integer sheetX,
            @JsonProperty(value = "sheetY", required = true) Integer sheetY,
            @JsonProperty(value = "skinVariation", required = true) String skinVariation,
            @JsonProperty(value = "tts", required = true) String tts,
            @JsonProperty(value = "keywords", required = true) List<String> keywords
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
        this.tts = tts;
        this.keywords = keywords;
    }

    @JsonProperty("skinVariations")
    public void setSkinVariations(Map<String, SkinVariations> skinVariations) {
        this.skinVariations = Optional.ofNullable(skinVariations);
    }

    public Optional<Map<String, SkinVariations>> getSkinVariations() {
        return skinVariations;
    }

    public Trello() {
    }
}
