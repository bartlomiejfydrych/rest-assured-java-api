package utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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

    public static String getAllCharactersSetInRandomOrder() {

        String ALL_CHARACTERS =
                "!\"#$%&\\'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\\\]^_`abcdefghijklmnopqrstuvwxyz{|}~ ęĘóÓąĄśŚłŁżŻźŹćĆńŃ";

        List<Character> characterList = new ArrayList<>();
        for (char c : ALL_CHARACTERS.toCharArray()) {
            characterList.add(c);
        }

        Collections.shuffle(characterList); // random shuffle

        StringBuilder shuffled = new StringBuilder(characterList.size());
        for (char c : characterList) {
            shuffled.append(c);
        }

        return shuffled.toString();
    }
}
