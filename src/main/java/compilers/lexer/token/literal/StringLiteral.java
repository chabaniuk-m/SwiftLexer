package compilers.lexer.token.literal;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class StringLiteral extends Literal {
    private enum Type {
        SINGLE_LINE_STRING,
        MULTILINE_STRING,
        TEMPLATE_STRING,
        MULTILINE_TEMPLATE_STRING,
        INVALID_TEMPLATE_STRING,
        INVALID_MULTILINE_TEMPLATE_STRING,
        INVALID_SINGLE_LINE_STRING,
        INVALID_MULTILINE_STRING;
    }
    private StringLiteral(Type type, String value, String extra) {
        super(type.toString(), valueOf(type, value), extra);
    }

    private static String valueOf(Type type, String value) {
        return switch (type) {
            case SINGLE_LINE_STRING, TEMPLATE_STRING,
                    INVALID_TEMPLATE_STRING, INVALID_SINGLE_LINE_STRING  -> value;
            case MULTILINE_STRING, MULTILINE_TEMPLATE_STRING,
                    INVALID_MULTILINE_TEMPLATE_STRING, INVALID_MULTILINE_STRING -> "\n" + value;
        };
    }

    /**
     * Converts string to a proper StringLiteral (single line, multiline, template),
     * filling list of inner expression if needed
     * @param code swift code to parse
     * @param beginIndex index of beginning '"'
     * @param innerExpression if value is a template string, innerExpression fills with template expressions
     */
    public static StringLiteral of(String code, AtomicInteger beginIndex, List<String> innerExpression) {
        // TODO: StringLiteral.of()
        boolean isMultiline = false;

        throw new UnsupportedOperationException();
    }
}
