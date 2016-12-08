import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by abell on 08/12/16.
 */
public class Day02Test {
    @Test
    public void example1() throws Exception {

        String expectedCode = "1985";

        List<String> lines = Files.readAllLines(Paths.get("src/test/resources/Day02Test.txt"));

        String code = Day02.decode(lines);

        assertEquals(expectedCode, code);
    }
}
