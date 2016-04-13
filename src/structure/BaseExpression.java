package structure;

/**
 * @author Raziel Solomon
 * @since 11-Apr-16.
 */

/**
 *
 */
public abstract class BaseExpression implements Expression {
    @Override
    public double evaluate() throws Exception {
        return evaluate(null);
    }
}
