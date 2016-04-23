package main;

import binary.Div;
import binary.Log;
import binary.Minus;
import binary.Mult;
import binary.Plus;
import binary.Pow;
import operands.Const;
import operands.Num;
import operands.Var;
import structure.Expression;
import unary.Cos;
import unary.Neg;
import unary.Sin;

/**
 * a main that demonstrates our simplification.
 *
 * @author Elisheva Broyer.
 * @since 22/04/2016.
 */
public class SimplificationDemo {
    public static void main(String[] args) {
        Expression[] expressions = new Expression[5];

        expressions[0] = new Plus(
                new Pow(
                        new Plus(new Const("e", Math.exp(1)), new Var("y")),
                        new Log(new Plus(new Const("e", Math.exp(1)), new Var("y")), new Num(8))),
                        new Mult(new Minus(new Var("x"), new Num(4)), new Num(0)));
        expressions[1] = new Minus(
                new Mult(
                        new Cos(new Const("pi", Math.PI)),
                        new Div(new Pow(new Var("y"), new Num(4)), new Var("y"))),
                new Neg(new Minus(new Neg(1), new Num(0))));
        expressions[2] = new Log(
                new Minus(
                        new Plus(new Num(0), new Var("y")), new Num(0)),
                new Pow(new Var("y"), new Num(8)));
        expressions[3] = new Plus(new Div(new Var("x"), new Num(3)), new Div(new Num(5), new Var("y")));
        expressions[4] = new Div(
                new Pow(new Minus("x", 6), new Num(3)),
                new Mult(new Num(4), new Minus("x", 6)));


        for (Expression e: expressions){
            printPairs(e);
        }
    }

    public static void printPairs(Expression e) {
        System.out.println(e.toString());
        System.out.println(e.simplify().toString());
        System.out.println();
    }
}
