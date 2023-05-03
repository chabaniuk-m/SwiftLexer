package compilers.lexer.token.literal;

import java.util.concurrent.atomic.AtomicInteger;

public class RegexLiteral extends Literal {

    private enum Type {
        REGULAR_EXPRESSION_LITERAL,
        INVALID_REGULAR_EXPRESSION_LITERAL;
    }

    private RegexLiteral(String type, String value, String extra, boolean isValid) {
        super(type, value, extra, isValid);
    }

    /**
     * Converts text starting at beginIndex to REGULAR_EXPRESSION_LITERAL (valid or invalid)
     * @param beginIndex must point to '/' sign and don't be the beginning of the comment
     * @return not null RegexLiteral instance
     */
    public static RegexLiteral of(String code, AtomicInteger beginIndex) {
        // TODO: RegexLiteral.of() - find end of the literal
        throw new UnsupportedOperationException();
    }
}
