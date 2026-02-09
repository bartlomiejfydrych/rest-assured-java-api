package expected_responses.lists;

import dto.lists.PUT_UpdateListDto;

public class PUT_UpdateListExpected {

    // ==========================================================================================================
    // FIELDS
    // ==========================================================================================================

    // --------
    // REQUIRED
    // --------

    private String id = "DEFAULT_ID";
    private String name = "DEFAULT_NAME";
    private Boolean closed = false;
    private String color = null;
    private String idBoard = "DEFAULT_BOARD_ID";
    private Long pos = 1L;

    // --------
    // OPTIONAL
    // --------

    private Boolean subscribed = null;

    // ==========================================================================================================
    // CONSTRUCTORS
    // ==========================================================================================================

    // ----
    // BASE
    // ----

    public static PUT_UpdateListExpected base() {
        return new PUT_UpdateListExpected();
    }

    // ==========================================================================================================
    // METHODS (replacing data)
    // ==========================================================================================================

    public PUT_UpdateListExpected withId(String id) {
        this.id = id;
        return this;
    }

    public PUT_UpdateListExpected withName(String name) {
        this.name = name;
        return this;
    }

    public PUT_UpdateListExpected withClosed(Boolean closed) {
        this.closed = closed;
        return this;
    }

    public PUT_UpdateListExpected withColor(String color) {
        this.color = color;
        return this;
    }

    public PUT_UpdateListExpected withBoardId(String idBoard) {
        this.idBoard = idBoard;
        return this;
    }

    public PUT_UpdateListExpected withPos(Long pos) {
        this.pos = pos;
        return this;
    }

    public PUT_UpdateListExpected withSubscribed(Boolean subscribed) {
        this.subscribed = subscribed;
        return this;
    }

    // ==========================================================================================================
    // BUILDER
    // ==========================================================================================================

    public PUT_UpdateListDto build() {
        PUT_UpdateListDto dto = new PUT_UpdateListDto(
                id,
                name,
                closed,
                color,
                idBoard,
                pos
        );

        // IMPORTANT: subscribed is optional in response
        if (subscribed != null) {
            dto.subscribed = subscribed;
        }

        return dto;
    }

    // ==========================================================================================================
    // NEGATIVE TESTS (expected responses)
    // ==========================================================================================================

    // --
    // id
    // --

    public static final String expectedPutUpdateListResponseInvalidId = "invalid id";

    // -------
    // idBoard
    // -------

    public static final String expectedPutUpdateListResponseInvalidBoardId = """
            {
                "message": "invalid id",
                "error": "BAD_REQUEST_ERROR"
            }
            """;

    public static final String expectedPutUpdateListResponseBoardNotFound = """
            {
                "message": "Board not found.",
                "error": "BOARD_NOT_FOUND"
            }
            """;

    // ---
    // pos
    // ---

    public static final String expectedPutUpdateListResponseInvalidPosition = """
            {
                "message": "Invalid position.",
                "error": "ERROR"
            }
            """;
}
