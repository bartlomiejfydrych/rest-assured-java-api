package loggers.custom;

import org.fusesource.jansi.Ansi;

public class ConsoleColors {

    // ==========================================================================================================
    // METHODS – MAIN
    // ==========================================================================================================

    public static void green(String text, boolean enabled) {
        print(text, Ansi.Color.GREEN, enabled);
    }

    public static void purple(String text, boolean enabled) {
        print(text, Ansi.Color.MAGENTA, enabled);
    }

    public static void cyan(String text, boolean enabled) {
        print(text, Ansi.Color.CYAN, enabled);
    }

    public static void yellow(String text, boolean enabled) {
        print(text, Ansi.Color.YELLOW, enabled);
    }

    // ==========================================================================================================
    // METHODS – SUB
    // ==========================================================================================================

    private static void print(String text, Ansi.Color color, boolean enabled) {
        if (enabled) {
            System.out.println(Ansi.ansi().fg(color).a(text).reset());
        } else {
            System.out.println(text);
        }
    }
}
