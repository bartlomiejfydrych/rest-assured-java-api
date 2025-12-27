package dto.boards.board;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

@JsonIgnoreProperties(ignoreUnknown = false)
public class LabelNames {

    // ==========================================================================================================
    // FIELDS
    // ==========================================================================================================

    public static final String FIELD_GREEN = "green";
    public static final String FIELD_YELLOW = "yellow";
    public static final String FIELD_ORANGE = "orange";
    public static final String FIELD_RED = "red";
    public static final String FIELD_PURPLE = "purple";
    public static final String FIELD_BLUE = "blue";
    public static final String FIELD_SKY = "sky";
    public static final String FIELD_LIME = "lime";
    public static final String FIELD_PINK = "pink";
    public static final String FIELD_BLACK = "black";

    public static final String FIELD_GREEN_DARK = "green_dark";
    public static final String FIELD_YELLOW_DARK = "yellow_dark";
    public static final String FIELD_ORANGE_DARK = "orange_dark";
    public static final String FIELD_RED_DARK = "red_dark";
    public static final String FIELD_PURPLE_DARK = "purple_dark";
    public static final String FIELD_BLUE_DARK = "blue_dark";
    public static final String FIELD_SKY_DARK = "sky_dark";
    public static final String FIELD_LIME_DARK = "lime_dark";
    public static final String FIELD_PINK_DARK = "pink_dark";
    public static final String FIELD_BLACK_DARK = "black_dark";

    public static final String FIELD_GREEN_LIGHT = "green_light";
    public static final String FIELD_YELLOW_LIGHT = "yellow_light";
    public static final String FIELD_ORANGE_LIGHT = "orange_light";
    public static final String FIELD_RED_LIGHT = "red_light";
    public static final String FIELD_PURPLE_LIGHT = "purple_light";
    public static final String FIELD_BLUE_LIGHT = "blue_light";
    public static final String FIELD_SKY_LIGHT = "sky_light";
    public static final String FIELD_LIME_LIGHT = "lime_light";
    public static final String FIELD_PINK_LIGHT = "pink_light";
    public static final String FIELD_BLACK_LIGHT = "black_light";

    // ==========================================================================================================
    // FIELDS – VALIDATION CONSTRAINTS
    // ==========================================================================================================

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
    public String greenDark;
    @NotNull
    public String yellowDark;
    @NotNull
    public String orangeDark;
    @NotNull
    public String redDark;
    @NotNull
    public String purpleDark;
    @NotNull
    public String blueDark;
    @NotNull
    public String skyDark;
    @NotNull
    public String limeDark;
    @NotNull
    public String pinkDark;
    @NotNull
    public String blackDark;

    @NotNull
    public String greenLight;
    @NotNull
    public String yellowLight;
    @NotNull
    public String orangeLight;
    @NotNull
    public String redLight;
    @NotNull
    public String purpleLight;
    @NotNull
    public String blueLight;
    @NotNull
    public String skyLight;
    @NotNull
    public String limeLight;
    @NotNull
    public String pinkLight;
    @NotNull
    public String blackLight;

    // ==========================================================================================================
    // CONSTRUCTORS
    // ==========================================================================================================

    @JsonCreator
    public LabelNames(
            @JsonProperty(value = FIELD_GREEN, required = true) String green,
            @JsonProperty(value = FIELD_YELLOW, required = true) String yellow,
            @JsonProperty(value = FIELD_ORANGE, required = true) String orange,
            @JsonProperty(value = FIELD_RED, required = true) String red,
            @JsonProperty(value = FIELD_PURPLE, required = true) String purple,
            @JsonProperty(value = FIELD_BLUE, required = true) String blue,
            @JsonProperty(value = FIELD_SKY, required = true) String sky,
            @JsonProperty(value = FIELD_LIME, required = true) String lime,
            @JsonProperty(value = FIELD_PINK, required = true) String pink,
            @JsonProperty(value = FIELD_BLACK, required = true) String black,

            @JsonProperty(value = FIELD_GREEN_DARK, required = true) String greenDark,
            @JsonProperty(value = FIELD_YELLOW_DARK, required = true) String yellowDark,
            @JsonProperty(value = FIELD_ORANGE_DARK, required = true) String orangeDark,
            @JsonProperty(value = FIELD_RED_DARK, required = true) String redDark,
            @JsonProperty(value = FIELD_PURPLE_DARK, required = true) String purpleDark,
            @JsonProperty(value = FIELD_BLUE_DARK, required = true) String blueDark,
            @JsonProperty(value = FIELD_SKY_DARK, required = true) String skyDark,
            @JsonProperty(value = FIELD_LIME_DARK, required = true) String limeDark,
            @JsonProperty(value = FIELD_PINK_DARK, required = true) String pinkDark,
            @JsonProperty(value = FIELD_BLACK_DARK, required = true) String blackDark,

            @JsonProperty(value = FIELD_GREEN_LIGHT, required = true) String greenLight,
            @JsonProperty(value = FIELD_YELLOW_LIGHT, required = true) String yellowLight,
            @JsonProperty(value = FIELD_ORANGE_LIGHT, required = true) String orangeLight,
            @JsonProperty(value = FIELD_RED_LIGHT, required = true) String redLight,
            @JsonProperty(value = FIELD_PURPLE_LIGHT, required = true) String purpleLight,
            @JsonProperty(value = FIELD_BLUE_LIGHT, required = true) String blueLight,
            @JsonProperty(value = FIELD_SKY_LIGHT, required = true) String skyLight,
            @JsonProperty(value = FIELD_LIME_LIGHT, required = true) String limeLight,
            @JsonProperty(value = FIELD_PINK_LIGHT, required = true) String pinkLight,
            @JsonProperty(value = FIELD_BLACK_LIGHT, required = true) String blackLight
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

        this.greenDark = greenDark;
        this.yellowDark = yellowDark;
        this.orangeDark = orangeDark;
        this.redDark = redDark;
        this.purpleDark = purpleDark;
        this.blueDark = blueDark;
        this.skyDark = skyDark;
        this.limeDark = limeDark;
        this.pinkDark = pinkDark;
        this.blackDark = blackDark;

        this.greenLight = greenLight;
        this.yellowLight = yellowLight;
        this.orangeLight = orangeLight;
        this.redLight = redLight;
        this.purpleLight = purpleLight;
        this.blueLight = blueLight;
        this.skyLight = skyLight;
        this.limeLight = limeLight;
        this.pinkLight = pinkLight;
        this.blackLight = blackLight;
    }

    // Empty constructor - needed to be able to assign values manually
    public LabelNames() {
    }
}
