package tests.unit;

import org.junit.jupiter.api.Test;
import utils.UtilsString;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class UtilsStringTest {

    // ==========================================================================================================
    // getRandomSingleChar(String)
    // ==========================================================================================================

    @Test
    void getRandomSingleChar_whenNull_shouldThrowException() {
        assertThrows(
                IllegalArgumentException.class,
                () -> UtilsString.getRandomSingleChar(null)
        );
    }

    @Test
    void getRandomSingleChar_whenEmpty_shouldThrowException() {
        assertThrows(
                IllegalArgumentException.class,
                () -> UtilsString.getRandomSingleChar("")
        );
    }

    @Test
    void getRandomSingleChar_whenValidChars_shouldReturnSingleAllowedChar() {
        String allowed = "ABC123";

        String result = UtilsString.getRandomSingleChar(allowed);

        assertEquals(1, result.length());
        assertTrue(
                allowed.contains(result),
                "Returned character must be from allowed set"
        );
    }

    // ==========================================================================================================
    // getRandomSingleCharAlphanumeric()
    // ==========================================================================================================

    @Test
    void getRandomSingleCharAlphanumeric_shouldReturnSingleAlphanumericChar() {
        String result = UtilsString.getRandomSingleCharAlphanumeric();

        assertEquals(1, result.length());
        assertTrue(
                result.matches("[A-Za-z0-9]"),
                "Returned character must be alphanumeric"
        );
    }

    // ==========================================================================================================
    // getAllCharactersSetInRandomOrder()
    // ==========================================================================================================

    @Test
    void getAllCharactersSetInRandomOrder_shouldContainSameCharacters() {
        String shuffled = UtilsString.getAllCharactersSetInRandomOrder();
        String original =
                "!\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~ ęĘóÓąĄśŚłŁżŻźŹćĆńŃ";

        assertEquals(original.length(), shuffled.length());
        assertEquals(
                toCharSet(original),
                toCharSet(shuffled),
                "Shuffled string must contain exactly the same characters"
        );
    }

    // ==========================================================================================================
    // getAllEncodedSpecialCharactersInRandomOrder()
    // ==========================================================================================================

    @Test
    void getAllEncodedSpecialCharactersInRandomOrder_shouldContainAllEncodedValues() {
        String result = UtilsString.getAllEncodedSpecialCharactersInRandomOrder();

        List<String> expected = List.of(
                "%2F", "%3F", "%23", "%3C", "%3E",
                "%22", "%27", "%7B", "%7D", "%5B", "%5D", "%25"
        );

        for (String encoded : expected) {
            assertTrue(
                    result.contains(encoded),
                    "Result should contain encoded value: " + encoded
            );
        }
    }

    // ==========================================================================================================
    // HELPERS
    // ==========================================================================================================

    private Set<Character> toCharSet(String input) {
        Set<Character> set = new HashSet<>();
        for (char c : input.toCharArray()) {
            set.add(c);
        }
        return set;
    }
}
