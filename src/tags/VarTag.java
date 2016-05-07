package tags;

import interfaces.Expression;
import interfaces.ExtendedExpression;
import interfaces.Tag;
import operands.Var;

import java.util.Collections;
import java.util.Map;

/**
 * Class represents "expression containing vars expected" in rules.
 *
 * @author Raziel Solomon
 * @since 19-Apr-21.
 */
public class VarTag extends Var implements ExtendedExpression, Tag {
    /**
     * constructor.
     *
     * @param value tag.
     */
    public VarTag(String value) {
        super(value);
    }

    /**
     * returns string representation.
     *
     * @return string.
     */
    @Override
    public String toString() {
        return "&" + getValue();
    }

    /**
     * map expression by rule for rulessimplification.
     *
     * @param rule rule to map by.
     * @return map of rule's tags.
     * @throws Exception expression is not compatible.
     */
    @Override
    public Map<String, Expression> mapByRule(Expression rule) throws Exception {
        try {
            rule.evaluate();
        } catch (Exception e) {
            return Collections.singletonMap(getValue(), rule);
        }

        throw new Exception("Expression does not follow specified rule.");
    }
}
