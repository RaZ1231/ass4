package Simplification;

import structure.Expression;

/**
 * @author Raziel Solomon
 * @since 19-Apr-16.
 */
public class NumTag extends Tag implements Expression {
    public NumTag(String value) {
        super(value);
    }

    @Override
    public String toString() {
        return "@" + getValue();
    }
}
