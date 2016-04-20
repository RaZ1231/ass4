package unary;

import operands.Num;
import operands.Var;
import structure.Expression;
import structure.UnaryExpression;

/**
 *  negative class representation.
 *
 * @author Elisheva Broyer.
 * @since 13/04/2016.
 */
public class Neg extends UnaryExpression implements Expression {
    /**
     * constructor.
     *
     * @param a a double variable.
     */
    public Neg(double a) {
        this(new Num(a));
    }

    /**
     * constructor.
     *
     * @param a an expression.
     */
    public Neg(Expression a) {
        super(a);
    }

    /**
     * constructor.
     *
     * @param a a string variable.
     */
    public Neg(String a) {
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
        return -1 * a;
    }

    /**
     * returns new expression by type.
     *
     * @param a an expression.
     * @return a new expression by type.
     */
    @Override
    public Expression create(Expression a) {
        return new Neg(a);
    }

    /**
     * returns the derivative of an expression.
     *
     * @param var a string variable.
     * @return the derivative of an expression.
     */
    @Override
    public Expression derivative(String var) {
        return new Neg(getA().differentiate(var));
    }

    /**
     * returned a simplified version of the current expression.
     *
     * @return simplified expression.
     */
    @Override
    public Expression simple() {
        if (getA() instanceof Neg) {
            return ((Neg) getA()).getA();
        } else {
            return new Neg(getA());
        }
    }

    /**
     * returns a nice string representation of the expression.
     *
     * @return string representation.
     */
    @Override
    public String toString() {
        return "(-" + getA() + ")";
    }
}
