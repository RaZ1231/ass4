package binary;

import operands.Num;
import operands.Var;
import structure.BinaryExpression;
import structure.Expression;
import unary.Neg;

/**
 * @author Raziel Solomon
 * @since 11-Apr-16.
 */
public class Minus extends BinaryExpression {
    /**
     * constructor.
     *
     * @param a a double variable.
     * @param b another double variable.
     */
    public Minus(double a, double b) {
        this(new Num(a), new Num(b));
    }

    /**
     * constructor.
     *
     * @param a an expression.
     * @param b another expression.
     */
    public Minus(Expression a, Expression b) {
        super(a, b);
    }

    /**
     * constructor.
     *
     * @param a a double variable.
     * @param b a string variable.
     */
    public Minus(double a, String b) {
        this(new Num(a), new Var(b));
    }

    /**
     * constructor.
     *
     * @param a a string variable.
     * @param b a double variable.
     */
    public Minus(String a, double b) {
        this(new Var(a), new Num(b));
    }

    /**
     * constructor.
     *
     * @param a a string variable.
     * @param b a string variable.
     */
    public Minus(String a, String b) {
        this(new Var(a), new Var(b));
    }

    /**
     * an operation function.
     *
     * @param a a parameter.
     * @param b another parameter.
     * @return operation result.
     */
    @Override
    public double operate(double a, double b) {
        return a - b;
    }

    /**
     * returns new expression by type.
     *
     * @param a an expression.
     * @param b another expression.
     * @return a new expression by type.
     */
    @Override
    public BinaryExpression create(Expression a, Expression b) {
        return new Minus(a, b);
    }

    /**
     * returns the derivative of an expression.
     *
     * @param var a string variable.
     * @return the derivative of an expression.
     */
    @Override
    public Expression derivative(String var) {
        return new Minus(getA().differentiate(var), getB().differentiate(var));
    }

    /**
     * Returned a simplified version of the current expression.
     *
     * @param a left expression
     * @param b right expression
     * @return simplified expression
     */
    @Override
    public Expression simple(Expression a, Expression b) {
        if (a.isZero()) {
            return new Neg(b);
        } else if (b.isZero()) {
            return a;
        } else if (a.equals(b)) {
            return new Num(0);
        } else {
            return new Minus(a, b);
        }
    }

    /**
     * returns a nice string representation of the expression.
     *
     * @return string representation.
     */
    @Override
    public String toString() {
        return "(" + getA() + " - " + getB() + ")";
    }
}
