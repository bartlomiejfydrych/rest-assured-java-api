package utils;

import com.github.javafaker.Faker;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class UtilsCommon {

    private static final Random RANDOM = new Random();
    private static final Faker faker = new Faker();

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

    public static String getRandomSingleChar() {
        return String.valueOf(faker.regexify("[A-Za-z0-9]").charAt(0));
    }

    /**
     * Reads a file from resources and returns its content as a String (UTF-8).
     * Throws a RuntimeException if reading fails, so tests don't need `throws IOException`.
     */
    public static String readResourceFileAsString(String relativePath) {
        try {
            return Files.readString(Paths.get(relativePath), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException("Failed to read resource file: " + relativePath, e);
        }
    }

    public static String getAllEncodedSpecialCharactersInRandomOrder() {

        String encodedCharacters = "%2F%3F%23%3C%3E%22%27%7B%7D%5B%5D%25";

        // We split the string into a list of URL codes (after %XX)
        List<String> tokens = new ArrayList<>();
        for (int i = 0; i < encodedCharacters.length(); i += 3) {
            tokens.add(encodedCharacters.substring(i, i + 3));
        }

        // We mix the order
        Collections.shuffle(tokens);

        // Put it back together into one String
        return String.join("", tokens);
    }
}
