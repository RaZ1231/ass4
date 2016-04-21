package unary;

import binary.Mult;
import operands.Num;
import operands.Var;
import structure.Expression;
import structure.UnaryExpression;

/**
 * cos class representation.
 *
 * @author Elisheva Broyer.
 * @since 13/04/2016.
 */
public class Cos extends UnaryExpression implements Expression {
    /**
     * constructor.
     *
     * @param a a double variable.
     */
    public Cos(double a) {
        this(new Num(a));
    }

    /**
     * constructor.
     *
     * @param a an expression.
     */
    public Cos(Expression a) {
        super(a);
    }

    /**
     * constructor.
     *
     * @param a a string variable.
     */
    public Cos(String a) {
        this(new Var(a));
    }

    /**
     * an operation function.
     *
     * @param a a parameter.
     * @return operation result.
     */
    @Override
    public double operate(double a) {
        return Math.cos(a);
    }

    /**
     * returns new expression by type.
     *
     * @param a an expression.
     * @return a new expression by type.
     */
    @Override
    public Expression create(Expression a) {
        return new Cos(a);
    }

    /**
     * returns the differentiate of an expression.
     *
     * @param var a string variable.
     * @return the differentiate of an expression.
     */
    @Override
    public Expression differentiate(String var) {
        return new Neg(new Mult(new Sin(getA()), getA().differentiate(var)));
    }

    /**
     * returns a nice string representation of the expression.
     *
     * @return string representation.
     */
    @Override
    public String toString() {
        return "cos(" + getA() + ")";
    }
}
