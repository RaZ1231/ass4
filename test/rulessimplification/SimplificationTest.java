package rulessimplification;

import binary.*;
import interfaces.Expression;
import operands.Num;
import operands.Var;
import org.junit.Test;
import unary.Cos;
import unary.Neg;
import unary.Sin;

import static org.junit.Assert.assertEquals;

/**
 * @author Raziel Solomon
 * @since 18-Apr-16.
 */
public class SimplificationTest {
    @Test
    public void simplify1() throws Exception {
        Expression expression = new Pow(new Var("x"), new Log(new Var("x"), new Num(5)));

        Expression expected = new Num(5);
        Expression actual = expression.simplify();

        assertEquals(expected, actual);
    }

    @Test
    public void simplify2() throws Exception {
        Expression expression = new Mult(new Var("x"), new Plus("x", 2));

        Expression expected = new Mult(new Var("x"), new Plus("x", 2));
        Expression actual = expression.simplify();

        assertEquals(expected, actual);
    }

    @Test
    public void simplify3() throws Exception {
        Expression expression = new Log(new Num(5), new Div(new Mult("x", 2), new Pow("x", 2)));

        Expression expected = new Minus(new Num(0.43067655807339306), new Log(5, "x"));
        Expression actual = expression.simplify();

        assertEquals(expected, actual);
    }

    @Test
    public void simplify4() throws Exception {
        Expression expression = new Pow(new Pow("x", 2), new Num(3));

        Expression expected = new Pow(new Var("x"), 6);
        Expression actual = expression.simplify();

        assertEquals(expected, actual);
    }

    @Test
    public void simplify5() throws Exception {
        Expression expression = new Pow(new Pow(new Plus("x", 5), new Mult("x", "y")), new Var("z"));

        Expression expected = new Pow(new Plus("x", 5), new Mult(new Mult("x", "y"), new Var("z")));
        Expression actual = expression.simplify();

        assertEquals(expected, actual);
    }

    @Test
    public void simplify6() throws Exception {
        Expression expression = new Mult(new Plus(3, 4), new Pow("x", 2));

        Expression expected = new Mult(new Pow("x", 2), 7);
        Expression actual = expression.simplify();

        assertEquals(expected, actual);
    }

    @Test
    public void simplify7() throws Exception {
        Expression expression = new Plus("x", 0);

        Expression expected = new Var("x");
        Expression actual = expression.simplify();

        assertEquals(expected, actual);
    }

    @Test
    public void simplify8() throws Exception {
        Expression expression = new Div(new Pow("x", 4), new Var("x"));

        Expression expected = new Pow("x", 3);
        Expression actual = expression.simplify();

        assertEquals(expected, actual);
    }

    @Test
    public void simplify9() throws Exception {
        Expression expression = new Pow(new Plus(3, 4), new Var("x"));

        Expression expected = new Pow(7, "x");
        Expression actual = expression.simplify();

        assertEquals(expected, actual);
    }

    @Test
    public void simplify10() throws Exception {
        Expression expression = new Pow(new Var("x"), new Plus(3, 4));

        Expression expected = new Pow("x", 7);
        Expression actual = expression.simplify();

        assertEquals(expected, actual);
    }

    @Test
    public void simplify11() throws Exception {
        Expression expression = new Minus(
                new Plus(
                        new Log(2, new Mult(new Mult(2, new Pow("x", 2)), new Mult(8, "y"))),
                        new Log(2, new Mult(2, new Pow("x", 2)))),
                new Log(2, new Mult(2, "y")));

        Expression expected = new Plus(new Mult(4, new Log(2, "x")), 4);
        Expression actual = expression.simplify();

        assertEquals(expected, actual);
    }

    @Test
    public void simplify12() throws Exception {
        Expression expression = new Plus(new Plus(new Plus(new Plus(new Plus(new Plus(new Plus(
                new Mult(2, "x"), new Mult(3, "y")), new Mult(4, "z")), new Mult(5, "a")),
                new Mult(4, "x")), new Mult(2, "y")), new Mult(5, "z")), new Mult(5, "a"));

        Expression expected = new Plus(new Mult(5, "y"), new Plus(
                new Mult(6, "x"), new Plus(new Mult(9, "z"), new Mult(10, "a"))));
        Expression actual = expression.simplify();

        assertEquals(expected, actual);
    }

    @Test
    public void simplify13() throws Exception {
        Expression expression = new Plus(new Plus(new Plus(new Plus(new Plus(new Plus(new Plus(
                new Mult(2, "x"), new Mult(3, new Neg("y"))), new Mult(4, "z")), new Mult(5, "a")),
                new Mult(4, "x")), new Mult(2, "y")), new Mult(5, "z")), new Mult(5, "a"));

        Expression expected = new Plus(new Mult(9, "z"), new Plus(
                new Mult(6, "x"), new Minus(new Mult(10, "a"), "y")));
        Expression actual = expression.simplify();

        assertEquals(expected, actual);
    }

    @Test
    public void simplify14() throws Exception {
        Expression expression = new Mult(-1, "x");

        Expression expected = new Neg("x");
        Expression actual = expression.simplify();

        assertEquals(expected, actual);
    }

    @Test
    public void simplify15() throws Exception {
        Expression expression = new Plus(new Plus(new Pow(new Cos("x"), 2), -1), new Pow(new Sin("x"), 2));

        Expression expected = new Num(0);
        Expression actual = expression.simplify();

        assertEquals(expected, actual);
    }

    @Test
    public void simplify16() throws Exception {
        Expression expression = new Mult(new Mult(new Mult(new Mult(3, "x"), "y"), "z"), 5);

        Expression expected = new Mult(15, new Mult("x", new Mult("y", "z")));
        Expression actual = expression.simplify();

        assertEquals(expected, actual);
    }

    @Test
    public void simplify17() throws Exception {
        Expression expression = new Div(new Mult(new Mult(new Mult(5, "x"), "y"), "z"), 5);

        Expression expected = new Mult("y", new Mult("z", "x"));
        Expression actual = expression.simplify();

        assertEquals(expected, actual);
    }
}