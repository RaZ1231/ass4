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
        String leftSubString = "";
        String rightSubString = "";

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
                            || strExp.charAt(i) == 'l' ||
                            ((strExp.charAt(i) == 's') && (strExp.charAt(i+1) == 'i'))
                            || strExp.charAt(i) == 'c' || strExp.charAt(i) == '#') {
                        if (counter > maxCount) {
                            maxIndex = i;
                        }
                    }
                    break;
            }
        }
        if ((maxIndex>0) && strExp.charAt(maxIndex) !='c' && strExp.charAt(maxIndex) !='s') {
            if (strExp.charAt(maxIndex) == '^'){
                leftSubString = strExp.substring(1, maxIndex);
                rightSubString = strExp.substring(maxIndex+1, strExp.length()-1);
            } else {
                leftSubString = strExp.substring(1, maxIndex-1);
                rightSubString = strExp.substring(maxIndex+2, strExp.length()-1);
            }

        }
        switch (strExp.charAt(maxIndex)) {
            case '+':
                return new Plus(StringToExpression(leftSubString),
                        StringToExpression(rightSubString));
            case '-':
                if (maxIndex>0) {
                    return new Minus(StringToExpression(leftSubString),
                            StringToExpression(rightSubString));
                }
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
                String cosSubString = strExp.substring(c1, c2);
                return new Cos(StringToExpression(cosSubString));
            case 's':
                int s1 = maxIndex+4;
                int s2 = maxIndex+5;
                while (strExp.charAt(s2) != ')') {
                    s2++;
                }
                String sinSubString = strExp.substring(s1, s2);
                return new Cos(StringToExpression(sinSubString));
            case '#':
                int index = maxIndex;
                while (Character.isDigit(strExp.charAt(index))){
                    index++;
                }
                String sub = strExp.substring(maxIndex, index);
                return new Tag(sub);
        }
        try{
            return new Num(Double.parseDouble(strExp));
        } catch (Exception e) {
            return new Var(strExp);
        }
    }
}
