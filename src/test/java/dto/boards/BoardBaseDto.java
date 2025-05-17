package dto.boards;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import dto.boards.board.LabelNames;
import dto.boards.board.Prefs;
import jakarta.validation.constraints.*;

@JsonIgnoreProperties(ignoreUnknown = false)
public class BoardBaseDto {

    @NotNull
    @Pattern(regexp = "^[0-9a-fA-F]{24}$")
    public String id;

    @NotNull
    @Size(min = 1, max = 16384)
    public String name;

    @NotNull
    @Size(max = 16384)
    public String desc;

    @NotNull
    public Object descData;

    @NotNull
    public Boolean closed;

    @NotNull
    @Pattern(regexp = "^[0-9a-fA-F]{24}$")
    public String idOrganization;

    @NotNull
    public Object idEnterprise;

    @NotNull
    public Boolean pinned;

    @NotNull
    public String url;

    @NotNull
    public String shortUrl;

    @NotNull
    public Prefs prefs;

    @NotNull
    public LabelNames labelNames;

    @JsonCreator
    public BoardBaseDto(
            @JsonProperty(value = "id", required = true) String id,
            @JsonProperty(value = "name", required = true) String name,
            @JsonProperty(value = "desc", required = true) String desc,
            @JsonProperty(value = "descData", required = true) Object descData,
            @JsonProperty(value = "closed", required = true) Boolean closed,
            @JsonProperty(value = "idOrganization", required = true) String idOrganization,
            @JsonProperty(value = "idEnterprise", required = true) Object idEnterprise,
            @JsonProperty(value = "pinned", required = true) Boolean pinned,
            @JsonProperty(value = "url", required = true) String url,
            @JsonProperty(value = "shortUrl", required = true) String shortUrl,
            @JsonProperty(value = "prefs", required = true) Prefs prefs,
            @JsonProperty(value = "labelNames", required = true) LabelNames labelNames
    ) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.descData = descData;
        this.closed = closed;
        this.idOrganization = idOrganization;
        this.idEnterprise = idEnterprise;
        this.pinned = pinned;
        this.url = url;
        this.shortUrl = shortUrl;
        this.prefs = prefs;
        this.labelNames = labelNames;
    }

    // Empty constructor - needed to be able to assign values manually
    public BoardBaseDto() {
    }
}
