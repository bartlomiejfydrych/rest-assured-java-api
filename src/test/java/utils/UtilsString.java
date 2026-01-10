package utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static providers.RandomProvider.random;

public final class UtilsString {

    // ==========================================================================================================
    // FIELDS
    // ==========================================================================================================

    private static final String ALPHANUMERIC = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final String ALL_CHARACTERS =
            "!\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~ ęĘóÓąĄśŚłŁżŻźŹćĆńŃ";
    private static final List<String> ENCODED_SPECIAL_CHARS = List.of(
            "%2F", "%3F", "%23", "%3C", "%3E",
            "%22", "%27", "%7B", "%7D", "%5B", "%5D", "%25"
    );

    // ==========================================================================================================
    // METHODS – MAIN
    // ==========================================================================================================

    public static String getRandomSingleChar(String allowedChars) {
        if (allowedChars == null || allowedChars.isEmpty()) {
            throw new IllegalArgumentException("Allowed characters cannot be empty");
        }
        return String.valueOf(
                allowedChars.charAt(random().nextInt(allowedChars.length()))
        );
    }

    public static String getRandomSingleCharAlphanumeric() {
        return getRandomSingleChar(ALPHANUMERIC);
    }

    public static String getAllCharactersSetInRandomOrder() {
        return shuffleCharacters(ALL_CHARACTERS);
    }

    public static String getAllEncodedSpecialCharactersInRandomOrder() {
        List<String> shuffled = new ArrayList<>(ENCODED_SPECIAL_CHARS);
        Collections.shuffle(shuffled, random());
        return String.join("", shuffled);
    }

    // ==========================================================================================================
    // METHODS – SUB
    // ==========================================================================================================

    private static String shuffleCharacters(String input) {
        List<Character> chars = new ArrayList<>(
                input.chars()
                        .mapToObj(c -> (char) c)
                        .toList()
        );

        Collections.shuffle(chars, random());

        StringBuilder sb = new StringBuilder(chars.size());
        chars.forEach(sb::append);
        return sb.toString();
    }
}
