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
        return "" + value;
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
        return this;
    }
}
