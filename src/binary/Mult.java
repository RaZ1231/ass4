package binary;

import operands.Num;
import operands.Var;
import structure.BinaryExpression;
import structure.Expression;

/**
 * @author Raziel Solomon
 * @since 11-Apr-16.
 */
public class Mult extends BinaryExpression {
    /**
     * constructor.
     *
     * @param a a double variable.
     * @param b another double variable.
     */
    public Mult(double a, double b) {
        this(new Num(a), new Num(b));
    }

    /**
     * constructor.
     *
     * @param a an expression.
     * @param b another expression.
     */
    public Mult(Expression a, Expression b) {
        super(a, b);
    }

    /**
     * constructor.
     *
     * @param a a double variable.
     * @param b a string variable.
     */
    public Mult(double a, String b) {
        this(new Num(a), new Var(b));
    }

    /**
     * constructor.
     *
     * @param a a string variable.
     * @param b a double variable.
     */
    public Mult(String a, double b) {
        this(new Var(a), new Num(b));
    }

    /**
     * constructor.
     *
     * @param a a string variable.
     * @param b a string variable.
     */
    public Mult(String a, String b) {
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
        return a * b;
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
        return new Mult(a, b);
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
        if (a.isOne()) {
            return b;
        } else if (b.isOne()) {
            return a;
        } else if (a.equals(b)) {
            return new Pow(a, new Num(2));
        } else {
            return new Mult(a, b);
        }
    }

    /**
     * returns a nice string representation of the expression.
     *
     * @return string representation.
     */
    @Override
    public String toString() {
        return "(" + getA() + " * " + getB() + ")";
    }
}
