package structure;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Raziel Solomon
 * @since 11-Apr-16.
 */
public abstract class BinaryExpression extends BaseExpression {
    private Expression a;
    private Expression b;

    @Override
    public List<String> getVariables() {
        List<String> vars = new LinkedList<>();

        vars.addAll(a.getVariables());
        vars.addAll(b.getVariables());

        return vars;
    }
}
