package payloads.boards;

import java.util.HashMap;
import java.util.Map;

public class PUT_UpdateBoardPayload {

    // ----------------
    // Query parameters
    // ----------------

    private final String name;
    private final String desc;
    private final Boolean closed;
    private final String subscribed;
    private final String idOrganization;
    private final String prefsPermissionLevel;
    private final Boolean prefsSelfJoin;
    private final Boolean prefsCardCovers;
    private final Boolean prefsHideVotes;
    private final String prefsInvitations;
    private final String prefsVoting;
    private final String prefsComments;
    private final String prefsBackground;
    private final String prefsCardAging;
    private final Boolean prefsCalendarFeedEnabled;

    // -----------------------------------------
    // Helper method – conversion to queryParams
    // -----------------------------------------

    public Map<String, Object> toQueryParams() {
        Map<String, Object> params = new HashMap<>();

        if (name != null) params.put("name", name);
        if (desc != null) params.put("desc", desc);
        if (closed != null) params.put("closed", closed);
        if (subscribed != null) params.put("subscribed", subscribed);
        if (idOrganization != null) params.put("idOrganization", idOrganization);
        if (prefsPermissionLevel != null) params.put("prefs/permissionLevel", prefsPermissionLevel);
        if (prefsSelfJoin != null) params.put("prefs/selfJoin", prefsSelfJoin);
        if (prefsCardCovers != null) params.put("prefs/cardCovers", prefsCardCovers);
        if (prefsHideVotes != null) params.put("prefs/hideVotes", prefsHideVotes);
        if (prefsInvitations != null) params.put("prefs/invitations", prefsInvitations);
        if (prefsVoting != null) params.put("prefs/voting", prefsVoting);
        if (prefsComments != null) params.put("prefs/comments", prefsComments);
        if (prefsBackground != null) params.put("prefs/background", prefsBackground);
        if (prefsCardAging != null) params.put("prefs/cardAging", prefsCardAging);
        if (prefsCalendarFeedEnabled != null) params.put("prefs/calendarFeedEnabled", prefsCalendarFeedEnabled);

        return params;
    }

    // --------------
    // Example of use
    // --------------

    /*

    PUT_UpdateBoardPayload payload = new PUT_UpdateBoardPayload.Builder()
        .setName("Zmieniona tablica")
        .setPrefsBackground("lime")
        .setPrefsSelfJoin(true)
        .setPrefsCardAging("Błąd krytyczny")
        .build();

    Map<String, Object> queryParams = payload.toQueryParams();

    */

    // -----------------------------------------------------
    // Private constructor (accessible only through builder)
    // -----------------------------------------------------

    private PUT_UpdateBoardPayload(Builder builder) {
        this.name = builder.name;
        this.desc = builder.desc;
        this.closed = builder.closed;
        this.subscribed = builder.subscribed;
        this.idOrganization = builder.idOrganization;
        this.prefsPermissionLevel = builder.prefsPermissionLevel;
        this.prefsSelfJoin = builder.prefsSelfJoin;
        this.prefsCardCovers = builder.prefsCardCovers;
        this.prefsHideVotes = builder.prefsHideVotes;
        this.prefsInvitations = builder.prefsInvitations;
        this.prefsVoting = builder.prefsVoting;
        this.prefsComments = builder.prefsComments;
        this.prefsBackground = builder.prefsBackground;
        this.prefsCardAging = builder.prefsCardAging;
        this.prefsCalendarFeedEnabled = builder.prefsCalendarFeedEnabled;
    }

    // -------
    // Builder
    // -------

    public static class Builder {
        private String name;
        private String desc;
        private Boolean closed;
        private String subscribed;
        private String idOrganization;
        private String prefsPermissionLevel;
        private Boolean prefsSelfJoin;
        private Boolean prefsCardCovers;
        private Boolean prefsHideVotes;
        private String prefsInvitations;
        private String prefsVoting;
        private String prefsComments;
        private String prefsBackground;
        private String prefsCardAging;
        private Boolean prefsCalendarFeedEnabled;

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setDesc(String desc) {
            this.desc = desc;
            return this;
        }

        public Builder setClosed(Boolean closed) {
            this.closed = closed;
            return this;
        }

        public Builder setSubscribed(String subscribed) {
            this.subscribed = subscribed;
            return this;
        }

        public Builder setIdOrganization(String idOrganization) {
            this.idOrganization = idOrganization;
            return this;
        }

        public Builder setPrefsPermissionLevel(String prefsPermissionLevel) {
            this.prefsPermissionLevel = prefsPermissionLevel;
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

        public Builder setPrefsHideVotes(Boolean prefsHideVotes) {
            this.prefsHideVotes = prefsHideVotes;
            return this;
        }

        public Builder setPrefsInvitations(String prefsInvitations) {
            this.prefsInvitations = prefsInvitations;
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

        public Builder setPrefsBackground(String prefsBackground) {
            this.prefsBackground = prefsBackground;
            return this;
        }

        public Builder setPrefsCardAging(String prefsCardAging) {
            this.prefsCardAging = prefsCardAging;
            return this;
        }

        public Builder setPrefsCalendarFeedEnabled(Boolean prefsCalendarFeedEnabled) {
            this.prefsCalendarFeedEnabled = prefsCalendarFeedEnabled;
            return this;
        }

        public PUT_UpdateBoardPayload build() {
            return new PUT_UpdateBoardPayload(this);
        }
    }
}
