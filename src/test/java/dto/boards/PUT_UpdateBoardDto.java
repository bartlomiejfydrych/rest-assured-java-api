package dto.boards;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import dto.boards.board.DescData;
import dto.boards.board.LabelNames;
import dto.boards.board.Organization;
import dto.boards.board.Prefs;
import jakarta.validation.Valid;

import java.net.URL;
import java.util.Optional;

@JsonIgnoreProperties(ignoreUnknown = false)
public class PUT_UpdateBoardDto extends BoardBaseDto {

    public static final String FIELD_ORGANIZATION = "organization";

    @Valid // <-- validates nested fields if object exists
    public Optional<Organization> organization = Optional.empty();

    @JsonCreator
    public PUT_UpdateBoardDto(
            @JsonProperty(value = "id", required = true) String id,
            @JsonProperty(value = "name", required = true) String name,
            @JsonProperty(value = "desc", required = true) String desc,
            @JsonProperty(value = "descData", required = true) DescData descData,
            @JsonProperty(value = "closed", required = true) Boolean closed,
            @JsonProperty(value = "idOrganization", required = true) String idOrganization,
            @JsonProperty(value = "idEnterprise", required = true) Object idEnterprise,
            @JsonProperty(value = "pinned", required = true) Boolean pinned,
            @JsonProperty(value = "url", required = true) URL url,
            @JsonProperty(value = "shortUrl", required = true) URL shortUrl,
            @JsonProperty(value = "prefs", required = true) Prefs prefs,
            @JsonProperty(value = "labelNames", required = true) LabelNames labelNames
    ) {
        super(id, name, desc, descData, closed, idOrganization, idEnterprise, pinned, url, shortUrl, prefs, labelNames);
    }

    @JsonProperty(FIELD_ORGANIZATION)
    public void setOrganization(Organization organization) {
        this.organization = Optional.ofNullable(organization);
    }

    public Optional<Organization> getOrganization() {
        return organization;
    }

    public Organization getOrganizationOrThrow() {
        return organization.orElseThrow(() -> new IllegalStateException("Organization is missing"));
    }

    public PUT_UpdateBoardDto() {
        super();
    }
}
