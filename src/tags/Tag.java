package tags;

import structure.Expression;

/**
 * tags interface.
 *
 * @author Raziel Solomon
 * @since 21-Apr-16.
 */
public interface Tag {
    /**
     * returns tag's value.
     *
     * @return tag's value.
     */
    String getValue();

    /**
     * check if expression match tag type.
     *
     * @param expression an expression.
     * @return true if match, false otherwise.
     */
    boolean check(Expression expression);
}
