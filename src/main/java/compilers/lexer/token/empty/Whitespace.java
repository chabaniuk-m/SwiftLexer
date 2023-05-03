package compilers.lexer.token.empty;

import java.util.concurrent.atomic.AtomicInteger;

public class Whitespace extends Empty {
    private enum Type {
        WHITESPACE_SYMBOL,
        WHITESPACE_SEQUENCE;
    }

    private Whitespace(Type type, String value, String extra) {
        super(type.toString(), value, extra);
    }

    /**
     * Converts symbols starting at beginIndex to WHITESPACE_SEQUENCE or WHITESPACE_SYMBOL
     * saving each one explicitly (space = '\s', tab = '\t', ...).
     * Function process all whitespace characters except of '\n')
     * @param beginIndex must point to a whitespace character in code
     * @return not null whitespace
     */
    public static Whitespace of(String code, AtomicInteger beginIndex) {
        // TODO: Whitespace.of()
        throw new UnsupportedOperationException();
    }

    /**
     * Converts whitespace character to its explicit string form (' ' -> "\s", '\t' -> "\t")
     * @return explicit string representation of character
     * @throws IllegalArgumentException if c is not a whitespace character
     */
    public static String whitespaceOf(char c) throws IllegalArgumentException {
        return switch (c) {
            case ' ' -> "'\\s'";
            case '\t' -> "'\\t'";
            case '\n' -> "'\\n'";
            case '\r' -> "'\\r'";
            case '\f' -> "'\\f'";
            case (char)11 -> "\\v";
            default -> throw new IllegalArgumentException("'" + c + "' is not a whitespace character");
        };
    }

    /**
     * Provides official name for a whitespace character
     * @return name of a character
     * @throws IllegalArgumentException if c is not a whitespace character
     */
    public static String nameOfWhitespace(char c) throws IllegalArgumentException {
        return switch (c) {
            case ' ' -> "space";
            case '\t' -> "tab";
            case '\n' -> "line feed";
            case '\r' -> "carriage return";
            case '\f' -> "form feed";
            case (char)11 -> "vertical tab";
            default -> throw new IllegalArgumentException("'" + c + "' is not a whitespace character");
        };
    }
}
