package operands;

import interfaces.Expression;
import interfaces.ExtendedExpression;
import nestedsimplification.LinearExpression;
import nestedsimplification.LinearSequence;
import nestedsimplification.PolynomialExpression;
import nestedsimplification.PolynomialSequence;
import tags.ExpTag;
import tags.VarTag;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * variance representation class.
 *
 * @author Raziel Solomon
 * @since 11-Apr-16.
 */
public class Var implements ExtendedExpression {
    private String value;

    /**
     * constructor.
     *
     * @param value a string variable.
     */
    public Var(String value) {
        this.value = value;
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

        Var var = (Var) o;

        return value != null ? value.equals(var.value) : var.value == null;
    }

    /**
     * returns a nice string representation of the expression.
     *
     * @return string representation
     */
    @Override
    public String toString() {
        return getValue();
    }

    /**
     * returns var's value.
     *
     * @return var's value.
     */
    public String getValue() {
        return value;
    }

    /**
     * map expression by rule for rulessimplification
     *
     * @param rule rule to map by
     * @return map of rule's tags
     * @throws Exception expression is not compatible
     */
    @Override
    public Map<String, Expression> mapByRule(Expression rule) throws Exception {
        if (rule instanceof ExpTag) {
            return Collections.singletonMap(((ExpTag) rule).getValue(), (Expression) this);
        } else if (rule instanceof VarTag) {
            return Collections.singletonMap(((VarTag) rule).getValue(), (Expression) this);
        } else {
            throw new Exception("Expression does not follow specified rule.");
        }
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
        if (assignment.containsKey(getValue())) {
            return assignment.get(getValue());
        } else {
            throw new Exception("Assignment doesn't contain " + getValue());
        }
    }

    /**
     * get list of expressions that are connected by linear operations
     *
     * @return the list
     */
    @Override
    public LinearSequence getLinearVariables() {
        return LinearSequence.singletonList(new LinearExpression(this));
    }

    /**
     * a convenience method. Similar to `evaluate(assignment)` method above,
     * but uses an empty assignment.
     *
     * @return equation solution
     * @throws Exception an exception.
     */
    @Override
    public double evaluate() throws Exception {
        return evaluate(null);
    }

    /**
     * get list of expressions that are connected by linear operations
     *
     * @return the list
     */
    @Override
    public PolynomialSequence getPolynomialVariables() {
        return PolynomialSequence.singletonList(new PolynomialExpression(this));
    }

    /**
     * returns a list of the variables in the expression.
     *
     * @return list containing the variables in the expression.
     */
    @Override
    public List<String> getVariables() {
        return Collections.singletonList(getValue());
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
        if (getValue().equals(var)) {
            return expression;
        } else {
            return new Var(getValue());
        }
    }

    /**
     * returned a simplified version of the current expression.
     *
     * @return simplified expression
     */
    @Override
    public Expression simplify() {
        return new Var(getValue());
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
        if (value.equals(var)) {
            return new Num(1);
        } else {
            return new Num(0);
        }
    }


}
