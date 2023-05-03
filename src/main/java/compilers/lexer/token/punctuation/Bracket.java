package compilers.lexer.token.punctuation;

public class Bracket extends Punctuation {
    protected Bracket(char value, String extra) {
        super("BRACKET", value, extra);
    }

    /**
     * Convert char c to a proper  BRACKET: '}' or '{'
     * @return Bracket instance if c is a bracket symbol, null otherwise
     */
    public static Bracket of(char c) {
        return switch (c) {
            case '{' -> new Bracket(c, "opening curly bracket");
            case '}' -> new Bracket(c, "closing curly bracket");
            default -> null;
        };
    }
}
