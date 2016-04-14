package structure;

/**
 * @author Raziel Solomon
 * @since 11-Apr-16.
 */

/**
 *
 */
public abstract class BaseExpression implements Expression {
    /**
     * a convenience method. Similar to `evaluate(assignment)` method above,
     * but uses an empty assignment.
     *
     * @return equation solution
     * @throws Exception
     */
    @Override
    public double evaluate() throws Exception {
        return evaluate(null);
    }

    /**
     * checks if zero
     *
     * @return true\false
     */
    @Override
    public boolean isZero() {
        return false;
    }

    /**
     * checks if one
     *
     * @return true/false
     */
    @Override
    public boolean isOne() {
        return false;
    }

    /**
     * checks if even
     *
     * @return true/false
     */
    @Override
    public boolean isEven() {
        return false;
    }

    /**
     * returns the expression tree resulting from differentiating
     * the current expression relative to variable `var`.
     *
     * @param var a string variable.
     * @return the differentiate of the expression.
     */
    public Expression differentiate(String var) {
        return derivative(var);
    }

    /**
     * returns the derivative of an expression.
     *
     * @param var a string variable.
     * @return the derivative of an expression.
     */
    public abstract Expression derivative(String var);

}
