package tests.unit;

import org.junit.jupiter.api.Test;
import utils.UtilsRandom;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UtilsRandomTest {

    // ==========================================================================================================
    // pickRandom(T...)
    // ==========================================================================================================

    @Test
    void pickRandom_varargs_whenNull_shouldThrowException() {
        assertThrows(
                IllegalArgumentException.class,
                () -> UtilsRandom.pickRandom((String[]) null)
        );
    }

    @Test
    void pickRandom_varargs_whenEmpty_shouldThrowException() {
        assertThrows(
                IllegalArgumentException.class,
                () -> UtilsRandom.pickRandom()
        );
    }

    @Test
    void pickRandom_varargs_whenValidOptions_shouldReturnOneOfThem() {
        String a = "A";
        String b = "B";
        String c = "C";

        String result = UtilsRandom.pickRandom(a, b, c);

        assertTrue(
                List.of(a, b, c).contains(result),
                "Returned value must be one of provided options"
        );
    }

    // ==========================================================================================================
    // pickRandom(List<T>)
    // ==========================================================================================================

    @Test
    void pickRandom_list_whenNull_shouldThrowException() {
        assertThrows(
                IllegalArgumentException.class,
                () -> UtilsRandom.pickRandom((List<String>) null)
        );
    }

    @Test
    void pickRandom_list_whenEmpty_shouldThrowException() {
        assertThrows(
                IllegalArgumentException.class,
                () -> UtilsRandom.pickRandom(List.of())
        );
    }

    @Test
    void pickRandom_list_whenValidOptions_shouldReturnOneOfThem() {
        List<Integer> options = List.of(1, 2, 3, 4, 5);

        Integer result = UtilsRandom.pickRandom(options);

        assertTrue(
                options.contains(result),
                "Returned value must be one of provided options"
        );
    }
}
