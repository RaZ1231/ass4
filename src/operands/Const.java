package operands;

import structure.Expression;

import java.util.List;
import java.util.Map;

/**
 * constant representation class.
 *
 * @author Raziel Solomon
 * @since 13-Apr-16.
 */
public class Const implements Expression {
    private String name;
    private double value;

    /**
     * constructor.
     *
     * @param name  a string.
     * @param value a number.
     */
    public Const(String name, double value) {
        this.name = name;
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

        Const aConst = (Const) o;

        return Double.compare(aConst.value, value) == 0;

    }

    /**
     * returns a nice string representation of the expression.
     *
     * @return string representation.
     */
    @Override
    public String toString() {
        return getName();
    }

    /**
     * returns const's name.
     *
     * @return const's name.
     */
    public String getName() {
        return name;
    }

    /**
     * returns const's value.
     *
     * @return const's value.
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
        return new Const(getName(), getValue());
    }

    /**
     * Returned a simplified version of the current expression.
     *
     * @return simplified expression
     */
    @Override
    public Expression simplify() {
        return new Const(getName(), getValue());
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
