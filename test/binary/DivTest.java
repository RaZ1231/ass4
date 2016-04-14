package binary;

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
    public void derivative() throws Exception {
        String expected = "0.25";
        String actual = new Div("x", 4).differentiate("x").simplify().toString();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void simple() throws Exception {
        String expected = "x";
        String actual = new Div(0, 0).simple(new Var("x"), new Num(1)).toString();

    }

    @Test
    public void toStringTest() throws Exception {

    }

}