package dto.boards.board.organization;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

@JsonIgnoreProperties(ignoreUnknown = false)
public class OrganizationMembership {

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
    public String lastActive;

    @JsonCreator
    public OrganizationMembership(
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

    public OrganizationMembership() {
    }
}
