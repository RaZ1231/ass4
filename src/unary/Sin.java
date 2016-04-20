package unary;

import binary.Mult;
import operands.Num;
import operands.Var;
import structure.Expression;
import structure.UnaryExpression;

/**
 *  sin class representation.
 *
 * @author Elisheva Broyer.
 * @since 13/04/2016.
 */
public class Sin extends UnaryExpression implements Expression {
    /**
     * constructor.
     *
     * @param a a double variable.
     */
    public Sin(double a) {
        this(new Num(a));
    }

    /**
     * constructor.
     *
     * @param a an expression.
     */
    public Sin(Expression a) {
        super(a);
    }

    /**
     * constructor.
     *
     * @param a a string variable.
     */
    public Sin(String a) {
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
        return Math.sin(a);
    }

    /**
     * returns new expression by type.
     *
     * @param a an expression.
     * @return a new expression by type.
     */
    @Override
    public Expression create(Expression a) {
        return new Sin(a);
    }

    /**
     * returns the derivative of an expression.
     *
     * @param var a string variable.
     * @return the derivative of an expression.
     */
    @Override
    public Expression derivative(String var) {
        return new Mult(new Cos(getA()), getA().differentiate(var));
    }

    /**
     * returned a simplified version of the current expression.
     *
     * @return simplified expression.
     */
    @Override
    public Expression simple() {
        return new Sin(getA());
    }

    /**
     * returns a nice string representation of the expression.
     *
     * @return string representation.
     */
    @Override
    public String toString() {
        return "sin(" + getA() + ")";
    }
}
