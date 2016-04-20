package Simplification;

import binary.*;
import operands.Num;
import operands.Var;
import structure.Expression;
import unary.Cos;
import unary.Neg;
import unary.Sin;

/**
 * @author Elisheva Broyer.
 * @since 18/04/2016.
 */
public class StringToExpression {
    public Expression toExpression(String s) {
        try {
            return new Num(Double.parseDouble(s));
        } catch (Exception e) {
            if (s.charAt(0) != '(') {
                return getExpression(s, 0);
            } else if (s.length() > 1 && s.charAt(1) == '-') {
                return getExpression(s, 1);
            } else {
                return getExpression(s, getExternalOperand(s));
            }
        }


    }

    private Expression toExpression(String s, int from) {
        return toExpression(s.substring(from));
    }

    private Expression toExpression(String s, int from, int to) {
        return toExpression(s.substring(from, to));
    }

    private Integer getExternalOperand(String s) {
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
                    if (s.charAt(i + 1) != ' ') {
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
            case '-':
                if (index == 1) {
                    return new Neg(toExpression(s, 2, s.length() - 1));
                } else {
                    return new Minus(toExpression(s, 1, index - 1),
                            toExpression(s, index + 2, s.length() - 1));
                }
            case '#':
                return new Tag(s.substring(1));
            case '@':
                return new NumTag(s.substring(1));
            case 'l':
                if (s.length() > 3 && s.substring(0, 3).equals("log")) {
                    return toExpression(s, 3);
                }
                break;
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
