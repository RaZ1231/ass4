package Simplification;

import binary.Log;
import binary.Pow;
import operands.Num;
import structure.BaseSimplifier;

/**
 * @author Raziel Solomon
 * @since 17-Apr-16.
 */
public class LogSimplifier extends BaseSimplifier {
    /**
     * Creates list containing simplification rules matching expression
     *
     * @param expression expression to use
     */
    public void initBy(Log expression) {
        init();

        add(new Log(expression.getA(), new Num(1)), new Num(0));
        add(new Log(expression.getA(), expression.getA()), new Num(1));
        add(new Log(expression.getA(), new Pow(expression.getA(), null)), null);
    }
}
