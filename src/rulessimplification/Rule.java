package rulessimplification;

import interfaces.Expression;

import java.util.Map;

/**
 * Representation of rule.
 *
 * @author Raziel Solomon
 * @since 16-Apr-16.
 */
public class Rule {
    private Expression complicated;
    private Expression simple;

    /**
     * constructor.
     *
     * @param complicated complicated form.
     * @param simple      simple form.
     */
    public Rule(Expression complicated, Expression simple) {
        this.complicated = complicated;
        this.simple = simple;
    }

    /**
     * constructor.
     *
     * @param complicated complicated form.
     * @param simple      simple form.
     */
    public Rule(String complicated, String simple) {
        Parser ste = new Parser();

        this.complicated = ste.toExpression(complicated);
        this.simple = ste.toExpression(simple);
    }

    /**
     * get complicated.
     *
     * @return complicated form.
     */
    public Expression getComplicated() {
        return complicated;
    }

    @Override
    public String toString() {
        return complicated + " -> " + simple;
    }

    /**
     * replace tags in simple expression with map tags.
     *
     * @param tags simple form of rule.
     * @return simple expression.
     */

    public Expression applyByMap(Map<String, Expression> tags) {
        Expression simpleExpression = getSimple();

        for (Map.Entry<String, Expression> tag : tags.entrySet()) {
            simpleExpression = simpleExpression.assign(tag.getKey(), tag.getValue());
        }

        return simpleExpression;
    }

    /**
     * get simple.
     *
     * @return simple form.
     */
    public Expression getSimple() {
        return simple;
    }
}
