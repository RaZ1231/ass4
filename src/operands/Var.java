package operands;

import structure.Expression;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author Raziel Solomon
 * @since 11-Apr-16.
 */
public class Var implements Expression {
    private String value;

    /**
     * constructor.
     *
     * @param value a string variable.
     */
    public Var(String value) {
        this.value = value;
    }

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
        if (assignment.containsKey(getValue())) {
            return assignment.get(getValue());
        } else {
            throw new Exception("Assignment doesn't contain " + getValue());
        }
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
