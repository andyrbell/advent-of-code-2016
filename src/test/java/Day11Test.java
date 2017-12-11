import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by abell on 02/01/17.
 */
public class Day11Test {
    @Test
    public void example1() throws Exception {
        List<String> lines = Files.readAllLines(Paths.get("src/test/resources/Day11Test.txt"));
        assertEquals(11, Day11.minimumSteps(lines));
    }
}
