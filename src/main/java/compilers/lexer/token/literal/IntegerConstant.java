package compilers.lexer.token.literal;

import java.util.concurrent.atomic.AtomicInteger;

public class IntegerConstant extends Literal {

    private enum Type {
        INTEGER_CONSTANT,
        INVALID_INTEGER_CONSTANT
    }
    private IntegerConstant(Integer value) {
        super("INTEGER_CONSTANT", value);
    }

    private IntegerConstant(Type type, Double value, String extra, boolean isValid) {
        super(type.toString(), value, extra, isValid);
    }

    private IntegerConstant(Type type, Double value) {
        super(type.toString(), value);
    }

    /**
     * If word that starts with begin index in code is considered to be an IntegerConstant (starts with - or digit
     * and contains at least one digit) converts that word to INTEGER_CONSTANT or INVALID_INTEGER_CONSTANT
     */
    public static IntegerConstant of(String code, AtomicInteger beginIndex) {
        // TODO: IntegerConstant.of()

        throw new UnsupportedOperationException();
    }
}
