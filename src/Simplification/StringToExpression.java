package Simplification;

import binary.Div;
import binary.Log;
import binary.Minus;
import binary.Mult;
import binary.Plus;
import binary.Pow;
import java.util.Objects;
import operands.Num;
import operands.Var;
import structure.BinaryExpression;
import structure.Expression;
import unary.Cos;

/**
 * @author Elisheva Broyer.
 * @since 18/04/2016.
 */
public class StringToExpression {
    /*public static Expression StringToExpression(String strExp) {
        //int counter =0;
        int opCount = 0;
        int clCount = 0;
        int lastCount = 0;
        int maxIndex = 0;
        String leftSubString = "";
        String rightSubString = "";

        for (int i = 0; i < strExp.length() ;i++){
            switch (strExp.charAt(i)) {
                case '(':
                    //counter++;
                    opCount++;
                    break;
                case ')':
                    //counter--;
                    clCount++;
                    break;
                case ' ':
                    break;
                default:
                    if (strExp.charAt(i) == '+' || strExp.charAt(i) == '-' || strExp.charAt(i) == '/'
                            || strExp.charAt(i) == '*' || strExp.charAt(i) == '^'
                            || strExp.charAt(i) == 'l' ||
                            ((strExp.charAt(i) == 's') && (strExp.charAt(i+1) == 'i'))
                            || strExp.charAt(i) == 'c' || strExp.charAt(i) == '#') {
                        //if (counter >= lastCount) {
                         if (opCount-clCount <= 1){
                            maxIndex = i;
                            //lastCount = counter;
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
    }*/

    public static Expression StringToExpression(String str, String type) {
        Expression l = null;
        Expression r = null;
        Expression p = null;
        int count = 0;
        int i = 0;
        if (str.charAt(0) == '('){
            l = StringToExpression(str.substring(1), "L");
            for(i = 0; i < str.length(); i++){
                if (str.charAt(i) == '('){
                    count++;
                }else if (str.charAt(i) == ')'){
                    count--;
                }
                if (count == 0){
                    break;
                }
            }
            if (i == str.length()-1){
                return null;
            }
            p = StringToExpression(str.substring(i), null);
            //p.setA(l);///////////////////
            return p;
        }
        if (type.equals("L")) {
            for(i = 0; i < str.length(); i++){
                if (str.charAt(i) == '+' || str.charAt(i) == '-' || str.charAt(i) == '/'
                        || str.charAt(i) == '*' || str.charAt(i) == '^'
                        || str.charAt(i) == 'l' || str.charAt(i) == 'c'
                        || ((str.charAt(i) == 's') && (str.charAt(i+1) == 'i'))) {
                    break;
                }
            }
            p = StringToExpression(str.substring(i), null);
            /////current
            //p.setA(current);//////////////////
            return p;
        }
        if (type.equals("R")) {
            for (i = 0; i < str.length(); i++) {
                if (str.charAt(i) == ')') {
                    break;
                }
            }
            String subS = str.substring(0, i - 1);
            try {
                return new Num(Double.parseDouble(subS));
            } catch (Exception ex) {
                return new Var(subS);
            }
        }
        if (type.equals("oper")) {
            r = StringToExpression(str.substring(1), "oper");
            //static current.setB(r);
            //return current;
        }
        return null;
    }
}
