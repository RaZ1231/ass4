package nestedsimplification;

import abstracts.NestedExpression;
import abstracts.NestedSequence;
import binary.Plus;
import interfaces.Expression;
import interfaces.ExtendedExpression;
import operands.Num;

/**
 * List of nested expression connected by plus/minus/neg
 *
 * @author Raziel Solomon
 * @since 07-May-16.
 */
public class LinearSequence extends NestedSequence {
    /**
     * convenience method to return a list with a single item
     *
     * @param expression item
     * @return list
     */
    public static LinearSequence singletonList(LinearExpression expression) {
        LinearSequence list = new LinearSequence();

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
        return new LinearExpression(expression);
    }

    /**
     * returns a plus - neutral number
     *
     * @return new num
     */
    @Override
    public NestedExpression getNeutralNum() {
        return new LinearExpression(new Num(0));
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
            return new Plus(get(0).toExpression(), subList(1, getExpressions().size()).toExpression());
        }
    }
}
