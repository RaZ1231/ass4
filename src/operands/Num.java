package operands;

import structure.Expression;

import java.util.List;
import java.util.Map;

/**
 * @author Raziel Solomon
 * @since 11-Apr-16.
 */
public class Num implements Expression {
    private double value;

    public Num(double num) {
        this.value = num;
    }

    @Override
    public String toString() {
        return "" + value;
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
