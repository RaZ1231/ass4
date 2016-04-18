package Simplifiers;

import binary.Div;
import operands.Num;

/**
 * @author Raziel Solomon
 * @since 17-Apr-16.
 */
public class DivSimplifier extends BaseSimplifier {
    /**
     * Creates list containing simplification rules matching expression
     *
     * @param expression expression to use
     */
    public void initBy(Div expression) {
        init();

        add(new Div(new Num(0), expression.getB()), new Num(0));
        add(new Div(expression.getA(), new Num(1)), expression.getA());
        add(new Div(expression.getA(), expression.getA()), new Num(1));
        //add(new Div(new Log())); Loa div Log with same base.
    }
}
