import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by abell on 13/12/16.
 */
public class Day08 {

    static int[][] createScreen(int x, int y) {
        return new int[x][y];
    }

    static int[][] addRectangle(int[][] screen, int x, int y) {

        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                screen[i][j] = 1;
            }
        }
        return screen;
    }

    static int[][] rotateColumn(int[][] screen, int column, int pixels) {
//        System.out.println(String.format("Rotate column %s by %s pixels", column, pixels));
        int columnLength = screen[column].length;
        int[] columnBuffer = new int[columnLength];

        for (int y = 0; y < columnLength; y++) {
            int offset = (y + pixels) % columnLength;
            columnBuffer[offset] = screen[column][y];
        }

        System.arraycopy(columnBuffer, 0, screen[column], 0, columnLength);

        return screen;
    }

    static int[][] rotateRow(int[][] screen, int row, int pixels) {
//        System.out.println(String.format("Rotate row %s by %s pixels", row, pixels));
        int rowLength = screen.length;
        int[] rowBuffer = new int[rowLength];

        for (int x = 0; x < rowLength; x++) {
            int offset = (x + pixels) % rowLength;
            rowBuffer[offset] = screen[x][row];
        }

        for (int x = 0; x < rowLength; x++) {
            screen[x][row] = rowBuffer[x];
        }

        return screen;
    }

    static int[][] evaluate(int[][] screen, List<String> instructions) {

        for (String instruction : instructions) {
            if (instruction != null && instruction.trim().length() > 0) {
//                System.out.println(instruction);
                String[] token = instruction.split(" ");

                if ("rect".equals(token[0])) {
                    String[] dimensions = token[1].split("x");
                    addRectangle(screen, Integer.parseInt(dimensions[0]), Integer.parseInt(dimensions[1]));
                } else if ("rotate".equals(token[0])) {
                    if ("column".equals(token[1])) {
                        rotateColumn(screen, Integer.parseInt(token[2].split("=")[1]), Integer.parseInt(token[4]));
                    } else if ("row".equals(token[1])) {
                        rotateRow(screen, Integer.parseInt(token[2].split("=")[1]), Integer.parseInt(token[4]));
                    }
                }
            }
//            System.out.println(Arrays.deepToString(screen));
        }

        return screen;
    }

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("src/main/resources/Day08.txt"));

        int screenWidth = 50;
        int screenHeight = 6;

        int[][] screen = Day08.createScreen(screenWidth, screenHeight);

        screen = Day08.evaluate(screen, lines);

        long litPixels = Arrays.stream(screen).map(x -> Arrays.stream(x).filter(y -> y == 1).count()).reduce(0L, (i, j) -> i + j);

        System.out.println("Part one answer: " + litPixels);

        for (int y = 0; y < screenHeight; y++) {
            for (int x = 0; x < screenWidth; x++) {
                System.out.print(screen[x][y] == 1 ? "#" : " ");
            }
            System.out.println();
        }
    }
}
