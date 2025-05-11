package dto.boards.board;

import dto.boards.board.prefs.SwitcherView;

import java.util.List;

public class Prefs {
    private String permissionLevel;
    private boolean hideVotes;
    private String voting;
    private String comments;
    private String invitations;
    private boolean selfJoin;
    private boolean cardCovers;
    private boolean showCompleteStatus;
    private boolean cardCounts;
    private boolean isTemplate;
    private String cardAging;
    private boolean calendarFeedEnabled;
    private List<Object> hiddenPluginBoardButtons;
    private List<SwitcherView> switcherViews;
    private Object autoArchive;
    private String background;
    private String backgroundColor;
    private Object backgroundDarkColor;
    private Object backgroundImage;
    private Object backgroundDarkImage;
    private Object backgroundImageScaled;
    private boolean backgroundTile;
    private String backgroundBrightness;
    private Object sharedSourceUrl;
    private String backgroundBottomColor;
    private String backgroundTopColor;
    private boolean canBePublic;
    private boolean canBeEnterprise;
    private boolean canBeOrg;
    private boolean canBePrivate;
    private boolean canInvite;

    public Prefs() {
    }

    public String getPermissionLevel() {
        return permissionLevel;
    }

    public void setPermissionLevel(String permissionLevel) {
        this.permissionLevel = permissionLevel;
    }

    public boolean isHideVotes() {
        return hideVotes;
    }

    public void setHideVotes(boolean hideVotes) {
        this.hideVotes = hideVotes;
    }

    public String getVoting() {
        return voting;
    }

    public void setVoting(String voting) {
        this.voting = voting;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getInvitations() {
        return invitations;
    }

    public void setInvitations(String invitations) {
        this.invitations = invitations;
    }

    public boolean isSelfJoin() {
        return selfJoin;
    }

    public void setSelfJoin(boolean selfJoin) {
        this.selfJoin = selfJoin;
    }

    public boolean isCardCovers() {
        return cardCovers;
    }

    public void setCardCovers(boolean cardCovers) {
        this.cardCovers = cardCovers;
    }

    public boolean isShowCompleteStatus() {
        return showCompleteStatus;
    }

    public void setShowCompleteStatus(boolean showCompleteStatus) {
        this.showCompleteStatus = showCompleteStatus;
    }

    public boolean isCardCounts() {
        return cardCounts;
    }

    public void setCardCounts(boolean cardCounts) {
        this.cardCounts = cardCounts;
    }

    public boolean isTemplate() {
        return isTemplate;
    }

    public void setIsTemplate(boolean template) {
        this.isTemplate = template;
    }

    public String getCardAging() {
        return cardAging;
    }

    public void setCardAging(String cardAging) {
        this.cardAging = cardAging;
    }

    public boolean isCalendarFeedEnabled() {
        return calendarFeedEnabled;
    }

    public void setCalendarFeedEnabled(boolean calendarFeedEnabled) {
        this.calendarFeedEnabled = calendarFeedEnabled;
    }

    public List<Object> getHiddenPluginBoardButtons() {
        return hiddenPluginBoardButtons;
    }

    public void setHiddenPluginBoardButtons(List<Object> hiddenPluginBoardButtons) {
        this.hiddenPluginBoardButtons = hiddenPluginBoardButtons;
    }

    public List<SwitcherView> getSwitcherViews() {
        return switcherViews;
    }

    public void setSwitcherViews(List<SwitcherView> switcherViews) {
        this.switcherViews = switcherViews;
    }

    public Object getAutoArchive() {
        return autoArchive;
    }

    public void setAutoArchive(Object autoArchive) {
        this.autoArchive = autoArchive;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public Object getBackgroundDarkColor() {
        return backgroundDarkColor;
    }

    public void setBackgroundDarkColor(Object backgroundDarkColor) {
        this.backgroundDarkColor = backgroundDarkColor;
    }

    public Object getBackgroundImage() {
        return backgroundImage;
    }

    public void setBackgroundImage(Object backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    public Object getBackgroundDarkImage() {
        return backgroundDarkImage;
    }

    public void setBackgroundDarkImage(Object backgroundDarkImage) {
        this.backgroundDarkImage = backgroundDarkImage;
    }

    public Object getBackgroundImageScaled() {
        return backgroundImageScaled;
    }

    public void setBackgroundImageScaled(Object backgroundImageScaled) {
        this.backgroundImageScaled = backgroundImageScaled;
    }

    public boolean isBackgroundTile() {
        return backgroundTile;
    }

    public void setBackgroundTile(boolean backgroundTile) {
        this.backgroundTile = backgroundTile;
    }

    public String getBackgroundBrightness() {
        return backgroundBrightness;
    }

    public void setBackgroundBrightness(String backgroundBrightness) {
        this.backgroundBrightness = backgroundBrightness;
    }

    public Object getSharedSourceUrl() {
        return sharedSourceUrl;
    }

    public void setSharedSourceUrl(Object sharedSourceUrl) {
        this.sharedSourceUrl = sharedSourceUrl;
    }

    public String getBackgroundBottomColor() {
        return backgroundBottomColor;
    }

    public void setBackgroundBottomColor(String backgroundBottomColor) {
        this.backgroundBottomColor = backgroundBottomColor;
    }

    public String getBackgroundTopColor() {
        return backgroundTopColor;
    }

    public void setBackgroundTopColor(String backgroundTopColor) {
        this.backgroundTopColor = backgroundTopColor;
    }

    public boolean isCanBePublic() {
        return canBePublic;
    }

    public void setCanBePublic(boolean canBePublic) {
        this.canBePublic = canBePublic;
    }

    public boolean isCanBeEnterprise() {
        return canBeEnterprise;
    }

    public void setCanBeEnterprise(boolean canBeEnterprise) {
        this.canBeEnterprise = canBeEnterprise;
    }

    public boolean isCanBeOrg() {
        return canBeOrg;
    }

    public void setCanBeOrg(boolean canBeOrg) {
        this.canBeOrg = canBeOrg;
    }

    public boolean isCanBePrivate() {
        return canBePrivate;
    }

    public void setCanBePrivate(boolean canBePrivate) {
        this.canBePrivate = canBePrivate;
    }

    public boolean isCanInvite() {
        return canInvite;
    }

    public void setCanInvite(boolean canInvite) {
        this.canInvite = canInvite;
    }
}
