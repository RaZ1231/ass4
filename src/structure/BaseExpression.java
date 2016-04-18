package structure;

/**
 * @author Raziel Solomon
 * @since 11-Apr-16.
 */

import operands.Num;

import java.util.List;
import java.util.Map;

/**
 *
 */
public abstract class BaseExpression {
    /**
     * returns a list of the variables in the expression.
     *
     * @return list containing the variables in the expression.
     */
    public List<String> getVariables() {
        return getVariablesSons();
    }

    /**
     * get variables of sons
     *
     * @return list containing the variables in the expression
     */
    protected abstract List<String> getVariablesSons();

    /**
     * returns a new expression in which all occurrences of the variable
     * var are replaced with the provided expression (Does not modify the
     * current expression).
     *
     * @param var        variable to replace.
     * @param expression expression to put instead.
     * @return modified expression.
     */
    public Expression assign(String var, Expression expression) {
        return assignSons(var, expression);
    }

    /**
     * Return assignment of sons
     *
     * @param var        variable to replace.
     * @param expression expression to put instead.
     * @return modified sons
     */
    protected abstract Expression assignSons(String var, Expression expression);

    /**
     * Returned a simplified version of the current expression.
     *
     * @return simplified expression
     */
    public Expression simplify() {
        try {
            return new Num(evaluate());
        } catch (Exception e) {
            Expression simple = simplifySons().simple();
            try {
                return new Num(simple.evaluate());
            } catch (Exception e1) {
                return simple;
            }
        }
    }

    /**
     * a convenience method. Similar to `evaluate(assignment)` method above,
     * but uses an empty assignment.
     *
     * @return equation solution
     * @throws Exception
     */
    public double evaluate() throws Exception {
        return evaluate(null);
    }

    /**
     * Simplify me
     *
     * @return simplified expression
     */
    protected abstract Expression simple();

    /**
     * Simplifies sons of base expression
     *
     * @return base expression with simplified sons
     */
    protected abstract BaseExpression simplifySons();

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
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return evaluateSons(assignment);
    }

    /**
     * Evaluates sons of expression
     *
     * @param assignment variables' values to assign
     * @return equation solution for the assignment
     * @throws Exception
     */
    protected abstract double evaluateSons(Map<String, Double> assignment) throws Exception;

    /**
     * returns the expression tree resulting from differentiating
     * the current expression relative to variable `var`.
     *
     * @param var a string variable.
     * @return the differentiate of the expression.
     */
    public Expression differentiate(String var) {
        return derivative(var);
    }

    /**
     * returns the derivative of an expression.
     *
     * @param var a string variable.
     * @return the derivative of an expression.
     */
    public abstract Expression derivative(String var);

}
