package dto.boards.board;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import dto.boards.board.desc_data.Emoji;
import jakarta.validation.constraints.NotNull;

@JsonIgnoreProperties(ignoreUnknown = false)
public class DescData {

    @NotNull
    public Emoji emoji;

    @JsonCreator
    public DescData(@JsonProperty(value = "emoji", required = true) Emoji emoji) {
        this.emoji = emoji;
    }

    public DescData() {
    }
}
