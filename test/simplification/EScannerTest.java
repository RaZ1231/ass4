package simplification;

import binary.Minus;
import binary.Mult;
import binary.Plus;
import operands.Num;
import operands.Var;
import org.junit.Assert;
import org.junit.Test;
import structure.Expression;
import unary.Neg;

import java.util.Arrays;
import java.util.List;

/**
 * @author Raziel Solomon
 * @since 01-May-16.
 */
public class EScannerTest {
    @Test
    public void scan() throws Exception {
        EScanner scanner = new EScanner();
        Expression e = new Plus("x", new Plus("y", new Minus("z", "y")));

        Expression expected = new Plus("x", "z");
        Expression actual = scanner.scan(e).simplify();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void scan2() throws Exception {
        EScanner scanner = new EScanner();
        Expression e = new Plus("x", new Plus("y", new Minus("z", "y")));

        Expression expected = new Plus("x", "z");
        Expression actual = scanner.scan(e).simplify();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getLinearVars() throws Exception {
        EScanner scanner = new EScanner();
        Expression e = new Plus("x", 5);

        List<Expression> expected = Arrays.asList(new Var("x"), new Num(5));
        List<Expression> actual = scanner.getLinearVars(e);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getLinearVars1() throws Exception {
        EScanner scanner = new EScanner();
        Expression e = new Minus("x", "y");

        List<Expression> expected = Arrays.asList(new Var("x"), new Neg("y"));
        List<Expression> actual = scanner.getLinearVars(e);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getLinearVars2() throws Exception {
        EScanner scanner = new EScanner();
        Expression e = new Minus("x", new Plus("y", "z"));

        List<Expression> expected = Arrays.asList(new Var("x"), new Neg("y"), new Neg("z"));
        List<Expression> actual = scanner.getLinearVars(e);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getLinearVars3() throws Exception {
        EScanner scanner = new EScanner();
        Expression e = new Minus("x", new Mult("y", "z"));

        List<Expression> expected = Arrays.asList(new Var("x"), new Neg(new Mult("y", "z")));
        List<Expression> actual = scanner.getLinearVars(e);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void addition() throws Exception {
        EScanner scanner = new EScanner();
        List<Expression> list = Arrays.asList(new Var("x"), new Var("y"), new Mult(5, "x"), new Mult(3, "y"));

        List<Expression> expected = Arrays.asList(new Num(0), new Num(0), new Mult(6, "x"), new Mult(4, "y"));
        List<Expression> actual = scanner.addition(list);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void add() throws Exception {
        EScanner scanner = new EScanner();
        Expression e = new Var("x");

        Expression expected = new Mult(2, e);
        Expression actual = scanner.add(e, e);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void add2() throws Exception {
        EScanner scanner = new EScanner();
        Expression e1 = new Var("x");
        Expression e2 = new Mult(2, "x");

        Expression expected = new Mult(new Plus(2, 1), "x");
        Expression actual = scanner.add(e1, e2);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void add3() throws Exception {
        EScanner scanner = new EScanner();
        Expression e1 = new Neg("x");
        Expression e2 = new Mult(2, "x");

        Expression expected = new Mult(new Minus(2, 1), "x");
        Expression actual = scanner.add(e1, e2);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void add4() throws Exception {
        EScanner scanner = new EScanner();
        Expression e1 = new Neg("x");
        Expression e2 = new Neg("x");

        Expression expected = new Mult(2, new Neg("x"));
        Expression actual = scanner.add(e1, e2);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void toExpression() throws Exception {
        EScanner scanner = new EScanner();
        List<Expression> e = Arrays.asList(new Var("x"), new Num(5), new Mult(5, "x"));

        Expression expected = new Plus("x", new Plus(5, new Mult(5, "x")));
        Expression actual = scanner.toExpression(e);

        Assert.assertEquals(expected, actual);
    }

}