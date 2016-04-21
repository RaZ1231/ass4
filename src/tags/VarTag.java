package tags;

import operands.Var;
import structure.Expression;

/**
 * Class represents "expression containing vars expected" in rules.
 *
 * @author Raziel Solomon
 * @since 19-Apr-21.
 */
public class VarTag extends Var implements Expression, Tag {
    /**
     * constructor
     *
     * @param value tag
     */
    public VarTag(String value) {
        super(value);
    }

    /**
     * returns string representation
     *
     * @return string
     */
    @Override
    public String toString() {
        return "&" + getValue();
    }

    @Override
    public boolean check(Expression expression) {
        try {
            expression.evaluate();
            return false;
        } catch (Exception e) {
            return true;
        }
    }
}
