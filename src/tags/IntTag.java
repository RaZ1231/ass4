package tags;

import operands.Var;
import structure.Expression;

/**
 * @author Raziel Solomon
 * @since 21-Apr-16.
 */
public class IntTag extends Var implements Expression, Tag {
    /**
     * constructor
     *
     * @param value tag
     */
    public IntTag(String value) {
        super(value);
    }

    /**
     * returns string representation
     *
     * @return string
     */
    @Override
    public String toString() {
        return "%" + getValue();
    }

    @Override
    public boolean check(Expression expression) {
        try {
            return (expression.evaluate() % 1 == 0);
        } catch (Exception e) {
            return false;
        }
    }
}
