package nestedsimplification;

import abstracts.BaseExpression;
import abstracts.NestedExpression;
import binary.Div;
import binary.Mult;
import interfaces.Expression;
import interfaces.ExtendedExpression;

/**
 * Class contains an expression and its power's sign.
 *
 * @author Raziel Solomon
 * @since 07-May-16.
 */
public class PolynomialExpression implements NestedExpression {
    private ExtendedExpression expression;
    private boolean isPositivePower;

    /**
     * constructor.
     *
     * @param expression non - fraction expression.
     */
    public PolynomialExpression(ExtendedExpression expression) {
        this(expression, true);
    }

    /**
     * constructor.
     *
     * @param expression      the expression.
     * @param isPositivePower opposite of is fraction.
     */
    public PolynomialExpression(ExtendedExpression expression, boolean isPositivePower) {
        this.expression = expression;
        this.isPositivePower = isPositivePower;
    }

    /**
     * string representation.
     *
     * @return a string.
     */
    @Override
    public String toString() {
        if (isPositivePower()) {
            return "(/1) " + expression;
        } else {
            return "(1/) " + expression;
        }
    }

    /**
     * get the expression.
     *
     * @return expression.
     */
    public ExtendedExpression getExpression() {
        return expression;
    }

    /**
     * opposite of is fraction.
     *
     * @return true - not fraction/false - fraction.
     */
    public boolean isPositivePower() {
        return isPositivePower;
    }

    /**
     * invert expression.
     */
    public void negate() {
        isPositivePower = !isPositivePower;
    }


    /**
     * return expression combined with power's sign.
     *
     * @return signed expression.
     */
    public Expression toExpression() {
        if (isPositivePower()) {
            return getExpression();
        } else {
            return new Div(1, getExpression());
        }
    }

    /**
     * combine two expressions.
     *
     * @param other expression.
     * @return combined (binary) expression.
     */
    public BaseExpression toBaseExpression(NestedExpression other) {
        try {
            PolynomialExpression other2 = (PolynomialExpression) other;

            Expression e1 = getExpression();
            Expression e2 = other.getExpression();

            if (isPositivePower()) {
                if (other2.isPositivePower()) {
                    return new Mult(e1, e2);
                } else {
                    return new Div(e1, e2);
                }
            } else {
                if (other2.isPositivePower()) {
                    return new Div(e2, e1);
                } else {
                    return new Div(1, new Mult(e1, e2));
                }
            }
        } catch (Exception e) {
            return null;
        }
    }
}
