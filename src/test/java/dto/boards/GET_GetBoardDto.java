package dto.boards;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import dto.boards.board.DescData;
import dto.boards.board.LabelNames;
import dto.boards.board.Prefs;

import java.net.URL;

@JsonIgnoreProperties(ignoreUnknown = false)
public class GET_GetBoardDto extends BoardBaseDto {

    // ==========================================================================================================
    // CONSTRUCTORS
    // ==========================================================================================================

    @JsonCreator
    public GET_GetBoardDto(
            @JsonProperty(value = FIELD_ID, required = true) String id,
            @JsonProperty(value = FIELD_NAME, required = true) String name,
            @JsonProperty(value = FIELD_DESC, required = true) String desc,
            @JsonProperty(value = FIELD_DESC_DATA, required = true) DescData descData,
            @JsonProperty(value = FIELD_CLOSED, required = true) Boolean closed,
            @JsonProperty(value = FIELD_ID_ORGANIZATION, required = true) String idOrganization,
            @JsonProperty(value = FIELD_ID_ENTERPRISE, required = true) Object idEnterprise,
            @JsonProperty(value = FIELD_PINNED, required = true) Boolean pinned,
            @JsonProperty(value = FIELD_URL, required = true) URL url,
            @JsonProperty(value = FIELD_SHORT_URL, required = true) URL shortUrl,
            @JsonProperty(value = FIELD_PREFS, required = true) Prefs prefs,
            @JsonProperty(value = FIELD_LABEL_NAMES, required = true) LabelNames labelNames
    ) {
        super(id, name, desc, descData, closed, idOrganization, idEnterprise, pinned, url, shortUrl, prefs, labelNames);
    }

    // Empty constructor - needed to be able to assign values manually
    public GET_GetBoardDto() {
        super();
    }
}
