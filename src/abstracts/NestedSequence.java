package abstracts;

import binary.Div;
import interfaces.Expression;
import interfaces.ExtendedExpression;
import operands.Num;
import unary.Neg;

import java.util.ArrayList;
import java.util.List;

/**
 * Class contains shared code between collections of nested expressions.
 *
 * @author Raziel Solomon
 * @since 07-May-16.
 */
public abstract class NestedSequence {
    private List<NestedExpression> expressions;

    /**
     * constructor.
     */
    public NestedSequence() {
        expressions = new ArrayList<>();
    }

    /**
     * add expression.
     *
     * @param expression new expression.
     */
    public void add(NestedExpression expression) {
        expressions.add(expression);
    }

    /**
     * add expressions list.
     *
     * @param expression expressions list.
     */
    public void addAll(NestedSequence expression) {
        expressions.addAll(expression.getExpressions());
    }

    /**
     * get expressions list.
     *
     * @return expressions list.
     */
    public List<NestedExpression> getExpressions() {
        return expressions;
    }

    /**
     * return a subList of expressions.
     *
     * @param from start.
     * @param to   end.
     * @return subList.
     */
    public NestedSequence subList(int from, int to) {
        expressions = getExpressions().subList(from, to);

        return this;
    }

    /**
     * negate every expression in list.
     *
     * @return negated list.
     */
    public NestedSequence negate() {
        NestedSequence nestedSequence = this;

        for (NestedExpression expression : nestedSequence.getExpressions()) {
            expression.negate();
        }

        return nestedSequence;
    }

    /**
     * try to combine expression by rules and evaluations.
     *
     * @return simplified expression.
     * @throws Exception an exception.
     */
    public Expression simplify() throws Exception {
        boolean isSimplified = false;
        BaseExpression baseExpression;
        Expression expression;

        for (int i = 0; i < size() - 1; i++) {
            for (int j = i + 1; j < size(); j++) {
                try {
                    try {
                        expression = new Num(get(i).toBaseExpression(get(j)).evaluate());
                    } catch (Exception e) {
                        baseExpression = get(i).toBaseExpression(get(j));

                        if (baseExpression instanceof Neg) {
                            expression = ((Neg) baseExpression).getA();
                        } else if (baseExpression instanceof Div && ((Div) baseExpression).getA().equals(new Num(1))) {
                            expression = ((Div) baseExpression).getB();
                        } else {
                            expression = (Expression) baseExpression;
                        }

                        expression = ((BaseExpression) expression).rulesSimplification();

                        if (baseExpression instanceof Neg) {
                            expression = new Neg(expression);
                        } else if (baseExpression instanceof Div && ((Div) baseExpression).getA().equals(new Num(1))) {
                            expression = new Div(1, expression);
                        }
                    }


                    set(j, create((ExtendedExpression) expression));
                    set(i, getNeutralNum());
                    isSimplified = true;

                } catch (Exception ignored) {
                    continue;
                }
            }
        }

        if (isSimplified) {
            return toExpression();
        } else {
            throw new Exception("Expression can not be simplified by Nested Simplification");
        }
    }

    /**
     * get size.
     *
     * @return size.
     */
    public int size() {
        return getExpressions().size();
    }

    /**
     * get ith expression.
     *
     * @param i place.
     * @return i'th expression.
     */
    public NestedExpression get(int i) {
        return getExpressions().get(i);
    }

    /**
     * set ith expression.
     *
     * @param i          place.
     * @param expression new expression.
     */
    public void set(int i, NestedExpression expression) {
        expressions.set(i, expression);
    }

    /**
     * constructor.
     *
     * @param expression for constructor.
     * @return new nested expression.
     */
    public abstract NestedExpression create(ExtendedExpression expression);

    /**
     * returns neutral number.
     *
     * @return neutral number.
     */
    public abstract NestedExpression getNeutralNum();

    /**
     * convert list to expression.
     *
     * @return expression.
     */
    public abstract Expression toExpression();
}
