package Simplification;

import com.sun.xml.internal.txw2.DatatypeWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Contains rules list.
 *
 * @author Raziel Solomon
 * @since 20-Apr-16.
 */
public class Rules {
    static final List<Rule> RULES = new ArrayList<Rule>() {{ //list of rules
        add(new Rule("(#1 + 0)", "#1"));
        add(new Rule("(0 + #1)", "#1"));
        add(new Rule("((@1 * #2) + (@3 * #1))", "((@1 + @3) * #1)"));///?
        add(new Rule("(#1 - 0)", "#1"));
        add(new Rule("(0 - #1)", "(-#1)"));
        add(new Rule("(#1 - #1)", "0"));
        add(new Rule("(-(-#1))", "#1"));
        add(new Rule("(#1 * 0)", "0"));
        add(new Rule("(0 * #1)", "0"));
        add(new Rule("(#1 * 1)", "#1"));
        add(new Rule("(1 * #1)", "#1"));
        add(new Rule("(#1 * #1)", "(#1^2)"));
        add(new Rule("(0 / #1)","0"));
        add(new Rule("(#1 / 0)","Crisis!!!"));///////?
        add(new Rule("(#1 / 1)","#1"));
        add(new Rule("(#1 / #1)", "1"));
        add(new Rule("log(#1, 1)", "0"));
        add(new Rule("log(#1, #1)", "1"));
        add(new Rule("(log(#1, #2) - log(#1, #3))", "log(#1, (#2 / #3))"));/// opposite?
        add(new Rule("(log(#1, #2) + log(#1, #3))", "log(#1, (#2 * #3))"));/// opposite?
        add(new Rule("(log(#1, #2) / log(#1, #3))", "log(#3, #2)"));
        add(new Rule("(#1^log(#1, #2))", "#2"));
        add(new Rule("log(#1, (#2^#3))", "(#3 * log(#1, #2))"));
        add(new Rule("log(#1, (#2^(#3 / #4)))", "((#4 / #3) * log(#1, #2))"));/// is problem with above?
        add(new Rule("(#1^0)", "1"));
        add(new Rule("(1^#1)", "1"));
        add(new Rule("(0^#1)", "0"));
        add(new Rule("(1 / (#1 ^ #2))", "(#1^(-#2))"));/// opposite?
        add(new Rule("((#1^#2)^#3)", "(#1^(#2 * #3))"));
        add(new Rule("((#1^#2) * (#1^#3))", "#1^(#2 + #3)"));
        add(new Rule("((#1^#2) * (#3^#2))", "((#1 * #3)^#2)"));
        add(new Rule("((#1^#2) / (#1^#3))", "#1^(#2 - #3)"));
        add(new Rule("((#1^#2) / (#3^#2))", "((#1 / #3)^#2)"));
        add(new Rule("sin((#1 * pi)", "0"));
        add(new Rule("cos((#1 * (pi / 2))", "0"));
        add(new Rule("((sin(#1)^2) + (cos(#1))^2))", "1"));
        add(new Rule("((cos(#1))^2) + (sin(#1)^2))", "1"));

    }};
}
