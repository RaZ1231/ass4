package binary;

import operands.Num;
import operands.Var;
import structure.BinaryExpression;
import structure.Expression;

/**
 * Divide representation class.
 *
 * @author Raziel Solomon
 * @since 11-Apr-16.
 */
public class Div extends BinaryExpression implements Expression {
    /**
     * constructor.
     *
     * @param a an expression.
     * @param b another expression.
     */
    public Div(Expression a, Expression b) {
        super(a, b);
    }

    /**
     * constructor.
     *
     * @param a an expression.
     * @param b another expression.
     */
    public Div(Expression a, double b) {
        super(a, new Num(b));
    }

    /**
     * constructor.
     *
     * @param a an expression.
     * @param b another expression.
     */
    public Div(double a, Expression b) {
        super(new Num(a), b);
    }

    /**
     * constructor.
     *
     * @param a an expression.
     * @param b another expression.
     */
    public Div(Expression a, String b) {
        super(a, new Var(b));
    }

    /**
     * constructor.
     *
     * @param a an expression.
     * @param b another expression.
     */
    public Div(String a, Expression b) {
        super(new Var(a), b);
    }

    /**
     * constructor.
     *
     * @param a a double variable.
     * @param b another double variable.
     */
    public Div(double a, double b) {
        this(new Num(a), new Num(b));
    }


    /**
     * constructor.
     *
     * @param a a double variable.
     * @param b a string variable.
     */
    public Div(double a, String b) {
        this(new Num(a), new Var(b));
    }

    /**
     * constructor.
     *
     * @param a a string variable.
     * @param b a double variable.
     */
    public Div(String a, double b) {
        this(new Var(a), new Num(b));
    }

    /**
     * constructor.
     *
     * @param a a string variable.
     * @param b a string variable.
     */
    public Div(String a, String b) {
        this(new Var(a), new Var(b));
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
        return new Div(a, b);
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
        return a / b;
    }

    /**
     * returns the differentiate of an expression.
     *
     * @param var a string variable.
     * @return the differentiate of an expression.
     */
    @Override
    public Expression differentiate(String var) {
        return new Div(
                new Minus(
                        new Mult(getA().differentiate(var), getB())
                        , new Mult(getA(), getB().differentiate(var))),
                new Pow(getB(), new Num(2)));
    }

    /**
     * returns a nice string representation of the expression.
     *
     * @return string representation.
     */
    @Override
    public String toString() {
        return "(" + getA() + " / " + getB() + ")";
    }
}
