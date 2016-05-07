package rulessimplification;

import binary.*;
import interfaces.Expression;
import operands.Const;
import operands.Num;
import operands.Var;
import org.junit.Test;
import tags.ExpTag;
import tags.NumTag;
import tags.VarTag;
import unary.Cos;
import unary.Neg;
import unary.Sin;

import static org.junit.Assert.assertEquals;

/**
 * @author Elisheva Broyer.
 * @since 18/04/2016.
 */
public class ParserTest {
    @Test
    public void toExpression() throws Exception {
        Parser parser = new Parser();
        Expression expected = new Pow(new Cos("x"), new Num(2));
        String s = expected.toString();
        Expression actual = parser.toExpression(s);

        assertEquals(expected, actual);
    }

    @Test
    public void toExpression2() throws Exception {
        Parser parser = new Parser();
        Expression expected = new Plus(new Pow(new Cos("x"), new Num(2)), new Mult(new Var("x"), new Log("y", "z")));
        String s = expected.toString();
        Expression actual = parser.toExpression(s);

        assertEquals(expected, actual);
    }

    @Test
    public void toExpression3() throws Exception {
        Parser parser = new Parser();
        Expression expected = new Plus(new Pow(new Cos("x"), new Sin(new ExpTag("1"))), new Mult(new Var("x"),
                new Log(new NumTag("2"), new Minus(new Neg("x"), new Div("o", "s")))));
        String s = expected.toString();
        Expression actual = parser.toExpression(s);

        assertEquals(expected, actual);
    }

    @Test
    public void toExpression4() throws Exception {
        Parser parser = new Parser();
        Expression expected = new Var("x");
        String s = expected.toString();
        Expression actual = parser.toExpression(s);

        assertEquals(expected, actual);
    }

    @Test
    public void toExpression5() throws Exception {
        Parser parser = new Parser();
        Expression expected = new Div(new Pow(new ExpTag("1"), new ExpTag("2")), new Pow(new ExpTag("1"), new ExpTag
                ("3")));
        String s = expected.toString();
        Expression actual = parser.toExpression(s);
//(#1^#2) / (#1^#3)
        assertEquals(expected, actual);
    }

    @Test
    public void toExpression6() throws Exception {
        Parser parser = new Parser();
        Expression expected = new Plus(new Div(new VarTag("1"), new NumTag("2")), new ExpTag("3"));
        String s = expected.toString();
        Expression actual = parser.toExpression(s);

        assertEquals(expected, actual);
    }

    @Test
    public void toExpression7() throws Exception {
        Parser parser = new Parser();
        Expression expected = new Div(new Const("pi", Math.PI), new Const("e", Math.exp(1)));
        String s = expected.toString();
        Expression actual = parser.toExpression(s);

        assertEquals(expected, actual);
    }

}