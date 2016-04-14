package binary;

import operands.Num;
import operands.Var;
import org.junit.Assert;
import org.junit.Test;
import structure.BinaryExpression;
import structure.Expression;

/**
 * @author Elisheva Broyer.
 * @since 14/04/2016.
 */
public class DivTest {
    @Test
    public void operate() throws Exception {
        Div div = new Div(1,1);

        double actual = div.operate(6,12);
        double expected = 0.5;

        Assert.assertEquals(expected, actual, 0);
    }

    @Test
    public void create() throws Exception {
        Div expected = new Div(1,2);
        BinaryExpression actual = expected.create(new Num(1), new Num(2));

        Assert.assertEquals(expected.toString(),
                actual.toString());
    }

    @Test
    public void derivative() throws Exception {
        Expression a = new Div(new Var("x"), new Num(4));
        Expression expected = new Num(0.25);

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