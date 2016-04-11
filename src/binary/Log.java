package binary;

import structure.BinaryExpression;
import structure.Expression;

import java.util.Map;

/**
 * @author Raziel Solomon
 * @since 11-Apr-16.
 */
public class Log extends BinaryExpression {
    private Expression a;
    private Expression b;

    public Log(Expression a, Expression b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return Math.log(a.evaluate(assignment)) / Math.log(b.evaluate(assignment));
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Log(a.assign(var, expression), b.assign(var, expression));
    }

    @Override
    public String toString() {
        return "log(" + a + ", " + b + ")";
    }
}
