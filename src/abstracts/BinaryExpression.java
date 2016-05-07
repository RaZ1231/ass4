package abstracts;

import interfaces.Expression;
import interfaces.ExtendedExpression;

import java.util.HashMap;
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
     * @param e1 an expression.
     * @param e2 another expression.
     * @return a new expression by type.
     */
    protected abstract Expression create(Expression e1, Expression e2);

    /**
     * Simplifies sons of base expression.
     *
     * @return base expression with simplified sons.
     */
    @Override
    protected Expression simplifySons() {
        return create(getA().simplify(), getB().simplify());
    }

    /**
     * maps sons and combine maps
     *
     * @param rule said rule
     * @return
     * @throws Exception
     */
    @Override
    protected Map<String, Expression> mapSons(Expression rule) throws Exception {
        Map<String, Expression> aMap = ((ExtendedExpression) getA()).mapByRule(((BinaryExpression) rule).getA());
        Map<String, Expression> bMap = ((ExtendedExpression) getB()).mapByRule(((BinaryExpression) rule).getB());

        return combineMaps(aMap, bMap);
    }

    /**
     * evaluates sons of expression.
     *
     * @param assignment variables' values to assign
     * @return equation solution for the assignment
     * @throws Exception an exception.
     */
    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return operate(getA().evaluate(assignment), getB().evaluate(assignment));
    }

    /**
     * an abstract operation function.
     *
     * @param d1 a parameter.
     * @param d2 another parameter.
     * @return operation result.
     */
    protected abstract double operate(double d1, double d2);

    /**
     * combine two maps and checking if their keys and values match
     *
     * @param aMap map
     * @param bMap other map
     * @return combined map
     * @throws Exception
     */
    protected Map<String, Expression> combineMaps(Map<String, Expression> aMap, Map<String, Expression> bMap)
            throws Exception {
        if (aMap == null) {
            return bMap;
        } else if (bMap == null) {
            return aMap;
        }


        Map<String, Expression> newMap = new HashMap<>();

        for (Map.Entry<String, Expression> entry : aMap.entrySet()) {
            newMap.put(entry.getKey(), entry.getValue());
        }

        for (Map.Entry<String, Expression> entry : bMap.entrySet()) {
            if (newMap.containsKey(entry.getKey())) {
                if (!entry.getValue().equals(newMap.get(entry.getKey()))) {
                    throw new Exception("Expression does not follow specified rule.");
                }
            } else {
                newMap.put(entry.getKey(), entry.getValue());
            }
        }

        return newMap;
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
