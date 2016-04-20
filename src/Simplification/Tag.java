package Simplification;

import operands.Var;
import structure.Expression;

/**
 * Class represents "all expression expected" in rules.
 *
 * @author Raziel Solomon
 * @since 18-Apr-16.
 */
public class Tag extends Var implements Expression {
    /**
     * constructor
     *
     * @param value tag
     */
    public Tag(String value) {
        super(value);
    }

    /**
     * returns string representation
     *
     * @return string
     */
    @Override
    public String toString() {
        return "#" + getValue();
    }
}
