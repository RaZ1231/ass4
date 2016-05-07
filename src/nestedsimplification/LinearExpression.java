package nestedsimplification;

import abstracts.BaseExpression;
import abstracts.NestedExpression;
import binary.Minus;
import binary.Plus;
import interfaces.Expression;
import interfaces.ExtendedExpression;
import unary.Neg;

/**
 * Class contains an expression and its sign
 *
 * @author Raziel Solomon
 * @since 06-May-16.
 */
public class LinearExpression implements NestedExpression {
    private ExtendedExpression expression;
    private boolean isPositive;

    /**
     * constructor
     *
     * @param expression positive expression
     */
    public LinearExpression(ExtendedExpression expression) {
        this(expression, true);
    }

    /**
     * constructor
     *
     * @param expression the expression
     * @param isPositive the sign
     */
    public LinearExpression(ExtendedExpression expression, boolean isPositive) {
        this.expression = expression;
        this.isPositive = isPositive;
    }

    /**
     * string representation
     *
     * @return string
     */
    @Override
    public String toString() {
        if (isPositive()) {
            return "(+) " + expression;
        } else {
            return "(-) " + expression;
        }
    }

    /**
     * get the expression
     *
     * @return expression
     */
    public ExtendedExpression getExpression() {
        return expression;
    }

    /**
     * check sign
     *
     * @return true - positive/false - negative
     */
    public boolean isPositive() {
        return isPositive;
    }

    /**
     * flip sign
     */
    public void negate() {
        isPositive = !isPositive;
    }


    /**
     * return signed expression
     *
     * @return signed expression
     */
    public Expression toExpression() {
        if (isPositive()) {
            return getExpression();
        } else {
            return new Neg(getExpression());
        }
    }

    /**
     * combine two expressions
     *
     * @param other expression
     * @return combined (binary) expression
     */
    public BaseExpression toBaseExpression(NestedExpression other) {
        try {
            LinearExpression other2 = (LinearExpression) other;

            Expression e1 = getExpression();
            Expression e2 = other.getExpression();

            if (isPositive()) {
                if (other2.isPositive()) {
                    return new Plus(e1, e2);
                } else {
                    return new Minus(e1, e2);
                }
            } else {
                if (other2.isPositive()) {
                    return new Minus(e2, e1);
                } else {
                    return new Neg(new Plus(e1, e2));
                }
            }
        } catch (Exception e) {
            return null;
        }
    }
}
