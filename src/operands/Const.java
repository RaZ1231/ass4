package operands;

import structure.Expression;

import java.util.List;
import java.util.Map;

/**
 * @author Raziel Solomon
 * @since 13-Apr-16.
 */
public class Const implements Expression {
    private String name;
    private double value;

    public Const(String name, double value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return value;
    }

    @Override
    public double evaluate() throws Exception {
        return evaluate(null);
    }

    @Override
    public List<String> getVariables() {
        return null;
    }


    @Override
    public Expression assign(String var, Expression expression) {
        return this;
    }
}
