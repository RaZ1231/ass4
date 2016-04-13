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

    /**
     * constructor.
     *
     * @param name a string.
     * @param value a number.
     */
    public Const(String name, double value) {
        this.name = name;
        this.value = value;
    }

    /**
     * returns a nice string representation of the expression.
     *
     * @return string representation.
     */
    @Override
    public String toString() {
        return name;
    }

    /**
     * evaluate the expression using the variable values provided
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
        return value;
    }

    /**
     * a convenience method. Similar to `evaluate(assignment)` method above,
     * but uses an empty assignment.
     *
     * @return equation solution
     * @throws Exception
     */
    @Override
    public double evaluate() throws Exception {
        return evaluate(null);
    }

    /**
     * returns a list of the variables in the expression.
     *
     * @return list containing the variables in the expression.
     */
    @Override
    public List<String> getVariables() {
        return null;
    }

    /**
     * returns a new expression in which all occurrences of the variable
     * var are replaced with the provided expression (Does not modify the
     * current expression).
     *
     * @param var        variable to replace
     * @param expression expression to put instead
     * @return modified expression
     */
    @Override
    public Expression assign(String var, Expression expression) {
        return this;
    }
}
