package Simplification;

import binary.Minus;
import operands.Num;
import structure.BaseSimplifier;
import unary.Neg;

/**
 * @author Raziel Solomon
 * @since 17-Apr-16.
 */
public class MinusSimplifier extends BaseSimplifier {
    /**
     * Creates list containing simplification rules matching expression
     *
     * @param expression expression to use
     */
    public void initBy(Minus expression) {
        init();

        add(new Minus(expression.getA(), new Num(0)), expression.getA());
        add(new Minus(new Num(0), expression.getB()), new Neg(expression.getB()));
        add(new Minus(expression.getA(), expression.getA()), new Num(0));
    }
}
