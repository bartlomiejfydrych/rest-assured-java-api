package dto.boards;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import dto.boards.board.DescData;
import dto.boards.board.LabelNames;
import dto.boards.board.Prefs;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.net.URL;

@JsonIgnoreProperties(ignoreUnknown = false)
public class BoardBaseDto {

    // ==========================================================================================================
    // FIELDS
    // ==========================================================================================================

    // NOTE:
    // These variables are also used to call the name of an ignored, redundant field when comparing objects using AssertJ.

    public static final String FIELD_ID = "id";
    public static final String FIELD_NAME = "name";
    public static final String FIELD_DESC = "desc";
    public static final String FIELD_DESC_DATA = "descData";
    public static final String FIELD_CLOSED = "closed";
    public static final String FIELD_ID_ORGANIZATION = "idOrganization";
    public static final String FIELD_ID_ENTERPRISE = "idEnterprise";
    public static final String FIELD_PINNED = "pinned";
    public static final String FIELD_URL = "url";
    public static final String FIELD_SHORT_URL = "shortUrl";
    public static final String FIELD_PREFS = "prefs";
    public static final String FIELD_LABEL_NAMES = "labelNames";

    // ==========================================================================================================
    // FIELDS – VALIDATION CONSTRAINTS
    // ==========================================================================================================

    @NotNull
    @Pattern(regexp = "^[0-9a-fA-F]{24}$")
    public String id;

    @NotNull
    @Size(min = 1, max = 16384, message = "'name' must be between {min} and {max} characters long")
    public String name;

    @NotNull
    @Size(max = 16384)
    public String desc;

    @Valid // <-- validates nested fields if object exists
    public DescData descData;

    @NotNull
    public Boolean closed;

    @NotNull
    @Pattern(regexp = "^[0-9a-fA-F]{24}$")
    public String idOrganization;

    // The @Valid annotation returns warnings for unspecified objects. It should only be uncommented once we have a specified object type.
    // @Valid // <-- validates nested fields if object exists
    public Object idEnterprise;

    @NotNull
    public Boolean pinned;

    @NotNull
    public URL url;

    @NotNull
    public URL shortUrl;

    @NotNull
    public Prefs prefs;

    @NotNull
    public LabelNames labelNames;

    // ==========================================================================================================
    // CONSTRUCTORS
    // ==========================================================================================================

    @JsonCreator
    public BoardBaseDto(
            @JsonProperty(value = FIELD_ID, required = true) String id,
            @JsonProperty(value = FIELD_NAME, required = true) String name,
            @JsonProperty(value = FIELD_DESC, required = true) String desc,
            @JsonProperty(value = FIELD_DESC_DATA, required = true) DescData descData,
            @JsonProperty(value = FIELD_CLOSED, required = true) Boolean closed,
            @JsonProperty(value = FIELD_ID_ORGANIZATION, required = true) String idOrganization,
            @JsonProperty(value = FIELD_ID_ENTERPRISE, required = true) Object idEnterprise,
            @JsonProperty(value = FIELD_PINNED, required = true) Boolean pinned,
            @JsonProperty(value = FIELD_URL, required = true) URL url,
            @JsonProperty(value = FIELD_SHORT_URL, required = true) URL shortUrl,
            @JsonProperty(value = FIELD_PREFS, required = true) Prefs prefs,
            @JsonProperty(value = FIELD_LABEL_NAMES, required = true) LabelNames labelNames
    ) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.descData = descData;
        this.closed = closed;
        this.idOrganization = idOrganization;
        this.idEnterprise = idEnterprise;
        this.pinned = pinned;
        this.url = url;
        this.shortUrl = shortUrl;
        this.prefs = prefs;
        this.labelNames = labelNames;
    }

    // Empty constructor - needed to be able to assign values manually
    public BoardBaseDto() {
    }
}
