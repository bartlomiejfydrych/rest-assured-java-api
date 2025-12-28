package dto.boards.board.organization;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@JsonIgnoreProperties(ignoreUnknown = false)
public class OrganizationMemberships {

    // ==========================================================================================================
    // FIELDS
    // ==========================================================================================================

    // NOTE:
    // These variables are also used to call the name of an ignored, redundant field when comparing objects using AssertJ.

    public static final String FIELD_ID = "id";
    public static final String FIELD_ID_MEMBER = "idMember";
    public static final String FIELD_MEMBER_TYPE = "memberType";
    public static final String FIELD_UNCONFIRMED = "unconfirmed";
    public static final String FIELD_DEACTIVATED = "deactivated";
    public static final String FIELD_LAST_ACTIVE = "lastActive";

    // ==========================================================================================================
    // FIELDS – VALIDATION CONSTRAINTS
    // ==========================================================================================================

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

    // ==========================================================================================================
    // CONSTRUCTORS
    // ==========================================================================================================

    @JsonCreator
    public OrganizationMemberships(
            @JsonProperty(value = FIELD_ID, required = true) String id,
            @JsonProperty(value = FIELD_ID_MEMBER, required = true) String idMember,
            @JsonProperty(value = FIELD_MEMBER_TYPE, required = true) String memberType,
            @JsonProperty(value = FIELD_UNCONFIRMED, required = true) Boolean unconfirmed,
            @JsonProperty(value = FIELD_DEACTIVATED, required = true) Boolean deactivated,
            @JsonProperty(value = FIELD_LAST_ACTIVE, required = true) String lastActive
    ) {
        this.id = id;
        this.idMember = idMember;
        this.memberType = memberType;
        this.unconfirmed = unconfirmed;
        this.deactivated = deactivated;
        this.lastActive = lastActive;
    }

    // Empty constructor - needed to be able to assign values manually
    public OrganizationMemberships() {
    }
}
