package binary;

import abstracts.CommutativeExpression;
import interfaces.Expression;
import interfaces.ExtendedExpression;
import nestedsimplification.LinearSequence;
import operands.Num;
import operands.Var;

/**
 * Plus representation class.
 *
 * @author Raziel Solomon
 * @since 11-Apr-16.
 */
public class Plus extends CommutativeExpression implements ExtendedExpression {
    /**
     * constructor.
     *
     * @param a an expression.
     * @param b another expression.
     */
    public Plus(Expression a, double b) {
        super(a, new Num(b));
    }

    /**
     * constructor.
     *
     * @param a an expression.
     * @param b another expression.
     */
    public Plus(double a, Expression b) {
        super(new Num(a), b);
    }

    /**
     * constructor.
     *
     * @param a an expression.
     * @param b another expression.
     */
    public Plus(Expression a, String b) {
        super(a, new Var(b));
    }

    /**
     * constructor.
     *
     * @param a an expression.
     * @param b another expression.
     */
    public Plus(String a, Expression b) {
        super(new Var(a), b);
    }

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
     * returns the differentiate of an expression.
     *
     * @param var a string variable.
     * @return the differentiate of an expression.
     */
    @Override
    public Expression differentiate(String var) {
        return new Plus(getA().differentiate(var), getB().differentiate(var));
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

    /**
     * get list of expressions that are connected by linear operations
     *
     * @return the list
     */
    @Override
    public LinearSequence getLinearVariables() {
        LinearSequence vars = new LinearSequence();

        vars.addAll(((ExtendedExpression) getA()).getLinearVariables());
        vars.addAll(((ExtendedExpression) getB()).getLinearVariables());

        return vars;
    }
}
