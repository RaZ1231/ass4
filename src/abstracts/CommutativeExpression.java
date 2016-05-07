package abstracts;

import interfaces.Expression;
import interfaces.ExtendedExpression;
import java.util.Map;

/**
 * Binary Expression that is commutative.
 *
 * @author Raziel Solomon
 * @since 06-May-16.
 */
public abstract class CommutativeExpression extends BinaryExpression {
    /**
     * constructor.
     *
     * @param a an expression.
     * @param b another expression.
     */
    public CommutativeExpression(Expression a, Expression b) {
        super(a, b);
    }

    /**
     * map sons commutativity.
     *
     * @param rule said rule.
     * @return mapped sons combined.
     * @throws Exception an exception.
     */
    @Override
    protected Map<String, Expression> mapSons(Expression rule) throws Exception {
        Map<String, Expression> aMap;
        Map<String, Expression> bMap;

        try {
            aMap = ((ExtendedExpression) getA()).mapByRule(((BinaryExpression) rule).getA());
            bMap = ((ExtendedExpression) getB()).mapByRule(((BinaryExpression) rule).getB());

            return combineMaps(aMap, bMap);
        } catch (Exception e) {
            aMap = ((ExtendedExpression) getA()).mapByRule(((BinaryExpression) rule).getB());
            bMap = ((ExtendedExpression) getB()).mapByRule(((BinaryExpression) rule).getA());

            return combineMaps(aMap, bMap);
        }
    }
}
