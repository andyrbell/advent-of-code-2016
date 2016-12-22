import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by abell on 16/12/16.
 */
public class Day09Test {

    @Test
    public void example1() throws Exception {
        testLength("ADVENT", 6, false);
    }

    @Test
    public void example2() throws Exception {
        testLength("A(1x5)BC", 7, false);
    }

    @Test
    public void example3() throws Exception {
        testLength("(3x3)XYZ", 9, false);
    }

    @Test
    public void example4() throws Exception {
        testLength("A(2x2)BCD(2x2)EFG", 11, false);
    }

    @Test
    public void example5() throws Exception {
        testLength("(6x1)(1x3)A", 6, false);
    }

    @Test
    public void example6() throws Exception {
        testLength("X(8x2)(3x3)ABCY", 18, false);
    }

    @Test
    public void example7() throws Exception {
        testLength("X(8x2)(3x3)ABCY", 20, true);
    }

    @Test
    public void example8() throws Exception {
        testLength("(27x12)(20x12)(13x14)(7x10)(1x12)A", 241920, true);
    }

    @Test
    public void example9() throws Exception {
        testLength("(25x3)(3x3)ABC(2x3)XY(5x2)PQRSTX(18x9)(3x2)TWO(5x7)SEVEN", 445, true);
    }

    private void testLength(String input, int expected, boolean recursive) throws Exception {
        assertEquals(expected, Day09.decompress(input, recursive));
    }

}
