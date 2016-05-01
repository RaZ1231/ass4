package simplification;

import binary.Minus;
import binary.Mult;
import binary.Plus;
import operands.Const;
import operands.Num;
import operands.Var;
import structure.BinaryExpression;
import structure.Expression;
import unary.Neg;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Raziel Solomon
 * @since 01-May-16.
 */
public class EScanner {
    public Expression scan(Expression expression) {
        if (expression instanceof Var || expression instanceof Num || expression instanceof Const) {
            return expression;
        } else {
            return toExpression(addition(getLinearVars(expression)));
        }
    }

    protected List<Expression> getLinearVars(Expression expression) {
        return getLinearVars(expression, false);
    }

    protected List<Expression> getLinearVars(Expression expression, boolean negative) {
        List<Expression> expressions = new LinkedList<Expression>();

        if (expression instanceof Plus) {
            Plus plus = (Plus) expression;

            expressions.addAll(getLinearVars(plus.getA(), negative));
            expressions.addAll(getLinearVars(plus.getB(), negative));
        } else if (expression instanceof Minus) {
            Minus minus = (Minus) expression;

            expressions.addAll(getLinearVars(minus.getA(), negative));
            expressions.addAll(getLinearVars(minus.getB(), !negative));
        } else if (expression instanceof Neg) {
            Neg neg = (Neg) expression;

            expressions.addAll(getLinearVars(neg.getA(), !negative));
        } else if (negative) {
            expressions.add(new Neg(expression));
        } else {
            expressions.add(expression);
        }

        return expressions;
    }

    protected List<Expression> addition(List<Expression> expressions) {
        Expression sum;

        for (int i = 0; i < expressions.size() - 1; i++) {
            for (int j = i + 1; j < expressions.size(); j++) {
                sum = add(expressions.get(i), expressions.get(j));

                if (sum != null) {
                    expressions.set(j, sum.simplify());
                    expressions.set(i, new Num(0));
                    break;
                }
            }
        }

        return expressions;
    }

    protected Expression add(Expression e1, Expression e2) {
        if (e1.equals(e2)) {
            return new Mult(2, e1);
        } else if (e1 instanceof BinaryExpression && e2 instanceof BinaryExpression &&
                ((BinaryExpression) e1).getB().equals(((BinaryExpression) e2).getB())) {
            return new Mult(
                    new Plus(((BinaryExpression) e1).getA(), ((BinaryExpression) e2).getA()),
                    ((BinaryExpression) e1).getB());
        } else if (e1 instanceof BinaryExpression && ((BinaryExpression) e1).getB().equals(e2)) {
            return new Mult(new Plus(((BinaryExpression) e1).getA(), 1), e2);
        } else if (e2 instanceof BinaryExpression && ((BinaryExpression) e2).getB().equals(e1)) {
            return new Mult(new Plus(((BinaryExpression) e2).getA(), 1), e1);
        } else if (e1 instanceof Neg && e2 instanceof Neg) {
            return new Neg(add(((Neg) e1).getA(), ((Neg) e2).getA()));
        } else if (e1 instanceof Neg) {
            return sub(e2, ((Neg) e1).getA());
        } else if (e2 instanceof Neg) {
            return sub(e1, ((Neg) e2).getA());
        }

        return null;
    }

    protected Expression sub(Expression e1, Expression e2) {
        if (e1.equals(e2)) {
            return new Num(0);
        } else if (e1 instanceof BinaryExpression && e2 instanceof BinaryExpression &&
                ((BinaryExpression) e1).getB().equals(((BinaryExpression) e2).getB())) {
            return new Mult(
                    new Minus(((BinaryExpression) e1).getA(), ((BinaryExpression) e2).getA()),
                    ((BinaryExpression) e1).getB());
        } else if (e1 instanceof BinaryExpression && ((BinaryExpression) e1).getB().equals(e2)) {
            return new Mult(new Minus(((BinaryExpression) e1).getA(), 1), e2);
        } else if (e2 instanceof BinaryExpression && ((BinaryExpression) e2).getB().equals(e1)) {
            return new Mult(new Minus(((BinaryExpression) e2).getA(), 1), e1);
        } else if (e1 instanceof Neg && e2 instanceof Neg) {
            return sub(((Neg) e2).getA(), ((Neg) e1).getA());
        } else if (e1 instanceof Neg) {
            return new Neg(add(e2, ((Neg) e1).getA()));
        } else if (e2 instanceof Neg) {
            return add(e1, ((Neg) e2).getA());
        }

        return null;
    }

    protected Expression toExpression(List<Expression> expressions) {
        if (expressions.size() == 0) {
            return null;
        } else if (expressions.size() == 1) {
            return expressions.get(0);
        } else {
            return new Plus(expressions.get(0), toExpression(expressions.subList(1, expressions.size())));
        }
    }


}
