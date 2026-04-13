package endpoints.utils.providers;

import endpoints.utils.NamedEndpoint;

import java.util.stream.Stream;

import static endpoints.emoji.EmojiBaseEndpoint.ENDPOINT_EMOJI;
import static endpoints.emoji.GET_ListAvailableEmojiEndpoint.listAvailableEmoji;

public class EmojiEndpointsProvider {

    private static final String ENDPOINT = ENDPOINT_EMOJI;

    public static Stream<NamedEndpoint> all() {
        return Stream.of(
                new NamedEndpoint("GET " + ENDPOINT, spec -> listAvailableEmoji(null, spec))
        );
    }
}
