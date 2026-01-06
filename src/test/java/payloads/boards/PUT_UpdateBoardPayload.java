package payloads.boards;

import payloads.BasePayload;

import java.util.HashMap;
import java.util.Map;

import static enums.query_parameters.boards.boards.BoardBaseQueryParameters.*;
import static enums.query_parameters.boards.boards.PUT_UpdateBoardQueryParameters.*;

public class PUT_UpdateBoardPayload extends BasePayload {

    // ==========================================================================================================
    // FIELDS – QUERY PARAMETERS
    // ==========================================================================================================

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

    // ==========================================================================================================
    // HELPER METHOD – CONVERTS A CLASS OBJECT TO QUERY PARAMETER MAP
    // ==========================================================================================================

    public Map<String, Object> toQueryParams() {

        Map<String, Object> params = new HashMap<>();

        putIfNotNull(params, NAME, name);
        putIfNotNull(params, DESC, desc);
        putIfNotNull(params, CLOSED, closed);
        putIfNotNull(params, SUBSCRIBED, subscribed);
        putIfNotNull(params, ID_ORGANIZATION, idOrganization);
        putIfNotNull(params, PREFS_PERMISSION_LEVEL, prefsPermissionLevel);
        putIfNotNull(params, PREFS_SELF_JOIN, prefsSelfJoin);
        putIfNotNull(params, PREFS_CARD_COVERS, prefsCardCovers);
        putIfNotNull(params, PREFS_HIDE_VOTES, prefsHideVotes);
        putIfNotNull(params, PREFS_INVITATIONS, prefsInvitations);
        putIfNotNull(params, PREFS_VOTING, prefsVoting);
        putIfNotNull(params, PREFS_COMMENTS, prefsComments);
        putIfNotNull(params, PREFS_BACKGROUND, prefsBackground);
        putIfNotNull(params, PREFS_CARD_AGING, prefsCardAging);
        putIfNotNull(params, PREFS_CALENDAR_FEED_ENABLED, prefsCalendarFeedEnabled);

        return params;
    }

    // ==========================================================================================================
    // EXAMPLE OF USE
    // ==========================================================================================================

    /*

    PUT_UpdateBoardPayload payload = new PUT_UpdateBoardPayload.Builder()
        .setName("Zmieniona tablica")
        .setPrefsBackground("lime")
        .setPrefsSelfJoin(true)
        .setPrefsCardAging("Błąd krytyczny")
        .build();

    Map<String, Object> queryParams = payload.toQueryParams();

    */

    // ==========================================================================================================
    // PRIVATE CONSTRUCTOR (ACCESSIBLE ONLY THROUGH BUILDER)
    // ==========================================================================================================

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

    // ==========================================================================================================
    // BUILDER
    // ==========================================================================================================

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
