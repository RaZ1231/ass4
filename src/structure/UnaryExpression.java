package structure;

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
     * Evaluates sons of expression
     *
     * @param assignment variables' values to assign
     * @return equation solution for the assignment
     * @throws Exception
     */
    @Override
    protected double evaluateSons(Map<String, Double> assignment) throws Exception {
        return operate(getA().evaluate(assignment));
    }

    /**
     * an abstract operation function.
     *
     * @param a a parameter.
     * @return operation result.
     */
    protected abstract double operate(double a);

    /**
     * Get left son
     *
     * @return left son
     */
    public Expression getA() {
        return a;
    }

    /**
     * get variables of sons
     *
     * @return list containing the variables in the expression
     */
    @Override
    protected List<String> getVariablesSons() {
        List<String> vars = new LinkedList<String>();

        vars.addAll(getA().getVariables());

        return vars;
    }

    /**
     * Return assignment of sons
     *
     * @param var        variable to replace.
     * @param expression expression to put instead.
     * @return modified sons
     */
    @Override
    protected Expression assignSons(String var, Expression expression) {
        return create(getA().assign(var, expression));
    }

    /**
     * returns new expression by type.
     *
     * @param a an expression.
     * @return a new expression by type.
     */
    protected abstract Expression create(Expression a);

    /**
     * Simplifies sons of base expression
     *
     * @return base expression with simplified sons
     */
    @Override
    protected BaseExpression simplifySons() {
        return (BaseExpression) create(getA().simplify());
    }

    /**
     * returns the derivative of an expression.
     *
     * @param var a string variable.
     * @return the derivative of an expression.
     */
    public abstract Expression derivative(String var);
}
