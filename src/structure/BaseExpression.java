package structure;

import java.util.Map;
import operands.Num;
import simplification.RuleChecker;

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
        Expression simple = (Expression) this;
        Expression previous;

        do { // evaluate and simplify until the expression isn't changing.
            try {
                return new Num(simple.evaluate());
            } catch (Exception e) {
                previous = simple;

                try {
                    simple = ((BaseExpression) simple).simplifySons().simple();
                } catch (Exception e2) {
                    break;
                }
            }
        } while (!simple.equals(previous));

        return previous;
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
     * Simplifies sons of base expression.
     *
     * @return base expression with simplified sons
     */
    protected abstract BaseExpression simplifySons();

    /**
     * a convenience method. Similar to `evaluate(assignment)` method above,
     * but uses an empty assignment.
     *
     * @return equation solution.
     * @throws Exception an exception.
     */
    public double evaluate() throws Exception {
        return evaluate(null);
    }

    /**
     * Evaluate the expression using the variable values provided
     * in the assignment, and return the result.  If the expression
     * contains a variable which is not in the assignment, an exception
     * is thrown.
     *
     * @param assignment variables' values to assign.
     * @return equation solution for the assignment.
     * @throws Exception an exception.
     */
    public abstract double evaluate(Map<String, Double> assignment) throws Exception;
}
