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
        Div div = new Div(0, 0);
        double actual = div.operate(6, 12);
        double expected = 0.5;

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
        Div div = new Div(new Var("x"), new Num(4));
        String expected = new Num(0.25).toString();
        String actual = div.differentiate("x").simplify().toString();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void simple() throws Exception {

    }

    @Test
    public void toStringTest() throws Exception {

    }

}