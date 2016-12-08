import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created by abell on 08/12/16.
 */
public class Day02 {


    static int[][] numberPad = new int[][] { {1,4,7}, {2,5,8}, {3,6,9} };

    public static String decode(List<String> lines) {
        StringBuilder sb = new StringBuilder();
        int x = 1;
        int y = 1;

        for (String line : lines) {
            for (char c : line.toCharArray()) {
                switch (c) {
                    case 'U':
                        int upY = y - 1;
                        if (upY >= 0 && upY < numberPad[x].length) {
                            y = upY;
                        }
                        break;
                    case 'D':
                        int downY = y + 1;
                        if (downY >= 0 && downY < numberPad[x].length) {
                            y = downY;
                        }
                        break;
                    case 'L':
                        int leftX = x - 1;
                        if (leftX >= 0 && leftX < numberPad.length) {
                            x = leftX;
                        }
                        break;
                    case 'R':
                        int rightX = x + 1;
                        if (rightX >= 0 && rightX < numberPad.length) {
                            x = rightX;
                        }
                        break;
                }
            }
            sb.append(numberPad[x][y]);
        }

        return sb.toString();

    }

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("src/main/resources/Day02.txt"));

        String code = Day02.decode(lines);

        System.out.println("Part one answer: " + code);

    }
}
