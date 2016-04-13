package structure;

import java.util.List;
import java.util.Map;

/**
 * Algebraic Expression interface.
 *
 * @author Raziel Solomon
 * @since 10-Apr-16.
 */
public interface Expression {

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
    double evaluate(Map<String, Double> assignment) throws Exception;

    /**
     * a convenience method. Similar to `evaluate(assignment)` method above,
     * but uses an empty assignment.
     *
     * @return equation solution
     * @throws Exception
     */
    double evaluate() throws Exception;

    /**
     * returns a list of the variables in the expression.
     *
     * @return list containing the variables in the expression.
     */
    List<String> getVariables();

    /**
     * returns a nice string representation of the expression.
     *
     * @return string representation
     */
    String toString();

    /**
     * returns a new expression in which all occurrences of the variable
     * var are replaced with the provided expression (Does not modify the
     * current expression).
     *
     * @param var        variable to replace
     * @param expression expression to put instead
     * @return modified expression
     */
    Expression assign(String var, Expression expression);
}
