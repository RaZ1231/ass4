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

    /**
     * constructor.
     *
     * @param a an expression.
     * @param b another expression.
     */
    public BinaryExpression(Expression a, Expression b) {
        this.a = a;
        this.b = b;
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
        return operate(a.evaluate(assignment), b.evaluate(assignment));
    }

    /**
     * an abstract operation function.
     *
     * @param a a parameter.
     * @param b another parameter.
     * @return operation result.
     */
    public abstract double operate(double a, double b);

    /**
     * returns a list of the variables in the expression.
     *
     * @return list containing the variables in the expression.
     */
    @Override
    public List<String> getVariables() {
        List<String> vars = new LinkedList<String>();

        vars.addAll(a.getVariables());
        vars.addAll(b.getVariables());

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
        return create(a.assign(var, expression), b.assign(var, expression));
    }

    /**
     * returns new expression by type.
     *
     * @param a an expression.
     * @param b another expression.
     * @return a new expression by type.
     */
    public abstract Expression create(Expression a, Expression b);

    /**
     * returns a nice string representation of the expression.
     *
     * @return string representation.
     */
    @Override
    public String toString() {
        return toString(a, b);
    }

    /**
     * returns a nice string representation of the expression.
     *
     * @param a an expression.
     * @param b another expression.
     * @return string representation.
     */
    public abstract String toString(Expression a, Expression b);
}
