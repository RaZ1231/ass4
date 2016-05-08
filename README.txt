RULES LIST:
* "(#1 + 0)" -> "#1"
* "(#1 - 0)" -> "#1"
* "(0 - #1)" -> "(-#1)"
* "(#1 - #1)" -> "0"
* "(#1 * 0)" -> "0"
* "(#1 * 1)" -> "#1"
* "(0 / #1)" -> "0"
* "(#1 / 1)" -> "#1"
* "(#1 / #1)" -> "1"
* "log(#1, 1)" -> "0"
* "log(#1, #1)" -> "1"
* "(#1^0)" -> "1"
* "(1^#1)" -> "1"
* "(#1^1)" -> "#1"
* "(0^#1)" -> "0"
* "(-(-#1))" -> "#1"
* "(#1 + (-#2))" -> "(#1 - #2)"));
* "(#1 - (-#2))" -> "(#1 + #2)"));
* "(#1 * (-#2))" -> "(-(#1 * #2))"));
* "((-#1) * (-#2))" -> "(#1 * #2)"));
* "(#1 / (-#2))" -> "(-(#1 / #2))"));
* "((-#1) / (-#2))" -> "(#1 / #2)"));
* "(#1 + #1)" -> "(2 * #1)"
* "((@1 * #2) + #2)" -> "((@1 + 1) * #2)"
* "((@1 * #2) + (@3 * #2))" -> "((@1 + @3) * #2)"
* "(@1 + (@2 + &3))" -> "((@1 + @2) + &3)"
* "((@1 * #2) + (#3 + (@4 * #2)))" -> "(((@1 + @4) * #2) + #3)"
* "(#1 * #1)" -> "(#1^2)"
* "(#1 * (#1^#2))" -> "(#1^(1 + #2))"
* "log(#1, (&2 / #3))" -> "(log(#1, &2) - log(#1, #3))"
* "log(#1, (#2 / &3))" -> "(log(#1, #2) - log(#1, &3))"
* "log(#1, (&2 * #3))" -> "(log(#1, &2) + log(#1, #3))"
* "(log(#1, @2) - log(#1, @3))" -> "log(#1, (@2 / @3))"
* "(log(#1, @2) + log(#1, @3))" -> "log(#1, (@2 * @3))"
* "(log(#1, #2) / log(#1, #3))" -> "log(#3, #2)"
* "(#1^log(#1, #2))" -> "#2"
* "log(#1, (#2^#3))" -> "(#3 * log(#1, #2))"
* "(1 / (#1^#2))" -> "(#1^(-#2))"
* "((#1^#2)^#3)" -> "(#1^(#2 * #3))"
* "((#1^#2) * (#1^#3))" -> "#1^(#2 + #3)"
* "((@1^#2) * (@3^#2))" -> "((@1 * @3)^#2)"
* "((#1^#2) / #1)" -> "#1^(#2 - 1)"
* "(#1 / (#1^#2))" -> "#1^(1 - #2)"
* "((#1^#2) / (#1^#3))" -> "#1^(#2 - #3)"
* "((@1^#2) / (@3^#2))" -> "((@1 / @3)^#2)"
* "((#1 / #2) + (#3 / #2))" -> "((#1 + #3) / #2"
* "((#1 / #2) - (#3 / #2))" -> "((#1 - #3) / #2"
* "((#1 / #2) + (#3 / #4))" -> "(((#1 * #4) + (#3 * #2)) / (#2 * #4))"
* "((#1 / #2) - (#3 / #4))" -> "(((#1 * #4) - (#3 * #2)) / (#2 * #4))"
* "((#1^#2) / (#3 * #1))" -> "((#1^(#2 - 1)) / #3)"));
* "((#1^#2) / (#3 * (#1^#4)))" -> "((#1^(#2 - #4)) / #3)"));
* "((sin(#1)^2) + (cos(#1)^2))" -> "1"
* "sin(pi - #1)" -> "sin(#1)"
* "cos(pi - #1)" -> "(-cos(#1))"
* "sin((-#1))" -> "(-sin(#1))"
* "cos((-#1))" -> "cos(#1)"

* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
OUR APPROACH:
We used ExtendedExpression interface to add more methods to the expressions.
We have three main concepts for simplification:
1. Evaluation - Trying to evaluate an expression (numeric).
2. Simplification by rules - A rule is an object that contain an expression and its simple form.
    We use a list containing complex algebraic rules, and an expression that knows how to check if it can be
    simplified by a certain rule. We built a special method that converting a String to Expression for
    easier management of all different rules. Checking of a rule is using a special function that
    tries to map the expression by that rule, and throws exception if it can not be simplified by that rule.
    For the comparison we use special classes called Tags. That way we can build a general rule and mark
    the places where we want a number, a variable or several instances of the same expression.
    If the mapping process succeed, we get a map with tags and corresponding expressions.
    Then, we can replace the tag of the simple form of the expression in the rule.
3. Nested expression - First, we use an adjusted form getVariables methods to get a list of variables
    connected by associative operations such as addition and multiplication. Then, we try to combine
    couples of expressions in that list by evaluation or by rules.
    Finally, the new list is converted back to an expression.

The simplify method loops through all of those concepts of simplification.
For each concept an exception is thrown if it had not been able simplify the expression.
The simplification process stops when all concepts fail.