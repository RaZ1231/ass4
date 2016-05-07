package abstracts;

import interfaces.Expression;
import interfaces.ExtendedExpression;

/**
 * Interface define demands for NestedExpression
 *
 * @author Raziel Solomon
 * @since 07-May-16.
 */
public interface NestedExpression {
    /**
     * get expression
     *
     * @return expression
     */
    ExtendedExpression getExpression();

    /**
     * flips sign for instance +/-
     */
    void negate();

    /**
     * return signed expression
     *
     * @return signed expression
     */
    Expression toExpression();

    /**
     * combine two expressions
     *
     * @param other expression
     * @return combined (binary) expression
     */
    BaseExpression toBaseExpression(NestedExpression other);
}
