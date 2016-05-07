package nestedsimplification;

import abstracts.NestedExpression;
import abstracts.NestedSequence;
import binary.Mult;
import interfaces.Expression;
import interfaces.ExtendedExpression;
import operands.Num;

/**
 * List of nested expression connected by mult/div
 *
 * @author Raziel Solomon
 * @since 07-May-16.
 */
public class PolynomialSequence extends NestedSequence {
    /**
     * convenience method to return a list with a single item
     *
     * @param expression item
     * @return list
     */
    public static PolynomialSequence singletonList(PolynomialExpression expression) {
        PolynomialSequence list = new PolynomialSequence();

        list.add(expression);

        return list;
    }

    /**
     * create new list
     *
     * @param expression for constructor
     * @return new list
     */
    @Override
    public NestedExpression create(ExtendedExpression expression) {
        return new PolynomialExpression(expression);
    }

    /**
     * returns a plus - neutral number
     *
     * @return new num
     */
    @Override
    public NestedExpression getNeutralNum() {
        return new PolynomialExpression(new Num(1));
    }

    /**
     * convert list to expression
     *
     * @return expression
     */
    @Override
    public Expression toExpression() {
        if (size() == 0) {
            return null;
        } else if (size() == 1) {
            return get(0).toExpression();
        } else {
            return new Mult(get(0).toExpression(), subList(1, getExpressions().size()).toExpression());
        }
    }
}