package simplification;

import binary.Div;
import binary.Log;
import binary.Minus;
import binary.Mult;
import binary.Plus;
import binary.Pow;
import operands.Num;
import operands.Var;
import org.junit.Test;
import structure.Expression;
import tags.ExpTag;
import tags.NumTag;
import tags.VarTag;
import unary.Sin;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author Raziel Solomon
 * @since 18-Apr-16.
 */
public class RuleCheckerTest {
    @Test
    public void checkTrue() throws Exception {
        Expression complicated = new Pow(new ExpTag("1"), new Log(new ExpTag("1"), new ExpTag("2")));
        Expression simple = new ExpTag("2");
        Expression expression = new Pow(new Var("x"), new Log(new Var("x"), new Num(5)));
        Rule rule = new Rule(complicated, simple);
        RuleChecker checker = new RuleChecker();

        assertTrue(checker.check(rule.getComplicated(), expression));
    }

    @Test
    public void checkComplicatedTrue() throws Exception {
        Expression complicated = new Pow(new ExpTag("1"), new Log(new ExpTag("1"), new ExpTag("2")));
        Expression simple = new ExpTag("2");
        Expression e1 = new Plus(new Sin("x"), new Mult("x", 2));
        Expression expression = new Pow(e1, new Log(e1, new Div("x", 5)));
        Rule rule = new Rule(complicated, simple);
        RuleChecker checker = new RuleChecker();

        assertTrue(checker.check(rule.getComplicated(), expression));
    }

    @Test
    public void checkFalse() throws Exception {
        Expression complicated = new Pow(new ExpTag("1"), new Log(new ExpTag("1"), new ExpTag("2")));
        Expression simple = new ExpTag("2");
        Expression expression = new Pow(new Var("x"), new Log(new Var("y"), new Num(5)));
        Rule rule = new Rule(complicated, simple);
        RuleChecker checker = new RuleChecker();

        assertFalse(checker.check(rule.getComplicated(), expression));
    }

    @Test
    public void checkComplicatedFalse() throws Exception {
        Expression e1 = new Plus(new Sin("x"), new Mult("x", 2));
        Expression e2 = new Plus(new Sin("y"), new Mult("x", 2));

        Expression complicated = new Pow(new ExpTag("1"), new Log(new ExpTag("1"), new ExpTag("2")));
        Expression simple = new ExpTag("2");
        Expression expression = new Pow(e1, new Log(e2, new Div("x", 5)));
        Rule rule = new Rule(complicated, simple);
        RuleChecker checker = new RuleChecker();

        assertFalse(checker.check(rule.getComplicated(), expression));
    }

    @Test
    public void apply() throws Exception {
        Expression complicated = new Pow(new ExpTag("1"), new Log(new ExpTag("1"), new ExpTag("2")));
        Expression simple = new ExpTag("2");
        Rule rule = new Rule(complicated, simple);
        Expression expression = new Pow(new Var("x"), new Log(new Var("x"), new Num(5)));
        RuleChecker checker = new RuleChecker();

        Expression expected = new Num(5);
        Expression actual = checker.apply(rule, expression);

        assertEquals(expected, actual);
    }

    @Test
    public void apply2() throws Exception {
        Expression expression = new Mult("x", 2);
        Rule rule = new Rule(new Mult(new ExpTag("1"), new Num(2)), new Mult(new Num(2), new ExpTag("1")));
        RuleChecker checker = new RuleChecker();

        Expression expected = new Mult(2, "x");
        Expression actual = checker.apply(rule, expression);

        assertEquals(expected, actual);
    }

    @Test
    public void apply3() throws Exception {
        Expression expression = new Mult(new Var("x"), new Plus("x", 2));
        Rule rule = new Rule(new Mult(new ExpTag("1"), new ExpTag("2")), new Mult(new ExpTag("2"), new ExpTag("1")));
        RuleChecker checker = new RuleChecker();

        Expression expected = new Mult(new Plus("x", 2), new Var("x"));
        Expression actual = checker.apply(rule, expression);

        assertEquals(expected, actual);
    }

    @Test
    public void apply4() throws Exception {
        Expression expression = new Log(new Num(5), new Div(new Mult("x", 2), new Pow("x", 2)));
        Rule rule = new Rule(new Log(new ExpTag("1"), new Div(new ExpTag("2"), new ExpTag("3"))),
                new Minus(new Log(new ExpTag("1"), new ExpTag("2")), new Log(new ExpTag("1"), new ExpTag("3"))));
        RuleChecker checker = new RuleChecker();

        Expression expected = new Minus(new Log(new Num(5), new Mult("x", 2)), new Log(new Num(5), new Pow("x", 2)));
        Expression actual = checker.apply(rule, expression);

        assertEquals(expected, actual);
    }

    @Test
    public void apply5() throws Exception {
        Expression expression = new Pow(new Pow("x", 2), new Num(3));
        Rule rule = new Rule(new Pow(new Pow(new ExpTag("1"), new ExpTag("2")), new ExpTag("3")),
                new Pow(new ExpTag("1"), new Mult(new ExpTag("2"), new ExpTag("3"))));
        RuleChecker checker = new RuleChecker();

        Expression expected = new Pow(new Var("x"), new Mult(2, 3));
        Expression actual = checker.apply(rule, expression);

        assertEquals(expected, actual);
    }

    @Test
    public void apply6() throws Exception {
        Expression expression = new Pow(new Pow(new Plus("x", 5), new Mult("x", "y")), new Var("z"));
        Rule rule = new Rule(new Pow(new Pow(new ExpTag("1"), new ExpTag("2")), new ExpTag("3")),
                new Pow(new ExpTag("1"), new Mult(new ExpTag("2"), new ExpTag("3"))));
        RuleChecker checker = new RuleChecker();

        Expression expected = new Pow(new Plus("x", 5), new Mult(new Mult("x", "y"), new Var("z")));
        Expression actual = checker.apply(rule, expression);

        assertEquals(expected, actual);
    }

    @Test
    public void apply7() throws Exception {
        Expression expression = new Mult(new Pow("x", 2), new Plus(3, 4));
        Rule rule = new Rule(new Mult(new ExpTag("1"), new NumTag("2")),
                new Mult(new NumTag("2"), new ExpTag("1")));
        RuleChecker checker = new RuleChecker();

        Expression expected = new Mult(new Plus(3, 4), new Pow("x", 2));
        Expression actual = checker.apply(rule, expression);

        assertEquals(expected, actual);
    }

    @Test
    public void apply8() throws Exception {
        Expression expression = new Mult("x", 2);
        Rule rule = new Rule(new Mult(new VarTag("1"), new NumTag("2")),
                new Mult(new NumTag("2"), new VarTag("1")));
        RuleChecker checker = new RuleChecker();

        Expression expected = new Mult(2, "x");
        Expression actual = checker.apply(rule, expression);

        assertEquals(expected, actual);
    }

    @Test
    public void applyRules() {
        Expression expression = new Plus("x", 0);
        RuleChecker checker = new RuleChecker();

        Expression expected = new Var("x");
        Expression actual = checker.applyRules(expression);
    }

    @Test
    public void applyRules2() {
        Expression expression = new Div(new Pow("x", 4), new Var("x"));
        RuleChecker checker = new RuleChecker();

        Expression expected = new Pow("x", 3);
        Expression actual = checker.applyRules(expression);
    }

    @Test
    public void applyRules3() {
        Expression expression = new Pow(new Plus(3, 4), new Var("x"));
        RuleChecker checker = new RuleChecker();

        Expression expected = new Pow(7, "x");
        Expression actual = checker.applyRules(expression);
    }

    @Test
    public void applyRules4() {
        Expression expression = new Pow(new Var("x"), new Plus(3, 4));
        RuleChecker checker = new RuleChecker();

        Expression expected = new Pow("x", 7);
        Expression actual = checker.applyRules(expression);
    }
}