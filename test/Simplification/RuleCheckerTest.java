package Simplification;

import binary.*;
import operands.Num;
import operands.Var;
import org.junit.Test;
import structure.Expression;
import unary.Sin;

import static org.junit.Assert.*;

/**
 * @author Raziel Solomon
 * @since 18-Apr-16.
 */
public class RuleCheckerTest {
    @Test
    public void checkTrue() throws Exception {
        Expression complicated = new Pow(new Tag("1"), new Log(new Tag("1"), new Tag("2")));
        Expression simple = new Tag("2");
        Expression expression = new Pow(new Var("x"), new Log(new Var("x"), new Num(5)));
        Rule rule = new Rule(complicated, simple);
        RuleChecker checker = new RuleChecker();

        assertTrue(checker.check(rule, expression));
    }

    @Test
    public void checkComplicatedTrue() throws Exception {
        Expression complicated = new Pow(new Tag("1"), new Log(new Tag("1"), new Tag("2")));
        Expression simple = new Tag("2");
        Expression e1 = new Plus(new Sin("x"), new Mult("x", 2));
        Expression expression = new Pow(e1, new Log(e1, new Div("x", 5)));
        Rule rule = new Rule(complicated, simple);
        RuleChecker checker = new RuleChecker();

        assertTrue(checker.check(rule, expression));
    }

    @Test
    public void checkFalse() throws Exception {
        Expression complicated = new Pow(new Tag("1"), new Log(new Tag("1"), new Tag("2")));
        Expression simple = new Tag("2");
        Expression expression = new Pow(new Var("x"), new Log(new Var("y"), new Num(5)));
        Rule rule = new Rule(complicated, simple);
        RuleChecker checker = new RuleChecker();

        assertFalse(checker.check(rule, expression));
    }

    @Test
    public void checkComplicatedFalse() throws Exception {
        Expression e1 = new Plus(new Sin("x"), new Mult("x", 2));
        Expression e2 = new Plus(new Sin("y"), new Mult("x", 2));

        Expression complicated = new Pow(new Tag("1"), new Log(new Tag("1"), new Tag("2")));
        Expression simple = new Tag("2");
        Expression expression = new Pow(e1, new Log(e2, new Div("x", 5)));
        Rule rule = new Rule(complicated, simple);
        RuleChecker checker = new RuleChecker();

        assertFalse(checker.check(rule, expression));
    }

    @Test
    public void applyRule() throws Exception {
        Expression complicated = new Pow(new Tag("1"), new Log(new Tag("1"), new Tag("2")));
        Expression simple = new Tag("2");
        Rule rule = new Rule(complicated, simple);
        Expression expression = new Pow(new Var("x"), new Log(new Var("x"), new Num(5)));
        RuleChecker checker = new RuleChecker();
        checker.check(rule, expression);

        Expression expected = new Num(5);
        Expression actual = checker.applyRule(rule);

        assertEquals(expected, actual);
    }

    @Test
    public void applyRule2() throws Exception {
        Expression expression = new Mult("x", 2);
        Rule rule = new Rule(new Mult(new Tag("1"), new Num(2)), new Mult(new Num(2), new Tag("1")));
        RuleChecker checker = new RuleChecker();
        checker.check(rule, expression);

        Expression expected = new Mult(2, "x");
        Expression actual = checker.applyRule(rule);

        assertEquals(expected, actual);
    }

    @Test
    public void applyRule3() throws Exception {
        Expression expression = new Mult(new Var("x"), new Plus("x", 2));
        Rule rule = new Rule(new Mult(new Tag("1"), new Tag("2")), new Mult(new Tag("2"), new Tag("1")));
        RuleChecker checker = new RuleChecker();
        checker.check(rule, expression);

        Expression expected = new Mult(new Plus("x", 2), new Var("x"));
        Expression actual = checker.applyRule(rule);

        assertEquals(expected, actual);
    }

    @Test
    public void applyRule4() throws Exception {
        Expression expression = new Log(new Num(5), new Div(new Mult("x", 2), new Pow("x", 2)));
        Rule rule = new Rule(new Log(new Tag("1"), new Div(new Tag("2"), new Tag("3"))),
                new Minus(new Log(new Tag("1"), new Tag("2")), new Log(new Tag("1"), new Tag("3"))));
        RuleChecker checker = new RuleChecker();
        checker.check(rule, expression);

        Expression expected = new Minus(new Log(new Num(5), new Mult("x", 2)), new Log(new Num(5), new Pow("x", 2)));
        Expression actual = checker.applyRule(rule);

        assertEquals(expected, actual);
    }

    @Test
    public void applyRule5() throws Exception {
        Expression expression = new Pow(new Pow("x", 2), new Num(3));
        Rule rule = new Rule(new Pow(new Pow(new Tag("1"), new Tag("2")), new Tag("3")),
                new Pow(new Tag("1"), new Mult(new Tag("2"), new Tag("3"))));
        RuleChecker checker = new RuleChecker();
        checker.check(rule, expression);

        Expression expected = new Pow(new Var("x"), new Mult(2, 3));
        Expression actual = checker.applyRule(rule);

        assertEquals(expected, actual);
    }

    @Test
    public void applyRuleComplicated() throws Exception {
        Expression expression = new Pow(new Pow(new Plus("x", 5), new Mult("x", "y")), new Var("z"));
        Rule rule = new Rule(new Pow(new Pow(new Tag("1"), new Tag("2")), new Tag("3")),
                new Pow(new Tag("1"), new Mult(new Tag("2"), new Tag("3"))));
        RuleChecker checker = new RuleChecker();
        checker.check(rule, expression);

        Expression expected = new Pow(new Plus("x", 5), new Mult(new Mult("x", "y"), new Var("z")));
        Expression actual = checker.applyRule(rule);

        assertEquals(expected, actual);
    }
}