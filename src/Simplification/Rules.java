package Simplification;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Raziel Solomon
 * @since 20-Apr-16.
 */
public class Rules {
    static final List<Rule> RULES = new ArrayList<Rule>() {{
        add(new Rule("(x + 0)", "x"));
        add(new Rule("(0 + x)", "x"));

    }};
}
