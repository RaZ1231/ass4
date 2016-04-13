package operands;

import structure.Expression;

import java.util.List;
import java.util.Map;

/**
 * @author Raziel Solomon
 * @since 11-Apr-16.
 */
public class Num implements Expression {
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
     * returns a nice string representation of the expression.
     *
     * @return string representation
     */
    @Override
    public String toString() {
        return "" + getValue();
    }

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
        return getValue();
    }

    @Override
    public double evaluate() throws Exception {
        return evaluate(null);
    }

    @Override
    public List<String> getVariables() {
        return null;
    }


    @Override
    public Expression assign(String var, Expression expression) {
        return new Num(getValue());
    }

    /**
     * Returned a simplified version of the current expression.
     *
     * @return simplified expression
     */
    @Override
    public Expression simplify() {
        return new Num(getValue());
    }

    /**
     * checks if zero
     *
     * @return true\false
     */
    @Override
    public boolean isZero() {
        return getValue() == 0;
    }

    /**
     * checks if one
     *
     * @return true/false
     */
    @Override
    public boolean isOne() {
        return getValue() == 1;
    }

    /**
     * checks if even
     *
     * @return true/false
     */
    @Override
    public boolean isEven() {
        return getValue() % 2 == 0;
    }
}
