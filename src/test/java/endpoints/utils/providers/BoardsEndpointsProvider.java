package endpoints.utils.providers;

import endpoints.utils.NamedEndpoint;

import java.util.stream.Stream;

import static endpoints.boards.BoardsBaseEndpoint.ENDPOINT_BOARDS;
import static endpoints.boards.DEL_DeleteBoardEndpoint.deleteBoard;
import static endpoints.boards.GET_GetBoardEndpoint.getBoard;
import static endpoints.boards.POST_CreateBoardEndpoint.createBoard;
import static endpoints.boards.PUT_UpdateBoardEndpoint.updateBoard;

public class BoardsEndpointsProvider {

    private static final String DUMMY_ID = "dummyId";
    private static final String ENDPOINT = ENDPOINT_BOARDS;

    public static Stream<NamedEndpoint> all() {
        return Stream.of(
                new NamedEndpoint("DELETE " + ENDPOINT + "/{id}", spec -> deleteBoard(DUMMY_ID, spec)),
                new NamedEndpoint("GET " + ENDPOINT + "/{id}", spec -> getBoard(DUMMY_ID, spec)),
                new NamedEndpoint("POST " + ENDPOINT, spec -> createBoard("testBoard", null, spec)),
                new NamedEndpoint("PUT " + ENDPOINT + "/{id}", spec -> updateBoard(DUMMY_ID, null, spec))
        );
    }
}
