package dto.boards;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import dto.boards.board.LabelNames;
import dto.boards.board.Prefs;
import jakarta.validation.constraints.NotNull;

import java.net.URL;

@JsonIgnoreProperties(ignoreUnknown = false)
public class POST_CreateBoardDto extends BoardBaseDto {

    public static final String FIELD_LIMITS = "limits";

    @NotNull
    public Object limits;

    @JsonCreator
    public POST_CreateBoardDto(
            @JsonProperty(value = "id", required = true) String id,
            @JsonProperty(value = "name", required = true) String name,
            @JsonProperty(value = "desc", required = true) String desc,
            @JsonProperty(value = "descData", required = true) Object descData,
            @JsonProperty(value = "closed", required = true) Boolean closed,
            @JsonProperty(value = "idOrganization", required = true) String idOrganization,
            @JsonProperty(value = "idEnterprise", required = true) Object idEnterprise,
            @JsonProperty(value = "pinned", required = true) Boolean pinned,
            @JsonProperty(value = "url", required = true) URL url,
            @JsonProperty(value = "shortUrl", required = true) URL shortUrl,
            @JsonProperty(value = "prefs", required = true) Prefs prefs,
            @JsonProperty(value = "labelNames", required = true) LabelNames labelNames,
            @JsonProperty(value = "limits", required = true) Object limits
    ) {
        super(id, name, desc, descData, closed, idOrganization, idEnterprise, pinned, url, shortUrl, prefs, labelNames);
        this.limits = limits;
    }

    public POST_CreateBoardDto() {
        super();
    }
}
