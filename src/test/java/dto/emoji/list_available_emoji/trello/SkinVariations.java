package dto.emoji.list_available_emoji.trello;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import dto.emoji.list_available_emoji.trello.skin_variations.SkinVariationEntry;
import jakarta.validation.Valid;

@JsonIgnoreProperties(ignoreUnknown = false)
public class SkinVariations {

    @Valid
    @JsonProperty("1F3FB")
    public SkinVariationEntry oneF3FB;

    @Valid
    @JsonProperty("1F3FC")
    public SkinVariationEntry oneF3FC;

    @Valid
    @JsonProperty("1F3FD")
    public SkinVariationEntry oneF3FD;

    @Valid
    @JsonProperty("1F3FE")
    public SkinVariationEntry oneF3FE;

    @Valid
    @JsonProperty("1F3FF")
    public SkinVariationEntry oneF3FF;

    public SkinVariations() {
    }
}
