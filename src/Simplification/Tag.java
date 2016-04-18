package Simplification;

import operands.Var;
import structure.Expression;

/**
 * @author Raziel Solomon
 * @since 18-Apr-16.
 */
public class Tag extends Var implements Expression {
    public Tag(String value) {
        super(value);
    }

    @Override
    public String toString() {
        return "#" + getValue();
    }
}
