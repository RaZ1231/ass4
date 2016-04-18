package Simplification;

import binary.Div;
import binary.Log;
import binary.Minus;
import binary.Mult;
import binary.Plus;
import binary.Pow;
import operands.Num;
import operands.Var;
import structure.Expression;
import unary.Cos;

/**
 * @author Elisheva Broyer.
 * @since 18/04/2016.
 */
public class StringToExpression {
    public static Expression StringToExpression(String strExp) {
        int counter =0;
        int maxCount = 0;
        int maxIndex = 0;

        for (int i = 0; i < strExp.length() ;i++){
            switch (strExp.charAt(i)) {
                case '(':
                    counter++;
                    break;
                case ')':
                    counter--;
                    break;
                case ' ':
                    break;
                default:
                    if (strExp.charAt(i) == '+' || strExp.charAt(i) == '-' || strExp.charAt(i) == '/'
                            || strExp.charAt(i) == '*' || strExp.charAt(i) == '^'
                            || strExp.charAt(i) == 'l' || strExp.charAt(i) == 's'
                            || strExp.charAt(i) == 'c') {
                        if (counter > maxCount) {
                            maxIndex = i;
                        }
                    }
                    break;
            }
        }
        String leftSubString = strExp.substring(0, maxIndex);
        String rightSubString = strExp.substring(maxIndex+1, strExp.length());
        switch (strExp.charAt(maxIndex)) {
            case '+':
                return new Plus(StringToExpression(leftSubString),
                        StringToExpression(rightSubString));
            case '-':
                return new Minus(StringToExpression(leftSubString),
                        StringToExpression(rightSubString));
            case '*':
                return new Mult(StringToExpression(leftSubString),
                        StringToExpression(rightSubString));
            case '/':
                return new Div(StringToExpression(leftSubString),
                        StringToExpression(rightSubString));
            case '^':
                return new Pow(StringToExpression(leftSubString),
                        StringToExpression(rightSubString));
            case 'l':
                return new Log(StringToExpression(leftSubString),
                        StringToExpression(rightSubString));
            case 'c':
                int c1 = maxIndex+4;
                int c2 = maxIndex+4;
                while (strExp.charAt(c2) != ')') {
                    c2++;
                }
                String cosSubString = strExp.substring(c1, c2-1);
                return new Cos(StringToExpression(cosSubString));
            case 's':
                int s1 = maxIndex+4;
                int s2 = maxIndex+4;
                while (strExp.charAt(s2) != ')') {
                    s2++;
                }
                String sinSubString = strExp.substring(s1, s2-1);
                return new Cos(StringToExpression(sinSubString));
        }
        try{
            return new Num(Integer.parseInt(strExp));
        } catch (Exception e) {
            return new Var(strExp);
        }
    }
}
