package binary;

import Simplifiers.PlusSimplifier;
import operands.Num;
import operands.Var;
import structure.BinaryExpression;
import structure.Expression;

/**
 * Plus representation class.
 *
 * @author Raziel Solomon
 * @since 11-Apr-16.
 */
public class Plus extends BinaryExpression implements Expression {
    /**
     * constructor.
     *
     * @param a a double variable.
     * @param b another double variable.
     */
    public Plus(double a, double b) {
        this(new Num(a), new Num(b));
    }

    /**
     * constructor.
     *
     * @param a an expression.
     * @param b another expression.
     */
    public Plus(Expression a, Expression b) {
        super(a, b);
    }

    /**
     * constructor.
     *
     * @param a a double variable.
     * @param b a string variable.
     */
    public Plus(double a, String b) {
        this(new Num(a), new Var(b));
    }

    /**
     * constructor.
     *
     * @param a a string variable.
     * @param b a double variable.
     */
    public Plus(String a, double b) {
        this(new Var(a), new Num(b));
    }

    /**
     * constructor.
     *
     * @param a a string variable.
     * @param b a string variable.
     */
    public Plus(String a, String b) {
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
        return a + b;
    }

    /**
     * returns new expression by type.
     *
     * @param a an expression.
     * @param b another expression.
     * @return a new expression by type.
     */
    @Override
    public Expression create(Expression a, Expression b) {
        return new Plus(a, b);
    }

    /**
     * returns the derivative of an expression.
     *
     * @param var a string variable.
     * @return the derivative of an expression.
     */
    @Override
    public Expression derivative(String var) {
        return new Plus(getA().differentiate(var), getB().differentiate(var));
    }

    /**
     * Returned a simplified version of the current expression.
     *
     * @return simplified expression
     */
    @Override
    public Expression simple() {
        PlusSimplifier simplifier = new PlusSimplifier();

        simplifier.initBy(this);

        return simplifier.simplify(this);
    }

    /**
     * returns a nice string representation of the expression.
     *
     * @return string representation.
     */
    @Override
    public String toString() {
        return "(" + getA() + " + " + getB() + ")";
    }
}
