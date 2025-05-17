package dto.boards.board;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import dto.boards.board.prefs.SwitcherView;
import jakarta.validation.constraints.*;

import java.util.List;

public class Prefs {

    @NotNull
    @Pattern(regexp = "org|private|public")
    public String permissionLevel;

    @NotNull
    public Boolean hideVotes;

    @NotNull
    @Pattern(regexp = "org|private|public")
    public String voting;

    @NotNull
    @Pattern(regexp = "disabled|members|observers|org|public")
    public String comments;

    @NotNull
    @Pattern(regexp = "members|admins")
    public String invitations;

    @NotNull
    public Boolean selfJoin;
    @NotNull
    public Boolean cardCovers;
    @NotNull
    public Boolean showCompleteStatus;
    @NotNull
    public Boolean cardCounts;
    @NotNull
    public Boolean isTemplate;

    @NotNull
    @Pattern(regexp = "pirate|regular")
    public String cardAging;

    @NotNull
    public Boolean calendarFeedEnabled;
    @NotNull
    public List<Object> hiddenPluginBoardButtons;
    @NotNull
    public List<SwitcherView> switcherViews;

    public Object autoArchive;

    @NotNull
    @Pattern(regexp = "blue|orange|green|red|purple|pink|lime|sky|grey")
    public String background;

    @NotNull
    public String backgroundColor;
    public Object backgroundDarkColor;
    public Object backgroundImage;
    public Object backgroundDarkImage;
    public Object backgroundImageScaled;

    @NotNull
    public Boolean backgroundTile;
    @NotNull
    public String backgroundBrightness;
    public Object sharedSourceUrl;

    @NotNull
    public String backgroundBottomColor;
    @NotNull
    public String backgroundTopColor;

    @NotNull
    public Boolean canBePublic;
    @NotNull
    public Boolean canBeEnterprise;
    @NotNull
    public Boolean canBeOrg;
    @NotNull
    public Boolean canBePrivate;
    @NotNull
    public Boolean canInvite;

    @JsonCreator
    public Prefs(
            @JsonProperty(value = "permissionLevel", required = true) String permissionLevel,
            @JsonProperty(value = "hideVotes", required = true) Boolean hideVotes,
            @JsonProperty(value = "voting", required = true) String voting,
            @JsonProperty(value = "comments", required = true) String comments,
            @JsonProperty(value = "invitations", required = true) String invitations,
            @JsonProperty(value = "selfJoin", required = true) Boolean selfJoin,
            @JsonProperty(value = "cardCovers", required = true) Boolean cardCovers,
            @JsonProperty(value = "showCompleteStatus", required = true) Boolean showCompleteStatus,
            @JsonProperty(value = "cardCounts", required = true) Boolean cardCounts,
            @JsonProperty(value = "isTemplate", required = true) Boolean isTemplate,
            @JsonProperty(value = "cardAging", required = true) String cardAging,
            @JsonProperty(value = "calendarFeedEnabled", required = true) Boolean calendarFeedEnabled,
            @JsonProperty(value = "hiddenPluginBoardButtons", required = true) List<Object> hiddenPluginBoardButtons,
            @JsonProperty(value = "switcherViews", required = true) List<SwitcherView> switcherViews,
            @JsonProperty("autoArchive") Object autoArchive,
            @JsonProperty(value = "background", required = true) String background,
            @JsonProperty(value = "backgroundColor", required = true) String backgroundColor,
            @JsonProperty("backgroundDarkColor") Object backgroundDarkColor,
            @JsonProperty("backgroundImage") Object backgroundImage,
            @JsonProperty("backgroundDarkImage") Object backgroundDarkImage,
            @JsonProperty("backgroundImageScaled") Object backgroundImageScaled,
            @JsonProperty(value = "backgroundTile", required = true) Boolean backgroundTile,
            @JsonProperty(value = "backgroundBrightness", required = true) String backgroundBrightness,
            @JsonProperty("sharedSourceUrl") Object sharedSourceUrl,
            @JsonProperty(value = "backgroundBottomColor", required = true) String backgroundBottomColor,
            @JsonProperty(value = "backgroundTopColor", required = true) String backgroundTopColor,
            @JsonProperty(value = "canBePublic", required = true) Boolean canBePublic,
            @JsonProperty(value = "canBeEnterprise", required = true) Boolean canBeEnterprise,
            @JsonProperty(value = "canBeOrg", required = true) Boolean canBeOrg,
            @JsonProperty(value = "canBePrivate", required = true) Boolean canBePrivate,
            @JsonProperty(value = "canInvite", required = true) Boolean canInvite
    ) {
        this.permissionLevel = permissionLevel;
        this.hideVotes = hideVotes;
        this.voting = voting;
        this.comments = comments;
        this.invitations = invitations;
        this.selfJoin = selfJoin;
        this.cardCovers = cardCovers;
        this.showCompleteStatus = showCompleteStatus;
        this.cardCounts = cardCounts;
        this.isTemplate = isTemplate;
        this.cardAging = cardAging;
        this.calendarFeedEnabled = calendarFeedEnabled;
        this.hiddenPluginBoardButtons = hiddenPluginBoardButtons;
        this.switcherViews = switcherViews;
        this.autoArchive = autoArchive;
        this.background = background;
        this.backgroundColor = backgroundColor;
        this.backgroundDarkColor = backgroundDarkColor;
        this.backgroundImage = backgroundImage;
        this.backgroundDarkImage = backgroundDarkImage;
        this.backgroundImageScaled = backgroundImageScaled;
        this.backgroundTile = backgroundTile;
        this.backgroundBrightness = backgroundBrightness;
        this.sharedSourceUrl = sharedSourceUrl;
        this.backgroundBottomColor = backgroundBottomColor;
        this.backgroundTopColor = backgroundTopColor;
        this.canBePublic = canBePublic;
        this.canBeEnterprise = canBeEnterprise;
        this.canBeOrg = canBeOrg;
        this.canBePrivate = canBePrivate;
        this.canInvite = canInvite;
    }

    public Prefs() {
    }
}
