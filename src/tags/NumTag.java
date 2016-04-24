package tags;

import operands.Var;
import structure.Expression;

/**
 * Class represents "numeric expression expected" in rules.
 *
 * @author Raziel Solomon
 * @since 19-Apr-16.
 */
public class NumTag extends Var implements Expression, Tag {
    /**
     * constructor.
     *
     * @param value tag.
     */
    public NumTag(String value) {
        super(value);
    }

    /**
     * returns string representation.
     *
     * @return string.
     */
    @Override
    public String toString() {
        return "@" + getValue();
    }

    /**
     * check if expression match tag type.
     *
     * @param expression an expression.
     * @return true if match, false otherwise.
     */
    @Override
    public boolean check(Expression expression) {
        try {
            expression.evaluate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
