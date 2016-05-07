package rulessimplification;

import java.util.LinkedList;
import java.util.List;

/**
 * Contains list of rules for simplifications.
 *
 * @author Raziel Solomon
 * @since 20-Apr-16.
 */
public class Rules {
    private static List<Rule> rules;

    /**
     * initialized list of rules to compare with.
     */
    static {
        rules = new LinkedList<Rule>(); //int
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
        rules.add(new Rule("((-1) * #1)", "(-#1)"));
        rules.add(new Rule("(-(-#1))", "#1"));
        rules.add(new Rule("(#1 + (-#2))", "(#1 - #2)"));
        rules.add(new Rule("(#1 - (-#2))", "(#1 + #2)"));
        rules.add(new Rule("(#1 * (-#2))", "(-(#1 * #2))"));
        rules.add(new Rule("((-#1) * (-#2))", "(#1 * #2)"));
        rules.add(new Rule("(#1 / (-#2))", "(-(#1 / #2))"));
        rules.add(new Rule("((-#1) / (-#2))", "(#1 / #2)"));
        rules.add(new Rule("(#1 + #1)", "(2 * #1)"));
        rules.add(new Rule("((@1 * #2) + #2)", "((@1 + 1) * #2)"));
        rules.add(new Rule("((@1 * #2) - #2)", "((@1 - 1) * #2)"));
        rules.add(new Rule("(#2 - (@1 * #2))", "((1 - @1) * #2)"));
        rules.add(new Rule("((@1 * #2) + (@3 * #2))", "((@1 + @3) * #2)"));
        rules.add(new Rule("((@1 * #2) - (@3 * #2))", "((@1 - @3) * #2)"));
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
        rules.add(new Rule("((#1^#2) / (#3 * #1))", "((#1^(#2 - 1)) / #3)"));
        rules.add(new Rule("((#1^#2) / (#3 * (#1^#4)))", "((#1^(#2 - #4)) / #3)"));
        rules.add(new Rule("((sin(#1)^2) + (cos(#1)^2))", "1"));
        rules.add(new Rule("sin(pi - #1)", "sin(#1)"));
        rules.add(new Rule("cos(pi - #1)", "(-cos(#1))"));
        rules.add(new Rule("sin((-#1))", "(-sin(#1))"));
        rules.add(new Rule("cos((-#1))", "cos(#1)"));
        rules.add(new Rule("(@1 * (@2 * &3))", "((@1 * @2) * &3)"));
        rules.add(new Rule("(@1 * (-&2))", "((-@1) * &2))"));
        rules.add(new Rule("(#1 * (#2 / #1))", "#2"));
        rules.add(new Rule("(#1 * (#2 / #3))", "((#1 * #2) / #3)"));

    }

    /**
     * returns list of rules.
     *
     * @return list of rules.
     */
    public static List<Rule> getRules() {
        return rules;
    }
}
