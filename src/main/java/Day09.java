import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by abell on 16/12/16.
 */
public class Day09 {

    static Pattern pattern = Pattern.compile("\\(([\\d]+)x([\\d]+)\\)");

    static long decompress(String input) {
        return decompress(input, false);
    }

    static long decompress(String input, boolean recursive) {
        return decompress(input, 0, input.length() - 1, recursive);
    }

    static long decompress(String input, int fromIndex, int toIndex, boolean recursive) {

        Matcher matcher = pattern.matcher(input);
        long length = 0;

        while (matcher.find(fromIndex) && matcher.start() < toIndex) {

            int markerNoOfChars = Integer.parseInt(matcher.group(1));
            int markerRepeat = Integer.parseInt(matcher.group(2));

            length += matcher.start() - fromIndex;

            long decompressedLength = recursive ? decompress(input, matcher.end(), matcher.end() + markerNoOfChars - 1, true) : markerNoOfChars;

            length += decompressedLength * markerRepeat;

            fromIndex = matcher.end() + markerNoOfChars;
        }

        length += toIndex - fromIndex + 1;

        return length;
    }

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("src/main/resources/Day09.txt"));

        System.out.println("Part one answer: " + decompress(lines.get(0)));
        System.out.println("Part two answer: " + decompress(lines.get(0), true));
    }
}
