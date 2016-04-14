package binary;

import operands.Const;
import operands.Num;
import operands.Var;
import org.junit.Assert;
import org.junit.Test;
import structure.Expression;

/**
 * @author Elisheva Broyer.
 * @since 14/04/2016.
 */
public class LogTest {
    @Test
    public void operate() throws Exception {
        double expected = 3;
        double actual = new Log(0, 0).operate(2, 8);

        Assert.assertEquals(expected, actual, 0);
    }

    @Test
    public void create() throws Exception {
        String expected = new Log(1, 2).toString();
        String actual = new Log(0, 0).create(new Num(1), new Num(2)).toString();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void derivative() throws Exception {
        Expression a = new Log(new Num(4), new Var("x"));
        Expression expected = new Div(new Num(1), new Mult(new Var("x"), new
                Log(new Const("e", Math.exp(1)), new Num(4))));

        Expression actual = a.differentiate("x").simplify();

        Assert.assertEquals(expected.toString(),
                actual.toString());
    }

    @Test
    public void simple() throws Exception {

    }

    @Test
    public void toStringTest() throws Exception {

    }

}