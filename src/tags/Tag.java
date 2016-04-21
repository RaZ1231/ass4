package tags;

import structure.Expression;

/**
 * @author Raziel Solomon
 * @since 21-Apr-16.
 */
public interface Tag {
    String getValue();

    boolean check(Expression expression);
}
