package tests.emoji;

import base.TestBase;
import org.junit.jupiter.api.Test;

import static endpoints.emoji.GET_ListAvailableEmoji.getListAvailableEmoji;
import static org.assertj.core.api.Assertions.assertThat;

public class GET_ListAvailableEmojiTest extends TestBase {

    @Test
    public void P1_shouldGetListAvailableEmoji() {
        // GET
        responseGet = getListAvailableEmoji();
        assertThat(responseGet.statusCode()).isEqualTo(200);
    }
}
