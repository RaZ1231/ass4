package Simplifiers;

import binary.Log;
import binary.Pow;
import operands.Num;
import structure.BinaryExpression;

/**
 * @author Raziel Solomon
 * @since 17-Apr-16.
 */
public class PowSimplifier extends BaseSimplifier {
    /**
     * Creates list containing simplification rules matching expression
     *
     * @param expression expression to use
     */
    public void initBy(Pow expression) {
        init();

        add(new Pow(expression.getA(), new Num(0)), new Num(1));
        add(new Pow(new Num(0), expression.getB()), new Num(0));
        add(new Pow(expression.getA(), new Num(1)), expression.getA());
        add(new Pow(new Num(1), expression.getB()), new Num(1));
        //add(new Mult(expression.getA(), new Log(expression.getA(),expression.getB().getB())), expression.getB().getB
        //        ());
        add(new Pow(expression.getA(), new Log(expression.getA(),
                ((BinaryExpression) expression.getB()).getB())),
                ((BinaryExpression) expression.getB()).getB());
    }
}
