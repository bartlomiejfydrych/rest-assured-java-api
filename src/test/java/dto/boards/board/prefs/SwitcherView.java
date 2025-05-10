package dto.boards.board.prefs;

public class SwitcherView {
    private String viewType;
    private boolean enabled;

    public SwitcherView() {
    }

    public String getViewType() {
        return viewType;
    }

    public void setViewType(String viewType) {
        this.viewType = viewType;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
