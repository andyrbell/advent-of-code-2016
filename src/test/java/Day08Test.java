import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;

/**
 * Created by abell on 13/12/16.
 */
public class Day08Test {
    @Test
    public void testAddRectangle() throws Exception {
        int[][] screen = Day08.createScreen(7, 3);

        int[][] expected = new int[][]{ {1,1,0}, {1,1,0}, {1,1,0}, {0,0,0}, {0,0,0}, {0,0,0}, {0,0,0} };

        assertArrayEquals(expected, Day08.addRectangle(screen, 3, 2));

    }

    @Test
    public void testRotateColumn() throws Exception {
        int[][] screen = Day08.createScreen(7, 3);
        screen = Day08.addRectangle(screen, 3, 2);

        int[][] expected = new int[][]{ {1,1,0}, {0,1,1}, {1,1,0}, {0,0,0}, {0,0,0}, {0,0,0}, {0,0,0} };

        assertArrayEquals(expected, Day08.rotateColumn(screen, 1, 1));

    }

    @Test
    public void testRotateRow() throws Exception {
        int[][] screen = Day08.createScreen(7, 3);
        screen = Day08.addRectangle(screen, 3, 2);
        screen = Day08.rotateColumn(screen, 1, 1);

        int[][] expected = new int[][]{ {0,1,0}, {0,1,1}, {0,1,0}, {0,0,0}, {1,0,0}, {0,0,0}, {1,0,0} };

        assertArrayEquals(expected, Day08.rotateRow(screen, 0, 4));


    }

    @Test
    public void testRotateRow2() throws Exception {
        int[][] screen = Day08.createScreen(7, 3);
        screen = Day08.addRectangle(screen, 3, 2);
        screen = Day08.rotateColumn(screen, 1, 1);
        screen = Day08.rotateRow(screen, 0, 4);

        int[][] expected = new int[][]{ {0,1,0}, {1,0,1}, {0,1,0}, {0,0,0}, {1,0,0}, {0,0,0}, {1,0,0} };

        assertArrayEquals(expected, Day08.rotateColumn(screen, 1, 1));


    }

    @Test
    public void testEvaluate() throws Exception {
        int[][] screen = Day08.createScreen(7, 3);

        int[][] expected = new int[][]{ {0,1,0}, {1,0,1}, {0,1,0}, {0,0,0}, {1,0,0}, {0,0,0}, {1,0,0} };

        List<String> lines = Files.readAllLines(Paths.get("src/test/resources/Day08Test.txt"));

        assertArrayEquals(expected, Day08.evaluate(screen, lines));


    }
}
