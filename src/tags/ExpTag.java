package tags;

import operands.Var;
import structure.Expression;

/**
 * Class represents "all expression expected" in rules.
 *
 * @author Raziel Solomon
 * @since 18-Apr-16.
 */
public class ExpTag extends Var implements Expression, Tag {
    /**
     * constructor.
     *
     * @param value tag.
     */
    public ExpTag(String value) {
        super(value);
    }

    /**
     * returns string representation.
     *
     * @return string.
     */
    @Override
    public String toString() {
        return "#" + getValue();
    }

    /**
     * check if expression match tag type.
     *
     * @param e an expression.
     * @return true if match, false otherwise.
     */
    @Override
    public boolean check(Expression e) {
        return true;
    }
}
