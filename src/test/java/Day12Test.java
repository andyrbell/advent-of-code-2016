import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Created by abell on 13/12/16.
 */
public class Day12Test {
    @Test
    public void example1() throws Exception {

        List<String> lines = Files.readAllLines(Paths.get("src/test/resources/Day12Test.txt"));

        Map<String, Integer> registers = new HashMap<>();
        registers.put("a", 0);
        registers.put("b", 0);
        registers.put("c", 0);
        registers.put("d", 0);

        int expected = 42;

        assertEquals(expected, Day12.evaluate(registers, lines));

    }
}
