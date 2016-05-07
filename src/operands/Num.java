package operands;

import interfaces.Expression;
import interfaces.ExtendedExpression;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import nestedsimplification.LinearExpression;
import nestedsimplification.LinearSequence;
import nestedsimplification.PolynomialExpression;
import nestedsimplification.PolynomialSequence;
import tags.ExpTag;
import tags.NumTag;
import unary.Neg;

/**
 * number representation class.
 *
 * @author Raziel Solomon
 * @since 11-Apr-16.
 */
public class Num implements ExtendedExpression {
    private double value;

    /**
     * constructor.
     *
     * @param num a number.
     */
    public Num(double num) {
        this.value = num;
    }

    /**
     * map expression by rule for rulessimplification.
     *
     * @param rule rule to map by.
     * @return map of rule's tags.
     * @throws Exception expression is not compatible.
     */
    @Override
    public Map<String, Expression> mapByRule(Expression rule) throws Exception {
        if (equals(rule)) {
            return null;
        } else if (rule instanceof ExpTag) {
            return Collections.singletonMap(((ExpTag) rule).getValue(), (Expression) this);
        } else if (rule instanceof NumTag) {
            return Collections.singletonMap(((NumTag) rule).getValue(), (Expression) this);
        } else if (rule instanceof Neg && asNeg().equals(rule)) {
            return null;
        } else {
            throw new Exception("Expression does not follow specified rule.");
        }
    }

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

        Num num = (Num) o;

        return Double.compare(num.value, value) == 0;

    }

    /**
     * returns a nice string representation of the expression.
     *
     * @return string representation.
     */
    @Override
    public String toString() {
        return "" + getValue();
    }

    /**
     * returns num's value.
     *
     * @return num's value.
     */
    public double getValue() {
        return value;
    }

    /**
     * evaluate the expression using the variable values provided
     * in the assignment, and return the result.  If the expression
     * contains a variable which is not in the assignment, an exception
     * is thrown.
     *
     * @param assignment variables' values to assign
     * @return equation solution for the assignment
     * @throws Exception an exception.
     */
    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return getValue();
    }

    /**
     * returns the expression as Neg.
     *
     * @return the expression as Neg.
     */
    protected Expression asNeg() {
        return new Neg((-1) * getValue());
    }

    /**
     * a convenience method. Similar to `evaluate(assignment)` method above,
     * but uses an empty assignment.
     *
     * @return equation solution.
     * @throws Exception an exception.
     */
    @Override
    public double evaluate() throws Exception {
        return evaluate(null);
    }

    /**
     * get list of expressions that are connected by linear operations.
     *
     * @return the list.
     */
    @Override
    public LinearSequence getLinearVariables() {
        return LinearSequence.singletonList(new LinearExpression(this));
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
     * get list of expressions that are connected by linear operations.
     *
     * @return the list.
     */
    @Override
    public PolynomialSequence getPolynomialVariables() {
        return PolynomialSequence.singletonList(new PolynomialExpression(this));
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
        return new Num(getValue());
    }

    /**
     * Returned a simplified version of the current expression.
     *
     * @return simplified expression.
     */
    @Override
    public Expression simplify() {
        return new Num(getValue());
    }

    /**
     * returns the expression tree resulting from differentiating
     * the current expression relative to variable `var`.
     *
     * @param var a string variable.
     * @return the differentiate of the expression.
     */
    @Override
    public Expression differentiate(String var) {
        return new Num(0);
    }
}
