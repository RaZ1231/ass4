package binary;

import interfaces.Expression;
import operands.Num;
import operands.Var;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Raziel Solomon
 * @since 13-Apr-16.
 */
public class PlusTest {
    @Test
    public void operate() throws Exception {
        double expected = 11;
        double actual = new Plus(0, 0).operate(5, 6);

        assertEquals(expected, actual, 0);
    }

    @Test
    public void equalsTrue() throws Exception {
        Plus plus1 = new Plus("x", 5);
        Plus plus2 = new Plus("x", 5);

        assertTrue(plus1.equals(plus2));
    }

    @Test
    public void equalsFalse() throws Exception {
        Plus plus1 = new Plus("x", 5);
        Plus plus2 = new Plus(5, "x");

        assertFalse(plus1.equals(plus2));
    }

    @Test
    public void equalsTrueComplicated() throws Exception {
        Plus plus1 = new Plus(new Mult("x", 5), new Div("x", 1));
        Plus plus2 = new Plus(new Mult("x", 5), new Div("x", 1));

        assertTrue(plus1.equals(plus2));
    }

    @Test
    public void equalsFalseComplicated() throws Exception {
        Plus plus1 = new Plus(new Mult("x", 5), new Div("x", 1));
        Plus plus2 = new Plus(new Mult(1, 5), new Div("x", 1));

        assertFalse(plus1.equals(plus2));
    }

    @Test
    public void assign() throws Exception {
        Plus plus = new Plus("x", 5);
        Expression expected = new Plus(new Plus("x", "y"), new Num(5));
        Expression actual = plus.assign("x", new Plus("x", "y"));

        assertEquals(expected, actual);
    }

    @Test
    public void assign2() throws Exception {
        Plus plus = new Plus(new Plus("x", "z"), new Num(5));
        Expression expected = new Plus(new Plus(new Plus("x", "y"), new Var("z")), new Num(5));
        Expression actual = plus.assign("x", new Plus("x", "y"));

        assertEquals(expected, actual);
    }

}