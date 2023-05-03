package compilers.lexer.token.operator;

import compilers.lexer.token.Token;

import java.util.concurrent.atomic.AtomicInteger;

public class Operator extends Token {
    private enum Type {
        INVALID_OPERATOR,               // ++= --= or ---= must be considered a valid operator
        PRIMARY_EXPRESSION_OPERATOR,    // () [] .
        UNARY_OPERATOR,                          // ! ? + -
        ARITHMETIC_OPERATOR,                     // + - * / %
        COMPARISON_OPERATOR,                     // == != > < >= <=
        OPERATOR,                       // parentheses
        NIL_COALESCING_OPERATOR,                 // ??
        COMMA_OPERATOR,
        ASSIGNMENT_OPERATOR,                     // += = *= <<= |=
        RANGE_OPERATOR                           // ... ..<
    }

    public Operator(Type type, Object value, String extra, boolean isValid) {
        super(type.toString(), value, extra, isValid);
    }

    /**
     * Converts the operator starting at beginIndex to a proper OPERATOR instance.
     * If beginIndex points to a non-operator sign, null will be returned
     * @param beginIndex first index of the operator
     * @param isPrevTokenIdentifier whether preceding token is identifier token or not
     * @return Operator instance if beginIndex points to a valid operator sign
     */
    public static Operator of(String code, AtomicInteger beginIndex, boolean isPrevTokenIdentifier) {
        // TODO: Operator.of()
        throw new UnsupportedOperationException();
    }
}
