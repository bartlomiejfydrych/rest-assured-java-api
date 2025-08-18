package dto.emoji.list_available_emoji.trello.skin_variations;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;

@JsonIgnoreProperties(ignoreUnknown = false)
public class SkinVariationEntry {

    @Valid
    @JsonProperty("unified")
    public String unified;

    @Valid
    @JsonProperty("native")
    public String nativeChar;

    @Valid
    @JsonProperty("sheetX")
    public Integer sheetX;

    @Valid
    @JsonProperty("sheetY")
    public Integer sheetY;

    public SkinVariationEntry() {
    }
}
