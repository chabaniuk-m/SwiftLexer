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
     * Function process all whitespace characters except of '\n'.
     * If it is a single whitespace character, value will be "'\s'" for example,
     * otherwise add double quotation instead of single.
     * Method implemented as FA Machine
     * @param beginIndex must point to a whitespace character in code
     * @return Whitespace instance or null if beginIndex does not point to a whitespace character
     */
    public static Whitespace of(String code, AtomicInteger beginIndex) {
        
        int index = beginIndex.get();
        String ch;
        char c = code.charAt(index);
        
        // not a Whitespace token at all
        if ((ch = whitespaceOf(c)) == null) {
            return null;
        }

        int lastIndex = code.length() - 1;
        
        // each code string can only end with ' ' character 
        if (c == ' ' && index == lastIndex) {
            beginIndex.set(index + 1);
            return new Whitespace(Type.WHITESPACE_SYMBOL, "'" + ch + "'", nameOfWhitespace(c));
        }
        StringBuilder sb = new StringBuilder(ch);
        
        // single whitespace character
        c = code.charAt(++index);
        if ((ch = whitespaceOf(c)) == null) {
            beginIndex.set(index);
            return new Whitespace(Type.WHITESPACE_SYMBOL, "'" + sb + "'", nameOfWhitespace(code.charAt(index - 1)));
        }
        
        // sequence of whitespaces
        do {
            sb.append(ch);
            if (c == ' ' && index == lastIndex) {
                ++index;
                break;
            }
            c = code.charAt(++index);
        } while ((ch = whitespaceOf(c)) != null);

        beginIndex.set(index);
        return new Whitespace(Type.WHITESPACE_SEQUENCE, "\"" + sb + '"', "");
    }

    public Whitespace cloneWithoutLastSpace() {
        if (this.value.equals("'\\s'")) {
            return null;
        } else {
            String v = (String) this.value;
            if (v.charAt(v.length() - 2) != 's') {
                throw new RuntimeException("last symbol of Whitespace sequence is not a space");
            }
            var value = v.substring(1, v.length() - 3);
            if (value.length() == 2) {
                var extra = switch (value.charAt(1)) {
                    case 's' -> "space";
                    case 't' -> "tab";
                    case 'r' -> "carriage return";
                    case 'f' -> "form feed";
                    case 'v' -> "vertical tab";
                    default -> throw new RuntimeException("malformed whitespace instance");
                };
                return new Whitespace(Type.WHITESPACE_SYMBOL, "'" + value + "'", extra);
            } else {
                return new Whitespace(Type.WHITESPACE_SEQUENCE, '"' + value + '"', "");
            }
        }
    }

    /**
     * Converts whitespace character to its explicit string form (' ' -> "\s", '\t' -> "\t")
     * @return explicit string representation of character or null if c is not a whitespace character or '\n'
     */
    public static String whitespaceOf(char c) {
        return switch (c) {
            case ' ' -> "\\s";
            case '\t' -> "\\t";
//            case '\n' -> "\\n";              // for swift lexer line feed is a separator, not a whitespace
            case '\r' -> "\\r";
            case '\f' -> "\\f";
            case (char)11 -> "\\v";
            default -> null;
        };
    }

    /**
     * Provides official name for a whitespace character
     * @return name of a character or null if c is not a whitespace character or '\n'
     */
    public static String nameOfWhitespace(char c) {
        return switch (c) {
            case ' ' -> "space";
            case '\t' -> "tab";
//            case '\n' -> "line feed";            // for swift lexer line feed is a separator, not a whitespace
            case '\r' -> "carriage return";
            case '\f' -> "form feed";
            case (char)11 -> "vertical tab";
            default -> null;
        };
    }
}
