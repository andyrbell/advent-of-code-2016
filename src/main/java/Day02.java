import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created by abell on 08/12/16.
 */
public class Day02 {


    // 1 2 3
    // 4 5 6
    // 7 8 9
    static String[][] numberPad1 = new String[][] { {"1","4","7"}, {"2","5","8"}, {"3","6","9"} };

    //     1
    //   2 3 4
    // 5 6 7 8 9
    //   A B C
    //     D
    static String[][] numberPad2 = new String[][] { {null, null, "5", null, null}, {null, "2","6","A", null}, {"1","3","7","B","D"}, {null, "4","8","C", null}, {null, null, "9", null, null} };

    static String decode(String[][] numberPad, int startX, int startY, List<String> lines) {
        StringBuilder sb = new StringBuilder();
        int x = startX;
        int y = startY;

        for (String line : lines) {
            for (char c : line.toCharArray()) {
                switch (c) {
                    case 'U':
                        int upY = y - 1;
                        if (isValid(numberPad, x, upY)) {
                            y = upY;
                        }
                        break;
                    case 'D':
                        int downY = y + 1;
                        if (isValid(numberPad, x, downY)) {
                            y = downY;
                        }
                        break;
                    case 'L':
                        int leftX = x - 1;
                        if (isValid(numberPad, leftX, y)) {
                            x = leftX;
                        }
                        break;
                    case 'R':
                        int rightX = x + 1;
                        if (isValid(numberPad, rightX, y)) {
                            x = rightX;
                        }
                        break;
                }
            }
            sb.append(numberPad[x][y]);
        }

        return sb.toString();

    }

    static boolean isValid(String[][] pad, int x, int y) {
        boolean valid = false;
        try {
            valid = pad[x][y] != null;
        } catch (ArrayIndexOutOfBoundsException e) {

        }
        return valid;
    }

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("src/main/resources/Day02.txt"));

        String code = Day02.decode(numberPad1, 1, 1, lines);
        String code2 = Day02.decode(numberPad2, 0, 2, lines);

        System.out.println("Part one answer: " + code);
        System.out.println("Part two answer: " + code2);

    }

}
