package dto.boards.board;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

public class LabelNames {

    @NotNull
    public String green;
    @NotNull
    public String yellow;
    @NotNull
    public String orange;
    @NotNull
    public String red;
    @NotNull
    public String purple;
    @NotNull
    public String blue;
    @NotNull
    public String sky;
    @NotNull
    public String lime;
    @NotNull
    public String pink;
    @NotNull
    public String black;

    @NotNull
    public String green_dark;
    @NotNull
    public String yellow_dark;
    @NotNull
    public String orange_dark;
    @NotNull
    public String red_dark;
    @NotNull
    public String purple_dark;
    @NotNull
    public String blue_dark;
    @NotNull
    public String sky_dark;
    @NotNull
    public String lime_dark;
    @NotNull
    public String pink_dark;
    @NotNull
    public String black_dark;

    @NotNull
    public String green_light;
    @NotNull
    public String yellow_light;
    @NotNull
    public String orange_light;
    @NotNull
    public String red_light;
    @NotNull
    public String purple_light;
    @NotNull
    public String blue_light;
    @NotNull
    public String sky_light;
    @NotNull
    public String lime_light;
    @NotNull
    public String pink_light;
    @NotNull
    public String black_light;

    @JsonCreator
    public LabelNames(
            @JsonProperty(value = "green", required = true) String green,
            @JsonProperty(value = "yellow", required = true) String yellow,
            @JsonProperty(value = "orange", required = true) String orange,
            @JsonProperty(value = "red", required = true) String red,
            @JsonProperty(value = "purple", required = true) String purple,
            @JsonProperty(value = "blue", required = true) String blue,
            @JsonProperty(value = "sky", required = true) String sky,
            @JsonProperty(value = "lime", required = true) String lime,
            @JsonProperty(value = "pink", required = true) String pink,
            @JsonProperty(value = "black", required = true) String black,

            @JsonProperty(value = "green_dark", required = true) String green_dark,
            @JsonProperty(value = "yellow_dark", required = true) String yellow_dark,
            @JsonProperty(value = "orange_dark", required = true) String orange_dark,
            @JsonProperty(value = "red_dark", required = true) String red_dark,
            @JsonProperty(value = "purple_dark", required = true) String purple_dark,
            @JsonProperty(value = "blue_dark", required = true) String blue_dark,
            @JsonProperty(value = "sky_dark", required = true) String sky_dark,
            @JsonProperty(value = "lime_dark", required = true) String lime_dark,
            @JsonProperty(value = "pink_dark", required = true) String pink_dark,
            @JsonProperty(value = "black_dark", required = true) String black_dark,

            @JsonProperty(value = "green_light", required = true) String green_light,
            @JsonProperty(value = "yellow_light", required = true) String yellow_light,
            @JsonProperty(value = "orange_light", required = true) String orange_light,
            @JsonProperty(value = "red_light", required = true) String red_light,
            @JsonProperty(value = "purple_light", required = true) String purple_light,
            @JsonProperty(value = "blue_light", required = true) String blue_light,
            @JsonProperty(value = "sky_light", required = true) String sky_light,
            @JsonProperty(value = "lime_light", required = true) String lime_light,
            @JsonProperty(value = "pink_light", required = true) String pink_light,
            @JsonProperty(value = "black_light", required = true) String black_light
    ) {
        this.green = green;
        this.yellow = yellow;
        this.orange = orange;
        this.red = red;
        this.purple = purple;
        this.blue = blue;
        this.sky = sky;
        this.lime = lime;
        this.pink = pink;
        this.black = black;

        this.green_dark = green_dark;
        this.yellow_dark = yellow_dark;
        this.orange_dark = orange_dark;
        this.red_dark = red_dark;
        this.purple_dark = purple_dark;
        this.blue_dark = blue_dark;
        this.sky_dark = sky_dark;
        this.lime_dark = lime_dark;
        this.pink_dark = pink_dark;
        this.black_dark = black_dark;

        this.green_light = green_light;
        this.yellow_light = yellow_light;
        this.orange_light = orange_light;
        this.red_light = red_light;
        this.purple_light = purple_light;
        this.blue_light = blue_light;
        this.sky_light = sky_light;
        this.lime_light = lime_light;
        this.pink_light = pink_light;
        this.black_light = black_light;
    }

    public LabelNames() {
    }
}
