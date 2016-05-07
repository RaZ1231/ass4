package binary;

import interfaces.Expression;
import operands.Num;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Raziel Solomon
 * @since 17-Apr-16.
 */
public class MultTest {
    @Test
    public void simplify() throws Exception {
        Expression expected = new Pow("x", 2);
        Expression actual = new Mult(new Num(1), new Plus(new Mult(0, "x"), new Mult("x", "x"))).simplify();

        assertEquals(expected, actual);
    }

    @Test
    public void simplifySimple() throws Exception {
        Expression expected = new Pow("x", 2);
        Expression actual = new Mult("x", "x").simplify();

        assertEquals(expected, actual);
    }

}