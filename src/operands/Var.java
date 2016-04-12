package operands;

import structure.Expression;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author Raziel Solomon
 * @since 11-Apr-16.
 */
public class Var implements Expression {
    private String value;

    public Var(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        if (assignment.containsKey(value)) {
            return assignment.get(value);
        } else {
            throw new Exception("Assignment doesn't contain " + value);
        }
    }

    @Override
    public double evaluate() throws Exception {
        return evaluate(null);
    }

    @Override
    public List<String> getVariables() {
        return Collections.singletonList(value);
    }


    @Override
    public Expression assign(String var, Expression expression) {
        if (value == var) {
            return expression;
        } else {
            return this;
        }
    }
}
