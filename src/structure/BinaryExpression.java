package structure;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * binary expression class representation.
 *
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
     * get variables of sons.
     *
     * @return list containing the variables in the expression.
     */
    public List<String> getVariables() {
        List<String> vars = new LinkedList<String>();

        vars.addAll(getA().getVariables());
        vars.addAll(getB().getVariables());

        return vars;
    }

    /**
     * return assignment of sons.
     *
     * @param var        variable to replace.
     * @param expression expression to put instead.
     * @return modified sons.
     */
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
    protected abstract Expression create(Expression a, Expression b);

    /**
     * Simplifies sons of base expression.
     *
     * @return base expression with simplified sons.
     */
    @Override
    protected BaseExpression simplifySons() {
        return (BaseExpression) create(getA().simplify(), getB().simplify());
    }

    /**
     * evaluates sons of expression.
     *
     * @param assignment variables' values to assign
     * @return equation solution for the assignment
     * @throws Exception
     */
    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        try {
            return operate(getA().evaluate(assignment), getB().evaluate(assignment));
        } catch (Exception e1) {
            getB().evaluate();
            throw new Exception("cannot evaluate 'a'");
        }
    }

    /**
     * an abstract operation function.
     *
     * @param a a parameter.
     * @param b another parameter.
     * @return operation result.
     */
    protected abstract double operate(double a, double b);

    /**
     * get left son.
     *
     * @return left son.
     */
    public Expression getA() {
        return a;
    }

    /**
     * get right son.
     *
     * @return right son.
     */
    public Expression getB() {
        return b;
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

        BinaryExpression that = (BinaryExpression) o;

        return (a != null ? a.equals(that.a) : that.a == null) && (b != null ? b.equals(that.b) : that.b == null);
    }
}
