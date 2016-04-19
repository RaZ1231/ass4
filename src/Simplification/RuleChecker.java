package Simplification;

import operands.Num;
import structure.BinaryExpression;
import structure.Expression;
import structure.UnaryExpression;

import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

/**
 * @author Raziel Solomon
 * @since 18-Apr-16.
 */
public class RuleChecker {
    private Map<String, Expression> tags;

    public RuleChecker() {
        tags = new TreeMap<>();
    }

    private void put(String tag, Expression expression) {
        tags.put(tag, expression);
    }

    private Expression get(String tag) {
        return containsKey(tag) ? tags.get(tag) : null;
    }

    private boolean containsKey(String tag) {
        return tags.containsKey(tag);
    }

    public void init() {
        tags.clear();
    }

    protected boolean check(Expression complicated, Expression expression) {
        return compare(complicated, expression);
    }

    private boolean compare(Expression rule, Expression expression) {
        if (rule instanceof NumTag) {
            NumTag tag = (NumTag) rule;

            try {
                put(tag.getValue(), new Num(expression.evaluate()));
                return true;
            } catch (Exception e) {
                return false;
            }
        } else if (rule instanceof Tag) {
            Tag tag = (Tag) rule;

            if (containsKey(tag.getValue())) {
                return expression.equals(get(tag.getValue()));
            } else {
                put(tag.getValue(), expression);
                return true;
            }
        } else if (rule.getClass() == expression.getClass()) {
            if (expression instanceof UnaryExpression) {
                UnaryExpression unaryRule = (UnaryExpression) rule;
                UnaryExpression unaryExpression = (UnaryExpression) expression;

                return compare(unaryRule.getA(), unaryExpression.getA());
            } else if (expression instanceof BinaryExpression) {
                BinaryExpression binaryRule = (BinaryExpression) rule;
                BinaryExpression binaryExpression = (BinaryExpression) expression;

                return compare(binaryRule.getA(), binaryExpression.getA())
                        && compare(binaryRule.getB(), binaryExpression.getB());
            } else {
                return rule.equals(expression);
            }
        } else {
            return false;
        }
    }

    private Expression applyRule(Expression simple) {
        for (Entry<String, Expression> tag : tags.entrySet()) {
            simple = simple.assign(tag.getKey(), tag.getValue());
        }

        return simple;
    }

    public Expression apply(Rule rule, Expression expression) throws Exception {
        init();

        if (check(rule.getComplicated(), expression)) {
            return applyRule(rule.getSimple());
        } else {
            throw new Exception("Expression does not meet rule criteria.");
        }
    }
}
