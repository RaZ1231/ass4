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
* "((sin(#1)^2) + (cos(#1)^2))" -> "1"
* "sin(pi - #1)" -> "sin(#1)"
* "cos(pi - #1)" -> "(-cos(#1))"
* "sin((-#1))" -> "(-sin(#1))"
* "cos((-#1))" -> "cos(#1)"

* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
OUR APPROACH:
We use 'Tag' classes to mark a spot where we expect Numeric, Algebraic or Any Expression.
We also use Tags where we look for the same expression in several spots.
All 'Tag' classes implement the 'Tag interface'. 'Rule' class contains a complicated pattern of
an expression and a simple pattern of the expression.
For convenience, we have a recursive Parser that can build expressions from Strings,
which makes 'Rules' class much readable and easy to manage.
We have a special 'compare' method that knows how to handle with different tags and
obeys algebraic commutative rules. While comparing the complicated pattern of the rule and the
expression, the 'compare' method also maps the expression. For each 'tag' the method saves the expression
in a map. If the check success, we use Expression's 'assign' method to place the expressions
we mapped in the simple pattern of rule and return the new simplified expression.
We use a static list of rules to contain all possible different simplification rules.