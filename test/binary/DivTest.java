package binary;

import interfaces.Expression;
import operands.Num;
import operands.Var;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Elisheva Broyer.
 * @since 14/04/2016.
 */
public class DivTest {
    @Test
    public void operate() throws Exception {
        double expected = 0.5;
        double actual = new Div(0, 0).operate(6, 12);

        Assert.assertEquals(expected, actual, 0);
    }

    @Test
    public void create() throws Exception {
        String expected = new Div(1, 2).toString();
        String actual = new Div(0, 0).create(new Num(1), new Num(2)).toString();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void differentiate() throws Exception {
        String expected = "0.25";
        String actual = new Div("x", 4).differentiate("x").simplify().toString();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void simplify() throws Exception {
        String expected = "0.25";
        String actual = new Div(4, 16).simplify().toString();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void simplify1() throws Exception {
        Div div = new Div("x", 1);

        Expression expected = new Var("x");
        Expression actual = div.rulesSimplification();

        Assert.assertEquals(expected, actual);
    }

}