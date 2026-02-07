package expected_responses.lists;

import dto.lists.POST_CreateNewListDto;
import dto.lists.list.DataSource;

import java.util.HashMap;
import java.util.Map;

public class POST_CreateNewListExpected {

    // ==========================================================================================================
    // FIELDS
    // ==========================================================================================================

    private String id = "DEFAULT_ID";
    private String name = "DEFAULT_NAME";
    private Boolean closed = false;
    private String color = null;
    private String idBoard = "DEFAULT_BOARD_ID";
    private Long pos = 1L;
    private String type = null;
    private DataSource datasource = new DataSource(false);
    private Map<String, Object> limits = new HashMap<>(); // new Object(); <- This broke the comparison to the empty object ({})

    // ==========================================================================================================
    // CONSTRUCTORS
    // ==========================================================================================================

    // ----
    // BASE
    // ----

    public static POST_CreateNewListExpected base() {
        return new POST_CreateNewListExpected();
    }

    // ==========================================================================================================
    // METHODS (replacing data)
    // ==========================================================================================================

    public POST_CreateNewListExpected withId(String id) {
        this.id = id;
        return this;
    }

    public POST_CreateNewListExpected withName(String name) {
        this.name = name;
        return this;
    }

    public POST_CreateNewListExpected withBoardId(String idBoard) {
        this.idBoard = idBoard;
        return this;
    }

    public POST_CreateNewListExpected withPos(Long pos) {
        this.pos = pos;
        return this;
    }

    // ==========================================================================================================
    // BUILDER
    // ==========================================================================================================

    public POST_CreateNewListDto build() {
        return new POST_CreateNewListDto(
                id,
                name,
                closed,
                color,
                idBoard,
                pos,
                type,
                datasource,
                limits
        );
    }

    // ==========================================================================================================
    // NEGATIVE TESTS (expected responses)
    // ==========================================================================================================

    // ----
    // name
    // ----

    public static final String expectedPostNewListResponseInvalidName = "invalid value for name";

    // -------
    // idBoard
    // -------

    public static final String expectedPostNewListResponseInvalidIdBoard = "invalid value for idBoard";

    // ------------
    // idListSource
    // ------------

    public static final String expectedPostNewListResponseInvalidIdListSource = """
            {
                "message": "Invalid objectId",
                "error": "ERROR"
            }
            """;

    // ---
    // pos
    // ---

    public static final String expectedPostNewListResponseInvalidPos = """
            {
                "message": "Invalid position.",
                "error": "ERROR"
            }
            """;
}
