package structure;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author Raziel Solomon
 * @since 11-Apr-16.
 */
public abstract class BinaryExpression extends BaseExpression {
    private Expression a;
    private Expression b;

    public BinaryExpression(Expression a, Expression b) {
        this.a = a;
        this.b = b;
    }

    /**
     * @param assignment variables' values to assign
     * @return
     * @throws Exception
     * @inheritDoc
     */
    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return operate(a.evaluate(assignment), b.evaluate(assignment));
    }

    public abstract double operate(double a, double b);

    @Override
    public List<String> getVariables() {
        List<String> vars = new LinkedList<String>();

        vars.addAll(a.getVariables());
        vars.addAll(b.getVariables());

        return vars;
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return create(a.assign(var, expression), b.assign(var, expression));
    }

    public abstract Expression create(Expression a, Expression b);

    @Override
    public String toString() {
        return toString(a, b);
    }

    public abstract String toString(Expression a, Expression b);
}
