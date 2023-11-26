package main.tools;

public class FormatForGraphViz {
    public static String formatForGraphviz(String name) {
        if (name.contains(" ") || name.contains("\"")) {
            return "\"" + name.replace("\"", "\\\"") + "\"";
        }
        return name;
    }
}
