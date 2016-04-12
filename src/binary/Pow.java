package binary;

import operands.Num;
import operands.Var;
import structure.BinaryExpression;
import structure.Expression;

/**
 * @author Raziel Solomon
 * @since 11-Apr-16.
 */
public class Pow extends BinaryExpression {
    public Pow(double a, double b) {
        this(new Num(a), new Num(b));
    }

    public Pow(Expression a, Expression b) {
        super(a, b);
    }

    public Pow(double a, String b) {
        this(new Num(a), new Var(b));
    }

    public Pow(String a, double b) {
        this(new Var(a), new Num(b));
    }

    public Pow(String a, String b) {
        this(new Var(a), new Var(b));
    }

    @Override
    public double operate(double a, double b) {
        return Math.pow(a, b);
    }

    @Override
    public Expression create(Expression a, Expression b) {
        return new Pow(a, b);
    }

    @Override
    public String toString(Expression a, Expression b) {
        return "(" + a + " ^ " + b + ")";
    }
}
