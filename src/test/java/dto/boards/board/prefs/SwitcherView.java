package dto.boards.board.prefs;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

public class SwitcherView {

    @NotNull
    public String viewType;
    @NotNull
    public Boolean enabled;

    @JsonCreator
    public SwitcherView(
            @JsonProperty(value = "viewType", required = true) String viewType,
            @JsonProperty(value = "enabled", required = true) Boolean enabled
    ) {
        this.viewType = viewType;
        this.enabled = enabled;
    }

    public SwitcherView() {
    }
}
