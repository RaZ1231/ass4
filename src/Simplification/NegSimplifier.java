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

        add(new Neg(new Neg(expression.getA())), expression.getA());
    }
}
