package main;

import interfaces.Expression;
import rulessimplification.Parser;

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
        Expression[] expressions = new Expression[5];
        Parser p = new Parser();

        expressions[0] = p.toExpression("(((e + y)^log((e + y), 8.0)) + ((x - 4.0) * 0.0))");

        expressions[1] = p.toExpression("((cos(pi) * ((y^4.0) / y)) - (-((-1.0) - 0.0)))");

        expressions[2] = p.toExpression("log(((0.0 + y) - 0.0), (y^8.0))");

        expressions[3] = p.toExpression("((x / 3.0) + (5.0 / y))");

        expressions[4] = p.toExpression("(((x - 6.0)^3.0) / (4.0 * (x - 6.0)))");

        for (Expression exp : expressions) {
            printPairs(exp);
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
