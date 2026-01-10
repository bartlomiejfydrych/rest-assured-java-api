package utils;

import providers.RandomProvider;

import java.util.List;

public final class UtilsRandom {

    // ==========================================================================================================
    // CONSTRUCTOR
    // ==========================================================================================================

    private UtilsRandom() {
    }

    // ==========================================================================================================
    // METHODS – MAIN
    // ==========================================================================================================

    @SafeVarargs
    public static <T> T pickRandom(T... options) {
        if (options == null || options.length == 0) {
            throw new IllegalArgumentException("pickRandom() requires at least one option");
        }
        return options[RandomProvider.random().nextInt(options.length)];
    }

    public static <T> T pickRandom(List<T> options) {
        if (options == null || options.isEmpty()) {
            throw new IllegalArgumentException("pickRandom() requires at least one option");
        }
        return options.get(RandomProvider.random().nextInt(options.size()));
    }
}
