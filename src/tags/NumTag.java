package tags;

import interfaces.Expression;
import interfaces.ExtendedExpression;
import interfaces.Tag;
import operands.Num;
import operands.Var;

import java.util.Collections;
import java.util.Map;

/**
 * Class represents "numeric expression expected" in rules.
 *
 * @author Raziel Solomon
 * @since 19-Apr-16.
 */
public class NumTag extends Var implements ExtendedExpression, Tag {
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
     * map expression by rule for rulessimplification
     *
     * @param rule rule to map by
     * @return map of rule's tags
     * @throws Exception expression is not compatible
     */
    @Override
    public Map<String, Expression> mapByRule(Expression rule) throws Exception {
        return Collections.singletonMap(getValue(), (Expression) new Num(rule.evaluate()));
    }
}
