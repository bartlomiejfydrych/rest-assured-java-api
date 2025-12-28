package dto.boards;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import dto.boards.board.DescData;
import dto.boards.board.LabelNames;
import dto.boards.board.Organization;
import dto.boards.board.Prefs;
import jakarta.validation.Valid;

import java.net.URL;

@JsonIgnoreProperties(ignoreUnknown = false)
public class PUT_UpdateBoardDto extends BoardBaseDto {

    // ==========================================================================================================
    // FIELDS
    // ==========================================================================================================

    // NOTE:
    // These variables are also used to call the name of an ignored, redundant field when comparing objects using AssertJ.

    public static final String FIELD_ORGANIZATION = "organization";

    // ==========================================================================================================
    // FIELDS – VALIDATION CONSTRAINTS
    // ==========================================================================================================

    @Valid // <-- validates nested fields if object exists
    @JsonProperty(value = FIELD_ORGANIZATION)
    public Organization organization;

    // ==========================================================================================================
    // CONSTRUCTORS
    // ==========================================================================================================

    @JsonCreator
    public PUT_UpdateBoardDto(
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
        super(id, name, desc, descData, closed, idOrganization, idEnterprise, pinned, url, shortUrl, prefs, labelNames);
    }

    // Empty constructor - needed to be able to assign values manually
    public PUT_UpdateBoardDto() {
        super();
    }
}

/*
########################################################################################################################
MY ADDITIONAL NOTES
########################################################################################################################

------------
organization
------------

Pełna odpowiedź w notatkach (notes).

PYTANIE:

Dlaczego tutaj pole "organization" zostawiliśmy osobno, nie podając go z pozostałymi parametrami w @JsonCreator?
Z tego, co pamiętam, to obiekt ten raz się pojawia w response, a raz nie.
I dlaczego ma on @JsonProperty(FIELD_ORGANIZATION) nad sobą?

ODPOWIEDŹ:

Pole `organization`:

- raz występuje w response, raz nie
- nie jest wymagane zawsze
- ma być walidowane tylko wtedy, gdy się pojawi
- nie może powodować błędu deserializacji, gdy go brak

➡️ dlatego:

- NIE jest w `@JsonCreator`
- JEST polem z `@JsonProperty`
- MA `@Valid`, ale NIE `@NotNull`

*/
