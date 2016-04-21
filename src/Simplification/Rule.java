package simplification;

import structure.Expression;

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

    /**
     * get simple.
     *
     * @return simple form.
     */
    public Expression getSimple() {
        return simple;
    }
}
