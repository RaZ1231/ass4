package Simplification;

import binary.Log;
import binary.Mult;
import binary.Plus;
import binary.Pow;
import operands.Num;
import operands.Var;
import org.junit.Test;
import structure.Expression;
import unary.Cos;

import static org.junit.Assert.assertEquals;

/**
 * @author Elisheva Broyer.
 * @since 18/04/2016.
 */
public class StringToExpressionTest {
    @Test
    public void stringToExpression() throws Exception {
        Expression expected = new Pow(new Cos("x"), new Num(2));
        String s = "((cos(x))^2)";
        Expression actual = StringToExpression.StringToExpression(s, "L");

        assertEquals(expected, actual);
    }

    @Test
    public void stringToExpression2() throws Exception {
        Expression expected = new Plus(new Pow(new Cos("x"), new Num(2)), new Mult(new Var("x"), new Log("y", "z")));
        String s = expected.toString();
        Expression actual = StringToExpression.StringToExpression(s, "L");

        assertEquals(expected, actual);
    }
}