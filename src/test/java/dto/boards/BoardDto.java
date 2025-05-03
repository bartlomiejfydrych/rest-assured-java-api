package dto.boards;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class BoardDto {

    @NotBlank
    private String id;

    @NotBlank
    @Size(min = 1, max = 16384)
    private String name;

    @NotNull
    @Size(min = 0, max = 16384)
    private String desc;

    private Object descData;

    @NotNull
    private Boolean closed;

    @Pattern(regexp = "^[0-9a-fA-F]{24}$")
    private String idOrganization;

    private String idEnterprise;

    @NotNull
    private Boolean pinned;

    @NotBlank
    private String url;

    @NotBlank
    private String shortUrl;

    @NotNull
    @Valid
    private Prefs prefs;

    @NotNull
    private LabelNames labelNames;

    private Map<String, Object> limits; // Empty object — optional and flexible

    // -------------- INNER CLASSES --------------

    @Data
    public static class Prefs {

        @NotNull
        @Pattern(regexp = "^(org|private|public)$")
        private String permissionLevel;

        @NotNull
        private Boolean hideVotes;

        @NotNull
        @Pattern(regexp = "^(org|private|public)$")
        private String voting;

        @NotNull
        @Pattern(regexp = "^(disabled|members|observers|org|public)$")
        private String comments;

        @NotNull
        @Pattern(regexp = "^(members|admins)$")
        private String invitations;

        @NotNull
        private Boolean selfJoin;

        @NotNull
        private Boolean cardCovers;

        @NotNull
        private Boolean showCompleteStatus;

        @NotNull
        private Boolean cardCounts;

        @NotNull
        private Boolean isTemplate;

        @NotNull
        @Pattern(regexp = "^(pirate|regular)$")
        private String cardAging;

        @NotNull
        private Boolean calendarFeedEnabled;

        @NotNull
        private List<@Valid SwitcherView> switcherViews;

        private List<Object> hiddenPluginBoardButtons;

        private Object autoArchive;

        @NotNull
        @Pattern(regexp = "^(blue|orange|green|red|purple|pink|lime|sky|grey)$")
        private String background;

        private String backgroundColor;
        private String backgroundDarkColor;
        private String backgroundImage;
        private String backgroundDarkImage;
        private Object backgroundImageScaled;
        private Boolean backgroundTile;

        private String backgroundBrightness;
        private String sharedSourceUrl;
        private String backgroundBottomColor;
        private String backgroundTopColor;

        @NotNull
        private Boolean canBePublic;
        @NotNull
        private Boolean canBeEnterprise;
        @NotNull
        private Boolean canBeOrg;
        @NotNull
        private Boolean canBePrivate;
        @NotNull
        private Boolean canInvite;
    }

    @Data
    public static class SwitcherView {
        @NotNull
        private String viewType;

        @NotNull
        private Boolean enabled;
    }

    @Data
    public static class LabelNames {
        private String green;
        private String yellow;
        private String orange;
        private String red;
        private String purple;
        private String blue;
        private String sky;
        private String lime;
        private String pink;
        private String black;
        private String green_dark;
        private String yellow_dark;
        private String orange_dark;
        private String red_dark;
        private String purple_dark;
        private String blue_dark;
        private String sky_dark;
        private String lime_dark;
        private String pink_dark;
        private String black_dark;
        private String green_light;
        private String yellow_light;
        private String orange_light;
        private String red_light;
        private String purple_light;
        private String blue_light;
        private String sky_light;
        private String lime_light;
        private String pink_light;
        private String black_light;
    }
}
