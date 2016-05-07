package operands;

import interfaces.Expression;
import interfaces.ExtendedExpression;

/**
 * constant representation class.
 *
 * @author Raziel Solomon
 * @since 13-Apr-16.
 */
public class Const extends Num implements ExtendedExpression {
    private String name;

    /**
     * constructor.
     *
     * @param name  a string.
     * @param value a number.
     */
    public Const(String name, double value) {
        super(value);
        this.name = name;
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

        return Double.compare(aConst.getValue(), getValue()) == 0;

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
}
