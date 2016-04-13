package unary;

import operands.Num;
import operands.Var;
import structure.Expression;
import structure.UnaryExpression;

/**
 * @author Elisheva Broyer.
 * @since 13/04/2016.
 */
public class Neg extends UnaryExpression {
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
    public UnaryExpression create(Expression a) {
        return new Neg(a);
    }

    /**
     * Returned a simplified version of the current expression.
     *
     * @param a left expression
     * @return simplified expression
     */
    @Override
    public Expression simple(Expression a) {
        if (a instanceof Neg) {
            return ((Neg) a).getA();
        } else {
            return new Neg(a);
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
