package Simplification;

import structure.BaseSimplifier;
import unary.Neg;

/**
 * @author Raziel Solomon
 * @since 17-Apr-16.
 */
public class NegSimplifier extends BaseSimplifier {
    /**
     * Creates list containing simplification rules matching expression
     *
     * @param expression expression to use
     */
    public void initBy(Neg expression) {
        init();

        //add(new Log(expression.getA(), new Num(1)), new Num(0));
        //add(new Log(expression.getA(), expression.getA()), new Num(1));
    }
}
