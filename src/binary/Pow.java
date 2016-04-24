package binary;

import operands.Const;
import operands.Num;
import operands.Var;
import structure.BinaryExpression;
import structure.Expression;

/**
 * Pow representation class.
 *
 * @author Raziel Solomon
 * @since 11-Apr-16.
 */
public class Pow extends BinaryExpression implements Expression {
    /**
     * constructor.
     *
     * @param a an expression.
     * @param b another expression.
     */
    public Pow(Expression a, Expression b) {
        super(a, b);
    }

    /**
     * constructor.
     *
     * @param a an expression.
     * @param b another expression.
     */
    public Pow(Expression a, double b) {
        super(a, new Num(b));
    }

    /**
     * constructor.
     *
     * @param a an expression.
     * @param b another expression.
     */
    public Pow(double a, Expression b) {
        super(new Num(a), b);
    }

    /**
     * constructor.
     *
     * @param a an expression.
     * @param b another expression.
     */
    public Pow(Expression a, String b) {
        super(a, new Var(b));
    }

    /**
     * constructor.
     *
     * @param a an expression.
     * @param b another expression.
     */
    public Pow(String a, Expression b) {
        super(new Var(a), b);
    }

    /**
     * constructor.
     *
     * @param a a double variable.
     * @param b another double variable.
     */
    public Pow(double a, double b) {
        this(new Num(a), new Num(b));
    }

    /**
     * constructor.
     *
     * @param a a double variable.
     * @param b a string variable.
     */
    public Pow(double a, String b) {
        this(new Num(a), new Var(b));
    }

    /**
     * constructor.
     *
     * @param a a string variable.
     * @param b a double variable.
     */
    public Pow(String a, double b) {
        this(new Var(a), new Num(b));
    }

    /**
     * constructor.
     *
     * @param a a string variable.
     * @param b a string variable.
     */
    public Pow(String a, String b) {
        this(new Var(a), new Var(b));
    }

    /**
     * constructor.
     *
     * @param a a string variable.
     * @param b a string variable.
     */
    public Pow(Const a, String b) {
        this(a, new Var(b));
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
        return Math.pow(a, b);
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
        return new Pow(a, b);
    }

    /**
     * returns the differentiate of an expression.
     *
     * @param var a string variable.
     * @return the differentiate of an expression.
     */
    @Override
    public Expression differentiate(String var) {
        return new Mult(new Pow(getA(), getB()),
                new Plus(
                        new Mult(getA().differentiate(var),
                                new Div(getB(), getA())),
                        new Mult(getB().differentiate(var),
                                new Log(new Const("e", Math.exp(1)), getA()))));
    }

    /**
     * returns a nice string representation of the expression.
     *
     * @return string representation.
     */
    @Override
    public String toString() {
        return "(" + getA() + "^" + getB() + ")";
    }
}
