package unary;

import binary.Mult;
import operands.Num;
import operands.Var;
import structure.Expression;
import structure.UnaryExpression;

/**
 * @author Elisheva Broyer.
 * @since 13/04/2016.
 */
public class Cos extends UnaryExpression {
    /**
     * constructor.
     *
     * @param a a double variable.
     */
    public Cos(double a) {
        this(new Num(a));
    }

    /**
     * constructor..
     *
     * @param a an expression.
     */
    public Cos(Expression a) {
        super(a);
    }

    /**
     * constructor.
     *
     * @param a a string variable.
     */
    public Cos(String a) {
        this(new Var(a));
    }

    /**
     * an operation function.
     *
     * @param a a parameter.
     * @return operation result.
     */
    @Override
    public double operate(double a) {
        return Math.cos(a);
    }

    /**
     * returns new expression by type.
     *
     * @param a an expression.
     * @return a new expression by type.
     */
    @Override
    public UnaryExpression create(Expression a) {
        return new Cos(a);
    }

    /**
     * returns the derivative of an expression.
     *
     * @param var a string variable.
     * @return the derivative of an expression.
     */
    @Override
    public Expression derivative(String var) {
        return new Neg(new Mult(new Sin(getA()), getA().differentiate(var)));
    }

    /**
     * Returned a simplified version of the current expression.
     *
     * @param a left expression
     * @return simplified expression
     */
    @Override
    public Expression simple(Expression a) {
        return new Cos(a);
    }

    /**
     * returns a nice string representation of the expression.
     *
     * @return string representation.
     */
    @Override
    public String toString() {
        return "Cos(" + getA() + ")";
    }
}
