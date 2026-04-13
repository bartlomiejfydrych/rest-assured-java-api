package endpoints.utils.providers;

import endpoints.utils.NamedEndpoint;

import java.util.stream.Stream;

import static endpoints.lists.GET_GetListEndpoint.getList;
import static endpoints.lists.ListsBaseEndpoint.ENDPOINT_LISTS;
import static endpoints.lists.POST_CreateNewListEndpoint.createNewList;
import static endpoints.lists.PUT_UpdateListEndpoint.updateList;

public class ListsEndpointsProvider {

    private static final String DUMMY_ID = "dummyId";
    private static final String ENDPOINT = ENDPOINT_LISTS;

    public static Stream<NamedEndpoint> all() {
        return Stream.of(
                new NamedEndpoint("GET " + ENDPOINT + "/{id}", spec -> getList(DUMMY_ID, spec)),
                new NamedEndpoint("POST " + ENDPOINT, spec -> createNewList(null, "listName", null, spec)),
                new NamedEndpoint("PUT " + ENDPOINT + "/{id}", spec -> updateList(DUMMY_ID, null, spec))
        );
    }
}
