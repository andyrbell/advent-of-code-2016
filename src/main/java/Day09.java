import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

/**
 * Created by abell on 16/12/16.
 */
public class Day09 {
    public static String decompress(String input) {
        char[] chars = input.toCharArray();

        StringBuilder markerBuffer = new StringBuilder();
        StringBuilder contentBuffer = new StringBuilder();
        boolean readingMarker = false;

        int i = 0;
        while (i < chars.length) {

            if (chars[i] == '(') {
                readingMarker = true;
                i++;
                continue;
            }

            if (readingMarker && chars[i] == ')') {
                String[] marker = markerBuffer.toString().split("x");
                int markerNoOfChars = Integer.parseInt(marker[0]);
                int markerRepeat = Integer.parseInt(marker[1]);

                char[] decompressedContent = Arrays.copyOfRange(chars, i + 1, i + 1 + markerNoOfChars);

                for (int j = 0; j < markerRepeat; j++) {
                    contentBuffer.append(decompressedContent);
                }
                readingMarker = false;
                markerBuffer.delete(0, markerBuffer.length());
                i += markerNoOfChars + 1;
                continue;
            }

            if (readingMarker) {
                markerBuffer.append(chars[i]);
            } else {
                contentBuffer.append(chars[i]);
            }

            i++;
        }

        System.out.println("contentBuffer length: " + contentBuffer.length());

        return contentBuffer.toString();
    }

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("src/main/resources/Day09.txt"));

        System.out.println("Part one answer: " + decompress(lines.get(0)).length());
    }
}
