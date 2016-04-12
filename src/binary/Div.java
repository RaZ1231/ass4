package binary;

import operands.Num;
import operands.Var;
import structure.BinaryExpression;
import structure.Expression;

/**
 * @author Raziel Solomon
 * @since 11-Apr-16.
 */
public class Div extends BinaryExpression {
    public Div(double a, double b) {
        this(new Num(a), new Num(b));
    }

    public Div(Expression a, Expression b) {
        super(a, b);
    }

    public Div(double a, String b) {
        this(new Num(a), new Var(b));
    }

    public Div(String a, double b) {
        this(new Var(a), new Num(b));
    }

    public Div(String a, String b) {
        this(new Var(a), new Var(b));
    }

    @Override
    public double operate(double a, double b) {
        return a / b;
    }

    @Override
    public Expression create(Expression a, Expression b) {
        return new Div(a, b);
    }

    @Override
    public String toString(Expression a, Expression b) {
        return "(" + a + " / " + b + ")";
    }
}
