package abstracts;

import interfaces.Expression;
import interfaces.ExtendedExpression;
import java.util.Collections;
import java.util.Map;
import nestedsimplification.LinearExpression;
import nestedsimplification.LinearSequence;
import nestedsimplification.PolynomialExpression;
import nestedsimplification.PolynomialSequence;
import operands.Num;
import rulessimplification.Rule;
import rulessimplification.Rules;
import tags.ExpTag;
import tags.NumTag;
import tags.VarTag;

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
        Expression simple = simplifySons();

        do { // evaluate and simplify until the expression isn't changing.
            try {
                simple = ((BaseExpression) simple).simplifySons();
            } catch (Exception ignored) {
                continue;
            } finally {
                try {
                    return new Num(simple.evaluate()); //try to evaluate
                } catch (Exception e1) {
                    try {
                        simple = ((BaseExpression) simple).rulesSimplification();
                    } catch (Exception e2) {
                        try {
                            simple = ((BaseExpression) simple).linearSimplification();
                        } catch (Exception e3) {
                            try {
                                simple = ((BaseExpression) simple).polynomialSimplification();
                            } catch (Exception e4) {
                                break;
                            }
                        }
                    }
                }
            }

        } while (true);

        return simple;
    }

    /**
     * Simplifies sons of base expression.
     *
     * @return base expression with simplified sons.
     */
    protected abstract Expression simplifySons();

    /**
     * iterate over rules to find a match for rulessimplification.
     *
     * @return simplified expression.
     * @throws Exception an exception.
     */
    public Expression rulesSimplification() throws Exception {
        Map<String, Expression> tags;

        for (Rule rule : Rules.getRules()) {
            try {
                tags = mapByRule(rule.getComplicated());

                return rule.applyByMap(tags);
            } catch (Exception ignored) {
                continue;
            }
        }

        throw new Exception("Expression can not be simplified by Rules Simplification");
    }

    /**
     * initiate linear rulessimplification.
     *
     * @return simplified expression.
     * @throws Exception an exception.
     */
    private Expression linearSimplification() throws Exception {
        LinearSequence expressions = new LinearSequence();

        expressions.addAll(getLinearVariables());

        return expressions.simplify();
    }

    /**
     * initiate polynomial rulessimplification.
     *
     * @return simplified expression.
     * @throws Exception an exception.
     */
    private Expression polynomialSimplification() throws Exception {
        PolynomialSequence expressions = new PolynomialSequence();

        expressions.addAll(getPolynomialVariables());

        return expressions.simplify();
    }

    /**
     * map expression by rule for rulessimplification.
     *
     * @param rule rule to map by.
     * @return map of rule's tags.
     * @throws Exception expression is not compatible.
     */
    public Map<String, Expression> mapByRule(Expression rule) throws Exception {
        if (rule instanceof ExpTag) {
            return Collections.singletonMap(((ExpTag) rule).getValue(), (Expression) this);
        } else if (rule instanceof NumTag) {
            return new Num(evaluate()).mapByRule(rule);
        } else if (rule instanceof VarTag) {
            try {
                evaluate();
            } catch (Exception e) {
                return Collections.singletonMap(((VarTag) rule).getValue(), (Expression) this);
            }
            return Collections.singletonMap(((VarTag) rule).getValue(), (Expression) this);
        } else if (rule.getClass() == getClass()) {
            return mapSons(rule);
        } else {
            throw new Exception("Expression does not follow specified rule.");
        }
    }

    /**
     * get list of expressions that are connected by linear operations.
     *
     * @return the list.
     */
    public LinearSequence getLinearVariables() {
        return LinearSequence.singletonList(new LinearExpression((ExtendedExpression) this));
    }

    /**
     * get list of expressions that are connected by polynomial operations.
     *
     * @return the list.
     */
    public PolynomialSequence getPolynomialVariables() {
        return PolynomialSequence.singletonList(new PolynomialExpression((ExtendedExpression) this));
    }

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
     * map sons by rule.
     *
     * @param rule said rule.
     * @return mapped expression.
     * @throws Exception an exception.
     */
    protected abstract Map<String, Expression> mapSons(Expression rule) throws Exception;

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
