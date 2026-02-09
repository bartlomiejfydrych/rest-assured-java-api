package expected_responses.labels;

import dto.labels.POST_CreateLabelDto;

import java.util.HashMap;
import java.util.Map;

public class POST_CreateLabelExpected {

    // ==========================================================================================================
    // FIELDS
    // ==========================================================================================================

    private String id = "DEFAULT_ID";
    private String idBoard = "DEFAULT_BOARD_ID";
    private String name = "DEFAULT_NAME";
    private String color = null;
    private Integer uses = 0;
    private Map<String, Object> limits = new HashMap<>(); // new Object(); <- This broke the comparison to the empty object ({})

    // ==========================================================================================================
    // CONSTRUCTORS
    // ==========================================================================================================

    // ----
    // BASE
    // ----

    public static POST_CreateLabelExpected base() {
        return new POST_CreateLabelExpected();
    }

    // ==========================================================================================================
    // METHODS (replacing data)
    // ==========================================================================================================

    public POST_CreateLabelExpected withId(String id) {
        this.id = id;
        return this;
    }

    public POST_CreateLabelExpected withBoardId(String idBoard) {
        this.idBoard = idBoard;
        return this;
    }

    public POST_CreateLabelExpected withName(String name) {
        this.name = name;
        return this;
    }

    public POST_CreateLabelExpected withColor(String color) {
        this.color = color;
        return this;
    }

    public POST_CreateLabelExpected withUses(Integer uses) {
        this.uses = uses;
        return this;
    }

    // Rather for the future, when limits will no longer be empty
    public POST_CreateLabelExpected withLimits(Map<String, Object> limits) {
        this.limits = limits;
        return this;
    }

    // ==========================================================================================================
    // BUILDER
    // ==========================================================================================================

    public POST_CreateLabelDto build() {
        return new POST_CreateLabelDto(
                id,
                idBoard,
                name,
                color,
                uses,
                limits
        );
    }

    // ==========================================================================================================
    // NEGATIVE TESTS (expected responses)
    // ==========================================================================================================

    // -------
    // idBoard
    // -------

    public static final String expectedPostLabelResponseInvalidId = """
            {
                "message": "Invalid id",
                "error": "ERROR"
            }
            """;

    // -----
    // color
    // -----

    public static final String expectedPostLabelResponseInvalidColor = """
            {
                "message": "invalid value for color",
                "error": "ERROR"
            }
            """;
}
