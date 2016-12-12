import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by abell on 09/12/16.
 */
public class Day06Test {
    @Test
    public void example1() throws Exception {

        List<String> lines = Files.readAllLines(Paths.get("src/test/resources/Day06Test.txt"));
        String expected = "easter";

        String result = Day06.correct(lines, Day06.countDesc);

        assertEquals(expected, result);

    }

    @Test
    public void example2() throws Exception {

        List<String> lines = Files.readAllLines(Paths.get("src/test/resources/Day06Test.txt"));
        String expected = "advent";

        String result = Day06.correct(lines, Day06.countAsc);

        assertEquals(expected, result);

    }

    @Test
    public void partOne() throws Exception {
        List<String> lines = Files.readAllLines(Paths.get("src/main/resources/Day06.txt"));
        String expected = "qtbjqiuq";

        String result = Day06.correct(lines, Day06.countDesc);

        assertEquals(expected, result);

    }

    @Test
    public void partTwo() throws Exception {
        List<String> lines = Files.readAllLines(Paths.get("src/main/resources/Day06.txt"));
        String expected = "akothqli";

        String result = Day06.correct(lines, Day06.countAsc);

        assertEquals(expected, result);

    }
}
