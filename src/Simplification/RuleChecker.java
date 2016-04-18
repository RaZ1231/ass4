package Simplification;

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

    public boolean check(Rule rule, Expression expression) {
        return compare(rule.getComplicated(), expression);
    }

    private boolean compare(Expression rule, Expression expression) {
        if (rule instanceof Tag) {
            Tag tag = (Tag) rule;

            if (containsKey(tag.getValue())) {
                return compare(get(tag.getValue()), expression);
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

    public Expression applyRule(Rule rule) {
        Expression simple = rule.getSimple();

        //return replaceTag(simple);

        for (Entry<String, Expression> tag : tags.entrySet()) {
            System.out.print(simple);
            simple = simple.assign(tag.getKey(), tag.getValue());
            System.out.print(" --[#" + tag.getKey() + "->" + tag.getValue() + "]--> ");
            System.out.println(simple);
        }

        return simple;
    }

    /*
    private Expression replaceTag(Expression expression) {
        if (expression instanceof Tag) {
            return get(((Tag) expression).getValue());
        } else if (expression instanceof UnaryExpression) {
            ((UnaryExpression) expression).setA() = replaceTag(((UnaryExpression) expression).getA());
            return expression;
        } else if (expression instanceof BinaryExpression) {
            replaceTag(((BinaryExpression) expression).getA());
            replaceTag(((BinaryExpression) expression).getB());
        }
    }
    */
}
