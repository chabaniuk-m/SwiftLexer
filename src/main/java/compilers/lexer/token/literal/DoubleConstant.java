package compilers.lexer.token.literal;

import java.util.concurrent.atomic.AtomicInteger;

public class DoubleConstant extends Literal {

    private enum Type {
        DOUBLE_CONSTANT,
        INVALID_DOUBLE_CONSTANT
    }
    private DoubleConstant(Type type, Double value, String extra, boolean isValid) {
        super(type.toString(), value, extra, isValid);
    }

    private DoubleConstant(Type type, Double value) {
        super(type.toString(), value);
    }

    /**
     * Converts following characters in the code to DOUBLE_CONSTANT or INVALID_DOUBLE_CONSTANT.
     * Text considered a double constant if it starts as number or begins with . and contains . or e (E)
     * @return DoubleConstant instance or null if text part is not considered to be a double constant
     */
    public static DoubleConstant of(String code, AtomicInteger beginIndex) {
        // TODO: DoubleConstant.of()

        throw new UnsupportedOperationException();
    }
}
