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

    // ==========================================================================================================
    // FIELDS
    // ==========================================================================================================

    public static final String FIELD_ID = "id";
    public static final String FIELD_NAME = "name";
    public static final String FIELD_DISPLAY_NAME = "displayName";
    public static final String FIELD_MEMBERSHIPS = "memberships";

    // ==========================================================================================================
    // FIELDS – VALIDATION CONSTRAINTS
    // ==========================================================================================================

    @NotNull
    public String id;

    @NotNull
    public String name;

    @NotNull
    public String displayName;

    @NotNull
    @NotEmpty
    public List<OrganizationMemberships> memberships;

    // ==========================================================================================================
    // CONSTRUCTORS
    // ==========================================================================================================

    @JsonCreator
    public Organization(
            @JsonProperty(value = FIELD_ID, required = true) String id,
            @JsonProperty(value = FIELD_NAME, required = true) String name,
            @JsonProperty(value = FIELD_DISPLAY_NAME, required = true) String displayName,
            @JsonProperty(value = FIELD_MEMBERSHIPS, required = true) List<OrganizationMemberships> memberships
    ) {
        this.id = id;
        this.name = name;
        this.displayName = displayName;
        this.memberships = memberships;
    }

    // Empty constructor - needed to be able to assign values manually
    public Organization() {
    }
}
