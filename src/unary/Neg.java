package unary;

import abstracts.UnaryExpression;
import interfaces.Expression;
import interfaces.ExtendedExpression;
import nestedsimplification.LinearSequence;
import operands.Num;
import operands.Var;

/**
 * negative class representation.
 *
 * @author Elisheva Broyer.
 * @since 13/04/2016.
 */
public class Neg extends UnaryExpression implements ExtendedExpression {
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
     * returns new expression by type.
     *
     * @param a an expression.
     * @return a new expression by type.
     */
    @Override
    public Expression create(Expression a) {
        return new Neg(a);
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
     * returns the differentiate of an expression.
     *
     * @param var a string variable.
     * @return the differentiate of an expression.
     */
    @Override
    public Expression differentiate(String var) {
        return new Neg(getA().differentiate(var));
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

    /**
     * get list of expressions that are connected by linear operations
     *
     * @return the list
     */
    @Override
    public LinearSequence getLinearVariables() {
        LinearSequence vars = new LinearSequence();

        vars.addAll(((ExtendedExpression) getA()).getLinearVariables().negate());

        return vars;
    }
}
