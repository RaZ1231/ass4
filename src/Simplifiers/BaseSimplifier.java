package Simplifiers;

import Simplification.Rule;
import structure.Expression;

import java.util.LinkedList;
import java.util.List;

/**
 *
 *
 * @author Raziel Solomon
 * @since 17-Apr-16.
 */
public abstract class BaseSimplifier {
    private List<Rule> rules;

    protected void init() {
        rules = new LinkedList<Rule>();
    }

    protected void add(Expression complicated, Expression simple) {
        rules.add(new Rule(complicated, simple));
    }

    public Expression simplify(Expression expression) {
        for (Rule rule : rules) {
            if (expression.equals(rule.getComplicated())) {
                return rule.getSimple();
            }
        }

        return expression;
    }
}
