package unary;

import operands.Num;
import operands.Var;
import structure.Expression;
import structure.UnaryExpression;

/**
 * @author Elisheva Broyer.
 * @since 13/04/2016.
 */
public class Cos extends UnaryExpression{
    /**
     * constructor..
     *
     * @param a an expression.
     */
    public Cos(Expression a) {
        super(a);
    }

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
     * returns a nice string representation of the expression.
     *
     * @param a an expression.
     * @return string representation.
     */
    @Override
    public String toString(Expression a) {
        return "Cos(" + a + ")";
    }
}
