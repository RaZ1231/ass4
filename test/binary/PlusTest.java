package binary;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Raziel Solomon
 * @since 13-Apr-16.
 */
public class PlusTest {
    @Test
    public void operate() throws Exception {
        double expected = 11;
        double actual = new Plus(0, 0).operate(5, 6);

        Assert.assertEquals(expected, actual, 0);
    }

    @Test
    public void create() throws Exception {

    }

    @Test
    public void toStringTest() throws Exception {

    }

}