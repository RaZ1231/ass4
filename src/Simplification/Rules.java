package simplification;

import java.util.ArrayList;
import java.util.List;

/**
 * Contains rules list.
 *
 * @author Raziel Solomon
 * @since 20-Apr-16.
 */
public class Rules {
    private static List<Rule> rules;
    private static boolean isInit = false;

    public List<Rule> getRules() {
        if (!isInit) {
            init();
        }
        return rules;
    }

    private void init() {
        isInit = true;
        rules = new ArrayList<>(); //list of rules
        //Simple rules
        rules.add(new Rule("(#1 + 0)", "#1"));
        rules.add(new Rule("(#1 - 0)", "#1"));
        rules.add(new Rule("(0 - #1)", "(-#1)"));
        rules.add(new Rule("(#1 - #1)", "0"));
        rules.add(new Rule("(#1 * 0)", "0"));
        rules.add(new Rule("(#1 * 1)", "#1"));
        rules.add(new Rule("(0 / #1)", "0"));
        rules.add(new Rule("(#1 / 1)", "#1"));
        rules.add(new Rule("(#1 / #1)", "1"));
        rules.add(new Rule("log(#1, 1)", "0"));
        rules.add(new Rule("log(#1, #1)", "1"));
        rules.add(new Rule("(#1^0)", "1"));
        rules.add(new Rule("(1^#1)", "1"));
        rules.add(new Rule("(#1^1)", "#1"));
        rules.add(new Rule("(0^#1)", "0"));

        //Complex rules
        rules.add(new Rule("(-(-#1))", "#1"));
        rules.add(new Rule("(#1 + (-#2))", "(#1 - #2)"));
        rules.add(new Rule("(#1 - (-#2))", "(#1 + #2)"));
        rules.add(new Rule("(#1 * (-#2))", "(-(#1 * #2))"));
        rules.add(new Rule("((-#1) * (-#2))", "(#1 * #2)"));
        rules.add(new Rule("(#1 / (-#2))", "(-(#1 / #2))"));
        rules.add(new Rule("((-#1) / (-#2))", "(#1 / #2)"));
        rules.add(new Rule("(#1 + #1)", "(2 * #1)"));
        rules.add(new Rule("((@1 * #2) + #2)", "((@1 + 1) * #2)"));
        rules.add(new Rule("((@1 * #2) + (@3 * #2))", "((@1 + @3) * #2)"));
        rules.add(new Rule("(@1 + (@2 + &3))", "((@1 + @2) + &3)"));
        rules.add(new Rule("((@1 * #2) + (#3 + (@4 * #2)))", "(((@1 + @4) * #2) + #3)"));
        rules.add(new Rule("(#1 * #1)", "(#1^2)"));
        rules.add(new Rule("(#1 * (#1^#2))", "(#1^(1 + #2))"));
        rules.add(new Rule("log(#1, (&2 / #3))", "(log(#1, &2) - log(#1, #3))"));
        rules.add(new Rule("log(#1, (#2 / &3))", "(log(#1, #2) - log(#1, &3))"));
        rules.add(new Rule("log(#1, (&2 * #3))", "(log(#1, &2) + log(#1, #3))"));
        rules.add(new Rule("(log(#1, @2) - log(#1, @3))", "log(#1, (@2 / @3))"));
        rules.add(new Rule("(log(#1, @2) + log(#1, @3))", "log(#1, (@2 * @3))"));
        rules.add(new Rule("(log(#1, #2) / log(#1, #3))", "log(#3, #2)"));
        rules.add(new Rule("(#1^log(#1, #2))", "#2"));
        rules.add(new Rule("log(#1, (#2^#3))", "(#3 * log(#1, #2))"));
        rules.add(new Rule("(1 / (#1^#2))", "(#1^(-#2))"));
        rules.add(new Rule("((#1^#2)^#3)", "(#1^(#2 * #3))"));
        rules.add(new Rule("((#1^#2) * (#1^#3))", "#1^(#2 + #3)"));
        rules.add(new Rule("((@1^#2) * (@3^#2))", "((@1 * @3)^#2)"));
        rules.add(new Rule("((#1^#2) / #1)", "(#1^(#2 - 1))"));
        rules.add(new Rule("(#1 / (#1^#2))", "(#1^(1 - #2))"));
        rules.add(new Rule("((#1^#2) / (#1^#3))", "(#1^(#2 - #3))"));
        rules.add(new Rule("((@1^#2) / (@3^#2))", "((@1 / @3)^#2)"));
        rules.add(new Rule("((#1 / #2) + (#3 / #2))", "((#1 + #3) / #2)"));
        rules.add(new Rule("((#1 / #2) - (#3 / #2))", "((#1 - #3) / #2)"));
        rules.add(new Rule("((#1 / #2) + (#3 / #4))", "(((#1 * #4) + (#3 * #2)) / (#2 * #4))"));
        rules.add(new Rule("((#1 / #2) - (#3 / #4))", "(((#1 * #4) - (#3 * #2)) / (#2 * #4))"));
        rules.add(new Rule("((sin(#1)^2) + (cos(#1)^2))", "1"));
        rules.add(new Rule("sin(pi - #1)", "sin(#1)"));
        rules.add(new Rule("cos(pi - #1)", "(-cos(#1))"));
        rules.add(new Rule("sin((-#1))", "(-sin(#1))"));
        rules.add(new Rule("cos((-#1))", "cos(#1)"));


    }
}
