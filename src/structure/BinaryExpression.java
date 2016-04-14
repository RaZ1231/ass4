package structure;

import operands.Num;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author Raziel Solomon
 * @since 11-Apr-16.
 */
public abstract class BinaryExpression extends BaseExpression {
    private Expression a;
    private Expression b;

    /**
     * constructor.
     *
     * @param a an expression.
     * @param b another expression.
     */
    public BinaryExpression(Expression a, Expression b) {
        this.a = a;
        this.b = b;
    }

    /**
     * Evaluate the expression using the variable values provided
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
        return operate(getA().evaluate(assignment), getB().evaluate(assignment));
    }

    /**
     * an abstract operation function.
     *
     * @param a a parameter.
     * @param b another parameter.
     * @return operation result.
     */
    protected abstract double operate(double a, double b);

    public Expression getA() {
        return a;
    }

    public Expression getB() {
        return b;
    }

    /**
     * returns a list of the variables in the expression.
     *
     * @return list containing the variables in the expression.
     */
    @Override
    public List<String> getVariables() {
        List<String> vars = new LinkedList<String>();

        vars.addAll(a.getVariables());
        vars.addAll(b.getVariables());

        return vars;
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
        return create(getA().assign(var, expression), getB().assign(var, expression));
    }

    /**
     * returns new expression by type.
     *
     * @param a an expression.
     * @param b another expression.
     * @return a new expression by type.
     */
    protected abstract BinaryExpression create(Expression a, Expression b);

    /**
     * returns the derivative of an expression.
     *
     * @param var a string variable.
     * @return the derivative of an expression.
     */
    public abstract Expression derivative(String var);

    /**
     * Returned a simplified version of the current expression.
     *
     * @return simplified expression
     */
    @Override
    public Expression simplify() {
        Expression simpleA = getA().simplify();
        Expression simpleB = getB().simplify();
        BinaryExpression simpleExpression = create(simpleA, simpleB);
        Expression newExpression = null;

        if (simpleExpression.getA() instanceof Num && simpleExpression.getB() instanceof Num) {
            try {
                newExpression = new Num(simpleExpression.evaluate());
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            newExpression = simpleExpression.simple(simpleExpression.getA(), simpleExpression.getB());
        }

        return newExpression;
    }

    /**
     * Returned a simplified version of the current expression.
     *
     * @param a left expression
     * @param b right expression
     * @return simplified expression
     */
    protected abstract Expression simple(Expression a, Expression b);
}
