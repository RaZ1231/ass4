package binary;

import operands.Num;
import operands.Var;
import structure.BinaryExpression;
import structure.Expression;

/**
 * @author Raziel Solomon
 * @since 11-Apr-16.
 */
public class Log extends BinaryExpression {
    public Log(double a, double b) {
        this(new Num(a), new Num(b));
    }

    public Log(Expression a, Expression b) {
        super(a, b);
    }

    public Log(double a, String b) {
        this(new Num(a), new Var(b));
    }

    public Log(String a, double b) {
        this(new Var(a), new Num(b));
    }

    public Log(String a, String b) {
        this(new Var(a), new Var(b));
    }

    @Override
    public double operate(double a, double b) {
        return Math.log(a) / Math.log(b);
    }

    @Override
    public Expression create(Expression a, Expression b) {
        return new Log(a, b);
    }

    @Override
    public String toString(Expression a, Expression b) {
        return "log(" + a + ", " + b + ")";
    }
}
