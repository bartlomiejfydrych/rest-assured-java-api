package utils;

import java.util.Random;

public class UtilsCommon {

    private static final Random RANDOM = new Random();

    @SafeVarargs
    public static <T> T pickRandom(T... options) {
        if (options == null || options.length == 0) {
            throw new IllegalArgumentException("No options provided");
        }
        return options[RANDOM.nextInt(options.length)];
    }
}
