package dto.boards.board.organization;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@JsonIgnoreProperties(ignoreUnknown = false)
public class OrganizationMemberships {

    @NotNull
    public String id;

    @NotNull
    public String idMember;

    @NotNull
    public String memberType;

    @NotNull
    public Boolean unconfirmed;

    @NotNull
    public Boolean deactivated;

    @NotNull
    @Pattern(
            regexp = "^\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}\\.\\d{3}Z$",
            message = "lastActive must match ISO 8601 format with milliseconds and 'Z', e.g., 2025-07-22T17:04:41.428Z"
    )
    public String lastActive;

    @JsonCreator
    public OrganizationMemberships(
            @JsonProperty(value = "id", required = true) String id,
            @JsonProperty(value = "idMember", required = true) String idMember,
            @JsonProperty(value = "memberType", required = true) String memberType,
            @JsonProperty(value = "unconfirmed", required = true) Boolean unconfirmed,
            @JsonProperty(value = "deactivated", required = true) Boolean deactivated,
            @JsonProperty(value = "lastActive", required = true) String lastActive
    ) {
        this.id = id;
        this.idMember = idMember;
        this.memberType = memberType;
        this.unconfirmed = unconfirmed;
        this.deactivated = deactivated;
        this.lastActive = lastActive;
    }

    public OrganizationMemberships() {
    }
}
