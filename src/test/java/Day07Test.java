import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by abell on 12/12/16.
 */
public class Day07Test {

    @Test
    public void example1() throws Exception {
        String ip = "abba[mnop]qrst";
        assertEquals(true, Day07.valid(ip));
    }

    @Test
    public void example2() throws Exception {
        String ip = "abcd[bddb]xyyx";
        assertEquals(false, Day07.valid(ip));
    }

    @Test
    public void example3() throws Exception {
        String ip = "aaaa[qwer]tyui";
        assertEquals(false, Day07.valid(ip));
    }

    @Test
    public void example4() throws Exception {
        String ip = "ioxxoj[asdfgh]zxcvbn";
        assertEquals(true, Day07.valid(ip));
    }
}
