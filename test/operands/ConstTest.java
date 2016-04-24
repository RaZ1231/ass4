package operands;

import org.junit.Test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author Raziel Solomon
 * @since 17-Apr-16.
 */
public class ConstTest {
    @Test
    public void equalsTrue() throws Exception {
        Const pi1 = new Const("p1", Math.PI);
        Const pi2 = new Const("p2", Math.PI);

        assertTrue(pi1.equals(pi2));
    }

    @Test
    public void equalsFalse() throws Exception {
        Const pi1 = new Const("p1", Math.PI);
        Const pi2 = new Const("p2", Math.exp(1));

        assertFalse(pi1.equals(pi2));
    }

}