package loggers.custom;

import com.fasterxml.jackson.databind.JsonNode;
import org.fusesource.jansi.Ansi;

import static providers.ProviderObjectMapper.getObjectMapper;

public class JsonColorPrinter {

    // ==========================================================================================================
    // METHODS – MAIN
    // ==========================================================================================================

    public static void print(String json, boolean colorEnabled) {
        try {
            JsonNode node = getObjectMapper().readTree(json);
            printNode(node, 0, colorEnabled);
        } catch (Exception e) {
            if (colorEnabled) {
                ConsoleColors.yellow("Invalid JSON. Display as text:", colorEnabled);
            }
            System.out.println(json);
        }
    }

    // ==========================================================================================================
    // METHODS – SUB
    // ==========================================================================================================

    private static void printNode(JsonNode node, int indent, boolean color) {

        if (node.isObject()) {
            System.out.println("{");

            int size = node.properties().size();
            int index = 0;

            for (var entry : node.properties()) {
                indent(indent + 1);

                String key = color
                        ? Ansi.ansi()
                        .fgMagenta()
                        .a("\"" + entry.getKey() + "\"")
                        .reset()
                        .toString()
                        : "\"" + entry.getKey() + "\"";

                System.out.print(key + ": ");
                printNode(entry.getValue(), indent + 1, color);

                if (index < size - 1) {
                    System.out.print(",");
                }
                System.out.println();

                index++;
            }

            indent(indent);
            System.out.println("}");
            return;
        }

        if (node.isArray()) {
            System.out.println("[");

            for (int i = 0; i < node.size(); i++) {
                indent(indent + 1);
                printNode(node.get(i), indent + 1, color);

                if (i < node.size() - 1) {
                    System.out.print(",");
                }
                System.out.println();
            }

            indent(indent);
            System.out.println("]");
            return;
        }

        String value;
        if (node.isTextual()) {
            value = color
                    ? Ansi.ansi().fgCyan().a("\"" + node.asText() + "\"").reset().toString()
                    : "\"" + node.asText() + "\"";
        } else if (node.isNumber()) {
            value = color
                    ? Ansi.ansi().fgGreen().a(node.numberValue()).reset().toString()
                    : node.asText();
        } else if (node.isBoolean()) {
            value = color
                    ? Ansi.ansi().fgYellow().a(node.asBoolean()).reset().toString()
                    : node.asText();
        } else if (node.isNull()) {
            value = color
                    ? Ansi.ansi().fgRed().a("null").reset().toString()
                    : "null";
        } else {
            value = node.asText();
        }

        System.out.print(value);
    }

    private static void indent(int level) {
        System.out.print("  ".repeat(level));
    }
}
