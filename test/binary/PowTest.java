package binary;

import interfaces.Expression;
import operands.Num;
import operands.Var;
import org.junit.Assert;
import org.junit.Test;
import tags.ExpTag;

import java.util.HashMap;
import java.util.Map;

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
        Expression expected = new Mult(4, new Pow(new Var("x"), new Num(3)));

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

    @Test
    public void mapByRule() throws Exception {
        Expression rule = new Pow(new ExpTag("1"), new Log(new ExpTag("1"), new ExpTag("2")));

        Map<String, Expression> expected = new HashMap<>();
        Map<String, Expression> actual = new Pow("x", new Log("x", "y")).mapByRule(rule);

        expected.put("1", new Var("x"));
        expected.put("2", new Var("y"));

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void simplifyByRules() throws Exception {
        Expression expected = new Var("y");
        Expression actual = new Pow("x", new Log("x", "y")).rulesSimplification();

        Assert.assertEquals(expected, actual);
    }

}