import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created by abell on 12/12/16.
 */
public class Day07 {
    static boolean valid(String ip) {

        boolean foundOutsideBrackets = false;
        boolean foundInsideBrackets = false;
        boolean foundMaybeInsideBrackets = false;
        int openBrackets = 0;

        char[] chars = ip.toCharArray();
        for (int i = 0; i < chars.length - 3; i++) {

            if (chars[i] == '[') {
                openBrackets++;
                continue;
            }

            if (chars[i] == ']') {
                if (openBrackets > 0) {
                    openBrackets--;
                    if (foundMaybeInsideBrackets) {
                        foundInsideBrackets = true;
                        foundMaybeInsideBrackets = false;
                    }
                }
                continue;
            }

            if (isAbba(chars, i)) {
                if (openBrackets > 0) {
                    foundMaybeInsideBrackets = true;
                } else {
                    foundOutsideBrackets = true;
                }
            }
        }
        return foundOutsideBrackets && !foundInsideBrackets;
    }

    private static boolean isAbba(char[] chars, int i) {
        return chars[i] == chars[i + 3] && chars[i + 1] == chars[i + 2] && chars[i] != chars[i + 1];
    }

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("src/main/resources/Day07.txt"));
        System.out.println("Part one answer: " + lines.stream().filter(Day07::valid).count());
    }
}
