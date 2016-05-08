package binary;

import abstracts.BinaryExpression;
import interfaces.Expression;
import interfaces.ExtendedExpression;
import operands.Const;
import operands.Num;
import operands.Var;

/**
 * Log representation class.
 *
 * @author Raziel Solomon
 * @since 11-Apr-16.
 */
public class Log extends BinaryExpression implements ExtendedExpression {
    /**
     * constructor.
     *
     * @param a an expression.
     * @param b another expression.
     */
    public Log(Expression a, double b) {
        super(a, new Num(b));
    }

    /**
     * constructor.
     *
     * @param a an expression.
     * @param b another expression.
     */
    public Log(double a, Expression b) {
        super(new Num(a), b);
    }

    /**
     * constructor.
     *
     * @param a an expression.
     * @param b another expression.
     */
    public Log(Expression a, String b) {
        super(a, new Var(b));
    }

    /**
     * constructor.
     *
     * @param a an expression.
     * @param b another expression.
     */
    public Log(String a, Expression b) {
        super(new Var(a), b);
    }

    /**
     * constructor.
     *
     * @param a a double variable.
     * @param b another double variable.
     */
    public Log(double a, double b) {
        this(new Num(a), new Num(b));
    }

    /**
     * constructor.
     *
     * @param a an expression.
     * @param b another expression.
     */
    public Log(Expression a, Expression b) {
        super(a, b);
    }

    /**
     * constructor.
     *
     * @param a a double variable.
     * @param b a string variable.
     */
    public Log(double a, String b) {
        this(new Num(a), new Var(b));
    }

    /**
     * constructor.
     *
     * @param a a string variable.
     * @param b a double variable.
     */
    public Log(String a, double b) {
        this(new Var(a), new Num(b));
    }

    /**
     * constructor.
     *
     * @param a a string variable.
     * @param b a string variable.
     */
    public Log(String a, String b) {
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
        return new Log(a, b);
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
        return Math.log(b) / Math.log(a);
    }

    /**
     * returns the differentiate of an expression.
     *
     * @param var a string variable.
     * @return the differentiate of an expression.
     */
    @Override
    public Expression differentiate(String var) {
        if (getA().differentiate(var).equals(new Num(0))) {
            return new Mult(
                    new Div(
                            new Num(1), new Mult(getB(), new Log(new Const("e", Math.exp(1)), getA()))),
                    getB().differentiate(var));
        } else {
            Div div = new Div(
                    new Log(new Const("e", Math.exp(1)), getB()),
                    new Log(new Const("e", Math.exp(1)), getA()));
            return div.differentiate(var);
        }
    }

    /**
     * returns a nice string representation of the expression.
     *
     * @return string representation.
     */
    @Override
    public String toString() {
        return "log(" + getA() + ", " + getB() + ")";
    }
}
