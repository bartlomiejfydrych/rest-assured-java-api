package payloads.boards;

import payloads.BasePayload;

import java.util.HashMap;
import java.util.Map;

import static enums.query_parameters.boards.BoardBaseQueryParameters.*;
import static enums.query_parameters.boards.POST_CreateBoardQueryParameters.*;

public class POST_CreateBoardPayload extends BasePayload {

    // ==========================================================================================================
    // FIELDS – QUERY PARAMETERS
    // ==========================================================================================================

    private final String name;
    private final Boolean defaultLabels;
    private final Boolean defaultLists;
    private final String desc;
    private final String idOrganization;
    private final String idBoardSource;
    private final String keepFromSource;
    private final String powerUps;
    private final String prefsPermissionLevel;
    private final String prefsVoting;
    private final String prefsComments;
    private final String prefsInvitations;
    private final Boolean prefsSelfJoin;
    private final Boolean prefsCardCovers;
    private final String prefsBackground;
    private final String prefsCardAging;

    // ==========================================================================================================
    // HELPER METHOD – CONVERTS A CLASS OBJECT TO QUERY PARAMETER MAP
    // ==========================================================================================================

    public Map<String, Object> toQueryParams() {

        Map<String, Object> params = new HashMap<>();

        putIfNotNull(params, NAME, name);
        putIfNotNull(params, DEFAULT_LABELS, defaultLabels);
        putIfNotNull(params, DEFAULT_LISTS, defaultLists);
        putIfNotNull(params, DESC, desc);
        putIfNotNull(params, ID_ORGANIZATION, idOrganization);
        putIfNotNull(params, ID_BOARD_SOURCE, idBoardSource);
        putIfNotNull(params, KEEP_FROM_SOURCE, keepFromSource);
        putIfNotNull(params, POWER_UPS, powerUps);
        putIfNotNull(params, PREFS_PERMISSION_LEVEL, prefsPermissionLevel);
        putIfNotNull(params, PREFS_VOTING, prefsVoting);
        putIfNotNull(params, PREFS_COMMENTS, prefsComments);
        putIfNotNull(params, PREFS_INVITATIONS, prefsInvitations);
        putIfNotNull(params, PREFS_SELF_JOIN, prefsSelfJoin);
        putIfNotNull(params, PREFS_CARD_COVERS, prefsCardCovers);
        putIfNotNull(params, PREFS_BACKGROUND, prefsBackground);
        putIfNotNull(params, PREFS_CARD_AGING, prefsCardAging);

        return params;
    }

    // ==========================================================================================================
    // EXAMPLE OF USE
    // ==========================================================================================================

    /*

    POST_CreateBoardPayload payload = new POST_CreateBoardPayload.Builder()
        .setName("Tablica API")
        .setDesc("Testowa tablica")
        .setDefaultLabels(false)
        .setPrefs_background("blue")
        .build();

    Map<String, Object> queryParams = payload.toQueryParams();

    */

    // ==========================================================================================================
    // PRIVATE CONSTRUCTOR (ACCESSIBLE ONLY THROUGH BUILDER)
    // ==========================================================================================================

    private POST_CreateBoardPayload(Builder builder) {
        this.name = builder.name;
        this.defaultLabels = builder.defaultLabels;
        this.defaultLists = builder.defaultLists;
        this.desc = builder.desc;
        this.idOrganization = builder.idOrganization;
        this.idBoardSource = builder.idBoardSource;
        this.keepFromSource = builder.keepFromSource;
        this.powerUps = builder.powerUps;
        this.prefsPermissionLevel = builder.prefsPermissionLevel;
        this.prefsVoting = builder.prefsVoting;
        this.prefsComments = builder.prefsComments;
        this.prefsInvitations = builder.prefsInvitations;
        this.prefsSelfJoin = builder.prefsSelfJoin;
        this.prefsCardCovers = builder.prefsCardCovers;
        this.prefsBackground = builder.prefsBackground;
        this.prefsCardAging = builder.prefsCardAging;
    }

    // ==========================================================================================================
    // BUILDER
    // ==========================================================================================================

    public static class Builder {
        private String name;
        private Boolean defaultLabels;
        private Boolean defaultLists;
        private String desc;
        private String idOrganization;
        private String idBoardSource;
        private String keepFromSource;
        private String powerUps;
        private String prefsPermissionLevel;
        private String prefsVoting;
        private String prefsComments;
        private String prefsInvitations;
        private Boolean prefsSelfJoin;
        private Boolean prefsCardCovers;
        private String prefsBackground;
        private String prefsCardAging;

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setDefaultLabels(Boolean defaultLabels) {
            this.defaultLabels = defaultLabels;
            return this;
        }

        public Builder setDefaultLists(Boolean defaultLists) {
            this.defaultLists = defaultLists;
            return this;
        }

        public Builder setDesc(String desc) {
            this.desc = desc;
            return this;
        }

        public Builder setIdOrganization(String idOrganization) {
            this.idOrganization = idOrganization;
            return this;
        }

        public Builder setIdBoardSource(String idBoardSource) {
            this.idBoardSource = idBoardSource;
            return this;
        }

        public Builder setKeepFromSource(String keepFromSource) {
            this.keepFromSource = keepFromSource;
            return this;
        }

        public Builder setPowerUps(String powerUps) {
            this.powerUps = powerUps;
            return this;
        }

        public Builder setPrefsPermissionLevel(String prefsPermissionLevel) {
            this.prefsPermissionLevel = prefsPermissionLevel;
            return this;
        }

        public Builder setPrefsVoting(String prefsVoting) {
            this.prefsVoting = prefsVoting;
            return this;
        }

        public Builder setPrefsComments(String prefsComments) {
            this.prefsComments = prefsComments;
            return this;
        }

        public Builder setPrefsInvitations(String prefsInvitations) {
            this.prefsInvitations = prefsInvitations;
            return this;
        }

        public Builder setPrefsSelfJoin(Boolean prefsSelfJoin) {
            this.prefsSelfJoin = prefsSelfJoin;
            return this;
        }

        public Builder setPrefsCardCovers(Boolean prefsCardCovers) {
            this.prefsCardCovers = prefsCardCovers;
            return this;
        }

        public Builder setPrefsBackground(String prefsBackground) {
            this.prefsBackground = prefsBackground;
            return this;
        }

        public Builder setPrefsCardAging(String prefsCardAging) {
            this.prefsCardAging = prefsCardAging;
            return this;
        }

        public POST_CreateBoardPayload build() {
            return new POST_CreateBoardPayload(this);
        }
    }
}
