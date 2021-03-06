package rulessimplification;

import binary.Div;
import binary.Log;
import binary.Minus;
import binary.Mult;
import binary.Plus;
import binary.Pow;
import interfaces.Expression;
import operands.Const;
import operands.Num;
import operands.Var;
import tags.ExpTag;
import tags.NumTag;
import tags.VarTag;
import unary.Cos;
import unary.Neg;
import unary.Sin;

/**
 * Converts String to Expression.
 *
 * @author Elisheva Broyer.
 * @since 18/04/2016.
 */
public class Parser {
    /**
     * Converts String to Expression.
     *
     * @param s string to convert.
     * @return expression.
     */
    public Expression toExpression(String s) {
        try {
            return new Num(Double.parseDouble(s));
        } catch (Exception e) {
            if (s.charAt(0) != '(') {
                return getExpression(s, 0);
            } else if (s.length() > 1 && s.charAt(1) == '-') {
                return getExpression(s, 1);
            } else {
                return getExpression(s, getExternalOperator(s));
            }
        }
    }

    /**
     * Converts Substring to Expression.
     *
     * @param s    string to convert.
     * @param from from index.
     * @return expression.
     */
    private Expression toExpression(String s, int from) {
        return toExpression(s.substring(from));
    }

    /**
     * Converts String to Expression.
     *
     * @param s    string to convert.
     * @param from from index.
     * @param to   to index.
     * @return expression.
     */
    private Expression toExpression(String s, int from, int to) {
        return toExpression(s.substring(from, to));
    }

    /**
     * search for next external operator.
     *
     * @param s string to search in.
     * @return index of operator.
     */
    private Integer getExternalOperator(String s) {
        int brackets = 0;
        int minBrackets = s.length();
        Integer minIndex = 0;

        for (int i = 0; i < s.length(); i++) {
            switch (s.charAt(i)) {
                case '(':
                    brackets += 1;
                    break;
                case ')':
                    brackets -= 1;
                    break;
                case '-':
                    if (s.charAt(i + 1) != ' ') { //Neg
                        break;
                    }
                case ',':
                case '+':
                case '*':
                case '/':
                case '^':
                    if (brackets < minBrackets) {
                        minBrackets = brackets;
                        minIndex = i;
                    }
                    break;
                default:
            }
        }

        return minIndex;
    }

    /**
     * create new expression for index.
     *
     * @param s     string to operate on.
     * @param index external operator.
     * @return expression on toBaseExpression(string).
     */
    private Expression getExpression(String s, int index) {
        switch (s.charAt(index)) {
            case 'c':
                if (s.length() > 3 && s.substring(0, 3).equals("cos")) {
                    return new Cos(toExpression(s, 4, s.length() - 1));
                }
                break;
            case 's':
                if (s.length() > 3 && s.substring(0, 3).equals("sin")) {
                    return new Sin(toExpression(s, 4, s.length() - 1));
                }
                break;
            case '-': //Neg
                if (index == 1) {
                    return new Neg(toExpression(s, 2, s.length() - 1));
                } else { //Minus
                    return new Minus(toExpression(s, 1, index - 1),
                            toExpression(s, index + 2, s.length() - 1));
                }
            case '#':
                return new ExpTag(s.substring(1));
            case '@':
                return new NumTag(s.substring(1));
            case '&':
                return new VarTag(s.substring(1));
            case 'l':
                if (s.length() > 3 && s.substring(0, 3).equals("log")) {
                    return toExpression(s, 3);
                }
                break;
            case 'p':
                if (s.length() > 1 && s.substring(0, 2).equals("pi")) {
                    return new Const("pi", Math.PI);
                }
                break;
            case 'e':
                return new Const("e", Math.exp(1));
            case ',':
                return new Log(toExpression(s, 1, index),
                        toExpression(s, index + 2, s.length() - 1));
            case '+':
                return new Plus(toExpression(s, 1, index - 1),
                        toExpression(s, index + 2, s.length() - 1));
            case '*':
                return new Mult(toExpression(s, 1, index - 1),
                        toExpression(s, index + 2, s.length() - 1));
            case '/':
                return new Div(toExpression(s, 1, index - 1),
                        toExpression(s, index + 2, s.length() - 1));
            case '^':
                return new Pow(toExpression(s, 1, index),
                        toExpression(s, index + 1, s.length() - 1));
            default:
        }

        return new Var(s);
    }
}
