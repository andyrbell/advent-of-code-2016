import lombok.Value;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by abell on 08/12/16.
 */
public class Day03 {

    static final Pattern pattern = Pattern.compile("[\\s]*(\\d*)[\\s]*(\\d*)[\\s]*(\\d*)[\\s]*");

    @Value(staticConstructor = "of")
    static class Triangle {
        int a;
        int b;
        int c;
    }

    static boolean isValid(Triangle t) {
        return t != null && isValid(t.getA(), t.getB(), t.getC());
    }

    static boolean isValid(int a, int b, int c) {
        return a + b > c && b + c > a && a + c > b;
    }

    static int[] parseInts(String line) {
        Matcher matcher = pattern.matcher(line);

        if (matcher.matches()) {
            int a = Integer.parseInt(matcher.group(1));
            int b = Integer.parseInt(matcher.group(2));
            int c = Integer.parseInt(matcher.group(3));

            return new int[] { a, b, c };
        }
        return null;

    }

    static Triangle parse(String line) {

        int[] sides = parseInts(line);

        if (sides != null) {
            return Triangle.of(sides[0], sides[1], sides[2]);
        }
        return null;
    }

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("src/main/resources/Day03.txt"));

        long count = lines.stream().map(Day03::parse).filter(Day03::isValid).count();

        System.out.println("Part one answer: " + count);

        List<Triangle> triangles = new ArrayList<>();

        for (int i = 0; i < lines.size(); i += 3) {
            int[] row1 = parseInts(lines.get(i));
            int[] row2 = parseInts(lines.get(i + 1));
            int[] row3 = parseInts(lines.get(i + 2));

            triangles.add(Triangle.of(row1[0], row2[0], row3[0]));
            triangles.add(Triangle.of(row1[1], row2[1], row3[1]));
            triangles.add(Triangle.of(row1[2], row2[2], row3[2]));

        }

        long count2 = triangles.stream().filter(Day03::isValid).count();

        System.out.println("Part two answer: " + count2);
    }
}
