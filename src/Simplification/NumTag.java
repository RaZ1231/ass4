package Simplification;

import structure.Expression;

/**
 * Class represents "numeric expression expected" in rules.
 *
 * @author Raziel Solomon
 * @since 19-Apr-16.
 */
public class NumTag extends Tag implements Expression {
    /**
     * constructor
     *
     * @param value tag
     */
    public NumTag(String value) {
        super(value);
    }

    /**
     * returns string representation
     *
     * @return string
     */
    @Override
    public String toString() {
        return "@" + getValue();
    }
}
