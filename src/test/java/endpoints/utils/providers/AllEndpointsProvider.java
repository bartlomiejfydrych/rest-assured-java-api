package endpoints.utils.providers;

import endpoints.utils.NamedEndpoint;

import java.util.stream.Stream;

public class AllEndpointsProvider {

    public static Stream<NamedEndpoint> all() {
        return Stream.of(
                BoardsEndpointsProvider.all(),
                LabelsEndpointsProvider.all(),
                ListsEndpointsProvider.all(),
                EmojiEndpointsProvider.all()
        ).flatMap(s -> s);
    }
}
