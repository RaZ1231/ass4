package binary;

import operands.Num;
import operands.Var;
import structure.BinaryExpression;
import structure.Expression;

/**
 * @author Raziel Solomon
 * @since 11-Apr-16.
 */
public class Mult extends BinaryExpression implements Expression {
    /**
     * constructor.
     *
     * @param a a double variable.
     * @param b another double variable.
     */
    public Mult(double a, double b) {
        this(new Num(a), new Num(b));
    }

    /**
     * constructor.
     *
     * @param a an expression.
     * @param b another expression.
     */
    public Mult(Expression a, Expression b) {
        super(a, b);
    }

    /**
     * constructor.
     *
     * @param a a double variable.
     * @param b a string variable.
     */
    public Mult(double a, String b) {
        this(new Num(a), new Var(b));
    }

    /**
     * constructor.
     *
     * @param a a string variable.
     * @param b a double variable.
     */
    public Mult(String a, double b) {
        this(new Var(a), new Num(b));
    }

    /**
     * constructor.
     *
     * @param a a string variable.
     * @param b a string variable.
     */
    public Mult(String a, String b) {
        this(new Var(a), new Var(b));
    }

    /**
     * an operation function.
     *
     * @param a a parameter.
     * @param b another parameter.
     * @return operation result.
     */
    @Override
    public double operate(double a, double b) {
        return a * b;
    }

    /**
     * returns new expression by type.
     *
     * @param a an expression.
     * @param b another expression.
     * @return a new expression by type.
     */
    @Override
    public Expression create(Expression a, Expression b) {
        return new Mult(a, b);
    }

    /**
     * returns the derivative of an expression.
     *
     * @param var a string variable.
     * @return the derivative of an expression.
     */
    @Override
    public Expression derivative(String var) {
        return new Plus(new Mult(getA().differentiate(var), getB()),
                new Mult(getA(), getB().differentiate(var)));
    }

    private Expression simpleLogic(double a, Expression b) throws Exception {
        if (a == 0) {
            return new Num(0);
        } else if (a == 1) {
            return b;
        } else {
            throw new Exception("Nothing to simplify");
        }
    }

    /**
     * Returned a simplified version of the current expression.
     *
     * @return simplified expression
     */
    @Override
    public Expression simple() {
        // second attempt
        try {
            double value;
            Expression otherSon;

            try {
                value = getA().evaluate();
                otherSon = getB();
            } catch (Exception e) {
                value = getB().evaluate();
                otherSon = getA();
            }

            if (value == 0) {
                return new Num(0);
            } else if (value == 1) {
                return otherSon;
            } else {
                throw new Exception("Nothing to simplify");
            }
        } catch (Exception NotValue) {
            if (getA().equals(getB())) {
                return new Pow(getA(), new Num(2));
            } else {
                return new Mult(getA(), getB());
            }
        }


        /** first attempt
         try {
         return simpleLogic(getA().evaluate(), getB());
         } catch (Exception e) {
         try {
         return simpleLogic(getB().evaluate(), getA());
         } catch (Exception e1) {
         if (getA().equals(getB())) {
         return new Pow(getA(), new Num(2));
         } else {
         return new Mult(getA(), getB());
         }
         }
         }
         */

        /**
         if (getA().isOne()) {
         return getB();
         } else if (getB().isOne()) {
         return getA();
         } else if (getA().equals(getB())) {
         return new Pow(getA(), new Num(2));
         } else if (getA().isZero() || getB().isZero()) {
         return new Num(0);
         } else {
         return new Mult(getA(), getB());
         }
         */
    }

    /**
     * returns a nice string representation of the expression.
     *
     * @return string representation.
     */
    @Override
    public String toString() {
        return "(" + getA() + " * " + getB() + ")";
    }
}
