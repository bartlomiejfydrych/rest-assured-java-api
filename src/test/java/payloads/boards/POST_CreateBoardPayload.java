package payloads.boards;

import java.util.HashMap;
import java.util.Map;

public class POST_CreateBoardPayload {

    // ----------------
    // Query parameters
    // ----------------

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

    // -----------------------------------------
    // Helper method – conversion to queryParams
    // -----------------------------------------

    public Map<String, Object> toQueryParams() {
        Map<String, Object> params = new HashMap<>();

        if (name != null) params.put("name", name);
        if (defaultLabels != null) params.put("defaultLabels", defaultLabels);
        if (defaultLists != null) params.put("defaultLists", defaultLists);
        if (desc != null) params.put("desc", desc);
        if (idOrganization != null) params.put("idOrganization", idOrganization);
        if (idBoardSource != null) params.put("idBoardSource", idBoardSource);
        if (keepFromSource != null) params.put("keepFromSource", keepFromSource);
        if (powerUps != null) params.put("powerUps", powerUps);
        if (prefsPermissionLevel != null) params.put("prefs_permissionLevel", prefsPermissionLevel);
        if (prefsVoting != null) params.put("prefs_voting", prefsVoting);
        if (prefsComments != null) params.put("prefs_comments", prefsComments);
        if (prefsInvitations != null) params.put("prefs_invitations", prefsInvitations);
        if (prefsSelfJoin != null) params.put("prefs_selfJoin", prefsSelfJoin);
        if (prefsCardCovers != null) params.put("prefs_cardCovers", prefsCardCovers);
        if (prefsBackground != null) params.put("prefs_background", prefsBackground);
        if (prefsCardAging != null) params.put("prefs_cardAging", prefsCardAging);

        return params;
    }

    // --------------
    // Example of use
    // --------------

    /*

    POST_CreateBoardPayload payload = new POST_CreateBoardPayload.Builder()
        .setName("Tablica API")
        .setDesc("Testowa tablica")
        .setDefaultLabels(false)
        .setPrefs_background("blue")
        .build();

    Map<String, Object> queryParams = payload.toQueryParams();

    */

    // -----------------------------------------------------
    // Private constructor (accessible only through builder)
    // -----------------------------------------------------

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

    // -------
    // Builder
    // -------

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
