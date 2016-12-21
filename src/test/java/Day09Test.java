import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by abell on 16/12/16.
 */
public class Day09Test {

    @Test
    public void example1() throws Exception {
        test("ADVENT", "ADVENT");
    }

    @Test
    public void example2() throws Exception {
        test("A(1x5)BC", "ABBBBBC");
    }

    @Test
    public void example3() throws Exception {
        test("(3x3)XYZ", "XYZXYZXYZ");
    }

    @Test
    public void example4() throws Exception {
        test("A(2x2)BCD(2x2)EFG", "ABCBCDEFEFG");
    }

    @Test
    public void example5() throws Exception {
        test("(6x1)(1x3)A", "(1x3)A");
    }

    @Test
    public void example6() throws Exception {
        test("X(8x2)(3x3)ABCY", "X(3x3)ABC(3x3)ABCY");
    }

    public void test(String input, String expected) throws Exception {
        assertEquals(expected, Day09.decompress(input));
    }
}
