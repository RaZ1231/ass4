package tags;

import interfaces.Expression;
import interfaces.ExtendedExpression;
import interfaces.Tag;
import java.util.Collections;
import java.util.Map;
import operands.Var;

/**
 * Class represents "all expression expected" in rules.
 *
 * @author Raziel Solomon
 * @since 18-Apr-16.
 */
public class ExpTag extends Var implements ExtendedExpression, Tag {
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
     * map expression by rule for rulessimplification.
     *
     * @param rule rule to map by.
     * @return map of rule's tags.
     * @throws Exception expression is not compatible.
     */
    @Override
    public Map<String, Expression> mapByRule(Expression rule) throws Exception {
        return Collections.singletonMap(getValue(), rule);
    }
}
