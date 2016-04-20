package main;

import binary.Mult;
import binary.Plus;
import binary.Pow;
import operands.Const;
import structure.Expression;
import unary.Sin;

import java.util.Map;
import java.util.TreeMap;

/**
 * Testing assignment.
 *
 * @author Raziel Solomon
 * @since 14-Apr-16.
 */
public class ExpressionsTest {
    /**
     * main method.
     *
     * @param args data from user.
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        Expression expression = new Plus(
                new Mult(2, "x"),
                new Plus(
                        new Sin(new Mult(4, "y")),
                        new Pow(new Const("e", Math.exp(1)), "x")
                ));
        Map<String, Double> assignment = new TreeMap<>();

        assignment.put("x", 2.0);
        assignment.put("y", 0.25);

        System.out.println(expression);
        System.out.println(expression.evaluate(assignment));
        System.out.println(expression.differentiate("x"));
        System.out.println(expression.differentiate("x").evaluate(assignment));
        System.out.println(expression.differentiate("x").simplify());
    }
}
