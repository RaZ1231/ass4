package unary;

import abstracts.UnaryExpression;
import binary.Mult;
import interfaces.Expression;
import interfaces.ExtendedExpression;
import operands.Num;
import operands.Var;

/**
 * sin class representation.
 *
 * @author Elisheva Broyer.
 * @since 13/04/2016.
 */
public class Sin extends UnaryExpression implements ExtendedExpression {
    /**
     * constructor.
     *
     * @param a a double variable.
     */
    public Sin(double a) {
        this(new Num(a));
    }

    /**
     * constructor.
     *
     * @param a an expression.
     */
    public Sin(Expression a) {
        super(a);
    }

    /**
     * constructor.
     *
     * @param a a string variable.
     */
    public Sin(String a) {
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
        return new Sin(a);
    }

    /**
     * an operation function.
     *
     * @param a a parameter.
     * @return operation result.
     */
    @Override
    public double operate(double a) {
        return Math.sin(a);
    }

    /**
     * returns the differentiate of an expression.
     *
     * @param var a string variable.
     * @return the differentiate of an expression.
     */
    @Override
    public Expression differentiate(String var) {
        return new Mult(new Cos(getA()), getA().differentiate(var));
    }

    /**
     * returns a nice string representation of the expression.
     *
     * @return string representation.
     */
    @Override
    public String toString() {
        return "sin(" + getA() + ")";
    }
}
