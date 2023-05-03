package compilers.lexer.token.punctuation;

import compilers.lexer.token.empty.Whitespace;

public class Separator extends Punctuation {
    private enum Type {
        STATEMENT_SEPARATOR,
        LINE_SEPARATOR;
    }

    protected Separator(Type type, Object value) {
        super(type.toString(), value);
    }

    /**
     * Convert char c to LINE_SEPARATOR or STATEMENT_SEPARATOR
     * @return Separator instance or null if c is not a '\n' or ';'
     */
    public static Separator of(char c) {
        return switch (c) {
            case '\n' -> new Separator(Type.LINE_SEPARATOR, Whitespace.whitespaceOf(c));
            case ';' -> new Separator(Type.STATEMENT_SEPARATOR, Whitespace.whitespaceOf(c));
            default -> null;
        };
    }
}
