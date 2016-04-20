package Simplification;

import structure.Expression;

/**
 * @author Raziel Solomon
 * @since 16-Apr-16.
 */
public class Rule {
    private Expression complicated;
    private Expression simple;

    public Rule(Expression complicated, Expression simple) {
        this.complicated = complicated;
        this.simple = simple;
    }

    public Rule(String complicated, String simple) {
        StringToExpression ste = new StringToExpression();

        this.complicated = ste.toExpression(complicated);
        this.simple = ste.toExpression(simple);
    }

    public Expression getComplicated() {
        return complicated;
    }

    public Expression getSimple() {
        return simple;
    }

}
