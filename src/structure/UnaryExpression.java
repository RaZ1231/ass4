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
        return operate(a.evaluate(assignment));
    }

    /**
     * an operation function.
     *
     * @param a a parameter.
     * @return operation result.
     */
    public abstract double operate(double a);

    /**
     * A convenience method. Similar to `evaluate(assignment)` method above,
     * but uses an empty assignment.
     *
     * @return equation solution
     * @throws Exception
     */
    @Override
    public double evaluate() throws Exception {//////////what is it??????
        return  0;
    }

    /**
     * returns a list of the variables in the expression.
     *
     * @return list containing the variables in the expression.
     */
    @Override
    public List<String> getVariables() {
        List<String> vars = new LinkedList<String>();

        vars.addAll(a.getVariables());
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
        return create(a.assign(var, expression));
    }

    /**
     * returns new expression by type.
     *
     * @param a an expression.
     * @return a new expression by type.
     */
    public abstract Expression create(Expression a);

    /**
     * returns a nice string representation of the expression.
     *
     * @return string representation.
     */
    @Override
    public String toString() {
        return toString(a);
    }

    /**
     * returns a nice string representation of the expression.
     *
     * @param a an expression.
     * @return string representation.
     */
    public abstract String toString(Expression a);

}
