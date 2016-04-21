package structure;

import operands.Num;
import simplification.RuleChecker;

import java.util.Map;

/**
 * base expression class representation.
 *
 * @author Raziel Solomon
 * @since 11-Apr-16.
 */
public abstract class BaseExpression {
    /**
     * Returned a simplified version of the current expression.
     *
     * @return simplified expression.
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
     * @return equation solution.
     * @throws Exception
     */
    public double evaluate() throws Exception {
        return evaluate(null);
    }

    /**
     * Simplify me.
     *
     * @return simplified expression.
     */
    public Expression simple() {
        RuleChecker checker = new RuleChecker();

        return checker.applyRules((Expression) this);
    }

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
     * @param assignment variables' values to assign.
     * @return equation solution for the assignment.
     * @throws Exception
     */
    public abstract double evaluate(Map<String, Double> assignment) throws Exception;
}
