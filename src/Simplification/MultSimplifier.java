package Simplification;

import binary.Mult;
import binary.Pow;
import operands.Num;
import structure.BaseSimplifier;

/**
 * @author Raziel Solomon
 * @since 17-Apr-16.
 */
public class MultSimplifier extends BaseSimplifier {
    /**
     * Creates list containing simplification rules matching expression
     *
     * @param expression expression to use
     */
    public void initBy(Mult expression) {
        init();

        add(new Mult(expression.getA(), new Num(0)), new Num(0));
        add(new Mult(new Num(0), expression.getB()), new Num(0));
        add(new Mult(expression.getA(), new Num(1)), expression.getA());
        add(new Mult(new Num(1), expression.getB()), expression.getB());
        add(new Mult(expression.getA(), expression.getA()), new Pow(expression.getA(), new Num(2)));
    }
}
