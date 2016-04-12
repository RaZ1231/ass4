package binary;

import operands.Num;
import operands.Var;
import structure.BinaryExpression;
import structure.Expression;

/**
 * @author Raziel Solomon
 * @since 11-Apr-16.
 */
public class Minus extends BinaryExpression {
    public Minus(double a, double b) {
        this(new Num(a), new Num(b));
    }

    public Minus(Expression a, Expression b) {
        super(a, b);
    }

    public Minus(double a, String b) {
        this(new Num(a), new Var(b));
    }

    public Minus(String a, double b) {
        this(new Var(a), new Num(b));
    }

    public Minus(String a, String b) {
        this(new Var(a), new Var(b));
    }

    @Override
    public double operate(double a, double b) {
        return a - b;
    }

    @Override
    public Expression create(Expression a, Expression b) {
        return new Minus(a, b);
    }

    @Override
    public String toString(Expression a, Expression b) {
        return "(" + a + " - " + b + ")";
    }
}
