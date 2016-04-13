package structure;

import operands.Num;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author Elisheva Broyer.
 * @since 13/04/2016.
 */
public abstract class UnaryExpression extends BaseExpression {
    private Expression a;

    /**
     * constructor.
     *
     * @param a an expression.
     */
    public UnaryExpression(Expression a) {
        this.a = a;
    }

    /**
     * Evaluate the expression using the variable values provided
     * in the assignment, and return the result.  If the expression
     * contains a variable which is not in the assignment, an exception
     * is thrown.
     *
     * @param assignment variables' values to assign
     * @return equation solution for the assignment
     * @throws Exception
     */
    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return operate(getA().evaluate(assignment));
    }

    /**
     * an operation function.
     *
     * @param a a parameter.
     * @return operation result.
     */
    protected abstract double operate(double a);

    public Expression getA() {
        return a;
    }

    /**
     * returns a list of the variables in the expression.
     *
     * @return list containing the variables in the expression.
     */
    @Override
    public List<String> getVariables() {
        List<String> vars = new LinkedList<String>();

        vars.addAll(getA().getVariables());
        return vars;
    }

    /**
     * returns a new expression in which all occurrences of the variable
     * var are replaced with the provided expression (Does not modify the
     * current expression).
     *
     * @param var        variable to replace.
     * @param expression expression to put instead.
     * @return modified expression.
     */
    @Override
    public Expression assign(String var, Expression expression) {
        return create(getA().assign(var, expression));
    }

    /**
     * returns new expression by type.
     *
     * @param a an expression.
     * @return a new expression by type.
     */
    protected abstract UnaryExpression create(Expression a);

    /**
     * Returned a simplified version of the current expression.
     *
     * @return simplified expression
     */
    @Override
    public Expression simplify() {
        Expression simpleA = getA().simplify();
        UnaryExpression simpleExpression = create(simpleA);
        Expression newExpression = null;

        if (simpleExpression.getA() instanceof Num) {
            try {
                newExpression = new Num(simpleExpression.evaluate());
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            newExpression = simpleExpression.simple(simpleExpression.getA());
        }

        return newExpression;
    }

    /**
     * Returned a simplified version of the current expression.
     *
     * @param a left expression
     * @return simplified expression
     */
    protected abstract Expression simple(Expression a);
}
