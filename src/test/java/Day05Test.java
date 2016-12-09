import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by abell on 09/12/16.
 */
public class Day05Test {

    @Test
    public void md5Example1() throws Exception {
        String input = "abc3231929";
        String expected = "00000155f8105dff7f56ee10fa9b9abd";

        assertEquals(expected, Day05.md5(input));
    }

    @Test
    public void md5Example2() throws Exception {
        String input = "abc5017308";
        String expected = "000008f82c5b3924a1ecbebf60344e00";

        assertEquals(expected, Day05.md5(input));
    }

    @Test
    public void example1() throws Exception {
        String input = "abc";
        String expected = "18f47a30";

        assertEquals(expected, Day05.password(input));
    }

    @Test
    public void example2() throws Exception {
        String input = "abc";
        String expected = "05ace8e3";

        assertEquals(expected, Day05.password2(input));
    }
}
