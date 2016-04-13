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
        Plus plus = new Plus(5, 6);
        double expected = 11;
        double actual = plus.operate(5, 6);

        Assert.assertEquals(expected, actual, 0);
    }

    @Test
    public void create() throws Exception {

    }

    @Test
    public void stoString() throws Exception {

    }

}