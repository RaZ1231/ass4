package binary;

import operands.Num;
import operands.Var;
import org.junit.Assert;
import org.junit.Test;
import structure.Expression;

/**
 * @author Elisheva Broyer.
 * @since 14/04/2016.
 */
public class PowTest {
    @Test
    public void operate() throws Exception {
        Pow pow = new Pow(2, 4);

        double actual = pow.operate(2, 4);
        double expected = 16;

        Assert.assertEquals(expected, actual, 0);
    }

    @Test
    public void create() throws Exception {
        Pow expected = new Pow(1, 2);
        Expression actual = expected.create(new Num(1), new Num(2));

        Assert.assertEquals(expected.toString(), actual.toString());
    }

    @Test
    public void derivative() throws Exception {
        Expression a = new Pow(new Var("x"), new Num(4));
        Expression expected = new Mult(new Pow(new Var("x"), new Num(4)),
                (new Div(new Num(4), new Var("x"))));

        Expression actual = a.differentiate("x").simplify();

        Assert.assertEquals(expected.toString(),
                actual.toString());
    }

    @Test
    public void assign() throws Exception {
        Expression expected = new Pow(new Plus("x", 1), new Var("y"));
        Expression actual = new Pow("x", "y").assign("x", new Plus("x", 1));

        Assert.assertEquals(expected, actual);
    }

}