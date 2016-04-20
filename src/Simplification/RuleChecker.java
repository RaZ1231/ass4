package Simplification;

import operands.Num;
import structure.BinaryExpression;
import structure.Expression;
import structure.UnaryExpression;

import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

/**
 * In charge of checking and applying rules to expressions.
 *
 * @author Raziel Solomon
 * @since 18-Apr-16.
 */
public class RuleChecker {
    private Map<String, Expression> tags;

    /**
     * constructor.
     */
    public RuleChecker() {
        tags = new TreeMap<>();
    }

    /**
     * put tag in map.
     *
     * @param tag        tag to add.
     * @param expression expression to add.
     */
    private void put(String tag, Expression expression) {
        tags.put(tag, expression);
    }

    /**
     * get expression by tag.
     *
     * @param tag the tag to get by.
     * @return expression.
     */
    private Expression get(String tag) {
        return containsKey(tag) ? tags.get(tag) : null;
    }

    /**
     * check if contain tag.
     *
     * @param tag tag to check.
     * @return true/false.
     */
    private boolean containsKey(String tag) {
        return tags.containsKey(tag);
    }

    /**
     * initialize  map.
     */
    public void init() {
        tags.clear();
    }

    /**
     * checks if expression meets criteria.
     *
     * @param complicated compare to.
     * @param expression  expression to check.
     * @return true/false.
     */
    protected boolean check(Expression complicated, Expression expression) {
        return compare(complicated, expression);
    }

    /**
     * compare two expressions.
     *
     * @param rule       first.
     * @param expression second.
     * @return true/false.
     */
    private boolean compare(Expression rule, Expression expression) {
        if (rule instanceof NumTag) { //NumTag
            NumTag tag = (NumTag) rule;

            try { //check if numeric
                put(tag.getValue(), new Num(expression.evaluate()));
                return true;
            } catch (Exception e) {
                return false;
            }
        } else if (rule instanceof Tag) { //Tag
            Tag tag = (Tag) rule;

            if (containsKey(tag.getValue())) { //check if exists
                return expression.equals(get(tag.getValue()));
            } else {
                put(tag.getValue(), expression);
                return true;
            }
        } else if (rule.getClass() == expression.getClass()) {
            if (expression instanceof UnaryExpression) { //unary
                UnaryExpression unaryRule = (UnaryExpression) rule;
                UnaryExpression unaryExpression = (UnaryExpression) expression;

                return compare(unaryRule.getA(), unaryExpression.getA());
            } else if (expression instanceof BinaryExpression) { //binary
                BinaryExpression binaryRule = (BinaryExpression) rule;
                BinaryExpression binaryExpression = (BinaryExpression) expression;

                return compare(binaryRule.getA(), binaryExpression.getA())
                        && compare(binaryRule.getB(), binaryExpression.getB());
            } else { //var
                return rule.equals(expression);
            }
        } else {
            return false;
        }
    }

    /**
     * replace tags in simple with expression's.
     *
     * @param simple simple form of rule.
     * @return simple expression.
     */
    private Expression applyRule(Expression simple) {
        for (Entry<String, Expression> tag : tags.entrySet()) {
            simple = simple.assign(tag.getKey(), tag.getValue());
        }

        return simple;
    }

    /**
     * checks and apply rule.
     *
     * @param rule       rule to apply.
     * @param expression expression to check.
     * @return simple expression.
     * @throws Exception
     */
    public Expression apply(Rule rule, Expression expression) throws Exception {
        init();

        if (check(rule.getComplicated(), expression)) {
            return applyRule(rule.getSimple());
        } else {
            throw new Exception("Expression does not meet rule criteria.");
        }
    }

    /**
     * checks expression against Rules list.
     *
     * @param expression expression to check.
     * @return simple expression.
     */
    public Expression applyRules(Expression expression) {
        int i = 0;

        while (i < Rules.RULES.size()) {
            try {
                expression = apply(Rules.RULES.get(i), expression);
                i = 0;
            } catch (Exception e) {
                i++;
            }
        }

        return expression;
    }
}
