package Simplifiers;

import binary.Mult;
import binary.Plus;
import operands.Num;

/**
 * @author Raziel Solomon
 * @since 17-Apr-16.
 */
public class PlusSimplifier extends BaseSimplifier {
    /**
     * Creates list containing simplification rules matching expression
     *
     * @param expression expression to use
     */
    public void initBy(Plus expression) {
        init();

        add(new Plus(expression.getA(), new Num(0)), expression.getA());
        add(new Plus(new Num(0), expression.getB()), expression.getB());
        add(new Plus(expression.getA(), expression.getA()), new Mult(new Num(2), expression.getA()));
    }
}
