package expected_responses.labels;

import dto.labels.PUT_UpdateLabelDto;

public class PUT_UpdateLabelExpected {

    // ==========================================================================================================
    // FIELDS
    // ==========================================================================================================

    private String id = "DEFAULT_ID";
    private String idBoard = "DEFAULT_BOARD_ID";
    private String name = "DEFAULT_NAME";
    private String color = null;
    private Integer uses = 0;

    // ==========================================================================================================
    // CONSTRUCTORS
    // ==========================================================================================================

    // ----
    // BASE
    // ----

    public static PUT_UpdateLabelExpected base() {
        return new PUT_UpdateLabelExpected();
    }

    // ==========================================================================================================
    // METHODS (replacing data)
    // ==========================================================================================================

    public PUT_UpdateLabelExpected withId(String id) {
        this.id = id;
        return this;
    }

    public PUT_UpdateLabelExpected withBoardId(String idBoard) {
        this.idBoard = idBoard;
        return this;
    }

    public PUT_UpdateLabelExpected withName(String name) {
        this.name = name;
        return this;
    }

    public PUT_UpdateLabelExpected withColor(String color) {
        this.color = color;
        return this;
    }

    public PUT_UpdateLabelExpected withUses(Integer uses) {
        this.uses = uses;
        return this;
    }

    // ==========================================================================================================
    // BUILDER
    // ==========================================================================================================

    public PUT_UpdateLabelDto build() {
        return new PUT_UpdateLabelDto(
                id,
                idBoard,
                name,
                color,
                uses
        );
    }

    // ==========================================================================================================
    // NEGATIVE TESTS (expected responses)
    // ==========================================================================================================

    // -----
    // color
    // -----

    public static final String expectedPutLabelResponseInvalidColor = """
            {
                "message": "invalid value for color",
                "error": "ERROR"
            }
            """;
}
