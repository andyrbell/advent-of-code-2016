import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by abell on 12/12/16.
 */
public class Day07 {
    static boolean supportsTls(String ip) {

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

    private static boolean isAbaOrBab(char[] chars, int i) {
        return chars[i] == chars[i + 2] && chars[i] != chars[i + 1];
    }

    static boolean supportsSsl(String ip) {
        Map<String, String> abaToBab = new HashMap<>();
        Map<String, String> babToAba = new HashMap<>();

        int openBrackets = 0;

        char[] chars = ip.toCharArray();
        for (int i = 0; i < chars.length - 2; i++) {

            if (chars[i] == '[') {
                openBrackets++;
                continue;
            }

            if (chars[i] == ']') {
                if (openBrackets > 0) {
                    openBrackets--;
                }
                continue;
            }

            if (isAbaOrBab(chars, i)) {
                String matched = String.valueOf(chars, i, 3);
                char[] rev = new char[3];
                rev[0] = chars[i + 1];
                rev[1] = chars[i];
                rev[2] = chars[i + 1];
                String reversed = new String(rev);
                if (openBrackets > 0) {
                    babToAba.put(matched, reversed);
                } else {
                    abaToBab.put(matched, reversed);
                }
            }
        }

        return abaToBab.values().stream().filter(babToAba::containsKey).count() > 0;
    }

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("src/main/resources/Day07.txt"));
        System.out.println("Part one answer: " + lines.stream().filter(Day07::supportsTls).count());
        System.out.println("Part two answer: " + lines.stream().filter(Day07::supportsSsl).count());
    }
}
