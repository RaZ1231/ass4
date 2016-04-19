package Simplification;

import binary.Plus;
import binary.Pow;
import operands.Num;
import operands.Var;
import org.junit.Test;
import structure.Expression;
import unary.Cos;
import static org.junit.Assert.*;

/**
 * @author Elisheva Broyer.
 * @since 18/04/2016.
 */
public class StringToExpressionTest {
    @Test
    public void stringToExpression() throws Exception {
        Expression expected = new Pow(new Cos("x"), new Num(2));
        String s = "((cos(x))^2)";
        Expression actual = StringToExpression.StringToExpression(s);

        assertEquals(expected,actual);
    }

}