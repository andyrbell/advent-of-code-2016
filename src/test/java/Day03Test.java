import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by abell on 08/12/16.
 */
public class Day03Test {
    @Test
    public void invalidTriangle() throws Exception {
        assertFalse(Day03.isValid(5, 10, 25));
    }

    @Test
    public void validTriangle() throws Exception {
        assertTrue(Day03.isValid(3, 4, 5));
    }


}
