package dto.boards.board;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import dto.boards.board.organization.OrganizationMemberships;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = false)
public class Organization {

    @NotNull
    public String id;

    @NotNull
    public String name;

    @NotNull
    public String displayName;

    @NotNull
    @NotEmpty
    public List<OrganizationMemberships> memberships;

    @JsonCreator
    public Organization(
            @JsonProperty(value = "id", required = true) String id,
            @JsonProperty(value = "name", required = true) String name,
            @JsonProperty(value = "displayName", required = true) String displayName,
            @JsonProperty(value = "memberships", required = true) List<OrganizationMemberships> memberships
    ) {
        this.id = id;
        this.name = name;
        this.displayName = displayName;
        this.memberships = memberships;
    }

    public Organization() {
    }
}
