import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by abell on 22/12/16.
 */
public class Day10Test {

    @Test
    public void example1() throws Exception {
        List<String> lines = Files.readAllLines(Paths.get("src/test/resources/Day10Test.txt"));

        Day10.State state = Day10.evaluate(lines);

        assertEquals("bot 2", state.getComparison(5, 2));
    }
}
