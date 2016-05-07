package binary;

import abstracts.CommutativeExpression;
import interfaces.Expression;
import interfaces.ExtendedExpression;
import nestedsimplification.PolynomialSequence;
import operands.Num;
import operands.Var;

/**
 * Multiple representation class.
 *
 * @author Raziel Solomon
 * @since 11-Apr-16.
 */
public class Mult extends CommutativeExpression implements ExtendedExpression {
    /**
     * constructor.
     *
     * @param a an expression.
     * @param b another expression.
     */
    public Mult(Expression a, double b) {
        super(new Num(b), a);
    }

    /**
     * constructor.
     *
     * @param a an expression.
     * @param b another expression.
     */
    public Mult(String a, Num b) {
        super(b, new Var(a));
    }

    /**
     * constructor.
     *
     * @param a an expression.
     * @param b another expression.
     */
    public Mult(String a, double b) {
        super(new Num(b), new Var(a));
    }

    /**
     * constructor.
     *
     * @param a an expression.
     * @param b another expression.
     */
    public Mult(double a, Expression b) {
        super(new Num(a), b);
    }

    /**
     * constructor.
     *
     * @param a an expression.
     * @param b another expression.
     */
    public Mult(Expression a, String b) {
        super(a, new Var(b));
    }

    /**
     * constructor.
     *
     * @param a an expression.
     * @param b another expression.
     */
    public Mult(String a, Expression b) {
        super(new Var(a), b);
    }

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
    public Mult(Expression a, Num b) {
        super(b, a);
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
     * @param a an expression.
     * @param b another expression.
     */
    public Mult(Expression a, Expression b) {
        super(a, b);
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
     * returns new expression by type.
     *
     * @param a an expression.
     * @param b another expression.
     * @return a new expression by type.
     */
    @Override
    public Expression create(Expression a, Expression b) {
        return new Mult(a, b);
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
     * returns the differentiate of an expression.
     *
     * @param var a string variable.
     * @return the differentiate of an expression.
     */
    @Override
    public Expression differentiate(String var) {
        return new Plus(new Mult(getA().differentiate(var), getB()),
                new Mult(getA(), getB().differentiate(var)));
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

    /**
     * get list of expressions that are connected by polynomial operations.
     *
     * @return the list.
     */
    @Override
    public PolynomialSequence getPolynomialVariables() {
        PolynomialSequence vars = new PolynomialSequence();

        vars.addAll(((ExtendedExpression) getA()).getPolynomialVariables());
        vars.addAll(((ExtendedExpression) getB()).getPolynomialVariables());

        return vars;
    }
}
