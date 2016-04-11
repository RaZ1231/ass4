package binary;

import structure.BinaryExpression;
import structure.Expression;

import java.util.Map;

/**
 * @author Raziel Solomon
 * @since 11-Apr-16.
 */
public class Div extends BinaryExpression {
    private Expression a;
    private Expression b;

    public Div(Expression a, Expression b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return a.evaluate(assignment) / b.evaluate(assignment);
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Div(a.assign(var, expression), b.assign(var, expression));
    }

    @Override
    public String toString() {
        return "(" + a + " / " + b + ")";
    }
}
