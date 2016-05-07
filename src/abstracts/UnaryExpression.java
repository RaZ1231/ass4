package abstracts;

import interfaces.Expression;
import interfaces.ExtendedExpression;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * unary expression class representation.
 *
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
     * get variables of sons.
     *
     * @return list containing the variables in the expression.
     */
    public List<String> getVariables() {
        List<String> vars = new LinkedList<String>();

        vars.addAll(getA().getVariables());

        return vars;
    }

    /**
     * get left son.
     *
     * @return left son.
     */
    public Expression getA() {
        return a;
    }

    /**
     * return assignment of sons.
     *
     * @param var        variable to replace.
     * @param expression expression to put instead.
     * @return modified sons
     */
    public Expression assign(String var, Expression expression) {
        return create(getA().assign(var, expression));
    }

    /**
     * returns new expression by type.
     *
     * @param e an expression.
     * @return a new expression by type.
     */
    protected abstract Expression create(Expression e);

    /**
     * simplifies sons of base expression.
     *
     * @return base expression with simplified sons.
     */
    @Override
    protected Expression simplifySons() {
        return create(getA().simplify());
    }

    /**
     * maps son by rule
     *
     * @param rule said rule
     * @return mapped son
     * @throws Exception
     */
    @Override
    protected Map<String, Expression> mapSons(Expression rule) throws Exception {
        return ((ExtendedExpression) getA()).mapByRule(((UnaryExpression) rule).getA());
    }

    /**
     * evaluates sons of expression.
     *
     * @param assignment variables' values to assign.
     * @return equation solution for the assignment.
     * @throws Exception an exception.
     */
    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return operate(getA().evaluate(assignment));
    }

    /**
     * an abstract operation function.
     *
     * @param d a parameter.
     * @return operation result.
     */
    protected abstract double operate(double d);

    /**
     * returns true if equals, false otherwise.
     *
     * @param o an object.
     * @return true if equals, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        UnaryExpression that = (UnaryExpression) o;

        return a != null ? a.equals(that.a) : that.a == null;
    }
}
