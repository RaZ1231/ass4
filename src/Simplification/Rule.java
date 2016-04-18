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

    public Expression getComplicated() {
        return complicated;
    }

    public Expression getSimple() {
        return simple;
    }

}
