import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by abell on 08/12/16.
 */
public class Day04Test {
//    aaaaa-bbb-z-y-x-123[abxyz] is a real room because the most common letters are a (5), b (3), and then a tie between x, y, and z, which are listed alphabetically.
//    a-b-c-d-e-f-g-h-987[abcde] is a real room because although the letters are all tied (1 of each), the first five are listed alphabetically.
//    not-a-real-room-404[oarel] is a real room.
//    totally-real-room-200[decoy] is not.


    @Test
    public void example1() throws Exception {
        List<String> lines = Files.readAllLines(Paths.get("src/test/resources/Day04Test.txt"));

        Integer sectorTotal = lines.stream()
                .map(Day04::parseRoom)
                .filter(Day04::isValid)
                .map(Day04.Room::getSector)
                .reduce(0, (a, b) -> a + b);

        assertEquals(1514, sectorTotal.intValue());

    }

    @Test
    public void example2() throws Exception {
        String encryptedName = "qzmt-zixmtkozy-ivhz-";
        int sectorId = 343;
        String expectedName = "very encrypted name";

        assertEquals(expectedName, Day04.shift(encryptedName, sectorId));

    }
}
