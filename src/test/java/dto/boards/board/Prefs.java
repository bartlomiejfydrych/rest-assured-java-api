package dto.boards.board;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import dto.boards.board.prefs.SwitcherView;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = false)
public class Prefs {

    // ==========================================================================================================
    // FIELDS
    // ==========================================================================================================

    // NOTE:
    // These variables are also used to call the name of an ignored, redundant field when comparing objects using AssertJ.

    public static final String FIELD_PERMISSION_LEVEL = "permissionLevel";
    public static final String FIELD_HIDE_VOTES = "hideVotes";
    public static final String FIELD_VOTING = "voting";
    public static final String FIELD_COMMENTS = "comments";
    public static final String FIELD_INVITATIONS = "invitations";
    public static final String FIELD_SELF_JOIN = "selfJoin";
    public static final String FIELD_CARD_COVERS = "cardCovers";
    public static final String FIELD_SHOW_COMPLETE_STATUS = "showCompleteStatus";
    public static final String FIELD_CARD_COUNTS = "cardCounts";
    public static final String FIELD_IS_TEMPLATE = "isTemplate";
    public static final String FIELD_CARD_AGING = "cardAging";
    public static final String FIELD_CALENDAR_FEED_ENABLED = "calendarFeedEnabled";
    public static final String FIELD_HIDDEN_PLUGIN_BOARD_BUTTONS = "hiddenPluginBoardButtons";
    public static final String FIELD_SWITCHER_VIEWS = "switcherViews";
    public static final String FIELD_AUTO_ARCHIVE = "autoArchive";
    public static final String FIELD_BACKGROUND = "background";
    public static final String FIELD_BACKGROUND_COLOR = "backgroundColor";
    public static final String FIELD_BACKGROUND_DARK_COLOR = "backgroundDarkColor";
    public static final String FIELD_BACKGROUND_IMAGE = "backgroundImage";
    public static final String FIELD_BACKGROUND_DARK_IMAGE = "backgroundDarkImage";
    public static final String FIELD_BACKGROUND_IMAGE_SCALED = "backgroundImageScaled";
    public static final String FIELD_BACKGROUND_TILE = "backgroundTile";
    public static final String FIELD_BACKGROUND_BRIGHTNESS = "backgroundBrightness";
    public static final String FIELD_SHARED_SOURCE_URL = "sharedSourceUrl";
    public static final String FIELD_BACKGROUND_BOTTOM_COLOR = "backgroundBottomColor";
    public static final String FIELD_BACKGROUND_TOP_COLOR = "backgroundTopColor";
    public static final String FIELD_CAN_BE_PUBLIC = "canBePublic";
    public static final String FIELD_CAN_BE_ENTERPRISE = "canBeEnterprise";
    public static final String FIELD_CAN_BE_ORG = "canBeOrg";
    public static final String FIELD_CAN_BE_PRIVATE = "canBePrivate";
    public static final String FIELD_CAN_INVITE = "canInvite";

    // ==========================================================================================================
    // FIELDS – VALIDATION CONSTRAINTS
    // ==========================================================================================================

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

    // The @Valid annotation returns warnings for unspecified objects. It should only be uncommented once we have a specified object type.
    // @Valid // <-- validates nested fields if object exists
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

    // ==========================================================================================================
    // CONSTRUCTORS
    // ==========================================================================================================

    @JsonCreator
    public Prefs(
            @JsonProperty(value = FIELD_PERMISSION_LEVEL, required = true) String permissionLevel,
            @JsonProperty(value = FIELD_HIDE_VOTES, required = true) Boolean hideVotes,
            @JsonProperty(value = FIELD_VOTING, required = true) String voting,
            @JsonProperty(value = FIELD_COMMENTS, required = true) String comments,
            @JsonProperty(value = FIELD_INVITATIONS, required = true) String invitations,
            @JsonProperty(value = FIELD_SELF_JOIN, required = true) Boolean selfJoin,
            @JsonProperty(value = FIELD_CARD_COVERS, required = true) Boolean cardCovers,
            @JsonProperty(value = FIELD_SHOW_COMPLETE_STATUS, required = true) Boolean showCompleteStatus,
            @JsonProperty(value = FIELD_CARD_COUNTS, required = true) Boolean cardCounts,
            @JsonProperty(value = FIELD_IS_TEMPLATE, required = true) Boolean isTemplate,
            @JsonProperty(value = FIELD_CARD_AGING, required = true) String cardAging,
            @JsonProperty(value = FIELD_CALENDAR_FEED_ENABLED, required = true) Boolean calendarFeedEnabled,
            @JsonProperty(value = FIELD_HIDDEN_PLUGIN_BOARD_BUTTONS, required = true) List<Object> hiddenPluginBoardButtons,
            @JsonProperty(value = FIELD_SWITCHER_VIEWS, required = true) List<SwitcherView> switcherViews,
            @JsonProperty(value = FIELD_AUTO_ARCHIVE) Object autoArchive,
            @JsonProperty(value = FIELD_BACKGROUND, required = true) String background,
            @JsonProperty(value = FIELD_BACKGROUND_COLOR, required = true) String backgroundColor,
            @JsonProperty(value = FIELD_BACKGROUND_DARK_COLOR) Object backgroundDarkColor,
            @JsonProperty(value = FIELD_BACKGROUND_IMAGE) Object backgroundImage,
            @JsonProperty(value = FIELD_BACKGROUND_DARK_IMAGE) Object backgroundDarkImage,
            @JsonProperty(value = FIELD_BACKGROUND_IMAGE_SCALED) Object backgroundImageScaled,
            @JsonProperty(value = FIELD_BACKGROUND_TILE, required = true) Boolean backgroundTile,
            @JsonProperty(value = FIELD_BACKGROUND_BRIGHTNESS, required = true) String backgroundBrightness,
            @JsonProperty(value = FIELD_SHARED_SOURCE_URL) Object sharedSourceUrl,
            @JsonProperty(value = FIELD_BACKGROUND_BOTTOM_COLOR, required = true) String backgroundBottomColor,
            @JsonProperty(value = FIELD_BACKGROUND_TOP_COLOR, required = true) String backgroundTopColor,
            @JsonProperty(value = FIELD_CAN_BE_PUBLIC, required = true) Boolean canBePublic,
            @JsonProperty(value = FIELD_CAN_BE_ENTERPRISE, required = true) Boolean canBeEnterprise,
            @JsonProperty(value = FIELD_CAN_BE_ORG, required = true) Boolean canBeOrg,
            @JsonProperty(value = FIELD_CAN_BE_PRIVATE, required = true) Boolean canBePrivate,
            @JsonProperty(value = FIELD_CAN_INVITE, required = true) Boolean canInvite
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

    // Empty constructor - needed to be able to assign values manually
    public Prefs() {
    }
}
