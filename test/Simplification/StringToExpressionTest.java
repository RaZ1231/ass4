package Simplification;

import binary.*;
import operands.Num;
import operands.Var;
import org.junit.Test;
import structure.Expression;
import unary.Cos;
import unary.Neg;
import unary.Sin;

import static org.junit.Assert.assertEquals;

/**
 * @author Elisheva Broyer.
 * @since 18/04/2016.
 */
public class StringToExpressionTest {
    @Test
    public void toExpression() throws Exception {
        StringToExpression ste = new StringToExpression();
        Expression expected = new Pow(new Cos("x"), new Num(2));
        String s = expected.toString();
        Expression actual = ste.toExpression(s);

        assertEquals(expected, actual);
    }

    @Test
    public void toExpression2() throws Exception {
        StringToExpression ste = new StringToExpression();
        Expression expected = new Plus(new Pow(new Cos("x"), new Num(2)), new Mult(new Var("x"), new Log("y", "z")));
        String s = expected.toString();
        Expression actual = ste.toExpression(s);

        assertEquals(expected, actual);
    }

    @Test
    public void toExpression3() throws Exception {
        StringToExpression ste = new StringToExpression();
        Expression expected = new Plus(new Pow(new Cos("x"), new Sin(new Tag("1"))), new Mult(new Var("x"),
                new Log(new NumTag("2"), new Minus(new Neg("x"), new Div("o", "s")))));
        String s = expected.toString();
        Expression actual = ste.toExpression(s);

        assertEquals(expected, actual);
    }

    @Test
    public void toExpression4() throws Exception {
        StringToExpression ste = new StringToExpression();
        Expression expected = new Var("x");
        String s = expected.toString();
        Expression actual = ste.toExpression(s);

        assertEquals(expected, actual);
    }
}