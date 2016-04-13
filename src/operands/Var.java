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

    /**
     * returns a nice string representation of the expression.
     *
     * @return string representation
     */
    @Override
    public String toString() {
        return getValue();
    }

    public String getValue() {
        return value;
    }

    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        if (assignment.containsKey(getValue())) {
            return assignment.get(getValue());
        } else {
            throw new Exception("Assignment doesn't contain " + getValue());
        }
    }

    @Override
    public double evaluate() throws Exception {
        return evaluate(null);
    }

    @Override
    public List<String> getVariables() {
        return Collections.singletonList(getValue());
    }


    @Override
    public Expression assign(String var, Expression expression) {
        if (getValue() == var) {
            return expression;
        } else {
            return new Var(getValue());
        }
    }

    /**
     * Returned a simplified version of the current expression.
     *
     * @return simplified expression
     */
    @Override
    public Expression simplify() {
        return new Var(getValue());
    }

    /**
     * checks if zero
     *
     * @return true\false
     */
    @Override
    public boolean isZero() {
        return false;
    }

    /**
     * checks if one
     *
     * @return true/false
     */
    @Override
    public boolean isOne() {
        return false;
    }

    /**
     * checks if even
     *
     * @return true/false
     */
    @Override
    public boolean isEven() {
        return false;
    }
}
