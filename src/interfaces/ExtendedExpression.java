package interfaces;

import java.util.Map;
import nestedsimplification.LinearSequence;
import nestedsimplification.PolynomialSequence;

/**
 * An extension to Expression.
 *
 * @author Raziel Solomon
 * @since 06-May-16.
 */
public interface ExtendedExpression extends Expression {
    /**
     * map expression by rule for rulessimplification.
     *
     * @param rule rule to map by.
     * @return map of rule's tags.
     * @throws Exception expression is not compatible.
     */
    Map<String, Expression> mapByRule(Expression rule) throws Exception;

    /**
     * get list of expressions that are connected by linear operations.
     *
     * @return the list.
     */
    LinearSequence getLinearVariables();

    /**
     * get list of expressions that are connected by polynomial operations.
     *
     * @return the list.
     */
    PolynomialSequence getPolynomialVariables();
}
