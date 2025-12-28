package dto.boards.board.prefs;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

@JsonIgnoreProperties(ignoreUnknown = false)
public class SwitcherView {

    // ==========================================================================================================
    // FIELDS
    // ==========================================================================================================

    // NOTE:
    // These variables are also used to call the name of an ignored, redundant field when comparing objects using AssertJ.

    public static final String FIELD_VIEW_TYPE = "viewType";
    public static final String FIELD_ENABLED = "enabled";

    // ==========================================================================================================
    // FIELDS – VALIDATION CONSTRAINTS
    // ==========================================================================================================

    @NotNull
    public String viewType;

    @NotNull
    public Boolean enabled;

    // ==========================================================================================================
    // CONSTRUCTORS
    // ==========================================================================================================

    @JsonCreator
    public SwitcherView(
            @JsonProperty(value = FIELD_VIEW_TYPE, required = true) String viewType,
            @JsonProperty(value = FIELD_ENABLED, required = true) Boolean enabled
    ) {
        this.viewType = viewType;
        this.enabled = enabled;
    }

    // Empty constructor - needed to be able to assign values manually
    public SwitcherView() {
    }
}
