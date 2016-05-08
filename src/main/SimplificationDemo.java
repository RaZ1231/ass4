package main;

import binary.*;
import interfaces.Expression;
import operands.Num;
import operands.Var;
import rulessimplification.Parser;
import unary.Cos;
import unary.Sin;

import java.util.LinkedList;
import java.util.List;

/**
 * a main that demonstrates our rulessimplification.
 *
 * @author Elisheva Broyer.
 * @since 22/04/2016.
 */
public class SimplificationDemo {
    /**
     * main method.
     *
     * @param args data from user.
     */
    public static void main(String[] args) {
        List<Expression> expressions = new LinkedList<>();
        Parser p = new Parser();

        expressions.add(new Log(new Num(5), new Div(new Mult("x", 2), new Pow("x", 2))));
        expressions.add(new Pow(new Pow(new Plus("x", 5), new Mult("x", "y")), new Var("z")));
        expressions.add(new Minus(new Plus(
                new Log(2, new Mult(new Mult(2, new Pow("x", 2)), new Mult(8, "y"))),
                new Log(2, new Mult(2, new Pow("x", 2)))), new Log(2, new Mult(2, "y"))));
        expressions.add(new Plus(new Plus(new Plus(new Plus(new Plus(new Plus(new Plus(
                new Mult(2, "x"), new Mult(3, "y")), new Mult(4, "z")), new Mult(5, "a")),
                new Mult(4, "x")), new Mult(2, "y")), new Mult(5, "z")), new Mult(5, "a")));
        expressions.add(new Plus(new Plus(new Pow(new Cos("x"), 2), -1), new Pow(new Sin("x"), 2)));
        expressions.add(new Mult(new Mult(new Mult(new Mult(3, "x"), "y"), "z"), 5));
        expressions.add(new Div(new Mult(new Mult(new Mult(5, "x"), "y"), "z"), 5));
        expressions.add(p.toExpression("(((e + y)^log((e + y), 8.0)) + ((x - 4.0) * 0.0))"));
        expressions.add(p.toExpression("((cos(pi) * ((y^4.0) / y)) - (-((-1.0) - 0.0)))"));
        expressions.add(p.toExpression("(8^log(8, (log(2, (y / x)) + log(2, x))))"));
        expressions.add(p.toExpression("(((x / 3.0) + (7 / y)) + (5.0 / 0))"));
        expressions.add(p.toExpression("((((x - 6.0)^3.0) / (4.0 * (x - 6.0))) + (((x - 6.0)^2.0) / 4.0)"));
        expressions.add(p.toExpression("(((((((x + y) - x) + y) - x) + y) - x) - y)"));

        for (Expression expression : expressions) {
            printPairs(expression);
        }
    }

    /**
     * prints out pairs of non-simplified and simplified expressions.
     *
     * @param exp an expression.
     */
    private static void printPairs(Expression exp) {
        System.out.println(exp);
        System.out.println(exp.simplify());
        System.out.println();
    }
}
