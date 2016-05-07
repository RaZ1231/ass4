package binary;

import abstracts.BinaryExpression;
import interfaces.Expression;
import interfaces.ExtendedExpression;
import nestedsimplification.LinearSequence;
import operands.Num;
import operands.Var;

/**
 * Minus representation class.
 *
 * @author Raziel Solomon
 * @since 11-Apr-16.
 */
public class Minus extends BinaryExpression implements ExtendedExpression {
    /**
     * constructor.
     *
     * @param a an expression.
     * @param b another expression.
     */
    public Minus(Expression a, double b) {
        super(a, new Num(b));
    }

    /**
     * constructor.
     *
     * @param a an expression.
     * @param b another expression.
     */
    public Minus(double a, Expression b) {
        super(new Num(a), b);
    }

    /**
     * constructor.
     *
     * @param a an expression.
     * @param b another expression.
     */
    public Minus(Expression a, String b) {
        super(a, new Var(b));
    }

    /**
     * constructor.
     *
     * @param a an expression.
     * @param b another expression.
     */
    public Minus(String a, Expression b) {
        super(new Var(a), b);
    }

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
     * returns new expression by type.
     *
     * @param a an expression.
     * @param b another expression.
     * @return a new expression by type.
     */
    @Override
    public Expression create(Expression a, Expression b) {
        return new Minus(a, b);
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
     * returns the differentiate of an expression.
     *
     * @param var a string variable.
     * @return the differentiate of an expression.
     */
    @Override
    public Expression differentiate(String var) {
        return new Minus(getA().differentiate(var), getB().differentiate(var));
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

    /**
     * get list of expressions that are connected by linear operations.
     *
     * @return the list.
     */
    @Override
    public LinearSequence getLinearVariables() {
        LinearSequence vars = new LinearSequence();

        vars.addAll(((ExtendedExpression) getA()).getLinearVariables());
        vars.addAll(((ExtendedExpression) getB()).getLinearVariables().negate());

        return vars;
    }
}
